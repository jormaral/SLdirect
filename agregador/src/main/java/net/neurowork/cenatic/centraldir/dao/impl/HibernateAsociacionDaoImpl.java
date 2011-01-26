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

import net.neurowork.cenatic.centraldir.dao.AsociacionDao;
import net.neurowork.cenatic.centraldir.dao.CantDeleteEntityException;
import net.neurowork.cenatic.centraldir.dao.DaoException;
import net.neurowork.cenatic.centraldir.model.satelite.Asociacion;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.exception.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 *
 * @author Alejandro Sanchez Acosta <asanchez@neurowork.net> http://www.neurowork.net
 * @since 03/12/2010
 */
public class HibernateAsociacionDaoImpl extends HibernateDaoSupport implements AsociacionDao {
	private final static Logger logger = LoggerFactory.getLogger(HibernateAsociacionDaoImpl.class);


	@Override
	public void save(Asociacion asociacion) throws DaoException {
		if(logger.isTraceEnabled())
			logger.trace("Saving Asociacion: " + asociacion);
		getHibernateTemplate().saveOrUpdate(asociacion);		
	}

	@Override
	public Asociacion findById(Integer id) throws DaoException {
		if(logger.isTraceEnabled())
			logger.trace("Loading Asociacion width id: " + id);
		return getHibernateTemplate().load(Asociacion.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Collection<Asociacion> findByName(String name) throws DaoException {
		if(logger.isTraceEnabled())
			logger.trace("Hibernate Searching Asociacion with name:" + name);
		DetachedCriteria criteria = DetachedCriteria.forClass(Asociacion.class);
		criteria.add(Restrictions.like("name", "%"+name+"%"));
		criteria.addOrder(Order.asc("name"));		
		return (List<Asociacion>)getHibernateTemplate().findByCriteria(criteria);
	}

	@Override
	public Collection<Asociacion> getAll() throws DaoException {
		if(logger.isTraceEnabled())
			logger.trace("Loading all Asociaciones");
		return getHibernateTemplate().loadAll(Asociacion.class);
	}

	@Override
	public void delete(Asociacion asociacion) throws DaoException {
		if(logger.isTraceEnabled())
			logger.trace("Deleting Asociacion:" + asociacion);
		
		try {
			getHibernateTemplate().delete(asociacion);
			if(logger.isTraceEnabled())
				logger.trace("Deleting Complete. Flusing");
			getHibernateTemplate().flush();
			if(logger.isTraceEnabled())
				logger.trace("Flusing Complete");
		} catch (ConstraintViolationException e) {
			throw new CantDeleteEntityException(e.getMessage());
		}
	}
}