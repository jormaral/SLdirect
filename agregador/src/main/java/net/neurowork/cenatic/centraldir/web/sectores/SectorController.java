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
import net.neurowork.cenatic.centraldir.service.InvalidCallException;
import net.neurowork.cenatic.centraldir.service.SectorService;
import net.neurowork.cenatic.centraldir.service.ServiceException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Alejandro Sanchez Acosta <asanchez@neurowork.net> http://www.neurowork.net
 * @since 25/12/2010
 */
@Controller
@SessionAttributes(types = Sector.class)
public class SectorController {
	private final static Logger logger = LoggerFactory.getLogger(SectorController.class);
	
	@Autowired
	private SectorService service;
	
	@RequestMapping(method = RequestMethod.DELETE, value="/sectores/{sectorId}/del")
	public ModelAndView deleteFormaJuridicaHandler(@ModelAttribute Sector sector) {
		if (logger.isTraceEnabled())
			logger.trace("Deleting Sector: " + sector);
		
		try {
			service.delete(sector);
			ModelAndView mv = new ModelAndView("redirect:/admin#sectores");;	
			mv.addObject("delteError", "");
			return mv;
		} catch (InvalidCallException e) {
			if (logger.isTraceEnabled())
				logger.trace("Informing Error: " + e.getMessage());
			ModelAndView mv = new ModelAndView("sectores/del");
			mv.addObject("sector", sector);
			mv.addObject("deleteError", e.getMessage());
			return mv;
		} catch (ServiceException e) {
			logger.error(e.getMessage());
			return new ModelAndView("404");
		}
	}

	@RequestMapping(method = RequestMethod.GET, value="/sectores/{sectorId}/del")
	public String showDeleteFormaJuridicaHandler(@PathVariable("sectorId") int sectorId, Model model) {
		Sector sector = null;
		try {
			sector = service.findById(sectorId);
			model.addAttribute(sector);
			return "sectores/del";
		} catch (ServiceException e) {
			logger.error(e.getMessage());
		}
		return "404";
	}

}
