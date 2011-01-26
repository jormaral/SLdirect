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
import net.neurowork.cenatic.centraldir.model.satelite.Provincia;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Alejandro Sanchez Acosta <asanchez@neurowork.net> http://www.neurowork.net
 * @since 06/12/2010
 */
public interface ProvinciaService extends CenaticService{
	String SERVICE_NAME = "ProvinciaService";

	@Transactional(propagation = Propagation.REQUIRED)
	void save(Provincia provincia) throws ServiceException;
	
	@Transactional(propagation = Propagation.REQUIRED)
	void delete(Provincia provincia) throws ServiceException;

	@Transactional(propagation = Propagation.REQUIRED)
	void delete(Integer Id) throws ServiceException;
		
	@Transactional(propagation = Propagation.REQUIRED)
	Provincia findProvincia(Satelite satelite, String provincia, Integer id) throws ServiceException;
	
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	Provincia findById(Integer Id) throws ServiceException;

	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	Collection<Provincia> findByName(String name) throws ServiceException;
	
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	Collection<Provincia> getProvincias() throws ServiceException;
}
