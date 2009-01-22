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

	class model2xml
	{
		static function modelArray2XML($array) {
			$head_part = "<?xml version=\"1.0\"?>";
			$head_part .="<models>";
			$items_part = "";
			foreach ($array as $model)
			{
				$items_part .=model2xml::modelItem2XML($model,false);
				
			}
			$foot_part ="</models>";
			return $head_part.$items_part.$foot_part;
		}
		
		static function modelItem2XML($model,$include_header = true,$include_tag = true) {
			$head_part = "<?xml version=\"1.0\"?>";
			$head2_part ="<".get_class($model)." ";
			$items_part = "";
			$containers_part = "";
			foreach ($model as $key => $value)
			{
				if (!preg_match("/^__/",$key))
				{
						 
					 if (is_object($value))
					 {
					 	$containers_part .="<".$key."".rtrim(model2xml::modelItem2XML($value,false,false),">")."></".$key.">";
					 } else {
					 	$items_part .=" ".$key."=\"".$value."\"";
					 }
				}
			}
			$second_items_part =">";
			$foot_part ="</".get_class($model).">";
			if ($include_header)return str_replace("\"<" ,"\"><",$head_part.$head2_part.$items_part.$containers_part.$foot_part);
			if ($include_tag) return str_replace("\"<" ,"\"><",$head2_part.$items_part.$second_items_part.$containers_part.$foot_part);
			if (!$include_tag) return str_replace("\"<" ,"\"><",$items_part.$second_items_part.$containers_part);
		}
		
	}
?>
