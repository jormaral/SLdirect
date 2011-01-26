<%@ include file="/WEB-INF/jsp/includes.jsp" %>
<jsp:include page="/WEB-INF/jsp/header.jsp" >
	<jsp:param name="pageTitle" value="Login"/>
</jsp:include>

<div class="breadcrumb">
	<img src="static/images/icons/16x16/house.png">
		<a href="/">Inicio</a>
	<img src="static/images/breadcrumb.png">
	<img src="static/images/icons/16x16/wrench.png">
	<a href="busqueda">Zona de Proveedores</a>
	<img src="static/images/breadcrumb.png">Registrar Empresa</div>
<table>
	<tr>
		<td>
			<%--
			<?php
			if (count($context["errors"]) > 0)
			{
				?>
				<p  style="margin:5px;border:solid 4px red;color:red;bakckground:#ffffdd;padding:8px;padding-left:27px;background:url('static/images/icons/16x16/error.png');background-repeat:no-repeat;background-position:5px 10px; font-size:1.0em !important; text-align:justify"> Se han encontrado errores en el formulario y no puede procesarse el registro.</p>
				<?php
			}
			?>
			 --%>
			<img src="static/images/registrarTitle.png" alt="Registrar Empresa" />
			<p>
				Cualquier empresa que proporcione servicios basados en Software Libre puede registrarse en el directorio SLDirect.<br /><br />
				Para registrar su empresa en SLDirect necesitamos unos datos b&aacute;sicos con los cuales conformaremos la ficha inicial y la identidad de acceso al directorio 
				de manera que una vez registrados, usted pueda continuar rellenando los datos de su perfil.
				<form method="post" action="registrar?do=1">
				<table class="viewForm" style="margin:12px;">
					<tr>
						<td class="key" style="font-weight:bold">
							Nombre de la Organizaci&oacute;n
							
						</td>
						<td colspan="2">
							<input type="text" name="nombre" /><br />
							
							<span style="font-size:9px">
								Nombre de la organizaci&oacute;n tal y como aparece en las escrituras de constituci&oacute;n de la misma, o bien marca comercial reconocible de la misma.		
							</span>
						</td>
					</tr>
					<tr>
						<td class="key" style="font-weight:bold">
							Codigo de Identificaci&oacute;n Fiscal
						</td>
						<td colspan="2">
							<input type="text" name="cif" /><br />
							<span style="font-size:9px">
								CIF o NIF de la organizaci&oacute;n a insertar en el directorio.
							</span>
						</td>
					</tr>
					<tr>
						<td class="key" style="font-weight:bold">
							Direcci&oacute;n de Correo Electr&oacute;nico
						</td>
						<td colspan="2">
							<input type="text" name="mail" size="20"  /><br />
							<span style="font-size:9px">
								Direcci&oacute;n a la que ser&aacute;n enviadas las credenciales de inicio de sesi&oacute;n iniciales al directorio.
							</span>
						</td>
					</tr>
				</table>
				<table class="viewForm" style="margin:12px;width:100%;margin-top:0px">
					<tr>
						<td class="key" style="width:95%" colspan="2">
							He leido y acepto los <a href="terminos">t&eacute;rminos y condiciones de uso</a> de SLDirect
						</td>
						<td>
							<input id="terms" type="checkbox" name="terms" onchange="if ($('#terms').attr('checked') && $('#privacy').attr('checked')) {  $('#submitter').attr('disabled','') ; $('#submitter').disabled = false; $('#disclaimer').css('display','none');} else { $('#submitter').attr('disabled','disabled') ; $('#submitter').disabled = true;  $('#disclaimer').css('display',''); }"/>
						</td>
					</tr>
					<tr>
						<td class="key" style="width:95%" colspan="2">
							He leido y acepto la <a href="privacidad">pol&iacute;tica de privacidad</a> de SLDirect
						</td>
						<td>
							<input id="privacy" type="checkbox" name="privacy" onchange="if ($('#terms').attr('checked') && $('#privacy').attr('checked')) {  $('#submitter').attr('disabled','') ; $('#submitter').disabled = false; $('#disclaimer').css('display','none');} else { $('#submitter').attr('disabled','disabled') ; $('#submitter').disabled = true;  $('#disclaimer').css('display',''); }"/>
						</td>
					</tr>
					<tr>
						<td colspan="3" style="text-align:center">
							<p id="disclaimer" style="margin:5px;border:solid 4px;padding:8px;padding-left:27px;background:url('static/images/icons/16x16/error.png');background-repeat:no-repeat;background-position:5px 10px; font-size:1.0em !important; text-align:justify"> Debe de aceptar los t&eacute;rminos y condiciones de uso y la pol&iacute;tica de privacidad para activar el bot&oacute;n de registro.</p>
							<input id="submitter" disabled type="submit" style="font-size:1.8em" value="Registrar Empresa en SLDirect">
						</td>
					</tr>
					<tr>
						<td colspan="3" class="key" style="font-size:0.9em; text-align:justify">
						Por favor, compruebe los t&eacute;rminos y condiciones de uso, as&iacute; como la pol&iacute;tica de privacidad del sitio antes de confirmar el registro en SLDirect. Al hacer click en "Registrar empresa en SLDirect" usted autoriza a Cenatic a enviarle un correo electr&oacute;nico de confirmaci&oacute;n. 
						Consulte la pol&iacute;tica de privacidad y los t&eacute;rminos y condiciones de uso para conocer los m&eacute;todos mediante los cuales acceder a su derecho de comprobaci&oacute;n, rectificaci&oacute;n y cancelaci&oacute;n de datos personales.
						</td>
					</tr>
					
				</table>

				</form>
			</p>
		</td>
		<td style="vertical-align:bottom;padding-top:0px">
			<img src="<spring:url value="/static/images/procesoRegistro.png"/>" />
		</td>
	</tr>
</table>


<script>
		$(window).bind("load",function(){
			$.each($("input"), function(i,item){
				item.checked = false;
			});
			
			$('#submitter').attr("disabled","disabled");
			$('#submitter').disabled=true;
		});
</script>
	
<%@ include file="/WEB-INF/jsp/footer.jsp" %>	