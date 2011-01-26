package net.neurowork.cenatic.centraldir.model.indicators.impl;

import net.neurowork.cenatic.centraldir.model.satelite.Organizacion;

public class EmpresasConActividadIMasD extends AbstractOrganizacionPorCampo {
	@Override
	protected String obtenerClave(Organizacion organizacion) {
		if(organizacion == null || organizacion.getActividadesImasD() == null)
			return "No Tiene";		
		return organizacion.getActividadesImasD() ? "Tiene" : "No Tiene";
	}

}
