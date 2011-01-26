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
$capacidad = new capacidad();
$capacidad->open($_REQUEST["capacidad_id"]);
$cat_options ="<option value=\" \"> </option>";
$capacidades = new capacidad();
foreach ($capacidades->getList(" 1=1 GROUP BY categoria") as $categoria){
	$cat_options .="<option value=\"".$categoria->categoria."\">".$categoria->categoria."</option>";
}
if (array_key_exists("post",$_REQUEST)){
	try{
		if ($_POST["nombre"] == ""){ throw new Exception("nombre");}
		if ($_POST["descripcion"] == ""){ throw new Exception("descripcion");}
		if ($_POST["categoria"] == ""){ throw new Exception("categoria");}
		
		$capacidad->cpc = $_POST["cpc"];
		$capacidad->isic = $_POST["isic"];
		$capacidad->nombre = $_POST["nombre"];
		$capacidad->descripcion = $_POST["descripcion"];
		$capacidad->categoria = $_POST["categoria"];
		$capacidad->update();
		header("location:capacidades.php");
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
<h1>Capacidad::<?php echo $capacidad->nombre?></h1>
<form method="post" action="capacidad_edit.php?capacidad_id=<?php echo $_REQUEST["capacidad_id"]?>&post=1">
<table class="form">
	<tbody>
		<tr>
			<td>CPC</td>
			<td><input type="text" name="cpc" size="5" value="<?php echo $capacidad->cpc?>"></td>
		</tr>
		<tr>
			<td>ISIC</td>
			<td><input type="text" name="isic" size="5" value="<?php echo $capacidad->isic?>"></td>
		</tr>
		<tr>
			<td>Nombre</td>
			<td><input type="text" name="nombre" size="50" value="<?php echo $capacidad->nombre?>"></td>
		</tr>
		<tr>
			<td>Descripci&oacute;n</td>
			<td><textarea name="descripcion" cols="80" rows="10"><?php echo $capacidad->descripcion?></textarea></td>
		</tr>
		<tr>
			<td>Categoria</td>
			<td>
				<select name="cats" onchange="document.getElementById('categoria').value = this.options[this.selectedIndex].value;">
					<?php echo $cat_options?>
				</select>
				<input type="text" id="categoria" name="categoria" value="<?php echo $capacidad->categoria?>" size="20"/>
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
