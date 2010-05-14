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

$evento = new evento();
$evento->open($_REQUEST["event_id"]);

echo("<div style=\"float:right;\"><b>Asistentes: </b>" . count($evento->getChildren("organizacionEvento")) . "</div>
<p style=\"font-size:1.2em;color:#99008b\"><b>" . $evento->titulo . "</b></p>
<p><b>Localización: </b>" . $evento->localizacion . "</p>
<p><b>Fecha: </b>" . dateUStoEUR($evento->fecha) . "</p>
<p>" . $evento->descripcion . "</p>
<div style=\"text-align:right;padding:8px;\">");
$currentDate = strtotime($evento->fecha);
$today = strtotime("today");
if($currentDate >=$today){
if($session->username == "") {
echo("<b>Inicie sesión para suscribirse a este evento</b>");
} else {

if($session->userid==$evento->organizacion_id->id) {
	echo("<b>Usted es el organizador de este evento</b>");
} else {
	$suscrito = false;
	foreach($organizacionUser->getChildren("organizacionEvento") as $organizacionEvento) {
		if($organizacionEvento->evento_id->id == $evento->id) $suscrito = true;
	}
	echo ($suscrito?"<b>Usted ya esta suscrito a este evento</b>":"<button title=\"Suscribirse a este evento\" onclick=\"suscribeTo(" . $evento->id . "," . $session->userid . ")\"><img src=\"media/icons/16x16/calendar_edit.png\"></button>");
}
}}
?>