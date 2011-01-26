/**
 * 
 */
package net.neurowork.cenatic.centraldir.model.indicators.impl;

import java.util.Map;

import net.neurowork.cenatic.centraldir.model.indicators.DatasetGenerator;
import net.neurowork.cenatic.centraldir.service.CenaticService;

/**
 * @author jorge
 *
 */
public abstract class AbstractDatasetGenerator implements DatasetGenerator{
	/**
	 * Service Map 
	 */
	protected Map<String, CenaticService> serviceMap;
	
	@Override
	public void setServiceMap(Map<String, CenaticService> serviceMap) {
		this.serviceMap = serviceMap;
	}

}
