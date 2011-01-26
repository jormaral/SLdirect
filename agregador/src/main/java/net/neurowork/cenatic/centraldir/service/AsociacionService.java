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

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import net.neurowork.cenatic.centraldir.model.satelite.Asociacion;
import net.neurowork.cenatic.centraldir.model.satelite.Organizacion;

/**
 *
 * @author Alejandro Sanchez Acosta <asanchez@neurowork.net> http://www.neurowork.net
 * @since 24/12/2010
 */
public interface AsociacionService extends CenaticService{
	String SERVICE_NAME = "AsociacionService";
	
	/**
	 * @param asociacion
	 * @throws ServiceException
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	void save(Asociacion asociacion) throws ServiceException;

	/**
	 * @param asociacion
	 * @throws ServiceException
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	void delete(Asociacion asociacion) throws ServiceException;
	
	/**
	 * @param Id
	 * @throws ServiceException
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	void delete(Integer Id) throws ServiceException;
	
	/**
	 * @param id
	 * @return
	 * @throws ServiceException
	 */
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)	
	Asociacion findById(Integer id) throws ServiceException;
	/**
	 * @param name
	 * @return
	 * @throws ServiceException
	 */
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	Collection<Asociacion> findByName(String name) throws ServiceException;
	/**
	 * Devuelve todas las asociaciones
	 * @return
	 * @throws ServiceException
	 */
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	Collection<Asociacion> getAll() throws ServiceException;
	
	/**
	 * Devuelve todas las asociaciones
	 * @return
	 * @throws ServiceException
	 */
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	Collection<Asociacion> getForOrganizacion(Organizacion organizacion) throws ServiceException;
	
}
