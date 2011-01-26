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
import net.neurowork.cenatic.centraldir.dao.OrganizacionAsociacionDao;
import net.neurowork.cenatic.centraldir.model.satelite.Organizacion;
import net.neurowork.cenatic.centraldir.model.satelite.OrganizacionAsociacion;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 *
 * @author Alejandro Sanchez Acosta <asanchez@neurowork.net> http://www.neurowork.net
 * @since 14/12/2010
 */
public class HibernateOrganizacionAsociacionDaoImpl extends HibernateDaoSupport implements OrganizacionAsociacionDao {
	private final static Logger logger = LoggerFactory.getLogger(HibernateOrganizacionAsociacionDaoImpl.class);

	@Override
	public List<OrganizacionAsociacion> getAll() throws DaoException {
		if(logger.isTraceEnabled())
			logger.trace("Loading all OrganizacionAsociacion");
		return getHibernateTemplate().loadAll(OrganizacionAsociacion.class);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<OrganizacionAsociacion> findByOrganizacion(Organizacion organizacion) throws DaoException {
		if(logger.isTraceEnabled())
			logger.trace("Hibernate Searching OrganizacionAsociacion with organizacion:" + organizacion);
		DetachedCriteria criteria = DetachedCriteria.forClass(OrganizacionAsociacion.class);
		criteria.add(Restrictions.eq("organizacion", organizacion));
		criteria.addOrder(Order.asc("name"));		
		return (List<OrganizacionAsociacion>)getHibernateTemplate().findByCriteria(criteria);
	}
	
	
	@Override
	public OrganizacionAsociacion findById(int oaId) throws DaoException {
		if(logger.isTraceEnabled())
			logger.trace("Hibernate Searching OrganizacionAsociacion with id:" + oaId);
		return getHibernateTemplate().load(OrganizacionAsociacion.class, oaId);
	}

	@Override
	public void delete(OrganizacionAsociacion sede) throws DaoException {
		if(logger.isTraceEnabled())
			logger.trace("Hibernate Deleting OrganizacionAsociacion:" + sede);
		
		getHibernateTemplate().delete(sede);
	}
	
}
