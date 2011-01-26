<?php
if ($_POST["latitud"]) header ("location:empresa.php#sedes");

include("include.php");
$organizacion = new organizacion();
$session = new session();
if ($session->username == "") { header ("location:login.php"); die(); }
$errors = array();
html_header();
$organizacion->open($session->userid);

if (count($organizacion))
error_reporting(0);
$sede = new organizacionSede();
$sede->open ($_REQUEST["sede_id"]);
if ($_POST["latitud"]){
	foreach ($sede as $k => $v){
		$sede->$k = $_POST[$k];
	}
	$sede->provincia_id = $_POST["provincia_id"];
	$sede->organizacion_id = $organizacion->id;
	$sede->id = $_REQUEST["sede_id"];
	$sede->update();
}


includeLang("modificarSede.php",array("errors" => $errors , "organizacion" => $organizacion , "sede" => $sede, "asociaciones" => $asociaciones, "completeness" => $completeness , "completeness_reason" => $completeness_reason));
html_footer();
?>