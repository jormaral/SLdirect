<?php
include ("include.php");
mp_header();
$presenciaCSIL = new presenciaCSIL();
$list = $presenciaCSIL->getList("organizacion_id=".$_REQUEST["organizacion_id"]);
if (!$list[0]->id){ 
?>
	<p>La empresa no ha rellenado los datos sobre su asistencia al Congreso</p>
	<?php
	die();
}

$presenciaCSIL->open($list[0]->id)	;

?>
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
						<?php if ($presenciaCSIL->dec1 == 1) { ?><img src="media/icons/16x16/accept.png" /> <?php } else {?> <img src="media/icons/16x16/cancel.png" /><?php  }?>
					</td>
					<td style="border:solid 1px #dddddd;text-align:center">
						<?php if ($presenciaCSIL->dec2 == 1) { ?><img src="media/icons/16x16/accept.png" /> <?php } else {?> <img src="media/icons/16x16/cancel.png" /><?php  }?>
					</td>
					<td style="border:solid 1px #dddddd;text-align:center">
						<?php if ($presenciaCSIL->dec3 == 1) { ?><img src="media/icons/16x16/accept.png" /> <?php } else {?> <img src="media/icons/16x16/cancel.png" /><?php  }?>
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
							<?php if ($presenciaCSIL->horario_morning == 1) { ?><img src="media/icons/16x16/accept.png" /> <?php } else {?> <img src="media/icons/16x16/cancel.png" /><?php  }?>
						</td>
						<td style="border:solid 1px #dddddd;text-align:center">
							<?php if ($presenciaCSIL->horario_evening == 1) { ?><img src="media/icons/16x16/accept.png" /> <?php } else {?> <img src="media/icons/16x16/cancel.png" /><?php  }?>
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
			<?php echo $presenciaCSIL->persona_nombre ?>
		</td>
	</tr>
	<tr>
		<td class="key">
			Correo Electr&oacute;nico
		</td>
		<td>
			<a href="mailto:<?php echo $presenciaCSIL->persona_email?>"><?php echo $presenciaCSIL->persona_email?></a>
		</td>
	</tr>
	<tr>
		<td class="key">
			Tel&eacute;fono de Contacto
		</td>
		<td>
			<?php echo $presenciaCSIL->persona_telefono?>
		</td>
	</tr>
	<tr>
		<td class="key">
			M&oacute;vil de Contacto
		</td>
		<td>
			<?php echo $presenciaCSIL->persona_movil?>
		</td>
	</tr>
	

</table>
<?php
mp_footer();
?>
