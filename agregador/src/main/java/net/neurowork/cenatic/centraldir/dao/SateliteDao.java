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

import net.neurowork.cenatic.centraldir.model.Satelite;

/**
 *
 * @author Alejandro Sanchez Acosta <asanchez@neurowork.net> http://www.neurowork.net
 * @since 09/11/2010
 */
public interface SateliteDao {
	List<Satelite> getSatelites() throws DaoException;
	
	Satelite loadSatelite(Integer id) throws DaoException;
	
	void saveSatelite(Satelite satelite) throws DaoException;

	Collection<Satelite> findSatelite(String name) throws DaoException;

	Collection<Satelite> findActivado() throws DaoException;
	
	Integer getOrganizacionesCount(Satelite satelite) throws DaoException;
}
