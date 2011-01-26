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
import net.neurowork.cenatic.centraldir.model.Satelite;

/**
 *
 * @author Alejandro Sanchez Acosta <asanchez@neurowork.net> http://www.neurowork.net
 * @since 29/12/2010
 */
public class OrganizacionSatelite extends BaseEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6123071272421891942L;
	private Organizacion organizacion;
	private Satelite satelite;
	
	public Organizacion getOrganizacion() {
		return organizacion;
	}
	public void setOrganizacion(Organizacion organizacion) {
		this.organizacion = organizacion;
	}
	public Satelite getSatelite() {
		return satelite;
	}
	public void setSatelite(Satelite satelite) {
		this.satelite = satelite;
	}

}
