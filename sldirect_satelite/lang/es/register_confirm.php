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


if (!$context["done"]){

?>
<div class="breadcrumb"><img src="media/icons/16x16/house.png"><a href="index.php">Inicio</a><img src="media/breadcrumb.png"><img src="media/icons/16x16/wrench.png"><a href="busqueda.php">Zona de Proveedores</a><img src="media/breadcrumb.png">Confirmar Registro de Empresa</div>
<img src="media/registrarTitle.png" alt="Registrar Empresa" />
<p>
	Gracias por registrar su empresa en SLDirect.
</p>
<p> Usted se encuentra accediendo a &eacute;sta p&aacute;gina web por alguno de los siguientes motivos:</p>
<ul>
	<li> Ha procedido al registro de una nueva organizaci&oacute;n en SLDirect, por lo cual ha de proporcionar una contrase&ntilde;a que le identifique para acceder a crear su perfil de empresa. </li>
	<li> Ha olvidado sus datos de acceso a SLDirect y ha procedido a Restaurar su Contrase&ntilde;a de acceso.</li>
</ul>
<p>
	Para continuar, por favor, establezca la contrase&ntilde;a de acceso al directorio para completar el registro en el directorio.
</p>
<p>
<?php
if ($context["differ"]){
	?>
		<p  style="margin:5px;border:solid 4px red;color:red;bakckground:#ffffdd;padding:8px;padding-left:27px;background:url('media/icons/16x16/error.png');background-repeat:no-repeat;background-position:5px 10px; font-size:1.0em !important; text-align:justify"> Las contrase&ntilde;as no coinciden.</p>
	<?php 
}
?>
<form method="post" action="register_confirm.php?hash=<?php echo $_REQUEST["hash"]?>">
<table class="viewForm"><tr><td class="key">
<b>Nueva contrase&ntilde;a :</td><td> <input type="password" name="pass1"></td></tr>
<tr><td class="key">
<b>Repetir contrase&ntilde;a :</td><td> <input type="password" name="pass2"></td></tr></table>
<input type="submit" value="Crear Contrase&ntilde;a" />
</p>
</form>

<?php
} else {
?>

<div class="breadcrumb"><img src="media/icons/16x16/house.png"><a href="index.php">Inicio</a><img src="media/breadcrumb.png"><img src="media/icons/16x16/wrench.png"><a href="busqueda.php">Zona de Proveedores</a><img src="media/breadcrumb.png">Confirmar Registro de Empresa</div>
<img src="media/registrarTitle.png" alt="Registrar Empresa" />
<p>
	Gracias por registrar su empresa en SLDirect. <br /><br />
	Ahora puede iniciar sesi&oacute;n con el nombre de usuario <?php echo $context["username"]?> y la contrase&ntilde;a proporcionada.
</p>
<br />
<span style="font-size:14px;">
<img src="media/icons/16x16/arrow_right.png"><a href="login.php"> Iniciar sesi&oacute;n en SLDirect </a>
</span>
<?php
}
?>
