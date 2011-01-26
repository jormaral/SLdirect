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

import javax.xml.parsers.ParserConfigurationException;

import net.neurowork.cenatic.centraldir.model.satelite.Sector;
import net.neurowork.cenatic.centraldir.service.SectorService;
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
 * @author Alejandro Sanchez Acosta <asanchez@neurowork.net> http://www.neurowork.net
 * @since 07/01/2011
 */
public class SectorXmlImporter {
	private final static Logger logger = LoggerFactory.getLogger(SectorXmlImporter.class);
	private RestUrlManager restUrl;
	private SectorService sectorService;
	
	public SectorXmlImporter(RestUrlManager restUrl,
			SectorService sectorService) {
		super();
		this.restUrl = restUrl;
		this.sectorService = sectorService;
	}
	
	
	public Sector buscarSector(int sectorCode) {
		HttpClient httpclient = XMLRestWorker.getHttpClient();
        GetMethod get = new GetMethod(restUrl.getSectorUrl());
		get.addRequestHeader("Accept" , "application/xml"); 
		Sector ret = null;
        try {
            int result = httpclient.executeMethod(get);
            logger.info("Response status code: " + result);
            String xmlString = get.getResponseBodyAsString(); 
            logger.info("Response body: " + xmlString);
            
           	ret = importSector(xmlString, sectorCode);
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

	private Sector importSector(String xmlString, Integer id) throws ParserConfigurationException, SAXException, IOException {
		Document doc = XmlParserUtil.createDocumentFromString(xmlString);
		Sector ret = null;
		NodeList nodeLst = doc.getElementsByTagName("sector");
		
		for (int s = 0; s < nodeLst.getLength(); s++) {
		    Node fstNode = nodeLst.item(s);
	        if (fstNode.getNodeType() == Node.ELEMENT_NODE) {
	        	Element elPDU = (Element)fstNode;
	        	String code = XmlParserUtil.getAttribute(elPDU, "code");
	    		NodeList fstNm = elPDU.getChildNodes();
	    		String sectorName = null;
	    		
	    		if(fstNm.getLength() > 0){
	    			String tmp = ((Node) fstNm.item(0)).getNodeValue();
	    			byte[] utf8 = tmp.getBytes("UTF-8"); 
	    			sectorName = new String(utf8, "UTF-8"); 	    			
	    			
		        	Integer capId = AbstractXmlImporter.getId(code);
		        	Sector sector = null;
		        	try {
		        		Collection<Sector> sectores = sectorService.findByName(sectorName);
		        		
		        		if(sectores != null && sectores.size() > 0){
		        			sector = sectores.iterator().next(); 
		        		}else{
		    	        	sector = new Sector();
		    	        	sector.setName(sectorName);
		    	        	sectorService.save(sector);
		        		}
		        		
			        	if(capId != null && capId.equals(id)){
			        		ret = sector;
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
