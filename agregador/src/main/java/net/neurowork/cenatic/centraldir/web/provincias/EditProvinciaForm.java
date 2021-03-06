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
package net.neurowork.cenatic.centraldir.web.provincias;

import net.neurowork.cenatic.centraldir.model.satelite.Provincia;
import net.neurowork.cenatic.centraldir.service.ProvinciaService;
import net.neurowork.cenatic.centraldir.service.ServiceException;
import net.neurowork.cenatic.centraldir.validators.ProvinciaValidator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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
@RequestMapping("/provincias/{provinciaId}/edit")
@SessionAttributes(types = Provincia.class)
public class EditProvinciaForm {
	private final static Logger logger = LoggerFactory.getLogger(EditProvinciaForm.class);
	
	@Autowired
	private ProvinciaService service;
	
	@InitBinder
	public void setAllowedFields(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public String setupForm(@PathVariable("provinciaId") int provinciaId, Model model) {
		Provincia provincia;
		try {
			provincia = service.findById(provinciaId);
			model.addAttribute(provincia);
		} catch (ServiceException e) {
			logger.error(e.getMessage());
		}
		return "provincias/form";
	}
	
	
	@RequestMapping(method = RequestMethod.PUT)
	public String processSubmit(@ModelAttribute Provincia provincia, BindingResult result, SessionStatus status) {
		if (logger.isTraceEnabled())
			logger.trace("Validating Forma Juridica: " + provincia);

		new ProvinciaValidator().validate(provincia, result);
		
		if (result.hasErrors()) {
			logger.trace("Validating Indicator has Errors.");
			return "provincias/form";
		}else{
			logger.trace("Validating Indicator OK.");
			try {
				service.save(provincia);
				status.setComplete();
				return "redirect:/admin#provincias";
			} catch (ServiceException e) {
				logger.error(e.getMessage());
			}
			return "provincias/form";
		}
	}
}