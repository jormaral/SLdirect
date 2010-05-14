<?php
error_reporting(0);
?>
<div class="breadcrumb"><img src="media/icons/16x16/house.png"><a href="index.php">Inicio</a><img src="media/breadcrumb.png"><img src="media/icons/16x16/wrench.png"><a href="busqueda.php">Zona de Proveedores</a><img src="media/breadcrumb.png">Ficha de Empresa de <?php echo $context["organizacion"]->nombre?></div>
<div id="tabs">
            <ul>
                <li class="ui-tabs-nav-item"><a href="#general"><span><img src="media/icons/16x16/book.png" />Modificar Sede</span></a></li>
            </ul>
	<script type="text/javascript">
	$(window).bind("load",function(){
	$('#tabs').tabs({ fx: { height: 'toggle', opacity: 'toggle', duration: 'fast' } });
	});
</script>
<div id="sedes" class="ui-tabs-panel" style="display:none">
<table class="viewForm">
<?php
foreach ($context["organizacionSedes"] as $sede)
{

?>		

	<tr>
		<td >
			<span style="font-size:14px;"><?php echo $sede->provincia_id->nombre?></span><br />
			<span style="font-weight:normal">
				<img src="media/icons/16x16/house.png" />&nbsp;<?php echo $sede->direccion?>, <?php echo $sede->localidad?><br />
				<table style="width:100%;"><tr><td style="font-size:12px;font-weight:normal;width:50%;">
				<br /><img src="media/icons/16x16/phone.png" /> &nbsp;<?php echo $sede->telefonoContacto ? "<a href=\"dialto:".$sede->telefonoContacto."\">".$sede->telefonoContacto."</a>" : 'No Disponible'?>
				<br /><img src="media/icons/16x16/user_suit.png" /> &nbsp;<?php echo $sede->personaContacto ? $sede->personaContacto : 'No Disponible'?> &nbsp;
				<br /><img src="media/icons/16x16/email.png" /> &nbsp;<?php echo $sede->mailContacto ? "<a href=\"mailto:".$sede->mailContacto."\">".$sede->mailContacto."</a>" : 'No Disponible'?>
				</td><td style="font-size:12px;font-weight:normal">
				<img src="media/icons/16x16/user_female.png" />&nbsp;<?php echo $sede->mujeres ? $sede->mujeres : 0  ?>&nbsp;Mujeres <br /><img src="media/icons/16x16/user.png" />&nbsp;<?php echo $sede->hombres ? $sede->hombres : 0 ?>&nbsp;Hombres
				</td></tr>
				</table>
			</span><br /><br />
			<button onclick="document.location='empresaSedeDelete.php?organizacionSede_id=<?php echo $sede->id?>';return false;"><img src="media/icons/16x16/delete.png"> Eliminar Sede </button>
		</td>
		<td style="width:400px;padding:0px;">
			<div id="map_<?php echo $sede->id?>" style="width: 400px; height: 180px"></div>
			<script type="text/javascript">

				function z_<?php echo $sede->id?>(){
				if (GBrowserIsCompatible()) {
					var map = new GMap2(document.getElementById("map_<?php echo $sede->id?>"));
					map.setCenter(new GLatLng(<?php echo $sede->latitud?>,<?php echo $sede->longitud?>), 14);
					var point = new GPoint(<?php echo $sede->longitud?>,<?php echo $sede->latitud?>);
					var marker = new GMarker(point);
					map.addOverlay(marker);
				}}
				setTimeout('z_<?php echo $sede->id?>()',7000);
			</script>
		</td>
	</tr>
<?php
}
?>	
<tr>
	<td colspan="2" class="separator">Modificar Sede </td>
</tr>
<?php
	if (array_key_exists("sede_add_errors",$_REQUEST))
	{
			?>
<tr>
	<td colspan="2">
		<p  style="margin:5px;border:solid 4px red;color:red;bakckground:#ffffdd;padding:8px;padding-left:27px;background:url('media/icons/16x16/error.png');background-repeat:no-repeat;background-position:5px 10px; font-size:1.0em !important; text-align:justify"> No se pudo insertar la sede. Se encontraron errores en los campos <?php echo $_REQUEST["sede_add_errors"]?></p>
	</td>
</tr>
			<?php
	}
?>
<tr>
	<td>
		<form id="sedeMod" method="post" action="modificarSede.php?sede_id=<?php echo $_REQUEST["sede_id"]?>&organizacion_id=<?php echo $context["organizacion"]->id?>">
		<img src="media/icons/16x16/map.png"><select style="font-size:12px;" name="provincia_id">
		<?php
			$provincias = new provincia();
			foreach ($provincias->getList("1=1 order by nombre ASC") as $provincia){
				?>
				<option value="<?php echo $provincia->id?>" <?php echo ($context["sede"]->provincia_id->id == $provincia->id ? "selected" : "")?>><?php echo $provincia->nombre?></option>				
				<?php
			}
			print_r($context["sede"]);
		?>
		</select>
		<span style="font-weight:normal"><br />
			<img src="media/icons/16x16/house.png" />&nbsp;<input style="font-size:12px;" type="text" name="direccion" value="<?php echo $context["sede"]->direccion?>" onclick="if (this.value=='[Direccion]') this.value=''" /> / <input type="text" style="font-size:12px;"  name="localidad" value="<?php echo $context["sede"]->localidad?>" onclick="if (this.value=='[Localidad]') this.value=''" /><br />
			<br /><img src="media/icons/16x16/phone.png" /> &nbsp;<input type="text" style="font-size:12px;"  name="telefonoContacto" value="<?php echo $context["sede"]->telefonoContacto?>" onclick="if (this.value=='[Tel&eacute;fono Contacto]') this.value=''" />
			<br /><img src="media/icons/16x16/user_suit.png" /> &nbsp;<input type="text" style="font-size:12px;"  name="personaContacto" value="<?php echo $context["sede"]->personaContacto?>" onclick="if (this.value=='[Persona Contacto]') this.value=''" />
			<br /><img src="media/icons/16x16/email.png" /> &nbsp;<input type="text" style="font-size:12px;"  name="mailContacto" value="<?php echo $context["sede"]->mailContacto?>" onclick="if (this.value=='[e-Mail Contacto]') this.value=''" />
			<br /><img src="media/icons/16x16/user.png" /> &nbsp;<input type="text" style="font-size:12px;"  size="3" name="hombres" value="<?php echo $context["sede"]->hombres?>" onclick="if (this.value=='[H]') this.value=''" />
			<br /><img src="media/icons/16x16/user_female.png" /> &nbsp;<input type="text" style="font-size:12px;" size="3"  name="mujeres" value="<?php echo $context["sede"]->mujeres?>" onclick="if (this.value=='[M]') this.value=''" />

			<br /><img src="media/icons/16x16/world.png" />&nbsp; <input type="text" style="font-size:12px" name="latitud" value="<?php echo $context["sede"]->latitud?>" id="latitud" size="6"> N <input type="text" style="font-size:12px" name="longitud" value="<?php echo $context["sede"]->longitud?>"id="longitud" size="6"> O
		</span><br /><br />
		<button onclick="document.getElementById('sedeMod').submit()"><img src="media/icons/16x16/add.png"> Modificar Sede </button>
		</form>
	</td>
	<td style="width:400px;padding:0px;text-align:center">
		<div id="map_add" style="width: 400px; height: 180px"></div>
		<span style="font-size:9px;text-align:center">Haga click simple para marcar la localizaci&oacute;n de la sede en el mapa.<br />Haga click doble para hacer zoom en el mapa.</span>
		<script type="text/javascript">
			function z_createmap(){
			if (GBrowserIsCompatible()) {
				var map = new GMap2(document.getElementById("map_add"));
				var create_marker;
				map.setCenter(new GLatLng(40.145289,-2.614746), 4);
				map.addControl(new GSmallMapControl());
				GEvent.addListener(map, "click" , function (overlay, latlng) {
					map.clearOverlays();			
					point = new GPoint(latlng.lng(),latlng.lat());
					create_marker = new GMarker(point);
					setTimeout("map.addOverlay(create_marker)",500);
					$("#latitud").attr("value",latlng.lat());
					$("#longitud").attr("value",latlng.lng());
				});
				GEvent.addListener(map, "zoomend" , function (overlay, latlng) {
					map.clearOverlays();
				});
			}
		}
		setTimeout("z_createmap()",2500);
		</script>
	</td>
</tr>
</table>
</div>


</div>
<script type="text/javascript">

$(window).bind("load",function(){
$('.ui-tabs-panel').css("display","");
$('#tabs').tabs({ fx: { height: 'toggle', opacity: 'toggle', duration: 'fast' } });
});
</script>
<?php flush();?>
<script src="http://maps.google.com/maps?file=api&amp;v=2&amp;key=<?php global $settings; echo $settings["gmaps.key"]?>" type="text/javascript"></script>
