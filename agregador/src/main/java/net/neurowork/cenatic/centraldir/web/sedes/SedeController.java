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
package net.neurowork.cenatic.centraldir.web.sedes;


import net.neurowork.cenatic.centraldir.model.satelite.OrganizacionSede;
import net.neurowork.cenatic.centraldir.service.OrganizacionSedeService;
import net.neurowork.cenatic.centraldir.service.ServiceException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Alejandro Sanchez Acosta <asanchez@neurowork.net> http://www.neurowork.net
 * @since 19/12/2010
 */
@Controller
public class SedeController {
	private final static Logger logger = LoggerFactory.getLogger(SedeController.class);

	@Autowired
	private OrganizacionSedeService organizacionSedeService;
	
	@RequestMapping("/sedes/{sedeId}/del")
	public String delSedeHandler(@PathVariable("sedeId") int sedeId, Model model) {
		if(logger.isTraceEnabled())
			logger.trace("Deleting OrganizacionSede with Id: " + sedeId);
		
		try {
			OrganizacionSede sede = organizacionSedeService.findById(sedeId);
			if(sede == null)
				return "404";
			
			int orgId = sede.getOrganizacion().getId();			
			organizacionSedeService.delete(sede);
			String retirect = "redirect:/orgs/" + orgId + "/edit#sedes"; 
			if(logger.isTraceEnabled())
				logger.trace("Redirecting to: " + retirect);
			return retirect; 
		} catch (ServiceException e) {
			logger.error(e.getMessage());
		} 
		return "404";
	}

}
