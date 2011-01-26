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

import net.neurowork.cenatic.centraldir.model.Satelite;
import net.neurowork.cenatic.centraldir.model.dto.SateliteDTO;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Alejandro Sanchez Acosta <asanchez@neurowork.net> http://www.neurowork.net
 * @since 10/11/2010
 */
public interface SateliteService extends CenaticService{
	String SERVICE_NAME = "SateliteService";
	/**
	 * Loads a Satelite by id
	 * @param username
	 * @return
	 * @throws ServiceException
	 */
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	Satelite findById(Integer id) throws ServiceException;
	
	/**
	 * Saves a Satelite
	 * @param user
	 * @throws ServiceException
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	void saveSatelite(Satelite satelite) throws ServiceException;

	/**
	 * @param name
	 * @return
	 * @throws ServiceException
	 */
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	Collection<Satelite> findByName(String name) throws ServiceException;

	/**
	 * @param name
	 * @return
	 * @throws ServiceException
	 */
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	Collection<Satelite> getSatelites() throws ServiceException;
	
	/**
	 * Return all activated satelites
	 * @return
	 * @throws ServiceException
	 */
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	Collection<Satelite> findActivado() throws ServiceException;

	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	Collection<SateliteDTO> getSatelitesDto() throws ServiceException;

	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	Integer getOrganizacionesSatelite(Satelite satelite) throws ServiceException;
}
