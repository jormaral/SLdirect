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

/* File Auto-generated by Webalianza CodeGen 0.1 <luis@webalianza.com> on 2008-09-12 07:11 */
require_once("models/organizacion.model.php");
require_once("models/capacidad.model.php");
require_once("models/sector.model.php");

class capacidadDemandaOrganizacion extends model {
	var $id;
	var $capacidad_id;
	var $organizacion_id;
	var $sector_id;
	var $descripcion;
	var $anunciado;

	function __construct(){
		$this->setRelativeTable("capacidades_demandas_organizaciones");
		$this->Foreign("organizacion_id","organizacion","");
		$this->Foreign("capacidad_id","capacidad","");
		$this->Foreign("sector_id","sector","");
	}

	function parseValidation(){
		$error_fields = array();;
		$organizacion_check = new organizacion();
		$organizacion_check->open($this->organizacion_id);
		if ($organizacion_check->id != $this->organizacion_id) $error_fields[]="organizacion_id";
		$capacidad_check = new capacidad();
		$capacidad_check->open($this->capacidad_id);
		if ($capacidad_check->id != $this->capacidad_id) $error_fields[]="capacidad_id";
		$sector_check = new sector();
		$sector_check->open($this->sector_id);
		if ($sector_check->id != $this->sector_id) $error_fields[]="sector_id";

		if (count($error_fields) >0) throw new InvalidModelException(implode(',',$error_fields));
	}

	function onInsert(){
		$this->parseValidation();
	}

	function onUpdate(){
		$this->parseValidation();
	}

	function afterInsert () {
		$this->pushToServer ();
	}
	function afterUpdate () {
		$this->pushToServer ();
	}
	function afterDelete () {
		$this->pushToServer ();
	}
	function pushToServer () {
		$organizacion = new organizacion ();
		$organizacion->open (is_object($this->organizacion_id) ? $this->organizacion_id->id : $this->organizacion_id);
		$organizacion->pushToServer ();
	}
}


/*
---BEGIN GENTAG---
YTo1OntzOjI6ImRiIjtzOjc6ImNlbmF0aWMiO3M6NToidGFibGUiO3M6MzQ6ImNhcGFjaWRhZGVzX2RlbWFuZGFfb3JnYW5pemFj
aW9uZXMiO3M6NToiY2xhc3MiO3M6Mjg6ImNhcGFjaWRhZERlbWFuZGFPcmdhbml6YWNpb24iO3M6ODoiY2hpbGRyZW4iO2E6Njp7
aTowO2E6Mjp7czo1OiJtb2RlbCI7czowOiIiO3M6Mzoia2V5IjtzOjA6IiI7fWk6MTthOjI6e3M6NToibW9kZWwiO3M6MDoiIjtz
OjM6ImtleSI7czowOiIiO31pOjI7YToyOntzOjU6Im1vZGVsIjtzOjA6IiI7czozOiJrZXkiO3M6MDoiIjt9aTozO2E6Mjp7czo1
OiJtb2RlbCI7czowOiIiO3M6Mzoia2V5IjtzOjA6IiI7fWk6NDthOjI6e3M6NToibW9kZWwiO3M6MDoiIjtzOjM6ImtleSI7czow
OiIiO31pOjU7YToyOntzOjU6Im1vZGVsIjtzOjA6IiI7czozOiJrZXkiO3M6MDoiIjt9fXM6MjoiZmsiO2E6Njp7aTowO2E6Mzp7
czo1OiJmaWVsZCI7czoxNToib3JnYW5pemFjaW9uX2lkIjtzOjU6Im1vZGVsIjtzOjEyOiJvcmdhbml6YWNpb24iO3M6NzoiY2Fw
dGlvbiI7czowOiIiO31pOjE7YTozOntzOjU6ImZpZWxkIjtzOjEyOiJjYXBhY2lkYWRfaWQiO3M6NToibW9kZWwiO3M6OToiY2Fw
YWNpZGFkIjtzOjc6ImNhcHRpb24iO3M6MDoiIjt9aToyO2E6Mzp7czo1OiJmaWVsZCI7czo5OiJzZWN0b3JfaWQiO3M6NToibW9k
ZWwiO3M6Njoic2VjdG9yIjtzOjc6ImNhcHRpb24iO3M6MDoiIjt9aTozO2E6Mzp7czo1OiJmaWVsZCI7czowOiIiO3M6NToibW9k
ZWwiO3M6MDoiIjtzOjc6ImNhcHRpb24iO3M6MDoiIjt9aTo0O2E6Mzp7czo1OiJmaWVsZCI7czowOiIiO3M6NToibW9kZWwiO3M6
MDoiIjtzOjc6ImNhcHRpb24iO3M6MDoiIjt9aTo1O2E6Mzp7czo1OiJmaWVsZCI7czowOiIiO3M6NToibW9kZWwiO3M6MDoiIjtz
Ojc6ImNhcHRpb24iO3M6MDoiIjt9fX0=
---END GENTAG---
*/
