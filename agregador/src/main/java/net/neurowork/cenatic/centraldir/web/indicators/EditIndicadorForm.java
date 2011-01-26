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
package net.neurowork.cenatic.centraldir.web.indicators;

import net.neurowork.cenatic.centraldir.model.indicators.Indicator;
import net.neurowork.cenatic.centraldir.service.IndicadorService;
import net.neurowork.cenatic.centraldir.service.ServiceException;
import net.neurowork.cenatic.centraldir.validators.IndicadorValidator;

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
@RequestMapping("/indicators/{indicatorId}/edit")
@SessionAttributes(types = Indicator.class)
public class EditIndicadorForm {
	private final static Logger logger = LoggerFactory.getLogger(EditIndicadorForm.class);
	
	@Autowired
	private IndicadorService indicadorService;

	
	@InitBinder
	public void setAllowedFields(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public String setupForm(@PathVariable("indicatorId") int indicatorId, Model model) {
		Indicator indicator;
		try {
			indicator = this.indicadorService.loadIndicator(indicatorId);
			model.addAttribute(indicator);
			model.addAttribute("agruparPors", indicadorService.getAgruparPors());
			model.addAttribute("tiposIndicador", indicadorService.getTiposIndicador());
			model.addAttribute("tiposGrafico", indicadorService.getTiposGrafico());
		} catch (ServiceException e) {
			logger.error(e.getMessage());
		}
		return "indicators/form";
	}
	
	
	@RequestMapping(method = RequestMethod.PUT)
	public String processSubmit(@ModelAttribute Indicator indicator, BindingResult result, SessionStatus status) {
		if (logger.isTraceEnabled())
			logger.trace("Validating Indicator: " + indicator);

		new IndicadorValidator().validate(indicator, result);
		
		if (result.hasErrors()) {
			logger.trace("Validating Indicator has Errors.");
			return "indicators/form";
		}else{
			logger.trace("Validating Indicator OK.");
			try {
				this.indicadorService.saveIndicator(indicator);
				status.setComplete();
				return "redirect:/indicators/" + indicator.getId();
			} catch (ServiceException e) {
				logger.error(e.getMessage());
			}
			return "indicators/form";
		}
	}
	

}
