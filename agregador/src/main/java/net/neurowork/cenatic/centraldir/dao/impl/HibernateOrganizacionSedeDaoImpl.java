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

import java.util.Collection;
import java.util.List;

import net.neurowork.cenatic.centraldir.dao.DaoException;
import net.neurowork.cenatic.centraldir.dao.OrganizacionSedeDao;
import net.neurowork.cenatic.centraldir.model.satelite.Organizacion;
import net.neurowork.cenatic.centraldir.model.satelite.OrganizacionSede;
import net.neurowork.cenatic.centraldir.model.satelite.Provincia;

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
public class HibernateOrganizacionSedeDaoImpl extends HibernateDaoSupport implements OrganizacionSedeDao {
	private final static Logger logger = LoggerFactory.getLogger(HibernateOrganizacionSedeDaoImpl.class);

	@Override
	public List<OrganizacionSede> getAll() throws DaoException {
		if(logger.isTraceEnabled())
			logger.trace("Loading all OrganizacionSede");
		return getHibernateTemplate().loadAll(OrganizacionSede.class);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<OrganizacionSede> findByOrganizacion(Organizacion organizacion) throws DaoException {
		if(logger.isTraceEnabled())
			logger.trace("Hibernate Searching OrganizacionSede with organizacion:" + organizacion);
		DetachedCriteria criteria = DetachedCriteria.forClass(OrganizacionSede.class);
		criteria.add(Restrictions.eq("organizacion", organizacion));
		return (List<OrganizacionSede>)getHibernateTemplate().findByCriteria(criteria);
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	public Collection<OrganizacionSede> findSedeByAddressLocalityProvince(String address,
			String locality, Provincia provincia) throws DaoException {
		DetachedCriteria criteria = DetachedCriteria.forClass(OrganizacionSede.class);
		criteria.add(Restrictions.eq("direccion", address));
		criteria.add(Restrictions.eq("localidad", locality));
		criteria.add(Restrictions.eq("provincia", provincia));
		criteria.addOrder(Order.asc("id"));		
		return (List<OrganizacionSede>)getHibernateTemplate().findByCriteria(criteria);
	}

	@Override
	public OrganizacionSede findById(int sedeId) throws DaoException {
		if(logger.isTraceEnabled())
			logger.trace("Hibernate Searching OrganizacionSede with id:" + sedeId);
		return getHibernateTemplate().load(OrganizacionSede.class, sedeId);
	}

	@Override
	public void delete(OrganizacionSede sede) throws DaoException {
		if(logger.isTraceEnabled())
			logger.trace("Hibernate Deleting OrganizacionSede:" + sede);
		
		getHibernateTemplate().delete(sede);
	}

	@Override
	public void save(OrganizacionSede sede) throws DaoException {
		if(logger.isTraceEnabled())
			logger.trace("Hibernate Saving OrganizacionSede:" + sede);
		getHibernateTemplate().saveOrUpdate(sede);
	}
	
}
