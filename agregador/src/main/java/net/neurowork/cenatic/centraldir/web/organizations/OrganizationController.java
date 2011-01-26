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
package net.neurowork.cenatic.centraldir.web.organizations;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import net.neurowork.cenatic.centraldir.model.satelite.Organizacion;
import net.neurowork.cenatic.centraldir.service.OrganizacionService;
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
 * @since 10/12/2010
 */
@Controller
@SessionAttributes(types = Organizacion.class)
public class OrganizationController {
	private final static Logger logger = LoggerFactory.getLogger(OrganizationController.class);
	
	@Autowired
	private OrganizacionService organizacionService;
	
	@RequestMapping("/orgs/{orgId}")
	public ModelAndView organizacionHandler(@PathVariable("orgId") int orgId) {
		if(logger.isTraceEnabled())
			logger.trace("Showing Information for Organizacion width Id: " + orgId);
		
		ModelAndView mav = new ModelAndView("orgs/show");
		Organizacion organizacion;
		try {
			organizacion = this.organizacionService.findById(orgId);
			if(organizacion != null){
				mav.addObject("organizacion", organizacion);
			}else{
				return new ModelAndView("404");
			}
		} catch (ServiceException e) {
			logger.error(e.getMessage());
		} 
		return mav;
	}
	
	@RequestMapping("/orgs")
	public ModelAndView organizacionesHandler() {
		ModelAndView mav = new ModelAndView("orgs/list");
		try {
			List<Organizacion> organizaciones = new ArrayList<Organizacion>(this.organizacionService.getOrganizaciones()); 
			Collections.sort(organizaciones, new Comparator<Organizacion>(){
				@Override
				public int compare(Organizacion arg0, Organizacion arg1) {
					return arg0.getName().compareTo(arg1.getName());
				}				
			});
			mav.addObject("organizaciones", organizaciones);			
		} catch (ServiceException e) {
			logger.error(e.getMessage());
		}
		return mav;
	}
	
	@RequestMapping(value="/orgs/sectores/{sectores}/capacidades/{capacidades}", method=RequestMethod.GET)
    public @ResponseBody List<DemandaSearchResult> handleOrganizacionBySectoresAndCapacidades(
    		@PathVariable("sectores") String sectores,
    		@PathVariable("capacidades") String capacidades) {
		if(logger.isTraceEnabled())
			logger.trace("Devolviendo todo las Organizacion como JSON: Sectores: " + sectores + " Capacidades: " +capacidades);
		if(logger.isTraceEnabled())
			logger.trace("Devolviendo todo las Organizacion como JSON: Sectores: " + sectores);
		
		String[] ids = sectores.split(", "); 
		Integer[] idsSectores = new Integer[ids.length];
		for(int i=0;i < ids.length; i++){
			idsSectores[i] = Integer.parseInt(ids[i]);
		}
		
		try {
			//TODO reemplazar para findBySectoresAndCapacidades
//			Collection<Organizacion> orgs = organizacionService.findByCapacidades(idsSectores);
			Collection<Organizacion> orgs = organizacionService.findByCapacidades(idsSectores);
			return buildDemandaSerachResultList(orgs);
		} catch (ServiceException e) {
			logger.error(e.getMessage());
		}
		return null;
    }	
	
	@RequestMapping(value="/orgs/sectores/{sectores}/demanda", method=RequestMethod.GET)
    public @ResponseBody List<DemandaSearchResult> handleDemandaBySectores(
    		@PathVariable("sectores") String sectores) {
		if(logger.isTraceEnabled())
			logger.trace("Devolviendo todo las Organizacion como JSON: DemandaSectores: " + sectores);
		
		String[] ids = sectores.split(", "); 
		Integer[] idsSectores = new Integer[ids.length];
		for(int i=0;i < ids.length; i++){
			idsSectores[i] = Integer.parseInt(ids[i]);
		}
		try {
			Collection<Organizacion> orgs = organizacionService.findByDemandaSectores(idsSectores);
			return buildDemandaSerachResultList(orgs);
		} catch (ServiceException e) {
			logger.error(e.getMessage());
		}
		return null;
    }	
	
	@RequestMapping(value="/orgs/capacidades/{capacidades}/demanda", method=RequestMethod.GET)
    public @ResponseBody List<DemandaSearchResult> handleDemandaByCapacidades(
    		@PathVariable("capacidades") String capacidades) {
		if(logger.isTraceEnabled())
			logger.trace("Devolviendo todo las Organizacion como JSON: Capacidades: " +capacidades);

		String[] ids = capacidades.split(", "); 
		Integer[] idsCapacidades = new Integer[ids.length];
		for(int i=0;i < ids.length; i++){
			idsCapacidades[i] = Integer.parseInt(ids[i]);
		}
		
		try {
			Collection<Organizacion> orgs = organizacionService.findByDemandaCapacidades(idsCapacidades);
			return buildDemandaSerachResultList(orgs);
		} catch (ServiceException e) {
			logger.error(e.getMessage());
		}
		return null;
    }

	@RequestMapping(value="/orgs/sectores/{sectores}/servicio", method=RequestMethod.GET)
    public @ResponseBody List<DemandaSearchResult> handleServivioBySectores(
    		@PathVariable("sectores") String sectores) {
		if(logger.isTraceEnabled())
			logger.trace("Devolviendo todo las Organizacion como JSON: ServicioSectores: " + sectores);
		
		String[] ids = sectores.split(", "); 
		Integer[] idsSectores = new Integer[ids.length];
		for(int i=0;i < ids.length; i++){
			idsSectores[i] = Integer.parseInt(ids[i]);
		}
		try {
			Collection<Organizacion> orgs = organizacionService.findByOfertaSectores(idsSectores);
			return buildDemandaSerachResultList(orgs);
		} catch (ServiceException e) {
			logger.error(e.getMessage());
		}
		return null;
    }	
	
	@RequestMapping(value="/orgs/capacidades/{capacidades}/servicio", method=RequestMethod.GET)
    public @ResponseBody List<DemandaSearchResult> handleServicioByCapacidades(
    		@PathVariable("capacidades") String capacidades) {
		if(logger.isTraceEnabled())
			logger.trace("Devolviendo todo las Organizacion como JSON: Capacidades: " +capacidades);

		String[] ids = capacidades.split(", "); 
		Integer[] idsCapacidades = new Integer[ids.length];
		for(int i=0;i < ids.length; i++){
			idsCapacidades[i] = Integer.parseInt(ids[i]);
		}
		
		try {
			Collection<Organizacion> orgs = organizacionService.findByOfertaCapacidades(idsCapacidades);
			return buildDemandaSerachResultList(orgs);
		} catch (ServiceException e) {
			logger.error(e.getMessage());
		}
		return null;
    }
	
	private static List<DemandaSearchResult> buildDemandaSerachResultList(Collection<Organizacion> orgs) {
		List<DemandaSearchResult> ret = new ArrayList<DemandaSearchResult>();
		for(Organizacion organizacion : orgs){
			DemandaSearchResult r = new DemandaSearchResult();
			r.setId(organizacion.getId());
			r.setNombre(organizacion.getName());
			r.setDireccion(organizacion.getDireccion());
			if(organizacion.getProvincia() != null)
				r.setProvincia(organizacion.getProvincia().getName());
			else
				r.setProvincia("Sin Provincia");
			r.setCapacidadMatch("");
			r.setSectorMatch("");
			ret.add(r);
		}
		if(logger.isTraceEnabled())
			logger.trace("Devolviendo: " + ret);
		return ret;
	}	
}
