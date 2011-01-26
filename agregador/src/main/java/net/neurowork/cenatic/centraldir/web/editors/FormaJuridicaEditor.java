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

import net.neurowork.cenatic.centraldir.model.satelite.FormaJuridica;
import net.neurowork.cenatic.centraldir.service.FormaJuridicaService;
import net.neurowork.cenatic.centraldir.service.ServiceException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Alejandro Sanchez Acosta <asanchez@neurowork.net> http://www.neurowork.net
 * @since 27/12/2010
 */
public class FormaJuridicaEditor extends PropertyEditorSupport {
	private final static Logger logger = LoggerFactory.getLogger(FormaJuridicaEditor.class);

	private FormaJuridicaService service;
	
	public FormaJuridicaEditor(FormaJuridicaService service) {
		super();
		this.service = service; 
	}

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		try {
			for(FormaJuridica fj : service.getAll()){
				if(fj.getName().equals(text)){
					if(logger.isTraceEnabled())
						logger.trace("Forma Jurídica: " + text + " setting value: " + fj);
					setValue(fj);
					return;
				}
			}
		} catch (ServiceException e) {
			logger.error(e.getMessage());
		}
	}
}