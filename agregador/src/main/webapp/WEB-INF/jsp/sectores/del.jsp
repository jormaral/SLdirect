<%@ include file="/WEB-INF/jsp/includes.jsp" %>
<jsp:include page="/WEB-INF/jsp/header.jsp" >
	<jsp:param name="pageTitle" value="Editar Organización"/>
</jsp:include>

<c:set var="method" value="delete"/>

<div class="breadcrumb">
	<img src="<spring:url value="/static/images/icons/16x16/house.png" />">
	<a href="<spring:url value="/" />">Inicio</a>
	<img src="<spring:url value="/static/images/breadcrumb.png" />">
	<a href="<spring:url value="/admin" />">Administración</a>
	<img src="<spring:url value="/static/images/breadcrumb.png" />">
	Borrar Sector
</div>

<div id="tabs">
	<ul>
		<li class="ui-tabs-nav-item"><a href="#general"><span><img src="<spring:url value="/static/images/icons/16x16/book.png" />" />Borrar: ${formaJuridica}</span></a></li>
  	</ul>
	<div id="general" class="ui-tabs-panel">
	<form:form modelAttribute="sector" method="${method}">
	<table class="viewForm" style="border-spacing:0px">
		<tr>
<c:choose>
	<c:when test="${empty deleteError}">
			<td colspan="2" class="separator">Confirma la eliminación?</td>
	</c:when>
	<c:otherwise>
			<td colspan="2" class="separator">${deleteError}</td>
	</c:otherwise>
</c:choose>			
		</tr>			
		<tr>
			<td class="key"><fmt:message key="sector.name"/></td>
			<td>
				<form:input path="name" size="50" maxlength="50" readonly="true"/>
			</td>
		</tr>
	</table>
	</form:form>
	<c:if test="${empty deleteError}">
	<button onclick="document.forms[1].submit();"><img src="<spring:url value="/static/images/icons/16x16/disk.png" />" />Borrar</button>
	</c:if>	
	<button onclick="document.location='<spring:url value="/admin#sectores" />'"><img src="<spring:url value="/static/images/icons/16x16/cancel.png" />" />Cancelar</button>
	</div>
</div>

<script type="text/javascript">

$(window).bind("load",function(){
$('#tabs').tabs({ fx: { height: 'toggle', opacity: 'toggle', duration: 'fast' } });
});

</script>

<%@ include file="/WEB-INF/jsp/footer.jsp" %>