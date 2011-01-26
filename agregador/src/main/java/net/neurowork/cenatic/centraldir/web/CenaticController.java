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
package net.neurowork.cenatic.centraldir.web;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

import net.neurowork.cenatic.centraldir.model.NamedEntity;
import net.neurowork.cenatic.centraldir.model.dto.EventDTO;
import net.neurowork.cenatic.centraldir.model.satelite.Organizacion;
import net.neurowork.cenatic.centraldir.model.satelite.Provincia;
import net.neurowork.cenatic.centraldir.service.AsociacionService;
import net.neurowork.cenatic.centraldir.service.FormaJuridicaService;
import net.neurowork.cenatic.centraldir.service.IndicadorService;
import net.neurowork.cenatic.centraldir.service.OrganizacionService;
import net.neurowork.cenatic.centraldir.service.ProvinciaService;
import net.neurowork.cenatic.centraldir.service.SateliteService;
import net.neurowork.cenatic.centraldir.service.SectorService;
import net.neurowork.cenatic.centraldir.service.ServiceException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Alejandro Sanchez Acosta <asanchez@neurowork.net> http://www.neurowork.net
 * @since 04/11/2010
 */
@Controller
public class CenaticController {
	private static final Logger logger = LoggerFactory.getLogger(CenaticController.class);
	
	@Autowired
	private SateliteService sateliteService;
	@Autowired
	private IndicadorService indicadorService;
	@Autowired
	private ProvinciaService provinciaService;
	@Autowired
	private SectorService sectorService;
	@Autowired
	private AsociacionService asociacionService;
	@Autowired
	private FormaJuridicaService formaJuridicaService;
	@Autowired
	private OrganizacionService organizacionService;

	private static final String DATE_FORMAT = "MM-dd-yyyy";
	
	private static DateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
	
	private static Comparator<NamedEntity> namedEntityComparator = new Comparator<NamedEntity>() {
		@Override
		public int compare(NamedEntity arg0, NamedEntity arg1) {
			return arg0.getName().compareTo(arg1.getName());
		}
		
	};
	

	/**
	 * Custom handler for the welcome view.
	 * <p>
	 * Note that this handler relies on the RequestToViewNameTranslator to
	 * determine the logical view name based on the request URL: "/welcome.do"
	 * -&gt; "welcome".
	 */
	@RequestMapping("/")
	public ModelAndView welcomeHandler() {
		logger.info("welcome");
		ModelAndView mv = new ModelAndView("index");
		try {
			Set<EventDTO> eventos = organizacionService.getEvents();
			
			mv.addObject("satelites", sateliteService.getSatelitesDto());
			mv.addObject("noticias", organizacionService.getNews());
			mv.addObject("eventos", eventos);
			mv.addObject("fechasEventos", buildFechas(eventos)); 
			mv.addObject("totalEmpresas", organizacionService.getTotalOrganizaciones());
			mv.addObject("latestOrgs", organizacionService.getLatest(3));
		} catch (ServiceException e) {
			logger.error(e.getMessage());
		}
		return mv;
	}
	
	private String buildFechas(Set<EventDTO> eventos) {
		StringBuffer ret = new StringBuffer();
		
		String sep = ", ";
		for(EventDTO dto : eventos){
			ret.append("'");
			ret.append(dateFormat.format(dto.getFecha()));			
			ret.append("'");
			ret.append(sep);
		}
		ret.append("''");
		
		return ret.toString();
	}

	@RequestMapping("/login")
	public String loginHandler() {
		logger.trace("Login User");
		return "login";
	}

	@RequestMapping("/terminos")
	public String terminosHandler() {
		return "terminos";
	}
	
	@RequestMapping("/privacidad")
	public String privacidadHandler() {
		return "privacidad";
	}
	
	@RequestMapping("/admin")
	public ModelAndView adminHandler() {
		logger.trace("Admin User");
		ModelAndView mv = new ModelAndView("admin/show");
		try {
			List<Organizacion> organizaciones = new ArrayList<Organizacion>(organizacionService.getOrganizaciones());
			List<Provincia> provincias = new ArrayList<Provincia>(provinciaService.getProvincias());
			
			Collections.sort(organizaciones, namedEntityComparator);			
			Collections.sort(provincias, namedEntityComparator);			
			
			mv.addObject("satelites", sateliteService.getSatelites());
			mv.addObject("indicadores", indicadorService.getIndicators());
			mv.addObject("provincias", provincias);
			mv.addObject("sectores", sectorService.getAll());
			mv.addObject("asociaciones", asociacionService.getAll());
			mv.addObject("formasJuridicas", formaJuridicaService.getAll());
			mv.addObject("organizaciones", organizaciones);
		} catch (ServiceException e) {
			logger.error(e.getMessage());
		}
		return mv;
	}
}
