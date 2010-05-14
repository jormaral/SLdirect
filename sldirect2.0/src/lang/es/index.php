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

$database = database::singleton("mysql");
$newsExist = 1;

$events_sql = "SELECT eventos.id, titulo, eventos.descripcion, localizacion, organizacion_id, organizaciones.nombre as organizacion, date_format(fecha,\"%d-%m-%Y\") as fecha_formatted, fecha FROM eventos INNER JOIN organizaciones ON organizacion_id = organizaciones.id WHERE fecha >=date(now()) ORDER BY fecha ASC LIMIT 3";
$events_sql = "SELECT eventos.id, titulo, eventos.descripcion, localizacion, eventos.organizacion_id, organizaciones.nombre as organizacion, date_format(fecha,\"%d-%m-%Y\") as fecha_formatted, fecha, group_concat(concat('{',organizaciones_eventos.organizacion_id,',\\'',organizaciones_asistentes.nombre,'\\'}') ) as asistentes_nombres, count(organizaciones_eventos.id) as numero_asistentes FROM eventos INNER JOIN organizaciones ON eventos.organizacion_id = organizaciones.id LEFT JOIN organizaciones_eventos on organizaciones_eventos.evento_id = eventos.id left join organizaciones as organizaciones_asistentes on organizaciones_asistentes.id = organizaciones_eventos.organizacion_id WHERE fecha >=date(now()) group by eventos.id ORDER BY fecha ASC limit 3;";
$events_dates_all = "SELECT date_format(fecha,\"%e\") as dia,date_format(fecha,\"%c\") as mes,date_format(fecha,\"%Y\") as year, fecha FROM eventos";
?>
<script src="http://maps.google.com/maps?file=api&amp;v=2&amp;key=<?php global $settings; echo $settings["gmaps.key"]?>" type="text/javascript"></script>
<script src="lib/jquery.jcarousellite.pauseOnHover.js" type="text/javascript"></script> 

<div id="pop" style="width:600px;color: white;background:#8b8fc6;-moz-border-radius:5px 5px 5px 5px;display:block;z-index:3; position:absolute; border: 1px solid #333333; text-align:center;">
	<div id="cerrar" style="float:right; margin-right:12px; cursor:pointer; font:Verdana, Arial, Helvetica, sans-serif; font-size:20px; font-weight:bold; color:#FFFFFF; width:12px; position:relative; margin-top:-1px; text-align:center;">X</div> 
	
	<h3 id="news_title" style="text-align:left;margin:8px 0px 0px 16px;font-size:2em;letter-spacing:-1px;font-weight:normal;color:#ffffff">
	</h3>
	<p id="news_body" style="font-size:1.6em !important;padding:12px;padding-bottom:0px;padding-top:0px;"></p>
</div>
	<table id="content" style="margin-right:12px;">
		
<!--		<tr >
			<td style="vertical-align:top;width:420px;background:#8b8fc6;-moz-border-radius:5px 5px 5px 5px">
				<h2 onmouseover="toogle_boxes();" onmouseout="toogle_boxes();" style="cursor:pointer;text-align:center;margin:0px;font-size:25px;letter-spacing:-2px;font-weight:normal;color:#ffffff">Para Clientes</h2>
				<div id="clientes" style="z-index:100;-moz-border-radius:5px;background:#6b6fa6;color:white;height:0px;overflow:hidden;">
					<li style="font-size:12px;padding:12px;padding-bottom:0px;"> Encuentre empresas en su zona que ofrezcan servicios TIC basados en Software Libre y C&oacute;digo Abierto.</li>
					<li style="font-size:12px;padding:12px;padding-bottom:0px;padding-top:0px;"> Conozca empresas que le puedan ayudar en su proyecto en base a las capacidades necesarias para el mismo.</li>
					<li style="font-size:12px;padding:12px;padding-bottom:0px;padding-top:0px;"> Encuentre proveedores alternativos para su infraestructura TIC que utilicen Software Libre</li>
				</div>
			</td>
		
			<td  style="vertical-align:top;background:#6b6fa6;-moz-border-radius:5px 5px 5px 5px">
				<h2 onmouseover="toogle_boxes();" onmouseout="toogle_boxes();" style="cursor:pointer;text-align:center;margin:0px;font-size:25px;letter-spacing:-2px;font-weight:normal;color:#ffffff">Para Proveedores</h2>
				<div id="proveedores" style="z-index:100;-moz-border-radius:5px;background:#8b8fc6;color:white;height:0px;overflow:hidden;">
					<li style="font-size:12px;padding:12px;padding-bottom:0px;"> Registre y de a conocer su empresa y sus capacidades en un directorio nacional.</li>
					<li style="font-size:12px;padding:12px;padding-bottom:0px;padding-top:0px;"> Aumente la visibilidad de sus servicios rellenando completamente su perfil de capacidades.</li>
					<li style="font-size:12px;padding:12px;padding-bottom:0px;padding-top:0px;"> Encuentre socios para proyectos demandando capacidades que su empresa no pueda desarrollar.</li>
				</div>
			</td>
		</tr>-->
		<tr>
			<td style="width: 250px;padding-top:12px;">
			  <div style="width:100%;-moz-border-radius:5px;width:100%;padding:1px;background:#6b6fa6;color:white;height:400px;">
			    <h2 style="text-align:center;margin:0px;font-size:1.8em;letter-spacing:-2px;font-weight:normal;color:#ffffff">Empresas</h2>

				<div style="-moz-border-radius:5px;background: #fff;color:#000;margin: 4px 0px 0px 0px;height: 373px;overflow:auto">
				  <?php
    				$organizaciones2 = new organizacion();
				    $results2 = $organizaciones2->getList("hashCreacion='' ORDER BY nombre ASC");
				    $par=1;
				    foreach ($results2 as $result) {
					  $par++;
					  echo "<div onclick=\"document.location='organizacion.php?organizacion_id=" . $result->id . "'\" class=\"searchResultTitle\" style=\"" . ($par%2==0?"background-color:#cfcffa;":"") . ";cursor:pointer;padding:4px;margin:0px;font-weight:bold;font-size:12px;border-bottom: 1px #8b8fc6 solid\">" . $result->nombre . "</div>";
				    }
				  ?>
				</div>
			  </div>
			</td>
			<td colspan="2" style="padding-top:12px;">
					<div style="width:100%;-moz-border-radius:5px;width:100%;background:#8b8fc6;color:white;height:400px;">
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
					<div id="map" style="-moz-border-radius:5px;height:310px;border:inset 1px;margin:3px;margin-top:12px;background:white;color:black">
					</div>
				</div>
			</td>
		</tr>
		<?php 
		try {
		$database->query ("SELECT id, nombre, news_title as title, news_body as body FROM organizaciones WHERE news_title != \"\" AND news_body!=\"\" ORDER BY RAND()");
		} catch (DatabaseException $e) {
			$newsExist = 0;
		}
		if($newsExist==1) {?>
		<tr>
			<td colspan="3" style="vertical-align:top;width:100%;padding:0px;background:#6b6fa6;-moz-border-radius:5px 5px 5px 5px">
				<h2 style="text-align:center;margin:0px;font-size:1.8em;letter-spacing:-2px;font-weight:normal;color:#ffffff">Carrusel de Noticias</h2>
				<div class="carousel" style="width:100%;">
					<ul>
						<?php
						foreach ($database->rows as $news)
						{
							echo("
							  	<li style=\"width:100%;margin:1px\">
									<div id=\"item" . $news->id . "\" class=\"carousel_item\" style=\"-moz-border-radius:5px;width:100%;height: 80px;background:#8b8fc6;color:white;overflow:hidden;\">
										<h3 style=\"text-align:left;margin:8px 0px 0px 16px;font-size:1.2em;letter-spacing:-1px;font-weight:normal;color:#ffffff\">
									    " . strip_tags($news->title,"<b><i>") . "
										</h3>
										<span style=\"font-size:0.8em;margin:0px 0px 0px 16px;\">escrito por " . $news->nombre . "</span>
										<p style=\"font-size:1em !important;margin-top:4px !important;margin-bottom:4px !important;padding:0px;padding-bottom:0px;padding-top:0px;\">
										" . (strlen($news->body)>128?strip_tags(substr($news->body,0,128),"<b><i>") . "&hellip; <span style=\"font-size:0.8em;\"><a href=\"#\" onclick=\"mostrar('" . strip_tags($news->title,"<b><i>") . "','" . strip_tags($news->body,"<b><i>") . "');return false;\" >leer mas</a></span>":strip_tags($news->body,"<b><i>")) . "
										</p>
									</div>
								</li>
							");
						}
						?>
					</ul>
				</div>
			</td>
		</tr>
		<?php 
		}
		try {
			$database->query($events_sql);
		} catch (DatabaseException $e) {}
		if(count($database->rows)>0)
		{	
		?>
		<tr>
			<td colspan="3" style="vertical-align:top;width:100%;background:#6b6fa6;-moz-border-radius:5px 5px 5px 5px">
				<div id="dialog-suscribirse" title="¿Desea suscribirse a este evento?">
					<p style="text-align: justify;">Al suscribirse a este evento acepta que la empresa organizadora le envie informaci&oacute;n sobre el evento a trav&eacute;s del correo electr&oacute;nico.</p>
				</div>
				<h2 style="text-align:center;margin:0px;font-size:1.8em;letter-spacing:-2px;font-weight:normal;color:#ffffff">Calendario de eventos</h2>
				<div id="eventContainer" style="padding-bottom:15px;-moz-border-radius:5px;width:100%;height: <?php echo ((sizeof($database->rows)*30 + 160)>190?sizeof($database->rows)*30+160:"190"); ?>px;background:#8b8fc6;color:white;overflow:hidden;">
					<table style="height:100%">
					<tr><td id="eventtd" style="vertical-align:top">
					<div id="eventCalendarDiv" style="font-size:1.3em;margin-left:8px;position:absolute;top:20px;"></div>
					<h3 style="margin-left:210px"><p>Puede seleccionar una fecha para mostrar los eventos de ese día.</p></h3>
					<div id="events_resize" style="height:142px;width: 710px">
						<div id="accordion" style="width:82%;font-size:1.3em;margin-left:220px;margin-right:9px;">
						<?php
						$indice = 0;		
						$today = strtotime("today");
						foreach ($database->rows as $event) {
							echo("<h3><a href=\"#\">" . $event->fecha_formatted . " > " . $event->titulo . "</a></h3>");
							echo("<div id=\"suscriptionDiv" . $event->id . "\" style=\"height:100px\">
							<div>
							  <div style=\"float:right;\"><b>Asistentes: </b>" . $event->numero_asistentes . "</div><div><b>Empresa: </b>" . $event->organizacion . "<br/><b>Localización: </b>" . $event->localizacion . "<br/><b>Descripción: </b>" . $event->descripcion . "</div>
							  <div style=\"text-align:right;padding:8px;\">");
							$currentDate = strtotime($event->fecha);
							if($currentDate >=$today){
							if($session->username == "") {
								echo("<b>Inicie sesión para suscribirse a este evento</b>");
							} else {
								if($session->userid==$event->organizacion_id) {
									echo("<b>Usted es el organizador de este evento</b>");
								} else {
									$suscrito = false;
									foreach($organizacionUser->getChildren("organizacionEvento") as $organizacionEvento) {
										if($organizacionEvento->evento_id->id == $event->id) $suscrito = true;
									}
									echo ($suscrito?"<b>Usted ya esta suscrito a este evento</b>":"<button title=\"Suscribirse a este evento\" onclick=\"suscribeTo(" . $event->id . "," . $session->userid . "," . $indice . ")\"><img src=\"media/icons/16x16/calendar_edit.png\"></button>");
								}
							}}
							$indice++;
							echo("
							  </div>
							</div>
							</div>");//suscription div
						}
						?>



				</div> <!--accordion -->
				</div> <!-- events_resize -->
				
				</td></tr></div></table>
			</td>
		</tr>
		<?php
		}
		?>
	</table>
	
	<script type="text/javascript">
	var points = Array();
	var map = new GMap2(document.getElementById("map"));
	$(window).bind("load",function(){
		jQuery.getJSON("jsonOrganizacionesSedesAll.php",onReady);
	});
	
	function suscribeTo(event_id,organizacion_id,indice) {
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
						url: "suscribirOrganizacionEvento.php?event_id=" + event_id + "&organizacion_id=" + organizacion_id,
						success: function (data) {
							$("#suscriptionDiv" + event_id).html(data);
							$("#accordion").accordion('destroy');
							var height = $("#accordion h3").length*30 + 120 +95>255?$("#accordion h3").length*30 + 120 +95:255;
							$("#eventContainer").css("height",height + "px");
							$("#accordion").accordion({ header: "h3", fillSpace: true, active: indice });
							$("#eventContainer").resizable('option', 'minHeight' , height);
						}
					});
				}
			}
		});

	}
	
	$(function() {
		$("#eventContainer").resizable({
			resize: function(e, ui) {
				$("#events_resize").css('height',ui.size.height-50+'px');
				$("#accordion").accordion("resize");
				$("#accordion").css('height','100px');
			},
			minHeight:190,
			maxWidth:853,
			minWidth: 853
		});
	});	
	$(document).ready(function() {
		<?php
		$database->query($events_dates_all);
		echo ("var dates = [");
		foreach ($database->rows as $date) {
			echo("'" . $date->dia . "-" . $date->mes . "-" . $date->year . "'," );
		}
		echo("''];");
		?>
		$.datepicker.setDefaults($.datepicker.regional['es']);
		
		$('#eventCalendarDiv').datepicker({
		   	beforeShowDay: function (date){
					  var today=new Date();
                      for (i = 0; i < dates.length; i++) {
                          if ((date.getDate() + "-" + (date.getMonth()+1) + "-" + date.getFullYear()) == dates[i]) {
                          //[disable/enable, class for styling appearance, tool tip]
                          return [true,"ui-state-active-event","Mostrar eventos"];
                          }
                       }
					   if(date.getDate() == today.getDate() && date.getMonth() == today.getMonth() && date.getFullYear() == today.getFullYear())
						 return [false, "ui-state-highlight-event"];//enable all other days
		     		   return [false, "ui-state-disabled-event"];//enable all other days
					},
			onSelect: function(dateText, inst) {
				$.ajax({ 
					url: "getEventsForDay.php?fecha=" + dateText,
					success: function (data) {
						$("#accordion").html(data);
						$("#accordion").accordion('destroy');
						var height = $("#accordion h3").length*20 + 170>190?$("#accordion h3").length*20 + 170:190;
						
						$("#eventContainer").css('height',height);
						
						$("#accordion").accordion({ header: "h3", fillSpace: true });
						$("#events_resize").css('height',height-50+'px');
						$("#events_resize").css('width','710px');
						$("#accordion").accordion("resize");
						$("#accordion").css('height','100px');
					}
				})
			}
		});
		$("#accordion").accordion({ header: "h3", fillSpace: true });
		$("#dialog-suscribirse").hide();
	});

	function toogle_boxes() {
	  if( $("#clientes").height() < 1) {
	    $("#clientes").effect("size", { to: {height: 180} }, 300);
	  } else {
		$("#clientes").effect("size", { to: {height: 0} }, 300);
	  }
	  if( $("#proveedores").height() < 1) {
	    $("#proveedores").effect("size", { to: {height: 180} }, 300);
	  } else {
		$("#proveedores").effect("size", { to: {height: 0} }, 300);
	  }
	}

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
