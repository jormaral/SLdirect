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

function setSection($section){
	global $__section;
	$__section = $section;

}

function getSection(){
	global $__section;
	return $__section;
}

require_once("settings/loader.php");

require_once("kernel/kernel.php");
header("content-type: text/html; charset=UTF-8");
$session = session::instance();

global $__lang;
$__lang = array_key_exists("lang",$_REQUEST) ? $_REQUEST["lang"] : "es";
error_reporting(E_ALL  & ~E_NOTICE & ~E_WARNING);

function html_header($title="")
{	
	global $__lang, $session;
	if (!file_exists(("lang/".$__lang."/header.php"))){
		include ("lang/es/header.php");
		return;
	}
	include ("lang/".$__lang."/header.php");	

}

function html_footer()
{
	global $__lang;
	if (!file_exists(("lang/".$__lang."/footer.php"))){
		include ("lang/es/footer.php");
		return;
	}
	include ("lang/".$__lang."/footer.php");	

}


function includeLang($template,$context=""){
	global $__lang;
	if (!file_exists(("lang/".$__lang."/".$template))){
		echo "<div style=\"position:absolute;left:100%;margin-left:-540px;top:10px;width:520px;border:solid 4px red;background:#ffffdd;font-size:11px;padding:5px;\"><b>Lo sentimos</b><br />No podemos encontrar una traducci&oacute;n para esta p&aacute;gina en la lengua solicitada. Se ha seleccionado <b>castellano</b> como reemplazo.<hr /><b>We're Sorry</b><br />We couldn't find a translation for this page for the language selected. <b>spanish</b> has been seleted as a replacement</div>";
		include ("lang/es/".$template);
		return;
	}
	include ("lang/".$__lang."/".$template);	
}

function mp_header()
{
	?>
	<html>
	<head>
		<link rel="stylesheet" href="../css/style.css" />
		<link rel="stylesheet" href="style.css" />
	</head>
	<body>
	<style>
		body { margin :0px;}
	</style>
	<?php
}

function mp_footer()
{
	?>
	</body>
</html>
	<?php
}

function dateUStoEUR($fecha) {
	$arr = explode ("-",$fecha);
	$timestamp = mktime(0,0,0,$arr[1],$arr[2],$arr[0]);
	return date ("d/m/Y",$timestamp);
}
function dateEURtoUS($fecha) {
	$format = 'd/m/Y';
	$arr = explode ("/",$fecha);
	$timestamp = mktime(0,0,0,$arr[1],$arr[0],$arr[2]);
	return date ("Y-m-d",$timestamp);
}

// Sólo si la funcion no existe, entonces la crea
if (!function_exists('json_encode')){

    function json_encode($a=false){
        if (is_null($a)) return 'null';
        if ($a === false) return 'false';
        if ($a === true) return 'true';
        if (is_scalar($a)){
            if (is_float($a)){
                // Siempre usa "." para floats.
                return floatval(str_replace(",", ".", strval($a)));
            }
            if (is_string($a)){
                static $jsonReplaces = array(array("\\", "/", "\n", "\t", "\r", "\b", "\f", '"'), array('\\\\', '\\/', '\\n', '\\t', '\\r', '\\b', '\\f', '\"'));
                return '"' . str_replace($jsonReplaces[0], $jsonReplaces[1], $a) . '"';
            }else
              return $a;
        }

        $isList = true;
        for ($i = 0, reset($a); $i < count($a); $i++, next($a)){
            if (key($a) !== $i){
                $isList = false;
                break;
            }
        }

        $result = array();

        if ($isList){
            foreach ($a as $v) $result[] = json_encode($v);
            return '[' . join(',', $result) . ']';
        }else{
            foreach ($a as $k => $v) $result[] = json_encode($k).':'.json_encode($v);
            return '{' . join(',', $result) . '}';
        }

    }

}
