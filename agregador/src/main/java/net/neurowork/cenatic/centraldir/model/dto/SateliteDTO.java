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
package net.neurowork.cenatic.centraldir.model.dto;

import net.neurowork.cenatic.centraldir.model.Satelite;

/**
 *
 * @author Alejandro Sanchez Acosta <asanchez@neurowork.net> http://www.neurowork.net
 * @since 03/01/2011
 */
public class SateliteDTO {
	private Integer id;
	private String name;
	private Integer numEmpresas;
	private float lat;
	private float lon;
	private String html;
	
	public SateliteDTO(final Satelite satelite, Integer numEmpresas){
		super();
		this.id = satelite.getId();
		this.name = satelite.getName();
		this.numEmpresas = numEmpresas;
		this.lat = satelite.getLat();
		this.lon = satelite.getLon();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getNumEmpresas() {
		return numEmpresas;
	}

	public void setNumEmpresas(Integer numEmpresas) {
		this.numEmpresas = numEmpresas;
	}

	public float getLat() {
		return lat;
	}

	public void setLat(float lat) {
		this.lat = lat;
	}

	public float getLon() {
		return lon;
	}

	public void setLon(float lon) {
		this.lon = lon;
	}
	
	public String toString(){
		return name;
	}

	public String getHtml() {
		return html;
	}

	public void setHtml(String html) {
		this.html = html;
	}
}
