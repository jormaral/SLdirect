CREATE TABLE organizaciones (
id int unsigned not null auto_increment,
nombre text,
descripcion text,
web text,
direccion text,
localidad text,
formaJuridica_id int,
provincia_id int,
codigoPostal int,
cif text,
anoConstitucion int,
username text,
passsword text,
primary key(id));

CREATE TABLE organizaciones_sedes (
id int unsigned not null auto_increment,
latitud float,
longitud float,
direccion text,
localidad text,
provincia_id int,
codigoPostal int,	
primary key(id));

CREATE TABLE asociaciones (
id int unsigned not null auto_increment,
nombre text,
primary key(id));

CREATE TABLE organizaciones_asociaciones (
id int unsigned not null auto_increment,
organizacion_id int,
asociacion_id int,
primary key(id));

CREATE TABLE provincias (
id int unsigned not null auto_increment,
nombre text,
primary key(id));

CREATE TABLE sectores (
id int unsigned not null auto_increment,
nombre text,
primary key(id));

CREATE TABLE capacidades (
id int unsigned not null auto_increment,
nombre text,
descripcion text,
categoria_id int,
primary key(id));

CREATE TABLE capacidades_ofertas_organizaciones (
id int unsigned not null auto_increment,
capacidad_id int,
organizacion_id int,
sector_id int,
recursos int,
porcentajeFacturacion int,
puntuacion int,
primary key(id));

CREATE TABLE capacidades_demandas_organizaciones (
id int unsigned not null auto_increment,
organizacion_id int,
capacidad_id int,
sector_id int,
recursos int,
primary key(id));	

CREATE TABLE formas_juridicas (
id int unsigned not null auto_increment,
nombre text,
primary key (id));
