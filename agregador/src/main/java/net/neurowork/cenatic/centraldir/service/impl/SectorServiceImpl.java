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
import net.neurowork.cenatic.centraldir.dao.SectorDao;
import net.neurowork.cenatic.centraldir.model.satelite.Organizacion;
import net.neurowork.cenatic.centraldir.model.satelite.Sector;
import net.neurowork.cenatic.centraldir.service.InvalidCallException;
import net.neurowork.cenatic.centraldir.service.OrganizacionService;
import net.neurowork.cenatic.centraldir.service.SectorService;
import net.neurowork.cenatic.centraldir.service.ServiceException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Alejandro Sanchez Acosta <asanchez@neurowork.net> http://www.neurowork.net
 * @since 14/11/2010
 */
public class SectorServiceImpl implements SectorService {
	private final static Logger logger = LoggerFactory.getLogger(SectorServiceImpl.class);
	
	@Autowired
	private SectorDao sectorDao;
	@Autowired
	private OrganizacionService organizacionService;
	@Override
	public void save(Sector sector) throws ServiceException {
		if(logger.isTraceEnabled())
			logger.trace("Saving Sector: " + sector);
		try {
			sectorDao.save(sector);
		} catch (DaoException e) {
			logger.error(e.getMessage());
		}
	}

	@Override
	public Sector findById(Integer id) throws ServiceException {
		if(logger.isTraceEnabled())
			logger.trace("Finding Sector by Id: " + id);
		try {
			return sectorDao.findById(id);
		} catch (DaoException e) {
			logger.error(e.getMessage());
			throw new ServiceException(e.getMessage());
		}
	}

	@Override
	public Collection<Sector> findByName(String name) throws ServiceException {
		if(logger.isTraceEnabled())
			logger.trace("Finding Sector by Name: " + name);
		try {
			return sectorDao.findByName(name);
		} catch (DaoException e) {
			logger.error(e.getMessage());
			throw new ServiceException(e.getMessage());
		}
	}

	@Override
	public Collection<Sector> getAll() throws ServiceException {
		if(logger.isTraceEnabled())
			logger.trace("Finding All Sectores");
		try {
			return sectorDao.getAll();
		} catch (DaoException e) {
			logger.error(e.getMessage());
			throw new ServiceException(e.getMessage());
		}
	}

	@Override
	public void delete(Sector sector) throws ServiceException {
		if(logger.isTraceEnabled())
			logger.trace("Deleting Sector: " + sector);
		try {
			Collection<Organizacion> orgs = organizacionService.findBySector(sector);
			if(orgs != null && orgs.size() > 0){
				logger.trace("Can't delete Sector: " + sector);
				throw new InvalidCallException("No se puede eliminar el Sector: " + sector + " por estar asociada a otras entidades.");
			}
			sectorDao.delete(sector);		
		} catch (DaoException e) {
			logger.error(e.getMessage());
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