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
package net.neurowork.cenatic.centraldir.service;

import java.util.Collection;

import org.springframework.security.access.prepost.PreAuthorize;

import net.neurowork.cenatic.centraldir.model.User;

/**
 *
 * @author Alejandro Sanchez Acosta <asanchez@neurowork.net> http://www.neurowork.net
 * @since 10/11/2010
 */
public interface UserService extends CenaticService{
	String SERVICE_NAME = "UserService";
	
	/**
	 * Loads a User by username
	 * @param username
	 * @return
	 * @throws ServiceException
	 */
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	User loadUser(String username) throws ServiceException;
	
	/**
	 * Saves a User
	 * @param user
	 * @throws ServiceException
	 */
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	void saveUser(User user) throws ServiceException;

	/**
	 * @param username
	 * @return
	 */
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	Collection<User> findUsers(String username) throws ServiceException;

	/**
	 * @param username
	 * @return
	 */
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	Collection<User> getUsers() throws ServiceException;
}
