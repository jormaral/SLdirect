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
$session = new session();
$organizacionUser = new organizacion();
if ($session->username != "") {
	
	$organizacionUser->open($session->userid);
}
?>
<div id="dialog-suscribirse" title="¿Desea suscribirse a este evento?">
	<p style="text-align: justify;">Al suscribirse a este evento acepta que la empresa organizadora le envie informaci&oacute;n sobre el evento a trav&eacute;s del correo electr&oacute;nico.</p>
</div>
<div class="breadcrumb"><img src="media/icons/16x16/house.png"><a href="index.php">Inicio</a><img src="media/breadcrumb.png"><img src="media/icons/16x16/zoom.png"><a href="busqueda.php">Buscador de Empresas</a><img src="media/breadcrumb.png"><?php echo $context["organizacion"]->nombre?></div>
<script src="http://maps.google.com/maps?file=api&amp;v=2&amp;key=<?php global $settings; echo $settings["gmaps.key"]?>" type="text/javascript"></script>
<div id="tabs" >
            <ul>
                <li class="ui-tabs-nav-item"><a href="#general"><span><img src="media/icons/16x16/book.png" /><?php echo $context["organizacion"]->nombre?></span></a></li>
                <li class="ui-tabs-nav-item"><a href="#asociaciones"><span><img src="media/icons/16x16/map.png" /> Asociaciones </span></a></li>
                <li class="ui-tabs-nav-item"><a href="#sedes"><span><img src="media/icons/16x16/map.png" /> Sedes </span></a></li>
                <li class="ui-tabs-nav-item"><a href="#capacidades"><span><img src="media/icons/16x16/wrench.png" />Servicios</span></a></li>
                <li class="ui-tabs-nav-item"><a href="#demandas"><span><img src="media/icons/16x16/wrench_orange.png" />Demanda</span></a></li>
				<li class="ui-tabs-nav-item"><a href="#eventos"><span><img src="media/icons/16x16/calendar.png" />Eventos</span></a></li>
                <!--<li class="ui-tabs-nav-item"><a href="#congreso"><span><img src="media/icons/16x16/wrench_orange.png" />Presencia CISL</span></a></li>-->
            </ul>
<div id="general" class="ui-tabs-panel">
<table class="viewForm">
	<tr>
		<td class="key">

		</td>
		<td style="text-align:right">
			<img src="<?php echo $context["organizacion"]->logo_url?>" />
		</td>
	</tr>

	<tr>
		<td class="key">
			Raz&oacute;n Social
		</td>
		<td>
			<?php echo $context["organizacion"]->nombre?>
		</td>
	</tr>
	<tr>
		<td class="key">
			Direcci&oacute;n Web
		</td>
		<td>
			<a href="<?php echo $context["organizacion"]->web?>?>"><?php echo $context["organizacion"]->web?></a>
		</td>
	</tr>
	<tr>
		<td class="key">
			Clasificaci&oacute;n
		</td>
		<td>
			<?php echo $context["organizacion"]->organizacionClasificacion_id->nombre?>

		</td>
	</tr>
	<tr>
		<td class="key">
			Forma Juridica
		</td>
		<td>
			<?php echo $context["organizacion"]->formaJuridica_id->nombre?>
		</td>
	</tr>


	<tr>
		<td class="key">
			Descripci&oacute;n de los Servicios
		</td>
		<td style="font-size:1.1em;">
			<?php echo $context["organizacion"]->descripcion?>
		</td>
	</tr>
	
	<tr>
		<td class="key">
			CIF
		</td>
		<td>
			<?php echo $context["organizacion"]->cif?>
		</td>
	</tr>
	<tr>
		<td class="key">
			A&ntilde;o de Constituci&oacute;n
		</td>
		<td>
			<?php echo $context["organizacion"]->anoConstitucion?>
		</td>
	</tr>
	<tr>
		<td class="key">
			N&uacute;mero total de Empleados
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
		</td>
	</tr>
	<?php /*
	<tr>
		<td class="key">
			Clase de Empresa
		</td>
		<td>
			<?php echo $context["organizacion"]->claseEmpresa_id->nombre?>
		</td>
	</tr>
	*/ ?>
	<tr>
		<td class="separator" colspan="2">
			Domicilio Social
		</td>
	</tr>
	<tr>
		<td class="key">
			Direcci&oacute;n
		</td>
		<td>
			<?php echo nl2br($context["organizacion"]->direccion)?>
		</td>
	</tr>
	<tr>
		<td class="key">
			C&oacute;digo Postal
		</td>
		<td>
			<?php echo nl2br($context["organizacion"]->codigoPostal)?>
		</td>
	</tr>

	<tr>
		<td class="key">
			Localidad
		</td>
		<td>
			<?php echo $context["organizacion"]->localidad?>
		</td>
	</tr>

	<tr>
		<td class="key">
			Provincia
		</td>
		<td>
			<?php echo $context["organizacion"]->provincia_id->nombre?>
		</td>
	</tr>
	<tr>
		<td class="key">
			Tel&eacute;fono
		</td>
		<td>
			<?php echo $context["organizacion"]->telefono?>
		</td>
	</tr>
	<tr>
		<td class="key">
			Correo Electr&oacute;nico
		</td>
		<td>
			<?php echo $context["organizacion"]->email?>
		</td>
	</tr>
	<tr>
		<td colspan="2" class="separator">
			Informaci&oacute;n Adicional
		</td>
	</tr>
	<tr>
		<td class="key">
			Certificacion Cenatic
		</td>
		<td>
			<?php
			if ($context["organizacion"]->certificacionCenatic ) {
				?>
				<img src="media/icons/16x16/accept.png">&nbsp;&nbsp;
				<?php 
			} else {
				?>
				<img src="media/icons/16x16/cancel.png">
				<?php
			}
			?>
		</td>
	</tr>
	<tr>
		<td class="key">
			Certificaciones de Calidad
		</td>
		<td>
			<?php
			$certificacionesCalidadChecked = false;
			$certificados = unserialize(base64_decode($context["organizacion"]->certificacionesCalidad));
			
			if (count($certificados) > 0) {
				?>
				<img src="media/icons/16x16/accept.png">&nbsp;&nbsp;
				<?php 
				$last = end(array_keys($certificados));
				foreach($certificados as $key => $certificado) {
					echo $certificado . ($key == $last?"":", ");
				}
				?>
				<?php 
			} else {
				?>
				<img src="media/icons/16x16/cancel.png">
				<?php
			}
			?>
		</td>
	</tr>
	
	<tr>
		<td class="key">
			Partners
		</td>
		<td>
			<?php
			if ($context["organizacion"]->partners ) {
				?>
				<img src="media/icons/16x16/accept.png">&nbsp;&nbsp;<?php echo $context["organizacion"]->partners ?>
				<?php 
			} else {
				?>
				<img src="media/icons/16x16/cancel.png">
				<?php
			}
			?>		</td>
	</tr>
	
	<tr>
		<td class="key">
			Realiza actividades de I+D
		</td>
		<td>
			<?php
			if ($context["organizacion"]->actividadesImasD ) {
				?>
				<img src="media/icons/16x16/accept.png">&nbsp;&nbsp;<?php echo $context["organizacion"]->actividadesImasD ?>
				<?php 
			} else {
				?>
				<img src="media/icons/16x16/cancel.png">
				<?php
			}
			?>		</td>
	</tr>
	<tr>
		<td class="key">
			Participaci&oacute;n en programas de I+D
		</td>
		<td>
			<?php
			if ($context["organizacion"]->participacionImasD ) {
				?>
				<img src="media/icons/16x16/accept.png">&nbsp;&nbsp;<?php echo $context["organizacion"]->participacionImasD ?>
				<?php 
			} else {
				?>
				<img src="media/icons/16x16/cancel.png">
				<?php
			}
			?>		</td>
	</tr>
	
	<tr>
		<td class="key">
			Relaciones con la Comunidad
		</td>
		<td>
			<?php
			if ($context["organizacion"]->relacionesComunidad ) {
				?>
				<img src="media/icons/16x16/accept.png">&nbsp;&nbsp; <?php echo $context["organizacion"]->relacionesComunidad  ?>
				<?php 
			} else {
				?>
				<img src="media/icons/16x16/cancel.png">
				<?php
			}
			?>
		</td>
	</tr>
	<tr>
		<td class="key">
			Pertenencia a Grupo Empresarial
		</td>
		<td>
			<?php
			if ($context["organizacion"]->grupoEmpresarial ) {
				?>
				<img src="media/icons/16x16/accept.png">&nbsp;&nbsp; <?php echo $context["organizacion"]->grupoEmpresarial  ?>
				<?php 
			} else {
				?>
				<img src="media/icons/16x16/cancel.png">
				<?php
			}
			?>
		</td>
	</tr>
	

</table>
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
			</span>
		</td>
		<td style="width:400px;padding:0px;">
			<div id="map_<?php echo $sede->id?>" style="width: 400px; height: 180px"></div>
			<script type="text/javascript">
			
			$(window).bind("load",function(){

				if (GBrowserIsCompatible()) {
					var map = new GMap2(document.getElementById("map_<?php echo $sede->id?>"));
					map.setCenter(new GLatLng(<?php echo $sede->latitud?>,<?php echo $sede->longitud?>), 14);
					var point = new GPoint(<?php echo $sede->longitud?>,<?php echo $sede->latitud?>);
					var marker = new GMarker(point);
					map.addOverlay(marker);
				}
			});
			</script>
		</td>
	</tr>
<?php
}
?>
</table>
</div>
<div id="asociaciones" class="ui-tabs-panel">
<table>
	<?php
	foreach ($context["asociaciones"] as $asociacion){
	?>	
		<tr>
			<td>
				<a href="<?php echo $asociacion->asociacion_id->url?>" target="new" ><img src="<?php echo $asociacion->asociacion_id->icon?>" /></a>
			</td>			
		</tr>
	<?php
	}
	?>
</table>
</div>


<div id="capacidades" class="ui-tabs-panel">
	<table class="grid capacidadesGrid">
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
				<td colspan="6" style="background-color:#dddddd">
				<?php
				$capacidad = new capacidad();
				$capacidad->open($capacidad_id);
				echo $capacidad->nombre;
				?>
				</td>
			</thead>
			<tbody>
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
					<?php echo $oferta->porcentajeFacturacion?>&nbsp;%
				</td>
				<td class="puntuacion">
					<?php echo $oferta->puntuacion?>
				</td>
			</tr>
	<?php
			}
		}
	?>
		</tbody>
	</table>
</div>
<div id="eventos" class="ui-tabs-panel">

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
					echo("<tr><td class=\"evento\"><div style=\"float:right;\"><b>Asistentes: </b>" . count($evento->getChildren("organizacionEvento")) . "</div>
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
						echo("<tr><td id=\"suscriptionDiv". $evento->id ."\" class=\"evento\"><div style=\"float:right;\"><b>Asistentes: </b>" . count($evento->getChildren("organizacionEvento")) . "</div>
						<p style=\"font-size:1.2em;color:#99008b\"><b>" . $evento->titulo . "</b></p>
					<p><b>Localización: </b>" . $evento->localizacion . "</p>
					<p><b>Fecha: </b>" . dateUStoEUR($evento->fecha) . "</p>
					<p>" . $evento->descripcion . "</p>
					<div style=\"text-align:right;padding:8px;\">");
					$currentDate = new DateTime($evento->fecha);
					if($currentDate >=$today){
					if($session->username == "") {
						echo("<b>Inicie sesión para suscribirse a este evento</b>");
					} else {
						
						if($session->userid==$evento->organizacion_id->id) {
							echo("<b>Usted es el organizador de este evento</b>");
						} else {
							$suscrito = false;
							foreach($organizacionUser->getChildren("organizacionEvento") as $organizacionEvento) {
								if($organizacionEvento->evento_id->id == $evento->id) $suscrito = true;
							}
							echo ($suscrito?"<b>Usted ya esta suscrito a este evento</b>":"<button title=\"Suscribirse a este evento\" onclick=\"suscribeTo(" . $evento->id . "," . $session->userid . ")\"><img src=\"media/icons/16x16/calendar_edit.png\"></button>");
						}
					}}
					$indice++;
					echo("</div>
					</td>
					</tr>");
					}
				}
			?>
	</tbody>
	</table>
</div>
<div id="demandas">
	<table class="grid capacidadesGrid">
		<thead>
			<td style="background-color:#bbbbbb">
				Servicio / Sector especializado
			</td>
			<td style="background-color:#bbbbbb">
				Descripci&oacute;n
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
				<td colspan="6" style="background-color:#dddddd">
				<?php
				$capacidad = new capacidad();
				$capacidad->open($capacidad_id);
				echo $capacidad->nombre;
				?>
				</td>
			</thead>
			<tbody>
			<?php
			foreach ($deman as $demanda)
			{
		?>
			<tr>
				<td class="capacidad">
					<?php echo $demanda->sector_id->nombre?>
				</td>
				<td>
					<?php echo $demanda->capacidad_id->descripcion . "<br />" . $demanda->descripcion?>
				</td>
			
				
			</tr>
	<?php
			}
		}
	?>
		</tbody>
	</table>
</div>
<!--<div id="congreso" class="ui-tabs-panel">
	<iframe src="congreso_public.php?organizacion_id=<?php echo $context["organizacion"]->id?>" style="height:500px;width:100%;border:none" noborder></iframe>
</div>
-->
</div>
<script type="text/javascript">
function toogleEventosPasados() {
if($("#eventosPasadosCheck").attr("checked") == true) {
	$("#eventosPasados").show('blind'),null,500;
} else {
	$("#eventosPasados").hide('blind'),null,500;
}
}

function suscribeTo(event_id,organizacion_id) {
	$("#dialog-suscribirse").dialog({
		resizable: false,
		modal: true,
		width: 400,
		buttons: {
			'Cancelar': function() {
				$(this).dialog('close');
			},
			'Deseo suscribirme': function() {
				$(this).dialog('close');
				$.ajax({ 
					url: "suscribirOrganizacionEvento2.php?event_id=" + event_id + "&organizacion_id=" + organizacion_id,
					success: function (data) {
						$("#suscriptionDiv" + event_id).html(data);
						
					}
				});
			}
		}
	});

}

$("#eventosPasados").hide();
$("#dialog-suscribirse").hide();
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
$('#tabs').tabs({ fx: { height: 'toggle', opacity: 'toggle', duration: 'fast' } });
});
</script>	
