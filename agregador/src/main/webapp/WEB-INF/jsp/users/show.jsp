<%@ include file="/WEB-INF/jsp/includes.jsp" %>
<jsp:include page="/WEB-INF/jsp/header.jsp" >
	<jsp:param name="pageTitle" value="Usuario"/>
</jsp:include>


<h2>
	<fmt:message key="user.information">
		<fmt:param value="${user.username}"/>
	</fmt:message>
</h2>
		
<table class="viewForm" style="margin:12px;">
<tr>
	<td class="key" style="font-weight:bold">
		<fmt:message key="user.name"/>
	</td>
	<td colspan="2">
		${user.username}
	</td>	
</tr>
<tr>
	<td class="key" style="font-weight:bold">
		<fmt:message key="user.estado"/>
	</td>
	<td colspan="2">
		${user.enabled}
	</td>	
</tr>
<tr>
	<td class="key" style="font-weight:bold">
       <spring:url value="/users/{username}/edit" var="editUrl">
       	<spring:param name="username" value="${user.username}" />
       </spring:url>
       <a href="${fn:escapeXml(editUrl)}">Editar Usuario</a>
	</td>
	<td colspan="2" >
       <spring:url value="/users/{username}/chpwd" var="editUrl">
       	<spring:param name="username" value="${user.username}" />
       </spring:url>
       <a href="${fn:escapeXml(editUrl)}">Cambiar Contraseña</a>
	</td>
</tr>
</table>

<%@ include file="/WEB-INF/jsp/footer.jsp" %>