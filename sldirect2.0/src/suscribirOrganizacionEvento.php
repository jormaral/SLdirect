<?php			
include("include.php");



$organizacionEvento = new organizacionEvento();
$organizacionEvento->organizacion_id = $session->userid;
$organizacionEvento->evento_id = $_REQUEST["event_id"];
$organizacionEvento->insert();
//$organizacionEvento->open(mysql_insert_id());

$session = new session();
$organizacionUser = new organizacion();
if ($session->username != "") {
	
	$organizacionUser->open($session->userid);
}

$event = new evento();
$event->open($_REQUEST["event_id"]);

				echo("<h3><a href=\"#\">" . dateUStoEUR($event->fecha) . " > " . $event->titulo . "</a></h3>
				<div><div style=\"float:right;\"><b>Asistentes: </b>" . count($event->getChildren("organizacionEvento")) . "</div><div><b>Empresa: </b>" . $event->organizacion_id->nombre . "<br/><b>Localización: </b>" . $event->localizacion . "<br/><b>Descripción: </b>" . $event->descripcion . "</div>
				<div style=\"text-align:right;padding:8px;\">");
				
				if($session->username == "") {
					echo("<b>Inicie sesión para suscribirse a este evento</b>");
				} else {
					if($session->userid==$event->organizacion_id->id) {
						echo("<b>Usted es el organizador de este evento</b>");
					} else {
						$suscrito = false;
						foreach($organizacionUser->getChildren("organizacionEvento") as $organizacionEvento) {
							if($organizacionEvento->evento_id->id == $event->id) $suscrito = true;
						}
						echo ($suscrito?"<b>Usted ya esta suscrio a este evento</b>":"<button onclick=\"suscribeTo(" . $event->id . "," . $session->userid . ")\"><img src=\"media/icons/16x16/calendar_edit.png\"></button>");
					}
				}
				
				echo("</div>
				</div>");
?>