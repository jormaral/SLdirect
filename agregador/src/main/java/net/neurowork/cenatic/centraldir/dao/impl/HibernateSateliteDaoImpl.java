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
import net.neurowork.cenatic.centraldir.dao.SateliteDao;
import net.neurowork.cenatic.centraldir.model.Satelite;
import net.neurowork.cenatic.centraldir.model.satelite.OrganizacionSatelite;

import org.hibernate.ObjectNotFoundException;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 *
 * @author Alejandro Sanchez Acosta <asanchez@neurowork.net> http://www.neurowork.net
 * @since 09/11/2010
 */
public class HibernateSateliteDaoImpl extends HibernateDaoSupport implements SateliteDao {
	private final static Logger logger = LoggerFactory.getLogger(HibernateSateliteDaoImpl.class);
	
	@Override
	public List<Satelite> getSatelites() throws DaoException {
		logger.trace("Loading all Satelites");
		return getHibernateTemplate().loadAll(Satelite.class);
	}

	@Override
	public Satelite loadSatelite(Integer id) throws DaoException {
		logger.trace("Loading Satelite width id: " + id);
		try{
			return getHibernateTemplate().load(Satelite.class, id);
		} catch (ObjectNotFoundException e){
			return null;
		} catch (DataAccessException e) {
			throw new DaoException(e.getMessage());
		}
	}

	@Override
	public void saveSatelite(Satelite satelite) throws DaoException {
		logger.trace("Saving Satelite: " + satelite);
		getHibernateTemplate().saveOrUpdate(satelite);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Collection<Satelite> findSatelite(String name) {
		if(logger.isTraceEnabled())
			logger.trace("Hibernate Searching Satelites with name:" + name);
		DetachedCriteria criteria = DetachedCriteria.forClass(Satelite.class);
		criteria.add(Restrictions.like("name", "%"+name+"%"));
		criteria.add(Restrictions.like("hostUrl", "%"+name+"%"));
		criteria.add(Restrictions.like("user", "%"+name+"%"));
		criteria.addOrder(Order.asc("user"));		
		return (List<Satelite>)getHibernateTemplate().findByCriteria(criteria);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Collection<Satelite> findActivado() throws DaoException {
		if(logger.isTraceEnabled())
			logger.trace("Hibernate Searching Active Satelites");
		DetachedCriteria criteria = DetachedCriteria.forClass(Satelite.class);
		criteria.add(Restrictions.eq("activado", Boolean.TRUE));
		criteria.addOrder(Order.asc("user"));		
		return (List<Satelite>)getHibernateTemplate().findByCriteria(criteria);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Integer getOrganizacionesCount(Satelite satelite) throws DaoException {
		if(logger.isTraceEnabled())
			logger.trace("Hibernate Searching Total Organizaciones");
		DetachedCriteria criteria = DetachedCriteria.forClass(OrganizacionSatelite.class);
		
		ProjectionList projList = Projections.projectionList();
		projList.add(Projections.rowCount()); 		
		criteria.setProjection(projList);
		criteria.add(Restrictions.eq("satelite", satelite));
		
		List result = getHibernateTemplate().findByCriteria(criteria);
		if(result != null)
			return (Integer)result.get(0);
		return 0;

	}
}
