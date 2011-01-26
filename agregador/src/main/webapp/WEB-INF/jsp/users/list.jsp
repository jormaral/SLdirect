<%@ include file="/WEB-INF/jsp/includes.jsp" %>
<jsp:include page="/WEB-INF/jsp/header.jsp" >
	<jsp:param name="pageTitle" value="Usuarios"/>
</jsp:include>
<h1><fmt:message key="users"/></h1>
<table>
  <tr>
    <th><fmt:message key="user.name"/></th>
    <th><fmt:message key="user.estado"/></th>
  </tr>
 <c:forEach var="user" items="${users}">
   <tr class="row" id="user${user.username}">
   	<td>
        <spring:url value="/users/{userName}" var="editUrl">
        	<spring:param name="userName" value="${user.username}" />
        </spring:url>
        <a href="${fn:escapeXml(editUrl)}">${user.username}</a>
   	</td>
     <td>${user.enabled}</td>
   </tr>
 </c:forEach>
</table>

<%@ include file="/WEB-INF/jsp/footer.jsp" %>