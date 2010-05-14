<?php			
include("include.php");
$session = new session();
$organizacionUser = new organizacion();
if ($session->username != "") {
	
	$organizacionUser->open($session->userid);
}
$database = database::singleton("mysql");
date_default_timezone_set('Europe/Madrid');

$date = dateEURtoUS($_REQUEST['fecha']);

$events_sql = "SELECT eventos.id, titulo, eventos.descripcion, localizacion, organizacion_id, organizaciones.nombre as organizacion, date_format(fecha,\"%d-%m-%Y\") as fecha_formatted, fecha FROM eventos INNER JOIN organizaciones ON organizacion_id = organizaciones.id WHERE fecha = date('" . $date . "')";
$events_sql = "SELECT eventos.id, titulo, eventos.descripcion, localizacion, eventos.organizacion_id, organizaciones.nombre as organizacion, date_format(fecha,\"%d-%m-%Y\") as fecha_formatted, fecha, group_concat(concat('{',organizaciones_eventos.organizacion_id,',\\'',organizaciones_asistentes.nombre,'\\'}') ) as asistentes_nombres, count(organizaciones_eventos.id) as numero_asistentes FROM eventos INNER JOIN organizaciones ON eventos.organizacion_id = organizaciones.id LEFT JOIN organizaciones_eventos on organizaciones_eventos.evento_id = eventos.id left join organizaciones as organizaciones_asistentes on organizaciones_asistentes.id = organizaciones_eventos.organizacion_id WHERE fecha = date('" . $date . "') group by eventos.id";

$database->query($events_sql);
$indice = 0;
$today = strtotime("today");
foreach ($database->rows as $event) {
				echo("<div id=\"suscriptionDiv" . $event->id . "\" ><h3><a href=\"#\">" . $event->fecha_formatted . " > " . $event->titulo . "</a></h3>
				<div><div style=\"float:right;\"><b>Asistentes: </b>" . $event->numero_asistentes . "</div><div><b>Empresa: </b>" . $event->organizacion . "<br/><b>Localización: </b>" . $event->localizacion . "<br/><b>Descripción: </b>" . $event->descripcion . "</div>
				<div style=\"text-align:right;padding:8px;\">");
				
				$currentDate = strtotime($event->fecha);
				if($currentDate >=$today){
				if($session->username == "") {
					echo("<b>Inicie sesión para suscribirse a este evento</b>");
				} else {
					if($session->userid==$event->organizacion_id) {
						echo("<b>Usted es el organizador de este evento</b>");
					} else {
						$suscrito = false;
						foreach($organizacionUser->getChildren("organizacionEvento") as $organizacionEvento) {
							if($organizacionEvento->evento_id->id == $event->id) $suscrito = true;
						}
						echo ($suscrito?"<b>Usted ya esta suscrito a este evento</b>":"<button title=\"Suscribirse a este evento\" onclick=\"suscribeTo(" . $event->id . "," . $session->userid . "," . $indice . ")\"><img src=\"media/icons/16x16/calendar_edit.png\"></button>");
					}
				}}
				$indice++;
				echo("</div>
				</div></div>");
	}
?>