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

import javax.xml.parsers.ParserConfigurationException;

import net.neurowork.cenatic.centraldir.model.Satelite;
import net.neurowork.cenatic.centraldir.model.satelite.Organizacion;
import net.neurowork.cenatic.centraldir.service.OrganizacionService;
import net.neurowork.cenatic.centraldir.service.ProvinciaService;
import net.neurowork.cenatic.centraldir.service.SateliteService;
import net.neurowork.cenatic.centraldir.service.ServiceException;
import net.neurowork.cenatic.centraldir.util.XmlPushCreator;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.xml.sax.SAXException;

/**
 *
 * @author Alejandro Sanchez Acosta <asanchez@neurowork.net> http://www.neurowork.net
 * @since 01/12/2010
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContextTest.xml" })
public class XMLPushTest {
	private final static Logger logger = LoggerFactory.getLogger(XMLPushTest.class);
	
	private static final String USERNAME_PARAM = "username";
	private static final String PASSWORD_PARAM = "password";
	private String user = "alejandrosanchezacosta@gmail.com";
	private String password = "netfilter";

	@Autowired
	ProvinciaService provinciaService;
	@Autowired
	SateliteService sateliteService;
	@Autowired
	OrganizacionService organizacionService;
	
	private Satelite satelite;
	private Organizacion organizacion;
	private RestUrlManager restUrl;
	private HttpClient httpclient;

	@Before
	public void beforeTest(){
		try {
			satelite = sateliteService.findById(3);
			organizacion = organizacionService.findById(1);
		} catch (ServiceException e) {
        	logger.error(e.getMessage());
		}
		restUrl = new RestUrlManager(satelite);
		httpclient = XMLRestWorker.getHttpClient();
	}
	
	@Test
	public void testPushOrganizacion() {
		GetMethod get = new GetMethod(restUrl.getTokenUrl());
		get.addRequestHeader("Accept" , "application/xml"); 

		NameValuePair userParam = new NameValuePair(USERNAME_PARAM, user);
		NameValuePair passwordParam = new NameValuePair(PASSWORD_PARAM, password);
		NameValuePair[] params = new NameValuePair[]{userParam, passwordParam};
		
		get.setQueryString(params);	
		
        try {
            int result = httpclient.executeMethod(get);
            logger.info("Response status code: " + result);
            logger.info("Response body: ");
            logger.info(get.getResponseBodyAsString());
            
            String token = XMLRestWorker.parseToken(get.getResponseBodyAsString());
            if(token != null){
            	pushOrganization(token);
            }            
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
	
	}
	

	private void pushOrganization(String token) {
		PostMethod post = new PostMethod(restUrl.getPushOrganizationUrl());

		NameValuePair tokenParam = new NameValuePair("token", token);
		NameValuePair[] params = new NameValuePair[]{tokenParam};
		
		post.addRequestHeader("Accept" , "application/xml"); 
		post.setQueryString(params);
		post.setRequestBody(XmlPushCreator.getInstance().getPushXml(organizacion));

        try {
            int result = httpclient.executeMethod(post);
            logger.info("Response status code: " + result);
            logger.info("Response body: ");
            logger.info(post.getResponseBodyAsString());
        } catch (HttpException e) {
        	logger.error(e.getMessage());
		} catch (IOException e) {
			logger.error(e.getMessage());
		} finally {
            post.releaseConnection();
        }
		
	}
}