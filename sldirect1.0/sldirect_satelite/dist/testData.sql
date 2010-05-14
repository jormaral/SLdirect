TRUNCATE TABLE organizaciones;
TRUNCATE TABLE provincias;
TRUNCATE TABLE asociaciones;
TRUNCATE TABLE organizaciones_sedes;
TRUNCATE TABLE sectores;
TRUNCATE TABLE capacidades;
TRUNCATE TABLE organizaciones_asociaciones;

TRUNCATE TABLE capacidades_ofertas_organizaciones;
TRUNCATE TABLE capacidades_demandas_organizaciones;


INSERT INTO asociaciones (nombre,icon,url) values ("Asociaci&oacute;n de Empresas de Software Libre de Euskadi - ESLE", "media/esle.png","http://www.esle.eu");

INSERT INTO organizaciones (nombre,descripcion,cif,provincia_id,anoConstitucion,username,password,direccion,localidad,web,logo_url) values ('Webalianza T.I. S.L.','Webalianza tiene como mision proveer servicios de consultoria tecnologia orientados a mejorar los procesos de negocio de sus clientes, Webalianza tiene como mision proveer servicios de consultoria tecnologia orientados a mejorar los procesos de negocio de sus clientes.','B20660627',1,1999,"webalianza","04299e213f5391ede16784de41dd847d",'Portuetxe 23 Edificio CEMEI Piso 3 Oficina 4','San Sebastian',"http://www.webalianza.com","http://www.webalianza.com/logo_webalianza.png");
INSERT INTO organizaciones_sedes (latitud,longitud,organizacion_id,provincia_id,direccion,localidad,codigoPostal,telefonoContacto,personaContacto,mailContacto) values (43.303289, -2.009511, 1,1,'Portuetxe 23 Edificio CEMEI Piso 3 Oficina 4','San Sebastian',20018,'902364368','Cristina Reiriz','cristina@webalianza.com');
INSERT INTO organizaciones_sedes (latitud,longitud,organizacion_id,provincia_id,direccion,localidad,codigoPostal,telefonoContacto,personaContacto,mailContacto) values (43.315569, -1.98603, 1,1,'Calle Marina 9 Bajo Izq','San Sebastian',20007,'943454623','Luis Martin-Santos','luis@webalianza.com');

INSERT INTO organizaciones (nombre,descripcion,cif,provincia_id,anoConstitucion,username,password,direccion,localidad,web,logo_url) 
values ('Zylk.NET','Mision','B48434343',1,2003,"zylk","04299e213f5391ede16784de41dd847d",'Ctra. Basurto Kastrexana, 70','Bilbao',"http://www.zylk.net","http://www.zylk.net/image/layout_set_logo?img_id=10152");
INSERT INTO organizaciones_sedes (latitud,longitud,organizacion_id,provincia_id,direccion,localidad,codigoPostal,telefonoContacto,personaContacto,mailContacto) 
values (43.254377, -2.975296, 2,11,'Ctra. Basurto Kastrexana, 70','Bilbao',20007,'944272119','David Olmos','david@zylk.net');

INSERT INTO organizaciones (nombre,descripcion,cif,provincia_id,anoConstitucion,username,password,direccion,localidad,web,logo_url) 
values ('Code Syntax','Herramientas de software para la generaci&oacute;n y gesti&oacute;n de webs dinámicos, multilengua y avanzados. Sistemas de gesti&oacute;n de conocimiento para Intranet. Tramitaci&oacute;n de pedidos por Internet.','B48434343',1,1999,"codesyntax","04299e213f5391ede16784de41dd847d",'Poligono Industrial Azitain 3','Eibar',"http://www.codesyntax.com","http://www.codesyntax.com/img/logo.gif");
INSERT INTO organizaciones_sedes (latitud,longitud,organizacion_id,provincia_id,direccion,localidad,codigoPostal,telefonoContacto,personaContacto,mailContacto) 
values (43.194633, -2.453213, 3,1,'Poligono Industrial Azitain 3','Eibar',20000,'944272119','Eneko Astigarraga','info@codesyntax.es');

INSERT INTO organizaciones (nombre,descripcion,cif,provincia_id,anoConstitucion,username,password,direccion,localidad,web,logo_url) 
values ('eIngenio','','B48434343',1,1999,"eingenio","04299e213f5391ede16784de41dd847d",'Av. Pardaleras Nº2 1º','Extremadura',"http://eingenio.es/","http://eingenio.es/themes/eingenio/eingenio.gif");

INSERT INTO organizaciones_sedes (latitud,longitud,organizacion_id,provincia_id,direccion,localidad,codigoPostal,telefonoContacto,personaContacto,mailContacto) 
values (38.899851,-6.987305, 4,9,'Av. Pardaleras Nº2 1º','Badajoz',06003,'924201488','Luis Garcia','eingenio@eingenio.es');


INSERT INTO organizaciones_asociaciones (organizacion_id,asociacion_id) VALUES (1,1);
INSERT INTO organizaciones_asociaciones (organizacion_id,asociacion_id) VALUES (2,1);
INSERT INTO organizaciones_asociaciones (organizacion_id,asociacion_id) VALUES (3,1);

INSERT INTO capacidades (nombre,descripcion,categoria) VALUES ('Desarrollo Aplicaciones Web','Desarrollo de aplicaciones web','Desarrollo');
INSERT INTO capacidades (nombre,descripcion,categoria) VALUES ('Desarrollo Aplicaciones Escritorio','Desarrollo de aplicaciones escritorio','Desarrollo');
INSERT INTO capacidades (nombre,descripcion,categoria) VALUES ('Consultor&iacute;a Legal TIC','Servicios de Consultor&iacute;a Legal sobre los servicios de tecnolog&iacute;as de la informaci&oacute;n','Consulting');
INSERT INTO capacidades (nombre,descripcion,categoria) VALUES ('Consultor&iacute;a Estrategia de Sistemas','Servicios de Consultor&iacute;a sobre las estrategias a seguir en la planificaci&oacute;n de los sistemas de informaci&oacute;n de las organizaciones.','Consulting');
INSERT INTO capacidades (nombre,descripcion,categoria) VALUES ('Consultor&iacute;a Desarrollo','Servicios de Consultor&iacute;a sobre las estrategias a seguir en la planificaci&oacute;n del desarrollo de aplicaciones corporativas.','Consulting');
INSERT INTO capacidades (nombre,descripcion,categoria) VALUES ('Consultor&iacute;a de Bases de Datos','Consultoría en Diseño, Implementación, Mantenimiento y Soporte de Bases de Datos.','Consulting');
INSERT INTO capacidades (nombre,descripcion,categoria) VALUES ('Software de Gesti&oacute;n Integrado (ERP) ','Software de gestión empresarial y ERP (OpenBravo, Compiere, OpenERP, SAP,etc...) . ','Software');
INSERT INTO capacidades (nombre,descripcion,categoria) VALUES ('Software de Seguridad y Redes','Software de seguridad de red (Firewalls, Nessus, NMAP, ipTables, etc...) .','Software');
INSERT INTO capacidades (nombre,descripcion,categoria) VALUES ('Software de Monitorizaci&oacute;n de Red','Software para monitorización de redes.','Software');
INSERT INTO capacidades (nombre,descripcion,categoria) VALUES ('Software de Gesti&oacute;n comercial (CRM)','Software de gestión comercial. Capacidad para desarrollar aplicaciones de gestión a medida e instalar, configurar y mantener el software','Software');
INSERT INTO capacidades (nombre,descripcion,categoria) VALUES ('Sistemas de Gesti&oacute;n documental (ECM y DMS)','Instalar, configurar y mantener sistemas de gestión documental.','Software');
INSERT INTO capacidades (nombre,descripcion,categoria) VALUES ('Sistemas de inteligencia de negocio (BI)','Instalar, configurar y mantener este tipo de herramientas y desarrollar soluciones alrededor de estas herramientas','Software');
INSERT INTO capacidades (nombre,descripcion,categoria) VALUES ('Sistemas de Virtualizaci&oacute;n','Sistemas de virtualización por software. ','Software');

INSERT INTO sectores ( nombre ) values ("Manufacturas Industriales");
INSERT INTO sectores ( nombre ) values ("Banca");
INSERT INTO sectores ( nombre ) values ("Alimentaci&oacute;n");
INSERT INTO sectores ( nombre ) values ("Farmaceutico");
INSERT INTO sectores ( nombre ) values ("Agrario");
INSERT INTO sectores ( nombre ) values ("Educaci&oacute;n");
INSERT INTO sectores ( nombre ) values ("Comunicaciones");
INSERT INTO sectores ( nombre ) values ("Mass-Media");
INSERT INTO sectores ( nombre ) values ("Servicios de Transporte");
INSERT INTO sectores ( nombre ) values ("Aduanas, Consignatarios, Puertos");
INSERT INTO sectores ( nombre ) values ("Consultor&iacute;a");
INSERT INTO sectores ( nombre ) values ("Salud P&uacute;blica");
INSERT INTO sectores ( nombre ) values ("Salud Privada");
INSERT INTO sectores ( nombre ) values ("Automoci&oacute;n");
INSERT INTO sectores ( nombre ) values ("Turismo y Hosteler&iacute;a");
INSERT INTO sectores ( nombre ) values ("Comunicaci&oacute;n, Publicidad y Marketing");
INSERT INTO sectores ( nombre ) values ("Administraci&oacute;n P&uacute;blica");

INSERT INTO capacidades_ofertas_organizaciones (capacidad_id,organizacion_id,sector_id,recursos,porcentajeFacturacion,puntuacion) values (3,2,1,5,50,10);
INSERT INTO capacidades_ofertas_organizaciones (capacidad_id,organizacion_id,sector_id,recursos,porcentajeFacturacion,puntuacion) values (4,2,3,3,25,10);
INSERT INTO capacidades_ofertas_organizaciones (capacidad_id,organizacion_id,sector_id,recursos,porcentajeFacturacion,puntuacion) values (1,3,1,5,50,10);
INSERT INTO capacidades_ofertas_organizaciones (capacidad_id,organizacion_id,sector_id,recursos,porcentajeFacturacion,puntuacion) values (2,3,3,3,25,10);
INSERT INTO capacidades_ofertas_organizaciones (capacidad_id,organizacion_id,sector_id,recursos,porcentajeFacturacion,puntuacion) values (1,4,1,5,50,10);
INSERT INTO capacidades_ofertas_organizaciones (capacidad_id,organizacion_id,sector_id,recursos,porcentajeFacturacion,puntuacion) values (1,1,1,5,50,10);
INSERT INTO capacidades_ofertas_organizaciones (capacidad_id,organizacion_id,sector_id,recursos,porcentajeFacturacion,puntuacion) values (2,1,3,3,25,10);
INSERT INTO capacidades_ofertas_organizaciones (capacidad_id,organizacion_id,sector_id,recursos,porcentajeFacturacion,puntuacion) values (4,1,1,5,25,10);
INSERT INTO capacidades_demandas_organizaciones (capacidad_id,organizacion_id,sector_id) values (4,1,3);

INSERT INTO provincias(nombre) VALUES ('Guipuzcoa');
INSERT INTO provincias(nombre) VALUES ('A Coru&ntilde;a');
INSERT INTO provincias(nombre) VALUES ('Araba');
INSERT INTO provincias(nombre) VALUES ('Albacete');
INSERT INTO provincias(nombre) VALUES ('Alicante');
INSERT INTO provincias(nombre) VALUES ('Almer&iacute;a');
INSERT INTO provincias(nombre) VALUES ('Asturias');
INSERT INTO provincias(nombre) VALUES ('&Aacute;vila');
INSERT INTO provincias(nombre) VALUES ('Badajoz');
INSERT INTO provincias(nombre) VALUES ('Barcelona');
INSERT INTO provincias(nombre) VALUES ('Bizkaia');
INSERT INTO provincias(nombre) VALUES ('Burgos');
INSERT INTO provincias(nombre) VALUES ('C&aacute;ceres');
INSERT INTO provincias(nombre) VALUES ('C&aacute;diz');
INSERT INTO provincias(nombre) VALUES ('Cantabria');
INSERT INTO provincias(nombre) VALUES ('Castell&oacute;n');
INSERT INTO provincias(nombre) VALUES ('Ceuta');
INSERT INTO provincias(nombre) VALUES ('Ciudad Real');
INSERT INTO provincias(nombre) VALUES ('C&oacute;rdoba');
INSERT INTO provincias(nombre) VALUES ('Cuenca');
INSERT INTO provincias(nombre) VALUES ('Girona');
INSERT INTO provincias(nombre) VALUES ('Granada');
INSERT INTO provincias(nombre) VALUES ('Guadalajara');
INSERT INTO provincias(nombre) VALUES ('Huelva');
INSERT INTO provincias(nombre) VALUES ('Huesca');
INSERT INTO provincias(nombre) VALUES ('Illes Balears');
INSERT INTO provincias(nombre) VALUES ('Ja&eacute;n');
INSERT INTO provincias(nombre) VALUES ('La Rioja');
INSERT INTO provincias(nombre) VALUES ('Las Palmas');
INSERT INTO provincias(nombre) VALUES ('Le&oacute;n');
INSERT INTO provincias(nombre) VALUES ('Lleida');
INSERT INTO provincias(nombre) VALUES ('Lugo');
INSERT INTO provincias(nombre) VALUES ('Madrid');
INSERT INTO provincias(nombre) VALUES ('M&aacute;laga');
INSERT INTO provincias(nombre) VALUES ('Melilla');
INSERT INTO provincias(nombre) VALUES ('Murcia');
INSERT INTO provincias(nombre) VALUES ('Navarra');
INSERT INTO provincias(nombre) VALUES ('Ourense');
INSERT INTO provincias(nombre) VALUES ('Palencia');
INSERT INTO provincias(nombre) VALUES ('Pontevedra');
INSERT INTO provincias(nombre) VALUES ('Salamanca');
INSERT INTO provincias(nombre) VALUES ('Santa Cruz de Tenerife');
INSERT INTO provincias(nombre) VALUES ('Segovia');
INSERT INTO provincias(nombre) VALUES ('Sevilla');
INSERT INTO provincias(nombre) VALUES ('Soria');
INSERT INTO provincias(nombre) VALUES ('Tarragona');
INSERT INTO provincias(nombre) VALUES ('Teruel');
INSERT INTO provincias(nombre) VALUES ('Toledo');
INSERT INTO provincias(nombre) VALUES ('Valencia');
INSERT INTO provincias(nombre) VALUES ('Valladolid');
INSERT INTO provincias(nombre) VALUES ('Zamora');
INSERT INTO provincias(nombre) VALUES ('Zaragoza');

INSERT INTO organizaciones_clasificaciones (nombre,codigo) VALUES ('Fabricaci&oacute;n de componentes electr&oacute;nicos',2611);
INSERT INTO organizaciones_clasificaciones (nombre,codigo) VALUES ('Fabricaci&oacute;n de circuitos impresos ensamblados',2612);
INSERT INTO organizaciones_clasificaciones (nombre,codigo) VALUES ('Fabricaci&oacute;n de ordenadores y equipos perif&eacute;ricos',2620);
INSERT INTO organizaciones_clasificaciones (nombre,codigo) VALUES ('Fabricaci&oacute;n de equipos de telecomunicaciones',2630);
INSERT INTO organizaciones_clasificaciones (nombre,codigo) VALUES ('Fabricaci&oacute;n de productos electr&oacute;nicos de consumo',2640);
INSERT INTO organizaciones_clasificaciones (nombre,codigo) VALUES ('Fabricaci&oacute;n de soportes magn&eacute;ticos y &oacute;pticos',2680);
INSERT INTO organizaciones_clasificaciones (nombre,codigo) VALUES ('Comercio al por mayor de ordenadores, equipos perif&eacute;ricos y programas inform&aacute;ticos',4651);
INSERT INTO organizaciones_clasificaciones (nombre,codigo) VALUES ('Comercio al por mayor de equipos electr&oacute;nicos y de telecomunicaciones y sus componentes',4652);
INSERT INTO organizaciones_clasificaciones (nombre,codigo) VALUES ('Edici&oacute;n de videojuegos',5821);
INSERT INTO organizaciones_clasificaciones (nombre,codigo) VALUES ('Edici&oacute;n de otros programas inform&aacute;ticos',5829);
INSERT INTO organizaciones_clasificaciones (nombre,codigo) VALUES ('Telecomunicaciones por cable',6110);
INSERT INTO organizaciones_clasificaciones (nombre,codigo) VALUES ('Telecomunicaciones inal&aacute;mbricas',6120);
INSERT INTO organizaciones_clasificaciones (nombre,codigo) VALUES ('Telecomunicaciones por sat&eacute;lite',6130);
INSERT INTO organizaciones_clasificaciones (nombre,codigo) VALUES ('Otras actividades de telecomunicaciones',6190);
INSERT INTO organizaciones_clasificaciones (nombre,codigo) VALUES ('Actividades de programaci&oacute;n inform&aacute;tica',6201);
INSERT INTO organizaciones_clasificaciones (nombre,codigo) VALUES ('Actividades de consultor&iacute;a inform&aacute;tica',6202);
INSERT INTO organizaciones_clasificaciones (nombre,codigo) VALUES ('Gesti&oacute;n de recursos inform&aacute;ticos',6203);
INSERT INTO organizaciones_clasificaciones (nombre,codigo) VALUES ('Otros servicios relacionados con las tecnolog&iacute;as de la informaci&oacute;n y la inform&aacute;tica',6209);
INSERT INTO organizaciones_clasificaciones (nombre,codigo) VALUES ('Proceso de datos, hosting y actividades relacionadas',6311);
INSERT INTO organizaciones_clasificaciones (nombre,codigo) VALUES ('Portales web',6312);
INSERT INTO organizaciones_clasificaciones (nombre,codigo) VALUES ('Reparaci&oacute;n de ordenadores y equipos perif&eacute;ricos',9511);
INSERT INTO organizaciones_clasificaciones (nombre,codigo) VALUES ('Reparaci&oacute;n de equipos de comunicaci&oacute;n',9512);


INSERT INTO formas_juridicas (nombre) VALUES ('Sociedad An&oacute;nima');
INSERT INTO formas_juridicas (nombre) VALUES ('Sociedad de Responsabilidad Limitada');
INSERT INTO formas_juridicas (nombre) VALUES ('Sociedad Colectiva');
INSERT INTO formas_juridicas (nombre) VALUES ('Sociedad Comanditaria');
INSERT INTO formas_juridicas (nombre) VALUES ('Comunidad de Bienes');
INSERT INTO formas_juridicas (nombre) VALUES ('Sociedad Cooperativa');
INSERT INTO formas_juridicas (nombre) VALUES ('Asociaciones y otros tipos');
INSERT INTO formas_juridicas (nombre) VALUES ('Organismos aut&oacute;nomos');
INSERT INTO formas_juridicas (nombre) VALUES ('Personas F&iacute;sicas');

