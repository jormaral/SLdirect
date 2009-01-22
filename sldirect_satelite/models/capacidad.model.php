<?php
/**
* SLDirect
* Directorio de Empresas, Servicios y Demandas
* ----------------------------------------------------------------------------------
* Copyright  (C) 2008-2009 CENATIC, Centro Nacional
                           de Referencia en TICs de
                           Fuentes Abiertas.
* ----------------------------------------------------------------------------------
*
* This file is part of SLDirect
*
* SLDirect is free software; you can redistribute it and/or modify
* it under the terms of the GNU General Public License as published by
* the Free Software Foundation; version 2 of the License.
*
* SLDirect is distributed in the hope that it will be useful,
* but WITHOUT ANY WARRANTY; without even the implied warranty of
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
* GNU General Public License for more details.
*
* You should have received a copy of the GNU General Public License
* along with bbPress; if not, write to the Free Software
* Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA
*
* ----------------------------------------------------------------------------------
* PHP version 5 ONLY. GPL2 ONLY.
* ----------------------------------------------------------------------------------
*
* @copyright 2008-2009 CENATIC
* @license   http://www.gnu.org/licenses/gpl.txt GNU General Public License v2
* @link      http://www.cenatic.es
*
**/

/* File Auto-generated by Webalianza CodeGen 0.1 <luis@webalianza.com> on 2008-09-12 07:07 */
require_once("models/capacidadOfertaOrganizacion.model.php");
require_once("models/capacidadDemandaOrganizacion.model.php");

class capacidad extends model {

	var $id;
	var $nombre;
	var $descripcion;
	var $categoria;

	function __construct(){
		$this->setRelativeTable("capacidades");
		$this->children("capacidadOfertaOrganizacion","capacidad_id");
		$this->children("capacidadDemandaOrganizacion","capacidad_id");
	}

	function parseValidation(){
		$error_fields = array();;
		$this->nombre = mysql_escape_string($this->nombre);
		$this->descripcion = mysql_escape_string($this->descripcion);
		$this->categoria = mysql_escape_string($this->categoria);

		if (count($error_fields) >0) throw new InvalidModelException(implode(',',$error_fields));
	}

	function onInsert(){
		$this->parseValidation();
	}

	function onUpdate(){
		$this->parseValidation();
	}
}


/*
---BEGIN GENTAG---
YTo1OntzOjI6ImRiIjtzOjc6ImNlbmF0aWMiO3M6NToidGFibGUiO3M6MTE6ImNhcGFjaWRhZGVzIjtzOjU6ImNsYXNzIjtzOjk6
ImNhcGFjaWRhZCI7czo4OiJjaGlsZHJlbiI7YTo2OntpOjA7YToyOntzOjU6Im1vZGVsIjtzOjI3OiJjYXBhY2lkYWRPZmVydGFP
cmdhbml6YWNpb24iO3M6Mzoia2V5IjtzOjEyOiJjYXBhY2lkYWRfaWQiO31pOjE7YToyOntzOjU6Im1vZGVsIjtzOjI4OiJjYXBh
Y2lkYWREZW1hbmRhT3JnYW5pemFjaW9uIjtzOjM6ImtleSI7czoxMjoiY2FwYWNpZGFkX2lkIjt9aToyO2E6Mjp7czo1OiJtb2Rl
bCI7czowOiIiO3M6Mzoia2V5IjtzOjA6IiI7fWk6MzthOjI6e3M6NToibW9kZWwiO3M6MDoiIjtzOjM6ImtleSI7czowOiIiO31p
OjQ7YToyOntzOjU6Im1vZGVsIjtzOjA6IiI7czozOiJrZXkiO3M6MDoiIjt9aTo1O2E6Mjp7czo1OiJtb2RlbCI7czowOiIiO3M6
Mzoia2V5IjtzOjA6IiI7fX1zOjI6ImZrIjthOjY6e2k6MDthOjM6e3M6NToiZmllbGQiO3M6MDoiIjtzOjU6Im1vZGVsIjtzOjA6
IiI7czo3OiJjYXB0aW9uIjtzOjA6IiI7fWk6MTthOjM6e3M6NToiZmllbGQiO3M6MDoiIjtzOjU6Im1vZGVsIjtzOjA6IiI7czo3
OiJjYXB0aW9uIjtzOjA6IiI7fWk6MjthOjM6e3M6NToiZmllbGQiO3M6MDoiIjtzOjU6Im1vZGVsIjtzOjA6IiI7czo3OiJjYXB0
aW9uIjtzOjA6IiI7fWk6MzthOjM6e3M6NToiZmllbGQiO3M6MDoiIjtzOjU6Im1vZGVsIjtzOjA6IiI7czo3OiJjYXB0aW9uIjtz
OjA6IiI7fWk6NDthOjM6e3M6NToiZmllbGQiO3M6MDoiIjtzOjU6Im1vZGVsIjtzOjA6IiI7czo3OiJjYXB0aW9uIjtzOjA6IiI7
fWk6NTthOjM6e3M6NToiZmllbGQiO3M6MDoiIjtzOjU6Im1vZGVsIjtzOjA6IiI7czo3OiJjYXB0aW9uIjtzOjA6IiI7fX19
---END GENTAG---
*/
