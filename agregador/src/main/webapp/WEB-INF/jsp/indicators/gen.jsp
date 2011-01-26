<%@ include file="/WEB-INF/jsp/includes.jsp" %>
<jsp:include page="/WEB-INF/jsp/header.jsp" >
	<jsp:param name="pageTitle" value="Generar Indicador"/>
</jsp:include>
<div class="breadcrumb">
	<img src="<spring:url value="/static/images/icons/16x16/house.png" />">
	<a href="<spring:url value="/" />">Inicio</a>
	<img src="<spring:url value="/static/images/breadcrumb.png" />">
	<a href="<spring:url value="/indicators" />">Indicadores</a>
</div>

<c:forEach var="indicador" items="${indicadores}">
<p>
	<spring:url value="/indicators/{indicatorId}/png" var="editUrl">
		<spring:param name="indicatorId" value="${indicador.id}" />
	</spring:url>
	<img alt="${indicador.name}" src="${fn:escapeXml(editUrl)}" />
</p>
<br/>
</c:forEach>


<%@ include file="/WEB-INF/jsp/footer.jsp" %>