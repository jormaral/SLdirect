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
package net.neurowork.cenatic.centraldir.web.organizations;

import java.util.HashSet;

import net.neurowork.cenatic.centraldir.model.satelite.Asociacion;
import net.neurowork.cenatic.centraldir.model.satelite.Organizacion;
import net.neurowork.cenatic.centraldir.model.satelite.OrganizacionAsociacion;
import net.neurowork.cenatic.centraldir.service.AsociacionService;
import net.neurowork.cenatic.centraldir.service.OrganizacionAsociacionService;
import net.neurowork.cenatic.centraldir.service.OrganizacionService;
import net.neurowork.cenatic.centraldir.service.ServiceException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Alejandro Sanchez Acosta <asanchez@neurowork.net> http://www.neurowork.net
 * @since 27/12/2010
 */
@Controller
public class OrganizacionAsociacionController {
	private final static Logger logger = LoggerFactory.getLogger(OrganizacionAsociacionController.class);

	@Autowired
	private OrganizacionService organizacionService;
	@Autowired
	private AsociacionService asociacionService;
	@Autowired
	private OrganizacionAsociacionService oaService;
	
	
	@RequestMapping(value="/orgs/{organizacionId}/newAsociacion", method=RequestMethod.POST)
	public String addAsocicionOrganizacionHandler(
			@PathVariable("organizacionId") int organizacionId,
			@RequestParam("asociacionId") int asociacionId){
		if (logger.isTraceEnabled())
			logger.trace("Adding Asociacion with Id: " + asociacionId + " to Organizacion with Id: " + organizacionId);
		
		String ret = "redirect:/orgs/" + organizacionId + "/edit#asociaciones";
		
		try {
			Organizacion organizacion = organizacionService.findById(organizacionId);
			Asociacion asociacion = asociacionService.findById(asociacionId);
			
			if(organizacion != null && asociacion != null){
				OrganizacionAsociacion oa = new OrganizacionAsociacion();
				oa.setOrganizacion(organizacion);
				oa.setAsociacion(asociacion);
				if(organizacion.getOrganizacionAsociacions() == null){
					organizacion.setOrganizacionAsociacions(new HashSet<OrganizacionAsociacion>());
				}
				organizacion.getOrganizacionAsociacions().add(oa);
				organizacionService.saveOrganization(organizacion);
			}			
		} catch (ServiceException e) {
			logger.error(e.getMessage());
		}
		
		
		return ret;
	}
	
	@RequestMapping(value="/orgAsoc/{orgAsocId}/del", method=RequestMethod.GET)
	public String deleteAsocicionOrganizacionHandler(@PathVariable("orgAsocId") int orgAsocId){
		if (logger.isTraceEnabled())
			logger.trace("Deleting OrganizacionAsociacion with Id: " + orgAsocId);
		
		OrganizacionAsociacion oa = null;
		int organizacionId = 0;
		try {
			oa = oaService.findById(orgAsocId);
			if(oa == null){
				return "404";
			}
			organizacionId = oa.getOrganizacion().getId();
			oaService.delete(oa);
		} catch (ServiceException e) {
			logger.error(e.getMessage());
		}
		String ret = "redirect:/orgs/" + organizacionId + "/edit#asociaciones";
		return ret;
	}	
}