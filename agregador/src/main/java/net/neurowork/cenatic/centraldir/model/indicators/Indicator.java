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
import net.neurowork.cenatic.centraldir.model.graphs.datasets.filters.OrganizacionFilter;


/**
 *
 * @author Alejandro Sanchez Acosta <asanchez@neurowork.net> http://www.neurowork.net
 * @since 04/11/2010
 */
public class Indicator extends NamedEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 69187704805252965L;

	private String descripcion;
	
	private TipoIndicador tipoIndicador;
	private TipoGrafico tipoGrafico;
	private AgruparPor agruparPor;
	private OrganizacionFilter filter;
	private int width;
	private int height;
	
	private String datasetGenerator;
	
	public String getDatasetGenerator() {
		return datasetGenerator;
	}

	public void setDatasetGenerator(String datasetGenerator) {
		this.datasetGenerator = datasetGenerator;
	}	
	
	public TipoIndicador getTipoIndicador() {
		return tipoIndicador;
	}
	public void setTipoIndicador(TipoIndicador tipoIndicador) {
		this.tipoIndicador = tipoIndicador;
	}
	public TipoGrafico getTipoGrafico() {
		return tipoGrafico;
	}
	public void setTipoGrafico(TipoGrafico tipoGrafico) {
		this.tipoGrafico = tipoGrafico;
	}
	public AgruparPor getAgruparPor() {
		return agruparPor;
	}
	public void setAgruparPor(AgruparPor agruparPor) {
		this.agruparPor = agruparPor;
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public OrganizacionFilter getFilter() {
		return filter;
	}

	public void setFilter(OrganizacionFilter filter) {
		this.filter = filter;
	}
}
