<%@ include file="/WEB-INF/jsp/includes.jsp" %>

<jsp:include page="/WEB-INF/jsp/header.jsp" >
	<jsp:param name="pageTitle" value="Búsqueda"/>
</jsp:include>

<div class="breadcrumb">
	<img src="<spring:url value="/static/images/icons/16x16/house.png" />">
	<a href="<spring:url value="/" />">Inicio</a>
	<img src="<spring:url value="/static/images/breadcrumb.png" />">
	Buscador de Empresas
</div>

<table>
	<tr>
		<td>
			<h1>El A-Z del Software Libre</h1>
			<span style="font-size:14px">Hay <b>${totalOrganizaciones}</b> empresas registradas en el directorio<?php if (array_key_exists("organizacionClasificacion_id",$_REQUEST) && $_REQUEST["organizacionClasificacion_id"] != "") echo " bajo el filtro seleccionado"?>. </span><br /><br />
			<table style="width:100%">
				<tr>
					<td colspan="2">
							<select onchange="document.location=this.options[this.selectedIndex].value;" name="organizacionClasificacion_id" style="width:100%;font-size:1.5em">
	<spring:url value="/busqueda" var="editUrl" />
								<option value="${fn:escapeXml(editUrl)}" style="font-weight:bold">Todas las Actividades</option>
<c:forEach var="orgClass" items="${organizacionClasificaciones}">
	<spring:url value="/busqueda/{orgClassId}" var="editUrl">
		<spring:param name="orgClassId" value="${orgClass.id}" />
	</spring:url>
	<c:choose>
		<c:when test="${classOrg eq orgClass}">
			<option value="${fn:escapeXml(editUrl)}" selected="selected">${orgClass}</option>
		</c:when>
		<c:otherwise>
			<option value="${fn:escapeXml(editUrl)}">${orgClass}</option>
		</c:otherwise>
	</c:choose>									
</c:forEach>
					</td>
				</tr>
			</table>
			<table style="width:100%;height:420px;overflow:scroll">
				<tbody style="overflow:scroll;overflow-y:auto;overflow-x:hidden;height:420px;">
<c:forEach var="entry" items="${sateliteOrgsMaps}" varStatus="status">
		<tr style="height:22px;">
			<td style="padding:0px">
				<h2 style="margin:none;color:white;padding:4px;background:#a72791">${entry.key}</h2>
			</td>
		</tr>
	<c:forEach var="organizacion" items="${entry.value}">
		<tr style="height:22px;">
			<spring:url value="/orgs/{orgId}" var="editUrl">
				<spring:param name="orgId" value="${organizacion.id}" />
			</spring:url>
		
			<td style="cursor:pointer;padding-right:22px;" onclick="document.location='${fn:escapeXml(editUrl)}'">
				<span class="searchResultTitle" style="font-size:14px;">${organizacion}</span>
				<span class="searchResultTitle" style="font-size:10px;font-weight:normal">${organizacion.descripcion}</span>
			</td>
		</tr>
	</c:forEach>		
</c:forEach>
 
 
			<tr><td>
				&nbsp;&nbsp;&nbsp;&nbsp;
			</td>
			</tr>
			</tbody>
			</table>
		</td>
		<td style="width:32px;">
			
		</td>			
		<td style="vertical-align:top">	
			<h1>B&uacute;squeda Estructurada</h1>
			<span style="font-size:14px">Tambi&eacute;n puede utilizar los buscadores Estructurados: </span><br /><br />
			<div onclick="document.location='<spring:url value="/busquedaServicio" />'" style="cursor:pointer;text-align:justify;width:350px;height:80px;background:url(<spring:url value="/static/images/porServicio.png" />) no-repeat"><h1 style="font-size:18px;padding-left:24px;margin-bottom:8px;padding-top:8px;">Buscar por Servicios</h1>
				<span style="font-size:1.3em;color:#525252;line-height:1.5em;"> Utiliza la b&uacute;squeda por Servicios para encontrar empresas que ofrezcan servicios basados en Software Libre en determinados sectores econ&oacute;micos.</span>				
			</div>
			<div onclick="document.location='<spring:url value="/busquedaDemanda" />'" style="cursor:pointer;margin-top:32px;text-align:justify;width:350px;height:80px;background:url(<spring:url value="/static/images/porDemanda.png" />) no-repeat"><h1 style="font-size:18px;padding-left:24px;margin-bottom:8px;padding-top:8px;">Buscar por Demanda</h1>
				<span style="font-size:1.3em;color:#525252;line-height:1.5em;"> Encuentra empresas que demanden servicios de otras empresas de tecnolog&iacute;a en determinados servicios.</span>				
			</div>		
			<div onclick="document.location='<spring:url value="/busquedaGeo" />'"  style="cursor:pointer;margin-top:32px;text-align:justify;width:350px;height:80px;background:url(<spring:url value="/static/images/porLocalizacion.png" />) no-repeat"><h1 style="font-size:18px;padding-left:24px;margin-bottom:8px;padding-top:8px;">Buscar por Localizaci&oacute;n</h1>
				<span style="font-size:1.3em;color:#525252;line-height:1.5em;"> Utiliza la b&uacute;squeda por Localizaci&oacute;n para encontrar empresas cercanas que ofrezcan servicios basados en Software Libre.</span>				
			</div>
		</td>
</table>

<%@ include file="/WEB-INF/jsp/footer.jsp" %>