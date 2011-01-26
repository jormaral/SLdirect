<%@ include file="/WEB-INF/jsp/includes.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
	<style type="text/css" media="screen">
		@import url("<spring:url value="/static/styles/style.css" htmlEscape="true" />");
	</style>
	<link rel="stylesheet" href="http://ui.jquery.com/applications/themeroller/css/app_screen.css" type="text/css" media="screen">
	<link type="text/css" href="<spring:url value="/static/lib/jquery.ui/css/ui-lightness/jquery-ui-1.8rc3.custom.css"/>" rel="stylesheet" />	
	<script type="text/javascript" src="<spring:url value="/static/lib/jquery.ui/js/jquery-1.4.2.min.js"/>"></script>
	<script type="text/javascript" src="<spring:url value="/static/lib/jquery.ui/development-bundle/ui/i18n/jquery.ui.datepicker-es.js"/>"></script>	
	<script type="text/javascript" src="<spring:url value="/static/lib/jquery.ui/js/jquery-ui-1.8rc3.custom.min.js" />"></script>
    <script type="text/javascript" src="http://maps.google.com/maps?file=api&amp;v=2&amp;key=<fmt:message key="mapa.key"/>"></script>
	<script type="text/javascript" src="<spring:url value="/static/lib/jquery.jcarousellite.pauseOnHover.js" />" ></script>     
    
    <script type="text/javascript">
   
    function initialize() {
      if (GBrowserIsCompatible()) {
        var map = new GMap2(document.getElementById("map_canvas"));
        map.setCenter(new GLatLng(40.416691, -3.700345), 6);       
        
        var bounds = map.getBounds();
        var southWest = bounds.getSouthWest();
        var northEast = bounds.getNorthEast();
        var lngSpan = northEast.lng() - southWest.lng();
        var latSpan = northEast.lat() - southWest.lat();
		        
     <c:forEach var="satelite" items="${satelites}">
        var point = new GLatLng(${satelite.lat},${satelite.lon}); 
        var marker = new GMarker(point);
        marker.value = "${satelite}";
		GEvent.addListener(marker,"click", function() {
	  		window.location="<spring:url value="/satelites/${satelite.id}/orgs"/>"; 
		});

        map.addOverlay(marker);

     </c:forEach>
      }
    }
    </script>
	<title>CENATIC - Mapa de Sat&eacute;lites</title>
</head>

  <body style="background-image:url(<spring:url value="/static/images/back.png" />);background-repeat:repeat-x;background-color:#2b2b2b" onload="initialize()" onunload="GUnload()">
	<div style="position:absolute;left:50%;margin-left:-487px;height:100%;">
		<a href="<spring:url value="/" />">
			<img src="<spring:url value="/static/images/logo.png" />" style="position:absolute;top:2px;left:24px;" alt="<fmt:message key="sldirect.title"/>">
		</a>
		<table style="border-spacing:0px;height:100%;" cellspacing="0" border="0">
		<tr>
			<td style="text-align:right;vertical-align:top;padding-left:0px;padding-right:0px;background:url(<spring:url value="/static/images/leftborder.png"/>);background-repeat:no-repeat;width:22px;">
				<div style="width:22px;"></div>
			</td>
			<td style="padding-left:0px;padding-right:0px;vertical-align:top;padding-top:13px;background:none;" rowspan="2">
				<table style="width:935px;border-spacing:0px;height:100%;" cellspacing="0">
				<tr>
					<td style="background:url(<spring:url value="/static/images/topdegrad.png"/>);height:80px;padding:0px;padding-left:420px;text-align:right;padding-right:32px">
						<form style="margin:0px;" action="<spring:url value="/busquedaTexto"/>" method="GET">
							<input type="text" id="q" name="q" style="padding-left:6px;width:420px;font-size:16px;height:26px;border:outset 1px #565656;border-right:none;-moz-border-radius:5px 0px 0px 5px;vertical-align:middle;">
							<button onclick="document.location='<spring:url value="/busquedaTexto"/>?q='+ $('#query').attr('value');" style="background:#dddddd;border:outset 1px #565656;height:30px;vertical-align:middle;-moz-border-radius:0px 5px 5px 0px">
								<img src="<spring:url value="/static/images/icons/16x16/zoom.png"/>" alt="<fmt:message key="search"/>"/>
							</button>
						</form>
						<div class="logoutBox">
							<sec:authorize access="isAuthenticated()">
								<fmt:message key="home.welcome"/>
								<sec:authentication property="principal.username"/>
								<button onCLick="location='<spring:url value="/logout"/>'">
									<fmt:message key="home.logout"/>
								</button>
							</sec:authorize>											 
						</div>
					</td>
				</tr>
				<tr>
					<td style="background:url(<spring:url value="/static/images/top2.png"/>);height:32px;padding-left:270px;padding-top:0px;padding-bottom:0px">
						<a href="<spring:url value="/busqueda"/>" style="border:none"><img src="<spring:url value="/static/images/topbuscador.png"/>" alt="<fmt:message key="search.enterprise"/>" style="margin:0px;vertical-align:middle;"/></a>
						<a href="<spring:url value="/busquedaGeo"/>" style="border:none"><img src="<spring:url value="/static/images/top2loc.png"/>"  alt="<fmt:message key="search.localization"/>"  style="margin:0px;vertical-align:middle"/></a>
						<a href="<spring:url value="/busquedaServicio"/>" style="border:none" ><img src="<spring:url value="/static/images/top2serv.png"/>" alt="<fmt:message key="search.service"/>"  style="margin:0px;vertical-align:middle"/></a>
						<a href="<spring:url value="/busquedaDemanda"/>" style="border:none" ><img src="<spring:url value="/static/images/top2demand.png"/>" alt="<fmt:message key="search.demand"/>"  style="margin:0px;vertical-align:middle"/></a>
					</td>
				</tr>
				<tr style="">
					<td style="background:url(<spring:url value="/static/images/top3.png"/>);padding-left:273px;overflow:hidden;padding-top:0px;padding-bottom:0px;">
<%--					
						<a href="#" style="border:none"><img src="<spring:url value="/static/images/topproveedores.png"/>" alt="<fmt:message key="home.proveedores"/>" style="vertical-align:middle;margin-right:15px;"/></a>
						<a href="<spring:url value="/registrar"/>" style="border:none"><img src="<spring:url value="/static/images/top3register.png"/>" alt="<fmt:message key="home.register"/>" style="margin:0px;vertical-align:middle"/></a>
						<a href="<spring:url value="/login"/>" style="border:none"><img src="<spring:url value="/static/images/top3login.png"/>" alt="<fmt:message key="home.login"/>" style="margin:0px;vertical-align:middle"/></a>
 --%>						
					</td>
				</tr>
				<tr style="background:white;">
					<td style="background:white;height:100%; height: 600px; vertical-align:top;padding:32px;">
						<div id="map_canvas" style="width: 100%; height: 600px; padding:0px"></div>
					</td>
				</tr>
				<tr>
					<td style="background:#525252;padding:6px;color:#929292;font-weight:bold">
						<a href="<spring:url value="/terminos"/>" style="color:black;border-bottom-color:black"><fmt:message key="footer.terms"/></a>  | 
						<a href="<spring:url value="/privacidad"/>" style="color:black;border-bottom-color:black"><fmt:message key="footer.priv"/></a> | 
						<a href="http://www.cenatic.es" style="color:black;border-bottom-color:black"><fmt:message key="footer.about"/> </a>
					</td>
				</tr>
				<tr>
					<td style="background:#222222;padding:6px;color:#929292;font-weight:bold;">
						<fmt:message key="footer.copyright"/>
					</td>
				</tr>
				</table>
				<br /><br />
			</td>
			<td style="text-align:left;vertical-align:top;padding-top:8px;padding-left:0px;padding-right:0px;background:url(<spring:url value="/static/images/rightborder.png"/>);background-position:-1px 8px;background-repeat:no-repeat;width:22px;">
			</td>
		</tr>	
		</table>
	</div>	
  </body>
</html>