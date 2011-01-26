package net.neurowork.cenatic.centraldir.model.indicators.impl;

import java.util.Collection;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import net.neurowork.cenatic.centraldir.model.graphs.datasets.filters.OrganizacionFilter;
import net.neurowork.cenatic.centraldir.model.satelite.Organizacion;
import net.neurowork.cenatic.centraldir.service.OrganizacionService;
import net.neurowork.cenatic.centraldir.service.ServiceException;

public abstract class AbstractOrganizacionPorCampoMultiClave extends AbstractDatasetGenerator {

	@Override
	public Map<String, ? extends Number> generateDataset(OrganizacionFilter filter) throws ServiceException {
		if(serviceMap == null)
			throw new ServiceException("Invalid Service: " + OrganizacionService.SERVICE_NAME);

		OrganizacionService organizacionService = (OrganizacionService) serviceMap.get(OrganizacionService.SERVICE_NAME);
		if(organizacionService == null)
			throw new ServiceException("Invalid Service: " + OrganizacionService.SERVICE_NAME);
		
		SortedMap<String, Integer> ret = new TreeMap<String, Integer>();
		/*
		 * Aplicar el Filtro seleccionado
		 */
		Collection<Organizacion> organizaciones = filter.filter(organizacionService.getOrganizaciones());
		
		String nullString = "";
		for(Organizacion organizacion : organizaciones){
			String[] keys = obtenerClaves(organizacion);
			for(String key : keys){
				Integer val = null;
				if(key == null){
					key = nullString;
				}
				val = ret.get(key);
				
				if(val == null){
					val = Integer.valueOf(1);
				}else{
					val = Integer.valueOf(val.intValue()+1);
				}
				ret.put(key, val);		
			}
		}		
		return ret;
	}
	protected abstract String[] obtenerClaves(Organizacion organizacion);
}
