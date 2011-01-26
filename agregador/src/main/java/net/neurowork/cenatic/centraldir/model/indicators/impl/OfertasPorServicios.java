package net.neurowork.cenatic.centraldir.model.indicators.impl;

import net.neurowork.cenatic.centraldir.model.satelite.Organizacion;
import net.neurowork.cenatic.centraldir.model.satelite.OrganizacionCapacidadOferta;

public class OfertasPorServicios extends AbstractOrganizacionPorCampoMultiClave {

	@Override
	protected String[] obtenerClaves(Organizacion organizacion) {
		String[] ret = null; 
		if(organizacion == null || 
			organizacion.getOfertas() == null || 
			organizacion.getOfertas().size() == 0){
			ret = new String[1];
			ret[0] = "No Tiene";
		}else{
			ret = new String[organizacion.getOfertas().size()];
			int i=0;
			for(OrganizacionCapacidadOferta oa : organizacion.getOfertas()){
				ret[i++] = oa.getCapacidad().getName();
			}
		}
		return ret;
	}
	
}
