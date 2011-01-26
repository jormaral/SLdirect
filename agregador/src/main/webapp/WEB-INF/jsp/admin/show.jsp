<%@ include file="/WEB-INF/jsp/includes.jsp" %>
<jsp:include page="/WEB-INF/jsp/header.jsp" >
	<jsp:param name="pageTitle" value="Administración"/>
</jsp:include>
<!-- Breadcrum -->
<div class="breadcrumb">
	<img src="<spring:url value="/static/images/icons/16x16/house.png" />">
	<a href="<spring:url value="/" />">Inicio</a>
	<img src="<spring:url value="/static/images/breadcrumb.png" />">
	<a href="<spring:url value="/admin" />">Administración</a>
</div>
<div id="tabs" >
  <ul>
	<li class="ui-tabs-nav-item"><a href="#satelites"><span><img src="<spring:url value="/static/images/icons/16x16/book.png" />" /><fmt:message key="satelites"/></span></a></li>
	<li class="ui-tabs-nav-item"><a href="#indicadores"><span><img src="<spring:url value="/static/images/icons/16x16/map.png" />" /><fmt:message key="indicadores"/></span></a></li>
	<li class="ui-tabs-nav-item"><a href="#sectores"><span><img src="<spring:url value="/static/images/icons/16x16/bricks.png" />" /><fmt:message key="sectores"/></span></a></li>
	<li class="ui-tabs-nav-item"><a href="#asociaciones"><span><img src="<spring:url value="/static/images/icons/16x16/group.png" />" /><fmt:message key="asociaciones"/></span></a></li>
	<li class="ui-tabs-nav-item"><a href="#fj"><span><img src="<spring:url value="/static/images/icons/16x16/shield.png" />" /><fmt:message key="formasjuridicas"/></span></a></li>
	<li class="ui-tabs-nav-item"><a href="#provincias"><span><img src="<spring:url value="/static/images/icons/16x16/map.png" />" /><fmt:message key="provincias"/></span></a></li>
	<li class="ui-tabs-nav-item"><a href="#organizaciones"><span><img src="<spring:url value="/static/images/icons/16x16/book.png" />" /><fmt:message key="organizaciones"/></span></a></li>
  </ul>
  <div id="satelites" class="ui-tabs-panel">
  <table class="viewForm">
	<tr>
		<th class="key"><fmt:message key="satelite.name"/></th>
		<th class="key"><fmt:message key="satelite.numEmpresas"/></th>
		<th class="key" style="text-align:right"><fmt:message key="satelite.state"/></th>
		<th class="key"></th>
	</tr>
<c:forEach var="satelite" items="${satelites}">
	<spring:url value="/satelites/{sateliteId}/edit" var="editUrl">
		<spring:param name="sateliteId" value="${satelite.id}" />
	</spring:url>
	<spring:url value="/satelites/{sateliteId}/orgs" var="orgsUrl">
		<spring:param name="sateliteId" value="${satelite.id}" />
	</spring:url>	
	<tr>
		<td class="key">${satelite}</td>
		<td class="key">
			<a href="${fn:escapeXml(orgsUrl)}">${satelite.numEmpresas}</a>	
		</td>
		<td style="text-align:right">
	    <c:choose>
	      <c:when test="${satelite.activado}">
			<img src="<spring:url value="/static/images/icons/16x16/accept.png" />">	      
	        <spring:url value="/satelites/{sateliteId}/stop" var="stateUrl">
	        	<spring:param name="sateliteId" value="${satelite.id}" />
	        </spring:url>
	        <a href="${fn:escapeXml(stateUrl)}"><fmt:message key="satelite.stop"/></a>
	      </c:when>
	      <c:otherwise>
			<img src="<spring:url value="/static/images/icons/16x16/cancel.png" />">	      
	        <spring:url value="/satelites/{sateliteId}/start" var="stateUrl">
	        	<spring:param name="sateliteId" value="${satelite.id}" />
	        </spring:url>
	        <a href="${fn:escapeXml(stateUrl)}"><fmt:message key="satelite.start"/></a>
	      </c:otherwise>
	    </c:choose>
		</td>
      <td><a href="${fn:escapeXml(editUrl)}"><fmt:message key="satelite.edit"/></a></td>
	</tr>
</c:forEach>	
	<spring:url value="/satelites/new" var="newUrl" />
	<tr>
		<td></td><td></td><td></td>
      	<td>
      		<a href="${fn:escapeXml(newUrl)}"><img alt="<fmt:message key="home.new"/>" src="<spring:url value="/static/images/icons/16x16/add.png" />"></a>
      	</td>
	</tr>
  </table>
  </div>
  <div id="indicadores" class="ui-tabs-panel">
  <table class="viewForm">
	<tr>
		<th class="key"><fmt:message key="indicator.name"/></th>
		<th class="key"><fmt:message key="indicator.tipoGrafico"/></th>
		<th class="key"><fmt:message key="indicator.size"/></th>
		<th class="key"><fmt:message key="home.acciones"/></th>
	</tr>
<c:forEach var="indicador" items="${indicadores}">
	<spring:url value="/indicators/{indicatorId}" var="showUrl">
		<spring:param name="indicatorId" value="${indicador.id}" />
	</spring:url>
	<spring:url value="/indicators/{indicatorId}/edit" var="editUrl">
		<spring:param name="indicatorId" value="${indicador.id}" />
	</spring:url>
	<spring:url value="/indicators/{indicatorId}/del" var="delUrl">
		<spring:param name="indicatorId" value="${indicador.id}" />
	</spring:url>
	<tr>
		<td><a href="${fn:escapeXml(showUrl)}">${indicador.name}</a></td>
		<td>${indicador.tipoGrafico}</td>
		<td>${indicador.width}x${indicador.height}</td>
      	<td>
      		<a href="${fn:escapeXml(editUrl)}"><img alt="<fmt:message key="home.edit"/>" src="<spring:url value="/static/images/icons/16x16/pencil.png" />"></a>
      		&nbsp;
      		<a href="${fn:escapeXml(delUrl)}"><img alt="<fmt:message key="home.del"/>" src="<spring:url value="/static/images/icons/16x16/cancel.png" />"></a>
      	</td>
	</tr>
</c:forEach>
	<spring:url value="/indicators/new" var="newUrl" />
	<tr>
		<td></td><td></td><td></td><td></td>
      	<td>
      		<a href="${fn:escapeXml(newUrl)}"><img alt="<fmt:message key="home.new"/>" src="<spring:url value="/static/images/icons/16x16/add.png" />"></a>
      	</td>
	</tr>
  </table>
  </div>
  
  <!-- Sectores -->
  
  <div id="sectores" class="ui-tabs-panel">
  <table class="viewForm">
	<tr>
		<th class="key"><fmt:message key="sector.name"/></th>
		<th class="key"><fmt:message key="home.acciones"/></th>
	</tr>
<c:forEach var="sector" items="${sectores}">
	<spring:url value="/sectores/{sectorId}/edit" var="editUrl">
		<spring:param name="sectorId" value="${sector.id}" />
	</spring:url>
	<spring:url value="/sectores/{sectorId}/del" var="delUrl">
		<spring:param name="sectorId" value="${sector.id}" />
	</spring:url>
	<tr>
		<td>${sector.name}</td>
      	<td>
      		<a href="${fn:escapeXml(editUrl)}"><img alt="<fmt:message key="home.edit"/>" src="<spring:url value="/static/images/icons/16x16/pencil.png" />"></a>
      		&nbsp;
      		<a href="${fn:escapeXml(delUrl)}"><img alt="<fmt:message key="home.del"/>" src="<spring:url value="/static/images/icons/16x16/cancel.png" />"></a>
      	</td>
	</tr>
</c:forEach>
	<spring:url value="/sectores/new" var="newUrl" />
	<tr>
		<td></td>
      	<td>
      		<a href="${fn:escapeXml(newUrl)}"><img alt="<fmt:message key="home.new"/>" src="<spring:url value="/static/images/icons/16x16/add.png" />"></a>
      	</td>
	</tr>
  </table>
  </div>
  
  <!-- Asociaciones -->
  
  <div id="asociaciones" class="ui-tabs-panel">
  <table class="viewForm">
	<tr>
		<th class="key"><fmt:message key="asociacion.name"/></th>
		<th class="key"><fmt:message key="asociacion.icon"/></th>
		<th class="key"><fmt:message key="asociacion.url"/></th>
		<th class="key"><fmt:message key="home.acciones"/></th>
	</tr>
<c:forEach var="asociacion" items="${asociaciones}">
	<spring:url value="/asociaciones/{asociacionId}/edit" var="editUrl">
		<spring:param name="asociacionId" value="${asociacion.id}" />
	</spring:url>
	<spring:url value="/asociaciones/{asociacionId}/del" var="delUrl">
		<spring:param name="asociacionId" value="${asociacion.id}" />
	</spring:url>
	<tr>
		<td>${asociacion.name}</td>
		<td>${asociacion.icon}</td>
		<td>${asociacion.url}</td>
      	<td>
      		<a href="${fn:escapeXml(editUrl)}"><img alt="<fmt:message key="home.edit"/>" src="<spring:url value="/static/images/icons/16x16/pencil.png" />"></a>
      		&nbsp;
      		<a href="${fn:escapeXml(delUrl)}"><img alt="<fmt:message key="home.del"/>" src="<spring:url value="/static/images/icons/16x16/cancel.png" />"></a>
      	</td>
	</tr>
</c:forEach>
	<spring:url value="/asociaciones/new" var="newUrl" />
	<tr>
		<td></td><td></td><td></td>
      	<td>
      		<a href="${fn:escapeXml(newUrl)}"><img alt="<fmt:message key="home.new"/>" src="<spring:url value="/static/images/icons/16x16/add.png" />"></a>
      	</td>
	</tr>
	</table>  
  </div>  
    
  <!-- Formas Juridicas -->
  
  <div id="fj" class="ui-tabs-panel">
  <table class="viewForm">
	<tr>
		<th class="key"><fmt:message key="formajuridica.name"/></th>
		<th class="key"><fmt:message key="home.acciones"/></th>
	</tr>
<c:forEach var="formaJuridica" items="${formasJuridicas}">
	<spring:url value="/fj/{formaJuridicaId}/edit" var="editUrl">
		<spring:param name="formaJuridicaId" value="${formaJuridica.id}" />
	</spring:url>
	<spring:url value="/fj/{formaJuridicaId}/del" var="delUrl">
		<spring:param name="formaJuridicaId" value="${formaJuridica.id}" />
	</spring:url>
	<tr>
		<td>${formaJuridica.name}</td>
      	<td>
      		<a href="${fn:escapeXml(editUrl)}"><img alt="<fmt:message key="home.edit"/>" src="<spring:url value="/static/images/icons/16x16/pencil.png" />"></a>
      		&nbsp;
      		&nbsp;
      		<a href="${fn:escapeXml(delUrl)}"><img alt="<fmt:message key="home.del"/>" src="<spring:url value="/static/images/icons/16x16/cancel.png" />"></a>
      	</td>
	</tr>
</c:forEach>
	<spring:url value="/fj/new" var="newUrl" />
	<tr>
		<td></td>
      	<td>
      		<a href="${fn:escapeXml(newUrl)}"><img alt="<fmt:message key="home.new"/>" src="<spring:url value="/static/images/icons/16x16/add.png" />"></a>
      	</td>
	</tr>
  </table>
  </div>

  <!-- Provincias -->
  <div id="provincias" class="ui-tabs-panel">
  <table class="viewForm">
	<tr>
		<th class="key"><fmt:message key="provincia.name"/></th>
		<th class="key"><fmt:message key="home.acciones"/></th>
	</tr>
<c:forEach var="provincia" items="${provincias}">
	<spring:url value="/provincias/{provinciaId}/edit" var="editUrl">
		<spring:param name="provinciaId" value="${provincia.id}" />
	</spring:url>
	<spring:url value="/provincias/{provinciaId}/del" var="delUrl">
		<spring:param name="provinciaId" value="${provincia.id}" />
	</spring:url>
	<tr>
		<td>${provincia.name}</td>
      	<td>
      		<a href="${fn:escapeXml(editUrl)}"><img alt="<fmt:message key="home.edit"/>" src="<spring:url value="/static/images/icons/16x16/pencil.png" />"></a>
      		&nbsp;
      		<a href="${fn:escapeXml(delUrl)}"><img alt="<fmt:message key="home.del"/>" src="<spring:url value="/static/images/icons/16x16/cancel.png" />"></a>
      	</td>
	</tr>
</c:forEach>
	<spring:url value="/provincias/new" var="newUrl" />
	<tr>
		<td></td>
      	<td>
      		<a href="${fn:escapeXml(newUrl)}"><img alt="<fmt:message key="home.new"/>" src="<spring:url value="/static/images/icons/16x16/add.png" />"></a>
      	</td>
	</tr>
  </table>
  </div>

  <!-- Organizaciones -->
  <div id="organizaciones" class="ui-tabs-panel">
  <table class="viewForm">
	<tr>
		<th class="key"><fmt:message key="organizacion.name"/></th>
		<th class="key"><fmt:message key="organizacion.cif"/></th>
		<th class="key"><fmt:message key="organizacion.satelite"/></th>
		<th class="key"><fmt:message key="home.acciones"/></th>
	</tr>
<c:forEach var="organizacion" items="${organizaciones}">
	<spring:url value="/orgs/{organizacionId}/edit" var="editUrl">
		<spring:param name="organizacionId" value="${organizacion.id}" />
	</spring:url>
	<spring:url value="/orgs/{organizacionId}/del" var="delUrl">
		<spring:param name="organizacionId" value="${organizacion.id}" />
	</spring:url>
	<tr>
		<td>${organizacion.name}</td>
		<td>${organizacion.cif}</td>
		<td>
<c:forEach var="sat" items="${organizacion.satelites}">
	${sat.satelite}&nbsp; <br/>		
</c:forEach>
		</td>
      	<td>
      		<a href="${fn:escapeXml(editUrl)}"><img alt="<fmt:message key="home.edit"/>" src="<spring:url value="/static/images/icons/16x16/pencil.png" />"></a>
      		&nbsp;
      		<a href="${fn:escapeXml(delUrl)}"><img alt="<fmt:message key="home.del"/>" src="<spring:url value="/static/images/icons/16x16/cancel.png" />"></a>
      	</td>
	</tr>
</c:forEach>
<%--
	<spring:url value="/orgs/new" var="newUrl" />
	<tr>
		<td></td><td></td>
      	<td>
      		<a href="${fn:escapeXml(newUrl)}"><img alt="<fmt:message key="home.new"/>" src="<spring:url value="/static/images/icons/16x16/add.png" />"></a>
      	</td>
	</tr>
 --%>	
  </table>
  </div>


</div>

<script type="text/javascript">
$(window).bind("load",function(){
$('#tabs').tabs({ fx: { height: 'toggle', opacity: 'toggle', duration: 'fast' } });
});
</script>
<%@ include file="/WEB-INF/jsp/footer.jsp" %>
