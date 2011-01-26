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
<div id="tabs">
            <ul>
                <li class="ui-tabs-nav-item"><a href="#general"><span><img src="media/icons/16x16/book.png" /><?php echo $context["organizacion"]->nombre?></span></a></li>
                <li class="ui-tabs-nav-item"><a href="#asociaciones"><span><img src="media/icons/16x16/map.png" /> Asociaciones </span></a></li>
                <li class="ui-tabs-nav-item"><a href="#sedes"><span><img src="media/icons/16x16/map.png" /> Sedes </span></a></li>
                <li class="ui-tabs-nav-item"><a href="#capacidades"><span><img src="media/icons/16x16/wrench.png" />Servicios</span></a></li>
                <li class="ui-tabs-nav-item"><a href="#demandas"><span><img src="media/icons/16x16/wrench_orange.png" />Demanda</span></a></li>
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
			<input type="text" name="web" size="30" value="<?php echo $context["organizacion"]->web?>" />
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
			<textarea name="descripcion"><?php echo $context["organizacion"]->descripcion?></textarea>
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
	
	<tr>
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
			Certificaciones de Calidad
		</td>
		<td>
			<?php
			$certificacionesCalidadChecked = false;
			if ($context["organizacion"]->certificacionesCalidad || array_key_exists("certificacionesCalidadChecked",$_REQUEST)) {$certificacionesCalidadChecked = true; }
			?>
			<input type="checkbox" <?php if ($certificacionesCalidadChecked) echo "checked" ?> onchange=" if (this.checked) { $('#certificacionesCalidadInput').css('display','inline');} else { $('#certificacionesCalidadInput').css('display','none'); }"/>
			<div id="certificacionesCalidadInput" style="display: <?php if ($certificacionesCalidadChecked) { echo "inline"; } else { echo "none"; } ?>;margin-left:12px;"> Por favor indique cuales:&nbsp;&nbsp;<input type="text" size="34" name="certificacionesCalidad" value="<?php echo (array_key_exists("certificacionesCalidad" ,$_REQUEST) ? $_REQUEST["certificacionesCalidad"] : $context["organizacion"]->certificacionesCalidad)?>" /><?php echo (in_array("certificacionesCalidad",$context["errors"]) ? "<div class=\"error\"><img src=\"media/icons/16x16/cancel.png\">El valorIntroducido no es V&aacute;lido.</div>" : "")?></div>
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
		</td>
		<td style="width:400px;padding:0px;">
			<div id="map_<?php echo $sede->id?>" style="width: 400px; height: 180px"></div>
			<script type="text/javascript">
				if (GBrowserIsCompatible()) {
					var map = new GMap2(document.getElementById("map_<?php echo $sede->id?>"));
					map.setCenter(new GLatLng(<?php echo $sede->latitud?>,<?php echo $sede->longitud?>), 14);
					var point = new GPoint(<?php echo $sede->longitud?>,<?php echo $sede->latitud?>);
					var marker = new GMarker(point);
					map.addOverlay(marker);
				}
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
		<img src="media/icons/16x16/map.png"><select style="font-size:12px;" name="provincia_id">
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
		</script>
	</td>
</tr>
</table>
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


<div id="capacidades"  class="ui-tabs-panel">
	<p>
		En este apartado va a describir los servicios que presta su organizaci&oacute;n. Seleccione las capacidades que describen el trabajo que realiza su empresa y relaci&oacute;nelas con uno o varios sectores de actividad (si una misma capacidad est&aacute; relacionada con varios sectores deber&aacute; indicarlo). As&iacute; mismo, para cada capacidad indique el n&uacute;mero de personas implicadas (recursos), el porcentaje de negocio que supone esta actividad para su empresa y finalmente punt&uacute;e entre 1 y 10 esta capacidad.		
	</p>
	<table class="grid capacidadesGrid" id="capacidades_table">
		<thead>
			<td>
				Servicio
			</td>
			<td>
				Descripci&oacute;n
			</td>
			<td>
				Recursos
			</td>
			<td>
				% Negocio
			</td>
			<td>
				Puntuaci&oacute;n
			</td>
			<td>
				
			</td>
		</thead>
		<tbody>
	<?php
		foreach ($context["organizacion"]->getChildren("capacidadOfertaOrganizacion") as $oferta)
		{
			$ofertas[$oferta->sector_id->id][] = $oferta;	
		}
		foreach ($ofertas as $sector_id => $ofer)
		{
			?>
			</tbody>
			<thead>
				<td colspan="6">
				<?php
				$sector = new sector();
				$sector->open($sector_id);
				echo $sector->nombre;
				?>
				</td>
			</thead>
			<tbody id="sector_<?php echo $sector_id?>">
			<?php
			foreach ($ofer as $oferta)
			{
		?>
			<tr>
				<td class="capacidad">
					<?php echo $oferta->capacidad_id->nombre?>
				</td>
				<td class="descripcion">
					<?php echo $oferta->capacidad_id->descripcion?>
				</td>
				<td class="personas">
					<?php echo $oferta->recursos?>&nbsp;personas.
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
				<td>Capacidad</td>
				<td>Sector</td>
				<td>Recursos</td>
				<td>% Negocio</td>
				<td>Puntuaci&oacute;n</td>
				<td> </td>
			</thead>
			<tbody>
				<tr>
					<td>
						<select id="capacidad_id" name="capacidad_id">
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
						<select id="sector_id" name="sector_id">
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
							<?php for ($i=0;$i<101;$i++) { ?> <option value="<?php echo $i?>"><?php echo $i?></option><?php } ?>
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
			jQuery.getJSON("jsonEmpresaCapacidadAdd.php?organizacion_id=<?php echo $context["organizacion"]->id?>&capacidad_id="+capacidad_id+"&sector_id="+sector_id+"&porcentaje="+porcentaje+"&personas="+personas+"&puntuacion="+puntuacion,capacidad_addReady);
		}
		
		function capacidad_addReady(data,scope){
			if (data.error == 1){
				alert ("Ha ocurrido un error al insertar el servicio, por favor revise los datos.");
				return;
			} 
			var tr = dce("tr");
			var td = dce("td"); td.className="capacidad"; td.innerHTML = data.capacidad; tr.appendChild(td);
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
				if (el.tBodies[i].id == "sector_"+data.sector){
					el.tBodies[i].appendChild(tr);
					done=1;
				}
			}
			if (done == 0){				
				var thead = dce("thead"); var tbody = dce("tbody");
				tbody.id = "sector_"+data.sector;
				var td = dce("td"); td.setAttribute("colspan",6); td.innerHTML = data.sector_name;
				thead.appendChild(td); tbody.appendChild(tr);
				el.appendChild(thead); el.appendChild(tbody);
			}
		}
	</script>
	
</div>

<div id="demandas"  class="ui-tabs-panel">
	<table class="grid capacidadesGrid" id="demandas_table">
		<thead>
			<td>
				Servicio
			</td>
			<td>
				Descripci&oacute;n
			</td>
			<td>
				
			</td>
		</thead>
		<tbody>
	<?php
		foreach ($context["organizacion"]->getChildren("capacidadDemandaOrganizacion") as $demanda)
		{
			$demandas[$demanda->sector_id->id][] = $demanda;	
		}
		foreach ($demandas as $sector_id => $deman)
		{
			?>
			</tbody>
			<thead>
				<td colspan="3">
				<?php
				$sector = new sector();
				$sector->open($sector_id);
				echo $sector->nombre;
				?>
				</td>
			</thead>
			<tbody id="sector_<?php echo $sector_id?>">
			<?php
			foreach ($deman as $demanda)
			{
		?>
			<tr>
				<td>
					<?php echo $demanda->capacidad_id->nombre?>
				</td>
				<td>
					<?php echo $demanda->capacidad_id->descripcion?>
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
				<td>Capacidad</td>
				<td>Sector</td>
				<td> </td>
			</thead>
			<tbody>
				<tr>
					<td>
						<select id="capacidad_id_demanda" name="capacidad_id">
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
						<select id="sector_id_demanda" name="sector_id">
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
			jQuery.getJSON("jsonEmpresaDemandaAdd.php?organizacion_id=<?php echo $context["organizacion"]->id?>&capacidad_id="+capacidad_id+"&sector_id="+sector_id,demanda_addReady);
		}
		
		function demanda_addReady(data,scope){
			if (data.error == 1){
				alert ("Ha ocurrido un error al insertar el servicio, por favor revise los datos.");
				return;
			} 
			var tr = dce("tr");
			var td = dce("td"); td.className="capacidad"; td.innerHTML = data.capacidad; tr.appendChild(td);
			var td = dce("td"); td.className="descripcion"; td.innerHTML = data.descripcion; tr.appendChild(td);
			var button = dce ("button");  var img = dce("img"); img.src= "media/icons/16x16/delete.png"; button.appendChild(img);
			button.onclick = function () { demanda_delete(this, data.id); }
			var td = dce("td"); td.appendChild(button); tr.appendChild(td);
			var done=0;

			el = document.getElementById('demandas_table');
			for (i=0;i < el.tBodies.length;i++){
				if (el.tBodies[i].id == "sector_"+data.sector){
					el.tBodies[i].appendChild(tr);
					done=1;
				}
			}
			if (done == 0){				
				var thead = dce("thead"); var tbody = dce("tbody");
				tbody.id = "sector_"+data.sector;
 				var td = dce("td"); td.setAttribute("colspan",6); td.innerHTML = data.sector_name;
				thead.appendChild(td); tbody.appendChild(tr);
				el.appendChild(thead); el.appendChild(tbody);
			}
		}
	</script>
	
	
	
	
</div>

</div>
<script type="text/javascript">

$(window).bind("load",function(){
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
$('#tabs > ul').tabs({ fx: { height: 'toggle', opacity: 'toggle', duration: 'fast' } });
});
</script>
