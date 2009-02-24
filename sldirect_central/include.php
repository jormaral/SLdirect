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

require_once("settings/production.settings.php");
require_once("kernel/kernel.php");
$session = session::instance();

global $__lang;
$__lang = array_key_exists("lang",$_REQUEST) ? $_REQUEST["lang"] : "es";
error_reporting(255);

function html_header($title="")
{	
	global $__lang;
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


?>
