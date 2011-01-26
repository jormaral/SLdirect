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
package net.neurowork.cenatic.centraldir.model.satelite;


import java.io.IOException;
import java.util.Map;
import java.util.Set;

import net.neurowork.cenatic.centraldir.model.NamedEntity;
import net.neurowork.cenatic.centraldir.util.SerializedPhpParser;

/**
 *
 * @author Alejandro Sanchez Acosta <asanchez@neurowork.net> http://www.neurowork.net
 * @since 08/11/2010
 */
public class Organizacion extends NamedEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = -376506407894320372L;
	/**
	 * Descripcion de la Organización 
	 */
	private String descripcion;
	/**
	 * URL de la Web de la Organización
	 */
	private String web;
	/**
	 * URL del Logo de la Organización
	 */
	private String logoUrl;
	/**
	 * Dirección de la Organización
	 */
	private String direccion;
	/**
	 * Certficaciones de Calidad que posee la Organización.
	 */
	private String certificacionesCalidad;
	/**
	 * Partners de la Organiazación.
	 */
	private String partners;
	/**
	 * Grupo Empresarial al que pertenece.
	 */
	private String grupoEmpresarial;
	private String participacionImasD;
	private String relacionesComunidad;
	private String localidad;
	private String username;
	private String password;
	private String hashCreacion;
	private String telefono;
	private String email;
	private String cif;
	private Integer codigoPostal;
	private Integer anoConstitucion;	
	/**
	 * Directorio en el cual se encuetra.
	 */
	private Directorio directorio;
	/**
	 * Forma Jurídica de la Organización. Ej. SA, SL, etc.
	 */
	private FormaJuridica formaJuridica;
	/**
	 * Clase de Empresa.
	 */
	private ClaseEmpresa claseEmpresa;
	private ClasificacionOrganizacion clasificacionOrganizacion;
	private Provincia provincia;
	/**
	 * ¿Está certificada por CENATIC? 
	 */
	private Boolean certificacionCenatic;
	/**
	 * ¿Realiza actividades de Investigación y Desarrollo?
	 */
	private Boolean actividadesImasD;
	/**
	 * Título de la Noticia para el Carrusel de Noticias 
	 */
	private String newsTitle;
	/**
	 * Cuerpo de la Noticia para el Carrusel de Noticias
	 */
	private String newsBody;
	/**
	 * Organizaciones con la que está asociada.
	 */
	private Set<OrganizacionAsociacion> organizacionAsociacions;
	/**
	 * Satelites en los se encuentra esta Organización. 
	 */
	private Set<OrganizacionSatelite> satelites;
	/**
	 * Sedes que posee la Organización. 
	 */
	private Set<OrganizacionSede> sedes;
	/**
	 * Demanda de Servicios de la Organización.
	 */
	private Set<OrganizacionCapacidadDemanda> demandas;
	/**
	 * Servicios que Oferta la Organización.
	 */
	private Set<OrganizacionCapacidadOferta> ofertas;
	/**
	 * Eventos publicados por la Organización.
	 */
	private Set<OrganizacionEvento> eventos;
	private String xmlHash;
	
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getWeb() {
		return web;
	}
	public void setWeb(String web) {
		this.web = web;
	}
	public String getLogoUrl() {
		return logoUrl;
	}
	public void setLogoUrl(String logoUrl) {
		this.logoUrl = logoUrl;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public String getCertificacionesCalidad() {
		return certificacionesCalidad;
	}
	public void setCertificacionesCalidad(String certificacionesCalidad) {
		this.certificacionesCalidad = certificacionesCalidad;
	}
	
	@SuppressWarnings("unchecked")
	public static String decoded(String codedString){
		if(codedString == null || codedString.isEmpty())
			return "";
		
		StringBuffer retBuf = new StringBuffer();
		try { 
			String input = codedString;
			// Convert base64 string to a byte array 
			byte[] buf = new sun.misc.BASE64Decoder().decodeBuffer(input);
			String x = new String(buf);
			SerializedPhpParser serializedPhpParser = new SerializedPhpParser(x);
			Object result = serializedPhpParser.parse();
			
			if(result != null){
				Map<Object,Object> map = (Map<Object,Object>)result;
				String sep = ""; 
				for(Object key : map.keySet()){
					Object value = map.get(key);
					retBuf.append(sep);
					retBuf.append(value);
					sep = ", ";
				}
			}
		} catch (IOException e) {
		}
		return retBuf.toString();		
	}
	
	public String getPartners() {
		return partners;
	}
	public void setPartners(String partners) {
		this.partners = partners;
	}
	public String getGrupoEmpresarial() {
		return grupoEmpresarial;
	}
	public void setGrupoEmpresarial(String grupoEmpresarial) {
		this.grupoEmpresarial = grupoEmpresarial;
	}
	public String getParticipacionImasD() {
		return participacionImasD;
	}
	public void setParticipacionImasD(String participacionImasD) {
		this.participacionImasD = participacionImasD;
	}
	public String getRelacionesComunidad() {
		return relacionesComunidad;
	}
	public void setRelacionesComunidad(String relacionesComunidad) {
		this.relacionesComunidad = relacionesComunidad;
	}
	public String getLocalidad() {
		return localidad;
	}
	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getHashCreacion() {
		return hashCreacion;
	}
	public void setHashCreacion(String hashCreacion) {
		this.hashCreacion = hashCreacion;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getCif() {
		return cif;
	}
	public void setCif(String cif) {
		this.cif = cif;
	}
	public Integer getCodigoPostal() {
		return codigoPostal;
	}
	public void setCodigoPostal(Integer codigoPostal) {
		this.codigoPostal = codigoPostal;
	}
	public Integer getAnoConstitucion() {
		return anoConstitucion;
	}
	public void setAnoConstitucion(Integer anoConstitucion) {
		this.anoConstitucion = anoConstitucion;
	}
	public Directorio getDirectorio() {
		return directorio;
	}
	public void setDirectorio(Directorio directorio) {
		this.directorio = directorio;
	}
	public FormaJuridica getFormaJuridica() {
		return formaJuridica;
	}
	public void setFormaJuridica(FormaJuridica formaJuridica) {
		this.formaJuridica = formaJuridica;
	}
	public ClaseEmpresa getClaseEmpresa() {
		return claseEmpresa;
	}
	public void setClaseEmpresa(ClaseEmpresa claseEmpresa) {
		this.claseEmpresa = claseEmpresa;
	}
	public ClasificacionOrganizacion getClasificacionOrganizacion() {
		return clasificacionOrganizacion;
	}
	public void setClasificacionOrganizacion(
			ClasificacionOrganizacion clasificacionOrganizacion) {
		this.clasificacionOrganizacion = clasificacionOrganizacion;
	}
	public Provincia getProvincia() {
		return provincia;
	}
	public void setProvincia(Provincia provincia) {
		this.provincia = provincia;
	}
	public Boolean getCertificacionCenatic() {
		return certificacionCenatic;
	}
	public void setCertificacionCenatic(Boolean certificacionCenatic) {
		this.certificacionCenatic = certificacionCenatic;
	}
	public Boolean getActividadesImasD() {
		return actividadesImasD;
	}
	public void setActividadesImasD(Boolean actividadesImasD) {
		this.actividadesImasD = actividadesImasD;
	}
	public String getNewsTitle() {
		return newsTitle;
	}
	public void setNewsTitle(String newsTitle) {
		this.newsTitle = newsTitle;
	}
	public String getNewsBody() {
		return newsBody;
	}
	public void setNewsBody(String newsBody) {
		this.newsBody = newsBody;
	}
	public Set<OrganizacionAsociacion> getOrganizacionAsociacions() {
		return organizacionAsociacions;
	}
	public void setOrganizacionAsociacions(
			Set<OrganizacionAsociacion> organizacionAsociacions) {
		this.organizacionAsociacions = organizacionAsociacions;
	}

	public String getXmlHash() {
		return xmlHash;
	}
	public void setXmlHash(String xmlHash) {
		this.xmlHash = xmlHash;
	}

	public Set<OrganizacionSede> getSedes() {
		return sedes;
	}
	public void setSedes(Set<OrganizacionSede> sedes) {
		this.sedes = sedes;
	}
	
	public Set<OrganizacionCapacidadDemanda> getDemandas() {
		return demandas;
	}
	public void setDemandas(Set<OrganizacionCapacidadDemanda> demandas) {
		this.demandas = demandas;
	}
	public Set<OrganizacionCapacidadOferta> getOfertas() {
		return ofertas;
	}
	public void setOfertas(Set<OrganizacionCapacidadOferta> ofertas) {
		this.ofertas = ofertas;
	}
	public int getHombres(){
		if(sedes == null)
			return 0;
		int hombres = 0;
		for(OrganizacionSede sede : sedes){
			hombres+= sede.getHombres();
		}
		return hombres;
	}
	public int getMujeres(){
		if(sedes == null)
			return 0;
		int mujeres = 0;
		for(OrganizacionSede sede : sedes){
			mujeres += sede.getMujeres();
		}
		return mujeres;
	}
	public int getEmpleados(){
		return getHombres()+getMujeres();
	}
	public Set<OrganizacionEvento> getEventos() {
		return eventos;
	}
	public void setEventos(Set<OrganizacionEvento> eventos) {
		this.eventos = eventos;
	}
	public Set<OrganizacionSatelite> getSatelites() {
		return satelites;
	}
	public void setSatelites(Set<OrganizacionSatelite> satelites) {
		this.satelites = satelites;
	}	
}
