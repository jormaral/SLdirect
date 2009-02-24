<?php
/**
* SLDirect
* Directorio de Empresas, Servicios y Demandas
* ----------------------------------------------------------------------------------
* Copyright  (C) 2008-2009 CENATIC, Centro Nacional
*                          de Referencia en TICs de
*                          Fuentes Abiertas.
* ----------------------------------------------------------------------------------
*
* This file is part of SLDirect
*
* SLDirect is free software; you can redistribute it and/or modify
* it under the terms of the GNU General Public License as published by
* the Free Software Foundation; version 2 of the License.
*
* SLDirect is distributed in the hope that it will be useful,
* but WITHOUT ANY WARRANTY; without even the implied warranty of
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
* GNU General Public License for more details.
*
* You should have received a copy of the GNU General Public License
* along with bbPress; if not, write to the Free Software
* Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA
*
* ----------------------------------------------------------------------------------
*
* @copyright 2008-2009 CENATIC
* @license   http://www.gnu.org/licenses/gpl.txt GNU General Public License v2
* @link      http://www.cenatic.es
*
**/

?>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>		
		<link rel="stylesheet" href="css/style.css" type="text/css" media="screen" />
		<link rel="stylesheet" href="css/print_style.php" type="text/css" media="print"/>
		<link rel="stylesheet" href="lib/jquery.treeview/jquery.treeview.css" />
		<link rel="stylesheet" href="lib/jquery.treeview/red-treeview.css" />
		<link rel="stylesheet" href="lib/jquery.treeview/screen.css" />
<!--		<link rel="stylesheet" href="lib/jquery.ui/themes/flora/flora.all.css" type="text/css" media="screen" title="Flora (Default)" />-->
		<link rel="stylesheet" href="http://ui.jquery.com/applications/themeroller/css/app_screen.css" type="text/css" media="screen">
		<link rel="stylesheet" href="lib/jquery.ui/themes/cenatic/cenatic.css" type="text/css" media="screen" title="Cenatic SLDirect" />
		<script src="lib/jquery/jquery.js"></script>
		<script src="lib/jquery.ui/jquery.ui.all.js"></script>
		<script src="lib/jquery.treeview/jquery.treeview.js"></script>
		<script>
		$(window).bind("load",function(){
				$("#menu").accordion();
		});
		</script>

		<title><?php echo $title?></title>
	</head>
	<body style="background-image:url(media/back.png);background-repeat:repeat-x;background-color:#2b2b2b">

			<div style="position:absolute;left:50%;margin-left:-487px;height:100%;">
				<a href="index.php"><img src="media/logo.png" style="position:absolute;top:2px;left:24px;" alt="SLDirect: El directorio del Software Libre"></a>
			<table style="border-spacing:0px;height:100%;" cellspacing="0" border="0">
				<tr>
					<td style="text-align:right;vertical-align:top;padding-left:0px;padding-right:0px;background:url(media/leftborder.png);background-repeat:no-repeat;width:22px;">
						<div style="width:22px;"></div>
					</td>
					<td style="padding-left:0px;padding-right:0px;vertical-align:top;padding-top:13px;background:none;" rowspan="2">
							<table style="width:935px;border-spacing:0px;height:100%;" cellspacing="0">
								<tr>
									<td style="background:url(media/topdegrad.png);height:80px;padding:0px;padding-left:420px;text-align:right;padding-right:32px">
										<form style="margin:0px;" action="busquedaTexto.php" method="GET"><input type="text" id="query" name="query" style="padding-left:6px;width:420px;font-size:16px;height:26px;border:outset 1px #565656;border-right:none;-moz-border-radius:5px 0px 0px 5px;vertical-align:middle;"><button onclick="document.location='busquedaTexto.php?query='+ $('#query').attr('value');" style="background:#dddddd;border:outset 1px #565656;height:30px;vertical-align:middle;-moz-border-radius:0px 5px 5px 0px"><img src="media/icons/16x16/zoom.png" alt="Buscar"/></button></form>
									</td>
								</tr>
								<tr>
									<td style="background:url(media/top2.png);height:32px;padding-left:270px;padding-top:0px;padding-bottom:0px">
										<a href="busqueda.php" style="border:none"><img src="media/topbuscador.png" alt="Busqueda de Empresas" style="margin:0px;vertical-align:middle;"/></a>
										<a href="busquedaGeografica.php" style="border:none"><img src="media/top2loc.png"  alt="por Localizacion"  style="margin:0px;vertical-align:middle"/></a>
										<a href="busquedaCapacidades.php" style="border:none" ><img src="media/top2serv.png" alt="por Servicio"  style="margin:0px;vertical-align:middle"/></a>
										<a href="busquedaDemanda.php" style="border:none" ><img src="media/top2demand.png" alt="por Demanda"  style="margin:0px;vertical-align:middle"/></a>
									</td>
								</tr>
								<tr style="">
									<td style="background:url(media/top3.png);padding-left:273px;overflow:hidden;padding-top:0px;padding-bottom:0px;">
										<a href="session_redirect.php" style="border:none"><img src="media/topproveedores.png" alt="Zona Proveedores" style="vertical-align:middle;margin-right:15px;"/></a>
										<a href="registrar.php" style="border:none"><img src="media/top3register.png" alt="Registrar Empresa" style="margin:0px;vertical-align:middle"/></a>
										<a href="login.php" style="border:none"><img src="media/top3login.png" alt="Iniciar Sesi&oacute;n" style="margin:0px;vertical-align:middle"/></a>
									</td>
								</tr>
								<tr style="background:white;">
									<td style="background:white;height:100%;vertical-align:top;padding:32px;">

											
