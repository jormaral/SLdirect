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
import net.neurowork.cenatic.centraldir.dao.UserDao;
import net.neurowork.cenatic.centraldir.model.User;

import org.hibernate.ObjectNotFoundException;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 *
 * @author Alejandro Sanchez Acosta <asanchez@neurowork.net> http://www.neurowork.net
 * @since 10/11/2010
 */
public class HibernateUserDaoImpl extends HibernateDaoSupport implements UserDao {
	private final static Logger logger = LoggerFactory.getLogger(HibernateUserDaoImpl.class);

	public User loadUser(String username) throws DaoException {
		if(logger.isTraceEnabled())
			logger.trace("Hibernate Loading User:" + username);
		try{
			return getHibernateTemplate().load(User.class, username);
		} catch (ObjectNotFoundException e){
			return null;
		} catch (DataAccessException e) {
			throw new DaoException(e.getMessage());
		}
	}

	public void saveUser(User user) throws DaoException {
		if(logger.isTraceEnabled())
			logger.trace("Hibernate Saving User:" + user);
		getHibernateTemplate().saveOrUpdate(user);
	}

	
	@SuppressWarnings("unchecked")
	public Collection<User> findUsers(String username) throws DaoException {
		if(logger.isTraceEnabled())
			logger.trace("Hibernate Searching Users with username:" + username);
		DetachedCriteria criteria = DetachedCriteria.forClass(User.class);
		criteria.add(Restrictions.like("username", "%"+username+"%"));
		criteria.addOrder(Order.asc("username"));		
		return (List<User>)getHibernateTemplate().findByCriteria(criteria);
	}

	@Override
	public Collection<User> getUsers() throws DaoException {
		if(logger.isTraceEnabled())
			logger.trace("Hibernate Retrieving all Users.");
		Collection<User> ret = null;
		ret = getHibernateTemplate().loadAll(User.class);
		if(logger.isTraceEnabled())
			logger.trace((ret == null)? "No Users Found." : ret.size() + " Users Found");
		return ret;
	}

}
