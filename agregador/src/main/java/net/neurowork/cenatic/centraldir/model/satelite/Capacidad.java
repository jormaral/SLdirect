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

import net.neurowork.cenatic.centraldir.model.NamedEntity;

/**
 *
 * @author Alejandro Sanchez Acosta <asanchez@neurowork.net> http://www.neurowork.net
 * @since 08/11/2010
 */
public class Capacidad extends NamedEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2248611065223310087L;
	private String cpc;
	private String isic;
	private String descripcion;
	private String categoria;
	
	public String getCpc() {
		return cpc;
	}
	public void setCpc(String cpc) {
		this.cpc = cpc;
	}
	public String getIsic() {
		return isic;
	}
	public void setIsic(String isic) {
		this.isic = isic;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getCategoria() {
		return categoria;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}	
}
