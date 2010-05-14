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

error_reporting(0);
$settings["datadriver.mysql.host"] = "";
$settings["datadriver.mysql.username"] = "";
$settings["datadriver.mysql.password"] = "";
$settings["datadriver.mysql.database"] = "";

$settings["session.auth.source"] = "cenaticOrganization";

$settings["mail.smtp.username"] = "";
$settings["mail.smtp.password"]= "";
$settings["mail.host"] = "";
$settings["mail.mailer"] =  "smtp";
$settings["mail.from"] = "registro@sldirect.cenatic.es";

$settings["baseUrl"] = "";
$settings["sessionpath"] = "";

$settings["gmaps.key"] = "";

$settings ["directory.server"] = "localhost";
$settings ["directory.port"] = 80;
$settings ["directory.path"] = "/sldirect_central/";
$settings ["directory.username"] = "satelite1";
$settings ["directory.password"] = "satelite1";?>
