/*
 * Copyright 2010 CENATIC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package net.neurowork.cenatic.centraldir.model.satelite;

import java.io.Serializable;

/**
 *
 * @author Alejandro Sanchez Acosta <asanchez@neurowork.net> http://www.neurowork.net
 * @since 08/11/2010
 */
public class OrganizacionCapacidadOferta implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7422665198932661909L;
	
	private Integer id;
	private Organizacion organizacion;
	private Capacidad capacidad;
	private Sector sector;
	private Integer recursos;
	private Integer porcentajeFacturacion;
	private Integer puntuacion;
	private String descripcion;	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Organizacion getOrganizacion() {
		return organizacion;
	}
	public void setOrganizacion(Organizacion organizacion) {
		this.organizacion = organizacion;
	}

	public Capacidad getCapacidad() {
		return capacidad;
	}
	public void setCapacidad(Capacidad capacidad) {
		this.capacidad = capacidad;
	}
	
	public String toString(){
		return String.valueOf(id);
	}
	public Sector getSector() {
		return sector;
	}
	public void setSector(Sector sector) {
		this.sector = sector;
	}
	public Integer getRecursos() {
		return recursos;
	}
	public void setRecursos(Integer recursos) {
		this.recursos = recursos;
	}
	public Integer getPorcentajeFacturacion() {
		return porcentajeFacturacion;
	}
	public void setPorcentajeFacturacion(Integer porcentajeFacturacion) {
		this.porcentajeFacturacion = porcentajeFacturacion;
	}
	public Integer getPuntuacion() {
		return puntuacion;
	}
	public void setPuntuacion(Integer puntuacion) {
		this.puntuacion = puntuacion;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
}
