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

import net.neurowork.cenatic.centraldir.model.satelite.Organizacion;
import net.neurowork.cenatic.centraldir.model.satelite.OrganizacionSede;
import net.neurowork.cenatic.centraldir.model.satelite.Provincia;
import net.neurowork.cenatic.centraldir.service.OrganizacionSedeService;
import net.neurowork.cenatic.centraldir.service.ServiceException;
import net.neurowork.cenatic.centraldir.util.XmlParserUtil;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @since 07/01/2011
 */
public class SedeXmlImporter implements XmlImportConstants {
	private ProvinciaXmlImporter provinciaImporter;
	private OrganizacionSedeService organizacionSedeService;

	public SedeXmlImporter(ProvinciaXmlImporter provinciaImporter, OrganizacionSedeService organizacionSedeService){
		super();
		this.provinciaImporter = provinciaImporter;
		this.organizacionSedeService = organizacionSedeService;
	}
	
	public Set<OrganizacionSede> buscarSedes(Element degationsEl, Organizacion organizacion) {
		if(degationsEl == null)
			return null;

		Set<OrganizacionSede> ret = new HashSet<OrganizacionSede>();
		
		NodeList nodeLst = degationsEl.getElementsByTagName("delegation");
		
		for (int s = 0; s < nodeLst.getLength(); s++) {
		    Node fstNode = nodeLst.item(s);
	        if (fstNode.getNodeType() == Node.ELEMENT_NODE) {
	        	Element elSede = (Element)fstNode;

      	
	        	String direccion = XmlParserUtil.getStringNodeValue(elSede, "street");
	        	String localidad = XmlParserUtil.getStringNodeValue(elSede, "locality");
	        	Element provEl = XmlParserUtil.getChildren(elSede, "province");
	        	String provId = XmlParserUtil.getAttribute(provEl, "code");	        		
        		String provincia = XmlParserUtil.getStringNodeValue(elSede, "province");        	
	        	Integer codigoPostal = XmlParserUtil.getIntegerNodeValue(elSede, "postalCode");	
				String telefonoContacto = XmlParserUtil.getStringNodeValue(elSede, "contactPhone");
				String mailContacto = XmlParserUtil.getStringNodeValue(elSede, "contactMail");
				String personaContacto = XmlParserUtil.getStringNodeValue(elSede, "contactPerson");
				float lat = XmlParserUtil.getFloatNodeValue(elSede, "locationLatitude");
				float lon = XmlParserUtil.getFloatNodeValue(elSede, "locationLongitude");
				Integer hombres = XmlParserUtil.getIntegerNodeValue(elSede, "numberOfMen");
				Integer mujeres = XmlParserUtil.getIntegerNodeValue(elSede, "numberOfWomen");
				Provincia prov = provinciaImporter.findProvincia(provId, provincia); 
				
	        	OrganizacionSede sede = null;
				try {
					sede = organizacionSedeService.findSedeByAddressLocalityProvince(direccion, localidad, prov);
				} catch (ServiceException e) {
				}
	        	if(sede == null)
		        	sede = new OrganizacionSede();
				
				sede.setDireccion(direccion);
				sede.setLocalidad(localidad);
				sede.setProvincia(prov);
				sede.setCodigoPostal(codigoPostal);
				sede.setTelefonoContacto(telefonoContacto);
				sede.setMailContacto(mailContacto);
				sede.setPersonaContacto(personaContacto);
				sede.setLatitud(lat);
				sede.setLongitud(lon);
				sede.setHombres(hombres);
				sede.setMujeres(mujeres);
				sede.setOrganizacion(organizacion);
	        	
	        	ret.add(sede);	        	
	        }
		}		
		return ret;
	}

}
