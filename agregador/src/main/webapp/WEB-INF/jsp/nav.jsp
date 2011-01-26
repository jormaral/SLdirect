<%@ include file="/WEB-INF/jsp/includes.jsp" %>

<ul>
	<li>
		<a href="<spring:url value="/satelites"/>" ><fmt:message key="satelites"/></a>	
	</li>
	<li>
		<a href="<spring:url value="/users"/>" ><fmt:message key="users"/></a>	
	</li>
	<li>
		<a href="<spring:url value="/mapa"/>" ><fmt:message key="mapa.satelites"/></a>	
	</li>
<sec:authorize access="hasRole('ROLE_ADMIN')">
	<li>
		<a href="<spring:url value="/indicators"/>" ><fmt:message key="indicators"/></a>	
	</li>
</sec:authorize>
</ul>
