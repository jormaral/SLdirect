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
import net.neurowork.cenatic.centraldir.model.satelite.OrganizacionCapacidadDemanda;
import net.neurowork.cenatic.centraldir.model.satelite.Sector;
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
public class DemandasXmlImporter {
	private final static Logger logger = LoggerFactory.getLogger(DemandasXmlImporter.class);
	private CapacidadXmlImporter capacidadImporter;
	private SectorXmlImporter sectorImporter;
	
	public DemandasXmlImporter(CapacidadXmlImporter capacidadImporter,
			SectorXmlImporter sectorImporter) {
		super();
		this.capacidadImporter = capacidadImporter;
		this.sectorImporter = sectorImporter; 
	}
	

	public Set<OrganizacionCapacidadDemanda> buscarDemandas(Element demandsEl, Organizacion organizacion) {
		Set<OrganizacionCapacidadDemanda> ret = new HashSet<OrganizacionCapacidadDemanda>();

		NodeList nodeLst = demandsEl.getElementsByTagName("demand");
		
		for (int s = 0; s < nodeLst.getLength(); s++) {
		    Node fstNode = nodeLst.item(s);
	        if (fstNode.getNodeType() == Node.ELEMENT_NODE) {
	        	Element elDemand = (Element)fstNode;
	        
	        	String capacityCode = XmlParserUtil.getAttribute(elDemand, "capacity_code");
	        	String sectorCode = XmlParserUtil.getAttribute(elDemand, "sector_code");
	        	String description = XmlParserUtil.getAttribute(elDemand, "description");

	        	Sector sector = sectorImporter.buscarSector(Integer.parseInt(sectorCode));
	        	Capacidad capacidad = capacidadImporter.buscarCapacidad(Integer.parseInt(capacityCode));
	        	
	        	if(capacidad != null){
		        	OrganizacionCapacidadDemanda demanda = null;
		        	
		        	if(organizacion.getDemandas() != null){
		        		for(OrganizacionCapacidadDemanda d : organizacion.getDemandas()){
		        			if(d.getSector().equals(sector) && 
		        			   d.getCapacidad().equals(capacidad)){
		        				demanda = d;
		        				break;
		        			}
		        		}
		        	}
	
		        	if(demanda == null){
		        		demanda = new OrganizacionCapacidadDemanda();
			        	demanda.setOrganizacion(organizacion);
						demanda.setCapacidad(capacidad );
						demanda.setSector(sector);
		        	}
		        	demanda.setDescripcion(description);
		        	
		        	ret.add(demanda);
	        	}else{
	        		if(logger.isTraceEnabled()){
	        			logger.trace("No se ha agregado la Demanda con código de capacidad: "+capacityCode);
	        		}
	        	}
	        }
		}
		
		
		return ret;
	}

}
