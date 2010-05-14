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

interface authsource {
	public function Login($username,$password);
	public function Logout($username);
	public function getUserList();
}
          

class session
{     
	static private $instance=NULL;
	public $username;
	public $userid;
    protected $sid;
    public $views;
    public $timestamp_created;
	public $timestamp_lastvisit;
	public $remote_ip;
	public $useragent;
	public $application;
	
	private function __clone() {  }

 	public function __construct() {
		global $settings;
		if (array_key_exists("__sid",$_COOKIE))
		{
			$r = new request();
		    if (file_exists($settings["sessionpath"] . "/webkernel_sid_".$_COOKIE["__sid"]))
			{
				$f = file($settings["sessionpath"] . "/webkernel_sid_".$_COOKIE["__sid"]);
				$f = base64_decode($f[0]);
				$f = unserialize($f);  
				$this->sid = $_COOKIE["__sid"];
				$this->views = $f->views +1;
				$this->timestamp_lastvisit = time();  
				$this->timestamp_created = $f->timestamp_created;
				$this->userid  =$f->userid;
				$this->username = $f->username;
				$this->remote_ip = $f->remote_ip;
				$this->useragent = $f->useragent;
				$this->application = $f->application;
			} else {    
				$this->createNewSession();		   
			}
		} else {
			$this->createNewSession();		   
		}
	}
    
	public function Login ($username,$password) {                                                    
		global $settings;      
		$this->createNewSession();
		$authsource = $settings["session.auth.source"];
		include_once ("drivers/identity/".$authsource.".driver.php");		
		$o = new $authsource();
		if ($o->Login ($username,$password))
		{   
			$this->username = $username;
			$this->userid = $o->getUid($username);
			$this->Save();
			return true;
		}
		$this->__construct();
		return false;
	}
   
	public function getUserParam ($param) {                                                    
	 	global $settings;      
		$authsource = $settings["session.authsource"];
		$o = new $authsource();     

		return $o->getUserParam($this->username,$param);
	}

       
	public function Save() {  
	 	global $settings;
		try 
		{
			$fn = $this->sid; 
			$fd = fopen($settings["sessionpath"] . "/webkernel_sid_".$fn,"w");
			$b = base64_encode(serialize($this));
			fwrite($fd,$b);
			fclose($fd);
			return true;
		} catch (Exception $e){
			return false;
		}   
	}

	public function __destruct()
	{
	 	$this->Save();
	}

	public function getSid()
	{
		return $this->sid;
	}
	
    public function createNewSession()
	{
		global $settings;
		try 
		{
			$fn = md5(uniqid(rand())); 
			setcookie("__sid",$fn,time()+60*60*24);
			$this->sid = $fn;   
			$this->userid = "";
			$this->username = "";
			$this->timestamp_created = time();
			$this->useragent = $_SERVER['HTTP_USER_AGENT'];
			$this->remote_ip = (getenv("HTTP_X_FORWARDED_FOR")) ? getenv("HTTP_X_FORWARDED_FOR") : getenv("REMOTE_ADDR");
			$fd = fopen($settings["sessionpath"] . "/webkernel_sid_".$fn,"w");
			$b = base64_encode(serialize($this));
			fwrite($fd,$b);
			fclose($fd);
			return true;
		} catch (Exception $e){
			return false;
		}
	}

	public function Logout()
	{
		global $settings;
		@unlink($settings["sessionpath"] . "/webkernel_sid_".$this->sid);
		$this->createNewSession();
	}
	
	public static function &instance() 
	{
        if(self::$instance==NULL) 
		{
            self::$instance = new session();
        }
        return self::$instance;
	}
}            
