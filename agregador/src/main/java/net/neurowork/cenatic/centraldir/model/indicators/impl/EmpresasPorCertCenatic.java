package net.neurowork.cenatic.centraldir.model.indicators.impl;

import net.neurowork.cenatic.centraldir.model.satelite.Organizacion;

public class EmpresasPorCertCenatic extends AbstractOrganizacionPorCampo {

	@Override
	protected String obtenerClave(Organizacion organizacion) {
		if(organizacion == null || organizacion.getCertificacionCenatic() == null)
			return "No Tiene";		
		return organizacion.getCertificacionCenatic() ? "Tiene" : "No Tiene";
	}

}
