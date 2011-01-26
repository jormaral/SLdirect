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

import java.util.ArrayList;
import java.util.Collection;

import net.neurowork.cenatic.centraldir.dao.AsociacionDao;
import net.neurowork.cenatic.centraldir.dao.DaoException;
import net.neurowork.cenatic.centraldir.model.satelite.Asociacion;
import net.neurowork.cenatic.centraldir.model.satelite.Organizacion;
import net.neurowork.cenatic.centraldir.service.AsociacionService;
import net.neurowork.cenatic.centraldir.service.InvalidCallException;
import net.neurowork.cenatic.centraldir.service.OrganizacionService;
import net.neurowork.cenatic.centraldir.service.ServiceException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Alejandro Sanchez Acosta <asanchez@neurowork.net> http://www.neurowork.net
 * @since 14/11/2010
 */
public class AsociacionServiceImpl implements AsociacionService {
	private final static Logger logger = LoggerFactory.getLogger(AsociacionServiceImpl.class);
	
	@Autowired
	private AsociacionDao dao;
	@Autowired
	private OrganizacionService organizacionService;
	
	@Override
	public void save(Asociacion asociacion) throws ServiceException {
		if(logger.isTraceEnabled())
			logger.trace("Saving Asociacion: " + asociacion);
		try {
			dao.save(asociacion);
		} catch (DaoException e) {
			logger.error(e.getMessage());
		}
	}

	@Override
	public Asociacion findById(Integer Id) throws ServiceException {
		if(logger.isTraceEnabled())
			logger.trace("Finding Asociacion by Id: " + Id);
		try {
			return dao.findById(Id);
		} catch (DaoException e) {
			logger.error(e.getMessage());
			throw new ServiceException(e.getMessage());
		}
	}

	@Override
	public Collection<Asociacion> findByName(String name) throws ServiceException {
		if(logger.isTraceEnabled())
			logger.trace("Finding Asociacion by Name: " + name);
		try {
			return dao.findByName(name);
		} catch (DaoException e) {
			logger.error(e.getMessage());
			throw new ServiceException(e.getMessage());
		}
	}

	@Override
	public Collection<Asociacion> getAll() throws ServiceException {
		if(logger.isTraceEnabled())
			logger.trace("Finding All Asociaciones");
		try {
			return dao.getAll();
		} catch (DaoException e) {
			logger.error(e.getMessage());
			throw new ServiceException(e.getMessage());
		}
	}

	@Override
	public void delete(Asociacion asociacion) throws ServiceException {
		if(logger.isTraceEnabled())
			logger.trace("Tryinh to delete Asociacion: " + asociacion);
		try {
			Collection<Organizacion> orgs = organizacionService.findByAsociacion(asociacion);
			if(orgs != null && orgs.size() > 0){
				logger.trace("Can't delete Provincia: " + asociacion);
				throw new InvalidCallException("No se puede eliminar la Provincia " + asociacion + " por estar asociada a otras entidades.");
			}
			if(logger.isTraceEnabled())
				logger.trace("Deleting Asociacion: " + asociacion);
			dao.delete(asociacion);
		} catch (DaoException e) {
			throw new ServiceException(e.getMessage());
		}
	}

	@Override
	public void delete(Integer Id) throws ServiceException {
		delete(findById(Id));		
	}

	@Override
	public Collection<Asociacion> getForOrganizacion(Organizacion organizacion) throws ServiceException {
		if(organizacion.getOrganizacionAsociacions() == null)
			return getAll();
		Collection<Asociacion> ret = new ArrayList<Asociacion>();
		Collection<Asociacion> asos = getAll();
		for(Asociacion a : asos){
			if(!organizacion.getOrganizacionAsociacions().contains(a))
				ret.add(a);
		}
		return ret;
	}

	@Override
	public String getServiceName() {
		return SERVICE_NAME;
	}
}