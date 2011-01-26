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
package net.neurowork.cenatic.centraldir.web.organizations;


/**
 *
 * @author Alejandro Sanchez Acosta <asanchez@neurowork.net> http://www.neurowork.net
 * @since 28/12/2010
 */
public class DemandaSearchResult {
	private Integer id;
	private String nombre;
	private String capacidadMatch;
	private String sectorMatch;
	private String direccion;
	private String provincia;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public void setCapacidadMatch(String capacidadMatch) {
		this.capacidadMatch = capacidadMatch;
	}
	public String getCapacidadMatch() {
		return capacidadMatch;
	}
	public String getSectorMatch() {
		return sectorMatch;
	}
	public void setSectorMatch(String sectorMatch) {
		this.sectorMatch = sectorMatch;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public String getProvincia() {
		return provincia;
	}
	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}
	
	public String toString(){
		return nombre;
	}
}
