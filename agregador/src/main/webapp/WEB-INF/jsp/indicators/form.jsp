<%@ include file="/WEB-INF/jsp/includes.jsp" %>
<jsp:include page="/WEB-INF/jsp/header.jsp" >
	<jsp:param name="pageTitle" value="Satelites"/>
</jsp:include>

<c:choose>
	<c:when test="${indicator.new}"><c:set var="method" value="post"/></c:when>
	<c:otherwise><c:set var="method" value="put"/></c:otherwise>
</c:choose>

<h2><c:if test="${indicator.new}">Nuevo </c:if><fmt:message key="indicator"/></h2>
<form:form modelAttribute="indicator" method="${method}">
<table class="viewForm" style="margin:12px;">
<tr>
	<td class="key" style="font-weight:bold">
		<fmt:message key="indicator.name"/>
		<form:errors path="name" cssClass="errors"/>
	</td>
	<td colspan="2">
		<form:input path="name" size="50" maxlength="50"/><br />
	</td>	
</tr>
<tr>
	<td class="key" style="font-weight:bold">
		<fmt:message key="indicator.descripcion"/>
		<form:errors path="descripcion" cssClass="errors"/>
	</td>
	<td colspan="2">
		<form:input path="descripcion" size="50" maxlength="255"/><br />
	</td>	
</tr>
<tr>
	<td class="key" style="font-weight:bold">
		<fmt:message key="indicator.tipoIndicador"/>
		<form:errors path="tipoIndicador" cssClass="errors"/>
	</td>
	<td colspan="2">
		<form:select path="tipoIndicador" items="${tiposIndicador}"/>
	</td>	
</tr>
<tr>
	<td class="key" style="font-weight:bold">
		<fmt:message key="indicator.tipoGrafico"/>
		<form:errors path="tipoGrafico" cssClass="errors"/>
	</td>
	<td colspan="2">
		<form:select path="tipoGrafico" items="${tiposGrafico}"/>
	</td>	
</tr>
<tr>
	<td class="key" style="font-weight:bold">
		<fmt:message key="indicator.agruparPor"/>
		<form:errors path="agruparPor" cssClass="errors"/>
	</td>
	<td colspan="2">
		<form:select path="agruparPor" items="${agruparPors}"/>
	</td>	
</tr>
<tr>
	<td class="key" style="font-weight:bold">
		<fmt:message key="indicator.width"/>
		<form:errors path="width" cssClass="errors"/>
	</td>
	<td colspan="2">
		<form:input path="width" size="50" maxlength="50"/>
	</td>	
</tr>
<tr>
	<td class="key" style="font-weight:bold">
		<fmt:message key="indicator.height"/>
		<form:errors path="height" cssClass="errors"/>
	</td>
	<td colspan="2">
		<form:input path="height" size="50" maxlength="50"/>
	</td>	
</tr>
<tr>
	<td colspan="3" style="text-align:center">
	    <c:choose>
	      <c:when test="${indicator.new}">
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