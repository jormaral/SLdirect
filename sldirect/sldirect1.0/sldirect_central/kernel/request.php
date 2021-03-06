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

class request
{
	function request()
	{	
		global $HTTP_POST_VARS, $HTTP_GET_VARS, $HTTP_COOKIE_VARS;
		$params = array_merge($HTTP_POST_VARS, $HTTP_GET_VARS, $HTTP_COOKIE_VARS);
		foreach ($params as $k => $v) 
		{
				$this-> $k = $v;
		}
	}	
	
	function hasKey($key)
	{
	global $HTTP_POST_VARS, $HTTP_GET_VARS, $HTTP_COOKIE_VARS;
		$params = array_merge($HTTP_POST_VARS, $HTTP_GET_VARS, $HTTP_COOKIE_VARS);
		foreach ($params as $k => $v) 
		{		
				if ($k == $key)
					return true;
		}
		return false;
	}
	
}

?>
