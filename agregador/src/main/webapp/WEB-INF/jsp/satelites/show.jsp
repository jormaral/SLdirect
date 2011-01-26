<%@ include file="/WEB-INF/jsp/includes.jsp" %>

<jsp:include page="/WEB-INF/jsp/header.jsp" >
	<jsp:param name="pageTitle" value="Satelite"/>
</jsp:include>
<h2>Satelite</h2>

<table class="viewForm" style="margin:12px;">
<tr>
	<td class="key" style="font-weight:bold">
		<fmt:message key="satelite.name"/>:
	</td>
	<td colspan="2">
		${satelite.name}
	</td>	
</tr>
<tr>
	<td class="key" style="font-weight:bold">
		<fmt:message key="satelite.hostUrl"/>:
	</td>
	<td colspan="2">
		${satelite.hostUrl}
	</td>	
</tr>
<tr>
	<td class="key" style="font-weight:bold">
		<fmt:message key="satelite.user"/>:
	</td>
	<td colspan="2">
		${satelite.user}
	</td>	
</tr>
<tr>
	<td class="key" style="font-weight:bold">
		<fmt:message key="satelite.lat"/>:
	</td>
	<td colspan="2">
		${satelite.lat}
	</td>	
</tr>
<tr>
	<td class="key" style="font-weight:bold">
		<fmt:message key="satelite.lon"/>:
	</td>
	<td colspan="2">
		${satelite.lon}
	</td>	
</tr>
<tr>
	<td class="key" style="font-weight:bold">
		<fmt:message key="satelite.lastRetrieval"/>:
	</td>
	<td colspan="2">
		${satelite.lastRetrieval}
	</td>	
</tr>
<tr>
	<td class="key" style="font-weight:bold">
		<fmt:message key="satelite.numEmpresas"/>:
	</td>
	<td colspan="2">
		${satelite.numEmpresas}
	</td>	
</tr>

</table>
<p>
	<spring:url value="/satelites/{sateliteId}/edit" var="editUrl">
		<spring:param name="sateliteId" value="${satelite.id}" />
	</spring:url>
	<a href="${fn:escapeXml(editUrl)}">Editar</a>
	
	<spring:url value="/satelites/{sateliteId}/orgs" var="editUrl">
		<spring:param name="sateliteId" value="${satelite.id}" />
	</spring:url>
	<a href="${fn:escapeXml(editUrl)}"><fmt:message key="organizaciones"/></a>

</p>

<%@ include file="/WEB-INF/jsp/footer.jsp" %>