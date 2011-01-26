<?php
require_once ("include.php");
mp_header();
$presenciaCSIL = new presenciaCSIL();
$session = new session();
if ($session->username == "") {  die("NA"); }
$errors = array();
$list = $presenciaCSIL->getList("organizacion_id=".$session->userid);
$presenciaCSIL->open($list[0]->id);
	
try{
if (array_key_exists("persona_nombre",$_REQUEST) && $_REQUEST["persona_nombre"]){
	$presenciaCSIL->persona_nombre = $_REQUEST["persona_nombre"];
	$presenciaCSIL->persona_email = $_REQUEST["persona_email"];
	$presenciaCSIL->persona_telefono = $_REQUEST["persona_telefono"];
	$presenciaCSIL->persona_movil = $_REQUEST["persona_movil"];
	$presenciaCSIL->dec1 = $_REQUEST["dec1"] ? 1 : 0 ;
	$presenciaCSIL->dec2 = $_REQUEST["dec2"] ? 1 : 0 ;
	$presenciaCSIL->dec3 = $_REQUEST["dec3"] ? 1 : 0 ;
	$presenciaCSIL->horario_morning = $_REQUEST["horario_morning"] ? 1 : 0;
	$presenciaCSIL->horario_evening = $_REQUEST["horario_evening"] ? 1 : 0;
	
	if ($presenciaCSIL->id){
		$presenciaCSIL->organizacion_id=$presenciaCSIL->organizacion_id->id;
		$presenciaCSIL->update();
		$presenciaCSIL->open($session->userid);
		
	} else {
		$presenciaCSIL->organizacion_id=$session->userid;
		$presenciaCSIL->insert();		
		$presenciaCSIL->open(mysql_insert_id());
	}
	?>
	<div style="border:solid 3px green; padding:5px;margin:10px;font-size:16px;color:green"> Informaci&oacute;n Guardada!</div>
	<?php
}
} catch (Exception $e){
	?>
		<div style="border:solid 3px red; padding:5px;margin:10px;font-size:16px;color:red"> Ocurri&oacute; un error guardando la informaci&oacute;!</div>
	<?php
}

?>
<form method="post" action="congreso_private.php" style="padding:10px;">
<table class="viewForm" style="font-size:10px">
	<tr>
		<td class="key">
			D&iacute;as de Presencia en el congreso
		</td>
		<td style="text-align:right">
			<table style="font-size:10px;border-collapse:collapse">
				<tr>
					<td style="border:solid 1px #dddddd;background:#eeeeee">
						1 de Diciembre
					</td>
					<td style="border:solid 1px #dddddd;background:#eeeeee">
						2 de Diciembre						
					</td>
					<td style="border:solid 1px #dddddd;background:#eeeeee">
						3 de Diciembre
					</td>
				</tr>
				<tr>
					<td style="border:solid 1px #dddddd;text-align:center">
						<input name="dec1" type="checkbox" <?php echo $presenciaCSIL->dec1 == 1 ? "checked" : ""?> />
					</td>
					<td style="border:solid 1px #dddddd;text-align:center">
						<input name="dec2" type="checkbox" <?php echo $presenciaCSIL->dec2 == 1 ? "checked" : ""?> />
					</td>
					<td style="border:solid 1px #dddddd;text-align:center">
						<input name="dec3" type="checkbox" <?php echo $presenciaCSIL->dec3 == 1 ? "checked" : ""?> />
					</td>
				</tr>
			</table>
		</td>
	</tr>
	<tr>
		<td class="key">
			Horario de Asistencia
		</td>
		<td>
				<table style="font-size:10px;border-collapse:collapse">
					<tr>
						<td style="border:solid 1px #dddddd;background:#eeeeee">
							Ma&ntilde;ana
						</td>
						<td style="border:solid 1px #dddddd;background:#eeeeee">
							Tarde
						</td>
					</tr>
					<tr>
						<td style="border:solid 1px #dddddd;text-align:center">
							<input name="horario_morning" type="checkbox" <?php echo $presenciaCSIL->horario_morning == 1 ? "checked" : ""?> />
						</td>
						<td style="border:solid 1px #dddddd;text-align:center">
							<input name="horario_evening" type="checkbox" <?php echo $presenciaCSIL->horario_evening == 1 ? "checked" : ""?> />
						</td>
					</tr>
				</table>
		</td>
	</tr>
	<tr>
		<td class="key">
			Persona de Contacto
		</td>
		<td>
			<input type="text" name="persona_nombre" value="<?php echo $presenciaCSIL->persona_nombre ?>" />
		</td>
	</tr>
	<tr>
		<td class="key">
			Correo Electr&oacute;nico
		</td>
		<td>
			<input type="text" name="persona_email" value="<?php echo $presenciaCSIL->persona_email ?>" />
		</td>
	</tr>
	<tr>
		<td class="key">
			Tel&eacute;fono de Contacto
		</td>
		<td>
			<input type="text" name="persona_telefono" value="<?php echo $presenciaCSIL->persona_telefono?>" />
		</td>
	</tr>
	<tr>
		<td class="key">
			M&oacute;vil de Contacto
		</td>
		<td>
			<input type="text" name="persona_movil" value="<?php echo $presenciaCSIL->persona_movil?>" />
		</td>
	</tr>
</table>
<br /><br />
<input type="submit" value="Guardar Informaci&oacute;n" />
</form>
<?php
mp_footer();
?>
