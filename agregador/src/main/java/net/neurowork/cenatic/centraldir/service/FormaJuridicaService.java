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

import net.neurowork.cenatic.centraldir.model.satelite.FormaJuridica;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Alejandro Sanchez Acosta <asanchez@neurowork.net> http://www.neurowork.net
 * @since 17/12/2010
 */
@Service
public interface FormaJuridicaService extends CenaticService{
	String SERVICE_NAME = "FormaJuridicaService";
	
	/**
	 * Busca una Forma Juridica por nombre
	 * @param name
	 * @return
	 * @throws ServiceException
	 */
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	List<FormaJuridica> findByName(String name) throws ServiceException;
	
	/**
	 * Busca una Forma Jurídica por Nombre
	 * @param id
	 * @return
	 * @throws ServiceException
	 */
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	FormaJuridica findById(Integer id) throws ServiceException;
	
	/**
	 * Almacena una Forma Jurídica
	 * @param formaJuridica
	 * @throws ServiceException
	 */
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
	void save(FormaJuridica formaJuridica) throws ServiceException;
	
	/**
	 * Devuelve todas las Formas Jurídicas
	 * @return
	 * @throws ServiceException
	 */
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	Collection<FormaJuridica> getAll() throws ServiceException;

	@Transactional(propagation = Propagation.REQUIRED)
	void delete(int Id) throws ServiceException;

	@Transactional(propagation = Propagation.REQUIRED)
	void delete(FormaJuridica formaJuridica) throws ServiceException;

}
