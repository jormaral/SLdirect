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

import net.neurowork.cenatic.centraldir.model.satelite.Sector;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Servicios Ofrecidos para los Sectores
 * @author Alejandro Sanchez Acosta <asanchez@neurowork.net> http://www.neurowork.net
 * @since 22/12/2010
 */
public interface SectorService extends CenaticService{
	String SERVICE_NAME = "SectorService";
	/**
	 * @param sector
	 * @throws ServiceException
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	void save(Sector sector) throws ServiceException;

	/**
	 * Borra un Sector
	 * @param sector
	 * @throws ServiceException
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	void delete(Sector sector) throws ServiceException;

	@Transactional(propagation = Propagation.REQUIRED)
	void delete(Integer Id) throws ServiceException;
	
	/**
	 * @param id
	 * @return
	 * @throws ServiceException
	 */
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	Sector findById(Integer id) throws ServiceException;
	/**
	 * @param name
	 * @return
	 * @throws ServiceException
	 */
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	Collection<Sector> findByName(String name) throws ServiceException;

	/**
	 * @return
	 * @throws ServiceException
	 */
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	Collection<Sector> getAll() throws ServiceException;

}
