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
public class OrganizacionAsociacion implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4271786645225684590L;
	
	private Integer id;
	private Organizacion organizacion;
	private Asociacion asociacion;
	
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
	public Asociacion getAsociacion() {
		return asociacion;
	}
	public void setAsociacion(Asociacion asociacion) {
		this.asociacion = asociacion;
	}
	
	public String toString(){
		return String.valueOf(id);
	}
}
