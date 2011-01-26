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

import javax.xml.parsers.ParserConfigurationException;

import net.neurowork.cenatic.centraldir.model.Satelite;
import net.neurowork.cenatic.centraldir.workers.RestUrlManager;
import net.neurowork.cenatic.centraldir.workers.XMLRestWorker;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.SAXException;

/**
 *
 * @since 07/01/2011
 */
public class XmlTokenImporter {

	private final static Logger logger = LoggerFactory.getLogger(XmlTokenImporter.class);

	private RestUrlManager restUrl;
	private Satelite satelite;
	
	public XmlTokenImporter(Satelite satelite, RestUrlManager restUrl){
		super();
		this.restUrl = restUrl;
		this.satelite = satelite;
	}
	
	public String getXmlToken() {
		String token = null;
		HttpClient httpclient = XMLRestWorker.getHttpClient(); 
		GetMethod get = new GetMethod(restUrl.getTokenUrl());
		get.addRequestHeader("Accept" , "application/xml"); 

		NameValuePair userParam = new NameValuePair(XMLRestWorker.USERNAME_PARAM, satelite.getUser());
		NameValuePair passwordParam = new NameValuePair(XMLRestWorker.PASSWORD_PARAM, satelite.getPassword());
		NameValuePair[] params = new NameValuePair[]{userParam, passwordParam};
		
		get.setQueryString(params);	
		try {
			int statusCode = httpclient.executeMethod(get);
			if (statusCode != HttpStatus.SC_OK) {
				logger.error("Method failed: " + get.getStatusLine());
			}
            token = XMLRestWorker.parseToken(get.getResponseBodyAsString());
		} catch (HttpException e) {
			logger.error(e.getMessage());
		} catch (IOException e) {
			logger.error(e.getMessage());
		} catch (ParserConfigurationException e) {
			logger.error(e.getMessage());
		} catch (SAXException e) {
			logger.error(e.getMessage());
		}finally {
            get.releaseConnection();
        }		
		return token;
	}
}
