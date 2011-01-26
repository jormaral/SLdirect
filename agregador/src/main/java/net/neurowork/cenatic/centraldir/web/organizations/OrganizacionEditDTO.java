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

import java.util.Set;

import net.neurowork.cenatic.centraldir.model.satelite.ClasificacionOrganizacion;
import net.neurowork.cenatic.centraldir.model.satelite.FormaJuridica;
import net.neurowork.cenatic.centraldir.model.satelite.Organizacion;
import net.neurowork.cenatic.centraldir.model.satelite.OrganizacionAsociacion;
import net.neurowork.cenatic.centraldir.model.satelite.OrganizacionCapacidadDemanda;
import net.neurowork.cenatic.centraldir.model.satelite.OrganizacionCapacidadOferta;
import net.neurowork.cenatic.centraldir.model.satelite.OrganizacionEvento;
import net.neurowork.cenatic.centraldir.model.satelite.OrganizacionSede;
import net.neurowork.cenatic.centraldir.model.satelite.Provincia;

import org.springframework.util.StringUtils;

/**
 *
 * @author Alejandro Sanchez Acosta <asanchez@neurowork.net> http://www.neurowork.net
 * @since 22/12/2010
 */
public class OrganizacionEditDTO {
	private Integer id;
	private String name;
	private String web;
	private String logoUrl;
	private ClasificacionOrganizacion clasificacionOrganizacion;
	private FormaJuridica formaJuridica;
	private String descripcion;
	private String cif;
	private Integer anoConstitucion;
	private int empleados;
	private int hombres;
	private int mujeres;
	private String direccion;
	private Integer codigoPostal;
	private String localidad;
	private Provincia provincia;
	private String telefono;
	private String email;
	private String newsTitle;
	private String newsBody;
	private Boolean certificacionCenatic;
	private Boolean certificacionCalidad;
	private Boolean tienePartners;
	private Boolean actividadesImasD;
	private Boolean participaImasD;
	private Boolean tieneRelacionesComunidad;
	private Boolean perteneceGrupoEmpresarial;
	
	private String username;
	private String password;
	
	private Set<OrganizacionAsociacion> organizacionAsociacions;
	private Set<OrganizacionSede> sedes;
	private Set<OrganizacionCapacidadDemanda> demandas;
	private Set<OrganizacionCapacidadOferta> ofertas;
	private Set<OrganizacionEvento> eventos;
	
	private String certificacionesCalidad;
	private String partners;
	private String grupoEmpresarial;
	private String participacionImasD;
	private String relacionesComunidad;
	
	public OrganizacionEditDTO(final Organizacion organizacion) {
		super();
		this.id = organizacion.getId();
		this.logoUrl = organizacion.getLogoUrl();
		this.name = organizacion.getName();
		this.web = organizacion.getWeb();
		this.clasificacionOrganizacion = organizacion.getClasificacionOrganizacion();
		this.formaJuridica = organizacion.getFormaJuridica();
		this.descripcion = organizacion.getDescripcion();
		this.cif = organizacion.getCif();
		this.anoConstitucion = organizacion.getAnoConstitucion();
		this.empleados = organizacion.getEmpleados();
		this.hombres = organizacion.getHombres();
		this.mujeres = organizacion.getMujeres();
		this.direccion = organizacion.getDireccion();
		this.codigoPostal = organizacion.getCodigoPostal();
		this.localidad = organizacion.getLocalidad();
		this.provincia = organizacion.getProvincia();
		this.telefono = organizacion.getTelefono();
		this.email = organizacion.getEmail();
		this.newsTitle = organizacion.getNewsTitle();
		this.newsBody = organizacion.getNewsBody();
		this.certificacionCenatic = organizacion.getCertificacionCenatic();		
		
		this.certificacionCalidad = StringUtils.hasLength(organizacion.getCertificacionesCalidad());
		this.certificacionesCalidad = organizacion.getCertificacionesCalidad();
		
		this.actividadesImasD = organizacion.getActividadesImasD();
		
		this.tienePartners = StringUtils.hasLength(organizacion.getPartners());
		this.partners = organizacion.getPartners();
		
		this.participaImasD = StringUtils.hasLength(organizacion.getParticipacionImasD());
		this.setParticipacionImasD(organizacion.getParticipacionImasD());
		
		this.tieneRelacionesComunidad = StringUtils.hasLength(organizacion.getRelacionesComunidad());
		this.relacionesComunidad = organizacion.getRelacionesComunidad();
		
		this.perteneceGrupoEmpresarial = StringUtils.hasLength(organizacion.getGrupoEmpresarial());
		this.grupoEmpresarial = organizacion.getGrupoEmpresarial();
			
		this.username = organizacion.getUsername();
		this.password = organizacion.getPassword();
		
		this.organizacionAsociacions = organizacion.getOrganizacionAsociacions();
		this.sedes = organizacion.getSedes();
		this.demandas = organizacion.getDemandas();
		this.ofertas = organizacion.getOfertas();
		this.eventos = organizacion.getEventos();
	}

	public String getLogoUrl() {
		return logoUrl;
	}

	public void setLogoUrl(String logoUrl) {
		this.logoUrl = logoUrl;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getWeb() {
		return web;
	}

	public void setWeb(String web) {
		this.web = web;
	}

	public ClasificacionOrganizacion getClasificacionOrganizacion() {
		return clasificacionOrganizacion;
	}

	public void setClasificacionOrganizacion(
			ClasificacionOrganizacion clasificacionOrganizacion) {
		this.clasificacionOrganizacion = clasificacionOrganizacion;
	}

	public FormaJuridica getFormaJuridica() {
		return formaJuridica;
	}

	public void setFormaJuridica(FormaJuridica formaJuridica) {
		this.formaJuridica = formaJuridica;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getCif() {
		return cif;
	}

	public void setCif(String cif) {
		this.cif = cif;
	}

	public Integer getAnoConstitucion() {
		return anoConstitucion;
	}

	public void setAnoConstitucion(Integer anoConstitucion) {
		this.anoConstitucion = anoConstitucion;
	}

	public int getEmpleados() {
		return empleados;
	}

	public void setEmpleados(int empleados) {
		this.empleados = empleados;
	}

	public int getHombres() {
		return hombres;
	}

	public void setHombres(int hombres) {
		this.hombres = hombres;
	}

	public int getMujeres() {
		return mujeres;
	}

	public void setMujeres(int mujeres) {
		this.mujeres = mujeres;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public Integer getCodigoPostal() {
		return codigoPostal;
	}

	public void setCodigoPostal(Integer codigoPostal) {
		this.codigoPostal = codigoPostal;
	}

	public String getLocalidad() {
		return localidad;
	}

	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}

	public Provincia getProvincia() {
		return provincia;
	}

	public void setProvincia(Provincia provincia) {
		this.provincia = provincia;
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

	public Boolean getCertificacionCenatic() {
		return certificacionCenatic;
	}

	public void setCertificacionCenatic(Boolean certificacionCenatic) {
		this.certificacionCenatic = certificacionCenatic;
	}

	public Boolean getCertificacionCalidad() {
		return certificacionCalidad;
	}

	public void setCertificacionCalidad(Boolean certificacionCalidad) {
		this.certificacionCalidad = certificacionCalidad;
	}

	public Boolean getActividadesImasD() {
		return actividadesImasD;
	}

	public void setActividadesImasD(Boolean actividadesImasD) {
		this.actividadesImasD = actividadesImasD;
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

	public Set<OrganizacionAsociacion> getOrganizacionAsociacions() {
		return organizacionAsociacions;
	}

	public void setOrganizacionAsociacions(
			Set<OrganizacionAsociacion> organizacionAsociacions) {
		this.organizacionAsociacions = organizacionAsociacions;
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

	public Set<OrganizacionEvento> getEventos() {
		return eventos;
	}

	public void setEventos(Set<OrganizacionEvento> eventos) {
		this.eventos = eventos;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Boolean getParticipaImasD() {
		return participaImasD;
	}

	public void setParticipaImasD(Boolean participaImasD) {
		this.participaImasD = participaImasD;
	}

	public Boolean getTieneRelacionesComunidad() {
		return tieneRelacionesComunidad;
	}

	public void setTieneRelacionesComunidad(Boolean tieneRelacionesComunidad) {
		this.tieneRelacionesComunidad = tieneRelacionesComunidad;
	}

	public Boolean getPerteneceGrupoEmpresarial() {
		return perteneceGrupoEmpresarial;
	}

	public void setPerteneceGrupoEmpresarial(Boolean perteneceGrupoEmpresarial) {
		this.perteneceGrupoEmpresarial = perteneceGrupoEmpresarial;
	}

	public String toString(){
		return name;
	}

	public Boolean getTienePartners() {
		return tienePartners;
	}

	public void setTienePartners(Boolean tienePartners) {
		this.tienePartners = tienePartners;
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

	public void setParticipacionImasD(String participacionImasD) {
		this.participacionImasD = participacionImasD;
	}

	public String getParticipacionImasD() {
		return participacionImasD;
	}

	public void setRelacionesComunidad(String relacionesComunidad) {
		this.relacionesComunidad = relacionesComunidad;
	}

	public String getRelacionesComunidad() {
		return relacionesComunidad;
	}

	public void setCertificacionesCalidad(String certificacionesCalidad) {
		this.certificacionesCalidad = certificacionesCalidad;
	}

	public String getCertificacionesCalidad() {
		return certificacionesCalidad;
	}
}
