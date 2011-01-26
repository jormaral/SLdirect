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

import net.neurowork.cenatic.centraldir.model.satelite.Organizacion;
import net.neurowork.cenatic.centraldir.model.satelite.OrganizacionCapacidadDemanda;

/**
 *
 * @author Alejandro Sanchez Acosta <asanchez@neurowork.net> http://www.neurowork.net
 * @since 14/12/2010
 */
public interface DemandaService extends CenaticService{
	String SERVICE_NAME = "DemandaService";
	
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	Collection<OrganizacionCapacidadDemanda> getAll() throws ServiceException;
	
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	Collection<OrganizacionCapacidadDemanda> findByOrganizacion(Organizacion organizacion) throws ServiceException;
	
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	OrganizacionCapacidadDemanda findById(int id) throws ServiceException;

	@Transactional(propagation = Propagation.REQUIRED)
	void delete(int id) throws ServiceException;
	
	@Transactional(propagation = Propagation.REQUIRED)
	void delete(OrganizacionCapacidadDemanda demanda) throws ServiceException;
}
