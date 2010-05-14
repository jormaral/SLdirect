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

?>
<div class="breadcrumb"><img src="media/icons/16x16/house.png"><a href="index.php">Inicio</a><img src="media/breadcrumb.png"><img src="media/icons/16x16/wrench.png"><a href="busqueda.php">Zona de Proveedores</a><img src="media/breadcrumb.png">Iniciar Sesi&oacute;n</div>
<table style="width:100%">
	<tr>
		<td style="width:80%;vertical-align:top;padding-right:30px">
			<img src="media/loginTitle.png" alt="Busqueda Geogr&aacute;fica" />
			<p>	Para iniciar sesi&oacute;n en SLDirect, es necesario poseer un usuario y contrase&ntilde;a proporcionado cuando registra su empresa en el directorio.</p>
			<form method="post" action="login.php" style="<?php echo ($errors ? "border:solid 2px red" : "");?>"/>
				<br /><br />
				<?php
				if ($context["errors"] > 0)
				{
					?>
					<p  style="margin:5px;border:solid 4px red;color:red;bakckground:#ffffdd;padding:8px;padding-left:27px;background:url('media/icons/16x16/error.png');background-repeat:no-repeat;background-position:5px 10px; font-size:1.0em !important; text-align:justify"> Nombre de Usuario o Contrase&ntilde;a no v&aacute;lidos.</p>
					<?php
				}
				?>
				
				<div style="text-align:center">
				<table class="viewForm">
					<tr>
						<td>
							<img src="media/icons/16x16/user.png" alt="Nombre de Usuario"/> <input type="text" name="username" id="username"/><br /><br />
						</td>
					</tr>
					<tr>
						<td>
							<img src="media/icons/16x16/shield.png" alt="Contrase&ntilde;a"/> <input type="password" name="password" /><br /><br />
						</td>
					</tr>
					<tr>
						<td>
							<button style="width:190px;" onclick="document.forms[1].submit();"><img src="media/icons/16x16/arrow_right.png" /> Iniciar Sesi&oacute;n </button><br />
							<button style="width:190px;font-size:10px;background:#fafafa;color:#bbbbbb;margin-top:12px;" onclick="if (document.getElementById('username').value == ''){ alert ('Debe de introducir un nombre de usuario'); return false; } else { document.forms[1].action='lostpw.php'; document.forms[1].submit(); return false}"><img src="media/icons/16x16/arrow_rotate_clockwise.png" /> Restaurar Contrase&ntilde;a </button>
						</td>
					</tr>
				</table>
				</div>
				<br />
				<br />
			</form>
		</td>
		<td style="width:30px;border-right:solid 3px #525252">
			&nbsp;
		</td>
		<td style="padding-left:30px;vertical-align:top;padding-top:45px;">
			<p> Si no dispone de un usuario y contrase&ntilde;a para iniciar sesi&oacute;n en el directorio, puede registrar su organizaci&oacute;n en SLDirect a trav&eacute;s del formulario de registro:</p>
			<div onclick="document.location='registrar.php'"  style="cursor:pointer;margin-top:22px;text-align:justify;width:350px;height:80px;background:url(media/registerIcon.png) no-repeat"><h1 style="font-size:18px;padding-left:24px;margin-bottom:8px;padding-top:8px;">Registrar Empresa</h1>
			</div>
			
		</td>

	</tr>
</table>
