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
header ("Content-Type: text/xml");
$organizacion = new organizacion ();
$organizacion->open ($_GET ["id"]);
echo ("<organization>
");
echo ("  <legalID>" . $organizacion->cif . "</legalID>
");
echo ("  <name>" . $organizacion->nombre . "</name>
");
echo ("  <description>" . $organizacion->descripcion . "</description>
");
echo ("  <web>" . $organizacion->web . "</web>
");
echo ("  <logo_url>" . $organizacion->logo_url . "</logo_url>
");
echo ("  <HQstreet>" . $organizacion->direccion . "</HQstreet>
");
echo ("  <HQlocality>" . $organizacion->localidad . "</HQlocality>
");
echo ("  <HQprovince code=\"" . $organizacion->provincia_id->id . "\">" . $organizacion->provincia_id->nombre . "</HQprovince>
");
echo ("  <HQpostalCode>" . $organizacion->codigoPostal . "</HQpostalCode>
");
echo ("  <year>" . $organizacion->anoConstitucion . "</year>
");
echo ("  <organizationType code=\"" . $organizacion->formaJuridica_id->id . "\">" . $organizacion->formaJuridica_id->nombre . "</organizationType>
");
echo ("  <organizationClasification code=\"" . $organizacion->organizacionClasificacion_id->id . "\">" . $organizacion->organizacionClasificacion_id->nombre . "</organizationClasification>
");
echo ("  <qualityCertifications>" . $organizacion->certificacionesCalidad . "</qualityCertifications>
");
echo ("  <partners>" . $organizacion->partners . "</partners>
");
echo ("  <researchAndDevelopmentProgrammes>" . $organizacion->participacionImasD . "</researchAndDevelopmentProgrammes>
");
echo ("  <communityRelationships>" . $organizacion->relacionesComunidad . "</communityRelationships>
");
$sedes = $organizacion->getChildren ("organizacionSede");
echo ("  <delegations>
");
foreach ($sedes as $sede) {
  echo ("    <delegation>
");
  echo ("      <street>" . $sede->direccion . "</street>
");
  echo ("      <locality>" . $sede->localidad . "</locality>
");
  echo ("      <province code=\"" . $sede->provincia_id->id . "\">" . $sede->provincia_id->nombre . "</province>
");
  echo ("      <postalCode>" . $sede->codigoPostal . "</postalCode>
");
  echo ("      <contactPhone>" . $sede->telefonoContacto . "</contactPhone>
");
  echo ("      <contactMail>" . $sede->mailContacto . "</contactMail>
");
  echo ("      <contactPerson>" . $sede->personaContacto . "</contactPerson>
");
  echo ("      <locationLatitude>" . $sede->latitud . "</locationLatitude>
");
  echo ("      <locationLongitude>" . $sede->longitud . "</locationLongitude>
");
  echo ("      <numberOfMen>" . $sede->hombres . "</numberOfMen>
");
  echo ("      <numberOfWomen>" . $sede->mujeres . "</numberOfWomen>
");
  echo ("    </delegation>
");
}
echo ("  </delegations>
");
$capacidades = $organizacion->getChildren ("capacidadOfertaOrganizacion");
echo ("  <capacities>
");
foreach ($capacidades as $capacidad) echo ("      <capacity capacity_code=\"" . $capacidad->capacidad_id->id . "\" sector_code=\"" . $capacidad->sector_id->id . "\" resources=\"" . $capacidad->recursos . "\" score=\"" . $capacidad->puntuacion . "\" billingProportion=\"" . $capacidad->porcentajeFacturacion . "\" />
");
echo ("  </capacities>
");
$demandas = $organizacion->getChildren ("capacidadDemandaOrganizacion");
echo ("  <demands>
"); 
foreach ($demandas as $demanda) echo ("    <demand capacity_code=\"" . $demanda->capacidad_id->id . "\" sector_code=\""  .$demanda->sector_id->id . "\" />
");
echo ("  </demands>
");
$asociaciones = $organizacion->getChildren ("organizacionAsociacion");
echo ("  <associationMemberships>
");
foreach ($asociaciones as $asociacion) echo ("    <associationMembership code=\"" . $asociacion->asociacion_id->id . "\" />
");
echo ("  </associationMemberships>
"); 
echo ("  <username>" . $organizacion->username . "</username>
");
echo ("  <password>" . $organizacion->password . "</password>
");
echo ("</organization>
");
