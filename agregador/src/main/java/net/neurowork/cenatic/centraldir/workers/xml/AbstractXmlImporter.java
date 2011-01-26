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

import net.neurowork.cenatic.centraldir.model.BaseEntity;
import net.neurowork.cenatic.centraldir.service.ServiceException;
import net.neurowork.cenatic.centraldir.workers.RestUrlManager;
import net.neurowork.cenatic.centraldir.workers.XMLRestWorker;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.GetMethod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.xml.sax.SAXException;

/**
 *
 * @since 07/01/2011
 */
public abstract class AbstractXmlImporter<T extends BaseEntity> implements XmlImportConstants{
	private final static Logger logger = LoggerFactory.getLogger(AbstractXmlImporter.class);

	protected RestUrlManager restUrl;
	
	public AbstractXmlImporter(RestUrlManager restUrl){
		super();
		this.restUrl = restUrl;
	}
	
	public static Integer getId(String id){
		Integer retId = null;
		try {
			retId = Integer.parseInt(id);
		} catch (NumberFormatException e) {
			logger.error(e.getMessage());
			retId = 0;
		}
		return retId;
	}
	
	public T findBaseEntity(String idValue, String name) {
		if(!StringUtils.hasLength(idValue))
			return null;

		Integer id = getId(idValue);
		T ret = null;
		List<T> l = null;
		try {
			l = findByName(name);
		} catch (ServiceException e) {
			logger.error(e.getMessage());
		}
		if(l != null && l.size() > 0){
			ret = l.get(0);
		}else{
			ret = findRestEntity(id);
		}
		return ret;
	}	
	
	protected T findRestEntity(Object id) {
        HttpClient httpclient = XMLRestWorker.getHttpClient();
        GetMethod get = new GetMethod(getRestUrl());
		get.addRequestHeader("Accept" , "application/xml"); 
		T ret = null;
        try {
			int result = httpclient.executeMethod(get);
            logger.info("Response status code: " + result);
            String xmlString = get.getResponseBodyAsString(); 
            logger.info("Response body: " + xmlString);
            
           	ret = importEntity(xmlString, id);
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

	protected abstract String getRestUrl();

	protected abstract T importEntity(String xmlString, Object id) throws ParserConfigurationException, SAXException, IOException;	
	protected abstract List<T> findByName(String name) throws ServiceException;
}
