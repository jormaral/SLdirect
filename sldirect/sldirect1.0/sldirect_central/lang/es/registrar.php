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
<div class="breadcrumb"><img src="media/icons/16x16/house.png"><a href="index.php">Inicio</a><img src="media/breadcrumb.png"><img src="media/icons/16x16/wrench.png"><a href="busqueda.php">Zona de Proveedores</a><img src="media/breadcrumb.png">Registrar Empresa</div>
<table>
	<tr>
		<td>
			<?php
			if (count($context["errors"]) > 0)
			{
				?>
				<p  style="margin:5px;border:solid 4px red;color:red;bakckground:#ffffdd;padding:8px;padding-left:27px;background:url('media/icons/16x16/error.png');background-repeat:no-repeat;background-position:5px 10px; font-size:1.0em !important; text-align:justify"> Se han encontrado errores en el formulario y no puede procesarse el registro.</p>
				<?php
			}
			?>
			<img src="media/registrarTitle.png" alt="Registrar Empresa" />
			<p>
				Cualquier empresa que proporcione servicios basados en Software Libre puede registrarse en el directorio SLDirect.<br /><br />
				Para registrar su empresa en SLDirect necesitamos unos datos b&aacute;sicos con los cuales conformaremos la ficha inicial y la identidad de acceso al directorio 
				de manera que una vez registrados, usted pueda continuar rellenando los datos de su perfil.
				<form method="post" action="registrar.php?do=1">
				<table class="viewForm" style="margin:12px;">
					<tr>
						<td class="key" style="font-weight:bold">
							Nombre de la Organizaci&oacute;n
							
						</td>
						<td colspan="2">
							<input type="text" name="nombre"  <?php if (array_key_exists("nombre",$_REQUEST)) echo "value=\"".$_REQUEST["nombre"]."\"";  ?>  size="25" <?php if (array_search("nombre",$context["errors"])!==false) echo "style=\"border:solid 2px red\""; ?> /><br />
							<?php if (array_search("nombre",$context["errors"])!==false) echo "<span style=\"color:red\">Debe de introducir un nombre para la organizaci&oacute;n</span><br />"; ?>
							<span style="font-size:9px">
								Nombre de la organizaci&oacute;n tal y como aparece en las escrituras de constituci&oacute;n de la misma, o bien marca comercial reconocible de la misma.		
							</span>
						</td>
					</tr>
					<tr>
						<td class="key" style="font-weight:bold">
							Codigo de Identificaci&oacute;n Fiscal
						</td>
						<td colspan="2">
							<input type="text" name="cif" <?php if (array_key_exists("cif",$_REQUEST)) echo "value=\"".$_REQUEST["cif"]."\"";  ?> size="10" <?php if (array_search("cif",$context["errors"])!==false) echo "style=\"border:solid 2px red\""; ?>/><br />
							<?php if (array_search("cif",$context["errors"])!==false) echo "<span style=\"color:red\">El CIF/NIF introducido no es v&aacute;lido o ya existe</span><br />"; ?>
							<span style="font-size:9px">
								CIF o NIF de la organizaci&oacute;n a insertar en el directorio.
							</span>
						</td>
					</tr>
					<tr>
						<td class="key" style="font-weight:bold">
							Direcci&oacute;n de Correo Electr&oacute;nico
						</td>
						<td colspan="2">
							<input type="text" name="mail" size="20"  <?php if (array_key_exists("mail",$_REQUEST)) echo "value=\"".$_REQUEST["mail"]."\"";  ?>  <?php if (array_search("mail",$context["errors"])!==false) echo "style=\"border:solid 2px red\""; ?> /><br />
							<?php if (array_search("mai",$context["errors"])!==false) echo "<span style=\"color:red\">La direcci&oacute;n de correo electr&oacute;nico proporcionada no es v&aacute;lida o ya existe.</span><br />"; ?>
							<span style="font-size:9px">
								Direcci&oacute;n a la que ser&aacute;n enviadas las credenciales de inicio de sesi&oacute;n iniciales al directorio.
							</span>
						</td>
					</tr>
				</table>
				<table class="viewForm" style="margin:12px;width:100%;margin-top:0px">
					<tr>
						<td class="key" style="width:95%" colspan="2">
							He leido y acepto los <a href="terminos.php">t&eacute;rminos y condiciones de uso</a> de SLDirect
						</td>
						<td>
							<input id="terms" type="checkbox" name="terms" onchange="if ($('#terms').attr('checked') && $('#privacy').attr('checked')) {  $('#submitter').attr('disabled','') ; $('#submitter').disabled = false; $('#disclaimer').css('display','none');} else { $('#submitter').attr('disabled','disabled') ; $('#submitter').disabled = true;  $('#disclaimer').css('display',''); }"/>
						</td>
					</tr>
					<tr>
						<td class="key" style="width:95%" colspan="2">
							He leido y acepto la <a href="privacidad.php">pol&iacute;tica de privacidad</a> de SLDirect
						</td>
						<td>
							<input id="privacy" type="checkbox" name="privacy" onchange="if ($('#terms').attr('checked') && $('#privacy').attr('checked')) {  $('#submitter').attr('disabled','') ; $('#submitter').disabled = false; $('#disclaimer').css('display','none');} else { $('#submitter').attr('disabled','disabled') ; $('#submitter').disabled = true;  $('#disclaimer').css('display',''); }"/>
						</td>
					</tr>
					<tr>
						<td colspan="3" style="text-align:center">
							<p id="disclaimer" style="margin:5px;border:solid 4px;padding:8px;padding-left:27px;background:url('media/icons/16x16/error.png');background-repeat:no-repeat;background-position:5px 10px; font-size:1.0em !important; text-align:justify"> Debe de aceptar los t&eacute;rminos y condiciones de uso y la pol&iacute;tica de privacidad para activar el bot&oacute;n de registro.</p>
							<input id="submitter" disabled type="submit" style="font-size:1.8em" value="Registrar Empresa en SLDirect">
						</td>
					</tr>
					<tr>
						<td colspan="3" class="key" style="font-size:0.9em; text-align:justify">
						Por favor, compruebe los t&eacute;rminos y condiciones de uso, as&iacute; como la pol&iacute;tica de privacidad del sitio antes de confirmar el registro en SLDirect. Al hacer click en "Registrar empresa en SLDirect" usted autoriza a Cenatic a enviarle un correo electr&oacute;nico de confirmaci&oacute;n. 
						Consulte la pol&iacute;tica de privacidad y los t&eacute;rminos y condiciones de uso para conocer los m&eacute;todos mediante los cuales acceder a su derecho de comprobaci&oacute;n, rectificaci&oacute;n y cancelaci&oacute;n de datos personales.
						</td>
					</tr>
					
				</table>

				</form>
			</p>
		</td>
		<td style="vertical-align:bottom;padding-top:0px">
			<img src="media/procesoRegistro.png" />
		</td>
	</tr>
</table>


<script>
		$(window).bind("load",function(){
			$.each($("input"), function(i,item){
				item.checked = false;
			});
			
			$('#submitter').attr("disabled","disabled");
			$('#submitter').disabled=true;
		});
	</script>
