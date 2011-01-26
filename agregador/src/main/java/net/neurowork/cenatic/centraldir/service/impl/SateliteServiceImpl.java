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
import java.util.List;

import net.neurowork.cenatic.centraldir.dao.DaoException;
import net.neurowork.cenatic.centraldir.dao.SateliteDao;
import net.neurowork.cenatic.centraldir.model.Satelite;
import net.neurowork.cenatic.centraldir.model.dto.SateliteDTO;
import net.neurowork.cenatic.centraldir.service.SateliteService;
import net.neurowork.cenatic.centraldir.service.ServiceException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Alejandro Sanchez Acosta <asanchez@neurowork.net> http://www.neurowork.net
 * @since 12/11/2010
 */
public class SateliteServiceImpl implements SateliteService {
	private final static Logger logger = LoggerFactory.getLogger(SateliteServiceImpl.class);
	
	@Autowired
	private SateliteDao sateliteDao;

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public Satelite findById(Integer id) throws ServiceException {
		if(logger.isTraceEnabled())
			logger.trace("Loading Satelite with id: " + id);
		try {
			return sateliteDao.loadSatelite(id);
		} catch (DaoException e) {
			logger.error(e.getMessage());
		}
		return null;
	}

	@Override
	public void saveSatelite(Satelite satelite) throws ServiceException {
		if(logger.isTraceEnabled())
			logger.trace("Saving Satelite with : " + satelite);
		try {
			sateliteDao.saveSatelite(satelite);
		} catch (DaoException e) {
			logger.error(e.getMessage());
		}
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public Collection<Satelite> findByName(String name) throws ServiceException {
		if(logger.isTraceEnabled())
			logger.trace("Finding Satelite: " + name);
		try {
			return sateliteDao.findSatelite(name);
		} catch (DaoException e) {
			logger.error(e.getMessage());
		}
		return null;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public Collection<Satelite> getSatelites() throws ServiceException {
		if(logger.isTraceEnabled())
			logger.trace("Retrieving all Satelites");
		try {
			return sateliteDao.getSatelites();
		} catch (DaoException e) {
			logger.error(e.getMessage());
		}
		return null;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public Collection<Satelite> findActivado() throws ServiceException {
		if(logger.isTraceEnabled())
			logger.trace("Retrieving all Satelites");
		try {
			return sateliteDao.findActivado();
		} catch (DaoException e) {
			throw new ServiceException(e.getMessage());
		}
	}

	@Override
	public Collection<SateliteDTO> getSatelitesDto() throws ServiceException {
		List<SateliteDTO> ret = new ArrayList<SateliteDTO>();
		Collection<Satelite> satelites = getSatelites();
		for(Satelite satelite : satelites){
			try {
				Integer empresas = sateliteDao.getOrganizacionesCount(satelite);
				SateliteDTO dto = new SateliteDTO(satelite, empresas);
				ret.add(dto);
			} catch (DaoException e) {
				logger.error(e.getMessage());
			}
		}
		return ret;
	}

	@Override
	public Integer getOrganizacionesSatelite(Satelite satelite)	throws ServiceException {
		if(logger.isTraceEnabled())
			logger.trace("Retrieving Organization count for Satelite: " + satelite);
		try {
			return sateliteDao.getOrganizacionesCount(satelite);
		} catch (DaoException e) {
			throw new ServiceException(e.getMessage());
		}
	}
	
	@Override
	public String getServiceName() {
		return SERVICE_NAME;
	}
}
