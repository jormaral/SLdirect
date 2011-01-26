<%@ include file="/WEB-INF/jsp/includes.jsp" %>
<jsp:include page="/WEB-INF/jsp/header.jsp" >
	<jsp:param name="pageTitle" value="Organizaciones del Satelite"/>
</jsp:include>

<script src="http://maps.google.com/maps?file=api&amp;v=2&amp;key=<fmt:message key="mapa.key"/>" type="text/javascript"></script>
<script src="<spring:url value="/static/lib/jquery.jcarousellite.pauseOnHover.js" />" type="text/javascript"></script> 
	<table id="content" style="margin-right:12px;">
		<tr>
			<td style="width: 250px;padding-top:12px;">
			  <div style="width:100%;-moz-border-radius:5px;width:100%;padding:1px;background:#6b6fa6;color:white;height:400px;">
			    <h2 style="text-align:center;margin:0px;font-size:1.8em;letter-spacing:-2px;font-weight:normal;color:#ffffff">Empresas</h2>

				<div style="-moz-border-radius:5px;background: #fff;color:#000;margin: 4px 0px 0px 0px;height: 373px;overflow:auto">
<c:forEach var="organizacion" items="${organizaciones}" varStatus="status">
     <spring:url value="/orgs/{orgId}" var="editUrl">
     	<spring:param name="orgId" value="${organizacion.id}" />
     </spring:url>
     
	<div onclick='document.location="${fn:escapeXml(editUrl)}"' class="searchResultTitle" style="${status.index % 2 != 0 ? 'background-color:#cfcffa' : ''};cursor:pointer;padding:4px;margin:0px;font-weight:bold;font-size:12px;border-bottom: 1px #8b8fc6 solid">
		${organizacion}
	</div>
 </c:forEach>
				</div>
			  </div>
			</td>
			<td colspan="2" style="width:750px;padding-top:12px;">
					<div style="width:100%;-moz-border-radius:5px;width:100%;background:#8b8fc6;color:white;height:400px;">
						<div style="padding:12px;">
						<table style="width:100%">
							<tr>
								<td>
									<input type="text" name="query" id="query2" style="-moz-border-radius:5px 0px 0px 5px;font-size:18px;border:inset 1px;width:100%;padding:3px;" />
								</td>
								<td style="width:32px;">
									<button style="-moz-border-radius:0px 5px 5px 0px;padding:5px;border:outset 1px;" onclick="document.location='busquedaTexto?q='+ $('#query2').attr('value');" >
										<img src="<spring:url value="/static/images/icons/16x16/zoom.png" />">
									</button>
								</td>
							</tr>
						</table>
					<div id="map" style="-moz-border-radius:5px;height:310px;border:inset 1px;margin:3px;margin-top:12px;background:white;color:black">
					</div>
				</div>
			</td>
		</tr>
	</table>
	     <spring:url value="/orgSede/{sateliteId}" var="sedeUrl">
	     	<spring:param name="sateliteId" value="${satelite.id}" />
	     </spring:url>
	     <spring:url value="/orgs" var="orgUrl" />
	
	<script type="text/javascript">
	var points = Array();
	var map = new GMap2(document.getElementById("map"));
	$(window).bind("load",function(){
		
		jQuery.getJSON("${fn:escapeXml(sedeUrl)}",onReady);
	});
	function onReady(data,scope){
		if (GBrowserIsCompatible()) {
			map.clearOverlays();			
			map.addControl(new GSmallMapControl());
			map.setCenter(new GLatLng(40.416691, -3.700345), 5);
		}
		points = Array();
		$.each(data, function(i,item){
			latlng = new GLatLng(item.latitud,item.longitud);
			point = new GPoint(item.longitud,item.latitud);
			var marker = new GMarker(point);
			points.push( latlng);
			map.addOverlay(marker);
			GEvent.addListener(marker, "click", function() {
				document.location='${fn:escapeXml(orgUrl)}/' + item.id;
//			    		marker.openInfoWindowHtml(item.html);
			});
		});
	}

	function loadAll(id){
		jQuery.getJSON("orgSede/all",onReady);
	}
	
	
	function loadProvincia(id){
		jQuery.getJSON("orgSede/provincia/"+id,onReady);
	}
	
	function fitMap() {
	   var bounds = new GLatLngBounds();
	   for (var i=0; i< points.length; i++) {
	      bounds.extend(points[i]);
	   }
	   map.setZoom(map.getBoundsZoomLevel(bounds));
	   map.setCenter(bounds.getCenter());
	}
		
	</script>	
	
<%@ include file="/WEB-INF/jsp/footer.jsp" %>