<%@ include file="/WEB-INF/jsp/includes.jsp" %>
<jsp:include page="/WEB-INF/jsp/header.jsp" >
	<jsp:param name="pageTitle" value="Indicador"/>
</jsp:include>
<h2><fmt:message key="indicator"/></h2>

<p>
	<spring:url value="/indicators/{indicatorId}/png" var="editUrl">
		<spring:param name="indicatorId" value="${indicator.id}" />
	</spring:url>
	<img alt="${indicator.name}" src="${fn:escapeXml(editUrl)}" />
</p>
<br/>

<table class="viewForm" style="margin:12px;">
<tr>
	<td class="key" style="font-weight:bold">
		<fmt:message key="indicator.name"/>:
	</td>
	<td colspan="2">
		${indicator.name}
	</td>	
</tr>
<tr>
	<td class="key" style="font-weight:bold">
		<fmt:message key="indicator.descripcion"/>:
	</td>
	<td colspan="2">
		${indicator.descripcion}
	</td>	
</tr>
</table>
<p>
	<spring:url value="/indicators/{indicatorId}/edit" var="editUrl">
		<spring:param name="indicatorId" value="${indicator.id}" />
	</spring:url>
	<a href="${fn:escapeXml(editUrl)}">Editar</a>
</p>

<%@ include file="/WEB-INF/jsp/footer.jsp" %>