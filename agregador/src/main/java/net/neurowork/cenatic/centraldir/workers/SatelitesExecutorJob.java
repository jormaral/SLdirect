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

import java.util.Collection;

import net.neurowork.cenatic.centraldir.model.Satelite;
import net.neurowork.cenatic.centraldir.service.AsociacionService;
import net.neurowork.cenatic.centraldir.service.FormaJuridicaService;
import net.neurowork.cenatic.centraldir.service.OrganizacionSedeService;
import net.neurowork.cenatic.centraldir.service.OrganizacionService;
import net.neurowork.cenatic.centraldir.service.ProvinciaService;
import net.neurowork.cenatic.centraldir.service.SateliteService;
import net.neurowork.cenatic.centraldir.service.SectorService;
import net.neurowork.cenatic.centraldir.service.ServiceException;

import org.apache.commons.lang.BooleanUtils;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.util.StringUtils;

/**
 *
 * @author Alejandro Sanchez Acosta <asanchez@neurowork.net> http://www.neurowork.net
 * @since 07/12/2010
 */
public class SatelitesExecutorJob extends QuartzJobBean {
	protected final static Logger logger = LoggerFactory.getLogger(SatelitesExecutorJob.class);

	private OrganizacionSedeService organizacionSedeService;
	private SateliteService sateliteService;	
	private ProvinciaService provinciaService;
	private OrganizacionService organizacionService;
	private FormaJuridicaService formaJuridicaService;
	private SectorService sectorService;
	private AsociacionService asociacionService;
	
	protected void executeInternal(JobExecutionContext ctx) throws JobExecutionException {
		logger.info("Se ejecuta la busqueda en los satélites");
		
		if(sateliteService == null){
			logger.error("Servicio inválido.");
			return;
		}
		
		try {
			Collection<Satelite> satelites = sateliteService.findActivado();
			
			for(Satelite satelite : satelites){				
				if(BooleanUtils.isTrue(satelite.isActivado())){
					if(StringUtils.hasLength(satelite.getHostUrl()) &&
					   StringUtils.hasLength(satelite.getUser()) &&
					   StringUtils.hasLength(satelite.getPassword())){
						
						Thread t = new Thread(new XMLRestWorker(satelite, 
								sateliteService, 
								provinciaService, 
								organizacionService, 
								organizacionSedeService,
								formaJuridicaService,
								sectorService,
								asociacionService));
						t.start();
					}
	//				else{
	//					logger.warn("No se inicia la busqueda para el satelite: " + satelite + " por falta de información.");
	//				}				
				}
			}			
		} catch (ServiceException e) {
			logger.error(e.getMessage());
		}		
	}

	public SateliteService getSateliteService() {
		return sateliteService;
	}

	public void setSateliteService(SateliteService sateliteService) {
		this.sateliteService = sateliteService;
	}

	public ProvinciaService getProvinciaService() {
		return provinciaService;
	}

	public void setProvinciaService(ProvinciaService provinciaService) {
		this.provinciaService = provinciaService;
	}

	public OrganizacionService getOrganizacionService() {
		return organizacionService;
	}

	public void setOrganizacionService(OrganizacionService organizacionService) {
		this.organizacionService = organizacionService;
	}

	public OrganizacionSedeService getOrganizacionSedeService() {
		return organizacionSedeService;
	}

	public void setOrganizacionSedeService(
			OrganizacionSedeService organizacionSedeService) {
		this.organizacionSedeService = organizacionSedeService;
	}

	public FormaJuridicaService getFormaJuridicaService() {
		return formaJuridicaService;
	}

	public void setFormaJuridicaService(FormaJuridicaService formaJuridicaService) {
		this.formaJuridicaService = formaJuridicaService;
	}

	public SectorService getSectorService() {
		return sectorService;
	}

	public void setSectorService(SectorService sectorService) {
		this.sectorService = sectorService;
	}

	public AsociacionService getAsociacionService() {
		return asociacionService;
	}

	public void setAsociacionService(AsociacionService asociacionService) {
		this.asociacionService = asociacionService;
	}
}