package net.neurowork.cenatic.centraldir.model.indicators.impl;

import net.neurowork.cenatic.centraldir.model.satelite.Organizacion;

public class EmpresasPorFormaJuridica extends AbstractOrganizacionPorCampo {

	@Override
	protected String obtenerClave(Organizacion organizacion) {
		if(organizacion == null || organizacion.getEmpleados() == 0)
			return "";
		return String.valueOf(organizacion.getEmpleados());
	}

}
