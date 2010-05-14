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
admin_header();
$asociacion = new asociacion();
$asociacion->open($_REQUEST["asociacion_id"]);
if (array_key_exists("confirm",$_REQUEST)){
	try{
	$asociacion->delete();
	header("location:asociaciones.php");
	} catch (CannotDeleteHasChildrenException $e){
		?>
		<div style="border:solid 2px red; padding:5px;margin:15px;">
			<b>Error al borrar: Asociaci&oacute;n en uso</b><br />
			La asociaci&oacute;n especificada est&aacute; siendo usada por otras entidades
		</div>
 		<?php
	} catch (Exception $e){
		?>
		<div style="border:solid 2px red; padding:5px;margin:15px;">
			<b>Error al borrar: Error Desconocido</b><br />
			Ocurri&oacute; un error desconocido que impide borrar esta asociacion:<br /><br />
			<?php echo $e->getMessage();?>
		</div>
 		<?php
	}
}
?>
<h1>Asociaci&oacute;n::<?php echo $asociacion->nombre?></h1><br />
Esta segur@ de que desea eliminar la asociacion seleccionada?<br /><br />
<button onclick="document.location='asociacion_delete.php?asociacion_id=<?php echo $_REQUEST["asociacion_id"]?>&confirm=1'">Si, deseo borrarla</button> |
<button onclick="document.location='asociaciones.php'">No, volver al listado</button>
