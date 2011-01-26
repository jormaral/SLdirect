package net.neurowork.cenatic.centraldir.model.graphs.datasets.filters;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import net.neurowork.cenatic.centraldir.model.satelite.Organizacion;
import net.neurowork.cenatic.centraldir.model.satelite.Provincia;

public class ProvinciaFilter implements OrganizacionFilter {
	private Provincia provincia;
	
	public ProvinciaFilter(Provincia provincia) {
		super();
		this.provincia = provincia;
	}

	@Override
	public Collection<Organizacion> filter(Collection<Organizacion> organizacions) {
		List<Organizacion> ret = new ArrayList<Organizacion>();
		for(Organizacion organizacion : organizacions){
			if(provincia.equals(organizacion.getProvincia())){
				ret.add(organizacion);
			}
		}		
		return ret;
	}

	public Provincia getProvincia() {
		return provincia;
	}

	public void setProvincia(Provincia provincia) {
		this.provincia = provincia;
	}

	public String toString(){
		return "Filter Provincia: " + provincia;
	}
	
}
