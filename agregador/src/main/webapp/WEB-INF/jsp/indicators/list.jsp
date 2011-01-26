<%@ include file="/WEB-INF/jsp/includes.jsp" %>
<jsp:include page="/WEB-INF/jsp/header.jsp" >
	<jsp:param name="pageTitle" value="Indicadores"/>
</jsp:include>
<!-- Breadcrum -->
<div class="breadcrumb">
	<img src="<spring:url value="/static/images/icons/16x16/house.png" />">
	<a href="<spring:url value="/" />">Inicio</a>
	<img src="<spring:url value="/static/images/breadcrumb.png" />">
	<a href="<spring:url value="/indicators" />">Indicadores</a>
</div>
<div id="tabs" >
  <ul>
	<li class="ui-tabs-nav-item"><a href="#general"><span><img src="<spring:url value="/static/images/icons/16x16/book.png" />" /><fmt:message key="home.general"/></span></a></li>
	<li class="ui-tabs-nav-item"><a href="#servicios"><span><img src="<spring:url value="/static/images/icons/16x16/bricks.png" />" /><fmt:message key="home.servicios"/></span></a></li>  
	<li class="ui-tabs-nav-item"><a href="#demandas"><span><img src="<spring:url value="/static/images/icons/16x16/shield.png" />" /><fmt:message key="home.demandas"/></span></a></li>  
  </ul>
  <div id="general" class="ui-tabs-panel">
<form:form id="indicatorForm" modelAttribute="indicatorDto" method="post">
  <form:hidden path="pdf"/> 
  <table class="viewForm">
	<tr>
		<td><fmt:message key="provincia.name"/></td>
		<td>
			<form:select path="provincia" items="${provincias}"/>		
		</td>
	</tr>
	<tr>
		<td><fmt:message key="indicadores"/></td>
		<td>
			<form:checkboxes items="${indicadores}" path="indicadores" delimiter="<br/>"/>		
		</td>
	</tr>
  </table>
</form:form>  	 
  <button onclick="document.getElementById('pdf').value='false';document.getElementById('indicatorForm').submit();">
     <img src="<spring:url value="/static/images/icons/16x16/chart_pie.png" />" />Generar
  </button> 
  <button onclick="document.getElementById('pdf').value='true';document.getElementById('indicatorForm').submit();">
     <img src="<spring:url value="/static/images/icons/16x16/page_white_acrobat.png" />" />Generar PDF
  </button> 
  </div>

  <!-- Servicios -->

  <div id="servicios" class="ui-tabs-panel">
  
<form:form id="serviciosForm" action="serviciosIndicators" modelAttribute="indicatorDto" method="post">
  <form:hidden id="serviciosPdf" path="pdf"/> 
  <table class="viewForm">
	<tr>
		<td><fmt:message key="servicio.name"/></td>
		<td>
			<form:select path="servicio" items="${servicios}"/>		
		</td>
	</tr>
	<tr>
		<td><fmt:message key="sector.name"/></td>
		<td>
			<form:select path="sector" items="${sectores}"/>		
		</td>
	</tr>
	<tr>
		<td><fmt:message key="provincia.name"/></td>
		<td>
			<form:select path="provincia" items="${provincias}"/>		
		</td>
	</tr>
  </table>
</form:form>  	 
  <button onclick="generarServicios(false);">
     <img src="<spring:url value="/static/images/icons/16x16/chart_pie.png" />" />Generar
  </button> 
  <button onclick="generarServicios(true);">
     <img src="<spring:url value="/static/images/icons/16x16/page_white_acrobat.png" />" />Generar PDF
  </button>
   
  </div>

  <!-- Demandas -->

  <div id="demandas" class="ui-tabs-panel">
<form:form id="demandasForm" action="demandasIndicators" modelAttribute="indicatorDto" method="post">
  <form:hidden id="demandasPdf" path="pdf"/> 
  <table class="viewForm">
	<tr>
		<td><fmt:message key="servicio.name"/></td>
		<td>
			<form:select path="servicio" items="${servicios}"/>		
		</td>
	</tr>
	<tr>
		<td><fmt:message key="sector.name"/></td>
		<td>
			<form:select path="sector" items="${sectores}"/>		
		</td>
	</tr>
	<tr>
		<td><fmt:message key="provincia.name"/></td>
		<td>
			<form:select path="provincia" items="${provincias}"/>		
		</td>
	</tr>
  </table>
</form:form>  	 
  <button onclick="generarDemandas(false);">
     <img src="<spring:url value="/static/images/icons/16x16/chart_pie.png" />" />Generar
  </button> 
  <button onclick="generarDemandas(true);">
     <img src="<spring:url value="/static/images/icons/16x16/page_white_acrobat.png" />" />Generar PDF
  </button>
  </div>
	
</div>

<script type="text/javascript">
$(window).bind("load",function(){
$('#tabs').tabs({ fx: { height: 'toggle', opacity: 'toggle', duration: 'fast' } });
});

	function generarServicios(generarPdf){
		document.getElementById('serviciosPdf').value=generarPdf;
		document.getElementById('serviciosForm').submit();
	}

	function generarDemandas(generarPdf){
		document.getElementById('demandasPdf').value=generarPdf;
		document.getElementById('demandasForm').submit();
	}
</script>



<%@ include file="/WEB-INF/jsp/footer.jsp" %>
