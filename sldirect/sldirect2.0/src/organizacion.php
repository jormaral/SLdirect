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

include("include.php");
html_header();
$organizacion = new organizacion();
$organizacion->open($_REQUEST["organizacion_id"]);
if ($organizacion->web{4} != ":") $organizacion->web = "http://".$organizacion->web;
$organizacion->localidad = ucfirst($organizacion->localidad);
if (strpos($organizacion->localidad,"-")){
	$exp = explode ("-",$organizacion->localidad);
	foreach ($exp as $a){
		$arr[] = ucfirst($a);
	}
	$organizacion->localidad = implode("-",$arr);
}
if (strpos($organizacion->localidad," ")){
	$exp = explode (" ",$organizacion->localidad);
	foreach ($exp as $a){
		$arr[] = ucfirst($a);
	}
	$organizacion->localidad = implode(" ",$arr);
}



includeLang("organizacion.php",array("organizacion" => $organizacion , "organizacionSedes" => $organizacion->getChildren("organizacionSede"), "asociaciones" => $organizacion->getChildren("organizacionAsociacion")));
html_footer();
?>
