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
package net.neurowork.cenatic.centraldir.workers.xml;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.xml.parsers.ParserConfigurationException;

import net.neurowork.cenatic.centraldir.model.Satelite;
import net.neurowork.cenatic.centraldir.model.satelite.ClasificacionOrganizacion;
import net.neurowork.cenatic.centraldir.model.satelite.FormaJuridica;
import net.neurowork.cenatic.centraldir.model.satelite.Organizacion;
import net.neurowork.cenatic.centraldir.model.satelite.OrganizacionAsociacion;
import net.neurowork.cenatic.centraldir.model.satelite.OrganizacionCapacidadDemanda;
import net.neurowork.cenatic.centraldir.model.satelite.OrganizacionCapacidadOferta;
import net.neurowork.cenatic.centraldir.model.satelite.OrganizacionEvento;
import net.neurowork.cenatic.centraldir.model.satelite.OrganizacionSatelite;
import net.neurowork.cenatic.centraldir.model.satelite.OrganizacionSede;
import net.neurowork.cenatic.centraldir.model.satelite.Provincia;
import net.neurowork.cenatic.centraldir.service.AsociacionService;
import net.neurowork.cenatic.centraldir.service.FormaJuridicaService;
import net.neurowork.cenatic.centraldir.service.OrganizacionSedeService;
import net.neurowork.cenatic.centraldir.service.OrganizacionService;
import net.neurowork.cenatic.centraldir.service.ProvinciaService;
import net.neurowork.cenatic.centraldir.service.SateliteService;
import net.neurowork.cenatic.centraldir.service.SectorService;
import net.neurowork.cenatic.centraldir.service.ServiceException;
import net.neurowork.cenatic.centraldir.util.XmlParserUtil;
import net.neurowork.cenatic.centraldir.workers.RestUrlManager;
import net.neurowork.cenatic.centraldir.workers.XMLRestWorker;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author Alejandro Sanchez Acosta <asanchez@neurowork.net> http://www.neurowork.net
 * @since 07/01/2011
 */
public class OrganizacionXmlImporter implements XmlImportConstants{
	private final static Logger logger = LoggerFactory.getLogger(OrganizacionXmlImporter.class);

	private Satelite satelite;
	private RestUrlManager restUrl;
	private int idLeap;
	
	private OrganizacionService organizacionService;
	private SateliteService sateliteService;
	
	private ProvinciaXmlImporter provinciaImporter;
	private FormaJuridicaXmlImporter formaJuridicaImporter;
	private ClasificacionOrganizacionXmlImporter clasificacionOrganizacionImporter;
	private SedeXmlImporter sedeImporter;
	private AsociacionXmlImporter asociacionImporter;
	private EventosXmlImporter eventosImporter;
	
	private CapacidadXmlImporter capacidadImporter;
	private SectorXmlImporter sectorImporter;
	private OfertasXmlImporter ofertasImporter;
	private DemandasXmlImporter demandasImporter;

	
	public OrganizacionXmlImporter(Satelite satelite, 
			RestUrlManager restUrl, 
			int idLeap, 
			OrganizacionService organizacionService,
			SateliteService sateliteService, 
			ProvinciaService provinciaService, 
			FormaJuridicaService formaJuridicaService,
			OrganizacionSedeService organizacionSedeService, 
			AsociacionService asociacionService, 
			SectorService sectorService){
		super();
		
		this.satelite = satelite;
		this.restUrl = restUrl;
		this.idLeap = idLeap;		 
		this.organizacionService = organizacionService;
		this.sateliteService = sateliteService;
		this.provinciaImporter = new ProvinciaXmlImporter(satelite, provinciaService);
		this.formaJuridicaImporter = new FormaJuridicaXmlImporter(restUrl, formaJuridicaService);
		this.clasificacionOrganizacionImporter = new ClasificacionOrganizacionXmlImporter(restUrl, organizacionService);
		this.sedeImporter = new SedeXmlImporter(provinciaImporter, organizacionSedeService);
		this.asociacionImporter = new AsociacionXmlImporter(restUrl, asociacionService);
		this.eventosImporter = new EventosXmlImporter(organizacionService);		
		this.capacidadImporter = new CapacidadXmlImporter(restUrl, organizacionService);
		this.sectorImporter = new SectorXmlImporter(restUrl, sectorService);		
		this.ofertasImporter = new OfertasXmlImporter(capacidadImporter, sectorImporter);
		this.demandasImporter = new DemandasXmlImporter(capacidadImporter, sectorImporter);
	}
	
	public void buscarOrganizacion(String token) {
		Organizacion organizacion = null;
		if(logger.isDebugEnabled())
			logger.debug("Buscando Organizaciones para Satelite: " + satelite);
		
		HttpClient httpclient = XMLRestWorker.getHttpClient(); 
		
		GetMethod get = new GetMethod(restUrl.getOrganizacionUrl());
		get.addRequestHeader("Accept" , "application/xml"); 
		NameValuePair tokenParam = new NameValuePair("token", token);
		int id = 0;
//		if(satelite.getLastOrgId() != null) 
//			id = satelite.getLastOrgId();

        int leap = idLeap;
		
		while(true){
			id = id+1;

			if(logger.isTraceEnabled()){
				logger.trace("Buscando Organizacion con id: " + id + " en el Satelite: " + satelite);
			}		
			
			NameValuePair passwordParam = new NameValuePair("id", String.valueOf(id));
			NameValuePair[] params = new NameValuePair[]{tokenParam, passwordParam};
			
			get.setQueryString(params);	
			String queryString = get.getQueryString();
			
	        int statusCode;
			try {
				if(logger.isTraceEnabled()){
					logger.trace("Query String: " + queryString);
				}			
				
				statusCode = httpclient.executeMethod(get);
				if (statusCode != HttpStatus.SC_OK) {
					logger.error("Method failed: " + get.getStatusLine());
				}else{
					String xmlString = get.getResponseBodyAsString();
					if(logger.isInfoEnabled()){
						logger.info("Xml Received.");
					}	
					
					String xmlHash = XMLRestWorker.constructXmlHash(xmlString);
					
					organizacion = organizacionService.findByHash(xmlHash);
					if(organizacion == null){
						organizacion = parseOrganizacion(xmlString);
						if(organizacion == null){
							leap--;							
							if(leap <= 0){
								logger.info("Se llegó al fin de las Organizaciones. Saliendo del Satelite: " + satelite);
								break;
							}else{
								logger.info("Leap: " + leap);
							}
						}else{
							if(organizacion.getSatelites() == null){
								organizacion.setSatelites(new HashSet<OrganizacionSatelite>());
							}
							boolean sateliteFound = false;
							for(OrganizacionSatelite sat : organizacion.getSatelites()){
								if(sat.getSatelite().getName().equals(satelite.getName())){
									sateliteFound=true;
									break;
								}
							}
							if(!sateliteFound){
								OrganizacionSatelite newSat = new OrganizacionSatelite();
								newSat.setSatelite(satelite);
								newSat.setOrganizacion(organizacion);
								organizacion.getSatelites().add(newSat);
							}
							
							organizacion.setXmlHash(xmlHash);
							organizacionService.saveOrganization(organizacion);
							
							int numEmpresas = 0;
							if(satelite.getNumEmpresas() != null){
								numEmpresas = satelite.getNumEmpresas();
							}							 
							numEmpresas++;							
							
							Date now = new Date();
							satelite.setNumEmpresas(numEmpresas);
							satelite.setLastRetrieval(new Timestamp(now.getTime()));
							satelite.setLastOrgId(id);
							sateliteService.saveSatelite(satelite);
						}
					}else{
						if(logger.isInfoEnabled()){
							logger.info("Organizacion with id: " + id + " already in centraldir");
						}
					}
				}
			} catch (HttpException e) {
				logger.error(e.getMessage());
			} catch (IOException e) {
				logger.error(e.getMessage());
			} catch (ParserConfigurationException e) {
				logger.error(e.getMessage());
			} catch (SAXException e) {
				logger.error(e.getMessage());
			} catch (ServiceException e) {
				logger.error(e.getMessage());
			} catch (NoSuchAlgorithmException e) {
				logger.error(e.getMessage());
			}finally {
	            get.releaseConnection();
	        }
		}		
	}
	
	private Organizacion parseOrganizacion(String xmlString) throws ParserConfigurationException, SAXException, IOException, ServiceException {
		Document doc = XmlParserUtil.createDocumentFromString(xmlString);

		Organizacion ret = null;
		
		NodeList nodeLst = doc.getElementsByTagName(ORGANIZATION_TAG);
		for (int s = 0; s < nodeLst.getLength(); s++) {
		    Node fstNode = nodeLst.item(s);
	        if (fstNode.getNodeType() == Node.ELEMENT_NODE) {
	        	Element elOrg = (Element)fstNode;

	        	String legalId = XmlParserUtil.getStringNodeValue(elOrg, XML_LEGAL_ID);
	        	if(!legalId.isEmpty()){
	        		ret = organizacionService.findByCIF(legalId);
	        		if(ret == null){
	        			if(logger.isTraceEnabled());
	        				logger.trace("Organizacion con CIF: " + legalId + " no encontrada, AGREGANDO");
		        		ret = new Organizacion();
	        		}else{
	        			if(logger.isTraceEnabled());
	        				logger.trace("Organizacion con CIF: " + legalId + " encontrada, ACTUALIZANDO");
	        		}
	        		
	        		String name = XmlParserUtil.getStringNodeValue(elOrg, "name");
	        		String description = XmlParserUtil.getStringNodeValue(elOrg, "description");
	        		String web = XmlParserUtil.getStringNodeValue(elOrg, "web");
	        		String logoUrl = XmlParserUtil.getStringNodeValue(elOrg, "logo_url");
	        		String direccion = XmlParserUtil.getStringNodeValue(elOrg, "HQstreet");
	        		String localidad = XmlParserUtil.getStringNodeValue(elOrg, "HQlocality");

	        		String newsTitle = XmlParserUtil.getStringNodeValue(elOrg, "newsTitle");
	        		String newsBody = XmlParserUtil.getStringNodeValue(elOrg, "newsBody");

		        	Element provEl = XmlParserUtil.getChildren(elOrg, "HQprovince");
		        	String provId = XmlParserUtil.getAttribute(provEl, "code");	        		
	        		String provincia = XmlParserUtil.getStringNodeValue(elOrg, "HQprovince");
	        		String codPostal = XmlParserUtil.getStringNodeValue(elOrg, "HQpostalCode");
	        		String year = XmlParserUtil.getStringNodeValue(elOrg, "year");

	        		String partners = XmlParserUtil.getStringNodeValue(elOrg, "partners");
	        		String corpGroup = XmlParserUtil.getStringNodeValue(elOrg, "enpresarialGroup");
	        		String phoneNumber = XmlParserUtil.getStringNodeValue(elOrg, "telephone");
	        		String email = XmlParserUtil.getStringNodeValue(elOrg, "email");
	        		String research = XmlParserUtil.getStringNodeValue(elOrg, "researchAndDevelopmentProgrammes");
	        		String relacionesComunidad = XmlParserUtil.getStringNodeValue(elOrg, "communityRelationships");
	        		String qualityCertifications = XmlParserUtil.getStringNodeValue(elOrg, "qualityCertifications");

		        	Element orgClassEl  = XmlParserUtil.getChildren(elOrg, "organizationClasification");
		        	String orgClassCode = XmlParserUtil.getAttribute(orgClassEl, "code");	        		
	        		String organizationClasification = XmlParserUtil.getStringNodeValue(elOrg, "organizationClasification");
	        		
	        		Element orgTypeEl = XmlParserUtil.getChildren(elOrg, "organizationType");
	        		String orgTypeCode = XmlParserUtil.getAttribute(orgTypeEl, "code");
	        		String organizationType = XmlParserUtil.getStringNodeValue(elOrg, "organizationType");

		        	Element degationsEl  = XmlParserUtil.getChildren(elOrg, "delegations");
		        	Element capacitiesEl = XmlParserUtil.getChildren(elOrg, "capacities");
		        	Element demandsEl = XmlParserUtil.getChildren(elOrg, "demands");
		        	Element eventsEl  = XmlParserUtil.getChildren(elOrg, "events");		        	
		        	// Association Memberships
		        	Element associationsEl = XmlParserUtil.getChildren(elOrg, "associationMemberships");
		        	//username
	        		String username = XmlParserUtil.getStringNodeValue(elOrg, "username");
		        	//password
	        		String password = XmlParserUtil.getStringNodeValue(elOrg, "password");
	        		
	        		ret.setCif(legalId);
	        		ret.setName(name);
	        		ret.setDescripcion(description);
	        		ret.setNewsTitle(newsTitle);
	        		ret.setNewsBody(newsBody);
	        		ret.setWeb(web);
	        		ret.setLogoUrl(logoUrl);
	        		ret.setDireccion(direccion);
	        		ret.setLocalidad(localidad);
	        		
	        		Provincia prov = provinciaImporter.findProvincia(provId, provincia);
	        		if(prov != null)
	        			ret.setProvincia(prov);
	        		
	        		ret.setCodigoPostal(Integer.parseInt(codPostal));
	        		try {
	        			if(year != null && !year.isEmpty())
	        				ret.setAnoConstitucion(Integer.parseInt(year));
					} catch (NumberFormatException e) {
					}
	        		ret.setPartners(partners);
	        		ret.setGrupoEmpresarial(corpGroup);
	        		ret.setTelefono(phoneNumber);
	        		ret.setEmail(email);
	        		ret.setActividadesImasD(!research.isEmpty());
	        		ret.setParticipacionImasD(research);	        		
	        		ret.setRelacionesComunidad(relacionesComunidad);
	        		ret.setCertificacionesCalidad(Organizacion.decoded(qualityCertifications));
	        		
	        		if(orgClassEl != null && StringUtils.hasLength(orgClassCode)){
	        			ClasificacionOrganizacion co = clasificacionOrganizacionImporter.findBaseEntity(orgClassCode, organizationClasification);
	        			if(co != null)
	        				ret.setClasificacionOrganizacion(co);
	        		}
	        		if(orgTypeEl != null && StringUtils.hasLength(orgTypeCode)){
	        			FormaJuridica fj = formaJuridicaImporter.findBaseEntity(orgTypeCode, organizationType);
	        			if(fj != null)
	        				ret.setFormaJuridica(fj);
	        		}
	        		if(degationsEl != null){
	        			Set<OrganizacionSede> sedes = sedeImporter.buscarSedes(degationsEl, ret);
	        			if(ret.getSedes() == null){
	    	        		ret.setSedes(sedes);
	        			}else{
	        				ret.getSedes().addAll(sedes);
	        			}
	        		}
	        		if(capacitiesEl != null){
	        			Set<OrganizacionCapacidadOferta> ofertas = ofertasImporter.buscarOfertas(capacitiesEl, ret);
	        			if(ret.getOfertas() == null){
	        				ret.setOfertas(ofertas);	
	        			}else{
	        				ret.getOfertas().addAll(ofertas);	
	        			}	        				
	        		}
	        		if(demandsEl != null){
	        			Set<OrganizacionCapacidadDemanda> demandas = demandasImporter.buscarDemandas(demandsEl, ret);
	        			if(ret.getDemandas() == null){        			
	        				ret.setDemandas(demandas);
	        			}else{
	        				ret.getDemandas().addAll(demandas);
	        			}	        			
	        		}
	        		if(associationsEl != null){
	        			Set<OrganizacionAsociacion> organizacionAsociacions = asociacionImporter.buscarAsociaciones(associationsEl, ret);
	        			if(ret.getOrganizacionAsociacions() == null){
	        				ret.setOrganizacionAsociacions(organizacionAsociacions);
	        			}else{
	        				ret.getOrganizacionAsociacions().addAll(organizacionAsociacions);
	        			}
	        		}
	        		if(eventsEl != null){
	        			Set<OrganizacionEvento> eventos = eventosImporter.buscarEventos(eventsEl, ret);
	        			if(ret.getEventos() == null){
	        				ret.setEventos(eventos);
	        			}else{
	        				ret.getEventos().addAll(eventos);
	        			}
	        		}

	        		ret.setUsername(username);
	        		ret.setPassword(password);
	        	}	        	
	        }
		}
		
		return ret;
	}
	
}
