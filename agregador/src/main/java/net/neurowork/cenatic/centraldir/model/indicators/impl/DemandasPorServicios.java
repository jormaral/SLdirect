package net.neurowork.cenatic.centraldir.model.indicators.impl;

import net.neurowork.cenatic.centraldir.model.satelite.Organizacion;
import net.neurowork.cenatic.centraldir.model.satelite.OrganizacionCapacidadDemanda;

public class DemandasPorServicios extends AbstractOrganizacionPorCampoMultiClave {

	@Override
	protected String[] obtenerClaves(Organizacion organizacion) {
		String[] ret = null; 
		if(organizacion == null || 
			organizacion.getDemandas() == null || 
			organizacion.getDemandas().size() == 0){
			ret = new String[1];
			ret[0] = "No Tiene";
		}else{
			ret = new String[organizacion.getDemandas().size()];
			int i=0;
			for(OrganizacionCapacidadDemanda oa : organizacion.getDemandas()){
				ret[i++] = oa.getCapacidad().getName();
			}
		}
		return ret;
	}


}
