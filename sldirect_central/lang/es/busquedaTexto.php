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

	function hi($text)
	{
		return str_ireplace($_REQUEST["query"],"<span class=\"highlight\">".$_REQUEST["query"]."</span>",$text);
		
	}

?>
	<div class="breadcrumb"><img src="media/icons/16x16/house.png"><a href="index.php">Inicio</a><img src="media/breadcrumb.png">B&uacute;squeda por Texto Libre</div>
	
	<table style="margin-right:12px;width:100%;">
			<td colspan="3" style="padding-top:12px;">
					<div style="width:100%;-moz-border-radius:5px;width:100%;background:#8b8fc6;color:white;height:60px;">
						<div style="padding:12px;">
						<table style="width:100%">
							<tr>
								<td>
									<form style="margin:0px;" action="busquedaTexto.php" method="GET"><input type="text" id="query2" name="query" style="-moz-border-radius:5px 0px 0px 5px;font-size:18px;border:inset 1px;width:100%;padding:3px;" value="<?php echo $_REQUEST["query"]?>"/></form>
								</td>
								<td style="width:32px;">
									<button style="-moz-border-radius:0px 5px 5px 0px;padding:5px;border:outset 1px;" onclick="document.location='busquedaTexto.php?query='+ $('#query2').attr('value');" ><img src="media/icons/16x16/zoom.png"></button>
								</td>
							</tr>
						</table>
				</div>
			</td>
		</tr>
	</table>
	<h1><?php echo count($context["results"])?> Empresas encontradas</h1>
	<?php
	foreach ($context["results"] as $organizacion)
	{
		?><a href="organizacion.php?organizacion_id=<?php echo $organizacion->id?>">
			<div class="searchResult">
				<span class="searchResultTitle"><?php echo hi($organizacion->nombre)?></span>
				<span class="searchResultDescription"><?php echo hi($organizacion->descripcion)?></span>			
			</div>
			</a>
		<?php
	}
	?>
	<div>
		<p>
			Las b&uacute;squedas en el directorio SLDirect se realizan en base a los datos de Raz&oacute;n Social, Descripci&oacute;n, Partners, Participaci&oacute;n en I+D, Relaciones con la Comunidad, Personas de Contacto, Correo Electr&oacute;nico de Contacto y Tel&eacute;fono de Contacto.
		</p>
		<p>
			Si lo desea, tambi&eacute;n puede realizar b&uacute;squedas <a href="busquedaCapacidades.php"> por Servicios Ofertados</a>, <a href="busquedaDemanda.php"> por Servicios Demandados</a> o por <a href="busquedaGeografica.php"> por Localizaci&oacute;n </a>. 
		</p>
	</div>
