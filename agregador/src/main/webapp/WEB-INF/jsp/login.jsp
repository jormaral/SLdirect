<%@ include file="/WEB-INF/jsp/includes.jsp" %>

<jsp:include page="/WEB-INF/jsp/header.jsp" >
	<jsp:param name="pageTitle" value="Login"/>
</jsp:include>

<div class="breadcrumb">
	<img src="<spring:url value="/static/images/icons/16x16/house.png"/>">
	<a href="<spring:url value="/"/>">
	<fmt:message key="home"/></a>
	<img src="<spring:url value="/static/images/breadcrumb.png"/>">
	<img src="<spring:url value="/static/images/icons/16x16/wrench.png"/>">
	<a href="<spring:url value="/busqueda"/>">
	<fmt:message key="home.proveedores"/></a>
	<img src="<spring:url value="/static/images/breadcrumb.png"/>">
	<fmt:message key="home.login"/>
</div>
	<table style="width:100%">
	<tr>
		<td colspan="2" style="width:80%;vertical-align:top;padding-right:30px">
			<img src="<spring:url value="/static/images/loginTitle.png"/>" alt="<fmt:message key="search.geographic"/>" />
			<p><fmt:message key="home.login.msg"/></p>
			<form method="post" action="j_spring_security_check" style="border:solid 2px red" >
			<br />
			<br />
				<c:if test="${not empty param.login_error}">
				<p  style="margin:5px;border:solid 4px red;color:red;bakckground:#ffffdd;padding:8px;padding-left:27px;background:url('static/images/icons/16x16/error.png');background-repeat:no-repeat;background-position:5px 10px; font-size:1.0em !important; text-align:justify"> Nombre de Usuario o Contrase&ntilde;a no v&aacute;lidos.</p>
    			</c:if>
						
				<div style="text-align:center">
				<table class="viewForm">
					<tr>
						<td>
							<img src="<spring:url value="/static/images/icons/16x16/user.png"/>" alt="<fmt:message key="home.login.user"/>"/> 
							<input id="j_username" name="j_username" size="20" maxlength="50" type="text" />
							<br /><br />
						</td>
					</tr>
					<tr>
						<td>
							<img src="<spring:url value="/static/images/icons/16x16/shield.png"/>" alt="<fmt:message key="home.login.password"/>"/> 
							<input id="j_password" name="j_password" size="20" maxlength="50" type="password"/>
							<br /><br />
						</td>
					</tr>
					<tr>
						<td>
							<button style="width:190px;" onclick="document.forms[1].submit();">
								<img src="<spring:url value="/static/images/icons/16x16/arrow_right.png" />"/>
								<fmt:message key="home.login"/>							
							</button>
							<br />
							<button style="width:190px;font-size:10px;background:#fafafa;color:#bbbbbb;margin-top:12px;" onclick="if (document.getElementById('j_username').value == ''){ alert ('<fmt:message key="home.login.msg"/>'); return false; } else { document.forms[1].action='lostpw'; document.forms[1].submit(); return false}">
								<img src="<spring:url value="/static/images/icons/16x16/arrow_rotate_clockwise.png"/>" /><fmt:message key="home.login.password.restore"/> 
							</button>
						</td>
					</tr>
				</table>
				</div>
				<br />
				<br />
			</form>
		</td>
	</tr>
</table>

<%@ include file="/WEB-INF/jsp/footer.jsp" %>