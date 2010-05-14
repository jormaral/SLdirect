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

	interface datadriver
	{
		function connect();
		function query($sql);
		function insert(datarow $row,$table);
		function delete(datarow $row,$table);
		function update(datarow $row,$table);
		function backup();
		function testConnection($application);
	} 

class database
{     
	static private $instance=NULL;
 
	private function __construct() {  }
	private function __clone() {  }
    
	/**
	* Creates an instance of a datadriver of the given type.
	* @param string $type the datadriver type.	
	*/     		
	public static function factory($type="mysql") {
		try {
			include_once("drivers/data/".$type.".driver.php");
		} catch (Exception $e) {
			throw new DatabaseDriverNotFoundException ('$type Driver not found');
		}
		
		if (class_exists($type. "_driver"))
		{
            $classname = $type. "_driver";
            return new $classname;
		}
		
        throw new Exception ('Driver not found');
    }

    /**
	* Returns a single and master instance of the datadriver object requested.
	* @return database
	*/ 
	public static function &singleton($driver='') {
        if(self::$instance[$driver]==NULL) 
		{
            self::$instance[$driver] = &self::factory($driver);
        }
    	return self::$instance[$driver];
	}
} 

class datarow {
}

?>
