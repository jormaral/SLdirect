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

class ldap implements authsource
{    
	var $hostname;
	private $binddn;
	private $bindpass;                                     
	var $searchpath;
	private $__ldap;
	private $__bind;
	function __construct()
	{
		global $settings;
		$this->hostname = $settings["session.ldap.hostname"];
		$this->binddn = $settings["session.ldap.binddn"];
		$this->bindpass = $settings["session.ldap.bindpass"];     
		$this->searchpath = $settings["session.ldap.searchpath"];
		$this->__ldap =ldap_connect($this->hostname);  // must be a valid LDAP server!
		ldap_set_option($this->__ldap, LDAP_OPT_PROTOCOL_VERSION, 3);
		if (!$this->__ldap)
			throw new UnavailableAuthSourceException("Unable to connect to LDAP Server");
	    $this->__bind=ldap_bind($this->__ldap,$this->binddn,$this->bindpass);
		if (!$this->__bind)
			throw new UnavailableAuthSourceException("Invalid Bind DN");
	}
	
	function Login($username,$password)
	{
		$result=ldap_search($this->__ldap, $this->searchpath, "uid=".$username); 
		if (!ldap_count_entries($this->__ldap, $result))
             throw new InvalidCredentialsException("User not found");
		$info = ldap_get_entries($this->__ldap, $result);
		if ($this->ValidatePassword($password,$info[0]["userpassword"][0]))
			return true;
		throw new InvalidCredentialsException("Invalid Password");
		return false;
	}
                                                                 
	public function ValidatePassword($password, $hash)
	{
		$ohash = base64_decode(substr($hash, 6));
		$osalt = substr($ohash, 20);
		$ohash = substr($ohash, 0, 20);
		$nhash = pack("H*", sha1($password . $osalt));
		if ($ohash == $nhash) 
			return true;
		else
			return false;
	}

	public function getUserParam($username,$param)
	{                        
		   $result=ldap_search($this->__ldap, $this->searchpath, "uid=".$username); 
			if (!ldap_count_entries($this->__ldap, $result))
				return false;   
		    $info = ldap_get_entries($this->__ldap, $result);
				return $info[0][$param][0];
	}

	function getUserList()
	{
		$result=ldap_search($this->__ldap, $this->searchpath, "uid=*");
		$info = ldap_get_entries($this->__ldap, $result);
			foreach ($info as $user)
			{
				$users[] = $user["uid"];
			}
		return $users;
	}
	
	function getUid($username)
	{                         
		
	 	   $result=ldap_search($this->__ldap, $this->searchpath, "uid=".$username); 
			if (!ldap_count_entries($this->__ldap, $result))
				return false;
		    $info = ldap_get_entries($this->__ldap, $result);
			return $info[0]["uidnumber"][0];
	}
	                                 
	function Logout($username){}
	
	function __destruct()
	{
	    ldap_close($this->__ldap);
	}
}

?>
