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

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.xml.parsers.ParserConfigurationException;

import net.neurowork.cenatic.centraldir.model.Satelite;
import net.neurowork.cenatic.centraldir.model.satelite.Asociacion;
import net.neurowork.cenatic.centraldir.model.satelite.Capacidad;
import net.neurowork.cenatic.centraldir.model.satelite.ClasificacionOrganizacion;
import net.neurowork.cenatic.centraldir.model.satelite.Evento;
import net.neurowork.cenatic.centraldir.model.satelite.FormaJuridica;
import net.neurowork.cenatic.centraldir.model.satelite.Organizacion;
import net.neurowork.cenatic.centraldir.model.satelite.OrganizacionAsociacion;
import net.neurowork.cenatic.centraldir.model.satelite.OrganizacionCapacidadDemanda;
import net.neurowork.cenatic.centraldir.model.satelite.OrganizacionCapacidadOferta;
import net.neurowork.cenatic.centraldir.model.satelite.OrganizacionEvento;
import net.neurowork.cenatic.centraldir.model.satelite.OrganizacionSatelite;
import net.neurowork.cenatic.centraldir.model.satelite.OrganizacionSede;
import net.neurowork.cenatic.centraldir.model.satelite.Provincia;
import net.neurowork.cenatic.centraldir.model.satelite.Sector;
import net.neurowork.cenatic.centraldir.service.AsociacionService;
import net.neurowork.cenatic.centraldir.service.FormaJuridicaService;
import net.neurowork.cenatic.centraldir.service.OrganizacionSedeService;
import net.neurowork.cenatic.centraldir.service.OrganizacionService;
import net.neurowork.cenatic.centraldir.service.ProvinciaService;
import net.neurowork.cenatic.centraldir.service.SateliteService;
import net.neurowork.cenatic.centraldir.service.SectorService;
import net.neurowork.cenatic.centraldir.service.ServiceException;
import net.neurowork.cenatic.centraldir.util.SHA1;
import net.neurowork.cenatic.centraldir.util.XmlParserUtil;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
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
 * @since 30/11/2010
 */
public class XMLRestWorker implements Runnable {
	public static final String HTTP_CONTEXT_CHARSET_ISO_8859_1 = "ISO-8859-1";
	public static final String HTTP_CONTEXT_CHARSET_UTF_8 = "UTF-8";

	private final static Logger logger = LoggerFactory.getLogger(XMLRestWorker.class);
	
	private static SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd"); 
	
	
	private static final String CODE_ATTR = "code";
	private static final String TOKEN_TAG = "token";

	private static final String TOKEN_RESULT_TAG = "TokenResult";
	private static final String ORGANIZATION_TAG = "organization";
	
	public static final String USERNAME_PARAM = "username";
	public static final String PASSWORD_PARAM = "password";
	
	private RestUrlManager restUrl;
	private Satelite satelite;
	private SateliteService sateliteService;
	private ProvinciaService provinciaService;
	private OrganizacionService organizacionService;
	private OrganizacionSedeService organizacionSedeService;
	private FormaJuridicaService formaJuridicaService;
	private SectorService sectorService;
	private AsociacionService asociacionService;
	
	private int idLeap;

	public XMLRestWorker(Satelite satelite, 
			SateliteService sateliteService, 
			ProvinciaService provinciaService, 
			OrganizacionService organizacionService,
			OrganizacionSedeService organizacionSedeService,
			FormaJuridicaService formaJuridicaService,
			SectorService sectorService,
			AsociacionService asociacionService) {
		super();
		if(satelite == null)
			throw new IllegalArgumentException("Invalid Satelite: null");
		if(provinciaService == null)
			throw new IllegalArgumentException("Invalid ProvinciaService: null");
		if(sateliteService == null)
			throw new IllegalArgumentException("Invalid SateliteService: null");
		if(organizacionService == null)
			throw new IllegalArgumentException("Invalid OrganizacionService: null");
		if(organizacionSedeService == null)
			throw new IllegalArgumentException("Invalid OrganizacionSedeService: null");
		if(formaJuridicaService == null)
			throw new IllegalArgumentException("Invalid FormaJuridicaService: null");
		if(sectorService == null)
			throw new IllegalArgumentException("Invalid SectorService: null");
		if(asociacionService == null)
			throw new IllegalArgumentException("Invalid AsociacionService: null");
		this.satelite = satelite;
		this.provinciaService = provinciaService;		
		this.sateliteService = sateliteService;
		this.organizacionService = organizacionService;
		this.organizacionSedeService = organizacionSedeService;
		this.formaJuridicaService = formaJuridicaService;
		this.sectorService = sectorService;
		this.asociacionService = asociacionService;
		
		this.restUrl = new RestUrlManager(satelite);
		this.idLeap = 15;
	}

	@Override
	public void run() {
		if(logger.isInfoEnabled())
			logger.info("Stating XMLPush Worker for Satelite: " + satelite);
		if(logger.isTraceEnabled())
			logger.trace("Verifiying Connection");

		String token = getXmlToken();
		if(token != null){
			logger.info("Token: " + token);
			buscarOrganizacion(token);
		}
		logger.info("Ending XML Push Worker for Satelite: " + satelite);
	}

	private String getXmlToken() {
		String token = null;
		HttpClient httpclient = getHttpClient(); 
		GetMethod get = new GetMethod(restUrl.getTokenUrl());
		get.addRequestHeader("Accept" , "application/xml"); 

		NameValuePair userParam = new NameValuePair(USERNAME_PARAM, satelite.getUser());
		NameValuePair passwordParam = new NameValuePair(PASSWORD_PARAM, satelite.getPassword());
		NameValuePair[] params = new NameValuePair[]{userParam, passwordParam};
		
		get.setQueryString(params);	
		try {
			int statusCode = httpclient.executeMethod(get);
			if (statusCode != HttpStatus.SC_OK) {
				logger.error("Method failed: " + get.getStatusLine());
			}
            token = parseToken(get.getResponseBodyAsString());
		} catch (HttpException e) {
			logger.error(e.getMessage());
		} catch (IOException e) {
			logger.error(e.getMessage());
		} catch (ParserConfigurationException e) {
			logger.error(e.getMessage());
		} catch (SAXException e) {
			logger.error(e.getMessage());
		}finally {
            get.releaseConnection();
        }
		
		return token;
	}

	public static String parseToken(String xmlString) throws ParserConfigurationException, SAXException, IOException {
		Document doc = XmlParserUtil.createDocumentFromString(xmlString);
		String retToken = null;
		
		NodeList nodeLst = doc.getElementsByTagName(TOKEN_RESULT_TAG);
		for (int s = 0; s < nodeLst.getLength(); s++) {
		    Node fstNode = nodeLst.item(s);
	        if (fstNode.getNodeType() == Node.ELEMENT_NODE) {
	        	Element elPDU = (Element)fstNode;
	        	Element tokenEl = XmlParserUtil.getChildren(elPDU, TOKEN_TAG);
	        	retToken = XmlParserUtil.getAttribute(tokenEl, CODE_ATTR);
	        }
		}
    	logger.info("Token: " + retToken);
		return retToken;
	}
	
	private void buscarOrganizacion(String token) {
		Organizacion organizacion = null;
		if(logger.isDebugEnabled())
			logger.debug("Buscando Organizaciones para Satelite: " + satelite);
		
		HttpClient httpclient = getHttpClient(); 
		
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
					
					String xmlHash = constructXmlHash(xmlString);
					
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
	
	public static String constructXmlHash(String xmlString) throws NoSuchAlgorithmException {
		return SHA1.getHash(xmlString);
	}

	private Organizacion parseOrganizacion(String xmlString) throws ParserConfigurationException, SAXException, IOException, ServiceException {
		Document doc = XmlParserUtil.createDocumentFromString(xmlString);

		Organizacion ret = null;
		
		NodeList nodeLst = doc.getElementsByTagName(ORGANIZATION_TAG);
		for (int s = 0; s < nodeLst.getLength(); s++) {
		    Node fstNode = nodeLst.item(s);
	        if (fstNode.getNodeType() == Node.ELEMENT_NODE) {
	        	Element elOrg = (Element)fstNode;

	        	String legalId = XmlParserUtil.getStringNodeValue(elOrg, "legalID");
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
	        		ret.setProvincia(findProvincia(provId, provincia));
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
	        			ret.setClasificacionOrganizacion(findClasificacionOrganizacion(orgClassCode, organizationClasification));
	        		}
	        		if(orgTypeEl != null && StringUtils.hasLength(orgTypeCode)){
	        			ret.setFormaJuridica(findFormaJuridica(orgTypeCode, organizationType));
	        		}
	        		if(degationsEl != null){
	        			Set<OrganizacionSede> sedes = buscarSedes(degationsEl, ret);
	        			if(ret.getSedes() == null){
	    	        		ret.setSedes(sedes);
	        			}else{
	        				ret.getSedes().addAll(sedes);
	        			}
	        		}
	        		if(capacitiesEl != null){
	        			Set<OrganizacionCapacidadOferta> ofertas = buscarOfertas(capacitiesEl, ret);
	        			if(ret.getOfertas() == null){
	        				ret.setOfertas(ofertas);	
	        			}else{
	        				ret.getOfertas().addAll(ofertas);	
	        			}	        				
	        		}
	        		if(demandsEl != null){
	        			Set<OrganizacionCapacidadDemanda> demandas = buscarDemandas(demandsEl, ret);
	        			if(ret.getDemandas() == null){        			
	        				ret.setDemandas(demandas);
	        			}else{
	        				ret.getDemandas().addAll(demandas);
	        			}	        			
	        		}
	        		if(associationsEl != null){
	        			Set<OrganizacionAsociacion> organizacionAsociacions = buscarAsociaciones(associationsEl, ret);
	        			if(ret.getOrganizacionAsociacions() == null){
	        				ret.setOrganizacionAsociacions(organizacionAsociacions);
	        			}else{
	        				ret.getOrganizacionAsociacions().addAll(organizacionAsociacions);
	        			}
	        		}
	        		if(eventsEl != null){
	        			Set<OrganizacionEvento> eventos = buscarEventos(eventsEl, ret);
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
	
	private Set<OrganizacionEvento> buscarEventos(Element eventsEl, Organizacion organizacion) {
		if(eventsEl == null)
			return null;
		
		Set<OrganizacionEvento> ret = new HashSet<OrganizacionEvento>();
		
		NodeList nodeLst = eventsEl.getElementsByTagName("event");
		
		for (int s = 0; s < nodeLst.getLength(); s++) {
		    Node fstNode = nodeLst.item(s);
	        if (fstNode.getNodeType() == Node.ELEMENT_NODE) {
	        	Element elEvento = (Element)fstNode;
      	
	        	String localization = XmlParserUtil.getStringNodeValue(elEvento, "localization");
	        	String title = XmlParserUtil.getStringNodeValue(elEvento, "title");
        		String description = XmlParserUtil.getStringNodeValue(elEvento, "description");
				String date = XmlParserUtil.getStringNodeValue(elEvento, "date");
				
				Date fecha = null;
				try {
					fecha = formatter.parse(date);
				} catch (ParseException e) {
				}
				
				Evento evento = null;
				
				if(organizacion.getEventos() != null){
					for(OrganizacionEvento oe : organizacion.getEventos()){
						Evento e = oe.getEvento();
						if(e.getTitulo().equals(title)){
							evento = e;
							break;
						}							
					}					
				}
				if(evento == null){
					evento = new Evento();
					
					evento.setLocalizacion(localization);
					evento.setTitulo(title);
					evento.setDescripcion(description);
					evento.setFecha(fecha);
					try {
						organizacionService.saveEvento(evento);
						
			        	OrganizacionEvento eventoOrg = new OrganizacionEvento();
						eventoOrg.setEvento(evento);
						eventoOrg.setOrganizacion(organizacion);		        	
			        	ret.add(eventoOrg);	        	
					} catch (ServiceException e) {
						logger.error(e.getMessage());
					}
				}
	        }
		}		
		return ret;
	}

	private Set<OrganizacionAsociacion> buscarAsociaciones(Element associationsEl, Organizacion organizacion) {
		if(associationsEl == null)
			return null;
		Set<OrganizacionAsociacion> ret = new HashSet<OrganizacionAsociacion>();

		NodeList nodeLst = associationsEl.getElementsByTagName("associationMembership");
		
		for (int s = 0; s < nodeLst.getLength(); s++) {
		    Node fstNode = nodeLst.item(s);
	        if (fstNode.getNodeType() == Node.ELEMENT_NODE) {
	        	Element elDemand = (Element)fstNode;
	        
	        	String code = XmlParserUtil.getAttribute(elDemand, "code");
	        	String url = XmlParserUtil.getAttribute(elDemand, "url");
	        	String icon = XmlParserUtil.getAttribute(elDemand, "icon");

	        	Asociacion asoc = buscarAsociacion(Integer.parseInt(code));
	        	boolean found = false;
	        	if(organizacion.getOrganizacionAsociacions() != null){
		        	for(OrganizacionAsociacion orgAsoc :  organizacion.getOrganizacionAsociacions()){
		        		if(orgAsoc.getAsociacion().getName().equalsIgnoreCase(asoc.getName())){
		        			found = true;
		        			break;
		        		}
		        	}
	        	}
	        	if(!found){
	        		asoc.setUrl(url);
	        		asoc.setIcon(icon);
	        		try {
						asociacionService.save(asoc);
			        	OrganizacionAsociacion asociacion = new OrganizacionAsociacion();
			        	asociacion.setOrganizacion(organizacion);
						asociacion.setAsociacion(asoc);
			        	ret.add(asociacion);
					} catch (ServiceException e) {
						logger.error(e.getMessage());
					}
	        	}
	        }
		}
		
		
		return ret;
	}

	private Asociacion buscarAsociacion(int code) {
		HttpClient httpclient = getHttpClient();		
        GetMethod get = new GetMethod(restUrl.getAssociationUrl());
		get.addRequestHeader("Accept" , "application/xml"); 
		Asociacion ret = null;
        try {
            int result = httpclient.executeMethod(get);
            logger.info("Response status code: " + result);
            String xmlString = get.getResponseBodyAsString(); 
            logger.info("Response body: " + xmlString);
            
           	ret = importAsociacion(xmlString, code);
        } catch (HttpException e) {
        	logger.error(e.getMessage());
		} catch (IOException e) {
			logger.error(e.getMessage());
		} catch (ParserConfigurationException e) {
			logger.error(e.getMessage());
		} catch (SAXException e) {
			logger.error(e.getMessage());
		} finally {
            get.releaseConnection();
        }
		return ret;
	}

	private Asociacion importAsociacion(String xmlString, Integer associationCode) throws ParserConfigurationException, SAXException, IOException {
		Document doc = XmlParserUtil.createDocumentFromString(xmlString);
		Asociacion ret = null;
		NodeList nodeLst = doc.getElementsByTagName("association");
		
		for (int s = 0; s < nodeLst.getLength(); s++) {
		    Node fstNode = nodeLst.item(s);
	        if (fstNode.getNodeType() == Node.ELEMENT_NODE) {
	        	Element elPDU = (Element)fstNode;
	        	String code = XmlParserUtil.getAttribute(elPDU, "code");
	        	String url = XmlParserUtil.getAttribute(elPDU, "url");
	        	String icon = XmlParserUtil.getAttribute(elPDU, "icon");
	    		NodeList fstNm = elPDU.getChildNodes();
	    		String associationName = null;
	    		
	    		if(fstNm.getLength() > 0){
	    			associationName = ((Node) fstNm.item(0)).getNodeValue();
	    			
		        	Integer capId = getId(code);
		        	Asociacion association = null;
		        	try {
		        		Collection<Asociacion> associations = asociacionService.findByName(associationName);
		        		
		        		if(associations != null && associations.size() > 0){
		        			association = associations.iterator().next(); 
		        		}else{
		        			association = new Asociacion();
		        			association.setName(associationName);
		        			association.setUrl(url);
		        			association.setIcon(icon);
		        			logger.info("Saving Asociacion: " + associationName + " url: " + url + " icon " + icon);
		        			asociacionService.save(association);
		        		}
		        		
			        	if(capId != null && capId.equals(associationCode)){
			        		ret = association;
			        	}	        	
					} catch (ServiceException e) {
						logger.error(e.getMessage());
					}
	    		}
	        }
		}
		return ret;
	}
	
	private FormaJuridica findFormaJuridica(String orgTypeCode, String formaJuridica) {
		if(!StringUtils.hasLength(orgTypeCode))
			return null;

		Integer id = getId(orgTypeCode);
		FormaJuridica ret = null;
		List<FormaJuridica> l = null;
		try {
			l = formaJuridicaService.findByName(formaJuridica);
		} catch (ServiceException e) {
			logger.error(e.getMessage());
		}
		if(l != null && l.size() > 0){
			ret = l.get(0);
		}else{
			ret = findRestFormaJuridica(id);
		}
		return ret;
	}

	private FormaJuridica findRestFormaJuridica(Integer id) {
        HttpClient httpclient = getHttpClient();
        GetMethod get = new GetMethod(restUrl.getFormaJuridicaUrl());
		get.addRequestHeader("Accept" , "application/xml"); 
		FormaJuridica ret = null;
        try {
			int result = httpclient.executeMethod(get);
            logger.info("Response status code: " + result);
            String xmlString = get.getResponseBodyAsString(); 
            logger.info("Response body: " + xmlString);
            
           	ret = importFormaJuridica(xmlString, id);
        } catch (HttpException e) {
        	logger.error(e.getMessage());
		} catch (IOException e) {
			logger.error(e.getMessage());
		} catch (ParserConfigurationException e) {
			logger.error(e.getMessage());
		} catch (SAXException e) {
			logger.error(e.getMessage());
		} finally {
            get.releaseConnection();
        }
		return ret;
	}

	private FormaJuridica importFormaJuridica(String xmlString, Integer id) throws ParserConfigurationException, SAXException, IOException {
		Document doc = XmlParserUtil.createDocumentFromString(xmlString);
		FormaJuridica ret = null;
		NodeList nodeLst = doc.getElementsByTagName("organizationType");
		
		for (int s = 0; s < nodeLst.getLength(); s++) {
		    Node fstNode = nodeLst.item(s);
	        if (fstNode.getNodeType() == Node.ELEMENT_NODE) {
	        	Element elPDU = (Element)fstNode;
	        	String code = XmlParserUtil.getAttribute(elPDU, "code");
	    		NodeList fstNm = elPDU.getChildNodes();
	    		String organizationType = null;
	    		
	    		if(fstNm.getLength() > 0){
	    			organizationType = ((Node) fstNm.item(0)).getNodeValue();
	    			
		        	Integer orgId = getId(code);
		        	FormaJuridica formaJuridica = null;
		        	try {
		        		List<FormaJuridica> lFJ = formaJuridicaService.findByName(organizationType);
		        		
		        		if(lFJ != null && lFJ.size() > 0){
		        			formaJuridica = lFJ.get(0); 
		        		}else{
		    	        	formaJuridica = new FormaJuridica();
		    	        	formaJuridica.setName(organizationType);
		    	        	formaJuridicaService.save(formaJuridica);
		        		}
		        		
			        	if(orgId != null && orgId.equals(id)){
			        		ret = formaJuridica;
			        	}	        	
					} catch (ServiceException e) {
						logger.error(e.getMessage());
					}
	    		}
	        }
		}
		return ret;
	}
	
	
	private Provincia findProvincia(String provId, String provincia) {
		if(!StringUtils.hasLength(provId))
			return null;
		
		try {
			return provinciaService.findProvincia(satelite, provincia, Integer.parseInt(provId));
		} catch (NumberFormatException e) {
			logger.error(e.getMessage());
		} catch (ServiceException e) {
			logger.error(e.getMessage());
		}
		return null;
	}
	
	private ClasificacionOrganizacion findClasificacionOrganizacion(String orgClassCode, String organizationClasification) {
		if(!StringUtils.hasLength(orgClassCode))
			return null;

		Integer id = getId(orgClassCode);
		ClasificacionOrganizacion ret = null;
		List<ClasificacionOrganizacion> l = null;
		try {
			l = organizacionService.findClasificacionOrganizacionByName(organizationClasification);
		} catch (ServiceException e) {
			logger.error(e.getMessage());
		}
		if(l != null && l.size() > 0){
			ret = l.get(0);
		}else{
			ret = findRestClasificacionOrganizacion(id);
		}
		return ret;
	}

	private ClasificacionOrganizacion findRestClasificacionOrganizacion(Integer id) {
        HttpClient httpclient = getHttpClient();
        GetMethod get = new GetMethod(restUrl.getClassOrganizacionUrl());
		get.addRequestHeader("Accept" , "application/xml"); 
		ClasificacionOrganizacion ret = null;
        try {
			int result = httpclient.executeMethod(get);
            logger.info("Response status code: " + result);
            String xmlString = get.getResponseBodyAsString(); 
            logger.info("Response body: " + xmlString);
            
           	ret = importClasificacionOrganizacion(xmlString, id);
        } catch (HttpException e) {
        	logger.error(e.getMessage());
		} catch (IOException e) {
			logger.error(e.getMessage());
		} catch (ParserConfigurationException e) {
			logger.error(e.getMessage());
		} catch (SAXException e) {
			logger.error(e.getMessage());
		} finally {
            get.releaseConnection();
        }
		return ret;
	}
	
	private ClasificacionOrganizacion importClasificacionOrganizacion(String xmlString, Integer id) throws ParserConfigurationException, SAXException, IOException {
		Document doc = XmlParserUtil.createDocumentFromString(xmlString);
		ClasificacionOrganizacion ret = null;
		NodeList nodeLst = doc.getElementsByTagName("classification");
		
		for (int s = 0; s < nodeLst.getLength(); s++) {
		    Node fstNode = nodeLst.item(s);
	        if (fstNode.getNodeType() == Node.ELEMENT_NODE) {
	        	Element elPDU = (Element)fstNode;
	        	String code = XmlParserUtil.getAttribute(elPDU, "code");
	    		NodeList fstNm = elPDU.getChildNodes();
	    		String classOrg = null;
	    		
	    		if(fstNm.getLength() > 0){
	    			classOrg = ((Node) fstNm.item(0)).getNodeValue();
	    			
		        	Integer orgId = getId(code);
		        	ClasificacionOrganizacion clasificacionOrganizacion = null;
		        	try {
		        		List<ClasificacionOrganizacion> lCO = organizacionService.findClasificacionOrganizacionByName(classOrg);
		        		
		        		if(lCO != null && lCO.size() > 0){
		        			clasificacionOrganizacion = lCO.get(0); 
		        		}else{
		        			clasificacionOrganizacion = new ClasificacionOrganizacion();
		        			clasificacionOrganizacion.setName(classOrg);
							organizacionService.saveClasificacionOrganizacion(clasificacionOrganizacion);
		        		}
		        		
			        	if(orgId != null && orgId.equals(id)){
			        		ret = clasificacionOrganizacion;
			        	}	        	
					} catch (ServiceException e) {
						logger.error(e.getMessage());
					}
	    		}
	        }
		}
		return ret;
	}
	
	private Set<OrganizacionSede> buscarSedes(Element degationsEl, Organizacion organizacion) {
		if(degationsEl == null)
			return null;

		Set<OrganizacionSede> ret = new HashSet<OrganizacionSede>();
		
		NodeList nodeLst = degationsEl.getElementsByTagName("delegation");
		
		for (int s = 0; s < nodeLst.getLength(); s++) {
		    Node fstNode = nodeLst.item(s);
	        if (fstNode.getNodeType() == Node.ELEMENT_NODE) {
	        	Element elSede = (Element)fstNode;

      	
	        	String direccion = XmlParserUtil.getStringNodeValue(elSede, "street");
	        	String localidad = XmlParserUtil.getStringNodeValue(elSede, "locality");
	        	Element provEl = XmlParserUtil.getChildren(elSede, "province");
	        	String provId = XmlParserUtil.getAttribute(provEl, "code");	        		
        		String provincia = XmlParserUtil.getStringNodeValue(elSede, "province");        	
	        	Integer codigoPostal = XmlParserUtil.getIntegerNodeValue(elSede, "postalCode");	
				String telefonoContacto = XmlParserUtil.getStringNodeValue(elSede, "contactPhone");
				String mailContacto = XmlParserUtil.getStringNodeValue(elSede, "contactMail");
				String personaContacto = XmlParserUtil.getStringNodeValue(elSede, "contactPerson");
				float lat = XmlParserUtil.getFloatNodeValue(elSede, "locationLatitude");
				float lon = XmlParserUtil.getFloatNodeValue(elSede, "locationLongitude");
				Integer hombres = XmlParserUtil.getIntegerNodeValue(elSede, "numberOfMen");
				Integer mujeres = XmlParserUtil.getIntegerNodeValue(elSede, "numberOfWomen");
				Provincia prov = findProvincia(provId, provincia); 
				
	        	OrganizacionSede sede = null;
				try {
					sede = organizacionSedeService.findSedeByAddressLocalityProvince(direccion, localidad, prov);
				} catch (ServiceException e) {
				}
	        	if(sede == null)
		        	sede = new OrganizacionSede();
				
				sede.setDireccion(direccion);
				sede.setLocalidad(localidad);
				sede.setProvincia(prov);
				sede.setCodigoPostal(codigoPostal);
				sede.setTelefonoContacto(telefonoContacto);
				sede.setMailContacto(mailContacto);
				sede.setPersonaContacto(personaContacto);
				sede.setLatitud(lat);
				sede.setLongitud(lon);
				sede.setHombres(hombres);
				sede.setMujeres(mujeres);
				sede.setOrganizacion(organizacion);
	        	
	        	ret.add(sede);	        	
	        }
		}		
		return ret;
	}
	
	private Set<OrganizacionCapacidadOferta> buscarOfertas(Element capacitiesEl, Organizacion organizacion) {
		if(capacitiesEl == null)
			return null;

		Set<OrganizacionCapacidadOferta> ret = new HashSet<OrganizacionCapacidadOferta>();
		NodeList nodeLst = capacitiesEl.getElementsByTagName("capacity");
		
		for (int s = 0; s < nodeLst.getLength(); s++) {
		    Node fstNode = nodeLst.item(s);
	        if (fstNode.getNodeType() == Node.ELEMENT_NODE) {
	        	Element elCapacity = (Element)fstNode;
	        
	        	String capacityCode = XmlParserUtil.getAttribute(elCapacity, "capacity_code");
	        	String sectorCode = XmlParserUtil.getAttribute(elCapacity, "sector_code");
	        	String resources = XmlParserUtil.getAttribute(elCapacity, "resources");
	        	String score = XmlParserUtil.getAttribute(elCapacity, "score");
	        	String billingProportion = XmlParserUtil.getAttribute(elCapacity, "billingProportion");
	        	String description = XmlParserUtil.getAttribute(elCapacity, "description");
	        	
	        	Capacidad capacidad = buscarCapacidad(Integer.parseInt(capacityCode));
	        	Sector sector = buscarSector(Integer.parseInt(sectorCode));

	        	OrganizacionCapacidadOferta oferta = null;

	        	if(organizacion.getOfertas() != null){
	        		for(OrganizacionCapacidadOferta o : organizacion.getOfertas()){
	        			if(o.getSector().equals(sector) && 
	        			   o.getCapacidad().equals(capacidad)){
	        				oferta = o;
	        				break;
	        			}
	        		}
	        	}
	        	
	        	if(oferta == null){
	        		oferta = new OrganizacionCapacidadOferta();
		        	oferta.setOrganizacion(organizacion);
					oferta.setCapacidad(capacidad);
					oferta.setSector(sector );
	        	}
	        	oferta.setRecursos(Integer.parseInt(resources));
	        	oferta.setPuntuacion(Integer.parseInt(score));
	        	oferta.setPorcentajeFacturacion(Integer.parseInt(billingProportion));
	        	oferta.setDescripcion(description);
	        	
	        	ret.add(oferta);
	        }
		}
		
		return ret;
	}

	private Set<OrganizacionCapacidadDemanda> buscarDemandas(Element demandsEl, Organizacion organizacion) {
		Set<OrganizacionCapacidadDemanda> ret = new HashSet<OrganizacionCapacidadDemanda>();

		NodeList nodeLst = demandsEl.getElementsByTagName("demand");
		
		for (int s = 0; s < nodeLst.getLength(); s++) {
		    Node fstNode = nodeLst.item(s);
	        if (fstNode.getNodeType() == Node.ELEMENT_NODE) {
	        	Element elDemand = (Element)fstNode;
	        
	        	String capacityCode = XmlParserUtil.getAttribute(elDemand, "capacity_code");
	        	String sectorCode = XmlParserUtil.getAttribute(elDemand, "sector_code");
	        	String description = XmlParserUtil.getAttribute(elDemand, "description");

	        	Sector sector = buscarSector(Integer.parseInt(sectorCode));
	        	Capacidad capacidad = buscarCapacidad(Integer.parseInt(capacityCode));
	        	
	        	if(capacidad != null){
		        	OrganizacionCapacidadDemanda demanda = null;
		        	
		        	if(organizacion.getDemandas() != null){
		        		for(OrganizacionCapacidadDemanda d : organizacion.getDemandas()){
		        			if(d.getSector().equals(sector) && 
		        			   d.getCapacidad().equals(capacidad)){
		        				demanda = d;
		        				break;
		        			}
		        		}
		        	}
	
		        	if(demanda == null){
		        		demanda = new OrganizacionCapacidadDemanda();
			        	demanda.setOrganizacion(organizacion);
						demanda.setCapacidad(capacidad );
						demanda.setSector(sector);
		        	}
		        	demanda.setDescripcion(description);
		        	
		        	ret.add(demanda);
	        	}else{
	        		if(logger.isTraceEnabled()){
	        			logger.trace("No se ha agregado la Demanda con código de capacidad: "+capacityCode);
	        		}
	        	}
	        }
		}
		
		
		return ret;
	}

	private Sector buscarSector(int sectorCode) {
		HttpClient httpclient = getHttpClient();
        GetMethod get = new GetMethod(restUrl.getSectorUrl());
		get.addRequestHeader("Accept" , "application/xml"); 
		Sector ret = null;
        try {
            int result = httpclient.executeMethod(get);
            logger.info("Response status code: " + result);
            String xmlString = get.getResponseBodyAsString(); 
            logger.info("Response body: " + xmlString);
            
           	ret = importSector(xmlString, sectorCode);
        } catch (HttpException e) {
        	logger.error(e.getMessage());
		} catch (IOException e) {
			logger.error(e.getMessage());
		} catch (ParserConfigurationException e) {
			logger.error(e.getMessage());
		} catch (SAXException e) {
			logger.error(e.getMessage());
		} finally {
            get.releaseConnection();
        }
		return ret;
	}

	private Sector importSector(String xmlString, Integer id) throws ParserConfigurationException, SAXException, IOException {
		Document doc = XmlParserUtil.createDocumentFromString(xmlString);
		Sector ret = null;
		NodeList nodeLst = doc.getElementsByTagName("sector");
		
		for (int s = 0; s < nodeLst.getLength(); s++) {
		    Node fstNode = nodeLst.item(s);
	        if (fstNode.getNodeType() == Node.ELEMENT_NODE) {
	        	Element elPDU = (Element)fstNode;
	        	String code = XmlParserUtil.getAttribute(elPDU, "code");
	    		NodeList fstNm = elPDU.getChildNodes();
	    		String sectorName = null;
	    		
	    		if(fstNm.getLength() > 0){
	    			String tmp = ((Node) fstNm.item(0)).getNodeValue();
	    			byte[] utf8 = tmp.getBytes("UTF-8"); 
	    			sectorName = new String(utf8, "UTF-8"); 	    			
	    			
		        	Integer capId = getId(code);
		        	Sector sector = null;
		        	try {
		        		Collection<Sector> sectores = sectorService.findByName(sectorName);
		        		
		        		if(sectores != null && sectores.size() > 0){
		        			sector = sectores.iterator().next(); 
		        		}else{
		    	        	sector = new Sector();
		    	        	sector.setName(sectorName);
		    	        	sectorService.save(sector);
		        		}
		        		
			        	if(capId != null && capId.equals(id)){
			        		ret = sector;
			        	}	        	
					} catch (ServiceException e) {
						logger.error(e.getMessage());
					}
	    		}
	        }
		}
		return ret;
	}

	private Capacidad buscarCapacidad(int capacityCode) {
		HttpClient httpclient = getHttpClient();
        GetMethod get = new GetMethod(restUrl.getCapacidadUrl());
		get.addRequestHeader("Accept" , "application/xml"); 
		Capacidad ret = null;
        try {
            int result = httpclient.executeMethod(get);
            if(logger.isTraceEnabled())
            	logger.trace("Response status code: " + result);
            String xmlString = get.getResponseBodyAsString(); 
            if(logger.isTraceEnabled())
            	logger.trace("Response body: " + xmlString);
            
           	ret = importCapacidad(xmlString, capacityCode);
        } catch (HttpException e) {
        	logger.error(e.getMessage());
		} catch (IOException e) {
			logger.error(e.getMessage());
		} catch (ParserConfigurationException e) {
			logger.error(e.getMessage());
		} catch (SAXException e) {
			logger.error(e.getMessage());
		} finally {
            get.releaseConnection();
        }
		return ret;
	}

	private Capacidad importCapacidad(String xmlString, Integer capacityId) throws ParserConfigurationException, SAXException, IOException {
		Document doc = XmlParserUtil.createDocumentFromString(xmlString);
		Capacidad ret = null;
		NodeList nodeLst = doc.getElementsByTagName("capacity");
		
		for (int s = 0; s < nodeLst.getLength(); s++) {
		    Node fstNode = nodeLst.item(s);
	        if (fstNode.getNodeType() == Node.ELEMENT_NODE) {
	        	Element elPDU = (Element)fstNode;
	        	String code = XmlParserUtil.getAttribute(elPDU, "code");
	        	String category = XmlParserUtil.getAttribute(elPDU, "category");
	    		NodeList fstNm = elPDU.getChildNodes();
	    		String capacidadName = null;
	    		
	    		if(fstNm.getLength() > 0){
	    			capacidadName = ((Node) fstNm.item(0)).getNodeValue();
	    			
		        	Integer capId = getId(code);
		        	Capacidad capacidad = null;
		        	try {
		        		List<Capacidad> capacidades = organizacionService.findCapacidadByName(capacidadName);
		        		
		        		if(capacidades != null && capacidades.size() > 0){
		        			capacidad = capacidades.get(0); 
		        		}else{
		    	        	capacidad = new Capacidad();
		    	        	capacidad.setName(capacidadName);
		    	        	if(StringUtils.hasLength(category))
		    	        		capacidad.setCategoria(category);
							organizacionService.saveCapacidad(capacidad);
		        		}
		        		
			        	if(capId != null && capId.equals(capacityId)){
			        		ret = capacidad;
			        	}	        	
					} catch (ServiceException e) {
						logger.error(e.getMessage());
					}
	    		}
	        }
		}
		
		if(ret != null){
			if(logger.isTraceEnabled())
				logger.trace("Se devuelve la Capacidad: " + ret);
			return ret;
		}
		if(logger.isTraceEnabled())
			logger.trace("No se ha encontrado la Capacidad con Id: " + capacityId);
		return null;
	}
	
	
	public static HttpClient getHttpClient(){
		HttpClient httpclient = new HttpClient();
//		httpclient.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, HTTP_CONTEXT_CHARSET_ISO_8859_1);
		httpclient.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, HTTP_CONTEXT_CHARSET_UTF_8);

		return httpclient;
	}
	private static Integer getId(String id){
		Integer retId = null;
		try {
			retId = Integer.parseInt(id);
		} catch (NumberFormatException e) {
			logger.error(e.getMessage());
			retId = 0;
		}
		return retId;
	}
	
}
