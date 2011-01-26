/**
 * 
 */
package net.neurowork.cenatic.centraldir.model.indicators.impl;

import net.neurowork.cenatic.centraldir.model.satelite.Organizacion;
import net.neurowork.cenatic.centraldir.model.satelite.OrganizacionAsociacion;

/**
 *
 */
public class EmpresasPorAsociaciones extends AbstractOrganizacionPorCampoMultiClave {

	@Override
	protected String[] obtenerClaves(Organizacion organizacion) {
		String[] ret = null; 
		if(organizacion == null || 
			organizacion.getOrganizacionAsociacions() == null || 
			organizacion.getOrganizacionAsociacions().size() == 0){
			ret = new String[1];
			ret[0] = "No Tiene";
		}else{
			ret = new String[organizacion.getOrganizacionAsociacions().size()];
			int i=0;
			for(OrganizacionAsociacion oa : organizacion.getOrganizacionAsociacions()){
				ret[i++] = oa.getAsociacion().getName();
			}
		}
		return ret;
	}

}
