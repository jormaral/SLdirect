<%@ include file="/WEB-INF/jsp/includes.jsp" %>

<jsp:include page="/WEB-INF/jsp/header.jsp" >
	<jsp:param name="pageTitle" value="Búsqueda Geográfica"/>
</jsp:include>

<div class="breadcrumb">
	<img src="<spring:url value="/static/images/icons/16x16/house.png" />">
	<a href="<spring:url value="/" />">Inicio</a>
	<img src="<spring:url value="/static/images/breadcrumb.png" />">
	<img src="<spring:url value="/static/images/icons/16x16/zoom.png" />">
	<a href="<spring:url value="/busqueda" />">Buscador de Empresas</a>
	<img src="<spring:url value="/static/images/breadcrumb.png" />">Buscador por Localizaci&oacute;n
</div>
<img src="<spring:url value="/static/images/busquedaGeograficaTitle.png" />" alt="Busqueda Geogr&aacute;fica" />
<script src="http://maps.google.com/maps?file=api&amp;v=2&amp;key=<fmt:message key="mapa.key"/>" type="text/javascript"></script>
<table style="width:100%;border-spacing:0px">
	<tr>
		<td style="width:90%;vertical-align:top;padding:0px;padding-left:32px;">
			<div id="map" style="width:99%;height:542px;border:inset 1px;margin-top:0px;background:white;color:black">&nbsp;</div>
		</td>
	</tr>
</table>

	<script type="text/javascript">
	var points = Array();
	
	var map = new GMap2(document.getElementById("map"));
	
	$(window).bind("load",function(){
		jQuery.getJSON("orgSede/all",onReady);
	});
	
	function onReady(data,scope) {
			if (GBrowserIsCompatible()) {
				map.clearOverlays();			
				map.addControl(new GSmallMapControl());
				map.setCenter(new GLatLng(40.416691, -3.700345), 6);
			}
			points = Array();
			
			$.each(data, function(i,item){
				latlng = new GLatLng(item.latitud,item.longitud);
				point = new GPoint(item.longitud,item.latitud);
				var marker = new GMarker(point);
				points.push( latlng);
				map.addOverlay(marker);
				GEvent.addListener(marker, "click", function() {
				    		marker.openInfoWindowHtml(item.address);
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
	   map.setCenter(bounds.getCenter());
	}
		
	</script>
	
<%@ include file="/WEB-INF/jsp/footer.jsp" %>