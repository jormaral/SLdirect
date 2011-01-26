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
package net.neurowork.cenatic.centraldir.util;

import java.text.SimpleDateFormat;

import net.neurowork.cenatic.centraldir.model.satelite.Organizacion;
import net.neurowork.cenatic.centraldir.model.satelite.OrganizacionAsociacion;
import net.neurowork.cenatic.centraldir.model.satelite.OrganizacionCapacidadDemanda;
import net.neurowork.cenatic.centraldir.model.satelite.OrganizacionCapacidadOferta;
import net.neurowork.cenatic.centraldir.model.satelite.OrganizacionEvento;
import net.neurowork.cenatic.centraldir.model.satelite.OrganizacionSede;

/**
 *
 * @author Alejandro Sanchez Acosta <asanchez@neurowork.net> http://www.neurowork.net
 * @since 15/12/2010
 */
public class XmlPushCreator {
	private static XmlPushCreator instance;
	private static SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd"); 
	
	
	private XmlPushCreator(){
		super();
	}
	
	public String getPushXml(Organizacion organizacion){
		StringBuffer buff = new StringBuffer();
		buff.append("<?xml version=\"1.0\" encoding=\"iso-8859-1\"?>");
		buff.append("<organization>");
		buff.append("<legalID>");
		buff.append(organizacion.getCif());
		buff.append("</legalID>");
		
		if(organizacion.getName() != null){		
			buff.append("<name>");
			buff.append(organizacion.getName());
			buff.append("</name>");
		}else{
			buff.append("<name/>");
		}
		if(organizacion.getDescripcion() != null){
			buff.append("<description>");
			buff.append(organizacion.getDescripcion()); 
			buff.append("</description>");
		}else{
			buff.append("<description/>");
		}
		if(organizacion.getWeb() != null){
			buff.append("<web>");
			buff.append(organizacion.getWeb());
			buff.append("</web>");
		}else{
			buff.append("<web/>");
		}
		if(organizacion.getNewsTitle() != null){
			buff.append("<news_title>");
			buff.append(organizacion.getNewsTitle());
			buff.append("</news_title>");
		}else{
			buff.append("<news_title/>");
		}
		if(organizacion.getNewsBody() != null){
			buff.append("<news_body>");
			buff.append(organizacion.getNewsBody());
			buff.append("</news_body>");
		}else{
			buff.append("<news_body/>");
		}
		if(organizacion.getLogoUrl() != null){
			buff.append("<logo_url>");
			buff.append(organizacion.getLogoUrl());
			buff.append("</logo_url>");
		}else{
			buff.append("<logo_url/>");
		}
		if(organizacion.getClaseEmpresa() != null){
			buff.append("<organizationClass code=\"");
			buff.append(organizacion.getClaseEmpresa().getId());
			buff.append("\">");
			buff.append(organizacion.getClaseEmpresa().getId());
			buff.append("</organizationClass>");
		}else{
			buff.append("<organizationClass/>");
		}
		
		buff.append("<HQstreet>");
		buff.append(organizacion.getDireccion());
		buff.append("</HQstreet>");
		buff.append("<HQlocality>");
		buff.append(organizacion.getLocalidad());
		buff.append("</HQlocality>");
		buff.append("<HQprovince code=\"");
		buff.append(organizacion.getProvincia().getId());
		buff.append("\">");
		buff.append(organizacion.getProvincia());
		buff.append("</HQprovince>");
		buff.append("<HQpostalCode>");
		buff.append(organizacion.getCodigoPostal());
		buff.append("</HQpostalCode>");
		buff.append("<telephone>");
		buff.append(organizacion.getTelefono());
		buff.append("</telephone>");
		buff.append("<email>");
		buff.append(organizacion.getEmail());
		buff.append("</email>");
		buff.append("<year>");
		buff.append(organizacion.getAnoConstitucion());
		buff.append("</year>");
		buff.append("<organizationType code=\"");
		buff.append(organizacion.getFormaJuridica().getId());
		buff.append("\">");
		buff.append(organizacion.getFormaJuridica());
		buff.append("</organizationType>");
		buff.append("<organizationClasification code=\"");
		buff.append(organizacion.getClasificacionOrganizacion().getId());
		buff.append("\">");
		buff.append(organizacion.getClasificacionOrganizacion());
		buff.append("</organizationClasification>");
		buff.append("<researchAndDevelopmentActivities>");
		buff.append(organizacion.getActividadesImasD());
		buff.append("</researchAndDevelopmentActivities>");
		buff.append("<cenaticCertification>");
		buff.append(organizacion.getCertificacionCenatic());
		buff.append("</cenaticCertification>");
		buff.append("<enpresarialGroup>");
		buff.append(organizacion.getGrupoEmpresarial());
		buff.append("</enpresarialGroup>");
		buff.append("<qualityCertifications>");
		buff.append(organizacion.getCertificacionesCalidad());
		buff.append("</qualityCertifications>");
		buff.append("<partners>");
		buff.append(organizacion.getPartners());
		buff.append("</partners>");
		buff.append("<researchAndDevelopmentProgrammes>");
		buff.append(organizacion.getParticipacionImasD());
		buff.append("</researchAndDevelopmentProgrammes>");
		buff.append("<communityRelationships>");
		buff.append(organizacion.getRelacionesComunidad());
		buff.append("</communityRelationships>");
		buff.append("<delegations>");
		
		for(OrganizacionSede sede : organizacion.getSedes()){
			buff.append("<delegation>");
			buff.append("<street>");
			buff.append(sede.getDireccion());
			buff.append("</street>");
			buff.append("<locality>");
			buff.append(sede.getLocalidad());
			buff.append("</locality>");
			buff.append("<province code=\"");
			buff.append(sede.getProvincia().getId());
			buff.append("\">");
			buff.append("</province>");
			buff.append("<postalCode>");
			buff.append(sede.getCodigoPostal());
			buff.append("</postalCode>");
			buff.append("<contactPhone>");
			buff.append(sede.getTelefonoContacto());
			buff.append("</contactPhone>");
			buff.append("<contactMail>");
			buff.append(sede.getMailContacto());
			buff.append("</contactMail>");
			buff.append("<contactPerson>");
			buff.append(sede.getPersonaContacto());
			buff.append("</contactPerson>");
			buff.append("<locationLatitude>");
			buff.append(sede.getLatitud());
			buff.append("</locationLatitude>");
			buff.append("<locationLongitude>");
			buff.append(sede.getLongitud());
			buff.append("</locationLongitude>");
			buff.append("<numberOfMen>");
			buff.append(sede.getHombres());
			buff.append("</numberOfMen>");
			buff.append("<numberOfWomen>");
			buff.append(sede.getMujeres());
			buff.append("</numberOfWomen>");
			buff.append("</delegation>");			
		}
		buff.append("</delegations>");
		buff.append("<events>");
		
		for(OrganizacionEvento evento : organizacion.getEventos()){
			buff.append("<event>");
			buff.append("<localization>");
			buff.append(evento.getEvento().getLocalizacion());
			buff.append("</localization>");
			buff.append("<title>");
			buff.append(evento.getEvento().getTitulo());
			buff.append("</title>");
			buff.append("<description>");
			buff.append(evento.getEvento().getDescripcion());
			buff.append("</description>");
			buff.append("<date>");
			buff.append(formatter.format(evento.getEvento().getFecha()));
			buff.append("</date>");
			buff.append("</event>");
		}
		buff.append("</events>");
		buff.append("<organizationEvents>");
//";
//foreach ($eventosSuscritos as $evento) {
//			$data .= "    <organizationEvent>
//";
//			$data .= "      <event_id>" . $evento->evento_id->id . "</event_id>
//";
//			$data .= "    </organizationEvent>
//";
//		}
		buff.append("</organizationEvents>");
		buff.append("<capacities>");
		for(OrganizacionCapacidadOferta oferta : organizacion.getOfertas()){
			buff.append("<capacity capacity_code=\"");
			buff.append(oferta.getCapacidad().getId());
			buff.append("\" sector_code=\"");
			buff.append(oferta.getCapacidad().getId());
			buff.append("\" resources=\"");
			buff.append(oferta.getRecursos());
			buff.append("\" score=\"");
			buff.append(oferta.getPuntuacion());
			buff.append("\" billingProportion=\"");
			buff.append(oferta.getPorcentajeFacturacion());
			buff.append("\" description=\"");
			buff.append(oferta.getCapacidad().getDescripcion());
			buff.append("\" />");
		}
		buff.append("</capacities>");
		buff.append("<demands>");
		for(OrganizacionCapacidadDemanda demanda : organizacion.getDemandas()){
			buff.append("<demand capacity_code=\"");
			buff.append(demanda.getCapacidad().getId());
			buff.append("\" sector_code=\"");
			buff.append(demanda.getSector().getId());
			buff.append("\" description=\"");
			buff.append(demanda.getDescripcion());
			buff.append("\" />");			
		}
		buff.append("</demands>");
		buff.append("<associationMemberships>");
		for(OrganizacionAsociacion asociacion : organizacion.getOrganizacionAsociacions()){
			buff.append("<associationMembership code=\"");
			buff.append(asociacion.getAsociacion().getIcon());
			buff.append("\" />");
		}		
		buff.append("</associationMemberships>");
		buff.append("<username>");
		buff.append(organizacion.getUsername());
		buff.append("</username>");
		buff.append("<password>");
		buff.append(organizacion.getPassword());
		buff.append("</password>");
		buff.append("</organization>");		
		
		return buff.toString();
	}
	
	public static XmlPushCreator getInstance(){
		if(instance == null)
			instance = new XmlPushCreator();
		return instance;
	}

}
