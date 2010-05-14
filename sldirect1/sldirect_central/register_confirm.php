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

include ("include.php");
$done = $differ = false;
$organizacion = new organizacion();
$list = $organizacion->getList("hashCreacion='".$_REQUEST["hash"]."'");
if (count ($list) == 0) die ("Hash no encontrado");
$organizacion = $list[0];

if (array_key_exists("pass1",$_REQUEST) && array_key_exists("pass2",$_REQUEST))
{
	if ($_POST["pass1"] == $_POST["pass2"])
	{
		$db = database::singleton("mysql");

		$rows = $db->query ("UPDATE organizaciones SET password='".md5($_REQUEST["pass1"])."' , hashCreacion='' WHERE id=".$organizacion->id);
		
		require("class.phpmailer.php");
		$mail = new PHPMailer();

		$mail->From     =  $settings["mail.from"] ;
		$mail->FromName = "SLDirect";
		$mail->Host     = $settings["mail.host"] ;
		$mail->Mailer   = $settings["mail.mailer"] ;
		$mail->Subject  = "Datos de acceso para SLDirect de ".$organizacion->nombre;
		$mail->Username = $settings["mail.smtp.username"];
		$mail->Password = $settings["mail.smtp.password"];
		$mail->SMTPAuth = true;

		$text_body  = "Datos de Acceso para SLDirect";
		$text_body .= "

Usted recibe este correo electr&oacute;nico porque usted o alguien de su organizaci&oacute;n ha procedido al registro de su organizaci&oacute;n en SLDirect, El directorio de empresas de Software Libre de Cenatic.

";			
		$text_body .= "Los datos de acceso al directorio que han sido proporcionados son los siguientes:

";			
		$text_body .= "Nombre de Usuario: ".$organizacion->username."

";					
		$text_body .= "Password : ".$_POST["pass1"]."

";					

		$text_body .= "Gracias por registrarse en SLDirect.

";			
			
		$mail->AltBody = $text_body;
		$mail->Body = "<html><body>".nl2br ($text_body)."</body></html>";
		$mail->AddAddress($organizacion->username);
		$mail->Username = $settings["mail.smtp.username"];
		$mail->Password = $settings["mail.smtp.password"];
		$mail->Send();
		
		
		$done=true;
	} else {
		$differ= true;
	}
}
html_header();
includeLang("register_confirm.php",array("hash" => $_REQUEST["hash"], "differ" => $differ,"done" => $done,"username" => $organizacion->username));
html_footer();
?>
