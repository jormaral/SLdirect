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
import java.util.HashMap;
import java.util.Map;

import net.neurowork.cenatic.centraldir.dao.DaoException;
import net.neurowork.cenatic.centraldir.dao.IndicadorDao;
import net.neurowork.cenatic.centraldir.model.graphs.datasets.GraphDatasetCreator;
import net.neurowork.cenatic.centraldir.model.graphs.datasets.filters.NullFilter;
import net.neurowork.cenatic.centraldir.model.graphs.datasets.filters.OrganizacionFilter;
import net.neurowork.cenatic.centraldir.model.indicators.AgruparPor;
import net.neurowork.cenatic.centraldir.model.indicators.DatasetGenerator;
import net.neurowork.cenatic.centraldir.model.indicators.Indicator;
import net.neurowork.cenatic.centraldir.model.indicators.TipoGrafico;
import net.neurowork.cenatic.centraldir.model.indicators.TipoIndicador;
import net.neurowork.cenatic.centraldir.service.CenaticService;
import net.neurowork.cenatic.centraldir.service.IndicadorService;
import net.neurowork.cenatic.centraldir.service.OrganizacionService;
import net.neurowork.cenatic.centraldir.service.ServiceException;

import org.jfree.data.general.Dataset;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

/**
 *
 * @author Alejandro Sanchez Acosta <asanchez@neurowork.net> http://www.neurowork.net
 * @since 13/11/2010
 */
public class IndicadorServiceImpl implements IndicadorService {
	private final static Logger logger = LoggerFactory.getLogger(IndicadorServiceImpl.class);
	
	Map<String, CenaticService> serviceMap;

	@Autowired
	private IndicadorDao indicadorDao;
	
	@Autowired
	private OrganizacionService organizacionService;
	

	
	@Override
	public Collection<Indicator> getIndicators() throws ServiceException {
		if(logger.isTraceEnabled())
			logger.trace("Retrieving all Indicator");
		Collection<Indicator> ret = null;
		try {
			ret = indicadorDao.getIndicadors();
			if(logger.isTraceEnabled())
				logger.trace((ret == null)? "No Indicator Found." : ret.size() + " Indicator Found");
		} catch (DaoException e) {
			logger.error(e.getMessage());
		}
		return ret;
	}

	@Override
	public void saveIndicator(Indicator indicator) throws ServiceException {
		if(logger.isTraceEnabled())
			logger.trace("Saving Indicator with : " + indicator);
		try {
			indicadorDao.saveIndicator(indicator);
		} catch (DaoException e) {
			logger.error(e.getMessage());
		}
	}

	@Override
	public Indicator loadIndicator(Integer id) throws ServiceException {
		if(logger.isTraceEnabled())
			logger.trace("Loading Indicator with id: " + id);
		try {
			return indicadorDao.loadIndicator(id);
		} catch (DaoException e) {
			logger.error(e.getMessage());
		}
		return null;
	}

	@Override
	public Collection<TipoIndicador> getTiposIndicador() throws ServiceException {
		try {
			return indicadorDao.getTiposIndicador();
		} catch (DaoException e) {
			logger.error(e.getMessage());
			throw new ServiceException(e.getMessage());
		}
	}

	@Override
	public Collection<TipoGrafico> getTiposGrafico() throws ServiceException {
		try {
			return indicadorDao.getTiposGrafico();
		} catch (DaoException e) {
			logger.error(e.getMessage());
			throw new ServiceException(e.getMessage());
		}
	}

	@Override
	public Collection<AgruparPor> getAgruparPors() throws ServiceException {
		try {
			return indicadorDao.getAgruparPors();
		} catch (DaoException e) {
			logger.error(e.getMessage());
			throw new ServiceException(e.getMessage());
		}
	}


	private Map<String, ? extends Number> generateIndicatorData(Indicator indicador) {
		String clazz = indicador.getDatasetGenerator();
		if(!StringUtils.hasLength(clazz)){
			logger.info("Invalid Dataset Generator for: " + indicador);
			return null;
		}
		try {
			if(logger.isTraceEnabled())
				logger.trace("Dataset Generator for: " + indicador + " = " + clazz);
			DatasetGenerator generator = (DatasetGenerator)Class.forName(clazz).newInstance();
			
			if(generator != null){
				generator.setServiceMap(getServiceMap());
				OrganizacionFilter filter = indicador.getFilter();
				if(filter == null){
					filter = new NullFilter();
					if(logger.isTraceEnabled())
						logger.trace("Setting Null Fillter for: " + indicador);
				}
				return generator.generateDataset(filter);			
			}
		} catch (InstantiationException e) {
			logger.error(e.getMessage());
		} catch (IllegalAccessException e) {
			logger.error(e.getMessage());
		} catch (ClassNotFoundException e) {
			logger.error(e.getMessage());
		} catch (ServiceException e) {
			logger.error(e.getMessage());
		}		
		return null;
	}

	private Map<String, CenaticService> getServiceMap() {
		if(serviceMap == null){
			serviceMap = new HashMap<String, CenaticService>();
			serviceMap.put(organizacionService.getServiceName(), organizacionService);
		}			
		return serviceMap;
	}

	@Override
	public String getServiceName() {
		return SERVICE_NAME;
	}

	@Override
	public Dataset buildGraphDataset(Indicator indicador) throws ServiceException {
		Map<String, ? extends Number> data = generateIndicatorData(indicador);
		if(data == null)
			throw new ServiceException("Invalid Data: null");
		
		if(logger.isTraceEnabled())
			logger.trace("Data: " + data.toString());
		
		String clazz = indicador.getTipoGrafico().getDatasetCreator();
		if(clazz == null)
			throw new ServiceException("Invalid Grupping class: null");
		try {
			GraphDatasetCreator creator = (GraphDatasetCreator)Class.forName(clazz).newInstance();

			if(creator == null){
				if(logger.isTraceEnabled())
					logger.warn("Can't create DataseCreator: " + clazz);
				throw new ServiceException("Can't create DataseCreator: " + clazz);
			}
			
			return creator.createDataset(data);
		} catch (InstantiationException e) {
			logger.error(e.getMessage());
		} catch (IllegalAccessException e) {
			logger.error(e.getMessage());
		} catch (ClassNotFoundException e) {
			logger.error(e.getMessage());
		}		
		return null;
	}

	@Override
	public TipoGrafico findTipoGraficoById(Integer id) throws ServiceException {
		try {
			return indicadorDao.findTipoGraficoById(id);
		} catch (DaoException e) {
			logger.error(e.getMessage());
			throw new ServiceException(e.getMessage());
		}
	}

}
