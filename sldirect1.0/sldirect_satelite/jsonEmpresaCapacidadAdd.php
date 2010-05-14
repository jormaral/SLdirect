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

	include ("include.php");
	$organizacion = new organizacion();
	$session = new session();
	if ($session->username == "") header ("location:login.php");
	$organizacion->open($session->userid);
	$capacidadOfertaOrganizacion = new capacidadOfertaOrganizacion();
	$capacidadOfertaOrganizacion->organizacion_id = $organizacion->id;
	$capacidadOfertaOrganizacion->capacidad_id = $_REQUEST["capacidad_id"];
	$capacidadOfertaOrganizacion->sector_id = $_REQUEST["sector_id"];
	$capacidadOfertaOrganizacion->recursos = $_REQUEST["personas"];
	$capacidadOfertaOrganizacion->porcentajeFacturacion = $_REQUEST["porcentaje"];
	$capacidadOfertaOrganizacion->puntuacion = $_REQUEST["puntuacion"];
	try {
		$capacidadOfertaOrganizacion->insert();
		$id = mysql_insert_id();
		$capacidadOfertaOrganizacion->open($id);
		echo (
		"{id: " . $id .
		", capacidad:'".$capacidadOfertaOrganizacion->capacidad_id->nombre.
		"',sector:'".$capacidadOfertaOrganizacion->sector_id->id.
		"',sector_name:'".$capacidadOfertaOrganizacion->sector_id->nombre.
		"',descripcion:'".$capacidadOfertaOrganizacion->capacidad_id->descripcion.
		"',personas:'".$capacidadOfertaOrganizacion->recursos.
		"',porcentaje:'".$capacidadOfertaOrganizacion->porcentajeFacturacion.
		"',puntuacion:'".$capacidadOfertaOrganizacion->puntuacion.
		"',error:'0'}");
	} catch (Exception $e){
		echo "{error:1,fields:'".$e->getMessage()."'}";
	}
?>
