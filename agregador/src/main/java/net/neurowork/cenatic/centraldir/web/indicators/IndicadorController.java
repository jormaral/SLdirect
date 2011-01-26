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
package net.neurowork.cenatic.centraldir.web.indicators;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import net.neurowork.cenatic.centraldir.model.dto.GenerateIndicadorDTO;
import net.neurowork.cenatic.centraldir.model.graphs.datasets.filters.NullFilter;
import net.neurowork.cenatic.centraldir.model.graphs.datasets.filters.OrganizacionFilter;
import net.neurowork.cenatic.centraldir.model.graphs.datasets.filters.ProvinciaFilter;
import net.neurowork.cenatic.centraldir.model.graphs.datasets.filters.ServicioFilter;
import net.neurowork.cenatic.centraldir.model.indicators.Indicator;
import net.neurowork.cenatic.centraldir.model.indicators.TipoGrafico;
import net.neurowork.cenatic.centraldir.model.satelite.Capacidad;
import net.neurowork.cenatic.centraldir.model.satelite.Provincia;
import net.neurowork.cenatic.centraldir.model.satelite.Sector;
import net.neurowork.cenatic.centraldir.service.IndicadorService;
import net.neurowork.cenatic.centraldir.service.OrganizacionService;
import net.neurowork.cenatic.centraldir.service.ProvinciaService;
import net.neurowork.cenatic.centraldir.service.SectorService;
import net.neurowork.cenatic.centraldir.service.ServiceException;
import net.neurowork.cenatic.centraldir.util.ChartsUtil;

import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.Dataset;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;


/**
 *
 * @author Alejandro Sanchez Acosta <asanchez@neurowork.net> http://www.neurowork.net
 * @since 12/11/2010
 */
@Controller
public class IndicadorController {
	private static final String TODOS_LOS_SERVICIOS = "Todos los Servicios";

	private static final String IND_FILTER = "_FILTER_";

	private final static Logger logger = LoggerFactory.getLogger(IndicadorController.class);
	
	private static Provincia allProvincias;
	private static Sector allSectores;
	
	static{
		allProvincias = new Provincia();
		allProvincias.setName("Todas las Provincias");
		allSectores = new Sector();
		allSectores.setName("Todos los Sectores");
	}
	
	@Autowired
	private IndicadorService indicatorService;
	@Autowired
	private ProvinciaService provinciaService;
	@Autowired
	private SectorService sectorService;
	@Autowired
	private OrganizacionService organizacionService;


	@RequestMapping("/indicators")
	public ModelAndView indicatorsHandler() {
		if(logger.isTraceEnabled())
			logger.trace("Generar Indicadores");
		ModelAndView mav = new ModelAndView("indicators/list");
		
		try {
			List<Provincia> provincias = new ArrayList<Provincia>();
			provincias.add(allProvincias);
			provincias.addAll(provinciaService.getProvincias());
			
			List<Sector> sectores = new ArrayList<Sector>();
			sectores.add(allSectores);
			sectores.addAll(sectorService.getAll());
			
			Collection<Capacidad> capList = organizacionService.getCapacidades();
			List<String> servicios = buildCategoryMap(capList);
			
			mav.addObject("indicatorDto", new GenerateIndicadorDTO()); 
			mav.addObject("indicadores", indicatorService.getIndicators());
			mav.addObject("provincias", provincias);
			mav.addObject("sectores", sectores);
			mav.addObject("servicios", servicios);
		} catch (ServiceException e) {
			logger.error(e.getMessage());
		}		
		return mav;
	}
	
	private List<String> buildCategoryMap(Collection<Capacidad> capList) {
		List<String> ret = new ArrayList<String>();
		ret.add(TODOS_LOS_SERVICIOS);
		for(Capacidad capacidad : capList){
			String categoria = capacidad.getCategoria();
			if(!ret.contains(categoria)){
				ret.add(categoria);
			}
		}
		return ret;
	}

	@RequestMapping(method=RequestMethod.POST, value="/indicators")
	public ModelAndView generateIndicadorHandler(@ModelAttribute GenerateIndicadorDTO generateDTO, 
			BindingResult result,  SessionStatus status,
			HttpSession session) {
		if(logger.isTraceEnabled())
			logger.trace("Generar Indicadores");

		
		OrganizacionFilter filter = null;
		if(generateDTO.getProvincia() == null)
			filter = new NullFilter();
		else
			filter = new ProvinciaFilter(generateDTO.getProvincia());

		List<Indicator> indicators = Arrays.asList(generateDTO.getIndicadores());
		if(logger.isTraceEnabled())
			logger.trace("Mostrando: " + indicators.size() + " indicadores.");
		
//		List<Indicator> indicators = new ArrayList<Indicator>();
//		for(Indicator indicator : generateDTO.getIndicadores()){
//			logger.trace("Indicador: " + indicator);
//			indicators.add(indicator);
//		}			
		
		ModelAndView mav = null;
		if(generateDTO.isPdf()){
			logger.info("Generar Indicadores en PDF");
			mav = new ModelAndView("pdfCharts");
			mav.addObject("indicadores", indicators);
			try {
				mav.addObject("indMap", builIndicatorsMap(indicators, filter));
			} catch (ServiceException e) {
				logger.error(e.getMessage());
			}
		}else{
			logger.info("Generar Indicadores sin PDF");
			mav = new ModelAndView("indicators/gen");
			mav.addObject("indicadores", indicators);
			session.setAttribute(IND_FILTER, filter);
		}
		
		return mav;
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/serviciosIndicators")
	public ModelAndView generateServiciosIndicadorHandler(@ModelAttribute GenerateIndicadorDTO generateDTO, 
			BindingResult result,  SessionStatus status, HttpSession session) {
		
		ModelAndView mav = null;

		if(logger.isTraceEnabled())
			logger.trace("Generar Indicadores por Servicios");


		TipoGrafico graficoBarra;
		try {
			graficoBarra = indicatorService.findTipoGraficoById(1);

			List<Indicator> indicators = new ArrayList<Indicator>();
			Indicator servicioIndicador = new Indicator();
			servicioIndicador.setName("Empresas por Servicios");
			servicioIndicador.setWidth(800);
			servicioIndicador.setHeight(480);
			servicioIndicador.setTipoGrafico(graficoBarra);
			servicioIndicador.setDatasetGenerator("net.neurowork.cenatic.centraldir.model.indicators.impl.OfertasPorServicios");
			indicators.add(servicioIndicador);

			ServicioFilter filter = new ServicioFilter();
			if(generateDTO.getProvincia() != null)
				filter.setProvincia(generateDTO.getProvincia());
			if(generateDTO.getSector() != null)
				filter.setSector(generateDTO.getSector());
			String servicio = generateDTO.getServicio();
			if(!servicio.equals(TODOS_LOS_SERVICIOS))
				filter.setServicio(servicio);
			
			if(generateDTO.isPdf()){
				logger.info("Generar Indicadores en PDF");
				mav = new ModelAndView("pdfCharts");
				mav.addObject("indicadores", indicators);
				mav.addObject("indMap", builIndicatorsMap(indicators, filter));
			}else{
				logger.info("Generar Indicadores sin PDF");
				mav = new ModelAndView("indicators/gen");
				mav.addObject("indicadores", indicators);
				session.setAttribute(IND_FILTER, filter);
			}
			
		} catch (ServiceException e) {
			logger.error(e.getMessage());
		}
		
		
		
		return mav;
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/demandasIndicators")
	public ModelAndView generateDemandasIndicadorHandler(@ModelAttribute GenerateIndicadorDTO generateDTO, 
			BindingResult result,  SessionStatus status, HttpSession session) {
		
		ModelAndView mav = null;
		return mav;
	}
	
	private Map<Indicator, JFreeChart> builIndicatorsMap(List<Indicator> indicators, OrganizacionFilter filter) throws ServiceException {
		Map<Indicator, JFreeChart> ret = new HashMap<Indicator, JFreeChart>();
		
		for(Indicator indicator : indicators){
			indicator.setFilter(filter);
			Dataset dataset = indicatorService.buildGraphDataset(indicator);
			JFreeChart chart = ChartsUtil.generateChart(indicator, dataset);
			ret.put(indicator, chart);
		}
		
		return ret;
	}

	@RequestMapping("/indicators/{indicatorId}")
	public ModelAndView indicatorHandler(@PathVariable("indicatorId") int indicatorId) {
		if(logger.isTraceEnabled())
			logger.trace("Showing Information for Satelite width Id: " + indicatorId);
		ModelAndView mav = new ModelAndView("indicators/show");
		Indicator indicator;
		try {
			indicator = this.indicatorService.loadIndicator(indicatorId);
			mav.addObject("indicator", indicator);
		} catch (ServiceException e) {
			logger.error(e.getMessage());
		} 
		return mav;
	}
	
	
	@RequestMapping("/indicators/{indicatorId}/png")
	public void pngIndicatorHandler(@PathVariable("indicatorId") int indicatorId, 
			OutputStream stream,
			SessionStatus status,
			HttpSession session) throws IOException {
		if(logger.isTraceEnabled())
			logger.trace("Showing PNG of Indicator with Id: " + indicatorId);
		Indicator indicador;
		try {
			indicador = indicatorService.loadIndicator(indicatorId);
			
			OrganizacionFilter filter = (OrganizacionFilter)session.getAttribute(IND_FILTER);
			
			if(filter != null){
				logger.info("Filtering: " + filter.toString());
			}
			
			indicador.setFilter(filter);
			
			Dataset dataset = indicatorService.buildGraphDataset(indicador);
			JFreeChart chart = ChartsUtil.generateChart(indicador, dataset);
			if(chart != null){
				ChartUtilities.writeChartAsPNG(stream, 
						chart, 
						indicador.getWidth(), 
						indicador.getHeight());
			}
			status.setComplete();
		} catch (ServiceException e) {
			logger.error(e.getMessage());
		}
	}
}
