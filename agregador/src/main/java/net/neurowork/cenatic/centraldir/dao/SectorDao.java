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

import net.neurowork.cenatic.centraldir.model.satelite.Sector;

/**
 * Repositorio de Datos para los Sectores
 * @author Alejandro Sanchez Acosta <asanchez@neurowork.net> http://www.neurowork.net
 * @since 22/12/2010
 */
public interface SectorDao {
	void save(Sector sector) throws DaoException;
	Sector findById(Integer id) throws DaoException;
	Collection<Sector> findByName(String name) throws DaoException;
	Collection<Sector> getAll() throws DaoException;
	void delete(Sector sector) throws DaoException, CantDeleteEntityException;
}
