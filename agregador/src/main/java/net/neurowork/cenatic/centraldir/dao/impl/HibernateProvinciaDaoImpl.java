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

import net.neurowork.cenatic.centraldir.dao.CantDeleteEntityException;
import net.neurowork.cenatic.centraldir.dao.DaoException;
import net.neurowork.cenatic.centraldir.dao.ProvinciaDao;
import net.neurowork.cenatic.centraldir.model.satelite.Provincia;

import org.hibernate.ObjectNotFoundException;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.exception.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 *
 * @author Alejandro Sanchez Acosta <asanchez@neurowork.net> http://www.neurowork.net
 * @since 06/12/2010
 */
public class HibernateProvinciaDaoImpl extends HibernateDaoSupport implements ProvinciaDao {
	private final static Logger logger = LoggerFactory.getLogger(HibernateProvinciaDaoImpl.class);

	@SuppressWarnings("unchecked")
	@Override
	public List<Provincia> findByName(String name) throws DaoException {
		if(logger.isTraceEnabled())
			logger.trace("Hibernate Searching Provincia with name:" + name);
		DetachedCriteria criteria = DetachedCriteria.forClass(Provincia.class);
		criteria.add(Restrictions.like("name", "%"+name+"%"));
		criteria.addOrder(Order.asc("name"));		
		return (List<Provincia>)getHibernateTemplate().findByCriteria(criteria);
	}

	@Override
	public void save(Provincia provincia) throws DaoException {
		if(logger.isTraceEnabled())
			logger.trace("Saving Provincia: " + provincia);
		getHibernateTemplate().saveOrUpdate(provincia);
	}
	
	public Provincia findById(Integer id) throws DaoException {
		if(logger.isTraceEnabled())
			logger.trace("Loading Provincia width id: " + id);
		try{
			return getHibernateTemplate().load(Provincia.class, id);
		} catch (ObjectNotFoundException e){
			return null;
		} catch (DataAccessException e) {
			throw new DaoException(e.getMessage());
		}
	}

	@Override
	public Collection<Provincia> getProvincias() throws DaoException {
		logger.trace("Loading all Provincias");
		return getHibernateTemplate().loadAll(Provincia.class);
	}

	@Override
	public void delete(Provincia provincia) throws DaoException {
		if(logger.isTraceEnabled())
			logger.trace("Deleting Provincia:" + provincia);
		
		try {
			getHibernateTemplate().delete(provincia);
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
