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
import net.neurowork.cenatic.centraldir.dao.DemandaDao;
import net.neurowork.cenatic.centraldir.model.satelite.Organizacion;
import net.neurowork.cenatic.centraldir.model.satelite.OrganizacionCapacidadDemanda;
import net.neurowork.cenatic.centraldir.service.DemandaService;
import net.neurowork.cenatic.centraldir.service.ServiceException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Alejandro Sanchez Acosta <asanchez@neurowork.net> http://www.neurowork.net
 * @since 14/12/2010
 */
public class DemandaServiceImpl implements DemandaService {
	private final static Logger logger = LoggerFactory.getLogger(DemandaServiceImpl.class);
	
	@Autowired
	private DemandaDao dao;

	@Override
	public Collection<OrganizacionCapacidadDemanda> getAll() throws ServiceException {
		if(logger.isTraceEnabled())
			logger.trace("Retrieving all Demandas");
		try {
			return dao.getAll();
		} catch (DaoException e) {
			logger.error(e.getMessage());
			throw new ServiceException(e.getMessage());
		}
	}

	@Override
	public Collection<OrganizacionCapacidadDemanda> findByOrganizacion(Organizacion organizacion) throws ServiceException {
		if(logger.isTraceEnabled())
			logger.trace("Retrieving all Demandas of Organizacion: " + organizacion);
		try {
			return dao.findByOrganizacion(organizacion);
		} catch (DaoException e) {
			logger.error(e.getMessage());
			throw new ServiceException(e.getMessage());
		}
	}
	
	@Override
	public OrganizacionCapacidadDemanda findById(int demandaId) throws ServiceException {
		if(logger.isTraceEnabled())
			logger.trace("Finding Demanda by Id:" + demandaId);
		try {
			return dao.findById(demandaId);
		} catch (DaoException e) {
			logger.error(e.getMessage());
			throw new ServiceException(e.getMessage());
		}
	}

	@Override
	public void delete(OrganizacionCapacidadDemanda demanda) throws ServiceException {
		if(logger.isTraceEnabled())
			logger.trace("Deleting Demanda:" + demanda);
		try {
			dao.delete(demanda);
		} catch (DaoException e) {
			logger.error(e.getMessage());
			throw new ServiceException(e.getMessage());
		}
	}

	@Override
	public void delete(int id) throws ServiceException {
		delete(findById(id));		
	}

	@Override
	public String getServiceName() {
		return SERVICE_NAME;
	}
}