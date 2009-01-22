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
?>
<h1>Gestionar Asociaci&oacute;nes</h1>
<table class="grid">
	<thead>
		<td>Nombre</td>
		<td>Icono</td>
		<td>URL</td>
		<td>Acciones</td>
	</thead>
	<tbody>
<?php
$asociaciones = new asociacion();
foreach ($asociaciones->getList() as $asociacion){
	?>
	<tr>
		<td>
			<b><?php echo $asociacion->nombre?></b>
		</td>
		<td>
			<?php echo $asociacion->icon?>
		</td>
		<td> 
			<a style="color:red !important" href="<?php echo $asociacion->url?>" target="new"><?php echo $asociacion->url?></a>
		</td>
		<td>
			<a href="asociacion_edit.php?asociacion_id=<?php echo $asociacion->id?>"><img src="../media/icons/16x16/pencil.png"/>&nbsp;<a href="asociacion_delete.php?asociacion_id=<?php echo $asociacion->id?>"><img src="../media/icons/16x16/cancel.png"/>
		</td>
	</tr>
	<?php
}
?>
<tr>
	<td colspan="3">
		&nbsp;
	</td>
	<td>
		<a href="asociacion_add.php"><img src="../media/icons/16x16/add.png" /></a>
	</td>
</tr>
</table>
