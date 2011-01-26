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
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import net.neurowork.cenatic.centraldir.model.satelite.Capacidad;
import net.neurowork.cenatic.centraldir.service.OrganizacionService;
import net.neurowork.cenatic.centraldir.service.ServiceException;
import net.neurowork.cenatic.centraldir.util.XmlParserUtil;
import net.neurowork.cenatic.centraldir.workers.RestUrlManager;
import net.neurowork.cenatic.centraldir.workers.XMLRestWorker;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.GetMethod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author Alejandro Sanchez Acosta <asanchez@neurowork.net> http://www.neurowork.net
 * @since 07/01/2011
 */
public class CapacidadXmlImporter implements XmlImportConstants {
	private final static Logger logger = LoggerFactory.getLogger(CapacidadXmlImporter.class);
	private RestUrlManager restUrl;
	private OrganizacionService organizacionService;
	
	public CapacidadXmlImporter(RestUrlManager restUrl,
			OrganizacionService organizacionService) {
		super();
		this.restUrl = restUrl;
		this.organizacionService = organizacionService;
	}

	public Capacidad buscarCapacidad(int capacityCode) {
		HttpClient httpclient = XMLRestWorker.getHttpClient();
        GetMethod get = new GetMethod(restUrl.getCapacidadUrl());
		get.addRequestHeader("Accept" , "application/xml"); 
		Capacidad ret = null;
        try {
            int result = httpclient.executeMethod(get);
            if(logger.isTraceEnabled())
            	logger.trace("Response status code: " + result);
            String xmlString = get.getResponseBodyAsString(); 
            if(logger.isTraceEnabled())
            	logger.trace("Response body: " + xmlString);
            
           	ret = importCapacidad(xmlString, capacityCode);
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
	
	
	private Capacidad importCapacidad(String xmlString, Integer capacityId) throws ParserConfigurationException, SAXException, IOException {
		Document doc = XmlParserUtil.createDocumentFromString(xmlString);
		Capacidad ret = null;
		NodeList nodeLst = doc.getElementsByTagName("capacity");
		
		for (int s = 0; s < nodeLst.getLength(); s++) {
		    Node fstNode = nodeLst.item(s);
	        if (fstNode.getNodeType() == Node.ELEMENT_NODE) {
	        	Element elPDU = (Element)fstNode;
	        	String code = XmlParserUtil.getAttribute(elPDU, XML_CODE);
	        	String category = XmlParserUtil.getAttribute(elPDU, "category");
	    		NodeList fstNm = elPDU.getChildNodes();
	    		String capacidadName = null;
	    		
	    		if(fstNm.getLength() > 0){
	    			capacidadName = ((Node) fstNm.item(0)).getNodeValue();
	    			
		        	Integer capId = AbstractXmlImporter.getId(code);
		        	Capacidad capacidad = null;
		        	try {
		        		List<Capacidad> capacidades = organizacionService.findCapacidadByName(capacidadName);
		        		
		        		if(capacidades != null && capacidades.size() > 0){
		        			capacidad = capacidades.get(0); 
		        		}else{
		    	        	capacidad = new Capacidad();
		    	        	capacidad.setName(capacidadName);
		    	        	if(StringUtils.hasLength(category))
		    	        		capacidad.setCategoria(category);
							organizacionService.saveCapacidad(capacidad);
		        		}
		        		
			        	if(capId != null && capId.equals(capacityId)){
			        		ret = capacidad;
			        	}	        	
					} catch (ServiceException e) {
						logger.error(e.getMessage());
					}
	    		}
	        }
		}
		
		if(ret != null){
			if(logger.isTraceEnabled())
				logger.trace("Se devuelve la Capacidad: " + ret);
			return ret;
		}
		if(logger.isTraceEnabled())
			logger.trace("No se ha encontrado la Capacidad con Id: " + capacityId);
		return null;
	}
	
}
