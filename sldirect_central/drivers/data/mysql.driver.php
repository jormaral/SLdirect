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
 * 
 * @copyright 2001-2008 Webalianza T.I. S.L.
 * @license   http://www.gnu.org/licenses/gpl.txt GNU General Public License v2
 * @link      http://www.webalianza.com
 *
 * $Id$
 **/


	class mysql_driver implements datadriver
	{       
		var $conn_id;
		var $rows;
		var $id_field;
		var $__cached;
		var $transaction;
		function __construct($connect=true)
		{
			if ($connect) $this->connect();
			$this->id_field = "id";
			$this->__cached = array();
			$this->transaction =0;
		}
		
		/**
		* Connects to the database
		* @return void
		*/
		function connect()
		{
			global $settings;
			try {
				$this->conn_id = mysql_pconnect($settings["datadriver.mysql.host"],$settings["datadriver.mysql.username"],$settings["datadriver.mysql.password"]);
				if (!$this->conn_id || !mysql_select_db($settings["datadriver.mysql.database"],$this->conn_id)) throw new Exception ("Error");
				return true;
			} catch (Exception $e) {
				throw new DatabaseException("Could not connect to the Database Server: " .mysql_error());
			}
			
		}
		
		/**
		* Executes the given SQL query
		* @param string $sql the SQL Query
		* @return void
		*/ 
		function query($sql)
		{   

			// We are good guys and cache the SHOW FIELDS queries :) (we make zillions of them)

			if (array_key_exists($sql,$this->__cached))
			{
				$this->rows =  $this->__cached[$sql];

				return;
			}
  			if (!$this->conn_id) return false;
			$this->rows = array();
			$result = mysql_query($sql, $this->conn_id);             
			if (mysql_error()) 
			{
				throw new DatabaseException("Invalid Query :".$sql. ":".mysql_error());
			}
			$n = 0;
			if (strstr($sql, "UPDATE") == FALSE && strstr($sql, "DELETE") == FALSE && strstr($sql, "INSERT") == FALSE && strstr($sql, "CREATE") == FALSE && strstr($sql, "DROP") == FALSE && strstr($sql, "ALTER") == FALSE) 
			{
				if ($result)
					while ($temprow = mysql_fetch_assoc($result)) 
					{
						$row = new datarow();
						foreach ($temprow as $k => $v) {
							$row->$k = $v;   
						}
						$this->rows[$n] = $row;
						$n ++;
					}
				if (!array_key_exists(0,$this->rows))
				{
					throw new DatabaseException("Query returned empty set");
				}
			}   
			
			// We are good guys and cache the SHOW FIELDS queries :) (we make zillions of them)
			if (preg_match('/^SHOW FIELDS FROM/',$sql)) 
			{
				$this->__cached[$sql] = $this->rows;     
			}
			
		}	
		/**
		* Creates and executes a SELECT SQL query based on the the id given and from the table given
		* @param int $id The given id
		* @param table the table to execute the query in
		* @return void
		*/ 
		function open($id,$table)
		{                                                               
			$this->query("SELECT * FROM ".$table." WHERE ".$this->id_field."=".$id);
			if (array_key_exists(0,$this->rows))
				return ($this->rows[0]);
			throw new DatabaseException("Not found");   				
		}
		
		/**
		* Creates and executes an INSERT SQL query based on the data contained in the datarow
		* @param datarow $row The given row
		* @param table the table to execute the query in
		* @return void
		*/ 
		function insert(datarow $row,$table) 
		{
			$strs = $strd = $n = "";
			foreach ($row as $k => $v) 
			{
				if (strpos($k,"__")===false)
				{
					$strs = $strs."\"".$v."\",";
					$strd = $strd.$k.", ";
				}
			}
			$strs = substr($strs, 0, -1);
			$strd = substr($strd, 0, -2);
			$que[$n] = "INSERT INTO ".$table." (".$strd.") VALUES ( ".$strs." )";
			$this->query($que[$n]);
			if (!mysql_error())return mysql_insert_id();
		}

		/**
		* Creates and executes an UPDATE SQL query based on the data contained in the datarow
		* @param datarow $row The given row
		* @param table the table to execute the query in
		* @return void
		*/ 
		function update(datarow $row,$table) 
		{   
			$strs = $strd = $n = "";
			foreach ($row as $k => $v) 
			{
				if (strpos($k,"__") === false && $k != "id")
				{
					if (!is_numeric($v))
					{
						$strs = $strs.$k." = \"".$v."\", ";
					} else {
						$strs = $strs.$k." = ".$v.", ";
					}
				}
			}
			$strs = substr($strs, 0, -2);
			$que = "UPDATE ".$table." SET ".$strs." WHERE ".$this->id_field."=".$row->id;
			$this->query($que);
		}

		/**
		* Creates and executes a DELETE SQL query based on the data contained in the datarow
		* @param datarow $row The given row
		* @param table the table to execute the query in
		* @return void
		*/ 
		function delete(datarow $row,$table) {
    		$this->query("DELETE FROM ".$table." WHERE ".$this->id_field."=".$row->id);
			if (!mysql_error())return true;
		}
		
		/**
		* Generates a Backup SQL File
		* @return void
		*/ 
		function backup(){
			
		}
				
		function testConnection($application)
		{
			$application=new application($application);
			global $settings;
			try {
				$this->conn_id = mysql_pconnect($settings["datadriver.mysql.host"],$settings["datadriver.mysql.username"],$settings["datadriver.mysql.password"]);
				if (!$this->conn_id || !mysql_select_db($settings["datadriver.mysql.database"],$this->conn_id)) return false;
				return true;
			} catch (Exception $e) {
				return false;
			}
		}
		
		
	}
?>
