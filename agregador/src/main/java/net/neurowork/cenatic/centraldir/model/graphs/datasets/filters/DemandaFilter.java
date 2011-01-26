/**
 * 
 */
package net.neurowork.cenatic.centraldir.model.graphs.datasets.filters;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import net.neurowork.cenatic.centraldir.model.satelite.Organizacion;
import net.neurowork.cenatic.centraldir.model.satelite.OrganizacionCapacidadDemanda;
import net.neurowork.cenatic.centraldir.model.satelite.Provincia;
import net.neurowork.cenatic.centraldir.model.satelite.Sector;

/**
 * @author jorge
 *
 */
public class DemandaFilter implements OrganizacionFilter {
	private Provincia provincia;
	private Sector sector;
	private String servicio;
	
	
	@Override
	public Collection<Organizacion> filter(Collection<Organizacion> organizacions) {
		List<Organizacion> ret = new ArrayList<Organizacion>();
		for(Organizacion organizacion : organizacions){
			boolean match = true;			
			if(servicio != null){
				match = buscarServicio(organizacion);
			}
			if(match && sector != null){
				match = buscarSector(organizacion);
			}
			if(match && provincia != null){
				match = (provincia.equals(organizacion.getProvincia()));
			}			
			if(match){
				ret.add(organizacion);
			}			
		}		
		return ret;
	}


	private boolean buscarSector(Organizacion organizacion) {
		if(organizacion.getDemandas() == null)
			return false;		
		for(OrganizacionCapacidadDemanda demanda : organizacion.getDemandas()){
			if(sector.equals(demanda.getSector()))
				return true;
		}		
		return false;
	}


	private boolean buscarServicio(Organizacion organizacion) {
		if(organizacion.getDemandas() == null)
			return false;		
		for(OrganizacionCapacidadDemanda demanda : organizacion.getDemandas()){
			if(servicio.equals(demanda.getCapacidad()))
				return true;
		}		
		return false;
	}


	public Provincia getProvincia() {
		return provincia;
	}


	public void setProvincia(Provincia provincia) {
		this.provincia = provincia;
	}


	public Sector getSector() {
		return sector;
	}


	public void setSector(Sector sector) {
		this.sector = sector;
	}


	public String getServicio() {
		return servicio;
	}


	public void setServicio(String servicio) {
		this.servicio = servicio;
	}

}
