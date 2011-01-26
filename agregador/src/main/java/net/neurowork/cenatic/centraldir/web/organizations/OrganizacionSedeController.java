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
import java.util.HashSet;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.neurowork.cenatic.centraldir.model.Satelite;
import net.neurowork.cenatic.centraldir.model.satelite.Organizacion;
import net.neurowork.cenatic.centraldir.model.satelite.OrganizacionSede;
import net.neurowork.cenatic.centraldir.model.satelite.Provincia;
import net.neurowork.cenatic.centraldir.service.OrganizacionSedeService;
import net.neurowork.cenatic.centraldir.service.OrganizacionService;
import net.neurowork.cenatic.centraldir.service.ProvinciaService;
import net.neurowork.cenatic.centraldir.service.SateliteService;
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
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author Alejandro Sanchez Acosta <asanchez@neurowork.net> http://www.neurowork.net
 * @since 14/12/2010
 */
@Controller
public class OrganizacionSedeController {
	private final static Logger logger = LoggerFactory.getLogger(OrganizacionSedeController.class);
	
	@Autowired
	private OrganizacionService organizacionService;
	@Autowired
	private OrganizacionSedeService organizacionSedeService;
	@Autowired
	private ProvinciaService provinciaService;
	@Autowired
	private SateliteService sateliteService;

	@RequestMapping(value="/orgSede/all", method=RequestMethod.GET)
    public @ResponseBody List<SedeDTO> getAllOrganizacionSedes(HttpServletRequest request) {
		if(logger.isTraceEnabled())
			logger.trace("Devolviendo todo los OrganizacionSede como JSON.");
		
		
		try {
			List<OrganizacionSede> l= organizacionSedeService.getAll();
			if(logger.isTraceEnabled())
				logger.trace("Devolviendo " + l.size() + " Sedes de Organizacion.");
			return buildSedeDTOList(l, request.getContextPath());
		} catch (ServiceException e) {
			logger.error(e.getMessage());
		}
		return null;
    }

	@RequestMapping(value="/orgSede/{sateliteId}", method=RequestMethod.GET)
    public @ResponseBody List<SedeDTO> getOrganizacionSedesBySatelite(
    		@PathVariable("sateliteId") int sateliteId,
    		HttpServletRequest request) {
		if(logger.isTraceEnabled())
			logger.trace("Devolviendo OrganizacionSede como JSON para el satelite: " + sateliteId);
		
		
		try {
			Satelite satelite = sateliteService.findById(sateliteId);
			List<OrganizacionSede> l= organizacionSedeService.findBySatelite(satelite);
			if(logger.isTraceEnabled())
				logger.trace("Devolviendo " + l.size() + " Sedes de Organizacion.");
			return buildSedeDTOList(l, request.getContextPath());
		} catch (ServiceException e) {
			logger.error(e.getMessage());
		}
		return null;
    }

	@RequestMapping(value="/orgSede", method=RequestMethod.GET)
    public @ResponseBody List<SedeDTO> getOrganizacionSedesBySateliteIds(
    		@RequestParam("ids") String ids, HttpServletRequest request) {
		if(logger.isTraceEnabled())
			logger.trace("DEVOLVIENDO OrganizacionSede como JSON para el satelite: " + ids);
		
		try {
			List<OrganizacionSede> l = new ArrayList<OrganizacionSede>();
			String[] satIds = ids.split(",");
			
			for(String satId : satIds){
				Integer id = Integer.parseInt(satId);
				if(id > 0){
					Organizacion organizacion = organizacionService.findById(id);
					if(organizacion != null){
						if(organizacion.getSedes() != null){
							l.addAll(organizacion.getSedes());						
						}
					}
				}
			}
			if(logger.isTraceEnabled())
				logger.trace("Devolviendo " + l.size() + " Sedes de Organizacion.");
			
			return buildSedeDTOList(l, request.getContextPath());
		} catch (ServiceException e) {
			logger.error(e.getMessage());
		}
		return null;
    }
	
	
	
	private List<SedeDTO> buildSedeDTOList(List<OrganizacionSede> l, String contextPath) {
		List<SedeDTO> ret = new ArrayList<SedeDTO>();
		for(OrganizacionSede sede : l){
			String address = createAddress(sede, contextPath);			
			ret.add(new SedeDTO(sede.getOrganizacion().getId(), sede.getLatitud(), sede.getLongitud(), address));
		}
		return ret;
	}


	private static String createAddress(OrganizacionSede sede, String contextPath) {
		StringBuffer ret = new StringBuffer();

		ret.append("<div style=\"width:250px;height:130px;font-size:1.4em;overflow:auto\"><span style=\"font-size:2.2em\"><a href=\"orgs/");
		ret.append(sede.getOrganizacion().getId());
		ret.append("\"><b style=\"font-size:14px;\">");
		ret.append(sede.getOrganizacion().getName());
		ret.append("</b><br /><img src=\"");
		ret.append(contextPath);
		ret.append("/static/images/icons/16x16/house.png\" />&nbsp;");
		ret.append(sede.getDireccion());
		ret.append("<br /><img src=\"");
		ret.append(contextPath);
		ret.append("/static/images/icons/16x16/phone.png\" /> &nbsp;");
		
		if(StringUtils.hasLength(sede.getTelefonoContacto())){
			ret.append("<a href=\"dialto:");
			ret.append(sede.getTelefonoContacto());
			ret.append("\">");
			ret.append(sede.getTelefonoContacto());
			ret.append("</a>"); 
		}else{
			ret.append("No Disponible");
		}
		ret.append("<br/><img src=\"");
		ret.append(contextPath);
		ret.append("/static/images/icons/16x16/user_suit.png\" /> &nbsp;");
		if(StringUtils.hasLength(sede.getPersonaContacto())){
			ret.append(sede.getPersonaContacto());
		}else{
			ret.append("No Disponible");
		}
		ret.append("<br/><img src=\"");
		ret.append(contextPath);
		ret.append("/static/images/icons/16x16/email.png\" /> &nbsp;");
		if(StringUtils.hasLength(sede.getMailContacto())){
			ret.append("<a href=\"mailto:");
			ret.append(sede.getMailContacto());
			ret.append("\">");
			ret.append(sede.getMailContacto());
			ret.append("</a>"); 
		}else{
			ret.append("No Disponible");
		}
		ret.append("</span></div>");		
		return ret.toString();
	}


	@RequestMapping(value="/orgs/{organizacionId}/sedes/new", method=RequestMethod.POST)
	public String addOrganizacionSedeHandler(@PathVariable("organizacionId") int organizacionId,
			@RequestParam("latitud") float latitud,
			@RequestParam("longitud") float longitud,
			@RequestParam("direccion") String direccion,
			@RequestParam("localidad") String localidad,
			@RequestParam("telefonoContacto") String telefonoContacto,
			@RequestParam("personaContacto") String personaContacto,
			@RequestParam("mailContacto") String mailContacto,
			@RequestParam("provinciaId") Integer provinciaId,
			@RequestParam("hombres") Integer hombres,
			@RequestParam("mujeres") Integer mujeres){
		if (logger.isTraceEnabled())
			logger.trace("Adding Sede to Organizacion with Id: " + organizacionId);
		
		String ret = "redirect:/orgs/" + organizacionId + "/edit#sedes";
		
		try {
			Organizacion organizacion = organizacionService.findById(organizacionId);
			
			if(organizacion == null){
				return "404";
			}
			Provincia provincia = provinciaService.findById(provinciaId);
			OrganizacionSede sede = new OrganizacionSede();
			
			sede.setOrganizacion(organizacion);
			sede.setLatitud(latitud);
			sede.setLongitud(longitud);
			sede.setDireccion(direccion);
			sede.setLocalidad(localidad);
			sede.setTelefonoContacto(telefonoContacto);
			sede.setPersonaContacto(personaContacto);
			sede.setMailContacto(mailContacto);
			sede.setProvincia(provincia);
			sede.setHombres(hombres);
			sede.setMujeres(mujeres);
			
			if(organizacion.getSedes() == null){
				organizacion.setSedes(new HashSet<OrganizacionSede>());
			}
			organizacion.getSedes().add(sede);
			organizacionService.saveOrganization(organizacion);
		} catch (ServiceException e) {
			logger.error(e.getMessage());
		}
		
		
		return ret;
	}
}
