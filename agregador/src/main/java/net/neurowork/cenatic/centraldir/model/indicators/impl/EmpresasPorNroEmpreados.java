package net.neurowork.cenatic.centraldir.model.indicators.impl;

import net.neurowork.cenatic.centraldir.model.satelite.Organizacion;

public class EmpresasPorNroEmpreados extends AbstractOrganizacionPorCampo {

	@Override
	protected String obtenerClave(Organizacion organizacion) {
		return String.valueOf(organizacion.getEmpleados());
	}

}
