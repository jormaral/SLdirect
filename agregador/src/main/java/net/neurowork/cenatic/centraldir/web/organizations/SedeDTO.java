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
public final class SedeDTO {
	private final Integer id;
	private final float latitud;
	private final float longitud;
	private final String address;
	
	public SedeDTO(Integer id, float latitud, float longitud, String address) {
		super();
		this.id = id;
		this.latitud = latitud;
		this.longitud = longitud;
		this.address = address;
	}
	
	public float getLatitud() {
		return latitud;
	}
	public float getLongitud() {
		return longitud;
	}
	public String getAddress() {
		return address;
	}

	public Integer getId() {
		return id;
	}
	
}
