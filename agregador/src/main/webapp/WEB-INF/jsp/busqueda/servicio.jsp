<%@ include file="/WEB-INF/jsp/includes.jsp" %>

<jsp:include page="/WEB-INF/jsp/header.jsp" >
	<jsp:param name="pageTitle" value="Buscador por Servicio"/>
</jsp:include>

<div class="breadcrumb">
	<img src="<spring:url value="/static/images/icons/16x16/house.png" />">
	<a href="<spring:url value="/" />">Inicio</a>
	<img src="<spring:url value="/static/images/breadcrumb.png" />">
	<img src="<spring:url value="/static/images/icons/16x16/zoom.png" />">
	<a href="<spring:url value="/busqueda" />">Buscador de Empresas</a>
	<img src="<spring:url value="/static/images/breadcrumb.png" />">Buscador por Servicio
</div>
<img src="<spring:url value="/static/images/busquedaServiciosTitle.png" />" alt="Busqueda por Servicios" />
<script src="http://maps.google.com/maps?file=api&amp;v=2&amp;key=<fmt:message key="mapa.key"/>" type="text/javascript"></script>

<table style="height:320px;width:100%;margin:0px">
	<tr style="height:320px;">
			<td style="vertical-align:top">
					<table class="grid" style="width:100%;overflow:scroll">
						<thead>
							<td colspan="2">
								Servicios Ofertados 
							</td>
						</thead>
						<tbody style="height:320px;overflow-y:scroll;overflow-x:hidden">
<c:forEach var="element" items="${capMap}">
					<tr style="height:12px">
						<td colspan="2" style="font-weight:bold;background:#ff5aaf;color:white;">
							${element.key}
						</td>
					</tr>
	<c:forEach var="capacidad" items="${element.value}">
					<tr style="height:12px;">
						<td style="width:28px;padding:0px;text-align:center;">
							<input class="chck_capacidad" type="checkbox" onclick="search();" capacidad_id="${capacidad.id}">
						</td>
						<td style="padding:0px;">
							<b>${capacidad}</b>
						</td>
					</tr>
	</c:forEach>
</c:forEach>
						</tbody>
					</table>
			</td>
		<td style="vertical-align:top;width:490px;vertical-align:top">
			<table class="grid" style="height:320px;overflow:auto;width:100%;border-spacing:0px">
				<thead>
					<td colspan="2">
						Sectores 
					</td>
				</thead>
				<tbody style="height:320px;width:100%;overflow:auto;overflow-y:auto;overflow-x:hidden">
<c:forEach var="sector" items="${sectores}">
						<tr style="height:12px;">
							<td style="padding:0px;text-align:center">
								<input class="chck_sector" type="checkbox" onclick="search();" sector_id="${sector.id}">
							</td>
							<td style="padding:0px;">
								<b>${sector}</b>
							</td>
						</tr>
</c:forEach>
				</tbody>
			</table>
		</td>


	</tr>
</table>
<div id="tabs">
            <ul>
                <li class="ui-tabs-nav-item"><a href="#listado"><span><img src="<spring:url value="/static/images/icons/16x16/table.png" />" />Resultados en Listado</span></a></li>
                <li class="ui-tabs-nav-item"><a href="#mapa"><span><img src="<spring:url value="/static/images/icons/16x16/map.png" />" />Resultados en Mapa </span></a></li>
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

<script><!--
			
	$(window).bind("load",function(){
		$("#tabs").tabs({ fx: { height: 'toggle', opacity: 'toggle', duration: 'fast' } });
	});
	
	var map = new GMap2(document.getElementById("map"));

	function clearElements(){
		map.clearOverlays();			
		while (!document.getElementById('results').childNodes.length == 0)
			document.getElementById('results').removeChild(document.getElementById('results').childNodes[0]);
	}
	
	function onReady(data,scope){
		clearElements();
		var ids = "0";	

		$.each(data, function(i,item){
			tr = document.createElement("TR");
			tr.style.cursor="pointer";
			tr.onclick=function(){ 
				document.location = "orgs/"+item.id; 
			};
			td = document.createElement("TD");
			td.innerHTML = "<b>"+item.nombre+"</b>";
			tr.appendChild(td);
			
			td = document.createElement("TD");
			td.innerHTML = "<b>"+item.capacidadMatch+"</b> para sector <b>"+item.sectorMatch+"</b>";
			tr.appendChild(td);

			td = document.createElement("TD");
			td.innerHTML = ""+item.direccion+"";
			tr.appendChild(td);

			td = document.createElement("TD");
			td.innerHTML = ""+item.provincia+"";
			tr.appendChild(td);
			document.getElementById('results').appendChild(tr);
			
			ids = ids + ","+item.id;
		});
		$.ajax({
			  url: "orgSede/?ids="+ ids,
			  cache: false,
			  dataType: "json",
			  success: onReadyMap});
	}

	function search(){
		var sectores_query="";
		var capacidades_query="";
		var sep = "";
		$.each($(".chck_sector"), function(i,item){
			if (item.checked){
				sectores_query = sectores_query + sep + item.getAttribute("sector_id");
				sep = ", ";
			}
		});
		sep = "";
		$.each($(".chck_capacidad"), function(i,item){
			if (item.checked){
				capacidades_query=capacidades_query + sep + item.getAttribute("capacidad_id");
				sep = ", ";
			}
		});

		
		var ajaxUrl = "orgs";
		if(sectores_query != "")
			ajaxUrl+="/sectores/"+sectores_query;
		if(capacidades_query != "")
			ajaxUrl+= "/capacidades/"+capacidades_query;

		if(sectores_query != "" || capacidades_query != ""){
			ajaxUrl+= "/servicio";		
			jQuery.getJSON(ajaxUrl,onReady);
		}else{
			clearElements();
		}
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
					document.location='orgs/' + item.id;
//				    marker.openInfoWindowHtml(item.address_html);
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
--></script>


<%@ include file="/WEB-INF/jsp/footer.jsp" %>