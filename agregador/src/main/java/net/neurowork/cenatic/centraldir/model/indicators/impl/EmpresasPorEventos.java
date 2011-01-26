package net.neurowork.cenatic.centraldir.model.indicators.impl;

import net.neurowork.cenatic.centraldir.model.satelite.Organizacion;

public class EmpresasPorEventos extends AbstractOrganizacionPorCampo {

	@Override
	protected String obtenerClave(Organizacion organizacion) {
		if(organizacion == null || organizacion.getEventos() == null)
			return "0";
		return String.valueOf(organizacion.getEventos().size());
	}

}
