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
$organizacionClasificacion = new organizacionClasificacion();
$organizacionClasificacion->open($_REQUEST["organizacionClasificacion_id"]);
$cat_options =" ";
if (array_key_exists("post",$_REQUEST)){
	try{
		if ($_POST["nombre"] == ""){ throw new Exception("nombre");}
		if ($_POST["codigo"] == ""){ throw new Exception("codigo");}		
		$organizacionClasificacion->nombre = $_POST["nombre"];
		$organizacionClasificacion->codigo = $_POST["codigo"];
		$organizacionClasificacion->update();
		header("location:clasificaciones.php");
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
<h1>Clasificaci&oacute;n::<?php echo $organizacionClasificacion->nombre?></h1>
<form method="post" action="clasificacion_edit.php?organizacionClasificacion_id=<?php echo $_REQUEST["organizacionClasificacion_id"]?>&post=1">
<table class="form">
	<tbody>
		<tr>
			<td>C&oacute;digo</td>
			<td><input type="text" name="codigo" size="6" value="<?php echo $organizacionClasificacion->codigo?>"></td>
		</tr>	

		<tr>
			<td>Nombre</td>
			<td><input type="text" name="nombre" size="50" value="<?php echo $organizacionClasificacion->nombre?>"></td>
		</tr>	
		<tr>
			<td colspan="2">
				<input type="submit" value="Guardar Cambios" />
			</td>
		</tr>
	</tbody>
</table>
</form>
