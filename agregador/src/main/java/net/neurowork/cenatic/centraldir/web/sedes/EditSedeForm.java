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

import java.util.Collection;

import net.neurowork.cenatic.centraldir.model.satelite.OrganizacionSede;
import net.neurowork.cenatic.centraldir.model.satelite.Provincia;
import net.neurowork.cenatic.centraldir.service.OrganizacionSedeService;
import net.neurowork.cenatic.centraldir.service.ProvinciaService;
import net.neurowork.cenatic.centraldir.service.ServiceException;
import net.neurowork.cenatic.centraldir.validators.OrganizacionSedeValidator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

/**
 *
 * @author Alejandro Sanchez Acosta <asanchez@neurowork.net> http://www.neurowork.net
 * @since 24/12/2010
 */
@Controller
@RequestMapping("/sedes/{sedeId}/edit")
@SessionAttributes(types = OrganizacionSede.class)
public class EditSedeForm {
	private final static Logger logger = LoggerFactory.getLogger(EditSedeForm.class);
	
	@Autowired
	private OrganizacionSedeService service;
	@Autowired
	private ProvinciaService provinciaService;
	
	@InitBinder
	public void setAllowedFields(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
		dataBinder.setDisallowedFields("organizacion");
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public String setupForm(@PathVariable("sedeId") int sedeId, Model model) {
		OrganizacionSede organizacionSede;
		try {
			organizacionSede = service.findById(sedeId);
			model.addAttribute(organizacionSede);
		} catch (ServiceException e) {
			logger.error(e.getMessage());
		}
		return "orgs/sede";
	}
	
	@ModelAttribute("provincias")
	public Collection<Provincia> populateProvincias(){
		try {
			return provinciaService.getProvincias();
		} catch (ServiceException e) {
		}
		return null;
	}
	
	@RequestMapping(method = RequestMethod.PUT)
	public String processSubmit(@ModelAttribute OrganizacionSede sede, BindingResult result, SessionStatus status) {
		if (logger.isTraceEnabled())
			logger.trace("Validating OrganizacionSede: " + sede);

		new OrganizacionSedeValidator().validate(sede, result);
		
		if (result.hasErrors()) {
			logger.trace("Validating OrganizacionSede: KO. Errors: " + result.getErrorCount());
			for(ObjectError err :  result.getAllErrors())
				logger.trace("Error:" + err.toString());
			return "orgs/sede";
		}else{
			logger.trace("Validating OrganizacionSede: OK.");
			try {
				if(sede.isNew()){
					logger.trace("New Sede, Inserting");
				}
				service.save(sede);
				status.setComplete();
				return "redirect:/orgs/" +sede.getOrganizacion().getId() + "/edit#sedes";
			} catch (ServiceException e) {
				logger.error(e.getMessage());
			}
			return "orgs/sede";
		}
	}
}