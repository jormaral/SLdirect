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
import net.neurowork.cenatic.centraldir.dao.FormaJuridicaDao;
import net.neurowork.cenatic.centraldir.model.satelite.FormaJuridica;

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
 * @since 17/12/2010
 */
public class HibernateFormaJuridicaDaoImpl extends HibernateDaoSupport implements FormaJuridicaDao {
	private final static Logger logger = LoggerFactory.getLogger(HibernateFormaJuridicaDaoImpl.class);

	@SuppressWarnings("unchecked")
	@Override
	public List<FormaJuridica> findByName(String name) throws DaoException {
		if(logger.isTraceEnabled())
			logger.trace("Hibernate Searching FormaJuridica with name:" + name);
		DetachedCriteria criteria = DetachedCriteria.forClass(FormaJuridica.class);
		criteria.add(Restrictions.like("name", "%"+name+"%"));
		criteria.addOrder(Order.asc("name"));		
		return (List<FormaJuridica>)getHibernateTemplate().findByCriteria(criteria);
	}

	@Override
	public void save(FormaJuridica formaJuridica) throws DaoException {
		if(logger.isTraceEnabled())
			logger.trace("Saving FormaJuridica: " + formaJuridica);
		getHibernateTemplate().saveOrUpdate(formaJuridica);		
	}

	@Override
	public FormaJuridica findById(Integer id) throws DaoException {
		if(logger.isTraceEnabled())
			logger.trace("Loading FormaJuridica width id: " + id);
		try{
			return getHibernateTemplate().load(FormaJuridica.class, id);
		} catch (ObjectNotFoundException e){
			return null;
		} catch (DataAccessException e) {
			throw new DaoException(e.getMessage());
		}
	}	
	
	@Override
	public Collection<FormaJuridica> getAll() throws DaoException {
		if(logger.isTraceEnabled())
			logger.trace("Loading all FormaJuridicas");
		return getHibernateTemplate().loadAll(FormaJuridica.class);
	}

	@Override
	public void delete(FormaJuridica formaJuridica) throws DaoException, CantDeleteEntityException {
		if(logger.isTraceEnabled())
			logger.trace("Deleting FormaJuridica:" + formaJuridica);
		
		try {
			getHibernateTemplate().delete(formaJuridica);
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
