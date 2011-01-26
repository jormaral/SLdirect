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
package net.neurowork.cenatic.centraldir.workers.xml;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import net.neurowork.cenatic.centraldir.model.satelite.Evento;
import net.neurowork.cenatic.centraldir.model.satelite.Organizacion;
import net.neurowork.cenatic.centraldir.model.satelite.OrganizacionEvento;
import net.neurowork.cenatic.centraldir.service.OrganizacionService;
import net.neurowork.cenatic.centraldir.service.ServiceException;
import net.neurowork.cenatic.centraldir.util.XmlParserUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @since 07/01/2011
 */
public class EventosXmlImporter {
	private final static Logger logger = LoggerFactory.getLogger(EventosXmlImporter.class);
	private static SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd"); 

	private OrganizacionService organizacionService;
	
	public EventosXmlImporter(OrganizacionService organizacionService) {
		super();
		this.organizacionService = organizacionService;
	}
	
	
	public Set<OrganizacionEvento> buscarEventos(Element eventsEl, Organizacion organizacion) {
		if(eventsEl == null)
			return null;
		
		Set<OrganizacionEvento> ret = new HashSet<OrganizacionEvento>();
		
		NodeList nodeLst = eventsEl.getElementsByTagName("event");
		
		for (int s = 0; s < nodeLst.getLength(); s++) {
		    Node fstNode = nodeLst.item(s);
	        if (fstNode.getNodeType() == Node.ELEMENT_NODE) {
	        	Element elEvento = (Element)fstNode;
      	
	        	String localization = XmlParserUtil.getStringNodeValue(elEvento, "localization");
	        	String title = XmlParserUtil.getStringNodeValue(elEvento, "title");
        		String description = XmlParserUtil.getStringNodeValue(elEvento, "description");
				String date = XmlParserUtil.getStringNodeValue(elEvento, "date");
				
				Date fecha = null;
				try {
					fecha = formatter.parse(date);
				} catch (ParseException e) {
				}
				
				Evento evento = null;
				
				if(organizacion.getEventos() == null){
					evento = new Evento();
				}else{
					for(OrganizacionEvento oe : organizacion.getEventos()){
						Evento e = oe.getEvento();
						if(e.getTitulo().equals(title)){
							evento = e;
							break;
						}							
					}					
					if(evento == null)
						evento = new Evento();
				}
				
				evento.setLocalizacion(localization);
				evento.setTitulo(title);
				evento.setDescripcion(description);
				evento.setFecha(fecha);
				try {
					organizacionService.saveEvento(evento);
					
		        	OrganizacionEvento eventoOrg = new OrganizacionEvento();
					eventoOrg.setEvento(evento);
					eventoOrg.setOrganizacion(organizacion);		        	
		        	ret.add(eventoOrg);	        	
				} catch (ServiceException e) {
					logger.error(e.getMessage());
				}				
	        }
		}		
		return ret;
	}
	
}
