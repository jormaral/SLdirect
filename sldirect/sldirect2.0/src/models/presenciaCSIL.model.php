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

require_once("models/organizacion.model.php");

class presenciaCSIL extends model {

	var $id;
	var $organizacion_id;
	var $dec1;
	var $dec2;
	var $dec3;
	var $horario_morning;
	var $horario_evening;
	var $persona_nombre;
	var $persona_cargo;
	var $persona_email;
	var $persona_telefono;
	var $persona_movil;
	
	function __construct(){
		$this->setRelativeTable("organizaciones_presencia_csil");
		$this->Foreign("organizacion_id","organizacion","");
	}

	function parseValidation(){

		if ($this->organizacion_id == '') $this->organizacion_id = 0;
		if (is_numeric($this->organizacion_id)){
			if ($this->organizacion_id != (int)$this->organizacion_id)
				$error_fields[]="organizacion_id";
		}else{
			$error_fields[]="organizacion_id";}
		$this->persona_nombre = mysql_escape_string ($this->persona_nombre);
		$this->persona_cargo = mysql_escape_string ($this->persona_cargo);
		$this->persona_email = mysql_escape_string ($this->persona_email);
		$this->persona_telefono = mysql_escape_string ($this->persona_telefono);
		$this->persona_movil = mysql_escape_string ($this->persona_movil);
		
		if (count($error_fields) >0) throw new InvalidModelException(implode(',',$error_fields));
	}

	function onInsert(){
		$this->parseValidation();
	}

	function onUpdate(){
		$this->parseValidation();
	}
	
	function afterInsert () {
	}
	function afterUpdate () {
	}
	function afterDelete () {
	}
}
