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
import net.neurowork.cenatic.centraldir.dao.ProvinciaDao;
import net.neurowork.cenatic.centraldir.model.Satelite;
import net.neurowork.cenatic.centraldir.model.satelite.Organizacion;
import net.neurowork.cenatic.centraldir.model.satelite.Provincia;
import net.neurowork.cenatic.centraldir.service.InvalidCallException;
import net.neurowork.cenatic.centraldir.service.OrganizacionService;
import net.neurowork.cenatic.centraldir.service.ProvinciaService;
import net.neurowork.cenatic.centraldir.service.ServiceException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Alejandro Sanchez Acosta <asanchez@neurowork.net> http://www.neurowork.net
 * @since 06/12/2010
 */
public class ProvinciaServiceImpl implements ProvinciaService {
	private final static Logger logger = LoggerFactory.getLogger(ProvinciaServiceImpl.class);
	
	@Autowired
	private ProvinciaDao dao;
	@Autowired
	private OrganizacionService organizacionService;
	

	@Override
	public Provincia findProvincia(Satelite satelite, String provincia, Integer id) throws ServiceException {
		if(logger.isTraceEnabled())
			logger.trace("Finding Provincia with name: " + provincia);
		Provincia ret = null;
		
		Collection<Provincia> provs = findByName(provincia);
		
		if(provs != null){
			if(provs.size() > 0){
				return provs.iterator().next(); 
			}
		}
		if(logger.isTraceEnabled())
			logger.trace("Provincia with name "+ provincia +" not found. Creating...");
		
		ret = new Provincia();
		ret.setName(provincia);
				
		try {
			dao.save(ret);
		} catch (DaoException e) {
			logger.error(e.getMessage());
			throw new ServiceException(e.getMessage());
		}		
		return ret;
	}

	@Override
	public Collection<Provincia> findByName(String name) throws ServiceException {
		if(logger.isTraceEnabled())
			logger.trace("Finding Provincia with name: " + name);
		try {
			return dao.findByName(name);
		} catch (DaoException e) {
			throw new ServiceException(e.getMessage());
		}
	}

	@Override
	public Collection<Provincia> getProvincias() throws ServiceException {
		if(logger.isTraceEnabled())
			logger.trace("Retrieving All Provincias");
		try {
			return dao.getProvincias();
		} catch (DaoException e) {
			throw new ServiceException(e.getMessage());
		}
	}

	@Override
	public void save(Provincia provincia) throws ServiceException {
		if(logger.isTraceEnabled())
			logger.trace("Saving Provincia: " + provincia);
		try {
			dao.save(provincia);
		} catch (DaoException e) {
			throw new ServiceException(e.getMessage());
		}
	}

	@Override
	public Provincia findById(Integer Id) throws ServiceException {
		if(logger.isTraceEnabled())
			logger.trace("Retrieving Provincia with Id: " + Id);
		try {
			return dao.findById(Id);
		} catch (DaoException e) {
			throw new ServiceException(e.getMessage());
		}
	}

	@Override
	public void delete(Provincia provincia) throws ServiceException {
		if(logger.isTraceEnabled())
			logger.trace("Deleting Provincia: " + provincia);
		try {
			Collection<Organizacion> orgs = organizacionService.findByProvincia(provincia);
			if(orgs != null && orgs.size() > 0){
				logger.trace("Can't delete Provincia: " + provincia);
				throw new InvalidCallException("No se puede eliminar la Provincia " + provincia + " por estar asociada a otras entidades.");
			}
			dao.delete(provincia);
		} catch (DaoException e) {
			throw new ServiceException(e.getMessage());
		}
	}

	@Override
	public void delete(Integer Id) throws ServiceException {
		delete(findById(Id));
	}
	
	@Override
	public String getServiceName() {
		return SERVICE_NAME;
	}
}