package net.neurowork.cenatic.centraldir.util;

import net.neurowork.cenatic.centraldir.model.graphs.GraphChartCreator;
import net.neurowork.cenatic.centraldir.model.indicators.Indicator;

import org.jfree.chart.JFreeChart;
import org.jfree.data.general.Dataset;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class ChartsUtil {
	private final static Logger logger = LoggerFactory.getLogger(ChartsUtil.class);
	
	public static JFreeChart generateChart(Indicator indicador, Dataset dataset){
		String clazz = indicador.getTipoGrafico().getCreator();
		try {
			GraphChartCreator creator = (GraphChartCreator)Class.forName(clazz).newInstance();
			return creator.createGraphChart(indicador.getName(), 
					indicador.getTipoIndicador().getName(),
					dataset);
		} catch (InstantiationException e) {
			logger.error(e.getMessage());
		} catch (IllegalAccessException e) {
			logger.error(e.getMessage());
		} catch (ClassNotFoundException e) {
			logger.error(e.getMessage());
		}
		return null;
	}

}
