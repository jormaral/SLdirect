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
$formaJuridica = new formaJuridica();
$formaJuridica->open($_REQUEST["formaJuridica_id"]);
$cat_options =" ";
if (array_key_exists("post",$_REQUEST)){
	try{
		if ($_POST["nombre"] == ""){ throw new Exception("nombre");}
		
		$formaJuridica->nombre = $_POST["nombre"];
		$formaJuridica->update();
		header("location:formasJuridicas.php");
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
<h1>Forma Jur&iacute;dica::<?php echo $formaJuridica->nombre?></h1>
<form method="post" action="formaJuridica_edit.php?formaJuridica_id=<?php echo $_REQUEST["formaJuridica_id"]?>&post=1">
<table class="form">
	<tbody>
		<tr>
			<td>Nombre</td>
			<td><input type="text" name="nombre" size="50" value="<?php echo $formaJuridica->nombre?>"></td>
		</tr>	
		<tr>
			<td colspan="2">
				<input type="submit" value="Guardar Cambios" />
			</td>
		</tr>
	</tbody>
</table>
</form>
