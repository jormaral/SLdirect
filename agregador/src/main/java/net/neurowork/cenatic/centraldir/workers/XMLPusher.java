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
package net.neurowork.cenatic.centraldir.workers;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import net.neurowork.cenatic.centraldir.model.Satelite;
import net.neurowork.cenatic.centraldir.model.satelite.Organizacion;
import net.neurowork.cenatic.centraldir.model.satelite.OrganizacionSatelite;
import net.neurowork.cenatic.centraldir.util.XmlPushCreator;
import net.neurowork.cenatic.centraldir.workers.xml.XmlTokenImporter;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @since 11/01/2011
 */
public class XMLPusher {
	private final static Logger logger = LoggerFactory.getLogger(XMLPusher.class);

	private Organizacion organizacion;

	public XMLPusher(Organizacion organizacion) {
		super();
		if(organizacion == null)
			throw new IllegalArgumentException("organizacion = null");
		this.organizacion = organizacion;
	}
	
	public void push(){
		if(logger.isTraceEnabled())
			logger.trace("Starting Xml Push over: " + organizacion);
		
		for(OrganizacionSatelite orgSat : organizacion.getSatelites()){
			Satelite satelite = orgSat.getSatelite();
			if(satelite != null){
				if(logger.isTraceEnabled())
					logger.trace("Pushing " + organizacion + " to satelite: " + satelite);
				
				RestUrlManager restUrl = new RestUrlManager(satelite);
				XmlTokenImporter tokenImporter = new XmlTokenImporter(satelite, restUrl);
				
				String token = tokenImporter.getXmlToken();
				if(token != null){
					logger.info("Token: " + token);
					pushOrganization(restUrl, token);
				}
			}
		}
	}
	
	private void pushOrganization(RestUrlManager restUrl, String token) {
		HttpClient httpclient = XMLRestWorker.getHttpClient(); 
		PostMethod post = new PostMethod(restUrl.getPushOrganizationUrl());

		NameValuePair tokenParam = new NameValuePair("token", token);
		NameValuePair[] params = new NameValuePair[]{tokenParam};
		
		post.addRequestHeader("Accept" , "application/xml"); 
		post.setQueryString(params);
		
		String content = XmlPushCreator.getInstance().getPushXml(organizacion);
		try {
			RequestEntity entity = new StringRequestEntity(content, PostMethod.FORM_URL_ENCODED_CONTENT_TYPE, null);
			post.setRequestEntity(entity);
			
            int result = httpclient.executeMethod(post);
            
            logger.info("Response status code: " + result);
            logger.info("Response body: ");
            logger.info(post.getResponseBodyAsString());

		} catch (UnsupportedEncodingException e) {
        	logger.error(e.getMessage());
		} catch (HttpException e) {
        	logger.error(e.getMessage());
		} catch (IOException e) {
			logger.error(e.getMessage());
		} finally {
            post.releaseConnection();
        }
		
	}
	
}
