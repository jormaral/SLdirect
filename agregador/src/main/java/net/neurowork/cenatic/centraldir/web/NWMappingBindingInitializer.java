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
package net.neurowork.cenatic.centraldir.web;

import java.text.SimpleDateFormat;
import java.util.Date;

import net.neurowork.cenatic.centraldir.model.indicators.AgruparPor;
import net.neurowork.cenatic.centraldir.model.indicators.Indicator;
import net.neurowork.cenatic.centraldir.model.indicators.TipoGrafico;
import net.neurowork.cenatic.centraldir.model.indicators.TipoIndicador;
import net.neurowork.cenatic.centraldir.model.satelite.ClasificacionOrganizacion;
import net.neurowork.cenatic.centraldir.model.satelite.FormaJuridica;
import net.neurowork.cenatic.centraldir.model.satelite.Provincia;
import net.neurowork.cenatic.centraldir.service.FormaJuridicaService;
import net.neurowork.cenatic.centraldir.service.IndicadorService;
import net.neurowork.cenatic.centraldir.service.OrganizacionService;
import net.neurowork.cenatic.centraldir.service.ProvinciaService;
import net.neurowork.cenatic.centraldir.web.editors.AgruparPorEditor;
import net.neurowork.cenatic.centraldir.web.editors.ClasificacionOrganizacionEditor;
import net.neurowork.cenatic.centraldir.web.editors.FormaJuridicaEditor;
import net.neurowork.cenatic.centraldir.web.editors.IndicatorEditor;
import net.neurowork.cenatic.centraldir.web.editors.ProvinciaEditor;
import net.neurowork.cenatic.centraldir.web.editors.TipoGraficoEditor;
import net.neurowork.cenatic.centraldir.web.editors.TipoIndicadorEditor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.support.WebBindingInitializer;
import org.springframework.web.context.request.WebRequest;


/**
 * Shared NWMappingBindingInitializer for custom editors.
 *
 * <p>Alternatively, such init-binder code may be put into
 * {@link org.springframework.web.bind.annotation.InitBinder}
 * annotated methods on the controller classes themselves.
 * 
 * @author Alejandro Sanchez Acosta <asanchez@neurowork.net> http://www.neurowork.net
 */
public class NWMappingBindingInitializer implements WebBindingInitializer{
//	private final static Logger logger = LoggerFactory.getLogger(NWMappingBindingInitializer.class);

	@Autowired
	private IndicadorService indicadorService;
	@Autowired
	private ProvinciaService provinciaService;
	@Autowired
	private FormaJuridicaService formaJuridicaService;
	@Autowired
	private OrganizacionService organizacionService;

	public void initBinder(WebDataBinder binder, WebRequest request) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/mm/yyyy");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
		binder.registerCustomEditor(String.class, new StringTrimmerEditor(false));
//		logger.info("Adding Custom Editors");
		binder.registerCustomEditor(TipoGrafico.class, new TipoGraficoEditor(indicadorService));
		binder.registerCustomEditor(TipoIndicador.class, new TipoIndicadorEditor(indicadorService));
		binder.registerCustomEditor(AgruparPor.class, new AgruparPorEditor(indicadorService));
		binder.registerCustomEditor(Provincia.class, new ProvinciaEditor(provinciaService));
		binder.registerCustomEditor(FormaJuridica.class, new FormaJuridicaEditor(formaJuridicaService));
		binder.registerCustomEditor(Indicator.class, new IndicatorEditor(indicadorService));
		binder.registerCustomEditor(ClasificacionOrganizacion.class, new ClasificacionOrganizacionEditor(organizacionService));
	}
}
