<%@ include file="/WEB-INF/jsp/includes.jsp" %>
<jsp:include page="/WEB-INF/jsp/header.jsp" >
	<jsp:param name="pageTitle" value="Editar Forma Jurídica"/>
</jsp:include>

<c:choose>
	<c:when test="${formaJuridica.new}"><c:set var="method" value="post"/></c:when>
	<c:otherwise><c:set var="method" value="put"/></c:otherwise>
</c:choose>

<div class="breadcrumb">
	<img src="<spring:url value="/static/images/icons/16x16/house.png" />">
	<a href="<spring:url value="/" />">Inicio</a>
	<img src="<spring:url value="/static/images/breadcrumb.png" />">
	<a href="<spring:url value="/admin" />">Administración</a>
	<img src="<spring:url value="/static/images/breadcrumb.png" />">
	${formaJuridica}
</div>

<div id="tabs">
	<ul>
		<li class="ui-tabs-nav-item"><a href="#general">
		<span>
			<img src="<spring:url value="/static/images/icons/16x16/book.png" />" />
<c:choose>
	<c:when test="${formaJuridica.new}">Nueva Forma Jur&iacute;dica</c:when>
	<c:otherwise>Editar ${formaJuridica}</c:otherwise>
</c:choose>			
		</span>
		</a>
		</li>
  	</ul>
	<div id="general" class="ui-tabs-panel">
	<form:form modelAttribute="formaJuridica" method="${method}">
	<table class="viewForm" style="border-spacing:0px">
		<tr>
			<td class="key" colspan="2">&nbsp;</td>
		</tr>
		<tr>
			<td class="key"><fmt:message key="formajuridica.name"/></td>
			<td>
				<form:input path="name" size="50" maxlength="50"/>
			</td>
		</tr>
	</table>
	</form:form>
	<button onclick="document.forms[1].submit();"><img src="<spring:url value="/static/images/icons/16x16/disk.png" />" />Guardar</button>
	<button onclick="document.location='<spring:url value="/admin#fj" />'"><img src="<spring:url value="/static/images/icons/16x16/cancel.png" />" />Cancelar</button>
	</div>
</div>

<script type="text/javascript">

$(window).bind("load",function(){
$('#tabs').tabs({ fx: { height: 'toggle', opacity: 'toggle', duration: 'fast' } });
});

</script>

<%@ include file="/WEB-INF/jsp/footer.jsp" %>