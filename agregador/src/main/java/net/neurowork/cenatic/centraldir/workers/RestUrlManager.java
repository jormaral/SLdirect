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
package net.neurowork.cenatic.centraldir.workers;

import net.neurowork.cenatic.centraldir.model.Satelite;

/**
 *
 * @author Alejandro Sanchez Acosta <asanchez@neurowork.net> http://www.neurowork.net
 * @since 02/12/2010
 */
public class RestUrlManager {
	private static final String REST_STR = "/rest/";
	private Satelite satelite;

	public RestUrlManager(Satelite satelite) {
		super();
		this.satelite = satelite;
	}
	
	public String getTokenUrl() {
		return satelite.getHostUrl() + REST_STR + "getToken.php";
	}
	
	public String getOrganizacionUrl(){
		return satelite.getHostUrl() + REST_STR + "getOrganization.php";
	}

	public String getFormaJuridicaUrl() {
		return satelite.getHostUrl() + REST_STR + "getOrganizationTypeList.php";
	}

	public String getClassOrganizacionUrl() {
		return satelite.getHostUrl() + REST_STR + "getOrganizationClassificationList.php";
	}

	public String getCapacidadUrl() {
		return satelite.getHostUrl() + REST_STR + "getCapacityList.php";
	}

	public String getSectorUrl() {
		return satelite.getHostUrl() + REST_STR + "getSectorList.php";
	}
	
	public String getAssociationUrl(){
		return satelite.getHostUrl() + REST_STR + "getAssociationList.php";
	}

	public String getPushOrganizationUrl() {
		return satelite.getHostUrl() + REST_STR + "pushOrganization.php";
	}
}
