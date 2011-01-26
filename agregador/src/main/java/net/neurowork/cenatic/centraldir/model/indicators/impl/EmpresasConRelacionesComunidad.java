package net.neurowork.cenatic.centraldir.model.indicators.impl;

import net.neurowork.cenatic.centraldir.model.satelite.Organizacion;

import org.springframework.util.StringUtils;

public class EmpresasConRelacionesComunidad extends AbstractOrganizacionPorCampo {
	@Override
	protected String obtenerClave(Organizacion organizacion) {
		if(organizacion == null || !StringUtils.hasLength(organizacion.getRelacionesComunidad()))
			return "No Tiene";
		return "Tiene";
	}
}
