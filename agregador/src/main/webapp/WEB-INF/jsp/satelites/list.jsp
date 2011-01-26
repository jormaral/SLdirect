<%@ include file="/WEB-INF/jsp/includes.jsp" %>
<jsp:include page="/WEB-INF/jsp/header.jsp" >
	<jsp:param name="pageTitle" value="Satelites"/>
</jsp:include>
<p>
<a href="<spring:url value="/satelites/new" />">Nuevo Sat&eacute;lite</a>
</p>
<h1>Lista de Sat&eacute;lites</h1>

		<table>
		<thead>	
		  <tr>
		    <th><fmt:message key="satelite.name"/></th>
		    <th><fmt:message key="satelite.hostUrl"/></th>
		    <th><fmt:message key="satelite.user"/></th>
		    <th><fmt:message key="satelite.lat"/></th>
		    <th><fmt:message key="satelite.lon"/></th>
		    <th><fmt:message key="satelite.numEmpresas"/></th>
		    <th><fmt:message key="satelite.activado"/></th>
<sec:authorize access="hasRole('ROLE_ADMIN')">
		    <th></th>
</sec:authorize>		    
		  </tr>
		</thead>
		<tbody>
		  <c:forEach var="satelite" items="${satelites}">
		    <tr class="row" id="satelite${satelite.id}">
		    	<td>
			        <spring:url value="/satelites/{sateliteId}/orgs" var="editUrl">
			        	<spring:param name="sateliteId" value="${satelite.id}" />
			        </spring:url>
			        <a href="${fn:escapeXml(editUrl)}">${satelite.name}</a>
		    	</td>
		      <td>${satelite.hostUrl}</td>
		      <td>${satelite.user}</td>
		      <td>${satelite.lat}</td>
		      <td>${satelite.lon}</td>
		      <td>${satelite.numEmpresas}</td>
		      <td>
	    <c:choose>
	      <c:when test="${satelite.activado}">
			<img src="<spring:url value="/static/images/icons/16x16/accept.png" />">	      
<sec:authorize access="hasRole('ROLE_ADMIN')">		      
	        <spring:url value="/satelites/{sateliteId}/stop" var="editUrl">
	        	<spring:param name="sateliteId" value="${satelite.id}" />
	        </spring:url>
	        <a href="${fn:escapeXml(editUrl)}"><fmt:message key="satelite.stop"/></a>
</sec:authorize>
	      </c:when>
	      <c:otherwise>
			<img src="<spring:url value="/static/images/icons/16x16/cancel.png" />">	      
<sec:authorize access="hasRole('ROLE_ADMIN')">		      
	        <spring:url value="/satelites/{sateliteId}/start" var="editUrl">
	        	<spring:param name="sateliteId" value="${satelite.id}" />
	        </spring:url>
	        <a href="${fn:escapeXml(editUrl)}"><fmt:message key="satelite.start"/></a>
</sec:authorize>
	      </c:otherwise>
	    </c:choose>
	    
					</td>
<sec:authorize access="hasRole('ROLE_ADMIN')">
		      <spring:url value="/satelites/{sateliteId}/edit" var="editUrl">
		      	<spring:param name="sateliteId" value="${satelite.id}" />
		      </spring:url>
		      <td><a href="${fn:escapeXml(editUrl)}"><fmt:message key="satelite.edit"/></a></td>
</sec:authorize>
		    </tr>
		  </c:forEach>
		</tbody>
		</table>

<%@ include file="/WEB-INF/jsp/footer.jsp" %>
