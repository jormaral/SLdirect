<?php
/**
* SLDirect
* Directorio de Empresas, Servicios y Demandas
* ----------------------------------------------------------------------------------
* Copyright  (C) 2008-2009 CENATIC, Centro Nacional
                           de Referencia en TICs de
                           Fuentes Abiertas.
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
* PHP version 5 ONLY. GPL2 ONLY.
* ----------------------------------------------------------------------------------
*
* @copyright 2008-2009 CENATIC
* @license   http://www.gnu.org/licenses/gpl.txt GNU General Public License v2
* @link      http://www.cenatic.es
*
**/

chdir("..");
include ("include.php");

function admin_header(){
	ob_start();
	?>
	<html>
		<head>
			<link rel="stylesheet" href="style.css" />
		</head>
		<body>
			<div class="header">
				<b> &nbsp;&nbsp; SLDirect :: Administraci&oacute;n</b><br />
				<a href="capacidades.php"><img src="../media/icons/16x16/wrench.png" />Capacidades</a>
				<a href="sectores.php"><img src="../media/icons/16x16/bricks.png" />Sectores</a>
				<a href="clasificaciones.php"><img src="../media/icons/16x16/building.png" />Clasificaciones</a>
				<a href="asociaciones.php"><img src="../media/icons/16x16/group.png" />Asociaciones</a>				
				<a href="formasJuridicas.php"><img src="../media/icons/16x16/shield.png" />Formas Jur&iacute;dicas</a>			
				<a href="provincias.php"><img src="../media/icons/16x16/map.png" />Provincias</a>
				<a href="organizaciones.php"><img src="../media/icons/16x16/book.png" />Organizaciones</a>	
			</div>	
			<div class="body">
	<?php
}

function admin_footer(){
	?>
			</div>
		</body>
	</html>
	<?php
	ob_end_flush();
}

?>
