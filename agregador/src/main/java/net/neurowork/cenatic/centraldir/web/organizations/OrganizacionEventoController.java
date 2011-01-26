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

import net.neurowork.cenatic.centraldir.model.satelite.Organizacion;
import net.neurowork.cenatic.centraldir.model.satelite.OrganizacionEvento;
import net.neurowork.cenatic.centraldir.service.EventoService;
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

/**
 *
 * @author Alejandro Sanchez Acosta <asanchez@neurowork.net> http://www.neurowork.net
 * @since 27/12/2010
 */
@Controller
public class OrganizacionEventoController {
	private final static Logger logger = LoggerFactory.getLogger(OrganizacionEventoController.class);
	
	@Autowired
	private EventoService eventoService;
	@Autowired
	private OrganizacionService organizacionService;
	
	@RequestMapping(value="/orgs/{organizacionId}/eventos/{eventoId}/del", method=RequestMethod.GET)
    public @ResponseBody String deleteOrganizacionCapacidad(@PathVariable("organizacionId") int organizacionId, 
    		@PathVariable("eventoId") int eventoId) {
		if(logger.isTraceEnabled())
			logger.trace("Deleting Evento with id: " + eventoId);
		
		try {
			OrganizacionEvento evento = eventoService.findById(eventoId);
			Organizacion organizacion = organizacionService.findById(organizacionId);
			
			if(organizacion.getEventos() != null){
				organizacion.getEventos().remove(evento);
				organizacionService.saveOrganization(organizacion);
			}			
			eventoService.delete(evento);		
		} catch (ServiceException e) {
			logger.error(e.getMessage());
			return e.getMessage();
		}
		return "";
    }
	

}
