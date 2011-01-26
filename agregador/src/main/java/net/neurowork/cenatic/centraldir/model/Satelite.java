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
package net.neurowork.cenatic.centraldir.model;

import java.sql.Timestamp;

/**
 *
 * @author Alejandro Sanchez Acosta <asanchez@neurowork.net> http://www.neurowork.net
 * @since 09/11/2010
 */
public class Satelite extends NamedEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 1 Day is the default Retrieval Interval
	 */
	public static final long DEF_RETRIEVAL_INTERVAL = 86400000L;
	
	private String hostUrl;
	private String user;
	private String password;
	private String passwordRetry;
	private Timestamp lastRetrieval;
	private Integer numEmpresas;
	private Integer lastOrgId;
	
	private float lat;
	private float lon;
	private Boolean activado;
	
	public Timestamp getLastRetrieval() {
		return lastRetrieval;
	}
	public void setLastRetrieval(Timestamp lastRetrieval) {
		this.lastRetrieval = lastRetrieval;
	}
	
	public Integer getNumEmpresas() {
		return numEmpresas;
	}
	public void setNumEmpresas(Integer numEmpresas) {
		this.numEmpresas = numEmpresas;
	}

	public float getLon() {
		return lon;
	}
	public void setLon(float lon) {
		this.lon = lon;
	}
	public float getLat() {
		return lat;
	}
	public void setLat(float lat) {
		this.lat = lat;
	}
	public Integer getLastOrgId() {
		return lastOrgId;
	}
	public void setLastOrgId(Integer lastOrgId) {
		this.lastOrgId = lastOrgId;
	}
	public String getHostUrl() {
		return hostUrl;
	}
	public void setHostUrl(String hostUrl) {
		this.hostUrl = hostUrl;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getPasswordRetry() {
		return passwordRetry;
	}
	public void setPasswordRetry(String passwordRetry) {
		this.passwordRetry = passwordRetry;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public Boolean isActivado() {
		return activado;
	}
	public Boolean getActivado() {
		return isActivado();
	}
	public void setActivado(Boolean activado) {
		this.activado = activado;
	}
}