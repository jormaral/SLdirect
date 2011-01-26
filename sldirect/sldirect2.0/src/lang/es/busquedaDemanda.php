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
<div class="breadcrumb"><img src="media/icons/16x16/house.png"><a href="index.php">Inicio</a><img src="media/breadcrumb.png"><img src="media/icons/16x16/zoom.png"><a href="busqueda.php">Buscador de Empresas</a><img src="media/breadcrumb.png">Buscador por Demanda</div>

<img src="media/busquedaDemandaTitle.png" alt="Busqueda por Servicios"  />
<script src="http://maps.google.com/maps?file=api&amp;v=2&amp;key=<?php global $settings; echo $settings["gmaps.key"]?>" type="text/javascript"></script>
<table style="height:320px;width:100%;margin:0px">
	<tr>

		<td style="vertical-align:top">
				<table class="grid" style="width:100%;">
					<thead>
						<td colspan="2">
							Servicios Ofertados 
						</td>
					</thead>
					<tbody style="height:320px;overflow-y:scroll;overflow-x:hidden">
	<?php
		$capacidades = new capacidad();
		$last_cat = "";
		foreach ($capacidades->getList("1=1 ORDER BY categoria,nombre") as $capacidad)
		{
			if ($last_cat != $capacidad->categoria)
			{
				?>
				<tr style="height:12px">
					<td colspan="2" style="font-weight:bold;background:#ff5aaf;color:white;"><?php echo $capacidad->categoria?></td>
				</tr>
				
				<?php 
				$last_cat = $capacidad->categoria;
			}
	?>
					<tr style="height:12px;">
						<td style="width:28px;padding:0px;text-align:center;">
							<input class="chck_capacidad" type="checkbox" onclick="search();" capacidad_id="<?php echo $capacidad->id?>">
						</td>
						<td style="padding:0px;">
							<b><?php echo $capacidad->nombre?></b>
						</td>
					</tr>
	<?php
		}
	?>				
					</tbody>
				</table>
		</td>
				<td style="vertical-align:top;width:490px;">
					<table class="grid" style="width:100%;border-spacing:0px">
						<thead>
							<td colspan="2">
								Sectores 
							</td>
						</thead>
						<tbody style="height:320px;width:100%;overflow-y:scroll;overflow-x:hidden">
		<?php
			$sectores = new sector();
			foreach ($sectores->getList("1=1 ORDER by nombre=\"Todos los sectores\" desc, nombre") as $sector)
			{
		?>
						<tr style="height:12px;">
							<td style="padding:0px;text-align:center">
								<input class="chck_sector" type="checkbox" onclick="search();" sector_id="<?php echo $sector->id?>">
							</td>
							<td style="padding:0px;">
								<b><?php echo $sector->nombre?></b>
							</td>
						</tr>
		<?php
			}
		?>				
						</tbody>
					</table>
				</td>
	</tr>
</table>
<div id="tabs">
            <ul>
                <li class="ui-tabs-nav-item"><a href="#listado"><span><img src="media/icons/16x16/table.png" />Resultados en Listado</span></a></li>
                <li class="ui-tabs-nav-item"><a href="#mapa"><span><img src="media/icons/16x16/map.png" /> Resultados en Mapa </span></a></li>
            </ul>
<div id="listado" class="ui-tabs-panel">
	<table class="grid" style="width:100%">
		<thead>
			<td>
				Organizaci&oacute;n
			</td>
			<td>
				Criterios que Concuerdan
			</td>
			<td>
				Direcci&oacute;n
			</td>
			<td style="width:70px;">
				Provincia
			</td>
		</thead>
		<tbody id="results" />
	</table>
</div>
<div id="mapa" class="ui-tabs-panel">
		<div id="map" style="border:inset 1px;height:400px;">map</div>
</div>
</div>

			<script>
			$(window).bind("load",function(){
				$("#tabs").tabs({ fx: { height: 'toggle', opacity: 'toggle', duration: 'fast' } });
			});
	

	
	var map = new GMap2(document.getElementById("map"));

	function onReady(data,scope)
	{
		
		map.clearOverlays();			
		while (!document.getElementById('results').childNodes.length == 0)
			document.getElementById('results').removeChild(document.getElementById('results').childNodes[0]);
		var ids = "0";	
		
		$.each(data, function(i,item){
			tr = document.createElement("TR");
			tr.style.cursor="pointer";
			tr.onclick=function(){ document.location = "organizacion.php?organizacion_id="+item.organizacion_id }
			td = document.createElement("TD");
			td.innerHTML = "<b>"+item.organizacion_nombre+"</b>";
			tr.appendChild(td);
			
			td = document.createElement("TD");
			td.innerHTML = "<b>"+item.capacidad_match+"</b> para sector <b>"+item.sector_match+"</b>";
			tr.appendChild(td);

			td = document.createElement("TD");
			td.innerHTML = ""+item.organizacion_direccion+"";
			tr.appendChild(td);

			td = document.createElement("TD");
			td.innerHTML = ""+item.organizacion_provincia+"";
			tr.appendChild(td);
			document.getElementById('results').appendChild(tr);
			

			ids = ids + ","+item.organizacion_id;
		});
		
		
		//jQuery.getJSON("jsonOrganizacionesSedes.php?organizaciones="+ids,onReadyMap);
		$.ajax({
		  url: "jsonOrganizacionesSedes.php?organizaciones="+ids,
		  cache: false,
		  dataType: "json",
		  success: onReadyMap});
		
	}

	function search(){
		var sectores_query="";
		var capacidades_query="";
		$.each($(".chck_sector"), function(i,item){
			if (item.checked){
				sectores_query=sectores_query + ","+item.getAttribute("sector_id");
			}
		});
		$.each($(".chck_capacidad"), function(i,item){
			if (item.checked){
				capacidades_query=capacidades_query + ","+item.getAttribute("capacidad_id");
			}
		});
//		jQuery.getJSON("jsonOrganizacionesSectoresDemandas.php?sectores="+sectores_query+"&capacidades="+capacidades_query,onReady);
		
		$.ajax({
		  url: "jsonOrganizacionesSectoresDemandas.php?sectores="+sectores_query+"&capacidades="+capacidades_query,
		  cache: false,
		  dataType: "json",
		  error:function (x,t,e){
			alert(e);
		},
		  success: onReady});
		
	}
	function onReadyMap(data,scope)
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

	function fitMap() {
	   var bounds = new GLatLngBounds();
	   for (var i=0; i< points.length; i++) {
	      bounds.extend(points[i]);
	   }
	   map.setZoom(map.getBoundsZoomLevel(bounds)-1);
	   map.setCenter(bounds.getCenter());
	}
	
	$(window).bind("load",function(){
		$.each($(".chck_sector"), function(i,item){
			item.checked = false;
		});
		
		$.each($(".chck_capacidad"), function(i,item){
			item.checked = false;
		});
		
	});
</script>
