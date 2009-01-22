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

?>
<div class="breadcrumb"><img src="media/icons/16x16/house.png"><a href="index.php">Inicio</a><img src="media/breadcrumb.png">Buscador de Empresas</div>

<table>
	<tr>
		<td>
			<h1>El A-Z del Software Libre</h1>
			<span style="font-size:14px">Hay <b><?php echo count($context["organizaciones"])?></b> empresas registradas en el directorio<?php if (array_key_exists("organizacionClasificacion_id",$_REQUEST) && $_REQUEST["organizacionClasificacion_id"] != "") echo " bajo el filtro seleccionado"?>. </span><br /><br />
			<table style="width:100%">
				<tr>
					<td colspan="2">
							<select onchange="document.location='busqueda.php?organizacionClasificacion_id='+this.options[this.selectedIndex].getAttribute('value');" name="organizacionClasificacion_id" style="width:100%;font-size:1.5em">
								<option value="" style="font-weight:bold">Todas las Actividades</option>
							<?php
							$organizacionClasificaciones = new organizacionClasificacion();
							foreach ($organizacionClasificaciones->getList("1=1 ORDER BY nombre ASC") as $organizacionClasificacion)
							{
								?>
								<option <?php if (array_key_exists("organizacionClasificacion_id",$_REQUEST) && $_REQUEST["organizacionClasificacion_id"] == $organizacionClasificacion->id) echo "selected"?>  value="<?php echo $organizacionClasificacion->id?>"><?php echo $organizacionClasificacion->nombre?></option>
								<?php
							}
						?>
						</form>
					</td>
				</tr>
			</table>
			<table style="width:100%;height:420px;overflow:scroll">
				<tbody style="overflow:scroll;overflow-y:auto;overflow-x:hidden;height:420px;">
			<?php
				$last_letter = "";
				foreach ($context["organizaciones"] as $organizacion)
				{
					$letter = strtoupper($organizacion->nombre{0});
					if ($last_letter != $letter){
						?>
					<tr style="height:22px;">
						<td style="padding:0px">
							<h2 style="margin:none;color:white;padding:4px;background:#a72791"><?php echo $letter;?></h2>
						</td>
					</tr>
						<?php 
						$last_letter = $letter;
					}
					?>
					<tr style="height:22px;">
						<td style="cursor:pointer;padding-right:22px;" onclick="document.location='organizacion.php?organizacion_id=<?php echo $organizacion->id?>'">
							<span class="searchResultTitle" style="font-size:14px;"><?php echo $organizacion->nombre?></span>
							<span class="searchResultTitle" style="font-size:10px;font-weight:normal"><?php echo $organizacion->descripcion?></span>
						</td>
					</tr>
					<?php
				}
			?>
			<tr><td>
				&nbsp;&nbsp;&nbsp;&nbsp;
			</td>
			</tr>
			</tbody>
			</table>
		</td>
		<td style="width:32px;">
			
		</td>			
		<td style="vertical-align:top">	
			<h1>B&uacute;squeda Estructurada</h1>
			<span style="font-size:14px">Tambi&eacute;n puede utilizar los buscadores Estructurados: </span><br /><br />
			<div onclick="document.location='busquedaCapacidades.php'" style="cursor:pointer;text-align:justify;width:350px;height:80px;background:url(media/porServicio.png) no-repeat"><h1 style="font-size:18px;padding-left:24px;margin-bottom:8px;padding-top:8px;">Buscar por Servicios</h1>
				<span style="font-size:1.3em;color:#525252;line-height:1.5em;"> Utiliza la b&uacute;squeda por Servicios para encontrar empresas que ofrezcan servicios basados en Software Libre en determinados sectores econ&oacute;micos.</span>				
			</div>
			<div onclick="document.location='busquedaDemanda.php'" style="cursor:pointer;margin-top:32px;text-align:justify;width:350px;height:80px;background:url(media/porDemanda.png) no-repeat"><h1 style="font-size:18px;padding-left:24px;margin-bottom:8px;padding-top:8px;">Buscar por Demanda</h1>
				<span style="font-size:1.3em;color:#525252;line-height:1.5em;"> Encuentra empresas que demanden servicios de otras empresas de tecnolog&iacute;a en determinados servicios.</span>				
			</div>		
			<div onclick="document.location='busquedaGeografica.php'"  style="cursor:pointer;margin-top:32px;text-align:justify;width:350px;height:80px;background:url(media/porLocalizacion.png) no-repeat"><h1 style="font-size:18px;padding-left:24px;margin-bottom:8px;padding-top:8px;">Buscar por Localizaci&oacute;n</h1>
				<span style="font-size:1.3em;color:#525252;line-height:1.5em;"> Utiliza la b&uacute;squeda por Localizaci&oacute;n para encontrar empresas cercanas que ofrezcan servicios basados en Software Libre.</span>				
			</div>
		

		</td>
</table>
