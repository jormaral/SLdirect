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
<div class="breadcrumb"><img src="media/icons/16x16/house.png"><a href="index.php">Inicio</a><img src="media/breadcrumb.png"><img src="media/icons/16x16/zoom.png"><a href="busqueda.php">Buscador de Empresas</a><img src="media/breadcrumb.png">Buscador por Localizaci&oacute;n</div>
<img src="media/busquedaGeograficaTitle.png" alt="Busqueda Geogr&aacute;fica" />
<script src="http://maps.google.com/maps?file=api&amp;v=2&amp;key=<?php global $settings; echo $settings["gmaps.key"]?>" type="text/javascript"></script>
<table style="width:100%;border-spacing:0px">
	<tr>
		<!--<td style="vertical-align:top;overflow:auto;padding:0px;">
			<table style="border:none;width:320px;margin:0px;padding:0px;border-spacing:0px" class="grid">
				<tr>
						<td colspan="4" style="cursor:pointer" onclick="loadAll();">
							<center><b>Todas las provincias</b></center>
						</td>
				</tr>
		<?php/*
		$provincias = new provincia();
		$list = $provincias->getList("1=1 order by nombre asc");
		$midsize = round(count ($list) / 2);
		for ($i=0;$i<$midsize;$i++){
		*/?>
				<tr>
					<td style="width:40%;height:10px;cursor:pointer;" onmouseover="this.style.background= '#ff5aaf'" onmouseout="this.style.background= '#ffffff'"   onclick="loadProvincia(<?php echo $list[$i]->id?>);">
					<b>	<?php/* echo $list[$i]->nombre*/?></b>
					</td>
					<td style="padding:1px;height:12px;padding-left:2px;text-align:center">
						<b>(<?php/* echo count($list[$i]->getChildren("organizacionSede"))*/?>)</b>
					</td>
					<td style="height:10px;cursor:pointer" onmouseover="this.style.background= '#ff5aaf'" onmouseout="this.style.background= '#ffffff'"  onclick="loadProvincia(<?php echo $list[$midsize+$i]->id?>);">
						<b><?php/* echo $list[$midsize+$i]->nombre*/?></b>
					</td>
					<td style="padding:1px;height:10px;text-align:center">
						<b>(<?php/* echo count($list[$midsize+$i]->getChildren("organizacionSede"))*/?>)</b>
					</td>
				</tr>
		<?php
		//}
		?>
			</table>
		</td>-->
		<td style="width:90%;vertical-align:top;padding:0px;padding-left:32px;">
			<div id="map" style="width:99%;height:542px;border:inset 1px;margin-top:0px;background:white;color:black">&nbsp;</div>
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
				map.setCenter(new GLatLng(<?php global $settings; echo ($settings ["mainmap.lat"] . "," . $settings["mainmap.long"])?>), <?php global $settings; echo ($settings ["mainmap.zoom"])?>);
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
	
