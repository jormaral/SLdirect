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

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

import net.neurowork.cenatic.centraldir.model.Satelite;
import net.neurowork.cenatic.centraldir.model.satelite.Capacidad;
import net.neurowork.cenatic.centraldir.model.satelite.ClasificacionOrganizacion;
import net.neurowork.cenatic.centraldir.model.satelite.Organizacion;
import net.neurowork.cenatic.centraldir.model.satelite.OrganizacionSatelite;
import net.neurowork.cenatic.centraldir.service.OrganizacionService;
import net.neurowork.cenatic.centraldir.service.SectorService;
import net.neurowork.cenatic.centraldir.service.ServiceException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Alejandro Sanchez Acosta <asanchez@neurowork.net> http://www.neurowork.net
 * @since 08/11/2010
 */
@Controller
public class SearchController {
	private static final Logger logger = LoggerFactory.getLogger(SearchController.class);

	@Autowired
	private OrganizacionService organizacionService;
	@Autowired
	private SectorService sectorService;
	
	@RequestMapping("/busqueda")
	public ModelAndView busquedaHandler() {
		logger.trace("Search...");
		ModelAndView mv = new ModelAndView("busqueda/show");		
		try {
			Collection<Organizacion> organizaciones = organizacionService.getOrganizaciones();
						
			mv.addObject("totalOrganizaciones", organizacionService.getTotalOrganizaciones());
			mv.addObject("organizacionClasificaciones", organizacionService.getClasificacionOrganizaciones());
			mv.addObject("sateliteOrgsMaps", buildSateliteOrganizacionesMap(organizaciones));
		} catch (ServiceException e) {
			logger.error(e.getMessage());
		}
		return mv;
	}
	
	private static Map<Satelite, List<Organizacion>> buildSateliteOrganizacionesMap(Collection<Organizacion> organizaciones) {
		SortedMap<Satelite, List<Organizacion>> ret = new TreeMap<Satelite, List<Organizacion>>(new Comparator<Satelite>() {
			public int compare(Satelite arg0, Satelite arg1) {
				return arg0.getName().compareTo(arg1.getName());
			}
		});		
		
		logger.trace("Indexing: " + organizaciones.size() + " organizaciones.");

		for(Organizacion organizacion : organizaciones){
			if(organizacion.getSatelites() != null){
				logger.trace("Indexing " + organizaciones + ",  satelites: " + organizacion.getSatelites().size());
				for(OrganizacionSatelite os: organizacion.getSatelites()){
					List<Organizacion> l = ret.get(os.getSatelite());					
					if(l == null){
						l = new ArrayList<Organizacion>();
						ret.put(os.getSatelite(), l);
					}
					l.add(organizacion);					
				}
			}else{
				logger.trace("Organizacion " + organizaciones + " no tiene satelites.");
			}			
		}
		
		for(Satelite satelite : ret.keySet()){
			List<Organizacion> l = ret.get(satelite);
			Collections.sort(l, new Comparator<Organizacion>(){
				public int compare(Organizacion arg0, Organizacion arg1) {
					return arg0.getName().compareTo(arg1.getName());
				}
			});
		}
		
		logger.trace("Returning: " + ret.keySet().size() + " satelites.");
		
		return ret ;
	}

	@RequestMapping("/busqueda/{orgClassId}")
	public ModelAndView busquedaOrgClassHandler(@PathVariable("orgClassId") int orgClassId) {
		if(logger.isTraceEnabled())
			logger.trace("Searching by Organizacion Clasificacion: " + orgClassId);
		
		ModelAndView mv = new ModelAndView("busqueda/show");
		try {
			ClasificacionOrganizacion classOrg = organizacionService.findClasificacionOrganizacionById(orgClassId);
			
			List<Organizacion> organizaciones = organizacionService.findByClasificacionOrganizacion(classOrg);
			
			mv.addObject("totalOrganizaciones", organizacionService.getTotalOrganizaciones());
			mv.addObject("organizacionClasificaciones", organizacionService.getClasificacionOrganizaciones());
			mv.addObject("sateliteOrgsMaps", buildSateliteOrganizacionesMap(organizaciones));
			mv.addObject("classOrg", classOrg);
		} catch (ServiceException e) {
			logger.error(e.getMessage());
		} 
		return mv;
	}

	@RequestMapping(value="/busquedaTexto", method = RequestMethod.GET)
	public ModelAndView busquedaTextHandler(@RequestParam("q") String q) {
		logger.trace("Busqueda de Texto: " + q);
		ModelAndView mv = new ModelAndView("orgs/list");
		Collection<Organizacion> organizaciones;
		try {
			organizaciones = this.organizacionService.findByName(q); 
			mv.addObject("organizaciones", organizaciones);			
		} catch (ServiceException e) {
			logger.error(e.getMessage());
		}
		return mv;
	}	
	
	@RequestMapping("/busquedaGeo")
	public ModelAndView busquedaGeoHandler() {
		logger.trace("Search...");
		ModelAndView mv = new ModelAndView("busqueda/geo");		
		try {
			mv.addObject("totalOrganizaciones", organizacionService.getTotalOrganizaciones());
			mv.addObject("organizaciones", organizacionService.getOrganizaciones());
			mv.addObject("organizacionClasificaciones", organizacionService.getClasificacionOrganizaciones());
		} catch (ServiceException e) {
			logger.error(e.getMessage());
		}
		return mv;
	}
	
	@RequestMapping("/busquedaDemanda")
	public ModelAndView busquedaDemandaHandler() {
		logger.trace("Search...");
		ModelAndView mv = new ModelAndView("busqueda/demanda");		
		try {
			Collection<Capacidad> capList = organizacionService.getCapacidades();
			mv.addObject("capMap", buildCapacidadCategoriaMap(capList));
			mv.addObject("sectores", sectorService.getAll());			
		} catch (ServiceException e) {
			logger.error(e.getMessage());
		}
		return mv;
	}
	
	@RequestMapping("/busquedaServicio")
	public ModelAndView busquedaOfertasHandler() {
		logger.trace("Search...");
		ModelAndView mv = new ModelAndView("busqueda/servicio");		
		try {
			Collection<Capacidad> capList = organizacionService.getCapacidades();
			
			mv.addObject("capMap", buildCapacidadCategoriaMap(capList));
			mv.addObject("sectores", sectorService.getAll());
		} catch (ServiceException e) {
			logger.error(e.getMessage());
		}
		return mv;
	}
	
	private static Map<String, Set<Capacidad>> buildCapacidadCategoriaMap(Collection<Capacidad> capacidades) {
		Map<String, Set<Capacidad>> ret = new HashMap<String, Set<Capacidad>>();
		
		String emptyString = "";
		
		for(Capacidad capacidad : capacidades){
			String key = capacidad.getCategoria();
			if(!StringUtils.hasLength(key))
				key = emptyString;
			
			Set<Capacidad> categiaSet = ret.get(key);
			if(categiaSet == null){
				categiaSet = new HashSet<Capacidad>();
				ret.put(key, categiaSet);
			}
			categiaSet.add(capacidad);
		}
		
		return ret;
	}	
}
