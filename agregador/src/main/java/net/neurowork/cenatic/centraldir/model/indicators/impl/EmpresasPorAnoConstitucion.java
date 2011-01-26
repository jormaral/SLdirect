package net.neurowork.cenatic.centraldir.model.indicators.impl;

import net.neurowork.cenatic.centraldir.model.satelite.Organizacion;

public class EmpresasPorAnoConstitucion extends AbstractOrganizacionPorCampo {

	@Override
	protected String obtenerClave(Organizacion organizacion) {
		if(organizacion == null || organizacion.getAnoConstitucion() == null)
			return "";		
		if(organizacion.getAnoConstitucion() <= 0)
			return "";		
		return organizacion.getAnoConstitucion().toString();
	}

}
