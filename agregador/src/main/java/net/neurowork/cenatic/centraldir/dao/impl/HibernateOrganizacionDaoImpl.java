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
package net.neurowork.cenatic.centraldir.dao.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import net.neurowork.cenatic.centraldir.dao.DaoException;
import net.neurowork.cenatic.centraldir.dao.OrganizacionDao;
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
import net.neurowork.cenatic.centraldir.model.satelite.OrganizacionSatelite;
import net.neurowork.cenatic.centraldir.model.satelite.Provincia;
import net.neurowork.cenatic.centraldir.model.satelite.Sector;

import org.hibernate.ObjectNotFoundException;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 *
 * @author Alejandro Sanchez Acosta <asanchez@neurowork.net> http://www.neurowork.net
 * @since 03/12/2010
 */
public class HibernateOrganizacionDaoImpl extends HibernateDaoSupport implements OrganizacionDao {
	private final static Logger logger = LoggerFactory.getLogger(HibernateOrganizacionDaoImpl.class);

	@SuppressWarnings("unchecked")
	private Collection<Organizacion> findByCriteria(DetachedCriteria criteria) {
		return (List<Organizacion>)getHibernateTemplate().findByCriteria(criteria);
	}
	
	@Override
	public List<Organizacion> getOrganizaciones() throws DaoException {
		if(logger.isTraceEnabled())
			logger.trace("Loading all Organizaciones");
		return getHibernateTemplate().loadAll(Organizacion.class);
	}

	@Override
	public void save(Organizacion organizacion) throws DaoException {
		if(logger.isTraceEnabled())
			logger.trace("Saving Organizacion: " + organizacion);
		getHibernateTemplate().saveOrUpdate(organizacion);
	}

	@Override
	public Organizacion findById(Integer id) throws DaoException {
		if(logger.isTraceEnabled())
			logger.trace("Loading Organizacion width id: " + id);
		try{
			return getHibernateTemplate().load(Organizacion.class, id);
		} catch (ObjectNotFoundException e){
			return null;
		} catch (DataAccessException e) {
			throw new DaoException(e.getMessage());
		}
	}

	@Override
	public Collection<Organizacion> findByName(String name) throws DaoException {
		if(logger.isTraceEnabled())
			logger.trace("Hibernate Searching Organizacion with name:" + name);
		DetachedCriteria criteria = DetachedCriteria.forClass(Organizacion.class);
		criteria.add(Restrictions.like("name", "%"+name+"%"));
		criteria.addOrder(Order.asc("name"));		
		return findByCriteria(criteria);
	}

	@Override
	public Organizacion findByHash(String xmlHash) throws DaoException {
		if(logger.isTraceEnabled())
			logger.trace("Hibernate Searching Organizacion with hash:" + xmlHash);
		DetachedCriteria criteria = DetachedCriteria.forClass(Organizacion.class);
		criteria.add(Restrictions.eq("xmlHash", xmlHash));
		
		Collection<Organizacion> l = findByCriteria(criteria);
		
		if(l != null){
			if(l.size() >= 1){
				return l.iterator().next();
			}
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Organizacion findByCIF(String cif) throws DaoException {
		if(logger.isTraceEnabled())
			logger.trace("Hibernate Searching Organizacion with CIF:" + cif);
		DetachedCriteria criteria = DetachedCriteria.forClass(Organizacion.class);
		criteria.add(Restrictions.eq("cif", cif));
		
		List<Organizacion> l = (List<Organizacion>)getHibernateTemplate().findByCriteria(criteria);
		
		if(l != null){
			if(l.size() >= 1){
				return l.get(0);
			}
		}
		return null;
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<ClasificacionOrganizacion> findClasificacionOrganizacionByName(String name) throws DaoException {
		if(logger.isTraceEnabled())
			logger.trace("Hibernate Searching FormaJuridica with name:" + name);
		DetachedCriteria criteria = DetachedCriteria.forClass(ClasificacionOrganizacion.class);
		criteria.add(Restrictions.like("name", "%"+name+"%"));
		criteria.addOrder(Order.asc("name"));		
		return (List<ClasificacionOrganizacion>)getHibernateTemplate().findByCriteria(criteria);
	}

	@Override
	public void saveClasificacionOrganizacion(ClasificacionOrganizacion clasificacionOrganizacion) throws DaoException {
		if(logger.isTraceEnabled())
			logger.trace("Saving ClasificacionOrganizacion: " + clasificacionOrganizacion);
		getHibernateTemplate().saveOrUpdate(clasificacionOrganizacion);		
	}

	@Override
	public ClasificacionOrganizacion findClasificacionOrganizacionById(Integer id) throws DaoException {
		if(logger.isTraceEnabled())
			logger.trace("Loading ClasificacionOrganizacion width id: " + id);
		try{
			return getHibernateTemplate().load(ClasificacionOrganizacion.class, id);
		} catch (ObjectNotFoundException e){
			return null;
		} catch (DataAccessException e) {
			throw new DaoException(e.getMessage());
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<OrganizacionSatelite> findBySatelite(Satelite satelite) throws DaoException {
		if(logger.isTraceEnabled())
			logger.trace("Hibernate Organizacion with Satelite: " + satelite);
		DetachedCriteria criteria = DetachedCriteria.forClass(OrganizacionSatelite.class);
		criteria.add(Restrictions.eq("satelite", satelite));
		List<OrganizacionSatelite> ret = getHibernateTemplate().findByCriteria(criteria); 
		if(logger.isTraceEnabled())
			logger.trace("Returning: " + ret.size() + " Organizaciones.");
		return ret;
	}

	@Override
	public void saveCapacidad(Capacidad capacidad) throws DaoException {
		if(logger.isTraceEnabled())
			logger.trace("Saving Capacidad: " + capacidad);
		getHibernateTemplate().saveOrUpdate(capacidad);		
	}

	@Override
	public Capacidad findCapacidadById(Integer id) throws DaoException {
		if(logger.isTraceEnabled())
			logger.trace("Loading Capacidad width id: " + id);
		try{
			return getHibernateTemplate().load(Capacidad.class, id);
		} catch (ObjectNotFoundException e){
			return null;
		} catch (DataAccessException e) {
			throw new DaoException(e.getMessage());
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Capacidad> findCapacidadByName(String name) throws DaoException {
		if(logger.isTraceEnabled())
			logger.trace("Hibernate Searching Capacidad with name:" + name);
		DetachedCriteria criteria = DetachedCriteria.forClass(Capacidad.class);
		criteria.add(Restrictions.like("name", "%"+name+"%"));
		criteria.addOrder(Order.asc("name"));		
		return (List<Capacidad>)getHibernateTemplate().findByCriteria(criteria);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Integer getTotalOrganizaciones() throws DaoException {
		if(logger.isTraceEnabled())
			logger.trace("Hibernate Searching Total Organizaciones");
		DetachedCriteria criteria = DetachedCriteria.forClass(Organizacion.class);
		
		ProjectionList projList = Projections.projectionList();
		projList.add(Projections.rowCount()); 		
		criteria.setProjection(projList);
		
		List result = getHibernateTemplate().findByCriteria(criteria);
		if(result != null)
			return (Integer)result.get(0);
		return 0;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ClasificacionOrganizacion> getClasificacionOrganizaciones() throws DaoException {
		if(logger.isTraceEnabled())
			logger.trace("Loading all Organizaciones");
		DetachedCriteria criteria = DetachedCriteria.forClass(ClasificacionOrganizacion.class);
		criteria.addOrder(Order.asc("name"));		
		return (List<ClasificacionOrganizacion>)getHibernateTemplate().findByCriteria(criteria);	
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Organizacion> findByClasificacionOrganizacion(ClasificacionOrganizacion clasificacionOrganizacion) throws DaoException {
		if(logger.isTraceEnabled())
			logger.trace("Hibernate Searching Organizacion with ClasificacionOrganizacion: " + clasificacionOrganizacion);
		DetachedCriteria criteria = DetachedCriteria.forClass(Organizacion.class);
		criteria.add(Restrictions.eq("clasificacionOrganizacion", clasificacionOrganizacion));
		criteria.addOrder(Order.asc("name"));		
		return (List<Organizacion>)getHibernateTemplate().findByCriteria(criteria);
	}

	@Override
	public List<Capacidad> getCapacidades() throws DaoException {
		if(logger.isTraceEnabled())
			logger.trace("Loading all Capacidades");
		return getHibernateTemplate().loadAll(Capacidad.class);
	}

	@Override
	public List<OrganizacionCapacidadDemanda> getDemandas() throws DaoException {
		if(logger.isTraceEnabled())
			logger.trace("Loading all Organizaciones");
		return getHibernateTemplate().loadAll(OrganizacionCapacidadDemanda.class);
	}

	@Override
	public Set<Organizacion> findByCapacidades(Collection<Capacidad> capacidades) throws DaoException {
		Set<Organizacion> ret = new HashSet<Organizacion>();
		for(Capacidad capacidad : capacidades)
			ret.addAll(findByCapacidad(capacidad));
		return ret;
	}

	@Override
	public void saveEvento(Evento evento) throws DaoException {
		if(logger.isTraceEnabled())
			logger.trace("Saving Evento: " + evento);
		getHibernateTemplate().saveOrUpdate(evento);
	}

	@Override
	public Collection<Organizacion> findByFormaJuridica(FormaJuridica formaJuridica) throws DaoException {
		if(logger.isTraceEnabled())
			logger.trace("Hibernate Searching Organizacion with Forma Juridica:" + formaJuridica);
		DetachedCriteria criteria = DetachedCriteria.forClass(Organizacion.class);
		criteria.add(Restrictions.eq("formaJuridica", formaJuridica));
		criteria.addOrder(Order.asc("name"));		
		return findByCriteria(criteria);
	}

	@Override
	public Collection<Organizacion> findByProvincia(Provincia provincia) throws DaoException {
		if(logger.isTraceEnabled())
			logger.trace("Hibernate Searching Organizacion with Provincia:" + provincia);
		DetachedCriteria criteria = DetachedCriteria.forClass(Organizacion.class);
		criteria.add(Restrictions.eq("provincia", provincia));
		criteria.addOrder(Order.asc("name"));		
		return findByCriteria(criteria);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Collection<Organizacion> findByAsociacion(Asociacion asociacion) throws DaoException {
		if(logger.isTraceEnabled())
			logger.trace("Hibernate Searching Organizacion with Asociacion:" + asociacion);
		DetachedCriteria criteria = DetachedCriteria.forClass(OrganizacionAsociacion.class);
		criteria.add(Restrictions.eq("asociacion", asociacion));
		Collection<Organizacion> ret = null;
		
		try {
			List<OrganizacionAsociacion> oas = (List<OrganizacionAsociacion>)getHibernateTemplate().findByCriteria(criteria);
			if(oas == null)
				logger.info("No Organizaciones found with Asociacion: " + asociacion);
			else if(oas.size() > 0){
				logger.info("Found: " + oas.size() + "Organizaciones found with Asociacion: " + asociacion);
				ret = new ArrayList<Organizacion>();
				for(OrganizacionAsociacion oa : oas){
					ret.add(oa.getOrganizacion());
				}
			}
		} catch (DataAccessException e) {
			logger.error(e.getMessage());
		}

		return ret;		
	}

	@Override
	public Set<Organizacion> findBySector(Sector sector) throws DaoException {
		if(logger.isTraceEnabled())
			logger.trace("Hibernate Searching Organizacion with Sector:" + sector);
		Set<Organizacion> ret = new HashSet<Organizacion>();
		
		Collection<Organizacion> demandas = findByDemandaSector(sector);
		if(demandas != null)
			ret.addAll(demandas);
		Collection<Organizacion> ofertas = findByOfertaSector(sector);
		if(ofertas != null)
			ret.addAll(ofertas);
		return ret;
	}

	@Override
	public Set<Organizacion> findByCapacidad(Capacidad capacidad) throws DaoException {
		if(logger.isTraceEnabled())
			logger.trace("Hibernate Searching Organizacion with Capacidad:" + capacidad);
		Set<Organizacion> ret = new HashSet<Organizacion>();
		
		Collection<Organizacion> demandas = findByDemandaCapacidad(capacidad);
		if(demandas != null)
			ret.addAll(demandas);
		Collection<Organizacion> ofertas = findByOfertaCapacidad(capacidad);
		if(ofertas != null)
			ret.addAll(ofertas);
		return ret;
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	public Set<Organizacion> findByDemandaSector(Sector sector) throws DaoException {
		DetachedCriteria criteria = DetachedCriteria.forClass(OrganizacionCapacidadDemanda.class);
		criteria.add(Restrictions.eq("sector", sector));
		Set<Organizacion> ret = new HashSet<Organizacion>();
		
		try {
			List<OrganizacionCapacidadDemanda> oas = (List<OrganizacionCapacidadDemanda>)getHibernateTemplate().findByCriteria(criteria);
			if(oas == null || oas.size() == 0){
				if(logger.isTraceEnabled())
					logger.trace("No Organizaciones found with Sector: " + sector);
			}else{
				if(logger.isTraceEnabled())
					logger.trace("Found: " + oas.size() + " Organizaciones found with Demanda Sector: " + sector);

				for(OrganizacionCapacidadDemanda oa : oas){
					ret.add(oa.getOrganizacion());
				}
			}
		} catch (DataAccessException e) {
			logger.error(e.getMessage());
		}

		return ret;		
	}
	

	@SuppressWarnings("unchecked")
	@Override
	public Set<Organizacion> findByOfertaSector(Sector sector) throws DaoException {
		DetachedCriteria criteria = DetachedCriteria.forClass(OrganizacionCapacidadOferta.class);
		criteria.add(Restrictions.eq("sector", sector));
		Set<Organizacion> ret = new HashSet<Organizacion>();
		
		try {
			List<OrganizacionCapacidadOferta> oas = (List<OrganizacionCapacidadOferta>)getHibernateTemplate().findByCriteria(criteria);
			if(oas == null)
				logger.info("No Organizaciones found with Sector: " + sector);
			else if(oas.size() > 0){
				logger.info("Found: " + oas.size() + "Organizaciones found with Sector: " + sector);
				for(OrganizacionCapacidadOferta oa : oas){
					ret.add(oa.getOrganizacion());
				}
			}
		} catch (DataAccessException e) {
			logger.error(e.getMessage());
		}

		return ret;		
	}

	@Override
	public Set<Organizacion> findBySectores(Collection<Sector> sectores) throws DaoException {
		Set<Organizacion> ret = new HashSet<Organizacion>();
		for(Sector sector : sectores)
			ret.addAll(findBySector(sector));
		return ret;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Set<Organizacion> findByOfertaCapacidad(Capacidad capacidad) throws DaoException {
		DetachedCriteria criteria = DetachedCriteria.forClass(OrganizacionCapacidadOferta.class);
		criteria.add(Restrictions.eq("capacidad", capacidad));
		Set<Organizacion> ret = new HashSet<Organizacion>();
		
		try {
			List<OrganizacionCapacidadOferta> oas = (List<OrganizacionCapacidadOferta>)getHibernateTemplate().findByCriteria(criteria);
			if(oas == null)
				logger.info("No Organizaciones found with Capacidad: " + capacidad);
			else if(oas.size() > 0){
				logger.info("Found: " + oas.size() + "Organizaciones found with Capacidad: " + capacidad);
				for(OrganizacionCapacidadOferta oa : oas){
					ret.add(oa.getOrganizacion());
				}
			}
		} catch (DataAccessException e) {
			logger.error(e.getMessage());
		}

		return ret;		
	}

	@SuppressWarnings("unchecked")
	@Override
	public Set<Organizacion> findByDemandaCapacidad(Capacidad capacidad) throws DaoException {
		DetachedCriteria criteria = DetachedCriteria.forClass(OrganizacionCapacidadDemanda.class);
		criteria.add(Restrictions.eq("capacidad", capacidad));
		Set<Organizacion> ret = new HashSet<Organizacion>();
		
		try {
			List<OrganizacionCapacidadDemanda> oas = (List<OrganizacionCapacidadDemanda>)getHibernateTemplate().findByCriteria(criteria);
			if(oas == null || oas.size() == 0){
				if(logger.isTraceEnabled())
					logger.trace("No Organizaciones found with Capacidad: " + capacidad);
			}else{
				if(logger.isTraceEnabled())
					logger.trace("Found: " + oas.size() + " Organizaciones found with Demanda Capacidad: " + capacidad);

				for(OrganizacionCapacidadDemanda oa : oas){
					ret.add(oa.getOrganizacion());
				}
			}
		} catch (DataAccessException e) {
			logger.error(e.getMessage());
		}
		return ret;		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Organizacion> getLatest(Integer count) throws DaoException {
		DetachedCriteria criteria = DetachedCriteria.forClass(Organizacion.class);
		criteria.addOrder(Order.desc("id"));		
		return (List<Organizacion>)getHibernateTemplate().findByCriteria(criteria, 0, count);
	}
}