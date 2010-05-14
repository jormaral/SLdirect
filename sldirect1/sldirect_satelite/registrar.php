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

include("include.php");
global $settings;

function validate_nif ($value)
{
 	  $letters = array ("T","R","W","A","G","M","Y","F","P","D","X","B","N","J","Z","S","Q","V","H","L","C","K","E");
	  $value_c = substr($value,0,strlen($value)-1);
	  $check = substr($value,strlen($value-1),strlen($value));
      $calculated_letter = $letters[ $value_c % 23];
      return $check == $calculated_letter;
}

function validate_cif($value)
{
	$letter = substr($value,0,1);
	$check = substr($value,strlen($value)-1,1);
	$value = substr($value,1,strlen($value)-1);
	$n1 = $n2 = 0;
	for ($i=0;$i<strlen($value);$i++)
	{
		$number = strtoupper(substr($value,$i+1,1));
		if ($i %2 != 0){
			$n1 +=$number;
		} else {
			$n2 =((2*$number) % 10) + ((2* $number) / 10);
		}
	}
	$calculated_number = (10 - (($n1 + $n2)  %10 )) %10;
	$calculated_letter = chr(64 + $calculated_number);
	if ($letter == "Q" || $letter == "P" || $letter == "N" || $letter == "S") {
		return $check == $calculated_letter;
	} else {
		return $check == $calculated_number;
	}
}

$errors=array();
	if (array_key_exists("do",$_REQUEST)){
	if (!eregi("^([A-Z]{1}[0-9]{8}|[0-9]{8}[A-Z]{1})$", $_REQUEST["cif"])) { $errors[] = "cif";}
	if (!validate_cif($_REQUEST["cif"]) && !validate_nif($_REQUEST["cif"])) { $errors[] = "cif";}
	$organizacion = new organizacion();
	if (count($organizacion->getList("cif='".$_REQUEST["cif"]."'")) > 0) { $errors[] = "cif";}
	if (count($organizacion->getList("username='".$_REQUEST["mail"]."'")) > 0) { $errors[] = "mail";}

	if (!eregi("^[[:alnum:]][a-z0-9_.-]*@[a-z0-9.-]+\.[a-z]{2,4}$", $_REQUEST['mail'])) { $errors[] = "mail";}
	if (!$_REQUEST["nombre"]) { $errors[] = "nombre";}
	if (count($errors)==0){
		try{
			$organizacion = new organizacion();
			$organizacion->nombre = $_REQUEST["nombre"];
			$organizacion->cif = $_REQUEST["cif"];
			$organizacion->username = $_REQUEST["mail"];
			$organizacion->password = md5(uniqid(rand()));
			$organizacion->hashCreacion = md5(uniqid(rand()));
			$organizacion->insert();
			require("class.phpmailer.php");
			$mail = new PHPMailer();

			$mail->From     =  $settings["mail.from"] ;
			$mail->FromName = "SLDirect";
			$mail->Host     = $settings["mail.host"] ;
			$mail->Mailer   = $settings["mail.mailer"] ;
			$mail->Subject  = "Registro en SLDirect de ".$organizacion->nombre;
			$mail->Username = $settings["mail.smtp.username"];
			$mail->Password = $settings["mail.smtp.password"];
			$mail->SMTPAuth = true;

			$text_body  = "Confirmaci&oacute;n de Registro en SLDirect";
			$text_body .= "

Usted recibe este correo electr&oacute;nico porque usted o alguien de su organizaci&oacute;n ha procedido al registro de su organizaci&oacute;n en SLDirect, El directorio de empresas de Software Libre de Cenatic.

";			
			$text_body .= "Para finalizar el registro es necesario que apunte su navegador a la siguiente direcci&oacute;n

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
			header ("location:registrar_done.php");
			die();
		} catch (Exception $e){

		}
	} 
}

html_header();
includeLang("registrar.php",array("errors" => $errors));
html_footer();
?>
