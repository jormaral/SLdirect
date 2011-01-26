/**
 * 
 */
package net.neurowork.cenatic.centraldir.model.indicators.impl;

import net.neurowork.cenatic.centraldir.model.satelite.Organizacion;

/**
 *
 */
public class EmpresasPorProvincia extends AbstractOrganizacionPorCampo {

	@Override
	protected String obtenerClave(Organizacion organizacion) {
		if(organizacion == null || organizacion.getProvincia() == null)
			return "";
		return organizacion.getProvincia().getName();
	}

	
}
