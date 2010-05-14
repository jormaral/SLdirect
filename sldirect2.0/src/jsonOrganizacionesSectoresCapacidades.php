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
$capacidadOfertaOrganizacion = new capacidadOfertaOrganizacion();
$results=array();
foreach (explode(",",$_REQUEST["sectores"]) as $sector){
	foreach (explode(",",$_REQUEST["capacidades"]) as $capacidad){
		if ($sector != "" && $capacidad != ""){
			if ($sector == "100"){
				foreach ($capacidadOfertaOrganizacion->getList("capacidad_id=".$capacidad) as $result)
					$results[] = $result;
			} else {
			foreach ($capacidadOfertaOrganizacion->getList("sector_id=".$sector." AND capacidad_id=".$capacidad) as $result)
				$results[] = $result;
			}
		}

	}
}

$json = "";
echo "[";
foreach ($results as $result)
{
	if ($result->organizacion_id->id){
	$json .= "{";
	$json .=" \"organizacion_id\":".$result->organizacion_id->id.",";
	$json .=" \"organizacion_nombre\":\"".$result->organizacion_id->nombre."\",";
	$json .=" \"organizacion_direccion\":\"".$result->organizacion_id->direccion."\",";
	$json .=" \"organizacion_provincia\":\"".$result->organizacion_id->provincia_id->nombre."\",";
	$json .=" \"organizacion_logo_url\":\"".$result->organizacion_id->logo_url."\",";
	$json .=" \"sector_match\":\"".$result->sector_id->nombre."\",";
	$json .=" \"capacidad_match\":\"".$result->capacidad_id->nombre."\"";
	$json .="},";
	}
}
$json = rtrim($json,",");
$json = str_replace ("&amp;","&",$json);
echo $json;
echo "]"

?>
