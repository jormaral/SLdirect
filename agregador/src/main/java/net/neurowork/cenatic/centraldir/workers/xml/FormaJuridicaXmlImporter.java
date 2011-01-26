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

import net.neurowork.cenatic.centraldir.model.satelite.FormaJuridica;
import net.neurowork.cenatic.centraldir.service.FormaJuridicaService;
import net.neurowork.cenatic.centraldir.service.ServiceException;
import net.neurowork.cenatic.centraldir.util.XmlParserUtil;
import net.neurowork.cenatic.centraldir.workers.RestUrlManager;

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
public class FormaJuridicaXmlImporter extends AbstractXmlImporter<FormaJuridica> {

	private final static Logger logger = LoggerFactory.getLogger(FormaJuridicaXmlImporter.class);

	private FormaJuridicaService formaJuridicaService;

	public FormaJuridicaXmlImporter(RestUrlManager restUrl, FormaJuridicaService formaJuridicaService) {
		super(restUrl);
		this.formaJuridicaService = formaJuridicaService;
	}
	
	@Override
	protected FormaJuridica importEntity(String xmlString, Object id)
			throws ParserConfigurationException, SAXException, IOException {
		Document doc = XmlParserUtil.createDocumentFromString(xmlString);
		FormaJuridica ret = null;
		NodeList nodeLst = doc.getElementsByTagName(XML_ORGANIZATION_TYPE);
		
		for (int s = 0; s < nodeLst.getLength(); s++) {
		    Node fstNode = nodeLst.item(s);
	        if (fstNode.getNodeType() == Node.ELEMENT_NODE) {
	        	Element elPDU = (Element)fstNode;
	        	String code = XmlParserUtil.getAttribute(elPDU, XML_CODE);
	    		NodeList fstNm = elPDU.getChildNodes();
	    		String organizationType = null;
	    		
	    		if(fstNm.getLength() > 0){
	    			organizationType = ((Node) fstNm.item(0)).getNodeValue();
	    			
		        	Integer orgId = getId(code);
		        	FormaJuridica formaJuridica = null;
		        	try {
		        		List<FormaJuridica> lFJ = formaJuridicaService.findByName(organizationType);
		        		
		        		if(lFJ != null && lFJ.size() > 0){
		        			formaJuridica = lFJ.get(0); 
		        		}else{
		    	        	formaJuridica = new FormaJuridica();
		    	        	formaJuridica.setName(organizationType);
		    	        	formaJuridicaService.save(formaJuridica);
		        		}
		        		
			        	if(orgId != null && orgId.equals(id)){
			        		ret = formaJuridica;
			        	}	        	
					} catch (ServiceException e) {
						logger.error(e.getMessage());
					}
	    		}
	        }
		}
		return ret;
	}

	@Override
	protected List<FormaJuridica> findByName(String name) throws ServiceException {
		return formaJuridicaService.findByName(name);
	}

	@Override
	protected String getRestUrl() {
		return restUrl.getFormaJuridicaUrl();
	}

}
