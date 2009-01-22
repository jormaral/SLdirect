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

include ("include.php");
admin_header();
$asociacion = new asociacion();
$asociacion->open($_REQUEST["asociacion_id"]);

if (array_key_exists("post",$_REQUEST)){
	try{
		if ($_POST["nombre"] == ""){ throw new Exception("nombre");}
		if ($_POST["icon"] == ""){ throw new Exception("icon");}
		if ($_POST["url"] == ""){ throw new Exception("url");}
		
		$asociacion->nombre = $_POST["nombre"];
		$asociacion->icon = $_POST["icon"];
		$asociacion->url = $_POST["url"];
		$asociacion->update();
		header("location:asociaciones.php");
	} catch (Exception $e){
		?>
		<div style="border:solid 2px red; padding:5px;margin:15px;">
			<b>Error en formulario</b><br />
			Los campos <i><?php echo $e->getMessage()?></i> contienen errores.
		</div>
 		<?php
	}
}

?>
<h1>Asociaci&oacute;n::<?php echo $asociacion->nombre?></h1>
<form method="post" action="asociacion_edit.php?asociacion_id=<?php echo $_REQUEST["asociacion_id"]?>&post=1">
<table class="form">
	<tbody>
		<tr>
			<td>Nombre</td>
			<td><input type="text" name="nombre" size="50" value="<?php echo $asociacion->nombre?>"></td>
		</tr>
		<tr>
			<td>URL Icono</td>
			<td><input type="text" id="icon" name="icon" value="<?php echo $asociacion->icon?>" size="60"/></td>
		</tr>
		<tr>
			<td>URL Web Asociaci&oacute;n</td>
			<td>
				<input type="text" id="url" name="url" value="<?php echo $asociacion->url?>" size="60"/>
			</td>
		</tr>
		<tr>
			<td colspan="2">
				<input type="submit" value="Guardar Cambios" />
			</td>
		</tr>
	</tbody>
</table>
</form>
