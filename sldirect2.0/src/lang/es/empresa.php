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

error_reporting(0);
?>
<div class="breadcrumb"><img src="media/icons/16x16/house.png"><a href="index.php">Inicio</a><img src="media/breadcrumb.png"><img src="media/icons/16x16/wrench.png"><a href="busqueda.php">Zona de Proveedores</a><img src="media/breadcrumb.png">Ficha de Empresa de <?php echo $context["organizacion"]->nombre?></div>
<script src="http://maps.google.com/maps?file=api&amp;v=2&amp;key=<?php global $settings; echo $settings["gmaps.key"]?>" type="text/javascript"></script>
<script type="text/javascript">
$(document).ready(function(){
  $('.certificacionesCalidad:last').change(addCertificacionesInput);
});
function addCertificacionesInput() {
	if($('.certificacionesCalidad:last').val()!='') $('#certificacionesCalidadTable').append("<tr><td></td><td><input type=\"text\" class=\"certificacionesCalidad\" size=\"34\" name=\"certificacionesCalidad[]\"/></td></tr>");
	$('.certificacionesCalidad:last').change(addCertificacionesInput);
	$('.certificacionesCalidad:last').focus();
}

function clearCertificaciones() {
	$('#certificacionesCalidadTable').html ("<tr><td> Por favor indique cuales:</td></td><td><input type=\"text\" class=\"certificacionesCalidad\" size=\"34\" name=\"certificacionesCalidad[]\"/></td></tr>");
	$('.certificacionesCalidad:last').change(addCertificacionesInput);
}
</script>
<div id="tabs">
            <ul>
                <li class="ui-tabs-nav-item"><a href="#general"><span><img src="media/icons/16x16/book.png" /><?php echo $context["organizacion"]->nombre?></span></a></li>
                <li class="ui-tabs-nav-item"><a href="#asociaciones"><span><img src="media/icons/16x16/map.png" /> Asociaciones </span></a></li>
                <li class="ui-tabs-nav-item"><a href="#sedes"><span><img src="media/icons/16x16/map.png" /> Sedes </span></a></li>
                <li class="ui-tabs-nav-item"><a href="#capacidades"><span><img src="media/icons/16x16/wrench.png" /> Servicios</span></a></li>
                <li class="ui-tabs-nav-item"><a href="#demandas"><span><img src="media/icons/16x16/wrench_orange.png" /> Demanda</span></a></li>
				<li class="ui-tabs-nav-item"><a href="#eventos"><span><img src="media/icons/16x16/calendar.png" /> Eventos</span></a></li>
				<!--<li class="ui-tabs-nav-item"><a href="#congreso"><span><img src="media/icons/16x16/lorry.png" /> Presencia CISL</span></a></li>-->
            </ul>
<div id="general" class="ui-tabs-panel">
<form methd="post" action="empresa.php">
<table class="viewForm" style="border-spacing:0px">
	<?php
	if ($context["completeness"] < 100){
	?>	
	<tr>
		<td style="border-left:solid 4px #a21170;border-top:solid 4px #a21170;border-bottom:solid 4px #a21170">
			<table>
				<tr>
					<td>
						<table style="width:150px;border:inset 1px black">
							<tr>
								<td style="width:<?php echo $context["completeness"]?>%;border:outset 1px;background:#af228a;overflow:hidden">

								</td>
								<td style="width:<?php echo 100-$context["completeness"]?>%;overflow:hidden;">
									&nbsp;
								</td>
							</tr>
						</table>
					</td>
					<td>
						<h1><?php echo $context["completeness"]?>%</h1>
					</td>
				</tr>
			</table>
		</td>
		<td style="border-right:solid 4px #a21170;border-top:solid 4px #a21170;border-bottom:solid 4px #a21170;">
			<h2 style="margin:0px;">Su perfil no est&aacute; completo!</h2>
			<span style="font-weight:normal">Su organizaci&oacute;n no se mostrar&aacute; correctamente en SLDirect dado que no ha introducido todos los datos requeridos:</span><br />
			<?php echo $context["completeness_reason"];?>
			
		</td>
	</tr>
	<?php
	}
	?>
	<tr>
		<td class="key" colspan="2">
			&nbsp;
		</td>
	</tr>
	<tr>
		<td class="key">

		</td>
		<td style="text-align:right">

			URL a Imagen de Logotipo: <input type="text" name="logo_url" value="<?php echo $context["organizacion"]->logo_url?>" style="width:200px"/>
			<?php echo (in_array("logo_url",$context["errors"]) ? "<div class=\"error\" style=\"margin-left:230px;width:320px\"><img src=\"media/icons/16x16/cancel.png\">La URL del logotipo introducida no es v&aacute;lida.</div>" : "")?>
			<br /><img id="logo" src="<?php echo $context["organizacion"]->logo_url?>" />
		</td>
	</tr>
	<tr>
		<td class="key">
			Raz&oacute;n Social
		</td>
		<td>
			<input type="text" size="30" name="nombre" value="<?php echo (array_key_exists("nombre" ,$_REQUEST) ? $_REQUEST["nombre"] : $context["organizacion"]->nombre)?>" /><?php echo (in_array("nombre",$context["errors"]) ? "<div class=\"error\"><img src=\"media/icons/16x16/cancel.png\">Debe de introducir un nombre para la Empresa</div>" : "")?>
		</td>
	</tr>
	<tr>
		<td class="key">
			Direcci&oacute;n Web
		</td>
		<td>
			<input type="text" name="web" size="30" value="<?php echo (array_key_exists("web" ,$_REQUEST) ? $_REQUEST["web"] : $context["organizacion"]->web)?>" />
			<?php echo (in_array("web",$context["errors"]) ? "<div class=\"error\"><img src=\"media/icons/16x16/cancel.png\">La URL introducida no es v&aacute;lida.</div>" : "")?>
		</td>
	</tr>
	<tr>
		<td class="key">
			Clasificaci&oacute;n
		</td>
		<td>

			<select name="organizacionClasificacion_id" style="width:100%">
			<?php
			$organizacionClasificaciones = new organizacionClasificacion();
			foreach ($organizacionClasificaciones->getList("1=1 ORDER BY nombre ASC") as $organizacionClasificacion)
			{
				?>
				<option <?php echo ($context["organizacion"]->organizacionClasificacion_id->id == $organizacionClasificacion->id ? "selected" : "")?> value="<?php echo $organizacionClasificacion->id?>"><?php echo $organizacionClasificacion->nombre?></option>
				<?php
			}
		?>
			</select>
			<?php echo (in_array("organizacionClasificacion_id",$context["errors"]) ? "<div class=\"error\"><img src=\"media/icons/16x16/cancel.png\">La Clasificacion introducida no es v&aacute;lida.</div>" : "")?>
			
		</td>
	</tr>
	<tr>
		<td class="key">
			Forma Jur&iacute;dica
		</td>
		<td>

			<select name="formaJuridica_id">
			<?php
			$formasJuridicas = new formaJuridica();
			foreach ($formasJuridicas->getList("1=1 ORDER BY nombre ASC") as $formaJuridica)
			{
				?>
				<option <?php echo ($context["organizacion"]->formaJuridica_id->id == $formaJuridica->id ? "selected" : "")?> value="<?php echo $formaJuridica->id?>"><?php echo $formaJuridica->nombre?></option>
				<?php
			}
		?>
			</select>
		</td>
	</tr>
	<tr>
		<td class="key">
			Descripci&oacute;n de los Servicios
		</td>
		<td style="font-size:1.1em;">
			<textarea id="descripcion" name="descripcion" rows=4><?php echo $context["organizacion"]->descripcion?></textarea>
			<div id="charlimitinfo"></div>
		</td>
	</tr>
	
	<tr>
		<td class="key">
			CIF
		</td>
		<td>
			<input type="text" size="9" name="cif" value="<?php echo (array_key_exists("cif" ,$_REQUEST) ? $_REQUEST["cif"] : $context["organizacion"]->cif)?>" /><?php echo (in_array("cif",$context["errors"]) ? "<div class=\"error\"><img src=\"media/icons/16x16/cancel.png\">El CIF introducido no es v&aacute;lido</div>" : "")?>
		</td>
	</tr>
	<tr>
		<td class="key">
			A&ntilde;o de Constituci&oacute;n
		</td>
		<td>
			<input type="text" size="4" name="anoConstitucion" value="<?php echo (array_key_exists("anoConstitucion" ,$_REQUEST) ? $_REQUEST["anoConstitucion"] : $context["organizacion"]->anoConstitucion)?>" /><?php echo (in_array("anoConstitucion",$context["errors"]) ? "<div class=\"error\"><img src=\"media/icons/16x16/cancel.png\">El a&ntilde;o de Constituci&oacute;n Introducido no es V&aacute;lido.</div>" : "")?>
		</td>
	</tr>
	<tr>
		<td class="key">
			N&uacute;mero Total de Empleados
		</td>
		<td style="font-weight:normal">
			<b>
			<?php
			$emp_totales = $hom_totales = $muj_totales = 0;
			foreach ($context["organizacionSedes"] as $sede){
				$emp_totales += $sede->hombres + $sede->mujeres;
				$hom_totales += $sede->hombres;
				$muj_totales += $sede->mujeres;
			}
			echo $emp_totales;
			?>
			</b> personas. (<b><?php echo $muj_totales?></b> mujeres y <b><?php echo $hom_totales?></b> hombres)<br />
			<span style="font-size:0.8em"> Dato Calculado a partir de la informaci&oacute;n introducida en el apartado <i>sedes</i></span>
		</td>
	</tr>
	<?php /*
	<tr>
		<td class="key">
			Clase de Empresa
		</td>
		<td>

			<select name="claseEmpresa_id">
			<?php
			$clasesEmpresas = new claseEmpresa();
			foreach ($clasesEmpresas->getList("1=1 ORDER BY nombre ASC") as $claseEmpresa)
			{
				?>
				<option <?php echo ($context["organizacion"]->claseEmpresa_id->id == $claseEmpresa->id ? "selected" : "")?> value="<?php echo $claseEmpresa->id?>"><?php echo $claseEmpresa->nombre?></option>
				<?php
			}
		?>
			</select><?php echo (in_array("claseEmpresa_id",$context["errors"]) ? "<div class=\"error\"><img src=\"media/icons/16x16/cancel.png\">Debe de introducir una clase de Empresa</div>" : "")?>
		</td>
	</tr>
	<tr>
	*/ ?>
		<td colspan="2" class="separator">
			Domicilio Social
		</td>
	</tr>
	<tr>
		<td colspan="2">
			Introduzca en este apartado los datos del domicilio social de su organizaci&oacute;n. Para que su organizaci&oacute;n aparezca en los mapas y en la localizaci&oacute;n por provincias, 
			debe rellenar al menos una sede en el apartado de sedes.
		</td>
	</tr>
	<tr>
		<td class="key">
			Direcci&oacute;n
		</td>
		<td>
			<input type="text" size="40" name="direccion" value="<?php echo (array_key_exists("direccion" ,$_REQUEST) ? $_REQUEST["direccion"] : $context["organizacion"]->direccion)?>" /><?php echo (in_array("direccion",$context["errors"]) ? "<div class=\"error\"><img src=\"media/icons/16x16/cancel.png\">Debe de introducir una direcci&oacute;n.</div>" : "")?>
		</td>
	</tr>
	<tr>
		<td class="key">
			C&oacute;digo Postal
		</td>
		<td>
			<input type="text" size="5" name="codigoPostal" value="<?php echo (array_key_exists("codigoPostal" ,$_REQUEST) ? $_REQUEST["codigoPostal"] : $context["organizacion"]->codigoPostal)?>" /><?php echo (in_array("codigoPostal",$context["errors"]) ? "<div class=\"error\"><img src=\"media/icons/16x16/cancel.png\">Debe de introducir un C&oacute;digo Postal v&aacute;lido</div>" : "")?>
		</td>
	</tr>

	<tr>
		<td class="key">
			Localidad
		</td>
		<td>
			<input type="text" size="15" name="localidad" value="<?php echo (array_key_exists("localidad" ,$_REQUEST) ? $_REQUEST["localidad"] : $context["organizacion"]->localidad)?>" /><?php echo (in_array("localidad",$context["errors"]) ? "<div class=\"error\"><img src=\"media/icons/16x16/cancel.png\">Debe de introducir una localidad.</div>" : "")?>
		</td>
	</tr>

	<tr>
		<td class="key">
			Provincia
		</td>
		<td>

			<select name="provincia_id">
			<?php
			$provincias = new provincia();
			foreach ($provincias->getList("1=1 ORDER BY nombre ASC") as $provincia)
			{
				?>
				<option <?php echo ($context["organizacion"]->provincia_id->id == $provincia->id ? "selected" : "")?> value="<?php echo $provincia->id?>"><?php echo $provincia->nombre?></option>
				<?php
			}
		?>
			</select>
		</td>
	</tr>
	<tr>
		<td class="key">
			Tel&eacute;fono
		</td>
		<td>
			<input type="text" size="15" name="telefono" value="<?php echo (array_key_exists("telefono" ,$_REQUEST) ? $_REQUEST["telefono"] : $context["organizacion"]->telefono)?>" /><?php echo (in_array("telefono",$context["errors"]) ? "<div class=\"error\"><img src=\"media/icons/16x16/cancel.png\">Debe de introducir un Tel&eacute;fono</div>" : "")?>
		</td>
	</tr>
	<tr>
		<td class="key">
			Correo Electr&oacute;nico
		</td>
		<td>
			<input type="text" size="40" name="email" value="<?php echo (array_key_exists("email" ,$_REQUEST) ? $_REQUEST["email"] : $context["organizacion"]->email)?>" /><?php echo (in_array("email",$context["errors"]) ? "<div class=\"error\"><img src=\"media/icons/16x16/cancel.png\">Debe de introducir un Correo electr&oacute;nico v&aacute;lido</div>" : "")?>
		</td>
	</tr>
	
	<tr>
		<td colspan="2" class="separator">
			Carrusel de Noricias
		</td>
	</tr>
	<tr>
		<td colspan="2">
			Por favor, introduzca la informaci&oacute;n que desea que se muestre en el carrusel de noticias de la p&aacute;gina principal.
		</td>
	</tr>
	<tr>
		<td class="key">
			T&iacute;tulo
		</td>
		<td s>
			<input size="40" type="text" name="news_title" value="<?php echo (array_key_exists("news_title" ,$_REQUEST) ? $_REQUEST["news_title"] : $context["organizacion"]->news_title)?>"/>
		</td>
	</tr>
	<tr>
		<td class="key">
			Noticia
		</td>
		<td style="font-size:1.1em;">
			<textarea name="news_body"><?php echo (array_key_exists("news_body" ,$_REQUEST) ? $_REQUEST["news_body"] : $context["organizacion"]->news_body)?></textarea>
		</td>
	</tr>
	<tr>
		<td colspan="2" class="separator">
			Informaci&oacute;n Adicional
		</td>
	</tr>
	<tr>
		<td colspan="2">
			Por favor, marque las casillas correspondientes en el caso de que su empresa posea alg&uacute;n tipo de certificaci&oacute;n, participe en programas de I+D, trabaje junto a partners en proyectos, o tenga relaciones con la Comunidad y explique cuando sea necesario.			
		</td>
	</tr>

	<tr>
		<td class="key">
			Certificacion Cenatic
		</td>
		<td>
			<?php
			$certificacionesCalidadChecked = false;
			if ($context["organizacion"]->certificacionesCalidad || array_key_exists("certificacionesCalidadChecked",$_REQUEST)) {$certificacionesCalidadChecked = true; }
			?>
			<input name="certificacionCenatic" value="1" type="checkbox" <?php if ($context["organizacion"]->certificacionCenatic==1) echo "checked" ?> />
		</td>
	</tr>
	<tr>
		<td class="key" style="vertical-align:top;padding-top:11px;">
			Certificaciones de Calidad
		</td>
		<td>
			<?php
			$certificacionesCalidadChecked = false;
			$certificados = unserialize(base64_decode($context["organizacion"]->certificacionesCalidad));
			if (count($certificados) > 0 || array_key_exists("certificacionesCalidadChecked",$_REQUEST)) {$certificacionesCalidadChecked = true; }
			
			
			?>
			<input style="margin-top:9px;" name="certificacionesCheck" type="checkbox" <?php if ($certificacionesCalidadChecked) echo "checked" ?> onchange=" if (this.checked) { $('#certificacionesCalidadInput').css('display','inline');} else { $('#certificacionesCalidadInput').css('display','none'); clearCertificaciones(); }"/>
			<div id="certificacionesCalidadInput" style="vertical-align:top;display: <?php if ($certificacionesCalidadChecked) { echo "inline"; } else { echo "none"; } ?>;margin-left:4px">
				<table style="font-size:0.8em;display:inline;" id="certificacionesCalidadTable">
				<?php		
				foreach($certificados as $key => $certificado){
					echo (($key==0?"<tr><td> Por favor indique cuales:</td>":"<td></td>") . "<td><input type=\"text\" class=\"certificacionesCalidad\" size=\"34\" name=\"certificacionesCalidad[]\" value=\"" . $certificado . "\"/></td></tr>");
				}
				echo (($key==0?"<tr><td> Por favor indique cuales:</td>":"<td></td>") . "<td><input type=\"text\" class=\"certificacionesCalidad\" size=\"34\" name=\"certificacionesCalidad[]\" /></td></tr>");
				
				?>
				</table>
			</div>
		</td>
	</tr>
	
	<tr>
		<td class="key">
			Partners
		</td>
		<td>
			<?php
			$partnersChecked = false;
			if ($context["organizacion"]->partners || array_key_exists("partnersChecked",$_REQUEST)) {$partnersChecked = true; }
			?>
			<input type="checkbox" <?php if ($partnersChecked) echo "checked" ?> onchange=" if (this.checked) { $('#partnersInput').css('display','inline');} else { $('#partnersInput').css('display','none'); }"/>
			<div id="partnersInput" style="display: <?php if ($partnersChecked) { echo "inline"; } else { echo "none"; } ?>;margin-left:12px;"> Por favor indique cuales:&nbsp;&nbsp;<input type="text" size="34" name="partners" value="<?php echo (array_key_exists("partners" ,$_REQUEST) ? $_REQUEST["partners"] : $context["organizacion"]->partners)?>" /><?php echo (in_array("partners",$context["errors"]) ? "<div class=\"error\"><img src=\"media/icons/16x16/cancel.png\">El valorIntroducido no es V&aacute;lido.</div>" : "")?></div>
		</td>
	</tr>
	<tr>
		<td class="key">
			Realiza Actividades I+D
		</td>
		<td>
			<?php
			$actividadesImasDChecked = false;
			if ($context["organizacion"]->actividadesImasD || array_key_exists("actividadesImasDChecked",$_REQUEST)) {$actividadesImasDChecked = true; }
			?>
			<input name="actividadesImasD" value="1" type="checkbox" <?php if ($context["organizacion"]->actividadesImasD==1) echo "checked" ?> />
		</td>
	</tr>
	<tr>
		<td class="key">
			Participaci&oacute;n en programas de I+D
		</td>
		<td>
			<?php
			$participacionImasDChecked = false;
			if ($context["organizacion"]->participacionImasD || array_key_exists("participacionImasDChecked",$_REQUEST)) {$participacionImasDChecked = true; }
			?>
			<input type="checkbox" <?php if ($participacionImasDChecked) echo "checked" ?> onchange=" if (this.checked) { $('#participacionImasDInput').css('display','inline');} else { $('#participacionImasDInput').css('display','none'); }"/>
			<div id="participacionImasDInput" style="display: <?php if ($participacionImasDChecked) { echo "inline"; } else { echo "none"; } ?>;margin-left:12px;"> Por favor indique en cuales:&nbsp;&nbsp;<input type="text" size="34" name="participacionImasD" value="<?php echo (array_key_exists("participacionImasD" ,$_REQUEST) ? $_REQUEST["participacionImasD"] : $context["organizacion"]->participacionImasD)?>" /><?php echo (in_array("participacionImasD",$context["errors"]) ? "<div class=\"error\"><img src=\"media/icons/16x16/cancel.png\">El valorIntroducido no es V&aacute;lido.</div>" : "")?></div>
		</td>
	</tr>
	
	<tr>
		<td class="key">
			Relaciones con la Comunidad
		</td>
		<td>
			<?php
			$relacionesComunidadChecked = false;
			if ($context["organizacion"]->relacionesComunidad || array_key_exists("relacionesComunidadChecked",$_REQUEST)) {$relacionesComunidadChecked = true; }
			?>
			<input type="checkbox" <?php if ($relacionesComunidadChecked) echo "checked" ?> onchange=" if (this.checked) { $('#relacionesComunidadInput').css('display','inline');} else { $('#relacionesComunidadInput').css('display','none'); }"/>
			<div id="relacionesComunidadInput" style="display: <?php if ($relacionesComunidadChecked) { echo "inline"; } else { echo "none"; } ?>;margin-left:12px;"> Por favor indique con qu&eacute; comunidades:&nbsp;&nbsp;<input type="text" size="24" name="relacionesComunidad" value="<?php echo (array_key_exists("relacionesComunidad" ,$_REQUEST) ? $_REQUEST["relacionesComunidad"] : $context["organizacion"]->relacionesComunidad)?>" /><?php echo (in_array("relacionesComunidad",$context["errors"]) ? "<div class=\"error\"><img src=\"media/icons/16x16/cancel.png\">El valorIntroducido no es V&aacute;lido.</div>" : "")?></div>
		</td>
	</tr>
	<tr>
		<td class="key">
			Pertenencia a grupo Empresarial
		</td>
		<td>
			<?php
			$grupoEmpresarialChecked = false;
			if ($context["organizacion"]->grupoEmpresarial || array_key_exists("grupoEmpresarialChecked",$_REQUEST)) {$grupoEmpresarialChecked = true; }
			?>
			<input type="checkbox" <?php if ($grupoEmpresarialChecked) echo "checked" ?> onchange=" if (this.checked) { $('#grupoEmpresarialInput').css('display','inline');} else { $('#grupoEmpresarialInput').css('display','none'); }"/>
			<div id="grupoEmpresarialInput" style="display: <?php if ($grupoEmpresarialChecked) { echo "inline"; } else { echo "none"; } ?>;margin-left:12px;"> Por favor indique el nombre del Grupo Empresarial: <input type="text" size="24" name="grupoEmpresarial" value="<?php echo (array_key_exists("grupoEmpresarial" ,$_REQUEST) ? $_REQUEST["grupoEmpresarial"] : $context["organizacion"]->grupoEmpresarial)?>" /><?php echo (in_array("grupoEmpresarial",$context["errors"]) ? "<div class=\"error\"><img src=\"media/icons/16x16/cancel.png\">El valorIntroducido no es V&aacute;lido.</div>" : "")?></div>
		</td>
	</tr>
	
	<tr>
		<td colspan="2" class="separator">
			Identidad de Acceso
		</td>
	</tr>
	<tr>
		<td class="key">
			Usuario
		</td>
		<td>
			<?php echo $context["organizacion"]->username; ?>
		</td>
	</tr>
	<tr>
		<td class="key">
			Contrase&ntilde;a
		</td>
		<td>
			<input type="password"  size="8" name="password" onfocus="this.value=''" value="*****" /><?php echo (in_array("password",$context["errors"]) ? "<div class=\"error\"><img src=\"media/icons/16x16/cancel.png\">Contrase&ntilde;a inv&aacute;lida.</div>" : "")?>
		</td>
	</tr>
	
	
</table>
</form>
<button onclick="document.forms[1].submit();"><img src="media/icons/16x16/disk.png" />Guardar cambios a ficha de Empresa</button>
</div>
<div id="asociaciones" class="ui-tabs-panel">
<table>
	<h2>Asociaciones a las que <?php echo $context["organizacion"]->nombre?> pertenece </h2>
	<?php
	foreach ($context["asociaciones"] as $asociacion){
	?>	
		<tr>
			<td>
				<a href="<?php echo $asociacion->asociacion_id->url?>"><img src="<?php echo $asociacion->asociacion_id->icon?>"a /></a><img>
			</td>			
			<td>
				<button onclick="document.location='empresaAsociacionDelete.php?organizacionAsociacion_id=<?php echo $asociacion->id?>';return false"><img src="media/icons/16x16/delete.png"> Eliminar pertenencia a <?php echo $asociacion->asociacion_id->nombre ?>
		</tr>
	<?php
	}
	?>
</table>
	<h2>Insertar asociaci&oacute;n a la lista</h2>
	<form method="post" action="empresaAsociacionAdd.php?organizacion_id=<?php echo $context["organizacion"]->id?>">
	<table>
		<tr>
			<td>
				<select name="asociacion_id">
					<?php
					$asociaciones = new asociacion();
					foreach ($asociaciones->getList() as $asociacion) { ?>
					<option value="<?php echo $asociacion->id?>"><?php echo $asociacion->nombre?></option>
					<?php } ?>
			</td>
			<td>
				<input type="submit" value="Insertar asociaci&oacute;n">
			</td>
		</tr>
	</table>
	</form>
</div>
<div id="sedes" class="ui-tabs-panel">
<table class="viewForm">
<?php
foreach ($context["organizacionSedes"] as $sede)
{
?>		

	<tr>
		<td >
			<span style="font-size:14px;"><?php echo $sede->provincia_id->nombre?></span><br />
			<span style="font-weight:normal">
				<img src="media/icons/16x16/house.png" />&nbsp;<?php echo $sede->direccion?>, <?php echo $sede->localidad?><br />
				<table style="width:100%;"><tr><td style="font-size:12px;font-weight:normal;width:50%;">
				<br /><img src="media/icons/16x16/phone.png" /> &nbsp;<?php echo $sede->telefonoContacto ? "<a href=\"dialto:".$sede->telefonoContacto."\">".$sede->telefonoContacto."</a>" : 'No Disponible'?>
				<br /><img src="media/icons/16x16/user_suit.png" /> &nbsp;<?php echo $sede->personaContacto ? $sede->personaContacto : 'No Disponible'?> &nbsp;
				<br /><img src="media/icons/16x16/email.png" /> &nbsp;<?php echo $sede->mailContacto ? "<a href=\"".$sede->mailContacto."\">".$sede->mailContacto."</a>" : 'No Disponible'?>
				</td><td style="font-size:12px;font-weight:normal">
				<img src="media/icons/16x16/user_female.png" />&nbsp;<?php echo $sede->mujeres ? $sede->mujeres : 0  ?>&nbsp;Mujeres <br /><img src="media/icons/16x16/user.png" />&nbsp;<?php echo $sede->hombres ? $sede->hombres : 0 ?>&nbsp;Hombres
				</td></tr>
				</table>
			</span><br /><br />
			<button onclick="document.location='empresaSedeDelete.php?organizacionSede_id=<?php echo $sede->id?>';return false;"><img src="media/icons/16x16/delete.png"> Eliminar Sede </button>
			<button onclick="document.location='modificarSede.php?sede_id=<?php echo $sede->id?>';return false;"><img src="media/icons/16x16/disk.png"> Modificar Sede </button>
		</td>
		<td style="width:400px;padding:0px;">
			<div id="map_<?php echo $sede->id?>" style="width: 400px; height: 180px"></div>
			<script type="text/javascript">

				function z_<?php echo $sede->id?>(){
				if (GBrowserIsCompatible()) {
					var map = new GMap2(document.getElementById("map_<?php echo $sede->id?>"));
					map.setCenter(new GLatLng(<?php echo $sede->latitud?>,<?php echo $sede->longitud?>), 14);
					var point = new GPoint(<?php echo $sede->longitud?>,<?php echo $sede->latitud?>);
					var marker = new GMarker(point);
					map.addOverlay(marker);
				}}
				setTimeout('z_<?php echo $sede->id?>()',7000);
			</script>
		</td>
	</tr>
<?php
}
?>	
<tr>
	<td colspan="2" class="separator">A&ntilde;adir nueva Sede </td>
</tr>
<?php
	if (array_key_exists("sede_add_errors",$_REQUEST))
	{
			?>
<tr>
	<td colspan="2">
		<p  style="margin:5px;border:solid 4px red;color:red;bakckground:#ffffdd;padding:8px;padding-left:27px;background:url('media/icons/16x16/error.png');background-repeat:no-repeat;background-position:5px 10px; font-size:1.0em !important; text-align:justify"> No se pudo insertar la sede. Se encontraron errores en los campos <?php echo $_REQUEST["sede_add_errors"]?></p>
	</td>
</tr>
			<?php
	}
?>
<tr>
	<td colspan="2">
		Usted puede a&ntilde;adir sedes al perfil de la empresa mediante este formulario. Por favor, aseg&uacute;rese de que selecciona su ubicaci&oacute;n geogr&aacute;fica en el mapa haciendo click en el lugar donde se encuentra ubicada la sede a a&ntilde;adir.
	</td>
</tr>
<tr>
	<td>
		<form id="sedeAdd" method="post" action="empresaSedeAdd.php?organizacion_id=<?php echo $context["organizacion"]->id?>">
		<img src="media/icons/16x16/map.png">
		<select style="font-size:12px;" name="provincia_id">
		<option value="" selected="selected">Provincia</option>
		<?php
			$provincias = new provincia();
			foreach ($provincias->getList("1=1 order by nombre ASC") as $provincia){
				?>
				<option value="<?php echo $provincia->id?>"><?php echo $provincia->nombre?></option>				
				<?php
			}
		?>
		</select>
		<span style="font-weight:normal"><br />
			<img src="media/icons/16x16/house.png" />&nbsp;<input style="font-size:12px;" type="text" name="direccion" value="[Direccion]" onclick="if (this.value=='[Direccion]') this.value=''" /> / <input type="text" style="font-size:12px;"  name="localidad" value="[Localidad]" onclick="if (this.value=='[Localidad]') this.value=''" /><br />
			<br /><img src="media/icons/16x16/phone.png" /> &nbsp;<input type="text" style="font-size:12px;"  name="telefonoContacto" value="[Tel&eacute;fono Contacto]" onclick="if (this.value=='[Tel&eacute;fono Contacto]') this.value=''" />
			<br /><img src="media/icons/16x16/user_suit.png" /> &nbsp;<input type="text" style="font-size:12px;"  name="personaContacto" value="[Persona Contacto]" onclick="if (this.value=='[Persona Contacto]') this.value=''" />
			<br /><img src="media/icons/16x16/email.png" /> &nbsp;<input type="text" style="font-size:12px;"  name="mailContacto" value="[e-Mail Contacto]" onclick="if (this.value=='[e-Mail Contacto]') this.value=''" />
			<br /><img src="media/icons/16x16/user.png" /> &nbsp;<input type="text" style="font-size:12px;"  size="3" name="hombres" value="[H]" onclick="if (this.value=='[H]') this.value=''" />
			<br /><img src="media/icons/16x16/user_female.png" /> &nbsp;<input type="text" style="font-size:12px;" size="3"  name="mujeres" value="[M]" onclick="if (this.value=='[M]') this.value=''" />

			<br /><img src="media/icons/16x16/world.png" />&nbsp; <input type="text" style="font-size:12px" name="latitud" id="latitud" size="6"> N <input type="text" style="font-size:12px" name="longitud" id="longitud" size="6"> O
		</span><br /><br />
		<button onclick="document.getElementById('sedeAdd').submit()"><img src="media/icons/16x16/add.png"> Insertar Sede </button>
		</form>
	</td>
	<td style="width:400px;padding:0px;text-align:center">
		<div id="map_add" style="width: 400px; height: 180px"></div>
		<span style="font-size:9px;text-align:center">Haga click simple para marcar la localizaci&oacute;n de la sede en el mapa.<br />Haga click doble para hacer zoom en el mapa.</span>
		<script type="text/javascript">
			function z_createmap(){
			if (GBrowserIsCompatible()) {
				var map = new GMap2(document.getElementById("map_add"));
				var create_marker;
				map.setCenter(new GLatLng(40.145289,-2.614746), 4);
				map.addControl(new GSmallMapControl());
				GEvent.addListener(map, "click" , function (overlay, latlng) {
					map.clearOverlays();			
					point = new GPoint(latlng.lng(),latlng.lat());
					create_marker = new GMarker(point);
					setTimeout("map.addOverlay(create_marker)",500);
					$("#latitud").attr("value",latlng.lat());
					$("#longitud").attr("value",latlng.lng());
				});
				GEvent.addListener(map, "zoomend" , function (overlay, latlng) {
					map.clearOverlays();
				});
			}
		}
		setTimeout("z_createmap()",2500);
		</script>
	</td>
</tr>
</table>
</div>

<div id="asistentes-modal" title="Asistentes al Evento">
	<div id="asistentes-div"></div>
</div>
<div id="eventos" class="ui-tabs-panel">
	<p>
		En este apartado va a describir los eventos que organiza su organizaci&oacute;n. Seleccione la fecha en la que se dar&aacute; el evento, a continuaci&oacute;n escriba el t&iacute;tulo del evento y una breve descripci&oacute;n del mismo, as&iacute; como la localizaci&oacute;n del mismo.		
	</p>
	<div style="text-align:right;font-size: 1.2em"><input id="eventosPasadosCheck" type="checkbox" onClick="toogleEventosPasados();"> Mostrar eventos pasados</input></div>
	<table style="margin-bottom:16px;" id="eventosPasados" class="grid eventosGrid">
		<thead>
			<td>
				Eventos Pasados
			</td>
			<tbody>
			<?php
				foreach ($context["organizacion"]->getChildren("evento") as $evento)
				{
					$currentDate = new DateTime($evento->fecha);
					$today = new DateTime();
					$today->setTime(0,0,0);
					
					if($currentDate <$today)
					{
					echo("<tr><td class=\"evento\"><div style=\"float:right;\"><a onClick='showAssistants(");
					$orgas = Array();
					foreach($evento->getChildren("organizacionEvento") as $orga) {
						$orgas [] = Array("id" => $orga->organizacion_id->id, "name" => $orga->organizacion_id->nombre);
						
					}
					echo json_encode($orgas) ; 
					echo(");' href=\"#\"><b>Asistentes: </b>" . count($evento->getChildren("organizacionEvento")) . "</a></div>
					<p style=\"font-size:1.2em;color:#99008b\"><b>" . $evento->titulo . "</b></p>
					<p><b>Localización: </b>" . $evento->localizacion . "</p>
					<p><b>Fecha: </b>" . dateUStoEUR($evento->fecha) . "</p>
					<p>" . $evento->descripcion . "</p>
					</td>
					</tr>");
					}
				}
			?>
	</tbody>
	</table>
	<table style="margin-bottom:16px;" class="grid eventosGrid" id="eventos_table">
		<thead>
			<td>
				Eventos
			</td>
			<td style="width:30px;">
			</td><tbody id="eventosBody">
			<?php
				foreach ($context["organizacion"]->getChildren("evento") as $evento)
				{
					$currentDate = new DateTime($evento->fecha);
					$today = new DateTime();
					$today->setTime(0,0,0);
					
					if($currentDate >=$today)
					{
						echo("<tr><td class=\"evento\"><div style=\"float:right;\"><a onClick='showAssistants(");
						$orgas = Array();
						foreach($evento->getChildren("organizacionEvento") as $orga) {
							$sedes = $orga->organizacion_id->getChildren("organizacionSede");
							$orgas [] = Array("id" => $orga->organizacion_id->id, "name" => $orga->organizacion_id->nombre, "email" => $orga->organizacion_id->username);

						}
						echo json_encode($orgas) ; 
						echo(",\"" . $evento->titulo . "\");' href=\"#\"><b>Asistentes: </b>" . count($evento->getChildren("organizacionEvento")) . "</a></div>
						<p style=\"font-size:1.2em;color:#99008b\"><b>" . $evento->titulo . "</b></p>
					<p><b>Localización: </b>" . $evento->localizacion . "</p>
					<p><b>Fecha: </b>" . dateUStoEUR($evento->fecha) . "</p>
					<p>" . $evento->descripcion . "</p>
					</td>
					<td>
						<button onclick=\"evento_delete(this, " . $evento->id . ")\"><img src=\"media/icons/16x16/delete.png\"></button>
					</td></tr>");
					}
				}
			?>
	</tbody>
	</table>
	<table class="grid eventosGrid" id="eventossuscritos">
		<thead>
			<td>
				Eventos a los que está suscrito
			</td>
			<td style="width:30px;">
			</td><tbody id="eventosBody">
			<?php
				foreach ($context["organizacion"]->getChildren("organizacionEvento") as $organizacionEvento)
				{
					
					$evento = $organizacionEvento->evento_id;
					$currentDate = new DateTime($evento->fecha);
					$today = new DateTime();
					$today->setTime(0,0,0);
					
					if($currentDate >=$today)
					{
					echo("<tr><td class=\"evento\">
					<p style=\"font-size:1.2em;color:#99008b\"><b>" . $evento->titulo . "</b></p>
					<p><b>Localización: </b>" . $evento->localizacion . "</p>
					<p><b>Fecha: </b>" . dateUStoEUR($evento->fecha) . "</p>
					<p>" . $evento->descripcion . "</p>
					</td>
					<td>
						<button title=\"Darse de baja de este evento\" onclick=\"organizacion_evento_delete(this, " . $organizacionEvento->id . ")\"><img src=\"media/icons/16x16/calendar_delete.png\"></button>
					</td></tr>");
					}
				}
			?>
	</tbody>
	</table>

	<table class="grid eventosAddGrid">
		<thead>
			<td>Añadir Evento Nuevo</td></thead>
		<tr>
			<td>
				<div style="float:right;"><button  onclick="evento_add();return false;"><img src="media/icons/16x16/add.png"></button></div>
				<span style="font-size:9px;font-weight:bold">T&iacute;tulo</span><br/>
				<input type="text" id="eventTitle" size="120" /><br/><br/>
				<div style="display:inline-block"><span style="font-size:9px;font-weight:bold">Localizaci&oacute;n</span><br/>
				<input type="text" id="eventLocalization" size="60" /></div>
				<div style="display:inline-block"><span style="font-size:9px;font-weight:bold">Fecha</span><br/>
				<input type="text" id="eventDate" /></div><br/><br/>
				<span style="font-size:9px;font-weight:bold">Descripci&oacute;n</span><br/>
				<textarea id="eventDescription" style="width:99%" rows="8"></textarea>
				
			</td>
		</tr>
	</table>
	<div id="dialog-modal" style="font-size:10px;" title="Error">
		
	</div>
	<script>
		function showAssistants (assistants,titulo) {
			email = "";
			$("#asistentes-div").html("");
			$.each(assistants, function (i, elem) {
				email += "&bcc=" + elem.email;
				$("#asistentes-div").append("<p><a target=\"blank\" href=\"organizacion.php?organizacion_id=" + elem.id +  "\">" + elem.name + "</a></p>");
			});
			$("#asistentes-div").append("<div style=\"text-align:right\"><button title=\"Enviar correo electr&oacute;nico a los asistentes\" onclick=\"location='mailto:?subject=Información del evento: " + titulo + email + "'\" ><img src=\"media/icons/16x16/email.png\"></button></div>");
			$("#asistentes-modal").dialog('open');
		}
	    function toogleEventosPasados() {
		if($("#eventosPasadosCheck").attr("checked") == true) {
			$("#eventosPasados").show('blind'),null,500;
		} else {
			$("#eventosPasados").hide('blind'),null,500;
		}
		}
		function dce (tagname){ return document.createElement(tagname);}
		var deleting_el="";
		
		function organizacion_evento_delete(el,id){
			deleting_el = 1;
			el.parentNode.parentNode.parentNode.removeChild(el.parentNode.parentNode);
			jQuery.getJSON("empresaOrganizacionEventoDelete.php?id="+id,organizacionevento_delReady);
		}
		
		function organizacionevento_delReady(data,scope){
			deleting_el = "";
		}
		
		function evento_delete(el,id){
			deleting_el = 1;
			el.parentNode.parentNode.parentNode.removeChild(el.parentNode.parentNode);
			jQuery.getJSON("empresaEventoDelete.php?id="+id,evento_delReady);
		}
	
		function evento_delReady(data,scope){
			deleting_el = "";
		}
	
		function evento_add(){
			
			var titulo = ($("#eventTitle").attr("value"));
			var localizacion = ($("#eventLocalization").attr("value"));
			var fecha = ($("#eventDate").attr("value"));
			var descripcion = ($("#eventDescription").attr("value"));
			var errors = "";
			if (titulo=="") {errors += "<p>El campo <span style=\"color:red;\">T&iacute;tulo</span> es obligatorio</p>";}
			if (localizacion=="") {errors += "<p>El campo  <span style=\"color:red;\">Localizaci&oacute;n</span> es obligatorio</p>";}
			if (fecha=="") {errors += "<p>El campo  <span style=\"color:red;\">Fecha</span> es obligatorio</p>";}
			if (descripcion=="") {errors += "<p>El campo  <span style=\"color:red;\">Descripci&oacute;n</span> es obligatorio</p>";}
			if (errors!= "") {
				$("#dialog-modal").dialog("open");
				$("#dialog-modal").html(errors);

				return;
			}
			jQuery.getJSON("jsonEmpresaEventoAdd.php?organizacion_id=<?php echo $context["organizacion"]->id?>&titulo="+titulo+"&localizacion="+localizacion+"&fecha="+fecha+"&descripcion="+descripcion,evento_addReady);
		}
		
		function evento_addReady(data,scope){
			if (data.error == 1){
				alert ("Ha ocurrido un error al insertar el servicio, por favor revise los datos.");
				return;
			} 
			$("#eventosBody").append("<tr><td class=\"evento\"><p style=\"font-size:1.2em;color:#99008b\"><b>" + data.titulo + "</b></p><p><b>Localización: </b>" + data.localizacion + "</p><p><b>Fecha: </b>" + data.fecha + "</p><p>" + data.descripcion + "</p></td><td><button onclick=\"evento_delete(this, " + data.id + ")\"><img src=\"media/icons/16x16/delete.png\"></button></td></tr>");
		}
	</script>
</div>
<div id="capacidades"  class="ui-tabs-panel">
	<p>
		En este apartado va a describir los servicios que presta su organizaci&oacute;n. Seleccione las capacidades que describen el trabajo que realiza su empresa y relaci&oacute;nelas con uno o varios sectores de actividad (si una misma capacidad est&aacute; relacionada con varios sectores deber&aacute; indicarlo). As&iacute; mismo, para cada capacidad indique el n&uacute;mero de personas implicadas (recursos), el porcentaje de negocio que supone esta actividad para su empresa y finalmente punt&uacute;e entre 1 y 10 esta capacidad.		
	</p>
	<table class="grid capacidadesGrid" id="capacidades_table">
		<thead>
			<td style="background-color:#BBBBBB">
				Servicio / Sector especializado
			</td>
			<td style="background-color:#BBBBBB">
				Descripci&oacute;n
			</td>
			<td style="background-color:#BBBBBB">
				Recursos
			</td>
			<td style="background-color:#BBBBBB">
				% Negocio
			</td>
			<td style="background-color:#BBBBBB">
				Puntuaci&oacute;n
			</td>
			<td>
				
			</td>
		</thead>
		<tbody>
	<?php
		foreach ($context["organizacion"]->getChildren("capacidadOfertaOrganizacion") as $oferta)
		{
			$ofertas[$oferta->capacidad_id->id][] = $oferta;	
		}
		foreach ($ofertas as $capacidad_id => $ofer)
		{
			?>
			</tbody>
			<thead>
				<td colspan="6" style="background-color:#DDDDDD">
				<?php
				$capacidad = new capacidad();
				$capacidad->open($capacidad_id);
				echo $capacidad->nombre;
				?>
				</td>
			</thead>
			<tbody id="capacidad_<?php echo $capacidad_id?>">
			<?php
			foreach ($ofer as $oferta)
			{
		?>
			<tr>
				<td class="capacidad">
					<?php echo $oferta->sector_id->nombre?>
				</td>
				<td class="descripcion">
					<?php echo $oferta->capacidad_id->descripcion?>
					<?php echo "<br/>" . $oferta->descripcion?>
				</td>
				<td class="personas">
					<?php echo ($oferta->recursos<101?$oferta->recursos:">100")?>&nbsp;personas.
				</td>
				<td class="porcentaje">
					<?php echo $oferta->porcentajeFacturacion?> %
				</td>
				<td class="puntuacion">
					<?php echo $oferta->puntuacion?>
				</td>
				<td>
					<button onclick="capacidad_delete(this, <?php echo $oferta->id?>)"><img src="media/icons/16x16/delete.png"></button>
				</td>
			</tr>
	<?php
			}
		}
	?>
		</tbody>
		</table>
		<table style="width:100%" class="grid capacidadesAddGrid">
			<thead>
				<td>Actividad</td>
				<td>Sector</td>
				<td>Recursos</td>
				<td>% Negocio</td>
				<td>Puntuaci&oacute;n</td>
				<td> </td>
			</thead>
			<tbody>
				<tr>
					<td>
						<select id="capacidad_id" name="capacidad_id" style="max-width: 300px">
					<?php
					$capacidades = new capacidad();
					$last_cap = "";
					foreach ($capacidades->getList("1=1 order by categoria,nombre asc") as $capacidad)
					{
						if ($last_cap != $capacidad->categoria)
						{
							?>
								<option value="0" style="text-decoration:italic; font-weight:bold;color#666666; background:#dddddd"><?php echo $capacidad->categoria?></option>
							<?php
							$last_cap = $capacidad->categoria;
						}
					?>
						<option value="<?php echo $capacidad->id?>"> &nbsp; &nbsp;<?php echo $capacidad->nombre?></option>
					<?php
					}
					?>
					</select>
					</td>
					<td>
						<select id="sector_id" name="sector_id" style="max-width: 300px">
					<?php
					$sector = new sector();
					$last_sec = "";
					foreach ($sector->getList("1=1 order by nombre asc") as $sector)
					{
					?>
						<option value="<?php echo $sector->id?>"> &nbsp; &nbsp;<?php echo $sector->nombre?></option>
					<?php
					}
					?>
					</select>
					</td>
					<td>
						<select id="personas" name="personas">
							<?php for ($i=0;$i<102;$i++) { ?> <option value="<?php echo $i?>"><?php echo ($i<101?$i:">100")?></option><?php } ?>
						</select>
					</td>
					<td>
						<select id="porcentaje" name="porcentaje">
							<?php for ($i=0;$i<101;$i++) { ?> <option value="<?php echo $i?>"><?php echo $i?> %</option><?php } ?>
						</select>
					</td>
					<td>
						<select id="puntuacion" name="puntuacion">
							<?php for ($i=0;$i<11;$i++) { ?> <option value="<?php echo $i?>"><?php echo $i?>/10</option><?php } ?>
						</select>
					</td>
					<tr>
						<td colspan="3">
							
							<span style="font-weight:bold;font-size:9px">Descripci&oacute;n</span>
							<textarea id="descripcion_oferta" style="width:99%;height:64px;font-size:11px;"></textarea>
							
						</td>
					</tr>
					<td>
						<button  onclick="capacidad_add();return false;"><img src="media/icons/16x16/add.png"></button>
					</td>
				</tr>
		</tbody>
	</table>
	<script>
		function dce (tagname){ return document.createElement(tagname);}
		var deleting_el="";
		function capacidad_delete(el,id){
			deleting_el = 1;
			el.parentNode.parentNode.parentNode.removeChild(el.parentNode.parentNode);
			jQuery.getJSON("jsonEmpresaCapacidadDelete.php?id="+id,capacidad_delReady);
		}
	
		function capacidad_delReady(data,scope){
			deleting_el = "";
		}
	
		function capacidad_add(){
			var capacidad_id = ($("#capacidad_id").attr("value"));
			if (capacidad_id < 1) { alert ("No se ha seleccionado una capacidad. Por favor, seleccione una capacidad que no forme parte de un grupo de capacidades"); return ; }
			var sector_id = ($("#sector_id").attr("value"));
			var porcentaje = ($("#porcentaje").attr("value"));
			var personas = ($("#personas").attr("value"));
			var puntuacion = ($("#puntuacion").attr("value"));
			var descripcion = ($("#descripcion_oferta").attr("value"));
			jQuery.getJSON("jsonEmpresaCapacidadAdd.php?organizacion_id=<?php echo $context["organizacion"]->id?>&capacidad_id="+capacidad_id+"&sector_id="+sector_id+"&porcentaje="+porcentaje+"&personas="+personas+"&puntuacion="+puntuacion+"&descripcion="+descripcion,capacidad_addReady);
		}
		
		function capacidad_addReady(data,scope){
			if (data.error == 1){
				alert ("Ha ocurrido un error al insertar el servicio, por favor revise los datos.");
				return;
			} 
			var tr = dce("tr");
			var td = dce("td"); td.className="capacidad"; td.innerHTML = data.sector_name; tr.appendChild(td);
			var td = dce("td"); td.className="descripcion"; td.innerHTML = data.descripcion; tr.appendChild(td);
			var td = dce("td"); td.className="personas"; td.innerHTML = data.personas+ "&nbsp;personas."; tr.appendChild(td);
			var td = dce("td"); td.className="porcentaje"; td.innerHTML = data.porcentaje+ " %"; tr.appendChild(td);
			var td = dce("td"); td.className="puntuacion"; td.innerHTML = data.puntuacion;  tr.appendChild(td);
			var button = dce ("button");  var img = dce("img"); img.src= "media/icons/16x16/delete.png"; button.appendChild(img);
			button.onclick = function () { capacidad_delete(this, data.id); }
			var td = dce("td"); td.appendChild(button); tr.appendChild(td);
			var done=0;

			el = document.getElementById('capacidades_table');
			for (i=0;i < el.tBodies.length;i++){
				if (el.tBodies[i].id == "capacidad_"+data.capacidad){
					el.tBodies[i].appendChild(tr);
					done=1;
				}
			}
			if (done == 0){				
				var thead = dce("thead"); var tbody = dce("tbody");
				tbody.id = "sector_"+data.capcidad;
				var td = dce("td"); td.setAttribute("colspan",6);td.setAttribute("colspan",6);td.style.backgroundColor = "#dddddd"; td.innerHTML = data.capacidad_name;
				thead.appendChild(td); tbody.appendChild(tr);
				el.appendChild(thead); el.appendChild(tbody);
			}
		}
	</script>
	
</div>
<!--<div id="congreso" class="ui-tabs-panel">
	<iframe src="congreso_private.php?organizacion_id=<?php echo $organizacion->id?>" style="height:500px;width:100%;border:none" noborder></iframe>
</div>-->

<div id="demandas"  class="ui-tabs-panel">
	<table class="grid capacidadesGrid" id="demandas_table">
		<thead>
			<td style="background-color: #bbbbbb">
				Servicio / Sector especializado
			</td>
			<td style="background-color: #bbbbbb">
				Descripci&oacute;n
			</td>
			<td>
				
			</td>
		</thead>
		<tbody>
	<?php
		foreach ($context["organizacion"]->getChildren("capacidadDemandaOrganizacion") as $demanda)
		{
			$demandas[$demanda->capacidad_id->id][] = $demanda;	
		}
		foreach ($demandas as $capacidad_id => $deman)
		{
			?>
			</tbody>
			<thead>
				<td colspan="3" style="background-color: #dddddd">
				<?php
				$capacidad = new capacidad();
				$capacidad->open($capacidad_id);
				echo $capacidad->nombre;
				?>
				</td>
			</thead>
			<tbody id="capacidad_<?php echo $capacidad_id?>">
			<?php
			foreach ($deman as $demanda)
			{
		?>
			<tr>
				<td class="capacidad">
					<?php echo $demanda->sector_id->nombre?>
				</td>
				<td class="descripcion">
					<?php echo $demanda->capacidad_id->descripcion . "<br>" . $demanda->descripcion?>
				</td>
				<td>
					<button onclick="demanda_delete(this, <?php echo $demanda->id?>)"><img src="media/icons/16x16/delete.png"></button>	
				</td>
			</tr>
	<?php
			}
		}
	?>
		</tbody>
	</table>
	
		<table style="width:100%" class="grid capacidadesAddGrid" >
			<thead>
				<td>Actividad</td>
				<td>Sector</td>
				<td> </td>
			</thead>
			<tbody>
				<tr>
					<td>
						<select id="capacidad_id_demanda" name="capacidad_id" style="max-width: 300px;">
					<?php
					$capacidades = new capacidad();
					$last_cap = "";
					foreach ($capacidades->getList("1=1 order by categoria,nombre asc") as $capacidad)
					{
						if ($last_cap != $capacidad->categoria)
						{
							?>
								<option value="0" style="text-decoration:italic; font-weight:bold;color#666666; background:#dddddd"><?php echo $capacidad->categoria?></option>
							<?php
							$last_cap = $capacidad->categoria;
						}
					?>
						<option value="<?php echo $capacidad->id?>"> &nbsp; &nbsp;<?php echo $capacidad->nombre?></option>
					<?php
					}
					?>
					</select>
					</td>
					<td>
						<select id="sector_id_demanda" name="sector_id" style="max-width:300px">
					<?php
					$sector = new sector();
					$last_sec = "";
					foreach ($sector->getList("1=1 order by nombre asc") as $sector)
					{
					?>
						<option value="<?php echo $sector->id?>"> &nbsp; &nbsp;<?php echo $sector->nombre?></option>
					<?php
					}
					?>
					</select>
					</td>		
					<tr>
						<td colspan="3">
							
							<span style="font-weight:bold;font-size:9px">Descripci&oacute;n</span>
							<textarea id="descripcion_demanda" style="width:99%;height:64px;font-size:11px;"></textarea>
							
						</td>
					</tr>			
					<td>
						<button  onclick="demanda_add();return false;"><img src="media/icons/16x16/add.png"></button>
					</td>
				</tr>
		</tbody>
	</table>
	<script>
		function dce (tagname){ return document.createElement(tagname);}
		var deleting_el="";
		function demanda_delete(el,id){
			deleting_el = 1;
			el.parentNode.parentNode.parentNode.removeChild(el.parentNode.parentNode);
			jQuery.getJSON("jsonEmpresaDemandaDelete.php?id="+id,capacidad_delReady);
		}
	
		function demanda_delReady(data,scope){
			deleting_el = "";
		}
	
		function demanda_add(){
			var capacidad_id = ($("#capacidad_id_demanda").attr("value"));
			if (capacidad_id < 1) { alert ("No se ha seleccionado una capacidad. Por favor, seleccione una capacidad que no forme parte de un grupo de capacidades"); return ; }
			var sector_id = ($("#sector_id_demanda").attr("value"));
			var descripcion_demanda = ($("#descripcion_demanda").attr("value"));
			jQuery.getJSON("jsonEmpresaDemandaAdd.php?organizacion_id=<?php echo $context["organizacion"]->id?>&capacidad_id="+capacidad_id+"&sector_id="+sector_id+"&descripcion="+descripcion_demanda,demanda_addReady);
		}
		
		function demanda_addReady(data,scope){
			if (data.error == 1){
				alert ("Ha ocurrido un error al insertar el servicio, por favor revise los datos.");
				return;
			} 
			var tr = dce("tr");
			var td = dce("td"); td.className="capacidad"; td.innerHTML = data.sector_name; tr.appendChild(td);
			var td = dce("td"); td.className="descripcion"; td.innerHTML = data.descripcion_capacidad + "<br />" + data.descripcion; tr.appendChild(td);
			var button = dce ("button");  var img = dce("img"); img.src= "media/icons/16x16/delete.png"; button.appendChild(img);
			button.onclick = function () { demanda_delete(this, data.id); }
			var td = dce("td"); td.appendChild(button); tr.appendChild(td);
			var done=0;

			el = document.getElementById('demandas_table');
			for (i=0;i < el.tBodies.length;i++){
				if (el.tBodies[i].id == "capacidad_"+data.capacidad){
					el.tBodies[i].appendChild(tr);
					done=1;
				}
			}
			if (done == 0){				
				var thead = dce("thead"); var tbody = dce("tbody");
				tbody.id = "capacidad_"+data.capacidad;
 				var td = dce("td"); td.setAttribute("colspan",6); td.style.backgroundColor = "#dddddd"; td.innerHTML = data.capacidad_name;
				thead.appendChild(td); tbody.appendChild(tr);
				el.appendChild(thead); el.appendChild(tbody);
			}
		}
	</script>
	
	
	
	
</div>


</div>
<script type="text/javascript">
function limitChars(textid, limit, infodiv)
{
var text = $('#'+textid).val(); 
var textlength = text.length;
if(textlength > limit)
{
$('#' + infodiv).html('No puede introducir mas de '+limit+' caracteres');
 $('#'+textid).val(text.substr(0,limit));
 return false;
 }
 else
 {
 $('#' + infodiv).html('Le quedan '+ (limit - textlength) +' caracteres.');
 return true;
 }
}

$(window).bind("load",function(){
	$('#descripcion').keyup(function(){
	 limitChars('descripcion', 255, 'charlimitinfo');
	})
	if (GBrowserIsCompatible()) {

<?php
foreach ($context["organizacionSedes"] as $sede)
{
?>		
		var map = new GMap2(document.getElementById("map_<?php echo $sede->id?>"));
		map.setCenter(new GLatLng(<?php echo $sede->latitud?>,<?php echo $sede->longitud?>), 14);
		var point = new GPoint(<?php echo $sede->longitud?>,<?php echo $sede->latitud?>);
		var marker = new GMarker(point);
		map.addOverlay(marker);


<?php } ?>
	}
$('#tabs').tabs({ fx: { height: 'toggle', opacity: 'toggle', duration: 'fast' } });
$("#eventDate").datepicker();
$("#eventosPasados").hide();
$("#asistentes-modal").dialog({
	autoOpen: false,
	modal: true,
	resizable: false
});
$("#dialog-modal").dialog({
	autoOpen: false,
	modal: true,
	resizable: false,
	buttons: {
					Ok: function() {
						$(this).dialog('close');
					}
				}
});

});
</script>
