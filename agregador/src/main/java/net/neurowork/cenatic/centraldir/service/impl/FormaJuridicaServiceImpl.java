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
import java.util.List;

import net.neurowork.cenatic.centraldir.dao.DaoException;
import net.neurowork.cenatic.centraldir.dao.FormaJuridicaDao;
import net.neurowork.cenatic.centraldir.model.satelite.FormaJuridica;
import net.neurowork.cenatic.centraldir.model.satelite.Organizacion;
import net.neurowork.cenatic.centraldir.service.FormaJuridicaService;
import net.neurowork.cenatic.centraldir.service.InvalidCallException;
import net.neurowork.cenatic.centraldir.service.OrganizacionService;
import net.neurowork.cenatic.centraldir.service.ServiceException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Alejandro Sanchez Acosta <asanchez@neurowork.net> http://www.neurowork.net
 * @since 17/12/2010
 */
public class FormaJuridicaServiceImpl implements FormaJuridicaService {
	private final static Logger logger = LoggerFactory.getLogger(FormaJuridicaServiceImpl.class);

	@Autowired
	private FormaJuridicaDao formaJuridicaDao;
	@Autowired
	private OrganizacionService organizacionService;
	
	@Override
	public List<FormaJuridica> findByName(String formaJuridica) throws ServiceException {
		if(logger.isTraceEnabled())
			logger.trace("Finding FormaJuridica by Name: " + formaJuridica);
		try {
			return formaJuridicaDao.findByName(formaJuridica);
		} catch (DaoException e) {
			logger.error(e.getMessage());
			throw new ServiceException(e.getMessage());
		}
	}

	@Override
	public FormaJuridica findById(Integer id) throws ServiceException {
		if(logger.isTraceEnabled())
			logger.trace("Finding FormaJuridica by Id: " + id);
		try {
			return formaJuridicaDao.findById(id);
		} catch (DaoException e) {
			logger.error(e.getMessage());
			throw new ServiceException(e.getMessage());
		}
	}

	@Override
	public void save(FormaJuridica formaJuridica) throws ServiceException {
		if(logger.isTraceEnabled())
			logger.trace("Saving FormaJuridica: " + formaJuridica);
		try {
			formaJuridicaDao.save(formaJuridica);
		} catch (DaoException e) {
			logger.error(e.getMessage());
		}
	}

	@Override
	public Collection<FormaJuridica> getAll() throws ServiceException {
		if(logger.isTraceEnabled())
			logger.trace("Finding All FormaJuridicas");
		try {
			return formaJuridicaDao.getAll();
		} catch (DaoException e) {
			logger.error(e.getMessage());
			throw new ServiceException(e.getMessage());
		}
	}

	@Override
	public void delete(int Id) throws ServiceException {
		delete(findById(Id));	
	}

	@Override
	public void delete(FormaJuridica formaJuridica) throws ServiceException {
		if(logger.isTraceEnabled())
			logger.trace("Deleting FormaJuridica: " + formaJuridica);
		try {
			Collection<Organizacion> orgs = organizacionService.findByFormaJuridica(formaJuridica);
			if(orgs != null && orgs.size() > 0){
				logger.trace("Can't delete FormaJuridica: " + formaJuridica);
				throw new InvalidCallException("No se puede eliminar la Forma Jurídica " + formaJuridica + " por estar asociada a otras entidades.");
			}
			formaJuridicaDao.delete(formaJuridica);		
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