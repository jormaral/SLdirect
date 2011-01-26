<%@ include file="/WEB-INF/jsp/includes.jsp" %>
<%@ include file="/WEB-INF/jsp/header.jsp" %>
<div id="content" class="clearfix">
	<div id="main">
		<h2>Buscar Agentes:</h2>
		<spring:url value="/agentes" var="formUrl"/>
		<form:form modelAttribute="agente" action="${fn:escapeXml(formUrl)}" method="get">
		  <table>
		    <tr>
		      <th>
		        Agente: <form:errors path="*" cssClass="errors"/>
		        <br/> 
		        <form:input path="name" size="30" maxlength="50" />
		      </th>
		    </tr>
		    <tr>
		      <td><p class="submit"><input type="submit" value="Buscar Agente"/></p></td>
		    </tr>
		  </table>
		</form:form>
		<br/>
		<a href='<spring:url value="/agentes/new" htmlEscape="true"/>'>Nuevo Agente</a>
	</div>
	<div id="nav">
		<%@ include file="/WEB-INF/jsp/nav.jsp" %>
	</div>
</div>
<p>&nbsp;</p>
<p>&nbsp;</p>

<%@ include file="/WEB-INF/jsp/footer.jsp" %>