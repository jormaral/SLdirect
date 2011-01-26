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
package net.neurowork.cenatic.centraldir.service;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import net.neurowork.cenatic.centraldir.model.Satelite;
import net.neurowork.cenatic.centraldir.model.dto.EventDTO;
import net.neurowork.cenatic.centraldir.model.dto.NewsDTO;
import net.neurowork.cenatic.centraldir.model.satelite.Asociacion;
import net.neurowork.cenatic.centraldir.model.satelite.Capacidad;
import net.neurowork.cenatic.centraldir.model.satelite.ClasificacionOrganizacion;
import net.neurowork.cenatic.centraldir.model.satelite.Evento;
import net.neurowork.cenatic.centraldir.model.satelite.FormaJuridica;
import net.neurowork.cenatic.centraldir.model.satelite.Organizacion;
import net.neurowork.cenatic.centraldir.model.satelite.OrganizacionCapacidadDemanda;
import net.neurowork.cenatic.centraldir.model.satelite.Provincia;
import net.neurowork.cenatic.centraldir.model.satelite.Sector;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Alejandro Sanchez Acosta <asanchez@neurowork.net> http://www.neurowork.net
 * @since 14/11/2010
 */
public interface OrganizacionService extends CenaticService{
	String SERVICE_NAME = "OrganizacionService";

	/**
	 * Devuelve todas las Organizaciones
	 * @return
	 * @throws ServiceException
	 */
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	Collection<Organizacion> getOrganizaciones() throws ServiceException;
	/**
	 * Almacena una Organización
	 * @param organizacion
	 * @throws ServiceException
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	void saveOrganization(Organizacion organizacion) throws ServiceException;

	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	Organizacion findById(Integer id) throws ServiceException;
	
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	Organizacion findByHash(String xmlHash) throws ServiceException;
	
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	Organizacion findByCIF(String cif) throws ServiceException;

	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	List<Organizacion> findByClasificacionOrganizacion(ClasificacionOrganizacion clasificacionOrganizacion) throws ServiceException;
	

	/**
	 * Devuelve todas las <code>Organizaciones</code> de una <code>provincia</code>.
	 * @param provincia
	 * @return
	 * @throws ServiceException
	 */
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	Collection<Organizacion> findByProvincia(Provincia provincia) throws ServiceException;

	/**
	 * Devuelve todas las <code>Organizaciones</code> de una <code>asociacion</code>.
	 * @param asociacion
	 * @return
	 * @throws ServiceException
	 */
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	Collection<Organizacion> findByAsociacion(Asociacion asociacion) throws ServiceException;

	/**
	 * Devuelve todas las <code>Organizaciones</code> de un <code>sector</code>.
	 * @param sector
	 * @return
	 * @throws ServiceException
	 */
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	Set<Organizacion> findBySector(Sector sector) throws ServiceException;

	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	Set<Organizacion> findByDemandaSector(Sector sector) throws ServiceException;
	
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	Set<Organizacion> findByOfertaSector(Sector sector) throws ServiceException;

	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	Set<Organizacion> findByDemandaCapacidad(Capacidad capacidad) throws ServiceException;
	
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	Set<Organizacion> findByOfertaCapacidad(Capacidad capacidad) throws ServiceException;

	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	Set<NewsDTO> getNews() throws ServiceException;
	
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	Set<EventDTO> getEvents() throws ServiceException;
	
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	List<Organizacion> getLatest(Integer count) throws ServiceException;
	
	
	List<ClasificacionOrganizacion> getClasificacionOrganizaciones() throws ServiceException;

	List<ClasificacionOrganizacion> findClasificacionOrganizacionByName(String name) throws ServiceException;

	void saveClasificacionOrganizacion(ClasificacionOrganizacion clasificacionOrganizacion) throws ServiceException;
	
	ClasificacionOrganizacion findClasificacionOrganizacionById(Integer id) throws ServiceException;

	List<OrganizacionCapacidadDemanda> getDemandas() throws ServiceException;
	
	
	
	/**
	 * Devuelve Todas las Organizaciones de un Satelite
	 * @param satelite
	 * @return
	 * @throws ServiceException
	 */
	List<Organizacion> findBySatelite(Satelite satelite) throws ServiceException;
	
	Integer getTotalOrganizaciones() throws ServiceException;

	
	//Capacidad
	/**
	 * @param capacidad
	 * @throws ServiceException
	 */
	void saveCapacidad(Capacidad capacidad) throws ServiceException;
	/**
	 * @param id
	 * @return
	 * @throws ServiceException
	 */
	Capacidad findCapacidadById(Integer id) throws ServiceException;
	/**
	 * @param name
	 * @return
	 * @throws ServiceException
	 */
	List<Capacidad> findCapacidadByName(String name) throws ServiceException;

	List<Capacidad> getCapacidades() throws ServiceException;	

	
	Set<Organizacion> findByCapacidades(Integer[] idsCapacidades) throws ServiceException;
	Set<Organizacion> findBySectores(Integer[] idsSectores) throws ServiceException;
	
	Set<Organizacion> findByDemandaSectores(Integer[] idsSectores) throws ServiceException;
	Set<Organizacion> findByOfertaSectores(Integer[] idsSectores) throws ServiceException;

	Set<Organizacion> findByDemandaCapacidades(Integer[] idsCapacidades) throws ServiceException;
	Set<Organizacion> findByOfertaCapacidades(Integer[] idsCapacidades) throws ServiceException;
	
	//Eventos
	void saveEvento(Evento evento) throws ServiceException;

	Collection<Organizacion> findByName(String name) throws ServiceException;

	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	Collection<Organizacion> findByFormaJuridica(FormaJuridica formaJuridica) throws ServiceException;

}
