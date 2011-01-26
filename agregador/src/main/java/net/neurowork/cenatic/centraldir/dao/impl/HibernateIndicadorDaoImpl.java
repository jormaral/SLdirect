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

import java.util.Collection;
import java.util.List;

import net.neurowork.cenatic.centraldir.dao.DaoException;
import net.neurowork.cenatic.centraldir.dao.IndicadorDao;
import net.neurowork.cenatic.centraldir.model.indicators.AgruparPor;
import net.neurowork.cenatic.centraldir.model.indicators.Indicator;
import net.neurowork.cenatic.centraldir.model.indicators.TipoGrafico;
import net.neurowork.cenatic.centraldir.model.indicators.TipoIndicador;

import org.hibernate.ObjectNotFoundException;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 *
 * @author Alejandro Sanchez Acosta <asanchez@neurowork.net> http://www.neurowork.net
 * @since 13/11/2010
 */
public class HibernateIndicadorDaoImpl extends HibernateDaoSupport implements IndicadorDao {
	private final static Logger logger = LoggerFactory.getLogger(HibernateIndicadorDaoImpl.class);

	public Collection<Indicator> getIndicadors() {
		return getHibernateTemplate().loadAll(Indicator.class);
	}

	@Override
	public void saveIndicator(Indicator indicator) throws DaoException {
		if(logger.isTraceEnabled())
			logger.trace("Saving Indicator: " + indicator);
		getHibernateTemplate().saveOrUpdate(indicator);
	}

	
	@SuppressWarnings("unchecked")
	@Override
	public Collection<Indicator> findIndicador(String name) throws DaoException {
		if(logger.isTraceEnabled())
			logger.trace("Hibernate Searching Indicador with name:" + name);
		DetachedCriteria criteria = DetachedCriteria.forClass(Indicator.class);
		criteria.add(Restrictions.like("name", "%"+name+"%"));
		criteria.addOrder(Order.asc("name"));		
		return (List<Indicator>)getHibernateTemplate().findByCriteria(criteria);
	}

	@Override
	public Indicator loadIndicator(Integer id) throws DaoException {
		if(logger.isTraceEnabled())
			logger.trace("Loading Indicator width id: " + id);
		try {
			return getHibernateTemplate().load(Indicator.class, id);
		} catch (ObjectNotFoundException e){
			return null;
		} catch (DataAccessException e) {
			throw new DaoException(e.getMessage());
		}
	}

	@Override
	public Collection<TipoIndicador> getTiposIndicador() throws DaoException {
		return  getHibernateTemplate().loadAll(TipoIndicador.class);
	}

	@Override
	public Collection<TipoGrafico> getTiposGrafico() throws DaoException {
		return  getHibernateTemplate().loadAll(TipoGrafico.class);
	}

	@Override
	public Collection<AgruparPor> getAgruparPors() throws DaoException {
		return  getHibernateTemplate().loadAll(AgruparPor.class);
	}

	@Override
	public TipoGrafico findTipoGraficoById(Integer id) throws DaoException {
		if(logger.isTraceEnabled())
			logger.trace("Loading TipoGrafico width id: " + id);
		try {
			return getHibernateTemplate().load(TipoGrafico.class, id);
		} catch (ObjectNotFoundException e){
			return null;
		} catch (DataAccessException e) {
			throw new DaoException(e.getMessage());
		}
	}
}
