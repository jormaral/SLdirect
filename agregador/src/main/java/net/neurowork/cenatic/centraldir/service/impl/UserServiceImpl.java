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
package net.neurowork.cenatic.centraldir.service.impl;

import java.util.Collection;

import net.neurowork.cenatic.centraldir.dao.DaoException;
import net.neurowork.cenatic.centraldir.dao.UserDao;
import net.neurowork.cenatic.centraldir.model.User;
import net.neurowork.cenatic.centraldir.service.ServiceException;
import net.neurowork.cenatic.centraldir.service.UserService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Alejandro Sanchez Acosta <asanchez@neurowork.net> http://www.neurowork.net
 * @since 10/11/2010
 */
@Service("userService")
public class UserServiceImpl implements UserService {
	private final static Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	
	@Autowired
	private UserDao userDao;

	public User loadUser(String username) throws ServiceException {
		if(logger.isTraceEnabled())
			logger.trace("Loading User:" + username);
		try {
			return userDao.loadUser(username);
		} catch (DaoException e) {
			logger.error(e.getMessage());
		}
		return null;
	}

	public void saveUser(User user) throws ServiceException {
		if(logger.isTraceEnabled())
			logger.trace("Saving User:" + user);
		try {
			userDao.saveUser(user);
		} catch (DaoException e) {
			logger.error(e.getMessage());
		}
	}

	@Override
	public Collection<User> findUsers(String username) throws ServiceException  {
		if(logger.isTraceEnabled())
			logger.trace("Searching Users with username:" + username);
		try {
			return userDao.findUsers(username);
		} catch (DaoException e) {
			logger.error(e.getMessage());
		}
		return null;
	}

	@Override
	public Collection<User> getUsers() throws ServiceException {
		if(logger.isTraceEnabled())
			logger.trace("Retrieving all Users");
		Collection<User> ret = null;
		try {
			ret = userDao.getUsers();
			if(logger.isTraceEnabled())
				logger.trace((ret == null)? "No Users Found." : ret.size() + " Users Found");
		} catch (DaoException e) {
			logger.error(e.getMessage());
		}
		return ret;
	}

	@Override
	public String getServiceName() {
		return SERVICE_NAME;
	}
}
