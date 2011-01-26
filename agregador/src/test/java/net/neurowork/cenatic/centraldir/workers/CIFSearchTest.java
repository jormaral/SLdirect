package net.neurowork.cenatic.centraldir.workers;

import net.neurowork.cenatic.centraldir.model.satelite.Organizacion;
import net.neurowork.cenatic.centraldir.service.OrganizacionService;
import net.neurowork.cenatic.centraldir.service.ServiceException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContextTest.xml" })
public class CIFSearchTest {
	private final static Logger logger = LoggerFactory.getLogger(CIFSearchTest.class);
	
	@Autowired
	OrganizacionService organizacionService;
	
	@Test
	public void testSearchCIF() {
		try {
			Organizacion neuro = organizacionService.findByCIF("B85215754");
			logger.info(neuro.toString());
		} catch (ServiceException e) {
		}
		
	}
	
}
