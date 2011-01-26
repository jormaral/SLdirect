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
import net.neurowork.cenatic.centraldir.dao.OrganizacionSedeDao;
import net.neurowork.cenatic.centraldir.model.Satelite;
import net.neurowork.cenatic.centraldir.model.satelite.Organizacion;
import net.neurowork.cenatic.centraldir.model.satelite.OrganizacionSede;
import net.neurowork.cenatic.centraldir.model.satelite.Provincia;
import net.neurowork.cenatic.centraldir.service.OrganizacionSedeService;
import net.neurowork.cenatic.centraldir.service.OrganizacionService;
import net.neurowork.cenatic.centraldir.service.ServiceException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Alejandro Sanchez Acosta <asanchez@neurowork.net> http://www.neurowork.net
 * @since 14/12/2010
 */
public class OrganizacionSedeServiceImpl implements OrganizacionSedeService {
	private final static Logger logger = LoggerFactory.getLogger(OrganizacionSedeServiceImpl.class);
	
	@Autowired
	private OrganizacionSedeDao organizacionSedeDao;
	@Autowired
	private OrganizacionService organizacionService;

	@Override
	public List<OrganizacionSede> getAll() throws ServiceException {
		if(logger.isTraceEnabled())
			logger.trace("Retrieving all OrganizacioSede");
		try {
			return organizacionSedeDao.getAll();
		} catch (DaoException e) {
			logger.error(e.getMessage());
			throw new ServiceException(e.getMessage());
		}
	}

	@Override
	public List<OrganizacionSede> findByOrganizacion(Organizacion organizacion) throws ServiceException {
		if(logger.isTraceEnabled())
			logger.trace("Retrieving all OrganizacioSede");
		try {
			return organizacionSedeDao.findByOrganizacion(organizacion);
		} catch (DaoException e) {
			logger.error(e.getMessage());
			throw new ServiceException(e.getMessage());
		}
	}
	
	@Override
	public OrganizacionSede findSedeByAddressLocalityProvince(String address, String locality, Provincia provincia) throws ServiceException {
		if(logger.isTraceEnabled())
			logger.trace("Finding Sede by Address, Locality and Province");
		try {
			Collection<OrganizacionSede> sedes = organizacionSedeDao.findSedeByAddressLocalityProvince(address, locality, provincia);
			if(sedes != null && sedes.size() > 0){
				return sedes.iterator().next();
			}
		} catch (DaoException e) {
			logger.error(e.getMessage());
			throw new ServiceException(e.getMessage());
		}
		return null;
	}

	@Override
	public OrganizacionSede findById(int sedeId) throws ServiceException {
		if(logger.isTraceEnabled())
			logger.trace("Finding OrganizacioSede by Id:" + sedeId);
		try {
			return organizacionSedeDao.findById(sedeId);
		} catch (DaoException e) {
			logger.error(e.getMessage());
			throw new ServiceException(e.getMessage());
		}
	}

	@Override
	public void delete(OrganizacionSede sede) throws ServiceException {
		if(logger.isTraceEnabled())
			logger.trace("Deleting OrganizacioSede:" + sede);
		try {
			organizacionSedeDao.delete(sede);
		} catch (DaoException e) {
			logger.error(e.getMessage());
			throw new ServiceException(e.getMessage());
		}
	}

	@Override
	public void save(OrganizacionSede sede) throws ServiceException {
		if(logger.isTraceEnabled())
			logger.trace("Saving OrganizacioSede:" + sede);
		try {
			organizacionSedeDao.save(sede);
		} catch (DaoException e) {
			logger.error(e.getMessage());
			throw new ServiceException(e.getMessage());
		}
	}

	@Override
	public List<OrganizacionSede> findBySatelite(Satelite satelite) throws ServiceException {
		List<OrganizacionSede> ret = new ArrayList<OrganizacionSede>();

		List<Organizacion> orgs = organizacionService.findBySatelite(satelite);
		if(orgs != null){
			for(Organizacion organizacion : orgs){
				ret.addAll(findByOrganizacion(organizacion));
			}
		}		
		return ret;
	}
	
	@Override
	public String getServiceName() {
		return SERVICE_NAME;
	}
}
