<%@ include file="/WEB-INF/jsp/includes.jsp" %>
<jsp:include page="/WEB-INF/jsp/header.jsp" >
	<jsp:param name="pageTitle" value="Editar Organización"/>
</jsp:include>

<div class="breadcrumb">
	<img src="<spring:url value="/static/images/icons/16x16/house.png" />">
	<a href="<spring:url value="/" />">Inicio</a>
	<img src="<spring:url value="/static/images/breadcrumb.png" />">
	<img src="<spring:url value="/static/images/icons/16x16/wrench.png" />">
	<a href="<spring:url value="/busqueda" />">Buscador de Empresas</a>
	<img src="<spring:url value="/static/images/breadcrumb.png" />">
	Ficha de Empresa ${organizacion.name}
</div>
<script src="http://maps.google.com/maps?file=api&amp;v=2&amp;key=<fmt:message key="mapa.key"/>" type="text/javascript"></script>
	
<script type="text/javascript">
$(document).ready(function(){
  $('.certificacionesCalidad:last').change(addCertificacionesInput);
});
function addCertificacionesInput() {
	if($('.certificacionesCalidad:last').val()!='') $('#certificacionesCalidadTable').append("<tr><td></td><td><input type=\"text\" class=\"certificacionesCalidad\" size=\"34\" name=\"certificacionesCalidad[]\"/></td></tr>");
	$('.certificacionesCalidad:last').change(addCertificacionesInput);
	$('.certificacionesCalidad:last').focus();
}

function clearCertificaciones() {
	$('#certificacionesCalidadTable').html ("<tr><td> Por favor indique cuales:</td></td><td><input type=\"text\" class=\"certificacionesCalidad\" size=\"34\" name=\"certificacionesCalidad[]\"/></td></tr>");
	$('.certificacionesCalidad:last').change(addCertificacionesInput);
}
</script>
<div id="tabs">
  <ul>
		<li class="ui-tabs-nav-item"><a href="#general"><span><img src="<spring:url value="/static/images/icons/16x16/book.png" />" />${organizacion.name}</span></a></li>
    <li class="ui-tabs-nav-item"><a href="#asociaciones"><span><img src="<spring:url value="/static/images/icons/16x16/map.png" />" />Asociaciones</span></a></li>
    <li class="ui-tabs-nav-item"><a href="#sedes"><span><img src="<spring:url value="/static/images/icons/16x16/map.png" />" />Sedes</span></a></li>
    <li class="ui-tabs-nav-item"><a href="#capacidades"><span><img src="<spring:url value="/static/images/icons/16x16/wrench.png" />" />Servicios</span></a></li>
    <li class="ui-tabs-nav-item"><a href="#demandas"><span><img src="<spring:url value="/static/images/icons/16x16/wrench_orange.png" />" />Demanda</span></a></li>
    <li class="ui-tabs-nav-item"><a href="#eventos"><span><img src="<spring:url value="/static/images/icons/16x16/calendar.png" />" />Eventos</span></a></li>
	</ul>
<div id="general" class="ui-tabs-panel">

<form:form modelAttribute="organizacion" method="put">

<table class="viewForm" style="border-spacing:0px">
	<c:if test="${completado lt 100}">
	<tr>
		<td style="border-left:solid 4px #a21170;border-top:solid 4px #a21170;border-bottom:solid 4px #a21170">
			<table>
				<tr>
					<td>
						<table style="width:150px;border:inset 1px black">
							<tr>
								<td style="width:${completado}%;border:outset 1px;background:#af228a;overflow:hidden"></td>
								<td style="width:${100 - completado}%;overflow:hidden;">&nbsp;</td>
							</tr>
						</table>
					</td>
					<td>
						<h1>${completado}%</h1>
					</td>
				</tr>
			</table>
		</td>
		<td style="border-right:solid 4px #a21170;border-top:solid 4px #a21170;border-bottom:solid 4px #a21170;">
			<h2 style="margin:0px;">Su perfil no est&aacute; completo!</h2>
			<span style="font-weight:normal">Su organizaci&oacute;n no se mostrar&aacute; correctamente en SLDirect dado que no ha introducido todos los datos requeridos:</span><br />
			${completeness_reason}			
		</td>
	</tr>
	</c:if>
	<tr>
		<td class="key" colspan="2">&nbsp;</td>
	</tr>
	<tr>
		<td class="key">URL a Imagen de Logotipo: </td>
		<td>
			<form:input path="logoUrl" size="200" maxlength="200" style="width:100%"/>
<c:if test="${!empty organizacion.logoUrl}">
			<br /><img id="logo" src="${organizacion.logoUrl}" />
</c:if>			
		</td>
	</tr>
	<tr>
		<td class="key">
			Raz&oacute;n Social
		</td>
		<td>
			<form:input path="name" size="30" maxlength="30"/>
		</td>
	</tr>
	<tr>
		<td class="key">
			Direcci&oacute;n Web
		</td>
		<td>
			<form:input path="web" size="30" maxlength="30"/>
		</td>
	</tr>
	<tr>
		<td class="key">
			Clasificaci&oacute;n
		</td>
		<td>
			<form:select path="clasificacionOrganizacion" items="${clasificacionOrganizaciones}"/>		
		</td>
	</tr>
	<tr>
		<td class="key">
			Forma Jur&iacute;dica
		</td>
		<td>
			<form:select path="formaJuridica" items="${formaJuridicas}"/>		
		</td>
	</tr>
	<tr>
		<td class="key">
			Descripci&oacute;n de los Servicios
		</td>
		<td style="font-size:1.1em;">
			<form:textarea  path="descripcion" rows="4"/>
			<div id="charlimitinfo"></div>
		</td>
	</tr>	
	<tr>
		<td class="key">
			CIF
		</td>
		<td>
			<form:input path="cif" size="9" maxlength="9"/>
		</td>
	</tr>
	<tr>
		<td class="key">
			A&ntilde;o de Constituci&oacute;n
		</td>
		<td>
			<form:input path="anoConstitucion" size="4" maxlength="4"/>
		</td>
	</tr>
	<tr>
		<td class="key">
			N&uacute;mero Total de Empleados
		</td>
		<td style="font-weight:normal">
			<b>
			${organizacion.empleados} </b> personas. (<b>${organizacion.mujeres}</b> mujeres y <b>${organizacion.hombres}</b> hombres)<br />
			<span style="font-size:0.8em"> Dato Calculado a partir de la informaci&oacute;n introducida en el apartado <i>sedes</i></span>
		</td>
	</tr>
	<tr>
		<td colspan="2" class="separator">
			Domicilio Social
		</td>
	</tr>
	<tr>
		<td colspan="2">
			Introduzca en este apartado los datos del domicilio social de su organizaci&oacute;n. Para que su organizaci&oacute;n aparezca en los mapas y en la localizaci&oacute;n por provincias, 
			debe rellenar al menos una sede en el apartado de sedes.
		</td>
	</tr>
	<tr>
		<td class="key">
			Direcci&oacute;n
		</td>
		<td>
			<form:input path="direccion" size="40" maxlength="40"/>
		</td>
	</tr>
	<tr>
		<td class="key">
			C&oacute;digo Postal
		</td>
		<td>
			<form:input path="codigoPostal" size="5" maxlength="5"/>
		</td>
	</tr>
	<tr>
		<td class="key">
			Localidad
		</td>
		<td>
			<form:input path="localidad" size="15" maxlength="15"/>
		</td>
	</tr>
	<tr>
		<td class="key">
			Provincia
		</td>
		<td>
			<form:select path="provincia" items="${provincias}"/>
		</td>
	</tr>
	<tr>
		<td class="key">
			Tel&eacute;fono
		</td>
		<td>
			<form:input path="telefono" size="15" maxlength="15"/>
		</td>
	</tr>
	<tr>
		<td class="key">
			Correo Electr&oacute;nico
		</td>
		<td>
			<form:input path="email" size="40" maxlength="40"/>
		</td>
	</tr>	
	<tr>
		<td colspan="2" class="separator">
			Carrusel de Noricias
		</td>
	</tr>
	<tr>
		<td colspan="2">
			Por favor, introduzca la informaci&oacute;n que desea que se muestre en el carrusel de noticias de la p&aacute;gina principal.
		</td>
	</tr>
	<tr>
		<td class="key">
			T&iacute;tulo
		</td>
		<td>
			<form:input path="newsTitle" size="40" maxlength="40"/>
		</td>
	</tr>
	<tr>
		<td class="key">
			Noticia
		</td>
		<td style="font-size:1.1em;">
			<form:textarea path="newsBody" />
		</td>
	</tr>
	<tr>
		<td colspan="2" class="separator">
			Informaci&oacute;n Adicional
		</td>
	</tr>
	<tr>
		<td colspan="2">
			Por favor, marque las casillas correspondientes en el caso de que su empresa posea alg&uacute;n tipo de certificaci&oacute;n, participe en programas de I+D, trabaje junto a partners en proyectos, o tenga relaciones con la Comunidad y explique cuando sea necesario.			
		</td>
	</tr>
	<tr>
		<td class="key">
			Certificacion Cenatic
		</td>
		<td>
			<form:checkbox path="certificacionCenatic"/>
		</td>
	</tr>
	<tr>
		<td class="key" style="vertical-align:top;padding-top:11px;">
			Certificaciones de Calidad
		</td>
		<td>
			<form:checkbox path="certificacionCalidad" onchange=" if (this.checked) { $('#certificacionesCalidadInput').css('display','inline');} else { $('#certificacionesCalidadInput').css('display','none'); clearCertificaciones(); }" />
<c:choose>
	<c:when test="${organizacion.certificacionCalidad}">
			<div id="certificacionesCalidadInput" style="display: inline;margin-left:12px;">
			Por favor indique cuales:&nbsp;&nbsp;
			<form:input size="34" path="certificacionesCalidad" />
			</div>
	</c:when>
	<c:otherwise>
			<div id="certificacionesCalidadInput" style="display: none;margin-left:12px;">			
			Por favor indique cuales:&nbsp;&nbsp;
			<form:input size="34" path="certificacionesCalidad" />
			</div>
	</c:otherwise>	
</c:choose>
		</td>
	</tr>
	<tr>
		<td class="key">
			Partners
		</td>
		<td>
			<form:checkbox path="tienePartners" onchange=" if (this.checked) { $('#partnersInput').css('display','inline');} else { $('#partnersInput').css('display','none'); }"/>
<c:choose>
	<c:when test="${organizacion.tienePartners}">
			<div id="partnersInput" style="display:inline;margin-left:12px;"> 
			Por favor indique cuales:&nbsp;&nbsp;
			<form:input size="34" path="partners" />
			</div>
	</c:when>
	<c:otherwise>
			<div id="partnersInput" style="display:none;margin-left:12px;"> 
			Por favor indique cuales:&nbsp;&nbsp;
			<form:input size="34" path="partners" />
			</div>
	</c:otherwise>	
</c:choose>
		</td>
	</tr>
	<tr>
		<td class="key">
			Realiza Actividades I+D
		</td>
		<td>
			<form:checkbox path="actividadesImasD" />
		</td>
	</tr>
	<tr>
		<td class="key">
			Participaci&oacute;n en programas de I+D
		</td>
		<td>
			<form:checkbox path="participaImasD" onchange=" if (this.checked) { $('#participacionImasDInput').css('display','inline');} else { $('#participacionImasDInput').css('display','none'); }"/>
<c:choose>
	<c:when test="${organizacion.participaImasD}">
			<div id="participacionImasDInput" style="display:inline;margin-left:12px;"> 
			Por favor indique en cuales:&nbsp;&nbsp;
			<form:input size="24" path="participacionImasD"/>
			</div>
	</c:when>
	<c:otherwise>
			<div id="participacionImasDInput" style="display:none;margin-left:12px;"> 
			Por favor indique en cuales:&nbsp;&nbsp;
			<form:input size="24" path="participacionImasD" />
			</div>	
	</c:otherwise>	
</c:choose>
		</td>
	</tr>	
	<tr>
		<td class="key">
			Relaciones con la Comunidad
		</td>
		<td>
			<form:checkbox path="tieneRelacionesComunidad" onchange=" if (this.checked) { $('#relacionesComunidadInput').css('display','inline');} else { $('#relacionesComunidadInput').css('display','none'); } " />
<c:choose>
	<c:when test="${organizacion.tieneRelacionesComunidad}">
			<div id="relacionesComunidadInput" style="display:inline;margin-left:12px;"> 
			Por favor indique con qu&eacute; comunidades:&nbsp;&nbsp;
			<form:input size="24" path="relacionesComunidad" />
			</div>	
	</c:when>
	<c:otherwise>
			<div id="relacionesComunidadInput" style="display:none;margin-left:12px;"> 
			Por favor indique con qu&eacute; comunidades:&nbsp;&nbsp;
			<form:input size="24" path="relacionesComunidad" />
			</div>	
	</c:otherwise>	
</c:choose>
		</td>
	</tr>
	<tr>
		<td class="key">
			Pertenencia a grupo Empresarial
		</td>
		<td>
			<form:checkbox path="perteneceGrupoEmpresarial" onchange=" if (this.checked) { $('#grupoEmpresarialInput').css('display','inline');} else { $('#grupoEmpresarialInput').css('display','none'); }" />
<c:choose>
	<c:when test="${organizacion.perteneceGrupoEmpresarial}">
			<div id="grupoEmpresarialInput" style="display: inline;margin-left:12px;"> 
			Por favor indique el nombre del Grupo Empresarial: 
			<form:input size="24" path="grupoEmpresarial" />
			</div>			
	</c:when>
	<c:otherwise>
			<div id="grupoEmpresarialInput" style="display: none;margin-left:12px;"> 
			Por favor indique el nombre del Grupo Empresarial: 
			<form:input size="24" path="grupoEmpresarial" />
			</div>			
	</c:otherwise>	
</c:choose>	
		</td>
	</tr>
	<tr>
		<td colspan="2" class="separator">
			Identidad de Acceso
		</td>
	</tr>
	<tr>
		<td class="key">
			Usuario
		</td>
		<td>
			<form:input path="username" readonly="true" size="40" maxlength="40"/>
		</td>
	</tr>
	<tr>
		<td class="key">
			Contrase&ntilde;a
		</td>
		<td>
			<form:password path="password" size="8" onfocus="this.value=''" value="*****"/>
		</td>
	</tr>	
</table>
</form:form>
<button onclick="document.forms[1].submit();">
	<img src="<spring:url value="/static/images/icons/16x16/disk.png" />" />Guardar cambios a ficha de Empresa</button>
</div>
<div id="asociaciones" class="ui-tabs-panel">
<table>
	<h2>Asociaciones a las que ${organizacion} pertenece </h2>
<c:forEach var="orgAsoc" items="${organizacion.organizacionAsociacions}">
		<tr>
			<td>
				<a href="${orgAsoc.asociacion.url}">
					<img src="${orgAsoc.asociacion.icon}"/>
				</a>
			</td>			
			<td>
	        <spring:url value="/orgAsoc/{orgAsocId}/del" var="delUrl">
	        	<spring:param name="orgAsocId" value="${orgAsoc.id}" />
	        </spring:url>
			
				<button onclick="document.location='${fn:escapeXml(delUrl)}';return false" >
					<img src="<spring:url value="/static/images/icons/16x16/delete.png" />">Eliminar pertenencia a ${orgAsoc.asociacion}
				</button>
		</tr>
</c:forEach>	
</table>
	<h2>Insertar asociaci&oacute;n a la lista</h2>
<spring:url value="/orgs/{organizacionId}/newAsociacion" var="addUrl" >
	<spring:param name="organizacionId" value="${organizacion.id}" />
</spring:url>
	<form method="post" action="${fn:escapeXml(addUrl)}">
	<table>
		<tr>
			<td>
				<select name="asociacionId">
<c:forEach var="asociacion" items="${asociaciones}">
					<option value="${asociacion.id}">${asociacion}</option>
</c:forEach>				
				</select>
			</td>
			<td>
				<input type="submit" value="Insertar asociaci&oacute;n">
			</td>
		</tr>
	</table>
	</form>
</div>
<div id="sedes" class="ui-tabs-panel">
<table class="viewForm">
<c:forEach var="sede" items="${organizacion.sedes}">
	<tr>
		<td >
			<span style="font-size:14px;">${sede.provincia}</span><br/>
			<span style="font-weight:normal">
				<img src="<spring:url value="/static/images/icons/16x16/house.png" />" />&nbsp;${sede.direccion}, ${sede.localidad}<br />
				<table style="width:100%;">
					<tr>
						<td style="font-size:12px;font-weight:normal;width:50%;">
							<br />
							<img src="<spring:url value="/static/images/icons/16x16/phone.png" />" /> &nbsp;
	    <c:choose>
	      <c:when test="${!empty sede.telefonoContacto}">
			<a href="dialto:${sede.telefonoContacto}"\>${sede.telefonoContacto}</a>	      
	      </c:when>
	      <c:otherwise>
			No Disponible	      
	      </c:otherwise>
	    </c:choose>
							<br />
							<img src="<spring:url value="/static/images/icons/16x16/user_suit.png" />" /> 
							&nbsp;
	    <c:choose>
	      <c:when test="${!empty sede.personaContacto}">
			${sede.personaContacto}	      
	      </c:when>
	      <c:otherwise>
			No Disponible	      
	      </c:otherwise>
	    </c:choose>
	    				&nbsp;<br />
							<img src="<spring:url value="/static/images/icons/16x16/email.png" />" /> &nbsp;
	    <c:choose>
	      <c:when test="${!empty sede.mailContacto}">
			<a href="mailto:${sede.mailContacto}"\>${sede.mailContacto}</a>	      
	      </c:when>
	      <c:otherwise>
			No Disponible	      
	      </c:otherwise>
	    </c:choose>
						</td>
						<td style="font-size:12px;font-weight:normal">
							<img src="<spring:url value="/static/images/icons/16x16/user_female.png" />" />&nbsp;${sede.mujeres}&nbsp;Mujeres <br />
							<img src="<spring:url value="/static/images/icons/16x16/user.png" />" />&nbsp;${sede.hombres}&nbsp;Hombres						</td>
					</tr>
				</table>
			</span><br /><br />
			
	<spring:url value="/sedes/{sedeId}/edit" var="editUrl">
 		<spring:param name="sedeId" value="${sede.id}" />
 	</spring:url>
	<spring:url value="/sedes/{sedeId}/del" var="delUrl">
 		<spring:param name="sedeId" value="${sede.id}" />
 	</spring:url>
			
			<button onclick="document.location='${fn:escapeXml(delUrl)}';return false;">
				<img src="<spring:url value="/static/images/icons/16x16/delete.png" />" /> 
				Eliminar Sede 
			</button>
			<button onclick="document.location='${fn:escapeXml(editUrl)}';return false;">
				<img src="<spring:url value="/static/images/icons/16x16/disk.png" />">
				Modificar Sede 
			</button>
		</td>
		<td style="width:400px;padding:0px;">
			<div id="map_${sede.id}" style="width: 400px; height: 180px"></div>
			<script type="text/javascript">
				function z_${sede.id}(){
				if (GBrowserIsCompatible()) {
					var map = new GMap2(document.getElementById("map_${sede.id}"));
					map.setCenter(new GLatLng(${sede.latitud},${sede.longitud}), 14);
					var point = new GPoint(${sede.latitud}, ${sede.longitud});
					var marker = new GMarker(point);
					map.addOverlay(marker);
				}}
				setTimeout('z_${sede.id}()',7000);
			</script>
		</td>
	</tr>
</c:forEach>

<tr>
	<td colspan="2" class="separator">A&ntilde;adir nueva Sede </td>
</tr>
<tr>
	<td colspan="2">
		Usted puede a&ntilde;adir sedes al perfil de la empresa mediante este formulario. Por favor, aseg&uacute;rese de que selecciona su ubicaci&oacute;n geogr&aacute;fica en el mapa haciendo click en el lugar donde se encuentra ubicada la sede a a&ntilde;adir.
	</td>
</tr>
<tr>
	<td>
<spring:url value="/orgs/{organizacionId}/sedes/new" var="addUrl" >
	<spring:param name="organizacionId" value="${organizacion.id}" />
</spring:url>
		<form id="sedeAdd" method="post" action="${fn:escapeXml(addUrl)}">
		<img src="<spring:url value="/static/images/icons/16x16/map.png" />">
		
		<select style="font-size:12px;" name="provinciaId">
			<option value="" selected="selected">Provincia</option>
<c:forEach var="provincia" items="${provincias}">
			<option value="${provincia.id}">${provincia}</option>				
</c:forEach>		
		</select>
		<span style="font-weight:normal"><br />
			<img src="<spring:url value="/static/images/icons/16x16/house.png" />" />&nbsp;
			<input style="font-size:12px;" type="text" name="direccion" value="[Direccion]" onclick="if (this.value=='[Direccion]') this.value=''" /> / <input type="text" style="font-size:12px;"  name="localidad" value="[Localidad]" onclick="if (this.value=='[Localidad]') this.value=''" />
			<br /><br />
			<img src="<spring:url value="/static/images/icons/16x16/phone.png" />" />&nbsp;
			<input type="text" style="font-size:12px;"  name="telefonoContacto" value="[Tel&eacute;fono Contacto]" onclick="if (this.value=='[Tel&eacute;fono Contacto]') this.value=''" />
			<br />
			<img src="<spring:url value="/static/images/icons/16x16/user_suit.png" />" />&nbsp;
			<input type="text" style="font-size:12px;"  name="personaContacto" value="[Persona Contacto]" onclick="if (this.value=='[Persona Contacto]') this.value=''" />
			<br />
			<img src="<spring:url value="/static/images/icons/16x16/email.png" />" />&nbsp;
			<input type="text" style="font-size:12px;"  name="mailContacto" value="[e-Mail Contacto]" onclick="if (this.value=='[e-Mail Contacto]') this.value=''" />
			<br />
			<img src="<spring:url value="/static/images/icons/16x16/user.png" />" />&nbsp;
			<input type="text" style="font-size:12px;"  size="3" name="hombres" value="[H]" onclick="if (this.value=='[H]') this.value=''" />
			<br />
			<img src="<spring:url value="/static/images/icons/16x16/user_female.png" />" />&nbsp;
			<input type="text" style="font-size:12px;" size="3"  name="mujeres" value="[M]" onclick="if (this.value=='[M]') this.value=''" />
			<br />
			<img src="<spring:url value="/static/images/icons/16x16/world.png" />" />&nbsp; 
			<input type="text" style="font-size:12px" name="latitud" id="latitud" size="6">	N 
			<input type="text" style="font-size:12px" name="longitud" id="longitud" size="6">	O
		</span><br /><br />
		<button onclick="document.getElementById('sedeAdd').submit()">
			<img src="<spring:url value="/static/images/icons/16x16/add.png" />"> Insertar Sede 
		</button>
		</form>
	</td>
	<td style="width:400px;padding:0px;text-align:center">
		<div id="map_add" style="width: 400px; height: 180px"></div>
		<span style="font-size:9px;text-align:center">Haga click simple para marcar la localizaci&oacute;n de la sede en el mapa.<br />Haga click doble para hacer zoom en el mapa.</span>
		<script type="text/javascript">
			function z_createmap(){
			if (GBrowserIsCompatible()) {
				var map = new GMap2(document.getElementById("map_add"));
				var create_marker;
				map.setCenter(new GLatLng(40.109447, -3.692870), 4);
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
</div>

<div id="asistentes-modal" title="Asistentes al Evento">
	<div id="asistentes-div"></div>
</div>
<div id="eventos" class="ui-tabs-panel">
	<p>
		En este apartado va a describir los eventos que organiza su organizaci&oacute;n. Seleccione la fecha en la que se dar&aacute; el evento, a continuaci&oacute;n escriba el t&iacute;tulo del evento y una breve descripci&oacute;n del mismo, as&iacute; como la localizaci&oacute;n del mismo.		
	</p>
	<div style="text-align:right;font-size: 1.2em"><input id="eventosPasadosCheck" type="checkbox" onClick="toogleEventosPasados();"> Mostrar eventos pasados</input></div>
	<table style="margin-bottom:16px;" id="eventosPasados" class="grid eventosGrid">
		<thead>
			<td>
				Eventos Pasados
			</td>
			<tbody>
<jsp:useBean id="now" class="java.util.Date"  />
<c:forEach var="evento" items="${organizacion.eventos}">
	<c:if test="${now ge evento.evento.fecha}">
	<tr>
		<td class="evento">
			<div style="float:right;">
			<a onclick="" href="#">
			<b>Asistentes: </b>0</a>
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
		<tbody id="eventosBody">
<c:forEach var="evento" items="${organizacion.eventos}">
	<c:if test="${now lt evento.evento.fecha}">
		<tr>
			<td class="evento">
				<div style="float:right;">
					<a onClick='showAssistants()' href="#">
						<b>Asistentes: </b>0
					</a>
				</div>
				<p style="font-size:1.2em;color:#99008b">
					<b> ${evento.evento.titulo}</b>
				</p>
				<p><b>Localizaci&oacute;n: </b>${evento.evento.localizacion}</p>
				<p><b>Fecha: </b><fmt:formatDate value="${evento.evento.fecha}" pattern="dd/MM/yyyy"/></p>
				<p>${evento.evento.descripcion}</p>
			</td>
			<td>
				<button onclick="evento_delete(this, ${evento.id})">
					<img src="<spring:url value="/static/images/icons/16x16/delete.png"/>">
				</button>
			</td>
		</tr>				
	</c:if>
</c:forEach>
	</tbody>
	</table>
	<table class="grid eventosGrid" id="eventossuscritos">
		<thead>
			<td>
				Eventos a los que est&aacute; suscrito
			</td>
			<td style="width:30px;">
			</td>
		<tbody id="eventosBody">
		</tbody>
	</table>
	<table class="grid eventosAddGrid">
		<thead>
			<td>A&ntilde;adir Evento Nuevo</td></thead>
		<tr>
			<td>
				<div style="float:right;">
					<button  onclick="evento_add();return false;">
						<img src="<spring:url value="/static/images/icons/16x16/add.png"/>">
					</button>
				</div>
				<span style="font-size:9px;font-weight:bold">T&iacute;tulo</span><br/>
				<input type="text" id="eventTitle" size="120" /><br/><br/>
				<div style="display:inline-block"><span style="font-size:9px;font-weight:bold">Localizaci&oacute;n</span><br/>
				<input type="text" id="eventLocalization" size="60" /></div>
				<div style="display:inline-block"><span style="font-size:9px;font-weight:bold">Fecha</span><br/>
				<input type="text" id="eventDate" /></div><br/><br/>
				<span style="font-size:9px;font-weight:bold">Descripci&oacute;n</span><br/>
				<textarea id="eventDescription" style="width:99%" rows="8"></textarea>
				
			</td>
		</tr>
	</table>
	<div id="dialog-modal" style="font-size:10px;" title="Error">
		
	</div>
	<script>
		function showAssistants (assistants,titulo) {
			email = "";
			$("#asistentes-div").html("");
			$.each(assistants, function (i, elem) {
				email += "&bcc=" + elem.email;
				$("#asistentes-div").append("<p><a target=\"blank\" href=\"organizaciones/" + elem.id +  "\">" + elem.name + "</a></p>");
			});
			$("#asistentes-div").append("<div style=\"text-align:right\"><button title=\"Enviar correo electr&oacute;nico a los asistentes\" onclick=\"location='mailto:?subject=InformaciÃ³n del evento: " + titulo + email + "'\" ><img src=\"media/icons/16x16/email.png\"></button></div>");
			$("#asistentes-modal").dialog('open');
		}
	    function toogleEventosPasados() {
		if($("#eventosPasadosCheck").attr("checked") == true) {
			$("#eventosPasados").show('blind'),null,500;
		} else {
			$("#eventosPasados").hide('blind'),null,500;
		}
		}
		function dce (tagname){ return document.createElement(tagname);}
		var deleting_el="";
		
		function organizacion_evento_delete(el,id){
			deleting_el = 1;
			el.parentNode.parentNode.parentNode.removeChild(el.parentNode.parentNode);
			jQuery.getJSON("eventosOrg/"+id+"/del",organizacionevento_delReady);
		}
		
		function organizacionevento_delReady(data,scope){
			deleting_el = "";
		}
		
		function evento_delete(el,id){
			deleting_el = 1;
			el.parentNode.parentNode.parentNode.removeChild(el.parentNode.parentNode);
			jQuery.getJSON("eventos/"+id+"/del",evento_delReady);
		}
	
		function evento_delReady(data,scope){
			deleting_el = "";
		}
	
		function evento_add(){
			
			var titulo = ($("#eventTitle").attr("value"));
			var localizacion = ($("#eventLocalization").attr("value"));
			var fecha = ($("#eventDate").attr("value"));
			var descripcion = ($("#eventDescription").attr("value"));
			var errors = "";
			if (titulo=="") {errors += "<p>El campo <span style=\"color:red;\">T&iacute;tulo</span> es obligatorio</p>";}
			if (localizacion=="") {errors += "<p>El campo  <span style=\"color:red;\">Localizaci&oacute;n</span> es obligatorio</p>";}
			if (fecha=="") {errors += "<p>El campo  <span style=\"color:red;\">Fecha</span> es obligatorio</p>";}
			if (descripcion=="") {errors += "<p>El campo  <span style=\"color:red;\">Descripci&oacute;n</span> es obligatorio</p>";}
			if (errors!= "") {
				$("#dialog-modal").dialog("open");
				$("#dialog-modal").html(errors);

				return;
			}
			jQuery.getJSON("eventos/add/titulo/"+titulo+"/localizacion/"+localizacion+"/fecha/"+fecha+"/descripcion/"+descripcion,evento_addReady);
		}
		
		function evento_addReady(data,scope){
			if (data.error == 1){
				alert ("Ha ocurrido un error al insertar el servicio, por favor revise los datos.");
				return;
			} 
			$("#eventosBody").append("<tr><td class=\"evento\"><p style=\"font-size:1.2em;color:#99008b\"><b>" + data.titulo + "</b></p><p><b>Localizaci&oactute;n: </b>" + data.localizacion + "</p><p><b>Fecha: </b>" + data.fecha + "</p><p>" + data.descripcion + "</p></td><td><button onclick=\"evento_delete(this, " + data.id + ")\"><img src=\"<spring:url value="/static/images/icons/16x16/delete.png"/>\"></button></td></tr>");
		}
	</script>
</div>
<div id="capacidades"  class="ui-tabs-panel">
	<p>
		En este apartado va a describir los servicios que presta su organizaci&oacute;n. Seleccione las capacidades que describen el trabajo que realiza su empresa y relaci&oacute;nelas con uno o varios sectores de actividad (si una misma capacidad est&aacute; relacionada con varios sectores deber&aacute; indicarlo). As&iacute; mismo, para cada capacidad indique el n&uacute;mero de personas implicadas (recursos), el porcentaje de negocio que supone esta actividad para su empresa y finalmente punt&uacute;e entre 1 y 10 esta capacidad.		
	</p>
	<table class="grid capacidadesGrid" id="capacidades_table">
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
			<td>
				
			</td>
		</thead>
		<tbody>
		</tbody>
<c:forEach var="oferta" items="${organizacion.ofertas}">
		<thead>
			<td colspan="6" style="background-color:#dddddd">${oferta.capacidad}</td>
		</thead>
		<tbody id="capacidad_${oferta.capacidad.id}">	
			<tr>
				<td class="capacidad">${oferta.sector}
				</td>
				<td class="descripcion">
					${oferta.capacidad.descripcion}
				<br/>
					${oferta.descripcion}
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
				<td>
					<button onclick="capacidad_delete(this, ${oferta.id})">
						<img src="<spring:url value="/static/images/icons/16x16/delete.png"/>">
					</button>
				</td>
			</tr>
		</tbody>
</c:forEach>		
		</table>
		<table style="width:100%" class="grid capacidadesAddGrid">
			<thead>
				<td>Actividad</td>
				<td>Sector</td>
				<td>Recursos</td>
				<td>% Negocio</td>
				<td>Puntuaci&oacute;n</td>
				<td> </td>
			</thead>
			<tbody>
				<tr>
					<td>
						<select id="capacidad_id" name="capacidad_id" style="max-width: 300px">
						</select>
					</td>
					<td>
						<select id="sector_id" name="sector_id" style="max-width: 300px">
<c:forEach var="sector" items="${sectores}">
					<option value="${sector.id}"> &nbsp; &nbsp;${sector}</option>						
</c:forEach>
						</select>
					</td>
					<td>
						<select id="personas" name="personas">
<c:forEach var="i" begin="1" end="100" step="1" varStatus ="status">
							<option value="${i}">${i}</option>
</c:forEach>
							<option value="101">&gt; 100</option>
						</select>
					</td>
					<td>
						<select id="porcentaje" name="porcentaje">
<c:forEach var="i" begin="1" end="100" step="1" varStatus ="status">
							<option value="${i}">${i}%</option>
</c:forEach>
						</select>
					</td>
					<td>
						<select id="puntuacion" name="puntuacion">
<c:forEach var="i" begin="1" end="10" step="1" varStatus ="status">
							<option value="${i}">${i}/10</option>
</c:forEach>
						</select>
					</td>
					<tr>
						<td colspan="3">
							<span style="font-weight:bold;font-size:9px">Descripci&oacute;n</span>
							<textarea id="descripcion_oferta" style="width:99%;height:64px;font-size:11px;"></textarea>
						</td>
					</tr>
					<td>
						<button  onclick="capacidad_add();return false;">
						<img src="<spring:url value="/static/images/icons/16x16/add.png" />"></button>
					</td>
				</tr>
		</tbody>
	</table>
	<script>
		function dce (tagname){ return document.createElement(tagname);}
		var deleting_el="";
		function capacidad_delete(el,id){
			deleting_el = 1;
			el.parentNode.parentNode.parentNode.removeChild(el.parentNode.parentNode);
			jQuery.getJSON("organizacionCapacidad/"+id+"/del",capacidad_delReady);
		}
	
		function capacidad_delReady(data,scope){
			deleting_el = "";
		}
	
		function capacidad_add(){
			var capacidad_id = ($("#capacidad_id").attr("value"));
			if (capacidad_id < 1) { alert ("No se ha seleccionado una capacidad. Por favor, seleccione una capacidad que no forme parte de un grupo de capacidades"); return ; }
			var sector_id = ($("#sector_id").attr("value"));
			var porcentaje = ($("#porcentaje").attr("value"));
			var personas = ($("#personas").attr("value"));
			var puntuacion = ($("#puntuacion").attr("value"));
			var descripcion = ($("#descripcion_oferta").attr("value"));
			jQuery.getJSON("organizacionCapacidad/${organizacion.id}&/capacidad/"+capacidad_id+"&sector_id="+sector_id+"&porcentaje="+porcentaje+"&personas="+personas+"&puntuacion="+puntuacion+"&descripcion="+descripcion,capacidad_addReady);
		}
		
		function capacidad_addReady(data,scope){
			if (data.error == 1){
				alert ("Ha ocurrido un error al insertar el servicio, por favor revise los datos.");
				return;
			} 
			var tr = dce("tr");
			var td = dce("td"); td.className="capacidad"; td.innerHTML = data.sector_name; tr.appendChild(td);
			var td = dce("td"); td.className="descripcion"; td.innerHTML = data.descripcion; tr.appendChild(td);
			var td = dce("td"); td.className="personas"; td.innerHTML = data.personas+ "&nbsp;personas."; tr.appendChild(td);
			var td = dce("td"); td.className="porcentaje"; td.innerHTML = data.porcentaje+ " %"; tr.appendChild(td);
			var td = dce("td"); td.className="puntuacion"; td.innerHTML = data.puntuacion;  tr.appendChild(td);
			var button = dce ("button");  var img = dce("img"); img.src= "<spring:url value="/static/images/icons/16x16/delete.png" />"; button.appendChild(img);
			button.onclick = function () { capacidad_delete(this, data.id); }
			var td = dce("td"); td.appendChild(button); tr.appendChild(td);
			var done=0;

			el = document.getElementById('capacidades_table');
			for (i=0;i < el.tBodies.length;i++){
				if (el.tBodies[i].id == "capacidad_"+data.capacidad){
					el.tBodies[i].appendChild(tr);
					done=1;
				}
			}
			if (done == 0){				
				var thead = dce("thead"); var tbody = dce("tbody");
				tbody.id = "sector_"+data.capcidad;
				var td = dce("td"); td.setAttribute("colspan",6);td.setAttribute("colspan",6);td.style.backgroundColor = "#dddddd"; td.innerHTML = data.capacidad_name;
				thead.appendChild(td); tbody.appendChild(tr);
				el.appendChild(thead); el.appendChild(tbody);
			}
		}
	</script>
	
</div>

<div id="demandas"  class="ui-tabs-panel">
	<table class="grid capacidadesGrid" id="demandas_table">
		<thead>
			<td style="background-color: #bbbbbb">
				Servicio / Sector especializado
			</td>
			<td style="background-color: #bbbbbb">
				Descripci&oacute;n
			</td>
			<td>
				
			</td>
		</thead>
		<tbody>
		</tbody>
<c:forEach var="demanda" items="${organizacion.demandas}">
		<thead>
			<td colspan="3" style="background-color: #dddddd">${demanda.capacidad}</td>
		</thead>
		<tbody id="capacidad_${demanda.capacidad.id}">
			<tr>
				<td class="capacidad">${demanda.sector}</td>
				<td>
					${demanda.capacidad.descripcion}
					<br />
					${demanda.descripcion}
				</td>
				<td>
					<button onclick="demanda_delete(this, ${demanda.id})">
						<img src="<spring:url value="/static/images/icons/16x16/delete.png" />">
					</button>	
				</td>
			</tr>
		</tbody>
</c:forEach>		
	</table>
	<table style="width:100%" class="grid capacidadesAddGrid" >
		<thead>
			<td>Actividad</td>
			<td>Sector</td>
			<td> </td>
		</thead>
		<tbody>
			<tr>
				<td>
					<select id="capacidad_id_demanda" name="capacidad_id" style="max-width: 300px;">
					</select>
					</td>
					<td>
						<select id="sector_id_demanda" name="sector_id" style="max-width:300px">
<c:forEach var="sector" items="${sectores}">
							<option value="${sector.id}"> &nbsp; &nbsp;${sector}</option>						
</c:forEach>
						</select>
					</td>		
					<tr>
						<td colspan="3">
							
							<span style="font-weight:bold;font-size:9px">Descripci&oacute;n</span>
							<textarea id="descripcion_demanda" style="width:99%;height:64px;font-size:11px;"></textarea>
							
						</td>
					</tr>			
					<td>
						<button  onclick="demanda_add();return false;">
							<img src="<spring:url value="/static/images/icons/16x16/add.png" />">
						</button>
					</td>
				</tr>
		</tbody>
	</table>
	<script>
		function dce (tagname){ return document.createElement(tagname);}
		var deleting_el="";
		function demanda_delete(el,id){
			deleting_el = 1;
			el.parentNode.parentNode.parentNode.removeChild(el.parentNode.parentNode);
			jQuery.getJSON("demandas/"+id+"/del",capacidad_delReady);
		}
	
		function demanda_delReady(data,scope){
			deleting_el = "";
		}
	
		function demanda_add(){
			var capacidad_id = ($("#capacidad_id_demanda").attr("value"));
			if (capacidad_id < 1) { alert ("No se ha seleccionado una capacidad. Por favor, seleccione una capacidad que no forme parte de un grupo de capacidades"); return ; }
			var sector_id = ($("#sector_id_demanda").attr("value"));
			var descripcion_demanda = ($("#descripcion_demanda").attr("value"));
			jQuery.getJSON("organizacionDemanda/${organizacion.id}/capacidad/"+capacidad_id+"/sector/"+sector_id+"/descripcion/"+descripcion_demanda,demanda_addReady);
		}
		
		function demanda_addReady(data,scope){
			if (data.error == 1){
				alert ("Ha ocurrido un error al insertar el servicio, por favor revise los datos.");
				return;
			} 
			var tr = dce("tr");
			var td = dce("td"); td.className="capacidad"; td.innerHTML = data.sector_name; tr.appendChild(td);
			var td = dce("td"); td.className="descripcion"; td.innerHTML = data.descripcion_capacidad + "<br />" + data.descripcion; tr.appendChild(td);
			var button = dce ("button");  var img = dce("img"); img.src= "media/icons/16x16/delete.png"; button.appendChild(img);
			button.onclick = function () { demanda_delete(this, data.id); }
			var td = dce("td"); td.appendChild(button); tr.appendChild(td);
			var done=0;

			el = document.getElementById('demandas_table');
			for (i=0;i < el.tBodies.length;i++){
				if (el.tBodies[i].id == "capacidad_"+data.capacidad){
					el.tBodies[i].appendChild(tr);
					done=1;
				}
			}
			if (done == 0){				
				var thead = dce("thead"); var tbody = dce("tbody");
				tbody.id = "capacidad_"+data.capacidad;
 				var td = dce("td"); td.setAttribute("colspan",6); td.style.backgroundColor = "#dddddd"; td.innerHTML = data.capacidad_name;
				thead.appendChild(td); tbody.appendChild(tr);
				el.appendChild(thead); el.appendChild(tbody);
			}
		}
	</script>
	
	
	
	
</div>


</div>
<script type="text/javascript">
function limitChars(textid, limit, infodiv)
{
var text = $('#'+textid).val(); 
var textlength = text.length;
if(textlength > limit)
{
$('#' + infodiv).html('No puede introducir mas de '+limit+' caracteres');
 $('#'+textid).val(text.substr(0,limit));
 return false;
 }
 else
 {
 $('#' + infodiv).html('Le quedan '+ (limit - textlength) +' caracteres.');
 return true;
 }
}

$(window).bind("load",function(){
	$('#descripcion').keyup(function(){
	 limitChars('descripcion', 255, 'charlimitinfo');
	})
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
$("#eventDate").datepicker();
$("#eventosPasados").hide();
$("#asistentes-modal").dialog({
	autoOpen: false,
	modal: true,
	resizable: false
});
$("#dialog-modal").dialog({
	autoOpen: false,
	modal: true,
	resizable: false,
	buttons: {
					Ok: function() {
						$(this).dialog('close');
					}
				}
});

});
</script>

<%@ include file="/WEB-INF/jsp/footer.jsp" %>