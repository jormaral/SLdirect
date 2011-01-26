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
import net.neurowork.cenatic.centraldir.dao.OrganizacionAsociacionDao;
import net.neurowork.cenatic.centraldir.model.satelite.Organizacion;
import net.neurowork.cenatic.centraldir.model.satelite.OrganizacionAsociacion;
import net.neurowork.cenatic.centraldir.service.OrganizacionAsociacionService;
import net.neurowork.cenatic.centraldir.service.ServiceException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Alejandro Sanchez Acosta <asanchez@neurowork.net> http://www.neurowork.net
 * @since 14/12/2010
 */
public class OrganizacionAsociacionServiceImpl implements OrganizacionAsociacionService {
	private final static Logger logger = LoggerFactory.getLogger(OrganizacionAsociacionServiceImpl.class);
	
	@Autowired
	private OrganizacionAsociacionDao dao;

	@Override
	public Collection<OrganizacionAsociacion> getAll() throws ServiceException {
		if(logger.isTraceEnabled())
			logger.trace("Retrieving all OrganizacioSede");
		try {
			return dao.getAll();
		} catch (DaoException e) {
			logger.error(e.getMessage());
			throw new ServiceException(e.getMessage());
		}
	}

	@Override
	public Collection<OrganizacionAsociacion> findByOrganizacion(Organizacion organizacion) throws ServiceException {
		if(logger.isTraceEnabled())
			logger.trace("Retrieving all OrganizacioSede");
		try {
			return dao.findByOrganizacion(organizacion);
		} catch (DaoException e) {
			logger.error(e.getMessage());
			throw new ServiceException(e.getMessage());
		}
	}
	
	@Override
	public OrganizacionAsociacion findById(int sedeId) throws ServiceException {
		if(logger.isTraceEnabled())
			logger.trace("Finding OrganizacioSede by Id:" + sedeId);
		try {
			return dao.findById(sedeId);
		} catch (DaoException e) {
			logger.error(e.getMessage());
			throw new ServiceException(e.getMessage());
		}
	}

	@Override
	public void delete(OrganizacionAsociacion sede) throws ServiceException {
		if(logger.isTraceEnabled())
			logger.trace("Deleting OrganizacioSede:" + sede);
		try {
			dao.delete(sede);
		} catch (DaoException e) {
			logger.error(e.getMessage());
			throw new ServiceException(e.getMessage());
		}
	}
	
	@Override
	public String getServiceName() {
		return SERVICE_NAME;
	}
}