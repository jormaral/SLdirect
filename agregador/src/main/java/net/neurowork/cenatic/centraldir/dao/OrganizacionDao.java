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
package net.neurowork.cenatic.centraldir.dao;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import net.neurowork.cenatic.centraldir.model.Satelite;
import net.neurowork.cenatic.centraldir.model.satelite.Asociacion;
import net.neurowork.cenatic.centraldir.model.satelite.Capacidad;
import net.neurowork.cenatic.centraldir.model.satelite.ClasificacionOrganizacion;
import net.neurowork.cenatic.centraldir.model.satelite.Evento;
import net.neurowork.cenatic.centraldir.model.satelite.FormaJuridica;
import net.neurowork.cenatic.centraldir.model.satelite.Organizacion;
import net.neurowork.cenatic.centraldir.model.satelite.OrganizacionCapacidadDemanda;
import net.neurowork.cenatic.centraldir.model.satelite.OrganizacionSatelite;
import net.neurowork.cenatic.centraldir.model.satelite.Provincia;
import net.neurowork.cenatic.centraldir.model.satelite.Sector;

/**
 *
 * @author Alejandro Sanchez Acosta <asanchez@neurowork.net> http://www.neurowork.net
 * @since 03/12/2010
 */
public interface OrganizacionDao {
	List<Organizacion> getOrganizaciones() throws DaoException;

	Integer getTotalOrganizaciones() throws DaoException;
	
	void save(Organizacion organizacion) throws DaoException;

	Organizacion findById(Integer id) throws DaoException;
	Organizacion findByHash(String xmlHash) throws DaoException;
	Collection<Organizacion> findByName(String name) throws DaoException;
	Collection<Organizacion> findByFormaJuridica(FormaJuridica formaJuridica) throws DaoException;
	Collection<Organizacion> findByProvincia(Provincia provincia) throws DaoException;
	Collection<Organizacion> findByAsociacion(Asociacion asociacion) throws DaoException;
	
	Set<Organizacion> findBySector(Sector sector) throws DaoException;
	Set<Organizacion> findBySectores(Collection<Sector> sectores) throws DaoException;
	Set<Organizacion> findByOfertaSector(Sector sector) throws DaoException;
	Set<Organizacion> findByDemandaSector(Sector sector) throws DaoException;

	Set<Organizacion> findByCapacidad(Capacidad capacidad) throws DaoException;
	Set<Organizacion> findByCapacidades(Collection<Capacidad> capacidades) throws DaoException;
	Set<Organizacion> findByOfertaCapacidad(Capacidad capacidad) throws DaoException;
	Set<Organizacion> findByDemandaCapacidad(Capacidad capacidad) throws DaoException;
	
	List<Organizacion> getLatest(Integer count) throws DaoException;
	
	Organizacion findByCIF(String cif) throws DaoException;
	
	Collection<OrganizacionSatelite> findBySatelite(Satelite satelite)  throws DaoException;
	
	List<Organizacion> findByClasificacionOrganizacion(ClasificacionOrganizacion clasificacionOrganizacion) throws DaoException;	

	List<OrganizacionCapacidadDemanda> getDemandas() throws DaoException;

	// ClasificacionOrganizacion
	List<ClasificacionOrganizacion> getClasificacionOrganizaciones() throws DaoException;
	
	List<ClasificacionOrganizacion> findClasificacionOrganizacionByName(String name) throws DaoException;
	
	void saveClasificacionOrganizacion(ClasificacionOrganizacion clasificacionOrganizacion) throws DaoException;
	
	ClasificacionOrganizacion findClasificacionOrganizacionById(Integer id) throws DaoException;

	
	//Capacidad
	void saveCapacidad(Capacidad capacidad) throws DaoException;

	Capacidad findCapacidadById(Integer id) throws DaoException;

	List<Capacidad> findCapacidadByName(String name) throws DaoException;
	List<Capacidad> getCapacidades() throws DaoException;

	//Evento
	void saveEvento(Evento evento) throws DaoException;
}