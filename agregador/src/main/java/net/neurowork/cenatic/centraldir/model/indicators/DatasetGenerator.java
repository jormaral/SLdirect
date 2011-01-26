/**
 * 
 */
package net.neurowork.cenatic.centraldir.model.indicators;

import java.util.Map;

import net.neurowork.cenatic.centraldir.model.graphs.datasets.filters.OrganizacionFilter;
import net.neurowork.cenatic.centraldir.service.CenaticService;
import net.neurowork.cenatic.centraldir.service.ServiceException;

/**
 * @author jorge
 *
 */
public interface DatasetGenerator {
	void setServiceMap(Map<String, CenaticService> serviceMap);
	
	Map<String, ? extends Number> generateDataset(OrganizacionFilter filter) throws ServiceException;
}
