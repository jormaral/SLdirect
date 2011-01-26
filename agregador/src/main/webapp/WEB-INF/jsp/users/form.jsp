<%@ include file="/WEB-INF/jsp/includes.jsp" %>
<jsp:include page="/WEB-INF/jsp/header.jsp" >
	<jsp:param name="pageTitle" value="Usuarios"/>
</jsp:include>

<c:choose>
	<c:when test="${user.new}"><c:set var="method" value="post"/></c:when>
	<c:otherwise><c:set var="method" value="put"/></c:otherwise>
</c:choose>

<h2><c:if test="${user.new}">Nuevo </c:if><fmt:message key="user"/></h2>

<form:form modelAttribute="user" method="${method}">
<table class="viewForm" style="margin:12px;">
<tr>
	<td class="key" style="font-weight:bold">
		<fmt:message key="user.name"/>
		<form:errors path="username" cssClass="errors"/>
	</td>
	<td colspan="2">
		<form:input path="username" size="50" maxlength="50"/><br />
		<span style="font-size:9px">
		</span>
	</td>	
</tr>
<tr>
	<td class="key" style="font-weight:bold">
		<fmt:message key="user.password"/>
		<form:errors path="password" cssClass="errors"/>
	</td>
	<td colspan="2">
		<form:password path="password" size="50" maxlength="50"/>
		<span style="font-size:9px">
		</span>
	</td>	
</tr>
<tr>
	<td class="key" style="font-weight:bold">
		<fmt:message key="user.passwordRetry"/>
		<form:errors path="passwordRetry" cssClass="errors"/>
	</td>
	<td colspan="2">
		<form:password path="passwordRetry" size="50" maxlength="50"/>
		<span style="font-size:9px">
		</span>
	</td>	
</tr>
<tr>
	<td colspan="3" style="text-align:center">
	    <c:choose>
	      <c:when test="${user.new}">
	        <input id="submitter" type="submit" style="font-size:1.8em" value="Agregar">
	      </c:when>
	      <c:otherwise>
	        <input id="submitter" type="submit" style="font-size:1.8em" value="Actualizar">
	      </c:otherwise>
	    </c:choose>
	</td>
</tr>
</table>
</form:form>


<%@ include file="/WEB-INF/jsp/footer.jsp" %>