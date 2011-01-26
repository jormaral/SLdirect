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
package net.neurowork.cenatic.centraldir.validators;

import net.neurowork.cenatic.centraldir.model.satelite.OrganizacionSede;

import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;

/**
 *
 * @author Alejandro Sanchez Acosta <asanchez@neurowork.net> http://www.neurowork.net
 * @since 27/12/2010
 */
public class OrganizacionSedeValidator {
	public void validate(OrganizacionSede sede, Errors errors) {
		if (!StringUtils.hasLength(sede.getDireccion())) {
			errors.rejectValue("direccion", "required", "required");
		}
		
	}

}
