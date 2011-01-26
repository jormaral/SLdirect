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

import java.io.IOException;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.xml.parsers.ParserConfigurationException;

import net.neurowork.cenatic.centraldir.model.satelite.Asociacion;
import net.neurowork.cenatic.centraldir.model.satelite.Organizacion;
import net.neurowork.cenatic.centraldir.model.satelite.OrganizacionAsociacion;
import net.neurowork.cenatic.centraldir.service.AsociacionService;
import net.neurowork.cenatic.centraldir.service.ServiceException;
import net.neurowork.cenatic.centraldir.util.XmlParserUtil;
import net.neurowork.cenatic.centraldir.workers.RestUrlManager;
import net.neurowork.cenatic.centraldir.workers.XMLRestWorker;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.GetMethod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @since 07/01/2011
 */
public class AsociacionXmlImporter implements XmlImportConstants {
	private final static Logger logger = LoggerFactory.getLogger(AsociacionXmlImporter.class);
	private RestUrlManager restUrl;
	private AsociacionService asociacionService;
	
	public AsociacionXmlImporter(RestUrlManager restUrl, AsociacionService asociacionService){
		super();
		this.restUrl = restUrl;
		this.asociacionService = asociacionService;
	}
	
	public Set<OrganizacionAsociacion> buscarAsociaciones(Element associationsEl, Organizacion organizacion) {
		if(associationsEl == null)
			return null;
		Set<OrganizacionAsociacion> ret = new HashSet<OrganizacionAsociacion>();

		NodeList nodeLst = associationsEl.getElementsByTagName("associationMembership");
		
		for (int s = 0; s < nodeLst.getLength(); s++) {
		    Node fstNode = nodeLst.item(s);
	        if (fstNode.getNodeType() == Node.ELEMENT_NODE) {
	        	Element elDemand = (Element)fstNode;
	        
	        	String code = XmlParserUtil.getAttribute(elDemand, "code");
	        	String url = XmlParserUtil.getAttribute(elDemand, "url");
	        	String icon = XmlParserUtil.getAttribute(elDemand, "icon");

	        	Asociacion asoc = buscarAsociacion(Integer.parseInt(code));
	        	boolean found = false;
	        	if(organizacion.getOrganizacionAsociacions() != null){
		        	for(OrganizacionAsociacion orgAsoc :  organizacion.getOrganizacionAsociacions()){
		        		if(orgAsoc.getAsociacion().getName().equalsIgnoreCase(asoc.getName())){
		        			found = true;
		        			break;
		        		}
		        	}
	        	}
	        	if(!found){
	        		asoc.setUrl(url);
	        		asoc.setIcon(icon);
	        		try {
						asociacionService.save(asoc);
			        	OrganizacionAsociacion asociacion = new OrganizacionAsociacion();
			        	asociacion.setOrganizacion(organizacion);
						asociacion.setAsociacion(asoc);
			        	ret.add(asociacion);
					} catch (ServiceException e) {
						logger.error(e.getMessage());
					}
	        	}
	        }
		}
		
		
		return ret;
	}
	
	private Asociacion buscarAsociacion(int code) {
		HttpClient httpclient = XMLRestWorker.getHttpClient();		
        GetMethod get = new GetMethod(restUrl.getAssociationUrl());
		get.addRequestHeader("Accept" , "application/xml"); 
		Asociacion ret = null;
        try {
            int result = httpclient.executeMethod(get);
            logger.info("Response status code: " + result);
            String xmlString = get.getResponseBodyAsString(); 
            logger.info("Response body: " + xmlString);
            
           	ret = importAsociacion(xmlString, code);
        } catch (HttpException e) {
        	logger.error(e.getMessage());
		} catch (IOException e) {
			logger.error(e.getMessage());
		} catch (ParserConfigurationException e) {
			logger.error(e.getMessage());
		} catch (SAXException e) {
			logger.error(e.getMessage());
		} finally {
            get.releaseConnection();
        }
		return ret;
	}

	private Asociacion importAsociacion(String xmlString, Integer associationCode) throws ParserConfigurationException, SAXException, IOException {
		Document doc = XmlParserUtil.createDocumentFromString(xmlString);
		Asociacion ret = null;
		NodeList nodeLst = doc.getElementsByTagName("association");
		
		for (int s = 0; s < nodeLst.getLength(); s++) {
		    Node fstNode = nodeLst.item(s);
	        if (fstNode.getNodeType() == Node.ELEMENT_NODE) {
	        	Element elPDU = (Element)fstNode;
	        	String code = XmlParserUtil.getAttribute(elPDU, "code");
	        	String url = XmlParserUtil.getAttribute(elPDU, "url");
	        	String icon = XmlParserUtil.getAttribute(elPDU, "icon");
	    		NodeList fstNm = elPDU.getChildNodes();
	    		String associationName = null;
	    		
	    		if(fstNm.getLength() > 0){
	    			associationName = ((Node) fstNm.item(0)).getNodeValue();
	    			
		        	Integer capId = AbstractXmlImporter.getId(code);
		        	Asociacion association = null;
		        	try {
		        		Collection<Asociacion> associations = asociacionService.findByName(associationName);
		        		
		        		if(associations != null && associations.size() > 0){
		        			association = associations.iterator().next(); 
		        		}else{
		        			association = new Asociacion();
		        			association.setName(associationName);
		        			association.setUrl(url);
		        			association.setIcon(icon);
		        			logger.info("Saving Asociacion: " + associationName + " url: " + url + " icon " + icon);
		        			asociacionService.save(association);
		        		}
		        		
			        	if(capId != null && capId.equals(associationCode)){
			        		ret = association;
			        	}	        	
					} catch (ServiceException e) {
						logger.error(e.getMessage());
					}
	    		}
	        }
		}
		return ret;
	}

}
