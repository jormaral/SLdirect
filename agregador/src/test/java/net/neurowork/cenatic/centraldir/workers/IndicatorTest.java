package net.neurowork.cenatic.centraldir.workers;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;
import net.neurowork.cenatic.centraldir.model.graphs.GraphChartCreator;
import net.neurowork.cenatic.centraldir.model.indicators.Indicator;
import net.neurowork.cenatic.centraldir.service.IndicadorService;
import net.neurowork.cenatic.centraldir.service.ServiceException;

import org.jfree.chart.JFreeChart;
import org.jfree.data.general.Dataset;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContextTest.xml" })
public class IndicatorTest {
	private final static Logger logger = LoggerFactory.getLogger(IndicatorTest.class);

	@Autowired
	private IndicadorService indicatorService;
	
	private Indicator indicador;
	
	
	@Before
	public void beforeTest(){
		try {
			indicador = indicatorService.loadIndicator(14);
		} catch (ServiceException e) {
        	logger.error(e.getMessage());
		}
	}

	@Test
	public void testIndicator() {
		logger.info(indicador.getName());	
		logger.info(indicador.getAgruparPor().getColumnName());			
		
		Dataset dataset;
		try {
			dataset = indicatorService.buildGraphDataset(indicador);
			String clazz = indicador.getTipoGrafico().getCreator();
			
			try {
				GraphChartCreator creator = (GraphChartCreator)Class.forName(clazz).newInstance();
				JFreeChart chart = creator.createGraphChart(indicador.getName(),
						indicador.getTipoIndicador().getName(),
						dataset);
				
				assertNotNull(chart);
			} catch (InstantiationException e) {
				logger.error(e.getMessage());
			} catch (IllegalAccessException e) {
				logger.error(e.getMessage());
			} catch (ClassNotFoundException e) {
				logger.error(e.getMessage());
			}		
		} catch (ServiceException e) {
			logger.error(e.getMessage());
			fail();
		}
		
	}
	
}
