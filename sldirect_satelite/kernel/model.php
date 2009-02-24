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

interface imodel
{
	function __construct();
	function getRelativeTable();
 	function onInsert();
	function onUpdate();
}      

abstract class model implements imodel
{
	private $modelCaptions;
	private $modelForeigns;
	private $modelRelativeTable;
	private $modelHasMany;
	private $modelACL;
	private $modelMandatory;
	private $modelHiddenFields;
	var $id;
	
	/**
	* Tells the model which table use as a base for the introspection. Must be called in order to have a good model
	* @param string $table The name of the table in the current database.
	* @return void
	*/ 

	function setHidden($field){
		$this->modelHiddenFields[] = $field;
	}
	
	function getHiddens() {
		return $this->modelHiddenFields;
	}
	
	function isHidden($field) {
		foreach ($this->modelHiddenFields as $hidden)
			if ($hidden == $field)
				return true;
		return false;
	}
	
	function setRelativeTable($table,$driver="mysql") {   
		/* Since it is mandatory that every model calls this method, we use it as our
		 hereditary constructor, since we assume that devels like to write their own
		 Constructors. */
		$this->modelHasMany = array();
		$this->modelForeigns = array();
		$this->modelCaptions = array();
		$this->__errors = array();            
		$this->modelACL = array();
		$this->modelMandatory = array();
		$this->modelRelativeTable = $table;
		$database = database::singleton($driver);
		$database->query ("SHOW FIELDS FROM ".$this->getRelativeTable());
		foreach ($database->rows as $row)
		{
			$field = $row->Field; 
			// This is pretty much the core of the model way of handling the object-relational mapping.
 			if (!array_key_exists($field,get_class_vars(get_class($this))) &&  $field != "id")$this->$field = " ";
		}   
	}

	function getInvalidFields() {
		
		foreach ($this->__errors as $field => $validity)
		{
			if ($validity)
			{
				$arr[]  = $field;
			}
		}
		return $arr;
	}
	
	/**
	* Adds an Access Control List Entry for the model.
	* @param string $param parameter to fetch from the auth source
	* @param string $value value to match
	* @param string $action action where the acl applies (insert,open,delete,update...). Can be null
	* @param string $id model id where the acl applies. Can be null
	* @return void 
	*/
	function setACL($param,$value,$action="",$id=0) {
		$this->modelACL[] = array ("param" => $param , "value" => $value, "action" => $action , "id" => $id);
	}
	
	/**
	* Gets the table from where the model extracts data.
	* @return string 
	*/     
	function getRelativeTable() {
		return $this->modelRelativeTable;
	}
	
	function isValid(){return $this->validate;}
	
	function setMandatory($field) {
		$this->modelMandatory[$field] = true;
	}	
	
	function isMandatory($field) {
		if (array_key_exists($field,$this->modelMandatory))
			return true;
		return false;
	}
	
	/**
	* Validates the data in the model against the schema of the database.
	* This is an automatically generated validator and can be overriden by
    * proper special case code.
	* @return boolean
	*/     
	function validate() {
		foreach ($this as $k=>$v)
		{   
			if  ($this->isMandatory($k) && $v == "")
				$this->__errors[$k] = true;
								
			$type = $this->getType($k); 
			if ($type)
			{
				$type_arr = explode("(",$type);
				if (array_key_exists(1,$type_arr)) $type_arr[1] = rtrim($type_arr[1],")");
				switch ($type_arr[0])
				{
					case "varchar":
						if (is_string($v))
							if (strlen($v) < $type_arr[1])
							break;
					$this->__errors[$k] = true;
					break;	
					
					case "int":
						if (is_numeric($v) && is_int((int)$v))
						{
							if(strstr("unsigned",$type_arr[1]))
							{
						 		if ($v == abs($v))
						 		    break;
						 	} else {
                                break;
							}
						 }   	
					$this->__errors[$k] = true;
					break;

					case "float":
						if (is_numeric($v) && is_float((float)$v))
								break;
					$this->__errors[$k] = true;
					break;
				}	
			}
		}
		if (count($this->__errors) > 0)
		{                                    
			$e_f = "";
			foreach ($this->__errors as $k=>$v)
			{
				$e_f .= $k.",";
			}
			$e_f = rtrim($e_f,",");
			throw new InvalidModelException("Invalid Model Data on fields ".$e_f." for model '".get_class($this)."'");
			return false;
		 }
		return true;
	}

	/**
	* Gets an empty model of this class.
	* @return model
	*/     
	function getEmptyModel($driver="mysql") {   
		// This function returns a new model with empty fields. 
		// It's written like this in order to override ACL checks.
		$class = get_class($this);
		$m = new $class($this->getRelativeTable());
		$database = database::singleton($driver);
		$database->query ("SHOW FIELDS FROM ".$this->getRelativeTable());
		foreach ($database->rows as $row)
		{
			$field = $row->Field; 
 			if (!array_key_exists($field,get_class_vars(get_class($this))) &&  $field != "id")$m->$field = " ";
			if (array_key_exists($field,$m->modelForeigns)) 
			{
				$model = $m->modelForeigns[$field]["model"];
				$ma = new $model;
				$m->$field = $ma->getEmptyModel();
			}
		}	
		return $m;
	} 
	
	
	/**
	* Returns the type of the field given by parameter as given by the database
	* @param string $field the field name
	* @return string
	*/     
	
	function getType($field,$driver="mysql") {   
		$database = database::singleton($driver);
		if ($field == "id") return false;
		$database->query ("SHOW FIELDS FROM ".$this->getRelativeTable());
		foreach ($database->rows as $row)
		{
			if ($row->Field == $field)
			{
				return $row->Type;
			}
		}
	}

	/**
	* Changes the caption of a given field when representing it on a form or grid.
    * This is a conveniency and it's not meant to be very used
	* @param string $field the field to change
	* @param string $name the caption to show
	* @return void
	*/     
	function setCaption($field,$name)
	{
		$this->modelCaptions[$field] = $name;
	}                                     
	                             
	/**
	* Defines a Foreign-Key relationship between models
	* @param string $local_field this model's field that contains the foreign id
	* @param string $model the class of the foreign model (not the database table)
	* @param string $foreign_caption the field to use as a default caption when used in a widget context (p.e in a grid)
	* @param string $foreign_key='id' the foreign table's field to use a id field.
	* @return void
	*/     
	function Foreign($local_field,$model,$foreign_caption,$foreign_key="id")
	{
		$this->modelForeigns[$local_field]["model"] = $model;
		$this->modelForeigns[$local_field]["foreign_caption"] = $foreign_caption;
		$this->modelForeigns[$local_field]["foreign_key"] = $foreign_key;
	}
	
	/**
	* Return all the Foreign-Key relationships of this model
	* @return array
	*/     
	function getForeigns()
	{
		return $this->modelForeigns;
	}
   
	function getForeignModels(){
		$models = array();
		foreach ($this->modelForeigns as $f){
			$models[] = $f["model"];
		}
		return $models;
	}

	/**
	* Returns the model class name corresponding to the foreign field or null on inexisting model
	* @param string $field The field to check
	* @return string
	*/     
	function getForeignModelClass($field)
	{
		if (array_key_exists($field,$this->modelForeigns))
			return $this->modelForeigns[$field]["model"];
	}
	
	/**
	* Checks that the given field is a Foreign-Key relationship.
	* @param string $field The field to check
	* @return boolean
	*/     
	function isForeign($field)
	{
		if (array_key_exists($field,$this->modelForeigns))
			return $this->modelForeigns[$field]["foreign_caption"];
		return false;
	}
    
	/**
	* Gets the caption of the given field as written by setCaption()
	* @param string $field the field name
	* @return string
	*/     
	function getCaption($field)
	{                              
		if (array_key_exists($field,$this->modelCaptions)) return $this->modelCaptions[$field];
		return $field;
	}
	
	/**
	* Fetches from the database the current model's data that matches the id given.
	* @param integer $id the row id field to fetch data from 
	* @return void
	*/     
	
	function open($id,$driver="mysql")
	{   
		
		$database = database::singleton($driver);
		
		try{	
			$row = $database->open($id,$this->getRelativeTable());
		} catch (Exception $e) {
			return new ModelNotFoundException();
		}
		if($row)
		foreach ($row as $key => $value)
		{
			if (!array_key_exists($key,$this->modelForeigns))
			{
				$this->$key = $value;
			} else {
				$model = $this->modelForeigns[$key]["model"];
				$m = new $model;
				$m->open($value);
				$this->$key = $m;
			}
		}
	}
	 
	/**
	* Fetches from the database the current model's data that matches the value given in the field given. Will open only the first match ordered by id
	* @param string $field the column to search from
	* @param string $value the value the column has to match 
	* @return void
	*/     
	
	function openByField($field,$value,$driver="mysql")
	{
		$database = database::singleton($driver);
		if (!is_numeric($value)) $value = '"'.$value.'"';
		$database->query ("SELECT * FROM ".$this->getRelativeTable()." WHERE ".$field." = ".$value. " ORDER BY id ASC");
		$row = $database->rows[0];
		foreach ($row as $key => $value)
			$this->$key = $value;			
	}
	
	/**
	* Writes the current model to the database. Automatically detects if it's an insert or update operation.
	* @return void
	*/     
	
	function write()
	{        
    	if (property_exists($this,"id"))
			if ($this->id != "")
			{
				$this->update();    	
				return;              
			}
		return $this->insert();
	}

	/**
	* Copies the properties of another object into this one. The other object can be of any type.
	* @param object $object the object to copy properties from
	* @param int $id the id of the model to copy to. Can be null to create a new one. 
	* @return void
	*/     
	
	function copy($object,$id="")
	{   
		if ($id) $this->open($id);
		$o_vars = get_object_vars($object);
		foreach ($this as $k => $v)
		{
			if (array_key_exists($k,$o_vars))
				$this->$k = $object->$k;
		}
	}
	
	function copyTo($object,$id="")
	{   
		if ($id) $this->open($id);
		$o_vars = get_object_vars($object);
		foreach ($this as $k => $v)
		{
			if (array_key_exists($k,$o_vars))
				 $object->$k = $this->$k;
		}
	}
	
	/**
	* Inserts into the database the current model. The model id has to be null in order to do so or it will throw a InvalidModelException
	* @return void
	*/     
	function insert($driver="mysql")
	{   
		if ($this->id !="") throw new InvalidModelException("Trying to insert a model with an id defined");
		$this->onInsert();
		$database = database::singleton($driver);
		$row = new datarow();
		/*foreach ($this as $key => $value)
		{
			if ($key{0} != "_" && $key{1} != "_")
			{                             
				$row->$key = $value;
			}
		}*/   
		$reflector = new ReflectionClass (get_class($this));
		foreach ($reflector->getProperties () as $property) {
			$property = $property->name;
			$row->$property = $this->$property;
		}
		$id = $database->insert($row,$this->getRelativeTable());
		$this->afterInsert ();
		return $id;
	}
	
	/**
	* Alias for update()
	* @return void
	*/     
	function save($driver="mysql")
	{
		$this->update($driver);
	}
	
	/**
	* Updates the current model's data into the database using the current model's id field as the identifier key.
	* @return void
	*/     
	function update($driver="mysql")
	{   
		try 
		{    
			$this->onUpdate();
			$database = database::singleton($driver);
			$row = new datarow();
			/*foreach ($this as $key => $value)
			{
				if (strpos($key,"__") === FALSE)
				{                             
					$row->$key = $value;
				}
			}*/
  		$reflector = new ReflectionClass (get_class($this));
	  	foreach ($reflector->getProperties () as $property) {
		  	$property = $property->name;
			  $row->$property = $this->$property;
		  }
			$database->update($row,$this->getRelativeTable());
			$this->afterUpdate ();
		} catch (Exception $e){
			throw $e;
		}	
	}
	
	/**
	* Deletes the current model from the database. Checks for children dependency consistency when doing so and throws an CannotDeleteHasChildrenException when cannot do so
	* @return void
	*/     
	
	function delete($driver="mysql")
	{
		$this->onDelete();
		foreach ($this->modelHasMany as $model => $key)
		{             
			$children = $this->getChildren($model);
			if (count($children) && $children[0]->id != "")
				throw new CannotDeleteHasChildrenException("The model ". get_class($this). " with id ".$this->id." still has " . get_class($children[0])."'s as children!!!");
		}
		$database = database::singleton($driver);
		$row = new datarow();
		foreach ($this as $key => $value)
		{
			if ($key != "modelRelativeTable")
			{                             
				$row->$key = $value;
			}
		}
		$database->delete($row,$this->getRelativeTable());
		$this->afterDelete ();
	}                    
	
	
	/**
	* Return an array of models with all the models present in the database 
	* @return array
	*/     		
	function getAll($driver="mysql")
	{
		return $this->getList("1",$driver);
	}              
	
	/**
	* Return an array of models with the models present in the database that matches the condition
	* @param string $condition the WHERE=%s part of the SQL query 
	* @return array
	*/     		
	function getList($condition="",$driver="mysql")
	{  
		try
		{              
			if ($condition == "") $condition = " 1=1";
			$database = database::singleton($driver);
			$database->query ("SELECT * FROM ".$this->getRelativeTable()." WHERE ".$condition);
			$class_name = get_class($this);
			$list = array();
			foreach ($database->rows as $row)
			{             
				$m = new $class_name($this->getRelativeTable());
				try {
				$m->open($row->id);
				$list[] = $m;
				} catch (AccessDeniedException $e)
				{
					$list[] = $m->getEmptyModel();
				}
			}
			if (count($list))
				return $list;
			return array();        
		} catch (DatabaseException $e) {
			$arr = array();
			return $arr;
		}
	} 

	function onInsert(){
		$this->validate();
	}  
	function onUpdate(){
	   $this->validate(); 
	}      
	
	function onDelete(){}
	
	function afterInsert (){}
	function afterUpdate (){}
	function afterDelete (){}
	
	function __toString()
	{
		return ("model::".get_class($this)."::".($this->id ? $this->id : "NoID"));
	}
	
	/**
	* Defines that the current model has children models with the corresponding Foreign-Key relationship
	* @param string $model the model (not database table) class name that is children of this model
	* @param string $key the children model's field that contains this model's id. 
	* @return array
	*/     		
	function Children($model,$key)
	{                    
		$this->modelHasMany[$model] = array("key" => $key);
	}
	
	/**
	* Return an array of models that are children of this model as defined by the Children() call.
	* @param string $model the model (not database table) class name of the childrens that we want to fetch.
	* @return array
	*/     		
	
	function getChildrenModelList()
	{
		$models = array();
		foreach ($this->modelHasMany as $model => $key){
			$models[] = $model;
		}
		return $models;
	}
	
	function getChildren($model) {
		if (!is_object($model)) 
		{
			$model = new $model;
		}
		if (array_key_exists(get_class($model),$this->modelHasMany))
		{                                    
			return $model->getList( $this->modelHasMany[get_class($model)]["key"]."=".$this->id);
		}
	}
	
	function toXML($o) {
		if (is_array($o))
		{
			return $this->toXML_Array($o);
		} else {
			return $this->toXML_Model($o);
		}
	}

	function toXML_Array($array) {
		$out .= "<".get_class($this)."List>";
		foreach ($array as $model)
		{
			if ($model->id)
			$out .= "
".$this->toXML_Model($model);
		}
		$out .= "</".get_class($this)."List>";
		return $out;
	}
	
	function patricide() {
		foreach ($this->modelForeigns as $field => $keys)
		{
			$foreign_key = $keys["foreign_key"];
			$this->$field = $this->$field->$foreign_key;
		}
	}
	
	function toXML_Model($o) {
		$out .="<".get_class($this).">";
		foreach ($o as $key => $value)
		{
			if (strpos($key,"__") === FALSE)
			{                             
				if ($this->isForeign($key))
				{
					$field = $this->isForeign($key);
					$out .="<".$key." id=\"".$value->id."\">".$value->$field."</".$key.">";
				} else {
					$out .="<".$key.">".$value."</".$key.">";
				}
			}
		}
		$out .="</".get_class($this).">";
		return $out;   
	}
}

class dummyModel extends model{
	var $id=0;
	function __construct (){}
	function getRelativeTable() {}
	function onDelete() {}
	function onOpen() {}
	function onValidate() {}
	
}
	
	
	
