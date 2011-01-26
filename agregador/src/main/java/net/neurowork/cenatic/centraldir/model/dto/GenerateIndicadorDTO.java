/**
 * 
 */
package net.neurowork.cenatic.centraldir.model.dto;

import net.neurowork.cenatic.centraldir.model.indicators.Indicator;
import net.neurowork.cenatic.centraldir.model.satelite.Provincia;
import net.neurowork.cenatic.centraldir.model.satelite.Sector;

/**
 * Almacen los datos necesarios para generar Indicadores
 */
public class GenerateIndicadorDTO {
	private Provincia provincia;
	private Indicator [] indicadores;
	private boolean pdf;
	private Sector sector;
	private String servicio;

	public Provincia getProvincia() {
		return provincia;
	}

	public void setProvincia(Provincia provincia) {
		this.provincia = provincia;
	}

	public Indicator[] getIndicadores() {
		return indicadores;
	}

	public void setIndicadores(Indicator[] indicadores) {
		this.indicadores = indicadores;
	}

	public boolean isPdf() {
		return pdf;
	}

	public void setPdf(boolean pdf) {
		this.pdf = pdf;
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
