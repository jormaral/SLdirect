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

import java.util.HashSet;
import java.util.Set;

import net.neurowork.cenatic.centraldir.model.satelite.Capacidad;
import net.neurowork.cenatic.centraldir.model.satelite.Organizacion;
import net.neurowork.cenatic.centraldir.model.satelite.OrganizacionCapacidadOferta;
import net.neurowork.cenatic.centraldir.model.satelite.Sector;
import net.neurowork.cenatic.centraldir.util.XmlParserUtil;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @since 07/01/2011
 */
public class OfertasXmlImporter {
	private CapacidadXmlImporter capacidadImporter;
	private SectorXmlImporter sectorImporter;
	
	public OfertasXmlImporter(CapacidadXmlImporter capacidadImporter,
			SectorXmlImporter sectorImporter) {
		super();
		this.capacidadImporter = capacidadImporter;
		this.sectorImporter = sectorImporter; 
	}
	
	public Set<OrganizacionCapacidadOferta> buscarOfertas(Element capacitiesEl, Organizacion organizacion) {
		if(capacitiesEl == null)
			return null;

		Set<OrganizacionCapacidadOferta> ret = new HashSet<OrganizacionCapacidadOferta>();
		NodeList nodeLst = capacitiesEl.getElementsByTagName("capacity");
		
		for (int s = 0; s < nodeLst.getLength(); s++) {
		    Node fstNode = nodeLst.item(s);
	        if (fstNode.getNodeType() == Node.ELEMENT_NODE) {
	        	Element elCapacity = (Element)fstNode;
	        
	        	String capacityCode = XmlParserUtil.getAttribute(elCapacity, "capacity_code");
	        	String sectorCode = XmlParserUtil.getAttribute(elCapacity, "sector_code");
	        	String resources = XmlParserUtil.getAttribute(elCapacity, "resources");
	        	String score = XmlParserUtil.getAttribute(elCapacity, "score");
	        	String billingProportion = XmlParserUtil.getAttribute(elCapacity, "billingProportion");
	        	String description = XmlParserUtil.getAttribute(elCapacity, "description");
	        	
	        	Capacidad capacidad = capacidadImporter.buscarCapacidad(Integer.parseInt(capacityCode));
	        	Sector sector = sectorImporter.buscarSector(Integer.parseInt(sectorCode));

	        	OrganizacionCapacidadOferta oferta = null;

	        	if(organizacion.getOfertas() != null){
	        		for(OrganizacionCapacidadOferta o : organizacion.getOfertas()){
	        			if(o.getSector().equals(sector) && 
	        			   o.getCapacidad().equals(capacidad)){
	        				oferta = o;
	        				break;
	        			}
	        		}
	        	}
	        	
	        	if(oferta == null){
	        		oferta = new OrganizacionCapacidadOferta();
		        	oferta.setOrganizacion(organizacion);
					oferta.setCapacidad(capacidad);
					oferta.setSector(sector );
	        	}
	        	oferta.setRecursos(Integer.parseInt(resources));
	        	oferta.setPuntuacion(Integer.parseInt(score));
	        	oferta.setPorcentajeFacturacion(Integer.parseInt(billingProportion));
	        	oferta.setDescripcion(description);
	        	
	        	ret.add(oferta);
	        }
		}
		
		return ret;
	}

}
