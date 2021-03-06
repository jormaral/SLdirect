<?php
/**
* SLDirect
* Directorio de Empresas, Servicios y Demandas
* ----------------------------------------------------------------------------------
* Copyright  (C) 2008-2009 CENATIC, Centro Nacional
*                          de Referencia en TICs de
*                          Fuentes Abiertas.
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
*
* @copyright 2008-2009 CENATIC
* @license   http://www.gnu.org/licenses/gpl.txt GNU General Public License v2
* @link      http://www.cenatic.es
*
**/

/* File Auto-generated by Webalianza CodeGen 0.1 <luis@webalianza.com> on 2008-09-12 07:03 */
require_once("models/organizacionAsociacion.model.php");

class asociacion extends model {

	var $id;
	var $nombre;
	var $url;
	var $icon;
	
	function __construct(){
		$this->setRelativeTable("asociaciones");
		$this->children("organizacionAsociacion","asociacion_id");
	}

	function parseValidation(){
		$error_fields = array();;
		$this->nombre = mysql_escape_string($this->nombre);

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
YTo1OntzOjI6ImRiIjtzOjc6ImNlbmF0aWMiO3M6NToidGFibGUiO3M6MTI6ImFzb2NpYWNpb25lcyI7czo1OiJjbGFzcyI7czox
MDoiYXNvY2lhY2lvbiI7czo4OiJjaGlsZHJlbiI7YTo2OntpOjA7YToyOntzOjU6Im1vZGVsIjtzOjIyOiJvcmdhbml6YWNpb25B
c29jaWFjaW9uIjtzOjM6ImtleSI7czoxMzoiYXNvY2lhY2lvbl9pZCI7fWk6MTthOjI6e3M6NToibW9kZWwiO3M6MDoiIjtzOjM6
ImtleSI7czowOiIiO31pOjI7YToyOntzOjU6Im1vZGVsIjtzOjA6IiI7czozOiJrZXkiO3M6MDoiIjt9aTozO2E6Mjp7czo1OiJt
b2RlbCI7czowOiIiO3M6Mzoia2V5IjtzOjA6IiI7fWk6NDthOjI6e3M6NToibW9kZWwiO3M6MDoiIjtzOjM6ImtleSI7czowOiIi
O31pOjU7YToyOntzOjU6Im1vZGVsIjtzOjA6IiI7czozOiJrZXkiO3M6MDoiIjt9fXM6MjoiZmsiO2E6Njp7aTowO2E6Mzp7czo1
OiJmaWVsZCI7czowOiIiO3M6NToibW9kZWwiO3M6MDoiIjtzOjc6ImNhcHRpb24iO3M6MDoiIjt9aToxO2E6Mzp7czo1OiJmaWVs
ZCI7czowOiIiO3M6NToibW9kZWwiO3M6MDoiIjtzOjc6ImNhcHRpb24iO3M6MDoiIjt9aToyO2E6Mzp7czo1OiJmaWVsZCI7czow
OiIiO3M6NToibW9kZWwiO3M6MDoiIjtzOjc6ImNhcHRpb24iO3M6MDoiIjt9aTozO2E6Mzp7czo1OiJmaWVsZCI7czowOiIiO3M6
NToibW9kZWwiO3M6MDoiIjtzOjc6ImNhcHRpb24iO3M6MDoiIjt9aTo0O2E6Mzp7czo1OiJmaWVsZCI7czowOiIiO3M6NToibW9k
ZWwiO3M6MDoiIjtzOjc6ImNhcHRpb24iO3M6MDoiIjt9aTo1O2E6Mzp7czo1OiJmaWVsZCI7czowOiIiO3M6NToibW9kZWwiO3M6
MDoiIjtzOjc6ImNhcHRpb24iO3M6MDoiIjt9fX0=
---END GENTAG---
*/
