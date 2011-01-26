package net.neurowork.cenatic.centraldir.model.indicators.impl;

import net.neurowork.cenatic.centraldir.model.satelite.Organizacion;

public class EmpresasConAsociaciones extends AbstractOrganizacionPorCampo {
	@Override
	protected String obtenerClave(Organizacion organizacion) {
		if(organizacion == null || organizacion.getOrganizacionAsociacions()==null)
			return "No Tiene";
		return organizacion.getOrganizacionAsociacions().size() > 0 ? "Tiene" : "No Tiene";
	}
}