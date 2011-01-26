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
package net.neurowork.cenatic.centraldir.web.sectores;

import net.neurowork.cenatic.centraldir.model.satelite.Sector;
import net.neurowork.cenatic.centraldir.service.SectorService;
import net.neurowork.cenatic.centraldir.service.ServiceException;
import net.neurowork.cenatic.centraldir.validators.SectorValidator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

/**
 *
 * @author Alejandro Sanchez Acosta <asanchez@neurowork.net> http://www.neurowork.net
 * @since 25/12/2010
 */
@Controller
@RequestMapping("/sectores/new")
@SessionAttributes(types = Sector.class)
public class AddSectorForm {
	private final static Logger logger = LoggerFactory.getLogger(AddSectorForm.class);
	
	@Autowired
	private SectorService service;
	
	@InitBinder
	public void setAllowedFields(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public String setupForm(Model model) {
		if(logger.isTraceEnabled())
			logger.trace("New Sector Form");
		model.addAttribute(new Sector());
		return "sectores/form";
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String processSubmit(@ModelAttribute Sector sector, BindingResult result, SessionStatus status) {
		if(logger.isTraceEnabled())
			logger.trace("Validating Sector: " + sector);
		
		new SectorValidator().validate(sector, result);
		
		if (!result.hasErrors()) {
			try {
				if(logger.isTraceEnabled())
					logger.trace("Sector: " + sector + " OK. Saving");
				
				service.save(sector);
				status.setComplete();
				
				return "redirect:/admin#sectores";
			} catch (ServiceException e) {
				logger.error(e.getMessage());
			}
		}
		return "sectores/form";
	}
}
