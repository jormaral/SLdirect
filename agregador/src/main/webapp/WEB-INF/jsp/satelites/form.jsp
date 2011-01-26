<%@ include file="/WEB-INF/jsp/includes.jsp" %>
<jsp:include page="/WEB-INF/jsp/header.jsp" >
	<jsp:param name="pageTitle" value="Satelites"/>
</jsp:include>

<c:choose>
	<c:when test="${satelite.new}"><c:set var="method" value="post"/></c:when>
	<c:otherwise><c:set var="method" value="put"/></c:otherwise>
</c:choose>

<div class="breadcrumb">
	<img src="<spring:url value="/static/images/icons/16x16/house.png" />">
	<a href="<spring:url value="/" />">Inicio</a>
	<img src="<spring:url value="/static/images/breadcrumb.png" />">
	<a href="<spring:url value="/admin" />">Administración</a>
	<img src="<spring:url value="/static/images/breadcrumb.png" />">
	${satelite}
</div>

<div id="tabs">
	<ul>
		<li class="ui-tabs-nav-item"><a href="#general">
		<span>
			<img src="<spring:url value="/static/images/icons/16x16/book.png" />" />
<c:choose>
	<c:when test="${satelite.new}">Nuevo Sat&eacute;lite</c:when>
	<c:otherwise>Editar ${satelite}</c:otherwise>
</c:choose>			
		</span>
		</a>
		</li>
  	</ul>
	<div id="general" class="ui-tabs-panel">
	<form:form modelAttribute="satelite" method="${method}">
	<table class="viewForm" style="border-spacing:0px">
		<tr>
			<td class="key" colspan="2">&nbsp;</td>
		</tr>
		<tr>
			<td class="key">
				<fmt:message key="satelite.name"/>
				<form:errors path="name" cssClass="errors"/>
			</td>
			<td>
				<form:input path="name" size="50" maxlength="50"/>
			</td>
		</tr>
		<tr>
			<td class="key">
				<fmt:message key="satelite.hostUrl"/>
				<form:errors path="hostUrl" cssClass="errors"/>
			</td>
			<td>
				<form:input path="hostUrl" size="50" maxlength="200"/>
			</td>
		</tr>
		<tr>
			<td class="key">
				<fmt:message key="satelite.user"/>
				<form:errors path="user" cssClass="errors"/>
			</td>
			<td>
				<form:input path="user" size="50" maxlength="50"/>
			</td>
		</tr>
		<tr>
			<td class="key">
				<fmt:message key="satelite.password"/>
				<form:errors path="password" cssClass="errors"/>
			</td>
			<td>
				<form:password path="password" size="50" maxlength="50"/>
			</td>
		</tr>
		<tr>
			<td class="key">
				<fmt:message key="satelite.passwordRetry"/>
				<form:errors path="passwordRetry" cssClass="errors"/>
			</td>
			<td>
				<form:password path="passwordRetry" size="50" maxlength="50"/>
			</td>
		</tr>
		<tr>
			<td class="key">
				<fmt:message key="satelite.lat"/>
				<form:errors path="lat" cssClass="errors"/>
			</td>
			<td>
				<form:input path="lat" size="50" maxlength="50"/>
			</td>
		</tr>
		<tr>
			<td class="key">
				<fmt:message key="satelite.lon"/>
				<form:errors path="lon" cssClass="errors"/>
			</td>
			<td>
				<form:input path="lon" size="50" maxlength="50"/>
			</td>
		</tr>
		<tr>
			<td class="key">
				<fmt:message key="satelite.activado"/>
				<form:errors path="activado" cssClass="errors"/>
			</td>
			<td>
				<form:checkbox path="activado"/>
			</td>
		</tr>		
	</table>
	</form:form>
	<button onclick="document.forms[1].submit();"><img src="<spring:url value="/static/images/icons/16x16/disk.png" />" />Guardar</button>
	<button onclick="document.location='<spring:url value="/admin#satelites" />'"><img src="<spring:url value="/static/images/icons/16x16/cancel.png" />" />Cancelar</button>
	</div>
</div>

<script type="text/javascript">

$(window).bind("load",function(){
$('#tabs').tabs({ fx: { height: 'toggle', opacity: 'toggle', duration: 'fast' } });
});

</script>

<%@ include file="/WEB-INF/jsp/footer.jsp" %>