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

import net.neurowork.cenatic.centraldir.model.Satelite;
import net.neurowork.cenatic.centraldir.service.AsociacionService;
import net.neurowork.cenatic.centraldir.service.FormaJuridicaService;
import net.neurowork.cenatic.centraldir.service.OrganizacionSedeService;
import net.neurowork.cenatic.centraldir.service.OrganizacionService;
import net.neurowork.cenatic.centraldir.service.ProvinciaService;
import net.neurowork.cenatic.centraldir.service.SateliteService;
import net.neurowork.cenatic.centraldir.service.SectorService;
import net.neurowork.cenatic.centraldir.service.ServiceException;
import net.neurowork.cenatic.centraldir.workers.xml.OrganizacionXmlImporter;
import net.neurowork.cenatic.centraldir.workers.xml.XmlTokenImporter;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 *
 * @since 30/11/2010
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContextTest.xml" })
public class SateliteTest {

	private final static Logger logger = LoggerFactory.getLogger(SateliteTest.class);
	
	@Autowired
	private ProvinciaService provinciaService;
	@Autowired
	private SateliteService sateliteService;
	@Autowired
	private OrganizacionService organizacionService;
	@Autowired
	private FormaJuridicaService formaJuridicaService;
	@Autowired
	private OrganizacionSedeService organizacionSedeService;
	@Autowired
	private SectorService sectorService;	
	@Autowired
	private AsociacionService asociacionService;	
	
	private Satelite satelite;
	private RestUrlManager restUrl;
	private XmlTokenImporter tokenImporter;
	private OrganizacionXmlImporter orgImporter;
	
	@Before
	public void beforeTest(){
		try {
			satelite = sateliteService.findById(1);
		} catch (ServiceException e) {
        	logger.error(e.getMessage());
		}
		restUrl = new RestUrlManager(satelite);
		tokenImporter = new XmlTokenImporter(satelite, restUrl);		
		orgImporter = new OrganizacionXmlImporter(satelite, 
				restUrl,
				15,
				organizacionService,
				sateliteService, 
				provinciaService, 
				formaJuridicaService, 
				organizacionSedeService, 
				asociacionService, 
				sectorService );
	}

	@Test
	public void testSatelite() {
		if(logger.isInfoEnabled())
			logger.info("Stating XMLPush Worker for Satelite: " + satelite);
		if(logger.isTraceEnabled())
			logger.trace("Verifiying Connection");

		String token = tokenImporter.getXmlToken();
		if(token != null){
			logger.info("Token: " + token);
			orgImporter.buscarOrganizacion(token);
		}
		logger.info("Ending XML Push Worker for Satelite: " + satelite);
	}

}
