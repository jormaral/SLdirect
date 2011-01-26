<%@ include file="/WEB-INF/jsp/includes.jsp" %>
<jsp:include page="/WEB-INF/jsp/header.jsp" >
	<jsp:param name="pageTitle" value="Directorio"/>
</jsp:include>

<script type="text/javascript" src="http://maps.google.com/maps?file=api&amp;v=2&amp;key=<fmt:message key="mapa.key"/>"></script>
<script type="text/javascript" src="<spring:url value="/static/lib/jquery.jcarousellite.pauseOnHover.js" />" ></script>     

<div id="pop" style="width:600px;color: white;background:#8b8fc6;-moz-border-radius:5px 5px 5px 5px;display:block;z-index:3; position:absolute; border: 1px solid #333333; text-align:center;">
	<div id="cerrar" style="float:right; margin-right:12px; cursor:pointer; font:Verdana, Arial, Helvetica, sans-serif; font-size:20px; font-weight:bold; color:#FFFFFF; width:12px; position:relative; margin-top:-1px; text-align:center;">X</div> 
	
	<h3 id="news_title" style="text-align:left;margin:8px 0px 0px 16px;font-size:2em;letter-spacing:-1px;font-weight:normal;color:#ffffff">
	</h3>
	<p id="news_body" style="font-size:1.6em !important;padding:12px;padding-bottom:0px;padding-top:0px;"></p>
</div>
	<table id="content" style="width:100%;margin-right:12px;">
	
		<tr>
			<td colspan="3" style="padding-top:12px;">
					<div style="width:100%;-moz-border-radius:5px;width:100%;background:#8b8fc6;color:white;height:600px;">
						<div style="padding:12px;">
						<table style="width:100%">
							<tr>
								<td>
									<input type="text" name="query" id="query2" style="-moz-border-radius:5px 0px 0px 5px;font-size:18px;border:inset 1px;width:100%;padding:3px;" />
								</td>
								<td style="width:32px;">
									<button style="-moz-border-radius:0px 5px 5px 0px;padding:5px;border:outset 1px;" onclick="document.location='busquedaTexto?q='+ $('#query2').attr('value');" >
									<img src="<spring:url value="/static/images/icons/16x16/zoom.png" />">
									</button>
								</td>
							</tr>
						</table>
					<div id="map" style="-moz-border-radius:5px;height:530px;border:inset 1px;margin:3px;margin-top:12px;background:white;color:black">
					</div>
				</div>
			</td>
		</tr>
	<!-- Últimas Organizaciones -->		
<c:if test="${!empty latestOrgs}">
		<tr>
			<td colspan="3" style="vertical-align:top;width:100%;padding:0px;background:#6b6fa6;-moz-border-radius:5px 5px 5px 5px">
				<h2 style="text-align:center;margin:0px;font-size:1.8em;letter-spacing:-2px;font-weight:normal;color:#ffffff">&Uacute;ltimas Organizaciones</h2>
				<div class="carousel" style="width:100%;">
					<ul>
<c:forEach var="organizacion" items="${latestOrgs}" varStatus="status">
	<spring:url value="/orgs/{orgId}" var="orgUrl">
		<spring:param name="orgId" value="${organizacion.id}" />
	</spring:url>
						<li style="width:100%;margin:1px">
							<div id="item${organizacion.id}" class="carousel_item" style="-moz-border-radius:5px;width:100%;height: 80px;background:#8b8fc6;color:white;overflow:hidden;">
								<h3 style="text-align:left;margin:8px 0px 0px 16px;font-size:1.2em;letter-spacing:-1px;font-weight:normal;color:#ffffff">
								<a href="${fn:escapeXml(orgUrl)}">${organizacion.name}</a>
									
								</h3>
								<span style="font-size:0.8em;margin:0px 0px 0px 16px;">provincia ${organizacion.provincia}</span>
									<p style="font-size:1em !important;margin-top:4px !important;margin-bottom:4px !important;padding:0px;padding-bottom:0px;padding-top:0px;">
									${organizacion.descripcion}									
									</p>
									</div>
								</li>
</c:forEach>					
					</ul>
				</div>
			</td>
		</tr>
</c:if>		
	<!-- Noticias -->		
<c:if test="${!empty noticias}">
		<tr>
			<td colspan="3" style="vertical-align:top;width:100%;padding:0px;background:#6b6fa6;-moz-border-radius:5px 5px 5px 5px">
				<h2 style="text-align:center;margin:0px;font-size:1.8em;letter-spacing:-2px;font-weight:normal;color:#ffffff">Carrusel de Noticias</h2>
				<div class="carousel" style="width:100%;">
					<ul>
<c:forEach var="noticia" items="${noticias}" varStatus="status">
						<li style="width:100%;margin:1px">
							<div id="item${noticia.id}" class="carousel_item" style="-moz-border-radius:5px;width:100%;height: 80px;background:#8b8fc6;color:white;overflow:hidden;">
								<h3 style="text-align:left;margin:8px 0px 0px 16px;font-size:1.2em;letter-spacing:-1px;font-weight:normal;color:#ffffff">
									${noticia.title}
								</h3>
								<span style="font-size:0.8em;margin:0px 0px 0px 16px;">escrito por ${noticia.name}</span>
									<p style="font-size:1em !important;margin-top:4px !important;margin-bottom:4px !important;padding:0px;padding-bottom:0px;padding-top:0px;">
<c:choose>
	<c:when test="${fn:length(noticia.body) gt 128}">
		${fn:substring(noticia.body, 0, 128)} "&hellip; 
		<span style="font-size:0.8em;">
			<a href="#" onclick="mostrar('${noticia.title}','${noticia.body}');return false;" >leer mas</a>
		</span>
	</c:when>
	<c:otherwise>
		${noticia.body}	
	</c:otherwise>
</c:choose>										
										
										</p>
									</div>
								</li>
</c:forEach>					
					</ul>
				</div>
			</td>
		</tr>
</c:if>		
		<tr>
			<td colspan="3" style="vertical-align:top;width:100%;background:#6b6fa6;-moz-border-radius:5px 5px 5px 5px">
				<div id="dialog-suscribirse" title="¿Desea suscribirse a este evento?">
					<p style="text-align: justify;">Al suscribirse a este evento acepta que la empresa organizadora le envie informaci&oacute;n sobre el evento a trav&eacute;s del correo electr&oacute;nico.</p>
				</div>
				<h2 style="text-align:center;margin:0px;font-size:1.8em;letter-spacing:-2px;font-weight:normal;color:#ffffff">Calendario de eventos</h2>
				<div id="eventContainer" style="padding-bottom:15px;-moz-border-radius:5px;width:100%;height:190px;background:#8b8fc6;color:white;overflow:hidden;">
					<table style="height:100%">
					<tr><td id="eventtd" style="vertical-align:top">
					<div id="eventCalendarDiv" style="font-size:1.3em;margin-left:8px;position:absolute;top:20px;"></div>
					<h3 style="margin-left:210px"><p>Puede seleccionar una fecha para mostrar los eventos de ese día.</p></h3>
					<div id="events_resize" style="height:142px;width: 710px">
						<div id="accordion" style="width:82%;font-size:1.3em;margin-left:220px;margin-right:9px;">
<c:forEach var="evento" items="${eventos}" >
     <spring:url value="/orgs/{orgId}" var="editUrl">
     	<spring:param name="orgId" value="${evento.id}" />
     </spring:url>
							<h3><a href="#">
							<fmt:formatDate value="${evento.fecha}" pattern="dd-MM-yyyy"/> &gt; ${evento.titulo} </a></h3>
							<div id="suscriptionDiv${evento.id}" style="height:100px">
							<div>
							  <div style="float:right;"><b>Asistentes: </b>0</div>
							  <div><b>Empresa: </b><a href="${fn:escapeXml(editUrl)}">${evento.orgName}</a><br/><b>						  
							  Localización: </b>${evento.localizacion}
								<br/><b>Descripción: </b>${evento.descripcion}</div>
							  <div style="text-align:right;padding:8px;">
							  </div>
							</div>
							</div>
</c:forEach>						
				</div> <!--accordion -->
				</div> <!-- events_resize -->
				
				</td></tr></div></table>
			</td>
		</tr>
	</table>
	
	<script type="text/javascript">
	var points = Array();
	var map = new GMap2(document.getElementById("map"));
	$(window).bind("load",function(){
		jQuery.getJSON("satelites/all",onReady);
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
		var dates = [${fechasEventos}];

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

	function onReady(data,scope){
			if (GBrowserIsCompatible()) {
				map.clearOverlays();			
				map.addControl(new GSmallMapControl());
				map.setCenter(new GLatLng(40.563895, -3.058203), 6);
			}
			points = Array();
			$.each(data, function(i,item){
				latlng = new GLatLng(item.lat,item.lon);
				point = new GPoint(item.lon,item.lat);
				var marker = new GMarker(point);
				points.push( latlng);
				map.addOverlay(marker);
				GEvent.addListener(marker, "click", function() {
					document.location='satelites/' + item.id + '/orgs';
				   	//marker.openInfoWindowHtml(item.html);
				});
			});
			
	}

	function loadAll(id){
		jQuery.getJSON("orgSede/all",onReady);
	}


	function loadProvincia(id){
		jQuery.getJSON("orgSede/provincia/"+id,onReady);
	}

	function fitMap() {
	   var bounds = new GLatLngBounds();
	   for (var i=0; i< points.length; i++) {
	      bounds.extend(points[i]);
	   }
	   map.setZoom(map.getBoundsZoomLevel(bounds));
//	   map.setCenter(bounds.getCenter());
	}
		
	</script>	

<%@ include file="/WEB-INF/jsp/footer.jsp" %>