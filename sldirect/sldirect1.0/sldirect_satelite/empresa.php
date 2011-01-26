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
$organizacion = new organizacion();
$session = new session();
if ($session->username == "") { header ("location:login.php"); die(); }
$errors = array();
html_header();
$organizacion->open($session->userid);
if (array_key_exists("nombre",$_REQUEST)){
 	foreach ($organizacion as $k=>$v){
		if (array_key_exists($k,$_REQUEST))
		{
			if ($k !="password")
			{
				$organizacion->$k = $_REQUEST[$k];
			} else {
				if ($_REQUEST[$k] != "*****")
					$organizacion->$k = md5($_REQUEST[$k]);
			}
		}
	}
	$excp = array();
	try {
		if ($organizacion->nombre == "")  $excp[] = "nombre";
		if ($organizacion->localidad == "") $excp[] = "localidad";
		if ($organizacion->cif == "") $excp[] = "cif";
		if ($organizacion->cif == "") $excp[] = "cif";
		if ($organizacion->username=="") $excp[] = "username";
		if ($organizacion->password=="" || $organizacion->password=="*****" ) $excp[] = "password";
		if (count($excp) > 0 ) throw new Exception (implode (",",$excp));
		
		$organizacion->update();
	} catch (Exception $e){
		$errors = explode (",",$e->getMessage());
	}
}

$organizacion->open($session->userid);
$sedes = $organizacion->getChildren("organizacionSede");
$asociaciones = $organizacion->getChildren("organizacionAsociacion");
$servicios = $organizacion->getChildren("capacidadOfertaOrganizacion");
$demandas = $organizacion->getChildren("capacidadDemandaOrganizacion");


$completeness = 100;
$completeness_reason = "";
if ($organizacion->nombre == "" || $organizacion->descripcion == "" || $organizacion->formaJuridica_id->id == "" || $organizacion->organizacionClasificacion_id->id == "" ||  $organizacion->anoConstitucion == "" || $organizacion->direccion == "" || $organizacion->localidad == "") { $completeness -= 20; $completeness_reason .=" &raquo; Faltan datos generales de la organizaci&oacute;n.<br />"; }
if (count($sedes) < 1) { $completeness -= 20; $completeness_reason .=" &raquo; No ha introducido ninguna sede.<br />"; }
if (count($servicios) < 4) { $completeness -= 10 * (4 -count($servicios)); $completeness_reason .=" &raquo; Ha introducido pocos servicios.<br />"; }
if (count($servicios) < 2) { $completeness -= 10 * (2 -count($servicios)); $completeness_reason .=" &raquo; Ha introducido poca demanda de servicios.<br />";  }


if (count($organizacion))

includeLang("empresa.php",array("errors" => $errors , "organizacion" => $organizacion , "organizacionSedes" => $sedes, "asociaciones" => $asociaciones, "completeness" => $completeness , "completeness_reason" => $completeness_reason));
html_footer();
?>
