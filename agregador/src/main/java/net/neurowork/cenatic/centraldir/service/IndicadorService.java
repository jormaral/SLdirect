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

import java.util.Collection;

import net.neurowork.cenatic.centraldir.model.indicators.AgruparPor;
import net.neurowork.cenatic.centraldir.model.indicators.Indicator;
import net.neurowork.cenatic.centraldir.model.indicators.TipoGrafico;
import net.neurowork.cenatic.centraldir.model.indicators.TipoIndicador;

import org.jfree.data.general.Dataset;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Alejandro Sanchez Acosta <asanchez@neurowork.net> http://www.neurowork.net
 * @since 13/11/2010
 */
public interface IndicadorService extends CenaticService{
	String SERVICE_NAME = "IndicadorService";

	/**
	 * @param id
	 * @return
	 * @throws ServiceException
	 */
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	Indicator loadIndicator(Integer id) throws ServiceException;
	

	/**
	 * @return
	 * @throws ServiceException
	 */
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	Collection<Indicator> getIndicators() throws ServiceException;

	/**
	 * @param indicator
	 * @throws ServiceException
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	void saveIndicator(Indicator indicator) throws ServiceException;


	/**
	 * @return
	 * @throws ServiceException
	 */
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	Collection<TipoIndicador> getTiposIndicador() throws ServiceException;


	/**
	 * @return
	 * @throws ServiceException
	 */
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	Collection<TipoGrafico> getTiposGrafico() throws ServiceException;


	/**
	 * @return
	 * @throws ServiceException
	 */
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	Collection<AgruparPor> getAgruparPors() throws ServiceException;


	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	Dataset buildGraphDataset(Indicator indicador) throws ServiceException ;


	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	TipoGrafico findTipoGraficoById(Integer id) throws ServiceException;
}
