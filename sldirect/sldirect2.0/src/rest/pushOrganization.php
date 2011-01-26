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

chdir ("..");
include ("include.php");
$directorio = new directorio ();
try { $directorio->openByField ("token", $_GET ["token"]); }
catch (exception $e) { die ("token error"); }
$xml = simplexml_load_file ("php://input");
$organizacion = new organizacion ();
try { $organizacion->openByField ("cif",  $xml->legalID); }
catch (exception $e) { $organizacion->cif = (string) $xml->legalID; $organizacion->directorio_id = $directorio->id; }
echo($directorio->id . "xxxxxxx " . $organizacion->directorio_id);
if ($organizacion->directorio_id != $directorio->id) die ("directory error");
$organizacion->nombre = (string) $xml->name;
$organizacion->descripcion = (string) $xml->description;
if ($xml->web) $organizacion->web = (string) $xml->web;
$organizacion->direccion = (string) $xml->HQstreet;
$organizacion->localidad = (string) $xml->HQlocality;
$organizacion->formaJuridica_id = (string) $xml->organizationType ["code"];
$organizacion->provincia_id = (string) $xml->HQprovince ["code"];
$organizacion->codigoPostal = (string) $xml->HQpostalCode;
$organizacion->claseEmpresa_id = (string) $xml->organizationClass ["code"];
$organizacion->anoConstitucion = (string) $xml->year;
$organizacion->news_title = (string) $xml->news_title;
$organizacion->news_body = (string) $xml->news_body;
$organizacion->telefono = (string) $xml->telephone;
$organizacion->email = (string) $xml->email;

$organizacion->logo_url = (string) $xml->logo_url;
$organizacion->organizacionClasificacion_id = (string) $xml->organizationClasification ["code"];
$organizacion->certificacionCenatic = $xml->cenaticCertification;
$organizacion->certificacionesCalidad = (string) $xml->qualityCertifications;
$organizacion->partners = (string) $xml->partners; 
$organizacion->participacionImasD = (string) $xml->researchAndDevelopmentProgrammes;
$organizacion->actividadesImasD = $xml->researchAndDevelopmentActivities;
$organizacion->relacionesComunidad = (string) $xml->communityRelationships;
$organizacion->username = (string) $xml->username;
$organizacion->password = (string) $xml->password;

$id = $organizacion->write ();
if (!$organizacion->id) $organizacion->id = $id;
foreach ($organizacion->getChildren ("organizacionSede") as $sede) $sede->delete ();
foreach ($xml->delegations->delegation as $xml2) {
  $sede = new organizacionSede ();
  $sede->organizacion_id = $organizacion->id;
  $sede->latitud = (string) $xml2->locationLatitude;
  $sede->longitud = (string) $xml2->locationLongitude;
  $sede->direccion = (string) $xml2->street;
  $sede->localidad = (string) $xml2->locality;
  $sede->provincia_id = (string) $xml2->province ["code"];
  $sede->codigoPostal = (string) $xml2->postalCode;
  $sede->hombres = (string) $xml2->numberOfMen;
  $sede->mujeres = (string) $xml2->numberOfWomen;
  $sede->telefonoContacto = (string) $xml2->contactPhone;
  $sede->mailContacto = (string) $xml2->contactMail;
  $sede->personaContacto = (string) $xml2->contactPerson;
  $sede->write ();
}
foreach ($organizacion->getChildren ("capacidadOfertaOrganizacion") as $oferta) $oferta->delete ();
foreach ($xml->capacities->capacity as $xml2) {
  $oferta = new capacidadOfertaOrganizacion ();
  $oferta->organizacion_id = $organizacion->id;
  $oferta->capacidad_id = (string) $xml2 ["capacity_code"]; 
  $oferta->sector_id = (string) $xml2 ["sector_code"]; 
  $oferta->recursos = (string) $xml2 ["resources"]; 
  $oferta->porcentajeFacturacion = (string) $xml2 ["billingProportion"];
  $oferta->puntuacion = (string) $xml2 ["score"];
  $oferta->descripcion = (string) $xml2 ["description"];
  $oferta->write ();
}
foreach ($organizacion->getChildren ("evento") as $evento) $evento->delete ();
foreach ($xml->events->event as $xml2) {
  $evento = new evento ();
  $evento->organizacion_id = $organizacion->id;
  $evento->localizacion = (string) $xml2->localization; 
  $evento->titulo = (string) $xml2->title;
  $evento->descripcion = (string) $xml2->description;
  $evento->fecha = (string) $xml2->date;
  $evento->write ();
}

foreach ($organizacion->getChildren ("capacidadDemandaOrganizacion") as $demanda) $demanda->delete ();
foreach ($xml->demands->demand as $xml2) {
  $demanda = new capacidadDemandaOrganizacion ();
  $demanda->organizacion_id = $organizacion->id;
  $demanda->capacidad_id = (string) $xml2 ["capacity_code"]; 
  $demanda->sector_id = (string) $xml2 ["sector_code"];
  $demanda->descripcion = (string) $xml2 ["description"];
  $demanda->write ();
}
foreach ($organizacion->getChildren ("organizacionAsociacion") as $asociacion) $asociacion->delete ();
foreach ($xml->associationMemberships->associationMembership as $xml2) {
  $membership = new organizacionAsociacion ();
  $membership->organizacion_id = $organizacion->id;
  $membership->asociacion_id = (string) $xml2 ["code"];
  $membership->write ();
}
echo ("ok");
