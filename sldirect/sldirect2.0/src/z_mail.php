<?php
include ("include.php");
require("class.phpmailer.php");
$demandas = new capacidadDemandaOrganizacion();
$organizacion = new organizacion();
echo "Parsing...\n";
foreach ($demandas->getList("anunciado=0") as $demanda){
	echo "[p] ".date("Y/m/d",time())." ". $post->titulo."\n";
	$sent=array();
	foreach ($organizacion->getList () as $orga){
		echo "[.] ".$orga->email."\n";
		$mail = new PHPMailer();
		$mail->From     =  $settings["mail.from"] ;
		$mail->FromName = "Demandas SLDirect";
		$mail->Host     = $settings["mail.host"] ;
		$mail->Mailer   = $settings["mail.mailer"] ;
		$mail->Subject  = "Nueva demanda en SLDirect de: '".str_replace("\\","",$demanda->organizacion_id->nombre)."'";
		$mail->Port = $settings["mail.port"];
		$mail->SMTPAuth = true;		
		$text_body  = "<div style=\"font-size:18px;letter-spacing:0px;font-weight:bold;margin-top:10px;margin-bottom:20px;background:#dddddd;border-bottom: solid 2px #aaaaaa;padding:4px;\">".$demanda->organizacion_id->nombre." ha realizado la siguiente demanda</div>\n";
		$text_body .= "<p>Capacidad: " . $demanda->capacidad_id->nombre . "</p><p>Sector: " . $demanda->sector_id->nombre . "</p>Descripcion: <span style=\"font-size:12px;\">".$demanda->descripcion."</span>\n";			
    $text_body .= "\nNOTA: Usted recibe este correo electr&oacute;nico porque usted o alguien de su organizaci&oacute;n ha procedido al registro en SLDirect\n\n";			
    $text_body .= "Gracias por registrarse en SLDirect.\n\n";			
		$mail->Body = "<html><body>".nl2br ($text_body)."</body></html>";
		$mail->AltBody = htmlentities(strip_tags($text_body));
		$mail->AddAddress($orga->email);			
		$mail->Username = $settings["mail.smtp.username"];
		$mail->Password = $settings["mail.smtp.password"];
		if (!array_search($orga->email,$sent)){
				$sent[] = $orga->email;
				$mail->Send();
		} else echo "   [!] Dupe!\n";
	}
	$demanda->anunciado = 1;
	$demanda->organizacion_id = $demanda->organizacion_id->id;
	$demanda->capacidad_id = $demanda->capacidad_id->id;
	$demanda->sector_id = $demanda->sector_id->id;
	$demanda->update();
}
echo "Done!\n";
?>
