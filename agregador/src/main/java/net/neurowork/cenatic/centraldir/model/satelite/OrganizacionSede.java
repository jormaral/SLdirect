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

import net.neurowork.cenatic.centraldir.model.BaseEntity;

/**
 *
 * @author Alejandro Sanchez Acosta <asanchez@neurowork.net> http://www.neurowork.net
 * @since 08/11/2010
 */
public class OrganizacionSede extends BaseEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4271786645225684590L;
	
	private Organizacion organizacion;
	private float latitud;
	private float longitud;
	private String direccion;
	private String localidad;
	private String telefonoContacto;
	private String personaContacto;
	private String mailContacto;
	private Provincia provincia;
	private Integer codigoPostal;
	private Integer hombres;
	private Integer mujeres;
		
	public Organizacion getOrganizacion() {
		return organizacion;
	}
	public void setOrganizacion(Organizacion organizacion) {
		this.organizacion = organizacion;
	}
	
	public float getLatitud() {
		return latitud;
	}
	public void setLatitud(float latitud) {
		this.latitud = latitud;
	}
	public float getLongitud() {
		return longitud;
	}
	public void setLongitud(float longitud) {
		this.longitud = longitud;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public String getLocalidad() {
		return localidad;
	}
	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}
	public String getTelefonoContacto() {
		return telefonoContacto;
	}
	public void setTelefonoContacto(String telefonoContacto) {
		this.telefonoContacto = telefonoContacto;
	}
	public String getPersonaContacto() {
		return personaContacto;
	}
	public void setPersonaContacto(String personaContacto) {
		this.personaContacto = personaContacto;
	}
	public String getMailContacto() {
		return mailContacto;
	}
	public void setMailContacto(String mailContacto) {
		this.mailContacto = mailContacto;
	}
	public Provincia getProvincia() {
		return provincia;
	}
	public void setProvincia(Provincia provincia) {
		this.provincia = provincia;
	}
	public Integer getCodigoPostal() {
		return codigoPostal;
	}
	public void setCodigoPostal(Integer codigoPostal) {
		this.codigoPostal = codigoPostal;
	}
	public Integer getHombres() {
		return hombres;
	}
	public void setHombres(Integer hombres) {
		this.hombres = hombres;
	}
	public Integer getMujeres() {
		return mujeres;
	}
	public void setMujeres(Integer mujeres) {
		this.mujeres = mujeres;
	}
	
	public String toString(){
		return "sede#"+ String.valueOf(this.getId());
	}
}
