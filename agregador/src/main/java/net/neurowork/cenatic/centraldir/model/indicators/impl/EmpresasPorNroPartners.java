package net.neurowork.cenatic.centraldir.model.indicators.impl;

import org.springframework.util.StringUtils;

import net.neurowork.cenatic.centraldir.model.satelite.Organizacion;

public class EmpresasPorNroPartners extends AbstractOrganizacionPorCampo {

	@Override
	protected String obtenerClave(Organizacion organizacion) {
		if(organizacion == null || !StringUtils.hasLength(organizacion.getPartners()))
			return "No Tiene";
		
		String partnets[] = organizacion.getPartners().split(", ");
		return String.valueOf(partnets.length);
	}

}
