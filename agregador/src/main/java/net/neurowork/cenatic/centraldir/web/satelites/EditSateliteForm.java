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
package net.neurowork.cenatic.centraldir.web.satelites;

import net.neurowork.cenatic.centraldir.model.Satelite;
import net.neurowork.cenatic.centraldir.service.SateliteService;
import net.neurowork.cenatic.centraldir.service.ServiceException;
import net.neurowork.cenatic.centraldir.validators.SateliteValidator;

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
 * @since 12/11/2010
 */
@Controller
@RequestMapping("/satelites/{sateliteId}/edit")
@SessionAttributes(types = Satelite.class)
public class EditSateliteForm {
	private final static Logger logger = LoggerFactory.getLogger(EditSateliteForm.class);
	
	@Autowired
	private SateliteService sateliteService;

	
	@InitBinder
	public void setAllowedFields(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public String setupForm(@PathVariable("sateliteId") int sateliteId, Model model) {
		Satelite satelite;
		try {
			satelite = this.sateliteService.findById(sateliteId);
			model.addAttribute(satelite);
		} catch (ServiceException e) {
			logger.error(e.getMessage());
		}
		return "satelites/form";
	}
	
	
	@RequestMapping(method = RequestMethod.PUT)
	public String processSubmit(@ModelAttribute Satelite satelite, BindingResult result, SessionStatus status) {
		if (logger.isTraceEnabled()) {
			logger.debug("Validating Satelite: " + satelite);
		}		
		new SateliteValidator().validate(satelite, result);
		
		if (!result.hasErrors()) {
			try {
				this.sateliteService.saveSatelite(satelite);
				status.setComplete();
				return "redirect:/satelites/" + satelite.getId();
			} catch (ServiceException e) {
				logger.error(e.getMessage());
			}
		}
		return "satelites/form";
	}
	

}
