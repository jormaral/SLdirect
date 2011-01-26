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
package net.neurowork.cenatic.centraldir.web.editors;

import java.beans.PropertyEditorSupport;
import java.security.InvalidParameterException;

import net.neurowork.cenatic.centraldir.model.indicators.TipoIndicador;
import net.neurowork.cenatic.centraldir.service.IndicadorService;
import net.neurowork.cenatic.centraldir.service.ServiceException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Alejandro Sanchez Acosta <asanchez@neurowork.net> http://www.neurowork.net
 * @since 15/11/2010
 */
public class TipoIndicadorEditor extends PropertyEditorSupport {
	private final static Logger logger = LoggerFactory.getLogger(TipoIndicadorEditor.class);

	private IndicadorService indicadorService;
	
	public TipoIndicadorEditor(IndicadorService indicadorService){
		super();
		if(indicadorService == null)
			throw new InvalidParameterException("null Inidicador Service");
		this.indicadorService = indicadorService;
	}
	

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		logger.info("Setting Tipo Indicador for: " + text);
		try {
			for(TipoIndicador ti : indicadorService.getTiposIndicador()){
				logger.info("Evaluating: " + ti + " against: " + text);
				if(ti.getName().equals(text)){
					logger.info("Setting Tipo Indicador: " + ti);
					setValue(ti);
					return;
				}
			}
		} catch (ServiceException e) {
			logger.error(e.getMessage());
		}
		logger.info("Tipo Indicador no encontrado.");
	}

}
