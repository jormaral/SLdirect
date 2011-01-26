/*
 * Copyright 2010 CENATIC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package net.neurowork.cenatic.centraldir.dao.impl;

import java.util.List;

import net.neurowork.cenatic.centraldir.dao.DaoException;
import net.neurowork.cenatic.centraldir.dao.DemandaDao;
import net.neurowork.cenatic.centraldir.model.satelite.Organizacion;
import net.neurowork.cenatic.centraldir.model.satelite.OrganizacionCapacidadDemanda;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 *
 * @author Alejandro Sanchez Acosta <asanchez@neurowork.net> http://www.neurowork.net
 * @since 14/12/2010
 */
public class HibernateDemandaDaoImpl extends HibernateDaoSupport implements DemandaDao {
	private final static Logger logger = LoggerFactory.getLogger(HibernateDemandaDaoImpl.class);

	@Override
	public List<OrganizacionCapacidadDemanda> getAll() throws DaoException {
		if(logger.isTraceEnabled())
			logger.trace("Loading all OrganizacionCapacidadDemanda");
		return getHibernateTemplate().loadAll(OrganizacionCapacidadDemanda.class);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<OrganizacionCapacidadDemanda> findByOrganizacion(Organizacion organizacion) throws DaoException {
		if(logger.isTraceEnabled())
			logger.trace("Hibernate Searching OrganizacionCapacidadDemanda with organizacion:" + organizacion);
		DetachedCriteria criteria = DetachedCriteria.forClass(OrganizacionCapacidadDemanda.class);
		criteria.add(Restrictions.eq("organizacion", organizacion));
		return (List<OrganizacionCapacidadDemanda>)getHibernateTemplate().findByCriteria(criteria);
	}
	
	
	@Override
	public OrganizacionCapacidadDemanda findById(int demandaId) throws DaoException {
		if(logger.isTraceEnabled())
			logger.trace("Hibernate Searching OrganizacionCapacidadDemanda with id:" + demandaId);
		return getHibernateTemplate().load(OrganizacionCapacidadDemanda.class, demandaId);
	}

	@Override
	public void delete(OrganizacionCapacidadDemanda demanda) throws DaoException {
		if(logger.isTraceEnabled())
			logger.trace("Hibernate Deleting OrganizacionCapacidadDemanda:" + demanda);
		
		getHibernateTemplate().delete(demanda);
	}
	
}
