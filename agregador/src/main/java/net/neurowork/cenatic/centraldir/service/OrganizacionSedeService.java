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

import java.util.List;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import net.neurowork.cenatic.centraldir.model.Satelite;
import net.neurowork.cenatic.centraldir.model.satelite.Organizacion;
import net.neurowork.cenatic.centraldir.model.satelite.OrganizacionSede;
import net.neurowork.cenatic.centraldir.model.satelite.Provincia;

/**
 *
 * @author Alejandro Sanchez Acosta <asanchez@neurowork.net> http://www.neurowork.net
 * @since 14/12/2010
 */
public interface OrganizacionSedeService extends CenaticService{
	String SERVICE_NAME = "OrganizacionSedeService";
	
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	List<OrganizacionSede> getAll() throws ServiceException;
	
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	List<OrganizacionSede> findByOrganizacion(Organizacion organizacion) throws ServiceException;
	
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	OrganizacionSede findSedeByAddressLocalityProvince(String address, String locality, Provincia provincia) throws ServiceException;

	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	List<OrganizacionSede> findBySatelite(Satelite satelite) throws ServiceException;
	
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	OrganizacionSede findById(int sedeId) throws ServiceException;

	@Transactional(propagation = Propagation.REQUIRED)
	void delete(OrganizacionSede sede) throws ServiceException;

	@Transactional(propagation = Propagation.REQUIRED)
	void save(OrganizacionSede sede) throws ServiceException;
}
