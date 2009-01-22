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

/* File Auto-generated by Webalianza CodeGen 0.1 <luis@webalianza.com> on 2008-09-12 08:14 */
require_once("models/organizacion.model.php");
require_once("models/provincia.model.php");

class organizacionSede extends model {

	var $id;
	var $organizacion_id;
	var $latitud;
	var $longitud;
	var $direccion;
	var $localidad;
	var $provincia_id;
	var $codigoPostal;
	var $hombres;
	var $mujeres;
	var $telefonoContacto;
	var $mailContacto;
	var $personaContacto;

	function __construct(){
		$this->setRelativeTable("organizaciones_sedes");
		$this->Foreign("organizacion_id","organizacion","nombre");
		$this->Foreign("provincia_id","provincia","nombre");
	}

	function parseValidation(){
		$error_fields = array();;
		if ($this->organizacion_id == '') $this->organizacion_id = 0;
		if (is_numeric($this->organizacion_id)){
			if ($this->organizacion_id != (int)$this->organizacion_id)
				$error_fields[]="organizacion_id";
		}else{
			$error_fields[]="organizacion_id";}
		if ($this->latitud == '') $this->latitud = 0;
		if (is_numeric($this->latitud)){
			if ($this->latitud != (float)$this->latitud)
				$error_fields[]="latitud";
		}else{
			$error_fields[]="latitud";}
		if ($this->longitud == '') $this->longitud = 0;
		if (is_numeric($this->longitud)){
			if ($this->longitud != (float)$this->longitud)
				$error_fields[]="longitud";
		}else{
			$error_fields[]="longitud";}
		$this->direccion = mysql_escape_string($this->direccion);
		$this->localidad = mysql_escape_string($this->localidad);
		if ($this->provincia_id == '') $this->provincia_id = 0;
		if (is_numeric($this->provincia_id)){
			if ($this->provincia_id != (int)$this->provincia_id)
				$error_fields[]="provincia_id";
		}else{
			$error_fields[]="provincia_id";}
		if ($this->codigoPostal == '') $this->codigoPostal = 0;
		if (is_numeric($this->codigoPostal)){
			if ($this->codigoPostal != (int)$this->codigoPostal)
				$error_fields[]="codigoPostal";
		}else{
			$error_fields[]="codigoPostal";
		}
		if (is_numeric($this->hombres)){
			if ($this->hombres != (int)$this->hombres)
				$error_fields[]="hombres";
		}else{
			$error_fields[]="hombres";
		}
					
		if (is_numeric($this->mujeres)){
			if ($this->mujeres != (int)$this->mujeres)
				$error_fields[]="mujeres";
		}else{
			$error_fields[]="mujeres";
		}
		$this->telefonoContacto = mysql_escape_string($this->telefonoContacto);
		$this->mailContacto = mysql_escape_string($this->mailContacto);
		$this->personaContacto = mysql_escape_string($this->personaContacto);
		$organizacion_check = new organizacion();
		$organizacion_check->open($this->organizacion_id);
		if ($organizacion_check->id != $this->organizacion_id) $error_fields[]="organizacion_id";
		$provincia_check = new provincia();
		$provincia_check->open($this->provincia_id);
		if ($provincia_check->id != $this->provincia_id) $error_fields[]="provincia_id";

		if (count($error_fields) >0) throw new InvalidModelException(implode(',',$error_fields));
	}

	function onInsert(){
		$this->parseValidation();
	}

	function onUpdate(){
		$this->parseValidation();
	}
}
