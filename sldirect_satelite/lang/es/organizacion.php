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

?>
<div class="breadcrumb"><img src="media/icons/16x16/house.png"><a href="index.php">Inicio</a><img src="media/breadcrumb.png"><img src="media/icons/16x16/zoom.png"><a href="busqueda.php">Buscador de Empresas</a><img src="media/breadcrumb.png"><?php echo $context["organizacion"]->nombre?></div>
<script src="http://maps.google.com/maps?file=api&amp;v=2&amp;key=<?php global $settings; echo $settings["gmaps.key"]?>" type="text/javascript"></script>
<div id="tabs" >
            <ul>
                <li class="ui-tabs-nav-item"><a href="#general"><span><img src="media/icons/16x16/book.png" /><?php echo $context["organizacion"]->nombre?></span></a></li>
                <li class="ui-tabs-nav-item"><a href="#asociaciones"><span><img src="media/icons/16x16/map.png" /> Asociaciones </span></a></li>
                <li class="ui-tabs-nav-item"><a href="#sedes"><span><img src="media/icons/16x16/map.png" /> Sedes </span></a></li>
                <li class="ui-tabs-nav-item"><a href="#capacidades"><span><img src="media/icons/16x16/wrench.png" />Servicios</span></a></li>
                <li class="ui-tabs-nav-item"><a href="#demandas"><span><img src="media/icons/16x16/wrench_orange.png" />Demanda</span></a></li>
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
		<td colspan="2" class="separator">
			Informaci&oacute;n Adicional
		</td>
	</tr>
	<tr>
		<td class="key">
			Certificaciones de Calidad
		</td>
		<td>
			<?php
			if ($context["organizacion"]->certificacionesCalidad ) {
				?>
				<img src="media/icons/16x16/accept.png">&nbsp;&nbsp;<?php echo $context["organizacion"]->certificacionesCalidad ?>
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
				<td colspan="6" >
				<?php
				$sector = new sector();
				$sector->open($sector_id);
				echo $sector->nombre;
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
					<?php echo $oferta->capacidad_id->nombre?>
				</td>
				<td class="descripcion">
					<?php echo $oferta->capacidad_id->descripcion?>
				</td>
				<td class="personas">
					<?php echo $oferta->recursos?>&nbsp;personas.
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

<div id="demandas">
	<table class="grid capacidadesGrid">
		<thead>
			<td>
				Servicio
			</td>
			<td>
				Descripci&oacute;n
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
				<td colspan="6" >
				<?php
				$sector = new sector();
				$sector->open($sector_id);
				echo $sector->nombre;
				?>
				</td>
			</thead>
			<tbody>
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
			
				
			</tr>
	<?php
			}
		}
	?>
		</tbody>
	</table>
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
