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
?>
<h1>Gestionar Formas Jur&iacute;dicas</h1>
<table class="grid">
	<thead>
		<td>Nombre</td>
		<td>Acciones</td>
	</thead>
	<tbody>
<?php
$formaJuridicaes = new formaJuridica();
foreach ($formaJuridicaes->getList() as $formaJuridica){
	?>
	<tr>
		<td>
			<b><?php echo $formaJuridica->nombre?></b>
		</td>
		<td>
			<a href="formaJuridica_edit.php?formaJuridica_id=<?php echo $formaJuridica->id?>"><img src="../media/icons/16x16/pencil.png"/>&nbsp;<a href="formaJuridica_delete.php?formaJuridica_id=<?php echo $formaJuridica->id?>"><img src="../media/icons/16x16/cancel.png"/>
		</td>
	</tr>
	<?php
}
?>
<tr>
	<td>
		&nbsp;
	</td>
	<td>
		<a href="formaJuridica_add.php"><img src="../media/icons/16x16/add.png" /></a>
	</td>
</tr>
</table>
