package net.neurowork.cenatic.centraldir.model.indicators.impl;

import net.neurowork.cenatic.centraldir.model.satelite.Organizacion;

import org.springframework.util.StringUtils;

public class EmpresasPorGrupoEmpresarial extends AbstractOrganizacionPorCampo {
	@Override
	protected String obtenerClave(Organizacion organizacion) {
		if(organizacion == null || !StringUtils.hasLength(organizacion.getGrupoEmpresarial()))
			return "No Tiene";
		return organizacion.getGrupoEmpresarial();
	}
}
