package net.neurowork.cenatic.centraldir.model.indicators.impl;

import org.springframework.util.StringUtils;

import net.neurowork.cenatic.centraldir.model.satelite.Organizacion;

public class EmpresasConParticipacionIMasD extends AbstractOrganizacionPorCampo {
	@Override
	protected String obtenerClave(Organizacion organizacion) {
		if(organizacion == null || !StringUtils.hasLength(organizacion.getParticipacionImasD()))
			return "No Tiene";
		return "Tiene";
	}

}
