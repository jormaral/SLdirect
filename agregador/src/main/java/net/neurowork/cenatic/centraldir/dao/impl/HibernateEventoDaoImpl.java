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
import net.neurowork.cenatic.centraldir.dao.EventoDao;
import net.neurowork.cenatic.centraldir.model.satelite.Organizacion;
import net.neurowork.cenatic.centraldir.model.satelite.OrganizacionEvento;

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
public class HibernateEventoDaoImpl extends HibernateDaoSupport implements EventoDao {
	private final static Logger logger = LoggerFactory.getLogger(HibernateEventoDaoImpl.class);

	@Override
	public List<OrganizacionEvento> getAll() throws DaoException {
		if(logger.isTraceEnabled())
			logger.trace("Loading all OrganizacionEvento");
		return getHibernateTemplate().loadAll(OrganizacionEvento.class);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<OrganizacionEvento> findByOrganizacion(Organizacion organizacion) throws DaoException {
		if(logger.isTraceEnabled())
			logger.trace("Hibernate Searching OrganizacionEvento with organizacion:" + organizacion);
		DetachedCriteria criteria = DetachedCriteria.forClass(OrganizacionEvento.class);
		criteria.add(Restrictions.eq("organizacion", organizacion));
		return (List<OrganizacionEvento>)getHibernateTemplate().findByCriteria(criteria);
	}
	
	
	@Override
	public OrganizacionEvento findById(int eventoId) throws DaoException {
		if(logger.isTraceEnabled())
			logger.trace("Hibernate Searching OrganizacionEvento with id:" + eventoId);
		return getHibernateTemplate().load(OrganizacionEvento.class, eventoId);
	}

	@Override
	public void delete(OrganizacionEvento evento) throws DaoException {
		if(logger.isTraceEnabled())
			logger.trace("Hibernate Deleting OrganizacionEvento:" + evento);		
		getHibernateTemplate().delete(evento);
	}
	
}
