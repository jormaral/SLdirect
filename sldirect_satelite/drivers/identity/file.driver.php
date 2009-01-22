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

/**
 * Webalianza webKernel 0.90 
 * MicroKernel for web apllications
 * Based on developments of ooCommon, ooCommonNG, Phowl and Ignite Project
 * ---------------------------------------------------------------------------------- 
 * Copyright  (C) 2001-2008 Webalianza T.I. S.L.
 * ooCommon   (C) 2001 Praetorians MSAT S.L., former name of Webalianza T.I. S.L
 * ooCommonNG (C) 2005 Webalianza T.I. S.L 
 * Phowl      (C) 2003-2004 Webalianza T.I. S.L
 * ----------------------------------------------------------------------------------
 * 
 * This file is part of Webalianza webKernel
 * 
 * webKernel is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; version 2 of the License.
 *
 * webKernel is distributed in the hope that it will be useful,
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
 * @copyright 2001-2008 Webalianza T.I. S.L.
 * @license   http://www.gnu.org/licenses/gpl.txt GNU General Public License v2
 * @link      http://www.webalianza.com
 *
 * $Id$
 **/


class file implements authsource
{    
	private $users;
	
	function __construct()
	{
		$file = "users.ser";
		$contents = implode("",file($file));
		$this->users = unserialize ($contents);
	}
	
	function addUser($username,$password)
	{
		foreach ($this->users as $user)
			if ($uid < $user["uid"]) $uid = $user["uid"] + 1;
		$this->users[$username] = array("uid" => $uid , "username" => $username, "password" => md5($password));
		return $this->save();
	}
	
	function save()
	{
		try {
			$serial = serialize($this->users);
			$file = "users.ser";
			$fd = fopen($file,"w");
			write($fd,$serial);
			close ($fd);
			return true;
		} catch (Exception $e) {
			return false;
		}
	}
	
	function Login($username,$password)
	{
		if (!array_key_exists($username,$this->users))
			return false;
		if (md5($password) == $this->users[$username]["password"])
			return true;
		return false;
	}
	
	public function getUserParam($username,$param)
	{                        
		return false;
	}

	public function getUserList()
	{
		foreach ($this->users as $user)
		{
			$users[] = array ("uid" => $user["uid"], "username" => $user["username"]);
		}
		return $users;
	}
	
	function getUid($username)
	{                         
		return $this->users[$username]["uid"];
	}
	                                 
	function Logout($username){}
	
	function __destruct()
	{
	
	}
	
	public function changeSource($application)
	{
		$file = "users.ser";
		$contents = implode("",file($file));
		$this->users = unserialize ($contents);
	} 
	
}

?>
