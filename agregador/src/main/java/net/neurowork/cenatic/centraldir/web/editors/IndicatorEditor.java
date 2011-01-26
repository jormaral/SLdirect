package net.neurowork.cenatic.centraldir.web.editors;

import java.beans.PropertyEditorSupport;

import net.neurowork.cenatic.centraldir.model.indicators.Indicator;
import net.neurowork.cenatic.centraldir.service.IndicadorService;
import net.neurowork.cenatic.centraldir.service.ServiceException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class IndicatorEditor extends PropertyEditorSupport {
	private final static Logger logger = LoggerFactory.getLogger(IndicatorEditor.class);

	private IndicadorService indicadorService;
	
	public IndicatorEditor(IndicadorService indicadorService) {
		super();
		this.indicadorService = indicadorService; 
	}

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		try {
			for(Indicator indicator : indicadorService.getIndicators()){
				if(indicator.getName().equals(text)){
					if(logger.isTraceEnabled())
						logger.trace("Indicador: " + text + " setting value: " + indicator);
					setValue(indicator);
					return;
				}
			}
		} catch (ServiceException e) {
			logger.error(e.getMessage());
		}
	}

}
