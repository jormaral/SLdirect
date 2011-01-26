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

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.neurowork.cenatic.centraldir.model.Satelite;
import net.neurowork.cenatic.centraldir.model.dto.SateliteDTO;
import net.neurowork.cenatic.centraldir.model.satelite.Organizacion;
import net.neurowork.cenatic.centraldir.service.OrganizacionService;
import net.neurowork.cenatic.centraldir.service.SateliteService;
import net.neurowork.cenatic.centraldir.service.ServiceException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Alejandro Sanchez Acosta <asanchez@neurowork.net> http://www.neurowork.net
 * @since 12/11/2010
 */
@Controller
@SessionAttributes(types = Satelite.class)
public class SateliteController {
	private final static Logger logger = LoggerFactory.getLogger(SateliteController.class);
	
	@Autowired
	private SateliteService sateliteService;
	@Autowired
	private OrganizacionService organizacionService;

	@RequestMapping("/satelites")
	public String satelitesHandler() {
		return "redirect:/admin#satelites";
//		ModelAndView mv = new ModelAndView("satelites/list");
//		Collection<Satelite> satelites;
//		try {
//			satelites = sateliteService.getSatelites();
//			mv.addObject("satelites", satelites);
//		} catch (ServiceException e) {
//			logger.error(e.getMessage());
//		}
//		return mv;
	}
	
	@RequestMapping(value="/satelites/all", method=RequestMethod.GET)
    public @ResponseBody List<SateliteDTO> getAllSatelitesJSON(HttpServletRequest request) {
		if(logger.isTraceEnabled())
			logger.trace("Devolviendo todo los Satelites como JSON.");
		
		try {
			List<Satelite> satelites = (List<Satelite>)sateliteService.getSatelites();
			if(logger.isTraceEnabled())
				logger.trace("Devolviendo " + satelites.size() + " Satelites.");
			return buildSateliteDTO(satelites);
		} catch (ServiceException e) {
			logger.error(e.getMessage());
		}
		return null;
    }
	
	
	private List<SateliteDTO> buildSateliteDTO(List<Satelite> satelites) {
		List<SateliteDTO> ret = new ArrayList<SateliteDTO>();
		for(Satelite sat : satelites){
			String html = buildSateliteHtml(sat);
			Integer numEmpresas;
			try {
				numEmpresas = sateliteService.getOrganizacionesSatelite(sat);
				SateliteDTO dto = new SateliteDTO(sat, numEmpresas);
				dto.setHtml(html);
				ret.add(dto);
			} catch (ServiceException e) {
			}
		}
		return ret;
	}

	private static String buildSateliteHtml(Satelite sat) {
		StringBuffer ret = new StringBuffer();

		ret.append("<div style=\"width:250px;height:130px;font-size:1.4em;overflow:auto\"><span style=\"font-size:2.2em\"><a href=\"satelites/");
		ret.append(sat.getId());
		ret.append("/orgs\"><b style=\"font-size:14px;\">");
		ret.append(sat.getName());
		
		ret.append("</span></div>");		
		return ret.toString();
	}

	@RequestMapping("/satelites/{sateliteId}")
	public ModelAndView sateliteHandler(@PathVariable("sateliteId") int sateliteId) {
		if(logger.isTraceEnabled())
			logger.trace("Showing Information for Satelite width Id: " + sateliteId);
		
		ModelAndView mav = new ModelAndView("satelites/show");
		Satelite satelite;
		try {
			satelite = this.sateliteService.findById(sateliteId);
			if(satelite != null){
				mav.addObject("satelite", satelite);
			}else{
				return new ModelAndView("404");
			}
		} catch (ServiceException e) {
			logger.error(e.getMessage());
		} 
		return mav;
	}
	
	@RequestMapping("/satelites/{sateliteId}/start")
	public String startSateliteHandler(@PathVariable("sateliteId") int sateliteId) {
		if(logger.isTraceEnabled())
			logger.trace("Starting Satelite width Id: " + sateliteId);
		
		Satelite satelite;
		try {
			satelite = this.sateliteService.findById(sateliteId);
			if(satelite != null){
				satelite.setActivado(Boolean.TRUE);
				sateliteService.saveSatelite(satelite);
				return "redirect:/admin#satelites";
			}
		} catch (ServiceException e) {
			logger.error(e.getMessage());
		} 
		return "redirect:/404";
	}
	
	@RequestMapping("/satelites/{sateliteId}/stop")
	public String stopSateliteHandler(@PathVariable("sateliteId") int sateliteId) {
		if(logger.isTraceEnabled())
			logger.trace("Stopping Satelite width Id: " + sateliteId);
		
		Satelite satelite;
		try {
			satelite = this.sateliteService.findById(sateliteId);
			if(satelite != null){
				satelite.setActivado(Boolean.FALSE);
				sateliteService.saveSatelite(satelite);
				return "redirect:/admin#satelites";
			}
		} catch (ServiceException e) {
			logger.error(e.getMessage());
		} 
		return "redirect:/404";
	}
	
	@RequestMapping("/mapa")
	public ModelAndView mapaHandler() {
		logger.trace("Google Map");

		ModelAndView mv = new ModelAndView("mapa");
		Collection<Satelite> satelites;
		try {
			satelites = sateliteService.getSatelites();
			mv.addObject("satelites", satelites);
		} catch (ServiceException e) {
			logger.error(e.getMessage());
		}
		return mv;
	}
	
	@RequestMapping("/satelites/{sateliteId}/orgs")	
	public ModelAndView retrieveSateliteOrgsHandler(@PathVariable("sateliteId") int sateliteId) {
		if(logger.isTraceEnabled())
			logger.trace("List Organizaciones for Satelite width Id: " + sateliteId);
		
		ModelAndView mav = new ModelAndView("satelites/orgs");
		Satelite satelite;
		try {
			satelite = this.sateliteService.findById(sateliteId);
			List<Organizacion> organizaciones = this.organizacionService.findBySatelite(satelite);
			
			Collections.sort(organizaciones, new Comparator<Organizacion>(){
				@Override
				public int compare(Organizacion arg0, Organizacion arg1) {
					return arg0.getName().compareTo(arg1.getName());
				}				
			});
			
			mav.addObject("organizaciones", organizaciones);			
			mav.addObject("satelite", satelite);			
		} catch (ServiceException e) {
			logger.error(e.getMessage());
		} 
		return mav;
	}
	

}
