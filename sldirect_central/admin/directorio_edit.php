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
$directorio = new directorio();
$directorio->open($_REQUEST["directorio_id"]);

if (array_key_exists("post",$_REQUEST)){
	try{
		if ($_POST["username"] == ""){ throw new Exception("username");}
		
		$directorio->username = $_POST["username"];
		$directorio->password = $_POST["password"] == "" ? $directorio->password : md5($_POST["password"]);
		$directorio->update();
		header("location:directorios.php");
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
<h1>Directorio::<?php echo $directorio->username?></h1>
<form method="post" action="directorio_edit.php?directorio_id=<?php echo $_REQUEST["directorio_id"]?>&post=1">
<table class="form">
	<tbody>
		<tr>
			<td>Username</td>
			<td><input type="text" name="username" value="<?php echo $directorio->username?>"/></td>
		</tr>
		<tr>
			<td>Password</td>
			<td><input type="password" name="password"/></td>
		</tr>
		<tr>
			<td colspan="2">
				<input type="submit" value="Guardar Cambios" />
			</td>
		</tr>
	</tbody>
</table>
</form>
