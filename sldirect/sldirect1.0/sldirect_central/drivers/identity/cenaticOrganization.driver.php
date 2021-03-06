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


		
class cenaticOrganization implements authsource
{    
	private $users;
	
	function __construct()
	{
	}
	
	function addUser($username,$password)
	{
		return false;
	}
	
	function save()
	{
		return false;
	}
	
	function Login($username,$password)
	{
		
		$organizacion = new organizacion();
		$list = $organizacion->getList("username='".$username."'");
		if (array_key_exists(0,$list))
		{

			if (!$list[0]->username) return false;
			$user = $list[0];
			if ($user->password == md5($password))
				return true;
		}
		return false;
	}
	
	public function getUserParam($username,$param)
	{                        
		return false;
	}

	public function getUserList()
	{
		$organizacion = new organizacion();
		return $organizacion->getList();
	}
	
	function getUid($username)
	{                         
		$organizacion = new organizacion();
		$list = $organizacion->getList("username='".$username."'");
		if (!$list[0]->username) return false;
		return $list[0]->id;
	}
	                                 
	function Logout($username){}
	
	function __destruct()
	{
	
	}
	
	
}

?>
