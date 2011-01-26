USE `centraldir`;

INSERT INTO `users`(username,password,enabled) VALUES ('admin','admin',1),('guest','guest',1);
INSERT INTO `groups` VALUES (1,'Users'),(2,'Administrators');
INSERT INTO `authorities` VALUES ('admin','ROLE_ADMIN'),('admin','ROLE_USER'),('guest','ROLE_USER');

INSERT INTO `provincias` VALUES (1,'Guipuzcoa'),(2,'A Coruña'),(3,'Araba'),(4,'Albacete'),(5,'Alicante'),(6,'Almería'),(7,'Asturias'),(8,'Ávila'),(9,'Badajoz'),(10,'Barcelona'),(11,'Bizkaia'),(12,'Burgos'),(13,'Cáceres'),(14,'Cádiz'),(15,'Cantabria'),(16,'Castellón'),(17,'Ceuta'),(18,'Ciudad Real'),(19,'Córdoba'),(20,'Cuenca'),(21,'Girona'),(22,'Granada'),(23,'Guadalajara'),(24,'Huelva'),(25,'Huesca'),(26,'Illes Balears'),(27,'Jaén'),(28,'La Rioja'),(29,'Las Palmas'),(30,'León'),(31,'Lleida'),(32,'Lugo'),(33,'Madrid'),(34,'Málaga'),(35,'Melilla'),(36,'Murcia'),(37,'Navarra'),(38,'Ourense'),(39,'Palencia'),(40,'Pontevedra'),(41,'Salamanca'),(42,'Santa Cruz de Tenerife'),(43,'Segovia'),(44,'Sevilla'),(45,'Soria'),(46,'Tarragona'),(47,'Teruel'),(48,'Toledo'),(49,'Valencia'),(50,'Valladolid'),(51,'Zamora'),(52,'Zaragoza');

INSERT INTO `capacidades` VALUES 
	(1,'Edición de videojuegos ',NULL,NULL,NULL,'Servicios de edición de programas informáticos'),
	(2,'Programas del sistema y de aplicación en  soporte físico',NULL,NULL,NULL,'Servicios de edición de programas informáticos'),
	(3,'Programas informáticos descargables y en línea',NULL,NULL,NULL,'Servicios de edición de programas informáticos'),
	(4,'Servicios de Liberación de código',NULL,NULL,NULL,'Servicios de consultoría informática'),
	(5,'Servicios de programación informática',NULL,NULL,NULL,'Servicios informáticos generales'),
	(6,'Consultoría sobre equipos informáticos',NULL,NULL,NULL,'Servicios de consultoría informática'),
	(7,'Consultoría sobre sistemas y programas',NULL,NULL,NULL,'Servicios de consultoría informática'),
	(8,'Servicios de apoyo técnico a las tecnologías de la información',NULL,NULL,NULL,'Servicios de consultoría informática'),
	(9,'Servicios de gestión de recursos informáticos',NULL,NULL,NULL,'Servicios informáticos generales'),
	(10,'Otros servicios informáticos y de tecnologías  de la información. Servicios de instalación e  implantación de equipos y programas',NULL,NULL,NULL,'Servicios informáticos generales'),
	(11,'Servicios de proceso de datos, hosting y  servicios similares',NULL,NULL,NULL,'Servicios informáticos generales'),
	(12,'Servicios de portales web',NULL,NULL,NULL,'Servicios informáticos generales'),
	(13,'Servicios de reparación de ordenadores y equipos periféricos',NULL,NULL,NULL,'Servicios informáticos generales'),
	(14,'Telecomunicaciones',NULL,NULL,NULL,'Servicios informáticos generales'),
	(15,'Fabricación de equipos informáticos',NULL,NULL,NULL,'Servicios informáticos generales'),
	(16,'Venta de equipos informáticos',NULL,NULL,NULL,'Servicios informáticos generales'),
	(17,'Venta de programas y aplicaciones no desarrollados por la empresa',NULL,NULL,NULL,'Servicios informáticos generales'),
	(18,'Venta de material auxiliar',NULL,NULL,NULL,'Servicios informáticos generales'),
	(19,'Alquiler de equipos informáticos sin operario',NULL,NULL,NULL,'Servicios informáticos generales'),
	(20,'Servicios de consultoría de gestión empresarial',NULL,NULL,NULL,'Servicios informáticos generales'),
	(21,'Otras actividades y servicios',NULL,NULL,NULL,'Servicios informáticos generales');


INSERT INTO `tipo_indicador`(id, name, creator)
VALUES (1,'Número de Empresas',NULL),
       (2,'Número de Empleados',NULL),
       (3,'Número de Mujeres',NULL),
       (4,'Número de Hombres',NULL);
INSERT INTO `tipo_grafico` (id, name, creator, datasetCreator) 
VALUES (1,'Gráfico de Barra','net.neurowork.cenatic.centraldir.model.graphs.BarchartGraphCreator', 'net.neurowork.cenatic.centraldir.model.graphs.datasets.CategoryDatasetCreator'),
       (2,'Gráfico de Torta','net.neurowork.cenatic.centraldir.model.graphs.PiechartGraphCreator','net.neurowork.cenatic.centraldir.model.graphs.datasets.PieDatasetCreator'),
       (3,'Gráfico de Líneas','net.neurowork.cenatic.centraldir.model.graphs.LinechartGraphCreator','');
INSERT INTO `agrupar_por` (id, name, columnName) 
VALUES (1,'Provincia','provincia'),
       (2,'Forma Jurídica','formaJuridica'),
       (3,'Localidad','localidad'),
       (4, 'Año de Registro','anoConstitucion');
       
INSERT INTO `indicator` (id, name, descripcion, width, height, ti_id, tg_id, ap_id, datasetGenerator) 
VALUES (1,'Empresas por provincia','El sumatorio de empresas por provincia.',800,480,1,1,1,'net.neurowork.cenatic.centraldir.model.indicators.impl.EmpresasPorProvincia'),
       (2,'Porcentaje de Empresas por Forma Juridica','',800,480,1,1,2,'net.neurowork.cenatic.centraldir.model.indicators.impl.EmpresasPorFormaJuridica'),
       (3,'Empresas por Año de Constitución','Sera el sumatorio de empresas clasificado por  año de constitución',800,480,1,1,4,'net.neurowork.cenatic.centraldir.model.indicators.impl.EmpresasPorAnoConstitucion'),
       (4,'Empresas por Nro de empleados','será el sumatorio del número de empleados en el central',800,480,1,1,1,'net.neurowork.cenatic.centraldir.model.indicators.impl.EmpresasPorFormaJuridica'),
       (5,'Porcentaje de Empresas con Certificaciones Cenatic','Será el sumatorio de certificación de Cenatic por año',800,480,1,2,1,'net.neurowork.cenatic.centraldir.model.indicators.impl.EmpresasPorCertCenatic'),
       (6,'Porcentaje de Emp. con Certificaciones de Calidad','será el sumatorio de certificaciones de calidad por año.',800,480,1,2,1,'net.neurowork.cenatic.centraldir.model.indicators.impl.EmpresasPorCertCalidad'),
       (7,'Empresas por Nro de Partners','será el sumatorio de partner por año.',800,480,1,1,1,'net.neurowork.cenatic.centraldir.model.indicators.impl.EmpresasPorNroPartners'),
       (8,'Porcentaje de Emp. con Actividades de I+D','será el sumatorio de actividades de I+D por año.',800,480,1,2,1,'net.neurowork.cenatic.centraldir.model.indicators.impl.EmpresasConActividadIMasD'),
       (9,'Porcentaje de Emp. con Participación de I+D','será el sumatorio del número de participación en programas de I+D por año',800,480,1,2,1,'net.neurowork.cenatic.centraldir.model.indicators.impl.EmpresasConParticipacionIMasD'),
       (10,'Porcentaje de Emp. con Relaciones con la Comunidad','será el sumatorio de las Relaciones con la Comunidad por año',800,480,1,2,1,'net.neurowork.cenatic.centraldir.model.indicators.impl.EmpresasConRelacionesComunidad'),
       (11,'Porcentaje de Emp. Pertenecen a Grupo Empresarial','será el sumatorio de Pertenencia a Grupo Empresarial por año',800,480,1,2,1,'net.neurowork.cenatic.centraldir.model.indicators.impl.EmpresasConGrupoEmpresarial'),
       (12,'Empresas por Grupo Empresarial','Número de Emresas que pertencen a un Grupo Empresarial',800,480,1,1,1,'net.neurowork.cenatic.centraldir.model.indicators.impl.EmpresasPorGrupoEmpresarial'),
       (13,'Porcentaje de Emp. con Asociaciones','',800,480,1,2,1,'net.neurowork.cenatic.centraldir.model.indicators.impl.EmpresasConAsociaciones'),
       (14,'Empresas por Asociaciones','Número de Empresas por Asociaciones',800,480,1,1,1,'net.neurowork.cenatic.centraldir.model.indicators.impl.EmpresasPorAsociaciones'),
       (15,'Empresas por Número de Eventos','Numero de Eventos que publica cada empresa',800,480,1,1,1,'net.neurowork.cenatic.centraldir.model.indicators.impl.EmpresasPorEventos'),
	   (16,'Porcentaje de Ofertas por Servicios','Porcentaje de Ofertas por Servicios',800,480,1,2,1,'net.neurowork.cenatic.centraldir.model.indicators.impl.OfertasPorServicios'),
	   (17,'Porcentaje de Demandas por Servicios','Porcentaje de Demandas por Servicios',800,480,1,2,1,'net.neurowork.cenatic.centraldir.model.indicators.impl.DemandasPorServicios');
       
INSERT INTO `satelite` (`id`, `name`, `numEmpresas`, `lat`, `lon`, `hostUrl`, `user`, `password`, `lastOrgId`)
VALUES ('1', 'Melilla', 0, '35.3039', '-2.9718', 'http://sld-melilla.cenatic.net', 'alejandrosanchezacosta@gmail.com', 'netfilter', 0),
       ('2', 'Murcia',  0, '37.9859', '-1.13296', 'http://sld-murcia.cenatic.net', 'alejandrosanchezacosta@gmail.com', 'netfilter', 0),
       ('3', 'Local', 0, '37.9859', '-1.13296', 'http://localhost/sldirect', 'alejandrosanchezacosta@gmail.com', 'netfilter', 0),
       ('4', 'Galicia', 0, '37.9859', '-2.13296', 'http://sld-galicia.cenatic.net', 'alejandrosanchezacosta@gmail.com', 'netfilter', 0);
       
       
       