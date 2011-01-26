DROP DATABASE IF EXISTS centraldir;
CREATE DATABASE IF NOT EXISTS centraldir
DEFAULT CHARACTER SET latin1
DEFAULT COLLATE latin1_swedish_ci;

USE centraldir;

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `eventos`
--

DROP TABLE IF EXISTS `eventos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `eventos` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `titulo` varchar(255) DEFAULT NULL,
  `descripcion` varchar(255) DEFAULT NULL,
  `fecha` datetime DEFAULT NULL,
  `localizacion` varchar(255) DEFAULT NULL,
  `organizacion_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKADF441FE68863668` (`organizacion_id`),
  CONSTRAINT `FKADF441FE68863668` FOREIGN KEY (`organizacion_id`) REFERENCES `organizaciones` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `eventos`
--

LOCK TABLES `eventos` WRITE;
/*!40000 ALTER TABLE `eventos` DISABLE KEYS */;
/*!40000 ALTER TABLE `eventos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `username` varchar(50) NOT NULL,
  `password` varchar(50) DEFAULT NULL,
  `enabled` bit(1) DEFAULT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES ('admin','admin',''),('guest','guest','');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `capacidades_demandas_organizaciones`
--

DROP TABLE IF EXISTS `capacidades_demandas_organizaciones`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `capacidades_demandas_organizaciones` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `organizacion_id` int(11) NOT NULL,
  `capacidad_id` int(11) NOT NULL,
  `sector_id` int(11) NOT NULL,
  `descripcion` varchar(255) DEFAULT NULL,
  `anunciado` bit(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK9C49A43524A949A8` (`sector_id`),
  KEY `FK9C49A435E06C340C` (`capacidad_id`),
  KEY `FK9C49A43568863668` (`organizacion_id`),
  CONSTRAINT `FK9C49A43568863668` FOREIGN KEY (`organizacion_id`) REFERENCES `organizaciones` (`id`),
  CONSTRAINT `FK9C49A43524A949A8` FOREIGN KEY (`sector_id`) REFERENCES `sectores` (`id`),
  CONSTRAINT `FK9C49A435E06C340C` FOREIGN KEY (`capacidad_id`) REFERENCES `capacidades` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `capacidades_demandas_organizaciones`
--

LOCK TABLES `capacidades_demandas_organizaciones` WRITE;
/*!40000 ALTER TABLE `capacidades_demandas_organizaciones` DISABLE KEYS */;
/*!40000 ALTER TABLE `capacidades_demandas_organizaciones` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `clases_empresas`
--

DROP TABLE IF EXISTS `clases_empresas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `clases_empresas` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `clases_empresas`
--

LOCK TABLES `clases_empresas` WRITE;
/*!40000 ALTER TABLE `clases_empresas` DISABLE KEYS */;
/*!40000 ALTER TABLE `clases_empresas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `group_members`
--

DROP TABLE IF EXISTS `group_members`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `group_members` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL,
  `group_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK3B9C77597C7ED3F4` (`username`),
  KEY `FK3B9C77591BBCF867` (`group_id`),
  CONSTRAINT `FK3B9C77591BBCF867` FOREIGN KEY (`group_id`) REFERENCES `groups` (`id`),
  CONSTRAINT `FK3B9C77597C7ED3F4` FOREIGN KEY (`username`) REFERENCES `users` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `group_members`
--

LOCK TABLES `group_members` WRITE;
/*!40000 ALTER TABLE `group_members` DISABLE KEYS */;
/*!40000 ALTER TABLE `group_members` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `capacidades`
--

DROP TABLE IF EXISTS `capacidades`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `capacidades` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(255) DEFAULT NULL,
  `cpc` varchar(255) DEFAULT NULL,
  `isic` varchar(255) DEFAULT NULL,
  `descripcion` varchar(255) DEFAULT NULL,
  `categoria` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `capacidades`
--

LOCK TABLES `capacidades` WRITE;
/*!40000 ALTER TABLE `capacidades` DISABLE KEYS */;
/*!40000 ALTER TABLE `capacidades` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `asociaciones`
--

DROP TABLE IF EXISTS `asociaciones`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `asociaciones` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(255) DEFAULT NULL,
  `icon` varchar(255) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `asociaciones`
--

LOCK TABLES `asociaciones` WRITE;
/*!40000 ALTER TABLE `asociaciones` DISABLE KEYS */;
/*!40000 ALTER TABLE `asociaciones` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `capacidades_ofertas_organizaciones`
--

DROP TABLE IF EXISTS `capacidades_ofertas_organizaciones`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `capacidades_ofertas_organizaciones` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `organizacion_id` int(11) NOT NULL,
  `capacidad_id` int(11) NOT NULL,
  `sector_id` int(11) NOT NULL,
  `recursos` int(11) DEFAULT NULL,
  `porcentajeFacturacion` int(11) DEFAULT NULL,
  `puntuacion` int(11) DEFAULT NULL,
  `descripcion` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKBB1BBB0E24A949A8` (`sector_id`),
  KEY `FKBB1BBB0EE06C340C` (`capacidad_id`),
  KEY `FKBB1BBB0E68863668` (`organizacion_id`),
  CONSTRAINT `FKBB1BBB0E68863668` FOREIGN KEY (`organizacion_id`) REFERENCES `organizaciones` (`id`),
  CONSTRAINT `FKBB1BBB0E24A949A8` FOREIGN KEY (`sector_id`) REFERENCES `sectores` (`id`),
  CONSTRAINT `FKBB1BBB0EE06C340C` FOREIGN KEY (`capacidad_id`) REFERENCES `capacidades` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `capacidades_ofertas_organizaciones`
--

LOCK TABLES `capacidades_ofertas_organizaciones` WRITE;
/*!40000 ALTER TABLE `capacidades_ofertas_organizaciones` DISABLE KEYS */;
/*!40000 ALTER TABLE `capacidades_ofertas_organizaciones` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `organizaciones`
--

DROP TABLE IF EXISTS `organizaciones`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `organizaciones` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(255) DEFAULT NULL,
  `descripcion` varchar(255) DEFAULT NULL,
  `web` varchar(255) DEFAULT NULL,
  `logo_url` varchar(255) DEFAULT NULL,
  `direccion` varchar(255) DEFAULT NULL,
  `certificacionesCalidad` varchar(255) DEFAULT NULL,
  `partners` varchar(255) DEFAULT NULL,
  `grupoEmpresarial` varchar(255) DEFAULT NULL,
  `participacionImasD` varchar(255) DEFAULT NULL,
  `relacionesComunidad` varchar(255) DEFAULT NULL,
  `localidad` varchar(255) DEFAULT NULL,
  `cif` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `hashCreacion` varchar(255) DEFAULT NULL,
  `telefono` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `news_title` varchar(255) DEFAULT NULL,
  `news_body` varchar(255) DEFAULT NULL,
  `certificacionCenatic` bit(1) DEFAULT NULL,
  `actividadesImasD` bit(1) DEFAULT NULL,
  `codigoPostal` int(11) DEFAULT NULL,
  `anoConstitucion` int(11) DEFAULT NULL,
  `directorio_id` int(11) DEFAULT NULL,
  `formaJuridica_id` int(11) DEFAULT NULL,
  `claseEmpresa_id` int(11) DEFAULT NULL,
  `organizacionClasificacion_id` int(11) DEFAULT NULL,
  `provincia_id` int(11) DEFAULT NULL,
  `xmlHash` varchar(40) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKABBC16921BA1E868` (`claseEmpresa_id`),
  KEY `FKABBC16923029D62C` (`provincia_id`),
  KEY `FKABBC16927E92B8B4` (`organizacionClasificacion_id`),
  KEY `FKABBC169287D1B52C` (`formaJuridica_id`),
  KEY `FKABBC169268638F28` (`directorio_id`),
  CONSTRAINT `FKABBC169268638F28` FOREIGN KEY (`directorio_id`) REFERENCES `directorios` (`id`),
  CONSTRAINT `FKABBC16921BA1E868` FOREIGN KEY (`claseEmpresa_id`) REFERENCES `clases_empresas` (`id`),
  CONSTRAINT `FKABBC16923029D62C` FOREIGN KEY (`provincia_id`) REFERENCES `provincias` (`id`),
  CONSTRAINT `FKABBC16927E92B8B4` FOREIGN KEY (`organizacionClasificacion_id`) REFERENCES `organizaciones_clasificaciones` (`id`),
  CONSTRAINT `FKABBC169287D1B52C` FOREIGN KEY (`formaJuridica_id`) REFERENCES `formas_juridicas` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `organizaciones`
--

LOCK TABLES `organizaciones` WRITE;
/*!40000 ALTER TABLE `organizaciones` DISABLE KEYS */;
/*!40000 ALTER TABLE `organizaciones` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `groups`
--

DROP TABLE IF EXISTS `groups`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `groups` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `group_name` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `groups`
--

LOCK TABLES `groups` WRITE;
/*!40000 ALTER TABLE `groups` DISABLE KEYS */;
INSERT INTO `groups` VALUES (1,'Users'),(2,'Administrators');
/*!40000 ALTER TABLE `groups` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `authorities`
--

DROP TABLE IF EXISTS `authorities`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `authorities` (
  `username` varchar(50) NOT NULL,
  `authority` varchar(50) NOT NULL,
  PRIMARY KEY (`username`,`authority`),
  KEY `FK2B0F13217C7ED3F4` (`username`),
  CONSTRAINT `FK2B0F13217C7ED3F4` FOREIGN KEY (`username`) REFERENCES `users` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `authorities`
--

LOCK TABLES `authorities` WRITE;
/*!40000 ALTER TABLE `authorities` DISABLE KEYS */;
INSERT INTO `authorities` VALUES ('admin','ROLE_ADMIN'),('admin','ROLE_USER'),('guest','ROLE_USER');
/*!40000 ALTER TABLE `authorities` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `organizaciones_clasificaciones`
--

DROP TABLE IF EXISTS `organizaciones_clasificaciones`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `organizaciones_clasificaciones` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(255) DEFAULT NULL,
  `codigo` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `organizaciones_clasificaciones`
--

LOCK TABLES `organizaciones_clasificaciones` WRITE;
/*!40000 ALTER TABLE `organizaciones_clasificaciones` DISABLE KEYS */;
/*!40000 ALTER TABLE `organizaciones_clasificaciones` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `directorios`
--

DROP TABLE IF EXISTS `directorios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `directorios` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `token` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `directorios`
--

LOCK TABLES `directorios` WRITE;
/*!40000 ALTER TABLE `directorios` DISABLE KEYS */;
/*!40000 ALTER TABLE `directorios` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `organizaciones_eventos`
--

DROP TABLE IF EXISTS `organizaciones_eventos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `organizaciones_eventos` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `organizacion_id` int(11) DEFAULT NULL,
  `evento_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKDAD14D5168863668` (`organizacion_id`),
  KEY `FKDAD14D5137C03748` (`evento_id`),
  CONSTRAINT `FKDAD14D5137C03748` FOREIGN KEY (`evento_id`) REFERENCES `eventos` (`id`),
  CONSTRAINT `FKDAD14D5168863668` FOREIGN KEY (`organizacion_id`) REFERENCES `organizaciones` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `organizaciones_eventos`
--

LOCK TABLES `organizaciones_eventos` WRITE;
/*!40000 ALTER TABLE `organizaciones_eventos` DISABLE KEYS */;
/*!40000 ALTER TABLE `organizaciones_eventos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tipo_indicador`
--

DROP TABLE IF EXISTS `tipo_indicador`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tipo_indicador` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `creator` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tipo_indicador`
--

LOCK TABLES `tipo_indicador` WRITE;
/*!40000 ALTER TABLE `tipo_indicador` DISABLE KEYS */;
INSERT INTO `tipo_indicador` VALUES (1,'Número de Empresas',NULL),(2,'Número de Empleados',NULL),(3,'Número de Mujeres',NULL),(4,'Número de Hombres',NULL);
/*!40000 ALTER TABLE `tipo_indicador` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `organizaciones_satelites`
--

DROP TABLE IF EXISTS `organizaciones_satelites`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `organizaciones_satelites` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `organizacion_id` int(11) NOT NULL,
  `satelite_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKBA7163968863668` (`organizacion_id`),
  KEY `FKBA71639B5C93C6D` (`satelite_id`),
  CONSTRAINT `FKBA71639B5C93C6D` FOREIGN KEY (`satelite_id`) REFERENCES `satelite` (`id`),
  CONSTRAINT `FKBA7163968863668` FOREIGN KEY (`organizacion_id`) REFERENCES `organizaciones` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `organizaciones_satelites`
--

LOCK TABLES `organizaciones_satelites` WRITE;
/*!40000 ALTER TABLE `organizaciones_satelites` DISABLE KEYS */;
/*!40000 ALTER TABLE `organizaciones_satelites` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `indicator`
--

DROP TABLE IF EXISTS `indicator`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `indicator` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `width` int(11) DEFAULT NULL,
  `height` int(11) DEFAULT NULL,
  `ti_id` int(11) DEFAULT NULL,
  `tg_id` int(11) DEFAULT NULL,
  `ap_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKD58FBE0F608C77B9` (`ti_id`),
  KEY `FKD58FBE0F324F787D` (`ap_id`),
  KEY `FKD58FBE0F734ED881` (`tg_id`),
  CONSTRAINT `FKD58FBE0F734ED881` FOREIGN KEY (`tg_id`) REFERENCES `tipo_grafico` (`id`),
  CONSTRAINT `FKD58FBE0F324F787D` FOREIGN KEY (`ap_id`) REFERENCES `agrupar_por` (`id`),
  CONSTRAINT `FKD58FBE0F608C77B9` FOREIGN KEY (`ti_id`) REFERENCES `tipo_indicador` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `indicator`
--

LOCK TABLES `indicator` WRITE;
/*!40000 ALTER TABLE `indicator` DISABLE KEYS */;
INSERT INTO `indicator` VALUES (1,'Empresas por Provincias',600,480,1,1,1),(2,'Empresas por Jorma Jurídica',800,480,1,1,2),(3,'Empresas por Localidad',800,480,1,1,3),(4,'Porcentaje de Empresas por Localidad',800,480,1,2,3);
/*!40000 ALTER TABLE `indicator` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sectores`
--

DROP TABLE IF EXISTS `sectores`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sectores` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sectores`
--

LOCK TABLES `sectores` WRITE;
/*!40000 ALTER TABLE `sectores` DISABLE KEYS */;
/*!40000 ALTER TABLE `sectores` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tipo_grafico`
--

DROP TABLE IF EXISTS `tipo_grafico`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tipo_grafico` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `creator` varchar(255) DEFAULT NULL,
  `datasetCreator` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tipo_grafico`
--

LOCK TABLES `tipo_grafico` WRITE;
/*!40000 ALTER TABLE `tipo_grafico` DISABLE KEYS */;
INSERT INTO `tipo_grafico` VALUES (1,'Gráfico de Barra','net.neurowork.cenatic.centraldir.model.graphs.datasets.CategoryDatasetCreator','net.neurowork.cenatic.centraldir.model.graphs.BarchartGraphCreator'),(2,'Gráfico de Torta','net.neurowork.cenatic.centraldir.model.graphs.datasets.PieDatasetCreator','net.neurowork.cenatic.centraldir.model.graphs.PiechartGraphCreator'),(3,'Gráfico de Líneas','','net.neurowork.cenatic.centraldir.model.graphs.LinechartGraphCreator');
/*!40000 ALTER TABLE `tipo_grafico` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `organizaciones_sedes`
--

DROP TABLE IF EXISTS `organizaciones_sedes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `organizaciones_sedes` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `organizacion_id` int(11) NOT NULL,
  `latitud` float DEFAULT NULL,
  `longitud` float DEFAULT NULL,
  `direccion` varchar(255) DEFAULT NULL,
  `localidad` varchar(255) DEFAULT NULL,
  `telefonoContacto` varchar(255) DEFAULT NULL,
  `personaContacto` varchar(255) DEFAULT NULL,
  `mailContacto` varchar(255) DEFAULT NULL,
  `provincia_id` int(11) NOT NULL,
  `codigoPostal` int(11) DEFAULT NULL,
  `hombres` int(11) DEFAULT NULL,
  `mujeres` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK8BC1C09368863668` (`organizacion_id`),
  KEY `FK8BC1C0933029D62C` (`provincia_id`),
  CONSTRAINT `FK8BC1C0933029D62C` FOREIGN KEY (`provincia_id`) REFERENCES `provincias` (`id`),
  CONSTRAINT `FK8BC1C09368863668` FOREIGN KEY (`organizacion_id`) REFERENCES `organizaciones` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `organizaciones_sedes`
--

LOCK TABLES `organizaciones_sedes` WRITE;
/*!40000 ALTER TABLE `organizaciones_sedes` DISABLE KEYS */;
/*!40000 ALTER TABLE `organizaciones_sedes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `agrupar_por`
--

DROP TABLE IF EXISTS `agrupar_por`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `agrupar_por` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `columnName` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `agrupar_por`
--

LOCK TABLES `agrupar_por` WRITE;
/*!40000 ALTER TABLE `agrupar_por` DISABLE KEYS */;
INSERT INTO `agrupar_por` VALUES (1,'Provincia','provincia'),(2,'Forma Jurídica','formaJuridica'),(3,'Localidad','localidad');
/*!40000 ALTER TABLE `agrupar_por` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `formas_juridicas`
--

DROP TABLE IF EXISTS `formas_juridicas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `formas_juridicas` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `formas_juridicas`
--

LOCK TABLES `formas_juridicas` WRITE;
/*!40000 ALTER TABLE `formas_juridicas` DISABLE KEYS */;
/*!40000 ALTER TABLE `formas_juridicas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `satelite`
--

DROP TABLE IF EXISTS `satelite`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `satelite` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `hostUrl` varchar(255) DEFAULT NULL,
  `user` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `lastRetrieval` datetime DEFAULT NULL,
  `numEmpresas` int(11) DEFAULT NULL,
  `lat` float DEFAULT NULL,
  `lon` float DEFAULT NULL,
  `lastOrgId` int(11) DEFAULT NULL,
  `activado` bit(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `satelite`
--

LOCK TABLES `satelite` WRITE;
/*!40000 ALTER TABLE `satelite` DISABLE KEYS */;
INSERT INTO `satelite` VALUES (1,'Melilla','http://sld-melilla.cenatic.net','alejandrosanchezacosta@gmail.com','netfilter',NULL,0,35.3039,-2.9718,0,NULL),(2,'Murcia','http://sld-murcia.cenatic.net','alejandrosanchezacosta@gmail.com','netfilter',NULL,0,37.9859,-1.13296,0,NULL),(3,'Local','http://localhost/sldirect','alejandrosanchezacosta@gmail.com','netfilter',NULL,0,37.9859,-1.13296,0,NULL),(4,'Galicia','http://sld-galicia.cenatic.net','alejandrosanchezacosta@gmail.com','netfilter',NULL,0,37.9859,-2.13296,0,NULL);
/*!40000 ALTER TABLE `satelite` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `provincias`
--

DROP TABLE IF EXISTS `provincias`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `provincias` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=53 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `provincias`
--

LOCK TABLES `provincias` WRITE;
/*!40000 ALTER TABLE `provincias` DISABLE KEYS */;
INSERT INTO `provincias` VALUES (1,'Guipuzcoa'),(2,'A Coruña'),(3,'Araba'),(4,'Albacete'),(5,'Alicante'),(6,'Almería'),(7,'Asturias'),(8,'Ávila'),(9,'Badajoz'),(10,'Barcelona'),(11,'Bizkaia'),(12,'Burgos'),(13,'Cáceres'),(14,'Cádiz'),(15,'Cantabria'),(16,'Castellón'),(17,'Ceuta'),(18,'Ciudad Real'),(19,'Córdoba'),(20,'Cuenca'),(21,'Girona'),(22,'Granada'),(23,'Guadalajara'),(24,'Huelva'),(25,'Huesca'),(26,'Illes Balears'),(27,'Jaén'),(28,'La Rioja'),(29,'Las Palmas'),(30,'León'),(31,'Lleida'),(32,'Lugo'),(33,'Madrid'),(34,'Málaga'),(35,'Melilla'),(36,'Murcia'),(37,'Navarra'),(38,'Ourense'),(39,'Palencia'),(40,'Pontevedra'),(41,'Salamanca'),(42,'Santa Cruz de Tenerife'),(43,'Segovia'),(44,'Sevilla'),(45,'Soria'),(46,'Tarragona'),(47,'Teruel'),(48,'Toledo'),(49,'Valencia'),(50,'Valladolid'),(51,'Zamora'),(52,'Zaragoza');
/*!40000 ALTER TABLE `provincias` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `organizaciones_asociaciones`
--

DROP TABLE IF EXISTS `organizaciones_asociaciones`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `organizaciones_asociaciones` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `organizacion_id` int(11) NOT NULL,
  `asociacion_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKD12635ED227B588` (`asociacion_id`),
  KEY `FKD12635E68863668` (`organizacion_id`),
  CONSTRAINT `FKD12635E68863668` FOREIGN KEY (`organizacion_id`) REFERENCES `organizaciones` (`id`),
  CONSTRAINT `FKD12635ED227B588` FOREIGN KEY (`asociacion_id`) REFERENCES `asociaciones` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `organizaciones_asociaciones`
--

LOCK TABLES `organizaciones_asociaciones` WRITE;
/*!40000 ALTER TABLE `organizaciones_asociaciones` DISABLE KEYS */;
/*!40000 ALTER TABLE `organizaciones_asociaciones` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;