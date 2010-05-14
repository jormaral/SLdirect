<?php
$warnings = array ();
if ($_SERVER ["REQUEST_METHOD"] == "POST") {
  $values = array ();
  if ($_POST ["system_url"] == "") $warnings ["system_url"] = "Debe especificarse"; else $values ["baseUrl"] = $_POST ["system_url"];
  if ($_POST ["system_tmp"] == "") $warnings ["system_tmp"] = "Debe especificarse"; else $values ["sessionpath"] = $_POST ["system_tmp"];
  if ($_POST ["mysql_host"] == "") $warnings ["mysql_host"] = "Debe especificarse"; else $values ["datadriver.mysql.host"] = $_POST ["mysql_host"];
  if ($_POST ["mysql_username"] == "") $warnings ["mysql_username"] = "Debe especificarse"; else $values ["datadriver.mysql.username"] = $_POST ["mysql_username"];
  if ($_POST ["mysql_password"] == "") $warnings ["mysql_password"] = "Debe especificarse"; else $values ["datadriver.mysql.password"] = $_POST ["mysql_password"];
  if ($_POST ["mysql_database"] == "") $warnings ["mysql_database"] = "Debe especificarse"; else $values ["datadriver.mysql.database"] = $_POST ["mysql_database"];
  if ($_POST ["mail_host"] == "") $warnings ["mail_host"] = "Debe especificarse"; else $values ["mail.host"] = $_POST ["mail_host"];
  if ($_POST ["mail_username"] == "") $warnings ["mail_username"] = "Debe especificarse"; else $values ["mail.smtp.username"] = $_POST ["mail_username"];
  if ($_POST ["mail_password"] == "") $warnings ["mail_password"] = "Debe especificarse"; else $values ["mail.smtp.password"] = $_POST ["mail_password"];
  if ($_POST ["mail_from"] == "") $warnings ["mail_from"] = "Debe especificarse"; else $values ["mail.from"] = $_POST ["mail_from"];
  if ($_POST ["maps_key"] == "") $warnings ["maps_key"] = "Debe especificarse"; else $values ["gmaps.key"] = $_POST ["maps_key"];
  if ($_POST ["maps_lat"] == "") $warnings ["maps_lat"] = "Debe especificarse"; else $values ["mainmap.lat"] = $_POST ["maps_lat"];
  if ($_POST ["maps_long"] == "") $warnings ["maps_long"] = "Debe especificarse"; else $values ["mainmap.long"] = $_POST ["maps_long"];
  if ($_POST ["maps_zoom"] == "") $warnings ["maps_zoom"] = "Debe especificarse"; else $values ["mainmap.zoom"] = $_POST ["maps_zoom"];
  if ($_POST ["directory_host"] != "") {
    $values ["directory.server"] = $_POST ["directory_host"];
    if ($_POST ["directory_username"] == "") $warnings ["directory_username"] = "Debe especificarse"; else $values ["directory.path"] = $_POST ["directory_path"];
    if ($_POST ["directory_password"] == "") $warnings ["directory_password"] = "Debe especificarse"; else $values ["directory.username"] = $_POST ["directory_username"];
    if ($_POST ["directory_path"] == "") $warnings ["directory_path"] = "Debe especificarse"; else $values ["directory.password"] = $_POST ["directory_password"];
  }
  if (count ($warnings) == 0) {
    echo ("<html>\n  <head>\n    <style>\nbody { background: #e0e0e0; font: 8pt tahoma; }\nbutton { border-width: 1px; border-color: #ffffff; width: 80px; height: 22px; background: #e0e0e0; font: 8pt tahoma; }\nbutton img { margin-right: 4px; width: 16px; height: 16px; vertical-align: -4px; }\n    </style>\n  </head>\n  <body>\n");
    echo ("Escribiendo fichero de configuraci&oacute;n ... ");
    $values ["session.auth.source"] = "cenaticOrganization";
    $values ["mail.mailer"] = "smtp";
    $values ["directory.port"] = 80;
    $config = "<?php\n";
    foreach ($values as $key => $value) $config .= "$" . "settings [\"" . $key . "\"] = \"" . $value . "\";\n";
    if (@file_put_contents ("settings/settings.php", $config)) echo ("ok<br />\n");
    else die ("error de escritura");
    echo ("Creando base de datos ... ");
    if (@mysql_connect ($_POST ["mysql_host"], $_POST ["mysql_username"], $_POST ["mysql_password"])) echo ("ok<br />\n"); else die ("error de conexi&oacute;n");
    mysql_query ("create database " . $_POST ["mysql_database"] . ";");
    mysql_select_db ($_POST ["mysql_database"]);
    system ("mysql -h" . $_POST ["mysql_host"] . " -u" . $_POST ["mysql_username"] . " -p" . $_POST ["mysql_password"] . " " . $_POST ["mysql_database"] . " < z_database.sql");
    if ($_POST ["directory_host"] != "") {
      echo ("Cacheando datos centrales ... ");
      include ("z_cache.php");
      echo ("ok<br />\n");
    }
    echo ("    <br />\n    <button onclick=\"location = '.';\"><img src=\"img/icons/accept.png\" />Aceptar</button><br />\n  </body>\n</html>\n");
    die ();
  }
} else {
  $_POST ["system_tmp"] = "/tmp";
  $_POST ["maps_zoom"] = 8;
}
?>
<html>
  <head>
    <style>
body { background: #e0e0e0; font: 8pt tahoma; cursor: default; }
button { border-width: 1px; border-color: #ffffff; width: 80px; height: 22px; background: #e0e0e0; font: 8pt tahoma; }
button img { margin-right: 4px; width: 16px; height: 16px; vertical-align: -4px; }
span.tooltip { margin: 0 4px; display: inline-block; width: 16px; height: 16px; background: url(img/icons/help.png) no-repeat; vertical-align: -4px; }
span.warning { position: absolute; display: inline-block; margin-left: 8px; background: #ffe000 url(img/icons/warning.png) no-repeat 1px 1px; padding: 2px 2px 2px 20px; white-space: nowrap; }
input { margin: 0; border: 1px inset; width: 128px; font: 8pt tahoma; }
table.form { font: 8pt tahoma; }
table.form td { padding: 0 8px 8px 0; }
table.form td.section { font-weight: bold; color: #808080; }
    </style>
  </head>
  <body onload="document.getElementsByTagName ('input')[0].focus ();">
    <form method="post">
<?
echo ("      <table class=\"form\" cellspacing=\"0\">\n");
echo ("        <tr><td colspan=\"2\" class=\"section\">Sistema</td></tr>\n");
echo ("        <tr><td>URL</td><td><input name=\"system_url\" value=\"" . htmlspecialchars ($_POST ["system_url"]) . "\" /><span class=\"tooltip\" title=\"URL de esta instalaci&oacute;n del SLDirect\"></span>" . ($warnings ["system_url"] ? "<span class=\"warning\">" . $warnings ["system_url"] . "</span>" : "") . "</td></tr>\n");
echo ("        <tr><td>TMP</td><td><input name=\"system_tmp\" value=\"" . htmlspecialchars ($_POST ["system_tmp"]) . "\" /><span class=\"tooltip\" title=\"Carpeta de almacenamiento temporal\"></span>" . ($warnings ["system_tmp"] ? "<span class=\"warning\">" . $warnings ["system_tmp"] . "</span>" : "") . "</td></tr>\n");
echo ("        <tr><td colspan=\"2\" class=\"section\">Base de datos</td></tr>\n");
echo ("        <tr><td>Host</td><td><input name=\"mysql_host\" value=\"" . htmlspecialchars ($_POST ["mysql_host"]) . "\" />" . ($warnings ["mysql_host"] ? "<span class=\"warning\">" . $warnings ["mysql_host"] . "</span>" : "") . "</td></tr>\n");
echo ("        <tr><td>Username</td><td><input name=\"mysql_username\" style=\"width: 96px;\" value=\"" . htmlspecialchars ($_POST ["mysql_username"]) . "\" />" . ($warnings ["mysql_username"] ? "<span class=\"warning\">" . $warnings ["mysql_username"] . "</span>" : "") . "</td></tr>\n");
echo ("        <tr><td>Password</td><td><input name=\"mysql_password\" style=\"width: 96px;\" value=\"" . htmlspecialchars ($_POST ["mysql_password"]) . "\" />" . ($warnings ["mysql_password"] ? "<span class=\"warning\">" . $warnings ["mysql_password"] . "</span>" : "") . "</td></tr>\n");
echo ("        <tr><td>Database</td><td><input name=\"mysql_database\" style=\"width: 96px;\" value=\"" . htmlspecialchars ($_POST ["mysql_database"]) . "\" /><span class=\"tooltip\" title=\"Ser&aacute; creada tras la configuraci&oacute;n\"></span>" . ($warnings ["mysql_database"] ? "<span class=\"warning\">" . $warnings ["mysql_database"] . "</span>" : "") . "</td></tr>\n");
echo ("        <tr><td colspan=\"2\" class=\"section\">Correo electr&oacute;nico</td></tr>\n");
echo ("        <tr><td>Host</td><td><input name=\"mail_host\" value=\"" . htmlspecialchars ($_POST ["mail_host"]) . "\" />" . ($warnings ["mail_host"] ? "<span class=\"warning\">" . $warnings ["mail_host"] . "</span>" : "") . "</td></tr>\n");
echo ("        <tr><td>Username</td><td><input name=\"mail_username\" style=\"width: 96px;\" value=\"" . htmlspecialchars ($_POST ["mail_username"]) . "\" />" . ($warnings ["mail_username"] ? "<span class=\"warning\">" . $warnings ["mail_username"] . "</span>" : "") . "</td></tr>\n");
echo ("        <tr><td>Password</td><td><input style=\"width: 96px;\" name=\"mail_password\" value=\"" . htmlspecialchars ($_POST ["mail_password"]) . "\" />" . ($warnings ["mail_password"] ? "<span class=\"warning\">" . $warnings ["mail_password"] . "</span>" : "") . "</td></tr>\n");
echo ("        <tr><td>Remitente</td><td><input name=\"mail_from\" value=\"" . htmlspecialchars ($_POST ["mail_from"]) . "\" />" . ($warnings ["mail_from"] ? "<span class=\"warning\">" . $warnings ["mail_from"] . "</span>" : "") . "</td></tr>\n");
echo ("      </table>\n");
echo ("      <table class=\"form\" cellspacing=\"0\" style=\"position: absolute; left: 352px; top:8px;\">\n");
echo ("        <tr><td colspan=\"2\" class=\"section\">Google maps</td></tr>\n");
echo ("        <tr><td>Clave</td><td><input name=\"maps_key\" value=\"" . htmlspecialchars ($_POST ["maps_key"]) . "\" /><span class=\"tooltip\" title=\"Obtener una en http://code.google.com/apis/maps/\"></span>" . ($warnings ["maps_key"] ? "<span class=\"warning\">" . $warnings ["maps_key"] . "</span>" : "") . "</td></tr>\n");
echo ("        <tr><td>Latitud</td><td><input name=\"maps_lat\" style=\"width: 64px;\" value=\"" . htmlspecialchars ($_POST ["maps_lat"]) . "\" />" . ($warnings ["maps_lat"] ? "<span class=\"warning\">" . $warnings ["maps_lat"] . "</span>" : "") . "</td></tr>\n");
echo ("        <tr><td>Longitud</td><td><input name=\"maps_long\" style=\"width: 64px;\" value=\"" . htmlspecialchars ($_POST ["maps_long"]) . "\" />" . ($warnings ["maps_long"] ? "<span class=\"warning\">" . $warnings ["maps_long"] . "</span>" : "") . "</td></tr>\n");
echo ("        <tr><td>Zoom</td><td><input name=\"maps_zoom\" style=\"width: 32px;\" value=\"" . htmlspecialchars ($_POST ["maps_zoom"]) . "\" />" . ($warnings ["maps_zoom"] ? "<span class=\"warning\">" . $warnings ["maps_zoom"] . "</span>" : "") . "</td></tr>\n");
echo ("        <tr><td colspan=\"2\" class=\"section\">Directorio central (opcional)</td></tr>\n");
echo ("        <tr><td>Host</td><td><input name=\"directory_host\" value=\"" . htmlspecialchars ($_POST ["directory_host"]) . "\" />" . ($warnings ["directory_host"] ? "<span class=\"warning\">" . $warnings ["directory_host"] . "</span>" : "") . "</td></tr>\n");
echo ("        <tr><td>Username</td><td><input name=\"directory_username\" style=\"width: 96px;\" value=\"" . htmlspecialchars ($_POST ["directory_username"]) . "\" />" . ($warnings ["directory_username"] ? "<span class=\"warning\">" . $warnings ["directory_username"] . "</span>" : "") . "</td></tr>\n");
echo ("        <tr><td>Password</td><td><input name=\"directory_password\" style=\"width: 96px;\" value=\"" . htmlspecialchars ($_POST ["directory_password"]) . "\" />" . ($warnings ["directory_password"] ? "<span class=\"warning\">" . $warnings ["directory_password"] . "</span>" : "") . "</td></tr>\n");
echo ("        <tr><td>Path</td><td><input name=\"directory_path\" value=\"" . htmlspecialchars ($_POST ["directory_path"]) . "\" />" . ($warnings ["directory_path"] ? "<span class=\"warning\">" . $warnings ["directory_path"] . "</span>" : "") . "</td></tr>\n");
echo ("      </table>\n");
?>
      <button type="submit"><img src="img/icons/accept.png" />Aceptar</button><br />
    </form>
  </body>
</html>
