<%@ include file="/WEB-INF/jsp/includes.jsp" %>

<jsp:include page="/WEB-INF/jsp/header.jsp" >
	<jsp:param name="pageTitle" value="Organizacion"/>
</jsp:include>

<div id="dialog-suscribirse" title="¿Desea suscribirse a este evento?">
	<p style="text-align: justify;">Al suscribirse a este evento acepta que la empresa organizadora le envie informaci&oacute;n sobre el evento a trav&eacute;s del correo electr&oacute;nico.</p>
</div>
<div class="breadcrumb">
	<img src="<spring:url value="/static/images/icons/16x16/house.png" />">
	<a href="<spring:url value="/" />">Inicio</a>
	<img src="<spring:url value="/static/images/breadcrumb.png" />">
	<img src="<spring:url value="/static/images/icons/16x16/zoom.png" />">
	<a href="<spring:url value="/busqueda" />">Buscador de Empresas</a>
	<img src="<spring:url value="/static/images/breadcrumb.png" />">
	${organizacion.name}
</div>
<script src="http://maps.google.com/maps?file=api&amp;v=2&amp;key=<fmt:message key="mapa.key"/>" type="text/javascript"></script>
<div id="tabs" >
  <ul>
		<li class="ui-tabs-nav-item"><a href="#general"><span><img src="<spring:url value="/static/images/icons/16x16/book.png" />" />${organizacion.name}</span></a></li>
    <li class="ui-tabs-nav-item"><a href="#asociaciones"><span><img src="<spring:url value="/static/images/icons/16x16/map.png" />" />Asociaciones</span></a></li>
    <li class="ui-tabs-nav-item"><a href="#sedes"><span><img src="<spring:url value="/static/images/icons/16x16/map.png" />" />Sedes</span></a></li>
    <li class="ui-tabs-nav-item"><a href="#capacidades"><span><img src="<spring:url value="/static/images/icons/16x16/wrench.png" />" />Servicios</span></a></li>
    <li class="ui-tabs-nav-item"><a href="#demandas"><span><img src="<spring:url value="/static/images/icons/16x16/wrench_orange.png" />" />Demanda</span></a></li>
    <li class="ui-tabs-nav-item"><a href="#eventos"><span><img src="<spring:url value="/static/images/icons/16x16/calendar.png" />" />Eventos</span></a></li>
  </ul>
<div id="general" class="ui-tabs-panel">
<table class="viewForm">
	<tr>
		<td class="key">
		</td>
		<td style="text-align:right">
<c:if test="${!empty organizacion.logoUrl}">
			<img id="logo" src="${organizacion.logoUrl}" />
</c:if>			
		</td>
	</tr>

	<tr>
		<td class="key">
			Raz&oacute;n Social
		</td>
		<td>${organizacion.name}</td>
	</tr>
	<tr>
		<td class="key">
			Direcci&oacute;n Web
		</td>
		<td>
<c:choose>
	<c:when test="${fn:startsWith(organizacion.web,'http') }">
		<a href="${organizacion.web}">${organizacion.web}</a>
	</c:when>
	<c:otherwise>
		<a href="http://${organizacion.web}">http://${organizacion.web}</a>
	</c:otherwise>
</c:choose>		
		</td>
	</tr>
	<tr>
		<td class="key">
			Clasificaci&oacute;n
		</td>
		<td>${organizacion.clasificacionOrganizacion}</td>
	</tr>
	<tr>
		<td class="key">
			Forma Juridica
		</td>
		<td>${organizacion.formaJuridica}</td>
	</tr>
	<tr>
		<td class="key">
			Descripci&oacute;n de los Servicios
		</td>
		<td style="font-size:1.1em;">${organizacion.descripcion}</td>
	</tr>	
	<tr>
		<td class="key">
			CIF
		</td>
		<td>${organizacion.cif}</td>
	</tr>
	<tr>
		<td class="key">
			A&ntilde;o de Constituci&oacute;n
		</td>
		<td>${organizacion.anoConstitucion}</td>
	</tr>
	<tr>
		<td class="key">
			N&uacute;mero total de Empleados
		</td>
		<td style="font-weight:normal">
			<b>
			</b>${organizacion.empleados} personas. (<b>${organizacion.mujeres}</b> mujeres y <b>${organizacion.hombres}</b> hombres)<br />
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
		<td>${organizacion.direccion}</td>
	</tr>
	<tr>
		<td class="key">
			C&oacute;digo Postal
		</td>
		<td>${organizacion.codigoPostal}</td>
	</tr>

	<tr>
		<td class="key">
			Localidad
		</td>
		<td>${organizacion.localidad}</td>
	</tr>

	<tr>
		<td class="key">
			Provincia
		</td>
		<td>${organizacion.provincia}</td>
	</tr>
	<tr>
		<td class="key">
			Tel&eacute;fono
		</td>
		<td>${organizacion.telefono}</td>
	</tr>
	<tr>
		<td class="key">
			Correo Electr&oacute;nico
		</td>
		<td>${organizacion.email}</td>
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
	    <c:choose>
	      <c:when test="${organizacion.certificacionCenatic}">
			<img src="<spring:url value="/static/images/icons/16x16/accept.png" />">&nbsp;&nbsp;	      
	      </c:when>
	      <c:otherwise>
			<img src="<spring:url value="/static/images/icons/16x16/cancel.png" />">	      
	      </c:otherwise>
	    </c:choose>
		</td>
	</tr>
	<tr>
		<td class="key">
			Certificaciones de Calidad
		</td>
		<td>
	    <c:choose>
	      <c:when test="${!empty organizacion.certificacionesCalidad}">
			<img src="<spring:url value="/static/images/icons/16x16/accept.png" />">&nbsp;&nbsp;${organizacion.certificacionesCalidad}	      
	      </c:when>
	      <c:otherwise>
			<img src="<spring:url value="/static/images/icons/16x16/cancel.png" />">	      
	      </c:otherwise>
	    </c:choose>
		</td>
	</tr>	
	<tr>
		<td class="key">
			Partners
		</td>
		<td>
	    <c:choose>
	      <c:when test="${!empty organizacion.partners}">
			<img src="<spring:url value="/static/images/icons/16x16/accept.png" />">&nbsp;&nbsp;${organizacion.partners}	      
	      </c:when>
	      <c:otherwise>
			<img src="<spring:url value="/static/images/icons/16x16/cancel.png" />">	      
	      </c:otherwise>
	    </c:choose>
		</td>
	</tr>
	
	<tr>
		<td class="key">
			Realiza actividades de I+D
		</td>
		<td>		
	    <c:choose>
	      <c:when test="${organizacion.actividadesImasD}">
			<img src="<spring:url value="/static/images/icons/16x16/accept.png" />">	      
	      </c:when>
	      <c:otherwise>
			<img src="<spring:url value="/static/images/icons/16x16/cancel.png" />">	      
	      </c:otherwise>
	    </c:choose>
		</td>
	</tr>
	<tr>
		<td class="key">
			Participaci&oacute;n en programas de I+D
		</td>
		<td>
	    <c:choose>
	      <c:when test="${!empty organizacion.participacionImasD}">
			<img src="<spring:url value="/static/images/icons/16x16/accept.png" />">&nbsp;&nbsp;${organizacion.participacionImasD}	      
	      </c:when>
	      <c:otherwise>
			<img src="<spring:url value="/static/images/icons/16x16/cancel.png" />">	      
	      </c:otherwise>
	    </c:choose>
		</td>
	</tr>
	
	<tr>
		<td class="key">
			Relaciones con la Comunidad
		</td>
		<td>
	    <c:choose>
	      <c:when test="${!empty organizacion.relacionesComunidad}">
			<img src="<spring:url value="/static/images/icons/16x16/accept.png" />">&nbsp;&nbsp;${organizacion.relacionesComunidad}	      
	      </c:when>
	      <c:otherwise>
			<img src="<spring:url value="/static/images/icons/16x16/cancel.png" />">	      
	      </c:otherwise>
	    </c:choose>
		</td>
	</tr>
	<tr>
		<td class="key">
			Pertenencia a Grupo Empresarial
		</td>
		<td>
	    <c:choose>
	      <c:when test="${!empty organizacion.grupoEmpresarial}">
			<img src="<spring:url value="/static/images/icons/16x16/accept.png" />">&nbsp;&nbsp;${organizacion.grupoEmpresarial}	      
	      </c:when>
	      <c:otherwise>
			<img src="<spring:url value="/static/images/icons/16x16/cancel.png" />">	      
	      </c:otherwise>
	    </c:choose>
		</td>
	</tr>
</table>
</div>
<div id="sedes" class="ui-tabs-panel">
<table class="viewForm">
<c:forEach var="sede" items="${organizacion.sedes}">
	<tr>
		<td >
			<span style="font-size:14px;">${sede.provincia}</span><br/>
			<span style="font-weight:normal">
				<img src="<spring:url value="/static/images/icons/16x16/house.png" />" />&nbsp;${sede.direccion}, ${sede.localidad}<br />
				<table style="width:100%;"><tr><td style="font-size:12px;font-weight:normal;width:50%;">
				<br /><img src="<spring:url value="/static/images/icons/16x16/phone.png" />" /> &nbsp;
	    <c:choose>
	      <c:when test="${!empty sede.telefonoContacto}">
			<a href="dialto:${sede.telefonoContacto}"\>${sede.telefonoContacto}</a>	      
	      </c:when>
	      <c:otherwise>
			No Disponible	      
	      </c:otherwise>
	    </c:choose>
				<br /><img src="<spring:url value="/static/images/icons/16x16/user_suit.png" />" /> &nbsp;
	    <c:choose>
	      <c:when test="${!empty sede.personaContacto}">
			${sede.personaContacto}	      
	      </c:when>
	      <c:otherwise>
			No Disponible	      
	      </c:otherwise>
	    </c:choose>&nbsp;
				<br /><img src="<spring:url value="/static/images/icons/16x16/email.png" />" /> &nbsp;
	    <c:choose>
	      <c:when test="${!empty sede.mailContacto}">
			<a href="mailto:${sede.mailContacto}"\>${sede.mailContacto}</a>	      
	      </c:when>
	      <c:otherwise>
			No Disponible	      
	      </c:otherwise>
	    </c:choose>
				</td><td style="font-size:12px;font-weight:normal">
				<img src="<spring:url value="/static/images/icons/16x16/user_female.png" />" />&nbsp;${sede.mujeres}&nbsp;Mujeres <br />
				<img src="<spring:url value="/static/images/icons/16x16/user.png" />" />&nbsp;${sede.hombres}&nbsp;Hombres
				</td></tr>
				</table>				
			</span>
		</td>
		<td style="width:400px;padding:0px;">
			<div id="map_${sede.id}" style="width: 400px; height: 180px"></div>
			<script type="text/javascript">
			
			$(window).bind("load",function(){

				if (GBrowserIsCompatible()) {
					var map = new GMap2(document.getElementById("map_${sede.id}"));
					map.setCenter(new GLatLng(${sede.latitud},${sede.longitud}), 14);
					var point = new GPoint(${sede.latitud},${sede.longitud});
					var marker = new GMarker(point);
					map.addOverlay(marker);
				}
			});
			</script>
		</td>
	</tr>
</c:forEach>
</table>
</div>

<div id="asociaciones" class="ui-tabs-panel">
<table>
<c:forEach var="asociacion" items="${organizacion.organizacionAsociacions}">
		<tr>
			<td>
				<a href="${asociacion.asociacion.url}" target="new" >
					<img src="${asociacion.asociacion.icon}" />
				</a>
			</td>			
		</tr>
</c:forEach>
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
		</tbody>
<c:forEach var="oferta" items="${organizacion.ofertas}">
		<thead>
			<td colspan="6" style="background-color:#dddddd">${oferta.capacidad}</td>
		</thead>
		<tbody>	
			<tr>
				<td class="capacidad">${oferta.sector}
				</td>
				<td class="descripcion">
<c:if test="${!empty oferta.capacidad.descripcion}">				
					${oferta.capacidad.descripcion}
</c:if>
				<br/>
<c:if test="${!empty oferta.descripcion}">
					${oferta.descripcion}
</c:if>				
				</td>
				<td class="personas">
	    <c:choose>
	      <c:when test="${oferta.recursos lt 100}">
	      	${oferta.recursos}
	      </c:when>
	      <c:otherwise>&gt;100</c:otherwise>
	    </c:choose>&nbsp;personas.
				</td>
				<td class="porcentaje">
					${oferta.porcentajeFacturacion}&nbsp;%
				</td>
				<td class="puntuacion">
					${oferta.puntuacion}
				</td>
			</tr>
		</tbody>
</c:forEach>	
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
<jsp:useBean id="now" class="java.util.Date" />				
<c:forEach var="evento" items="${organizacion.eventos}">
	<c:if test="${now ge evento.evento.fecha}">
	<tr>
		<td class="evento">
			<div style="float:right;">
			<b>Asistentes: </b>0
			</div>
			<p style="font-size:1.2em;color:#99008b"><b>${evento.evento.titulo}</b></p>
			<p><b>Localización: </b>${evento.evento.localizacion}</p>
			<p><b>Fecha: </b><fmt:formatDate value="${evento.evento.fecha}" pattern="dd/MM/yyyy"/></p>
			<p>${evento.evento.descripcion}</p>
		</td>
	</tr>	
	</c:if>
</c:forEach>	
	</tbody>
	</table>
	<table style="margin-bottom:16px;" class="grid eventosGrid" id="eventos_table">
		<thead>
			<td>
				Eventos
			</td>
			<td style="width:30px;">
			</td>
		</thead>
		<tbody id="eventosBody">
<c:forEach var="evento" items="${organizacion.eventos}">
	<c:if test="${now lt evento.evento.fecha}">
	<tr>
		<td class="evento">
			<div style="float:right;">
			<b>Asistentes: </b>0
			</div>
			<p style="font-size:1.2em;color:#99008b"><b>${evento.evento.titulo}</b></p>
			<p><b>Localización: </b>${evento.evento.localizacion}</p>
			<p><b>Fecha: </b><fmt:formatDate value="${evento.evento.fecha}" pattern="dd/MM/yyyy"/></p>
			<p>${evento.evento.descripcion}</p>
		</td>
	</tr>
	</c:if>	
</c:forEach>	
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
		</tbody>
<c:forEach var="demanda" items="${organizacion.demandas}">
		<thead>
			<td colspan="6" style="background-color:#dddddd">${demanda.capacidad}</td>
		</thead>
		<tbody>
			<tr>
				<td class="capacidad">${demanda.sector}</td>
				<td>
					${demanda.capacidad.descripcion}
					<br />
					${demanda.descripcion}
				</td>
			</tr>
		</tbody>
</c:forEach>		
	</table>
</div>
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
<c:forEach var="sede" items="${organizacion.sedes}">		
		var map = new GMap2(document.getElementById("map_${sede.id}"));
		map.setCenter(new GLatLng(${sede.latitud}, ${sede.longitud}), 14);
		var point = new GPoint(${sede.latitud}, ${sede.longitud});
		var marker = new GMarker(point);
		map.addOverlay(marker);
</c:forEach>
	}
$('#tabs').tabs({ fx: { height: 'toggle', opacity: 'toggle', duration: 'fast' } });
});
</script>	

<%@ include file="/WEB-INF/jsp/footer.jsp" %>