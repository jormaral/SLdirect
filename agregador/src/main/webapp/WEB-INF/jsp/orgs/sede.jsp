<%@ include file="/WEB-INF/jsp/includes.jsp" %>
<jsp:include page="/WEB-INF/jsp/header.jsp" >
	<jsp:param name="pageTitle" value="Editar Sede"/>
</jsp:include>

<c:set var="method" value="put"/>

<div class="breadcrumb">
	<img src="<spring:url value="/static/images/icons/16x16/house.png" />">
	<a href="<spring:url value="/" />">Inicio</a>
	<img src="<spring:url value="/static/images/breadcrumb.png" />">
	<a href="<spring:url value="/admin" />">Administración</a>
	<img src="<spring:url value="/static/images/breadcrumb.png" />">
</div>
<script src="http://maps.google.com/maps?file=api&amp;v=2&amp;key=<fmt:message key="mapa.key"/>" type="text/javascript"></script>

<div id="tabs">
	<ul>
		<li class="ui-tabs-nav-item"><a href="#general"><span><img src="<spring:url value="/static/images/icons/16x16/book.png" />" />Modificar Sede</span></a></li>
  	</ul>
	<div id="sedes" class="ui-tabs-panel">
	<table class="viewForm">
	<tr>
		<td colspan="2" class="separator">Modificar Sede </td>
	</tr>
	<tr>
		<td>
		<form:form id="sedeMod" modelAttribute="organizacionSede" method="${method}">
		<img src="<spring:url value="/static/images/icons/16x16/map.png" />">
		<form:select style="font-size:12px;" path="provincia" items="${provincias}"/>
		<span style="font-weight:normal"><br />
			<img src="<spring:url value="/static/images/icons/16x16/house.png" />" />&nbsp;
			<form:input style="font-size:12px;" path="direccion" onclick="if (this.value=='[Direccion]') this.value=''" /> / 
			<form:input style="font-size:12px;" path="localidad" onclick="if (this.value=='[Localidad]') this.value=''" /><br />
			<br /><img src="<spring:url value="/static/images/icons/16x16/phone.png" />" /> &nbsp;
			<form:input style="font-size:12px;"  path="telefonoContacto" onclick="if (this.value=='[Tel&eacute;fono Contacto]') this.value=''" />
			<br /><img src="<spring:url value="/static/images/icons/16x16/user_suit.png" />" /> &nbsp;
			<form:input style="font-size:12px;"  path="personaContacto" onclick="if (this.value=='[Persona Contacto]') this.value=''" />
			<br /><img src="<spring:url value="/static/images/icons/16x16/email.png" />" /> &nbsp;
			<form:input style="font-size:12px;"  path="mailContacto" onclick="if (this.value=='[e-Mail Contacto]') this.value=''" />
			<br /><img src="<spring:url value="/static/images/icons/16x16/user.png" />" /> &nbsp;
			<form:input style="font-size:12px;"  size="3" path="hombres" onclick="if (this.value=='[H]') this.value=''" />
			<br /><img src="<spring:url value="/static/images/icons/16x16/user_female.png" />" /> &nbsp;
			<form:input style="font-size:12px;" size="3"  path="mujeres" onclick="if (this.value=='[M]') this.value=''" />
			<br /><img src="<spring:url value="/static/images/icons/16x16/world.png" />" />&nbsp;
			<form:input style="font-size:12px" path="latitud"  id="latitud" size="6" /> N 
			<form:input style="font-size:12px" path="longitud" id="longitud" size="6" /> O
		</span><br /><br />
		</form:form>
		</td>
		<td style="width:400px;padding:0px;text-align:center">
			<div id="map_add" style="width: 400px; height: 180px"></div>
			<span style="font-size:9px;text-align:center">Haga click simple para marcar la localizaci&oacute;n de la sede en el mapa.<br />Haga click doble para hacer zoom en el mapa.</span>
			<script type="text/javascript">
				function z_createmap(){
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
			}
			setTimeout("z_createmap()",2500);
			</script>
		</td>
	</tr>
	</table>
	<button onclick="document.getElementById('sedeMod').submit();"><img src="<spring:url value="/static/images/icons/16x16/disk.png" />" />Guardar</button>
<spring:url value="/orgs/{organizacionId}/edit#sedes" var="cancelUrl" >
	<spring:param name="organizacionId" value="${organizacionSede.organizacion.id}" />
</spring:url>	
	<button onclick="document.location='${fn:escapeXml(cancelUrl)}'"><img src="<spring:url value="/static/images/icons/16x16/cancel.png" />" />Cancelar</button>

	</div>
</div>

<script type="text/javascript">
$(window).bind("load",function(){
$('#tabs').tabs({ fx: { height: 'toggle', opacity: 'toggle', duration: 'fast' } });
});
</script>

<%@ include file="/WEB-INF/jsp/footer.jsp" %>