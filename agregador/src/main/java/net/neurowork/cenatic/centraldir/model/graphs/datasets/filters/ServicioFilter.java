/**
 * 
 */
package net.neurowork.cenatic.centraldir.model.graphs.datasets.filters;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import net.neurowork.cenatic.centraldir.model.satelite.Organizacion;
import net.neurowork.cenatic.centraldir.model.satelite.OrganizacionCapacidadOferta;
import net.neurowork.cenatic.centraldir.model.satelite.Provincia;
import net.neurowork.cenatic.centraldir.model.satelite.Sector;

/**
 * @author jorge
 *
 */
public class ServicioFilter implements OrganizacionFilter {
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
		if(organizacion.getOfertas() == null)
			return false;		
		for(OrganizacionCapacidadOferta oferta : organizacion.getOfertas()){
			if(sector.equals(oferta.getSector()))
				return true;
		}		
		return false;
	}


	private boolean buscarServicio(Organizacion organizacion) {
		if(organizacion.getOfertas() == null)
			return false;		
		for(OrganizacionCapacidadOferta oferta : organizacion.getOfertas()){
			if(servicio.equals(oferta.getCapacidad()))
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
