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
package net.neurowork.cenatic.centraldir.model.indicators;

import net.neurowork.cenatic.centraldir.model.NamedEntity;

/**
 *
 * @author Alejandro Sanchez Acosta <asanchez@neurowork.net> http://www.neurowork.net
 * @since 15/11/2010
 */
public class TipoGrafico extends NamedEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String creator;

	private String datasetCreator;

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public String getDatasetCreator() {
		return datasetCreator;
	}

	public void setDatasetCreator(String datasetCreator) {
		this.datasetCreator = datasetCreator;
	}	
	
}
