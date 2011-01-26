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
package net.neurowork.cenatic.centraldir.workers.xml;

import net.neurowork.cenatic.centraldir.model.Satelite;
import net.neurowork.cenatic.centraldir.model.satelite.Provincia;
import net.neurowork.cenatic.centraldir.service.ProvinciaService;
import net.neurowork.cenatic.centraldir.service.ServiceException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

/**
 *
 * @author Alejandro Sanchez Acosta <asanchez@neurowork.net> http://www.neurowork.net
 * @since 07/01/2011
 */
public class ProvinciaXmlImporter {
	private final static Logger logger = LoggerFactory.getLogger(ProvinciaXmlImporter.class);

	private Satelite satelite;
	private ProvinciaService provinciaService;
	
	public ProvinciaXmlImporter(Satelite satelite, ProvinciaService provinciaService){
		super();
		this.satelite = satelite;
		this.provinciaService = provinciaService;
	}	
	
	public Provincia findProvincia(String provId, String provincia) {
		if(!StringUtils.hasLength(provId))
			return null;
		
		try {
			return provinciaService.findProvincia(satelite, provincia, Integer.parseInt(provId));
		} catch (NumberFormatException e) {
			logger.error(e.getMessage());
		} catch (ServiceException e) {
			logger.error(e.getMessage());
		}
		return null;
	}

}
