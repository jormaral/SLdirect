CREATE TABLE `asociaciones` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `nombre` text,
  `icon` text,
  `url` text,
  PRIMARY KEY (`id`)
) DEFAULT CHARSET=utf8;
CREATE TABLE `capacidades` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `cpc` text,
  `isic` text,
  `nombre` text,
  `descripcion` text,
  `categoria` text,
  PRIMARY KEY (`id`)
) CHARSET=utf8;
CREATE TABLE `capacidades_demandas_organizaciones` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `organizacion_id` int(11) DEFAULT NULL,
  `capacidad_id` int(11) DEFAULT NULL,
  `sector_id` int(11) DEFAULT NULL,
  `descripcion` blob,
  `anunciado` tinyint(1) NOT NULL,
  PRIMARY KEY (`id`)
) CHARSET=utf8;
CREATE TABLE `capacidades_ofertas_organizaciones` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `capacidad_id` int(11) DEFAULT NULL,
  `organizacion_id` int(11) DEFAULT NULL,
  `sector_id` int(11) DEFAULT NULL,
  `recursos` int(11) DEFAULT NULL,
  `porcentajeFacturacion` int(11) DEFAULT NULL,
  `puntuacion` int(11) DEFAULT NULL,
  `descripcion` blob,
  PRIMARY KEY (`id`)
) CHARSET=utf8;
CREATE TABLE `clases_empresas` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` text,
  PRIMARY KEY (`id`)
) CHARSET=utf8;
CREATE TABLE `directorios` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` text,
  `password` text,
  `token` text,
  PRIMARY KEY (`id`)
) CHARSET=utf8;
CREATE TABLE `eventos` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `organizacion_id` int(11) NOT NULL,
  `titulo` text,
  `descripcion` text,
  `fecha` date DEFAULT NULL,
  `localizacion` text,
  PRIMARY KEY (`id`)
) CHARSET=utf8;
CREATE TABLE `formas_juridicas` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `nombre` text,
  PRIMARY KEY (`id`)
) CHARSET=utf8;
CREATE TABLE `organizaciones` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `directorio_id` int(11) DEFAULT NULL,
  `nombre` text,
  `descripcion` text,
  `web` text,
  `news_title` varchar(255) DEFAULT NULL,
  `news_body` blob,
  `formaJuridica_id` int(11) DEFAULT NULL,
  `claseEmpresa_id` int(11) DEFAULT NULL,
  `logo_url` text,
  `direccion` text,
  `organizacionClasificacion_id` int(11) DEFAULT NULL,
  `certificacionCenatic` int(1) DEFAULT NULL,
  `certificacionesCalidad` text,
  `partners` text,
  `grupoEmpresarial` text,
  `actividadesImasD` int(1) DEFAULT NULL,
  `participacionImasD` text,
  `relacionesComunidad` text,
  `localidad` text,
  `provincia_id` int(11) DEFAULT NULL,
  `codigoPostal` int(11) DEFAULT NULL,
  `telefono` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `cif` text,
  `anoConstitucion` int(11) DEFAULT NULL,
  `username` text,
  `password` text,
  `hashCreacion` text,
  PRIMARY KEY (`id`)
) CHARSET=utf8;
CREATE TABLE `organizaciones_asociaciones` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `organizacion_id` int(11) DEFAULT NULL,
  `asociacion_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) CHARSET=utf8;
CREATE TABLE `organizaciones_clasificaciones` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `codigo` int(11) DEFAULT NULL,
  `nombre` text,
  PRIMARY KEY (`id`)
) CHARSET=utf8;
CREATE TABLE `organizaciones_eventos` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `organizacion_id` int(11) NOT NULL,
  `evento_id` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) CHARSET=utf8;
CREATE TABLE `organizaciones_presencia_csil` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `organizacion_id` int(11) DEFAULT NULL,
  `dec1` int(11) DEFAULT NULL,
  `dec2` int(11) DEFAULT NULL,
  `dec3` int(11) DEFAULT NULL,
  `horario_morning` int(11) DEFAULT NULL,
  `horario_evening` int(11) DEFAULT NULL,
  `persona_nombre` varchar(255) DEFAULT NULL,
  `persona_cargo` varchar(255) DEFAULT NULL,
  `persona_email` varchar(255) DEFAULT NULL,
  `persona_telefono` varchar(11) DEFAULT NULL,
  `persona_movil` varchar(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) CHARSET=utf8;
CREATE TABLE `organizaciones_sedes` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `organizacion_id` int(11) DEFAULT NULL,
  `latitud` float DEFAULT NULL,
  `longitud` float DEFAULT NULL,
  `direccion` text,
  `localidad` text,
  `telefonoContacto` text,
  `personaContacto` text,
  `mailContacto` text,
  `provincia_id` int(11) DEFAULT NULL,
  `codigoPostal` int(11) DEFAULT NULL,
  `hombres` int(11) DEFAULT NULL,
  `mujeres` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) CHARSET=utf8;
CREATE TABLE `provincias` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `nombre` text,
  PRIMARY KEY (`id`)
) CHARSET=utf8;
CREATE TABLE `sectores` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `nombre` text,
  PRIMARY KEY (`id`)
) CHARSET=utf8;
INSERT INTO `provincias` VALUES (1,'Guipuzcoa'),(2,'A Coruña'),(3,'Araba'),(4,'Albacete'),(5,'Alicante'),(6,'Almería'),(7,'Asturias'),(8,'Ávila'),(9,'Badajoz'),(10,'Barcelona'),(11,'Bizkaia'),(12,'Burgos'),(13,'Cáceres'),(14,'Cádiz'),(15,'Cantabria'),(16,'Castellón'),(17,'Ceuta'),(18,'Ciudad Real'),(19,'Córdoba'),(20,'Cuenca'),(21,'Girona'),(22,'Granada'),(23,'Guadalajara'),(24,'Huelva'),(25,'Huesca'),(26,'Illes Balears'),(27,'Jaén'),(28,'La Rioja'),(29,'Las Palmas'),(30,'León'),(31,'Lleida'),(32,'Lugo'),(33,'Madrid'),(34,'Málaga'),(35,'Melilla'),(36,'Murcia'),(37,'Navarra'),(38,'Ourense'),(39,'Palencia'),(40,'Pontevedra'),(41,'Salamanca'),(42,'Santa Cruz de Tenerife'),(43,'Segovia'),(44,'Sevilla'),(45,'Soria'),(46,'Tarragona'),(47,'Teruel'),(48,'Toledo'),(49,'Valencia'),(50,'Valladolid'),(51,'Zamora'),(52,'Zaragoza');
