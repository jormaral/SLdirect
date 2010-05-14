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
$url = "http://" . $settings ["directory.server"] . ":" . $settings ["directory.port"] . $settings ["directory.path"] . "rest/";
$database = database::singleton ("mysql");

$result = simplexml_load_file ($url . "getAssociationList.php");
foreach ($result->association as $row) {
  $item = new asociacion ();
  $item->open ($row ["code"]);
  if ($item->id == null) $database->query ("INSERT into asociaciones (id, nombre) values (" . $row ["code"] . ", \"" . mysql_escape_string ($row) . "\");");
}

$result = simplexml_load_file ($url . "getCapacityList.php");
foreach ($result->capacity as $row) {
  $item = new capacidad ();
  $item->open ($row ["code"]);
  if ($item->id == null) $database->query ("INSERT into capacidades (id, nombre) values (" . $row ["code"] . ", \"" . mysql_escape_string ($row) . "\");");
}

$result = simplexml_load_file ($url . "getOrganizationClassificationList.php");
foreach ($result->classification as $row) {
  $item = new organizacionClasificacion ();
  $item->open ($row ["code"]);
  if ($item->id == null) $database->query ("INSERT into organizaciones_clasificaciones (id, nombre) values (" . $row ["code"] . ", \"" . mysql_escape_string ($row) . "\");");
}

$result = simplexml_load_file ($url . "getOrganizationTypeList.php");
foreach ($result->organizationType as $row) {
  $item = new formaJuridica ();
  $item->open ($row ["code"]);
  if ($item->id == null) $database->query ("INSERT into formas_juridicas (id, nombre) values (" . $row ["code"] . ", \"" . mysql_escape_string ($row) . "\");");
}

$result = simplexml_load_file ($url . "getSectorList.php");
foreach ($result->sector as $row) {
  $item = new sector ();
  $item->open ($row ["code"]);
  if ($item->id == null) $database->query ("INSERT into sectores (id, nombre) values (" . $row ["code"] . ", \"" . mysql_escape_string ($row) . "\");");
}
