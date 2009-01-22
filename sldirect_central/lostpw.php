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

include("include.php");
global $settings;
error_reporting(0);
if (array_key_exists("username",$_REQUEST))
{	
		try{
			$organizacion = new organizacion();
			$list = $organizacion->getList("username='".$_REQUEST["username"]."'");
			$organizacion->open($list[0]->id);
			$organizacion->password = md5(uniqid(rand()));
			$organizacion->hashCreacion = md5(uniqid(rand()));
			$organizacion->provincia_id = $organizacion->provincia_id->id;
			$organizacion->formaJuridica_id = $organizacion->formaJuridica_id->id;
			$organizacion->organizacionClasificacion_id = $organizacion->organizacionClasificacion_id->id;
			$organizacion->update();
			require("class.phpmailer.php");
			$mail = new PHPMailer();
			$mail->From     =  $settings["mail.from"] ;
			$mail->FromName = "SLDirect";
			$mail->Host     = $settings["mail.host"] ;
			$mail->Mailer   = $settings["mail.mailer"] ;
			$mail->Subject  = "Restaurar acceso a SLDirect de ".$organizacion->nombre;
			$mail->Username = $settings["mail.smtp.username"];
			$mail->Password = $settings["mail.smtp.password"];
			$mail->SMTPAuth = true;
			$text_body  = "Restaurar acceso a SLDirect";
			$text_body .= "

Usted recibe este correo electr&oacute;nico porque usted o alguien de su organizaci&oacute;n ha indicado que su contrase&ntilde;a ha sido perdida.

";
			$text_body .= "Para restaurar la contrase&ntilde;a de acceso es necesario que apunte su navegador a la siguiente direcci&oacute;n

";			
			$text_body .= "".$settings["baseUrl"]."/register_confirm.php?hash=".$organizacion->hashCreacion." 

";			
			$text_body .= "Gracias por registrarse en SLDirect.

";			
			$mail->AltBody = $text_body;
			$mail->Body = "<html><body>".nl2br ($text_body)."</body></html>";
			$mail->AddAddress($organizacion->username);
			$mail->Username = $settings["mail.smtp.username"];
			$mail->Password = $settings["mail.smtp.password"];
			$mail->Send();
		} catch (Exception $e){
			$errors = true;
		}
}

html_header();
includeLang("lostpw.php",array());
html_footer();
?>
