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

header("content-type:source/javascript; charset=iso-8859-15");
include("include.php");
$organizacionSede = new organizacionSede();
$json = "";
echo "[";
foreach ($organizacionSede->getList() as $sede)
{
	$address = "<div style=\\"width:250px;height:180px;\\"><span style=\\"font-size:2.2em\\"><a href=\\"organizacion.php?organizacion_id=".$sede->organizacion_id->id."\\"><b style=\\"font-size:14px;\\">".$sede->organizacion_id->nombre."</b><br />".str_replace("
","",nl2br($sede->organizacion_id->descripcion))."</a><br /><br /><img src=\\"media/icons/16x16/house.png\\" />&nbsp;".$sede->direccion.", ".$sede->localidad."<br /><img src=\\"media/icons/16x16/phone.png\\" /> &nbsp;".($sede->telefonoContacto ? "<a href=\\"dialto:".$sede->telefonoContacto."\\">".$sede->telefonoContacto."</a>" : 'No Disponible')."<br /><img src=\\"media/icons/16x16/user_suit.png\\" /> &nbsp;".($sede->personaContacto ? $sede->personaContacto : 'No Disponible')."<br /><img src=\\"media/icons/16x16/email.png\\" /> &nbsp;".($sede->mailContacto ? "<a href=\\"".$sede->mailContacto."\\">".$sede->mailContacto."</a>" : 'No Disponible')."</span></div>";
	$json .= "{";
	$json .=" organizacion_id:".$sede->organizacion_id->id.",";
	$json .=" address_html:\"".$address."\",";
	$json .=" latitud:".$sede->latitud.",";
	$json .=" longitud:".$sede->longitud;
	
	$json .="},";
}
$json = rtrim($json,",");
echo $json;
echo "]"

?>
