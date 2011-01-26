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
package net.neurowork.cenatic.centraldir.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import net.neurowork.cenatic.centraldir.dao.DaoException;
import net.neurowork.cenatic.centraldir.dao.OrganizacionDao;
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
import net.neurowork.cenatic.centraldir.model.satelite.OrganizacionEvento;
import net.neurowork.cenatic.centraldir.model.satelite.OrganizacionSatelite;
import net.neurowork.cenatic.centraldir.model.satelite.Provincia;
import net.neurowork.cenatic.centraldir.model.satelite.Sector;
import net.neurowork.cenatic.centraldir.service.EventoService;
import net.neurowork.cenatic.centraldir.service.OrganizacionService;
import net.neurowork.cenatic.centraldir.service.SectorService;
import net.neurowork.cenatic.centraldir.service.ServiceException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

/**
 *
 * @author Alejandro Sanchez Acosta <asanchez@neurowork.net> http://www.neurowork.net
 * @since 14/11/2010
 */
public class OrganizacionServiceImpl implements OrganizacionService {
	private final static Logger logger = LoggerFactory.getLogger(OrganizacionServiceImpl.class);
	
	@Autowired
	private OrganizacionDao organizacionDao;
	@Autowired
	private SectorService sectorService;
	@Autowired
	private EventoService eventoService;
	
	public boolean isOrganizationImported(Satelite satelite, Integer organizationId) throws ServiceException {

		return false;
	}

	@Override
	public void saveOrganization(Organizacion organizacion) throws ServiceException {
		if(logger.isTraceEnabled())
			logger.trace("Saving Organizacion: " + organizacion);
		try {
			String cif = organizacion.getCif().toUpperCase();
			organizacion.setCif(cif);
			organizacionDao.save(organizacion);
		} catch (DaoException e) {
			logger.error(e.getMessage());
		}
	}

	@Override
	public Organizacion findById(Integer id) throws ServiceException {
		if(logger.isTraceEnabled())
			logger.trace("Finding Organizacion by Id: " + id);
		try {
			return organizacionDao.findById(id);
		} catch (DaoException e) {
			logger.error(e.getMessage());
			throw new ServiceException(e.getMessage());
		}
	}

	@Override
	public Organizacion findByHash(String xmlHash) throws ServiceException {
		if(logger.isTraceEnabled())
			logger.trace("Finding Organizacion by HASH: " + xmlHash);
		try {
			return organizacionDao.findByHash(xmlHash);
		} catch (DaoException e) {
			logger.error(e.getMessage());
			throw new ServiceException(e.getMessage());
		}
	}

	@Override
	public Organizacion findByCIF(String cif) throws ServiceException {
		if(logger.isTraceEnabled())
			logger.trace("Finding Organizacion by cif: " + cif);
		try {
			return organizacionDao.findByCIF(cif.toUpperCase());
		} catch (DaoException e) {
			logger.error(e.getMessage());
			throw new ServiceException(e.getMessage());
		}
	}

	@Override
	public List<ClasificacionOrganizacion> findClasificacionOrganizacionByName(String name) throws ServiceException {
		if(logger.isTraceEnabled())
			logger.trace("Finding ClasificacionOrganizacion by Name: " + name);
		try {
			return organizacionDao.findClasificacionOrganizacionByName(name);
		} catch (DaoException e) {
			logger.error(e.getMessage());
			throw new ServiceException(e.getMessage());
		}
	}


	@Override
	public void saveClasificacionOrganizacion(
			ClasificacionOrganizacion clasificacionOrganizacion)
			throws ServiceException {
		if(logger.isTraceEnabled())
			logger.trace("Saving ClasificacionOrganizacion: " + clasificacionOrganizacion);
		try {
			organizacionDao.saveClasificacionOrganizacion(clasificacionOrganizacion);
		} catch (DaoException e) {
			logger.error(e.getMessage());
		}
	}

	@Override
	public ClasificacionOrganizacion findClasificacionOrganizacionById(
			Integer id) throws ServiceException {
		if(logger.isTraceEnabled())
			logger.trace("Finding ClasificacionOrganizacion by Id: " + id);
		try {
			return organizacionDao.findClasificacionOrganizacionById(id);
		} catch (DaoException e) {
			logger.error(e.getMessage());
			throw new ServiceException(e.getMessage());
		}
	}

	@Override
	public List<Organizacion> findBySatelite(Satelite satelite)	throws ServiceException {
		if(logger.isTraceEnabled())
			logger.trace("Finding Organizaciones by Satelite: " + satelite);
		try {
			Collection<OrganizacionSatelite> oss = organizacionDao.findBySatelite(satelite);
			List<Organizacion> ret = new ArrayList<Organizacion>();
			for(OrganizacionSatelite os : oss){
				ret.add(os.getOrganizacion());
			}
			return ret;
		} catch (DaoException e) {
			logger.error(e.getMessage());
			throw new ServiceException(e.getMessage());
		}
	}

	@Override
	public void saveCapacidad(Capacidad capacidad) throws ServiceException {
		if(logger.isTraceEnabled())
			logger.trace("Saving Capacidad: " + capacidad);
		try {
			organizacionDao.saveCapacidad(capacidad);
		} catch (DaoException e) {
			logger.error(e.getMessage());
		}
	}

	@Override
	public Capacidad findCapacidadById(Integer id) throws ServiceException {
		if(logger.isTraceEnabled())
			logger.trace("Finding Capacidad by Id: " + id);
		try {
			return organizacionDao.findCapacidadById(id);
		} catch (DaoException e) {
			logger.error(e.getMessage());
			throw new ServiceException(e.getMessage());
		}
	}

	@Override
	public List<Capacidad> findCapacidadByName(String name) throws ServiceException {
		if(logger.isTraceEnabled())
			logger.trace("Finding Capacidad by Name: " + name);
		try {
			return organizacionDao.findCapacidadByName(name);
		} catch (DaoException e) {
			logger.error(e.getMessage());
			throw new ServiceException(e.getMessage());
		}
	}

	@Override
	public Collection<Organizacion> getOrganizaciones() throws ServiceException {
		if(logger.isTraceEnabled())
			logger.trace("Finding all Organizacion");
		try {
			return organizacionDao.getOrganizaciones();
		} catch (DaoException e) {
			logger.error(e.getMessage());
			throw new ServiceException(e.getMessage());
		}
	}

	@Override
	public Integer getTotalOrganizaciones() throws ServiceException {
		if(logger.isTraceEnabled())
			logger.trace("Finding Total de Organizaciones");
		try {
			return organizacionDao.getTotalOrganizaciones();
		} catch (DaoException e) {
			logger.error(e.getMessage());
			throw new ServiceException(e.getMessage());
		}
	}

	@Override
	public List<ClasificacionOrganizacion> getClasificacionOrganizaciones() throws ServiceException {
		if(logger.isTraceEnabled())
			logger.trace("Finding All ClasificacionOrganizacion");
		try {
			return organizacionDao.getClasificacionOrganizaciones();
		} catch (DaoException e) {
			logger.error(e.getMessage());
			throw new ServiceException(e.getMessage());
		}
	}

	@Override
	public List<Organizacion> findByClasificacionOrganizacion(ClasificacionOrganizacion clasificacionOrganizacion)	throws ServiceException {
		if(logger.isTraceEnabled())
			logger.trace("Finding Organizacion by ClasificacionOrganizacion: " + clasificacionOrganizacion);
		
		List<Organizacion> ret = null;
		
		try {
			ret = organizacionDao.findByClasificacionOrganizacion(clasificacionOrganizacion);
		} catch (DaoException e) {
			logger.error(e.getMessage());
			throw new ServiceException(e.getMessage());
		}
		if(logger.isTraceEnabled())
			logger.trace("Returning: " + ret);
		return ret;
	}

	@Override
	public List<Capacidad> getCapacidades() throws ServiceException {
		if(logger.isTraceEnabled())
			logger.trace("Finding All Capacidades");
		try {
			return organizacionDao.getCapacidades();
		} catch (DaoException e) {
			logger.error(e.getMessage());
			throw new ServiceException(e.getMessage());
		}
	}

	@Override
	public List<OrganizacionCapacidadDemanda> getDemandas() throws ServiceException {
		if(logger.isTraceEnabled())
			logger.trace("Finding All Sectores");
		try {
			return organizacionDao.getDemandas();
		} catch (DaoException e) {
			logger.error(e.getMessage());
			throw new ServiceException(e.getMessage());
		}
	}

	@Override
	public Set<Organizacion> findByCapacidades(Integer[] idsCapacidades) throws ServiceException {
		if(logger.isTraceEnabled())
			logger.trace("Finding All Sectores");
		try {
			List<Capacidad> capacidades = new ArrayList<Capacidad>();
			for(Integer id : idsCapacidades){
				capacidades.add(organizacionDao.findCapacidadById(id));
			}			
			return organizacionDao.findByCapacidades(capacidades);
		} catch (DaoException e) {
			logger.error(e.getMessage());
			throw new ServiceException(e.getMessage());
		}
	}

	@Override
	public Set<Organizacion> findBySectores(Integer[] idsSectores) throws ServiceException {
		if(logger.isTraceEnabled())
			logger.trace("Finding All Sectores");
		try {
			List<Sector> sectores = new ArrayList<Sector>();
			for(Integer id : idsSectores){
				sectores.add(sectorService.findById(id));
			}			
			return organizacionDao.findBySectores(sectores);
		} catch (DaoException e) {
			logger.error(e.getMessage());
			throw new ServiceException(e.getMessage());
		}
	}
	
	
	@Override
	public void saveEvento(Evento evento) throws ServiceException {
		if(logger.isTraceEnabled())
			logger.trace("Saving Evento: " + evento);
		try {
			organizacionDao.saveEvento(evento);
		} catch (DaoException e) {
			logger.error(e.getMessage());
		}
	}

	@Override
	public Collection<Organizacion> findByName(String name) throws ServiceException {
		if(logger.isTraceEnabled())
			logger.trace("Finding Organizaciones by Name: " + name);
		try {
			return organizacionDao.findByName(name);
		} catch (DaoException e) {
			logger.error(e.getMessage());
			throw new ServiceException(e.getMessage());
		}
	}

	@Override
	public Collection<Organizacion> findByFormaJuridica(FormaJuridica formaJuridica) throws ServiceException {
		if(logger.isTraceEnabled())
			logger.trace("Finding Organizaciones by Forma Juridica: " + formaJuridica);
		try {
			return organizacionDao.findByFormaJuridica(formaJuridica);
		} catch (DaoException e) {
			logger.error(e.getMessage());
			throw new ServiceException(e.getMessage());
		}	}

	@Override
	public Collection<Organizacion> findByProvincia(Provincia provincia) throws ServiceException {
		if(logger.isTraceEnabled())
			logger.trace("Finding Organizaciones by Provincia: " + provincia);
		try {
			return organizacionDao.findByProvincia(provincia);
		} catch (DaoException e) {
			logger.error(e.getMessage());
			throw new ServiceException(e.getMessage());
		}
	}

	@Override
	public Collection<Organizacion> findByAsociacion(Asociacion asociacion) throws ServiceException {
		if(logger.isTraceEnabled())
			logger.trace("Finding Organizaciones by Asociacion: " + asociacion);
		try {
			return organizacionDao.findByAsociacion(asociacion);
		} catch (DaoException e) {
			logger.error(e.getMessage());
			throw new ServiceException(e.getMessage());
		}
	}

	@Override
	public Set<Organizacion> findBySector(Sector sector) throws ServiceException {
		if(logger.isTraceEnabled())
			logger.trace("Finding Organizaciones by Sector: " + sector);
		try {
			return organizacionDao.findBySector(sector);
		} catch (DaoException e) {
			logger.error(e.getMessage());
			throw new ServiceException(e.getMessage());
		}
	}

	@Override
	public Set<Organizacion> findByDemandaSector(Sector sector) throws ServiceException {
		if(logger.isTraceEnabled())
			logger.trace("Finding Organizaciones with Demanda Sector: " + sector);
		try {
			return organizacionDao.findByDemandaSector(sector);
		} catch (DaoException e) {
			logger.error(e.getMessage());
			throw new ServiceException(e.getMessage());
		}
	}

	@Override
	public Set<Organizacion> findByOfertaSector(Sector sector) throws ServiceException {
		if(logger.isTraceEnabled())
			logger.trace("Finding Organizaciones with Oferta Sector: " + sector);
		try {
			return organizacionDao.findByOfertaSector(sector);
		} catch (DaoException e) {
			logger.error(e.getMessage());
			throw new ServiceException(e.getMessage());
		}
	}

	@Override
	public Set<Organizacion> findByDemandaSectores(Integer[] idsSectores) throws ServiceException {
		if(logger.isTraceEnabled())
			logger.trace("Finding Sectores Demanda: " + idsSectores);
		
		Set<Organizacion> ret = new HashSet<Organizacion>();
		for(Integer id : idsSectores){
			Sector sector = sectorService.findById(id);
			if(sector != null){
				if(logger.isTraceEnabled())
					logger.trace("Finding Sectores Demanda: " + sector);
				ret.addAll(findByDemandaSector(sector));	
			}				
		}			
		if(logger.isTraceEnabled())
			logger.trace("Returning " + ret.size() + " Organizacion Demanda Sector");
		return ret;
	}

	@Override
	public Set<Organizacion> findByOfertaSectores(Integer[] idsSectores) throws ServiceException {
		if(logger.isTraceEnabled())
			logger.trace("Finding All Sectores");
		
		Set<Organizacion> ret = new HashSet<Organizacion>();
		for(Integer id : idsSectores){
			Sector sector = sectorService.findById(id);
			if(sector != null){
				Set<Organizacion> orgs = findByOfertaSector(sector);
				if(orgs != null)
					ret.addAll(orgs);	
			}				
		}			
		return ret;
	}

	@Override
	public Set<Organizacion> findByDemandaCapacidades(Integer[] idsCapacidades) throws ServiceException {
		if(logger.isTraceEnabled())
			logger.trace("Finding Demanda by Capacidades: " + idsCapacidades);
		
		Set<Organizacion> ret = new HashSet<Organizacion>();
		for(Integer id : idsCapacidades){
			Capacidad capacidad = findCapacidadById(id);
			if(capacidad != null){
				if(logger.isTraceEnabled())
					logger.trace("Finding Demanda by Capacidad: " + capacidad);
				ret.addAll(findByDemandaCapacidad(capacidad));	
			}				
		}			
		if(logger.isTraceEnabled())
			logger.trace("Returning " + ret.size() + " Organizacion Demanda Sector");
		return ret;
	}

	@Override
	public Set<Organizacion> findByOfertaCapacidades(Integer[] idsCapacidades) throws ServiceException {
		if(logger.isTraceEnabled())
			logger.trace("Finding Ofertas by Capacidades: " + idsCapacidades);
		
		Set<Organizacion> ret = new HashSet<Organizacion>();
		for(Integer id : idsCapacidades){
			Capacidad capacidad = findCapacidadById(id);
			if(capacidad != null){
				ret.addAll(findByOfertaCapacidad(capacidad));	
			}				
		}			
		return ret;
	}

	@Override
	public Set<Organizacion> findByDemandaCapacidad(Capacidad capacidad)	throws ServiceException {
		if(logger.isTraceEnabled())
			logger.trace("Finding Organizaciones with Demanda Sector: " + capacidad);
		try {
			return organizacionDao.findByDemandaCapacidad(capacidad);
		} catch (DaoException e) {
			logger.error(e.getMessage());
			throw new ServiceException(e.getMessage());
		}
	}

	@Override
	public Set<Organizacion> findByOfertaCapacidad(Capacidad capacidad) throws ServiceException {
		if(logger.isTraceEnabled())
			logger.trace("Finding Organizaciones with Oferta Capacidad: " + capacidad);
		try {
			return organizacionDao.findByOfertaCapacidad(capacidad);
		} catch (DaoException e) {
			logger.error(e.getMessage());
			throw new ServiceException(e.getMessage());
		}
	}

	@Override
	public Set<NewsDTO> getNews() throws ServiceException {
		Set<NewsDTO> ret = new HashSet<NewsDTO>();
		Collection<Organizacion> orgs = getOrganizaciones();
		for(Organizacion organizacion : orgs){
			if(StringUtils.hasLength(organizacion.getNewsTitle())){
				ret.add(new NewsDTO(organizacion));
			}
		}
		
		return ret;
	}

	@Override
	public Set<EventDTO> getEvents() throws ServiceException {
		Set<EventDTO> ret = new HashSet<EventDTO>();
		Collection<OrganizacionEvento> events = eventoService.getAll();
		Date now = new Date();
		for(OrganizacionEvento e : events){
			if(e.getEvento() != null){
				Evento evento = e.getEvento(); 
				if(evento.getFecha().after(now)){
					EventDTO dto = new EventDTO();
					dto.setId(evento.getId());
					dto.setTitulo(evento.getTitulo());
					dto.setLocalizacion(evento.getLocalizacion());
					dto.setFecha(evento.getFecha());
					dto.setDescripcion(evento.getDescripcion());
					if(e.getOrganizacion() != null){
						dto.setOrgName(e.getOrganizacion().getName());	
					}
					ret.add(dto);
					if(ret.size() >= 3)
						break;
				}
			}
		}
		return ret;
	}

	@Override
	public List<Organizacion> getLatest(Integer count) throws ServiceException {
		if(logger.isTraceEnabled())
			logger.trace("Returning: " + count + " latest Organizaciones");
		try {
			return organizacionDao.getLatest(count);
		} catch (DaoException e) {
			logger.error(e.getMessage());
			throw new ServiceException(e.getMessage());
		}
	}
	
	@Override
	public String getServiceName() {
		return SERVICE_NAME;
	}
}