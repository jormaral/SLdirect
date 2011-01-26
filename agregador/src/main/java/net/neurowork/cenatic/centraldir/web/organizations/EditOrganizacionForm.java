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

import java.util.Collection;

import net.neurowork.cenatic.centraldir.model.satelite.Asociacion;
import net.neurowork.cenatic.centraldir.model.satelite.ClasificacionOrganizacion;
import net.neurowork.cenatic.centraldir.model.satelite.FormaJuridica;
import net.neurowork.cenatic.centraldir.model.satelite.Organizacion;
import net.neurowork.cenatic.centraldir.model.satelite.Provincia;
import net.neurowork.cenatic.centraldir.model.satelite.Sector;
import net.neurowork.cenatic.centraldir.service.AsociacionService;
import net.neurowork.cenatic.centraldir.service.FormaJuridicaService;
import net.neurowork.cenatic.centraldir.service.OrganizacionService;
import net.neurowork.cenatic.centraldir.service.ProvinciaService;
import net.neurowork.cenatic.centraldir.service.SectorService;
import net.neurowork.cenatic.centraldir.service.ServiceException;
import net.neurowork.cenatic.centraldir.workers.XMLPusher;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
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
 * @since 17/12/2010
 */
@Controller
@RequestMapping("/orgs/{orgId}/edit")
@SessionAttributes(types = OrganizacionEditDTO.class)
public class EditOrganizacionForm {
	private final static Logger logger = LoggerFactory.getLogger(EditOrganizacionForm.class);
	
	@Autowired
	private OrganizacionService organizacionService;
	@Autowired
	private ProvinciaService provinciaService;
	@Autowired
	private FormaJuridicaService formaJuridicaService;
	@Autowired
	private AsociacionService asociacionService;
	@Autowired
	private SectorService sectorService;
	
	@InitBinder
	public void setAllowedFields(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}
	
	@ModelAttribute("provincias")
	public Collection<Provincia> populateProvincias(){
		try {
			return provinciaService.getProvincias();
		} catch (ServiceException e) {
		}
		return null;
	}
	
	@ModelAttribute("asociaciones")
	public Collection<Asociacion> populateAsociaciones(){
		try {
			return asociacionService.getAll();
		} catch (ServiceException e) {
		}
		return null;
	}

	@ModelAttribute("formaJuridicas")
	public Collection<FormaJuridica> populateFormasJuridicas(){
		try {
			return formaJuridicaService.getAll();
		} catch (ServiceException e) {
		}
		return null;
	}

	@ModelAttribute("sectores")
	public Collection<Sector> populateSectores(){
		try {
			return sectorService.getAll();
		} catch (ServiceException e) {
		}
		return null;
	}
	
	
	@ModelAttribute("clasificacionOrganizaciones")
	public Collection<ClasificacionOrganizacion> populateClasificacionOrganizaciones(){
		try {
			return organizacionService.getClasificacionOrganizaciones();
		} catch (ServiceException e) {
		}
		return null;
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public String setupForm(@PathVariable("orgId") int orgId, Model model) {
		if(logger.isTraceEnabled())
			logger.trace("Editing Organizacion width Id: " + orgId);
		
		Organizacion organizacion;
		try {
			organizacion = this.organizacionService.findById(orgId);
			if(organizacion != null){
				OrganizacionEditDTO dto = new OrganizacionEditDTO(organizacion);
				
				calcualarCompletado(model, organizacion);
				
				model.addAttribute("organizacion", dto);				
			}else{
				return "404";
			}
		} catch (ServiceException e) {
			logger.error(e.getMessage());
		} 
		return "orgs/edit";
	}
	
	@RequestMapping(method = RequestMethod.PUT)
	public String processSubmit(
			@ModelAttribute(value="organizacion") OrganizacionEditDTO dto, 
			BindingResult result, SessionStatus status) {
		if (logger.isTraceEnabled())
			logger.trace("Validating Organizacion: " + dto.getName());

		Organizacion organizacion = buildOrganizacion(dto);
		
		try {
			organizacionService.saveOrganization(organizacion);
			XMLPusher pusher = new XMLPusher(organizacion);
			pusher.push();
			
		} catch (ServiceException e) {
			logger.error(e.getMessage());
		}
		
		return "redirect:/admin#organizaciones";
	}	

	private Organizacion buildOrganizacion(OrganizacionEditDTO dto) {
		try {
			Organizacion organizacion = organizacionService.findById(dto.getId());
			
			organizacion.setName(dto.getName());
			organizacion.setWeb(dto.getWeb());
			organizacion.setLogoUrl(dto.getLogoUrl());
			organizacion.setClasificacionOrganizacion(dto.getClasificacionOrganizacion());
			organizacion.setFormaJuridica(dto.getFormaJuridica());
			organizacion.setDescripcion(dto.getDescripcion());
			organizacion.setCif(dto.getCif());
			organizacion.setAnoConstitucion(dto.getAnoConstitucion());			
			organizacion.setDireccion(dto.getDireccion());
			organizacion.setCodigoPostal(dto.getCodigoPostal());
			organizacion.setLocalidad(dto.getLocalidad());
			organizacion.setProvincia(dto.getProvincia());
			organizacion.setTelefono(dto.getTelefono());
			organizacion.setEmail(dto.getEmail());
			organizacion.setNewsTitle(dto.getNewsTitle());			
			organizacion.setNewsBody(dto.getNewsBody());
			organizacion.setCertificacionCenatic(dto.getCertificacionCenatic());
			organizacion.setPartners(dto.getPartners());
			organizacion.setGrupoEmpresarial(dto.getGrupoEmpresarial());
			organizacion.setCertificacionesCalidad(dto.getCertificacionesCalidad());
			organizacion.setRelacionesComunidad(dto.getRelacionesComunidad());
			organizacion.setParticipacionImasD(dto.getParticipacionImasD());
			
			return organizacion;
		} catch (ServiceException e) {
			logger.error(e.getMessage());
		}
		return null;
	}

	public static void calcualarCompletado(Model model, Organizacion organizacion) {
		int completeness = 100;
		String completeness_reason = "";
		
		if (!StringUtils.hasLength(organizacion.getName()) ||
			!StringUtils.hasLength(organizacion.getDescripcion()) || 
			organizacion.getFormaJuridica() == null ||
			organizacion.getClasificacionOrganizacion() == null ||  
			organizacion.getAnoConstitucion() == null ||
			organizacion.getAnoConstitucion() == 0 || 
			!StringUtils.hasLength(organizacion.getDireccion()) ||
			!StringUtils.hasLength(organizacion.getLocalidad())) { 
			completeness -= 20; 
			completeness_reason = " &raquo; Faltan datos generales de la organizaci&oacute;n.<br />"; 
		}
		if(organizacion.getSedes() == null || organizacion.getSedes().size() < 1) { 
			completeness -= 20; 
			completeness_reason +=" &raquo; No ha introducido ninguna sede.<br />"; 
		}
		
		if(organizacion.getOfertas() == null){
			completeness -= 40; 
			completeness_reason +=" &raquo; No ha introducido servicios.<br />"; 
		}else if (organizacion.getOfertas().size() < 4) {
			completeness -= 10 * (4 - organizacion.getOfertas().size()); 
			completeness_reason +=" &raquo; Ha introducido pocos servicios.<br />"; 
		}

		if(organizacion.getDemandas() == null){
			completeness -= 20; 
			completeness_reason +=" &raquo; No ha introducido demanda de servicios.<br />";  
		}else if (organizacion.getDemandas().size() < 2) { 
			completeness -= 10 * (2 - organizacion.getDemandas().size()); 
			completeness_reason +=" &raquo; Ha introducido poca demanda de servicios.<br />";  
		}
		
		model.addAttribute("completeness_reason", completeness_reason);
		model.addAttribute("completado", completeness);
	}	
}
