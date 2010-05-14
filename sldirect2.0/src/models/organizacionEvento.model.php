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

/* File Auto-generated by Webalianza CodeGen 0.1 <luis@webalianza.com> on 2008-09-12 07:04 */
require_once("models/organizacion.model.php");
require_once("models/asociacion.model.php");

class organizacionEvento extends model {

	var $id;
	var $organizacion_id;
	var $evento_id;

	function __construct(){
		$this->setRelativeTable("organizaciones_eventos");
		$this->Foreign("organizacion_id","organizacion","nombre");
		$this->Foreign("evento_id","evento","id");
	}

	function parseValidation(){
		$error_fields = array();;
		if ($this->organizacion_id == '') $this->organizacion_id = 0;
		if (is_numeric($this->organizacion_id)){
			if ($this->organizacion_id != (int)$this->organizacion_id)
				$error_fields[]="organizacion_id";
		}else{
			$error_fields[]="organizacion_id";}
		if ($this->evento_id == '') $this->evento_id = 0;
		if (is_numeric($this->evento_id)){
			if ($this->evento_id != (int)$this->evento_id)
				$error_fields[]="evento_id";
		}else{
			$error_fields[]="evento_id";}
		$organizacion_check = new organizacion();
		$organizacion_check->open($this->organizacion_id);
		if ($organizacion_check->id != $this->organizacion_id) $error_fields[]="organizacion_id";
		$evento_check = new evento();
		$evento_check->open($this->evento_id);
		if ($evento_check->id != $this->evento_id) $error_fields[]="evento_id";

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
YTo1OntzOjI6ImRiIjtzOjc6ImNlbmF0aWMiO3M6NToidGFibGUiO3M6Mjc6Im9yZ2FuaXphY2lvbmVzX2Fzb2NpYWNpb25lcyI7
czo1OiJjbGFzcyI7czoyMjoib3JnYW5pemFjaW9uQXNvY2lhY2lvbiI7czo4OiJjaGlsZHJlbiI7YTo2OntpOjA7YToyOntzOjU6
Im1vZGVsIjtzOjA6IiI7czozOiJrZXkiO3M6MDoiIjt9aToxO2E6Mjp7czo1OiJtb2RlbCI7czowOiIiO3M6Mzoia2V5IjtzOjA6
IiI7fWk6MjthOjI6e3M6NToibW9kZWwiO3M6MDoiIjtzOjM6ImtleSI7czowOiIiO31pOjM7YToyOntzOjU6Im1vZGVsIjtzOjA6
IiI7czozOiJrZXkiO3M6MDoiIjt9aTo0O2E6Mjp7czo1OiJtb2RlbCI7czowOiIiO3M6Mzoia2V5IjtzOjA6IiI7fWk6NTthOjI6
e3M6NToibW9kZWwiO3M6MDoiIjtzOjM6ImtleSI7czowOiIiO319czoyOiJmayI7YTo2OntpOjA7YTozOntzOjU6ImZpZWxkIjtz
OjE1OiJvcmdhbml6YWNpb25faWQiO3M6NToibW9kZWwiO3M6MTI6Im9yZ2FuaXphY2lvbiI7czo3OiJjYXB0aW9uIjtzOjY6Im5v
bWJyZSI7fWk6MTthOjM6e3M6NToiZmllbGQiO3M6MTM6ImFzb2NpYWNpb25faWQiO3M6NToibW9kZWwiO3M6MTA6ImFzb2NpYWNp
b24iO3M6NzoiY2FwdGlvbiI7czo2OiJub21icmUiO31pOjI7YTozOntzOjU6ImZpZWxkIjtzOjA6IiI7czo1OiJtb2RlbCI7czow
OiIiO3M6NzoiY2FwdGlvbiI7czowOiIiO31pOjM7YTozOntzOjU6ImZpZWxkIjtzOjA6IiI7czo1OiJtb2RlbCI7czowOiIiO3M6
NzoiY2FwdGlvbiI7czowOiIiO31pOjQ7YTozOntzOjU6ImZpZWxkIjtzOjA6IiI7czo1OiJtb2RlbCI7czowOiIiO3M6NzoiY2Fw
dGlvbiI7czowOiIiO31pOjU7YTozOntzOjU6ImZpZWxkIjtzOjA6IiI7czo1OiJtb2RlbCI7czowOiIiO3M6NzoiY2FwdGlvbiI7
czowOiIiO319fQ==
---END GENTAG---
*/