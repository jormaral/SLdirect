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
<script src="http://maps.google.com/maps?file=api&amp;v=2&amp;key=<?php global $settings; echo $settings["gmaps.key"]?>" type="text/javascript"></script>
	<table style="margin-right:12px;">
		<tr>
			<td style="vertical-align:top;width:50%;background:#8b8fc6;-moz-border-radius:5px 5px 5px 5px">
				<h2 style="text-align:center;margin:0px;font-size:2.5em;letter-spacing:-2px;font-weight:normal;color:#ffffff">Para Clientes</h2>
				<div style="-moz-border-radius:5px;width:100%;background:#6b6fa6;color:white;height:240px;overflow:hidden;">
					<li style="font-size:1.4em;padding:12px;padding-bottom:0px;"> Encuentre empresas en su zona que ofrezcan servicios TIC basados en Software Libre y C&oacute;digo Abierto.</li>
					<li style="font-size:1.4em;padding:12px;padding-bottom:0px;padding-top:0px;"> Conozca empresas que le puedan ayudar en su proyecto en base a las capacidades necesarias para el mismo.</li>
					<li style="font-size:1.4em;padding:12px;padding-bottom:0px;padding-top:0px;"> Encuentre proveedores alternativos para su infraestructura TIC que utilicen Software Libre</li>
				</div>
			</td>
			<td style="width:64px;">
				&nbsp;
			</td>
			<td style="vertical-align:top;width:50%;background:#6b6fa6;-moz-border-radius:5px 5px 5px 5px">
				<h2 style="text-align:center;margin:0px;font-size:2.5em;letter-spacing:-2px;font-weight:normal;color:#ffffff">Para Proveedores</h2>
				<div style="-moz-border-radius:5px;width:100%;background:#8b8fc6;color:white;height:240px;overflow:hidden;">
					<li style="font-size:1.4em;padding:12px;padding-bottom:0px;"> Registre y de a conocer su empresa y sus capacidades en un directorio nacional.</li>
					<li style="font-size:1.4em;padding:12px;padding-bottom:0px;padding-top:0px;"> Aumente la visibilidad de sus servicios rellenando completamente su perfil de capacidades.</li>
					<li style="font-size:1.4em;padding:12px;padding-bottom:0px;padding-top:0px;"> Encuentre socios para proyectos demandando capacidades que su empresa no pueda desarrollar.</li>
				</div>
			</td>
		</tr>
		<tr>
			<td colspan="3" style="padding-top:12px;">
					<div style="width:100%;-moz-border-radius:5px;width:100%;background:#8b8fc6;color:white;height:550px;">
						<div style="padding:12px;">
						<table style="width:100%">
							<tr>
								<td>
									<input type="text" name="query" id="query2" style="-moz-border-radius:5px 0px 0px 5px;font-size:18px;border:inset 1px;width:100%;padding:3px;" />
								</td>
								<td style="width:32px;">
									<button style="-moz-border-radius:0px 5px 5px 0px;padding:5px;border:outset 1px;" onclick="document.location='busquedaTexto.php?query='+ $('#query2').attr('value');" ><img src="media/icons/16x16/zoom.png"></button>
								</td>
							</tr>
						</table>
					<div id="map" style="-moz-border-radius:5px;height:480px;border:inset 1px;margin:3px;margin-top:12px;background:white;color:black">
					</div>
				</div>
			</td>
		</tr>
	</table>
	
	<script type="text/javascript">
	var points = Array();
	
	var map = new GMap2(document.getElementById("map"));
	
	$(window).bind("load",function(){
		jQuery.getJSON("jsonOrganizacionesSedesAll.php",onReady);
	});
	
	function onReady(data,scope)
	{

			if (GBrowserIsCompatible()) {
				map.clearOverlays();			
				map.addControl(new GSmallMapControl());
				map.setCenter(new GLatLng(40.145289,-2.614746), 6);
			}
			points = Array();
			$.each(data, function(i,item){

				latlng = new GLatLng(item.latitud,item.longitud);
				point = new GPoint(item.longitud,item.latitud);
				var marker = new GMarker(point);
				points.push( latlng);
				map.addOverlay(marker);
				GEvent.addListener(marker, "click", function() {
				    		marker.openInfoWindowHtml(item.address_html);
				});
			});
			fitMap();
	}

	function loadAll(id){
		jQuery.getJSON("jsonOrganizacionesSedesAll.php",onReady);
	}


	function loadProvincia(id){
		jQuery.getJSON("jsonOrganizacionesSedesProvincias.php?provincia_id="+id,onReady);
	}

	function fitMap() {
	   var bounds = new GLatLngBounds();
	   for (var i=0; i< points.length; i++) {
	      bounds.extend(points[i]);
	   }
	   map.setZoom(map.getBoundsZoomLevel(bounds));
	   map.setCenter(bounds.getCenter());
	}
		
	</script>
