create Database AirSafety
GO
USE AirSafety
GO


CREATE TABLE AS_Aeropuertos(
	Codigo char(3) NOT NULL,
	Nombre varchar(30) NOT NULL,
	Ciudad varchar(30) NOT NULL,
	Pais varchar(30) NOT NULL,
 CONSTRAINT PK_Aeropuerto PRIMARY KEY (Codigo)
)


CREATE TABLE AS_Aviones(
	Matricula char(10) NOT NULL,
	Nombre varchar(30) NOT NULL,
	ID_Fabricante tinyint NOT NULL,
	Modelo varchar(25) NOT NULL,
	Fecha_Fabricacion date NULL,
	Fecha_Entrada date NULL,
	Filas smallint NOT NULL,
	Asientos_x_Fila tinyint NOT NULL,
	Autonomia int NULL,
	Activo Char(1) Not Null Default 'S',
 CONSTRAINT PK_Aviones PRIMARY KEY (Matricula)
)


CREATE TABLE AS_Fabricantes(
	ID_Fabricante tinyint NOT NULL,
	Nombre varchar(25) NOT NULL,
 CONSTRAINT PK_Fabricantes PRIMARY KEY (ID_Fabricante)
)


CREATE TABLE AS_Vuelos(
	Codigo int IDENTITY(1,1) NOT NULL,
	Aeropuerto_Salida char(3) NOT NULL,
	Aeropuerto_Llegada [char](3) NOT NULL,
	Salida smalldatetime NOT NULL,
	Llegada smalldatetime NOT NULL,
	Matricula_Avion char(10) NOT NULL,
	Precio_Pasaje smallmoney NULL,
 CONSTRAINT PK_Vuelos PRIMARY KEY (Codigo)
)

GO
CREATE Table AS_Incidencias (
	ID Int Identity Not NULL Constraint PK_Incidencia Primary Key
	,Momento SmallDateTime Not NULL Default Current_Timestamp
	,Avion char(10) NOT NULL
	,Latitud Decimal (7,4) Null
	,Longitud Decimal (7,4) Null
	,Descripcion NVarChar(300) Not NULL
	,Tipo Char(1) Not NULL Constraint CKTipo Check (Tipo IN ('A','P','O'))
	,Constraint FKIncidenciasAviones Foreign Key (Avion) References AS_Aviones (Matricula)
)
CREATE Table EX_Actualizaciones(
	 MatriculaAvion char(10) NOT NULL
	,Latitud Decimal (7,4) Null
	,Longitud Decimal (7,4) Null
	,Descripcion NVarChar(300) Not NULL
	,Tipo Char(1) Not NULL Constraint CKTipoEX Check (Tipo IN ('A','P','O'))
	,AccidenteDefinitivo Bit -- Si tiene el valor 1 significa que el avión ha quedado en un estado irreparable
	,NombreAvion varchar(30) NULL,
	Fabricante varchar(25) NULL,
	Modelo varchar(25) NULL,
	Fecha_Fabricacion date NULL,
	Fecha_Entrada date NULL,
	Filas smallint NULL,
	Asientos_x_Fila tinyint NULL,
	Autonomia int NULL
)

SET ANSI_PADDING OFF
Set dateformat 'ymd'
GO
INSERT AS_Aeropuertos (Codigo, Nombre, Ciudad, Pais) VALUES (N'AGP', N'Pablo Ruiz Picasso', N'Málaga', N'España'), (N'APA', N'Centennial', N'Denver', N'Estados Unidos de América')
, (N'BCN', N'El Prat', N'Barcelona', N'España'), (N'CAI', N'El Cairo', N'El Cairo', N'Egipto'), (N'CDG', N'Charles de Gaulle', N'Paris', N'Francia')
, (N'CVG', N'Cincinnati/Kentucky Norte', N'Cincinnati', N'Estados Unidos de América'), (N'FCO', N'Leonardo da Vinci', N'Roma', N'Italia')
, (N'JFK', N'John F. Kennedy', N'New York', N'Estados Unidos de América'), (N'LGA', N'La Guardia', N'New York', N'Estados Unidos de América')
INSERT AS_Aeropuertos (Codigo, Nombre, Ciudad, Pais) VALUES (N'LHR', N'Heathrow', N'Londres', N'Reino Unido'), (N'MAD', N'Barajas', N'Madrid', N'España')
, (N'MIA', N'Miami', N'Miami', N'Estados Unidos de América'), (N'NRT', N'Narita', N'Tokio', N'Japón'), (N'PMI', N'Son San Joan', N'Palma de Mallorca', N'España')
, (N'SDF', N'Louisville', N'Louisville', N'Estados Unidos de América'), (N'SVQ', N'San Pablo', N'Sevilla', N'España'), (N'TXL', N'Otto Lilienthal', N'Berlin', N'Alemania')

INSERT AS_Aviones (Matricula, Nombre, ID_Fabricante, Modelo, Fecha_Fabricacion, Fecha_Entrada, Filas, Asientos_x_Fila, Autonomia) VALUES (N'ESP2345   ', N'Rayo', 2, N'A320', CAST(N'2006-03-20' AS Date), CAST(N'2006-04-15' AS Date), 25, 6, 3500)
, ('ESP4502   ', 'Argonauta', 2, 'A320', CAST('2007-05-20' AS Date), CAST('2007-08-15' AS Date), 25, 6, 2500), ('ESP5077   ', 'Plus Ultra', 3, 'B747', CAST('2006-09-27' AS Date), CAST('2007-03-25' AS Date), 42, 10, 6500), 
('ESP8067   ', 'Santísima Trinidad', 3, 'B737', CAST('2006-01-24' AS Date), CAST('2006-02-18' AS Date), 22, 6, 3200),
('FRA0955   ', 'Bucentaure', 3, 'B747', CAST('2010-09-27' AS Date), CAST('2010-11-25' AS Date), 42, 10, 6500),
('FRA4502   ', 'Acheron', 2, 'A320', CAST('2007-05-24' AS Date), CAST('2007-07-11' AS Date), 25, 6, 2500),
('FRA5021   ', 'Redoutable', 2, 'A320', CAST('2007-05-20' AS Date), CAST('2007-08-15' AS Date), 25, 6, 2500),
('GBR2574   ', 'God save the Queen', 13, 'YAK-42', NULL, NULL, 25, 4, NULL)
INSERT AS_Aviones (Matricula, Nombre, ID_Fabricante, Modelo, Fecha_Fabricacion, Fecha_Entrada, Filas, Asientos_x_Fila, Autonomia) VALUES ('USA174136 ', 'Montana', 3, 'B737', CAST('2015-05-02' AS Date), CAST('2015-06-20' AS Date), 24, 6, 3300),
('USA174137 ', 'Idaho', 3, 'B737', CAST('2015-05-03' AS Date), CAST('2015-06-20' AS Date), 24, 6, 3300),
('USA174138 ', 'Arkansas', 3, 'B737', CAST('2015-05-04' AS Date), CAST('2015-06-20' AS Date), 24, 6, 3300)
,('USA174139 ', 'Nebraska', 3, 'B737', CAST('2015-05-05' AS Date), CAST('2015-06-20' AS Date), 24, 6, 3300)
,('USA174140 ', 'North Carolina', 3, 'B737', CAST('2015-05-03' AS Date), CAST('2015-06-20' AS Date), 24, 6, 3300)
,('USA5068   ', 'Enola Gay', 3, 'B737', CAST('2009-03-20' AS Date), CAST('2009-04-15' AS Date), 24, 6, 2700)
,('USA5077   ', 'Spirit of St Louis', 3, 'B747', CAST('2007-01-27' AS Date), CAST('2007-03-15' AS Date), 42, 10, 6500)
INSERT AS_Fabricantes (ID_Fabricante, Nombre) VALUES (2, 'Airbus'), (3, 'Boeing'), (13, 'Yakolev'), (16, 'Flying bricks')
SET IDENTITY_INSERT [dbo].[AS_Vuelos] ON 

INSERT AS_Vuelos (Codigo, Aeropuerto_Salida, Aeropuerto_Llegada, Salida, Llegada, Matricula_Avion, Precio_Pasaje) VALUES (1, 'APA', 'JFK', CAST('2014-06-14 14:05:00' AS SmallDateTime), CAST('2014-06-14 17:30:00' AS SmallDateTime), 'USA5068   ', 150.9500)
,(2, 'MIA', 'JFK', CAST('2017-08-16 14:45:00' AS SmallDateTime), CAST('2017-08-16 18:30:00' AS SmallDateTime), 'USA5068   ', 290.9500)
,(3, 'APA', 'MIA', CAST('2017-10-14 07:05:00' AS SmallDateTime), CAST('2017-10-14 14:30:00' AS SmallDateTime), 'USA5077   ', 150.9500)
,(4, 'APA', 'JFK', CAST('2017-07-14 14:05:00' AS SmallDateTime), CAST('2017-07-14 17:30:00' AS SmallDateTime), 'USA5068   ', 150.9500)
,(5, 'APA', 'JFK', CAST('2017-08-14 14:05:00' AS SmallDateTime), CAST('2017-08-14 17:30:00' AS SmallDateTime), 'USA5068   ', 150.9500)
,(6, 'CVG', 'JFK', CAST('2017-06-18 14:05:00' AS SmallDateTime), CAST('2017-06-18 17:30:00' AS SmallDateTime), 'USA5068   ', 155.9500)
,(7, 'APA', 'AGP', CAST('2016-11-14 14:05:00' AS SmallDateTime), CAST('2016-11-14 17:30:00' AS SmallDateTime), 'ESP4502   ', 850.9500)

INSERT AS_Vuelos (Codigo, Aeropuerto_Salida, Aeropuerto_Llegada, Salida, Llegada, Matricula_Avion, Precio_Pasaje) VALUES (8, 'MAD', 'SVQ', CAST('2010-09-14 14:45:00' AS SmallDateTime), CAST('2010-09-14 18:30:00' AS SmallDateTime), 'ESP4502   ', 90.8500)
,(9, 'APA', 'MAD', CAST('2010-05-14 07:05:00' AS SmallDateTime), CAST('2010-05-14 14:30:00' AS SmallDateTime), 'ESP8067   ', 450.9500)
,(10, 'AGP', 'SVQ', CAST('2010-02-14 14:05:00' AS SmallDateTime), CAST('2010-02-14 17:30:00' AS SmallDateTime), 'ESP8067   ', 50.5500)
,(11, 'SVQ', 'JFK', CAST('2010-03-14 14:05:00' AS SmallDateTime), CAST('2010-03-14 17:30:00' AS SmallDateTime), 'ESP5077   ', 450.7500)
,(12, 'PMI', 'NRT', CAST('2010-01-18 14:05:00' AS SmallDateTime), CAST('2010-01-18 17:30:00' AS SmallDateTime), 'ESP5077   ', 1255.9500)
,(13, 'APA', 'AGP', CAST('2010-11-14 14:05:00' AS SmallDateTime), CAST('2010-11-14 17:30:00' AS SmallDateTime), 'ESP4502   ', 850.9500)
,(14, 'MAD', 'SVQ', CAST('2010-01-16 14:45:00' AS SmallDateTime), CAST('2010-01-16 18:30:00' AS SmallDateTime), 'ESP4502   ', 90.8500)

INSERT AS_Vuelos (Codigo, Aeropuerto_Salida, Aeropuerto_Llegada, Salida, Llegada, Matricula_Avion, Precio_Pasaje) VALUES (15, 'APA', 'MAD', CAST('2010-05-10 07:05:00' AS SmallDateTime), CAST('2010-05-10 14:30:00' AS SmallDateTime), 'ESP8067   ', 450.9500)
,(16, 'AGP', 'FCO', CAST('2010-02-14 14:05:00' AS SmallDateTime), CAST('2010-02-14 17:30:00' AS SmallDateTime), 'ESP8067   ', 50.5500)
,(17, 'SVQ', 'JFK', CAST('2010-03-14 14:05:00' AS SmallDateTime), CAST('2010-03-14 17:30:00' AS SmallDateTime), 'ESP5077   ', 450.7500)
,(18, 'PMI', 'NRT', CAST('2010-01-18 14:05:00' AS SmallDateTime), CAST('2010-01-18 17:30:00' AS SmallDateTime), 'ESP5077   ', 1255.9500)
,(19, 'LGA', 'AGP', CAST('2010-11-16 14:05:00' AS SmallDateTime), CAST('2010-11-17 07:30:00' AS SmallDateTime), 'ESP4502   ', 650.9500)
,(20, 'MAD', 'LGA', CAST('2010-01-16 14:45:00' AS SmallDateTime), CAST('2010-01-16 22:30:00' AS SmallDateTime), 'FRA5021   ', 474.8500)
,(21, 'CAI', 'MAD', CAST('2010-05-17 07:05:00' AS SmallDateTime), CAST('2010-05-17 14:30:00' AS SmallDateTime), 'FRA4502   ', 550.9500)

INSERT AS_Vuelos (Codigo, Aeropuerto_Salida, Aeropuerto_Llegada, Salida, Llegada, Matricula_Avion, Precio_Pasaje) VALUES (22, 'PMI', 'SVQ', CAST('2010-02-14 14:05:00' AS SmallDateTime), CAST('2010-02-14 17:30:00' AS SmallDateTime), 'ESP8067   ', 80.5500)
,(23, 'SVQ', 'CAI', CAST('2010-06-14 14:05:00' AS SmallDateTime), CAST('2010-06-14 17:30:00' AS SmallDateTime), 'ESP5077   ', 450.7500)
,(24, 'LGA', 'NRT', CAST('2010-01-12 14:05:00' AS SmallDateTime), CAST('2010-01-12 17:30:00' AS SmallDateTime), 'FRA0955   ', 1255.9500)
,(25, 'APA', 'JFK', CAST('2014-06-21 14:05:00' AS SmallDateTime), CAST('2014-06-21 17:30:00' AS SmallDateTime), 'USA5068   ', 150.9500)
,(26, 'MIA', 'JFK', CAST('2014-08-23 14:45:00' AS SmallDateTime), CAST('2014-08-23 18:30:00' AS SmallDateTime), 'USA5068   ', 290.9500)
,(27, 'APA', 'MIA', CAST('2014-10-21 07:05:00' AS SmallDateTime), CAST('2014-10-21 14:30:00' AS SmallDateTime), 'USA5077   ', 150.9500)
,(28, 'APA', 'JFK', CAST('2014-07-21 14:05:00' AS SmallDateTime), CAST('2014-07-21 17:30:00' AS SmallDateTime), 'USA5068   ', 150.9500)

INSERT AS_Vuelos (Codigo, Aeropuerto_Salida, Aeropuerto_Llegada, Salida, Llegada, Matricula_Avion, Precio_Pasaje) VALUES (29, 'APA', 'JFK', CAST('2014-08-21 14:05:00' AS SmallDateTime), CAST('2014-08-21 17:30:00' AS SmallDateTime), 'USA5068   ', 150.9500)
,(30, 'CVG', 'JFK', CAST('2014-06-25 14:05:00' AS SmallDateTime), CAST('2014-06-25 17:30:00' AS SmallDateTime), 'USA5068   ', 155.9500)
,(31, 'APA', 'AGP', CAST('2010-11-21 14:05:00' AS SmallDateTime), CAST('2010-11-21 17:30:00' AS SmallDateTime), 'ESP4502   ', 850.9500)
,(32, 'MAD', 'SVQ', CAST('2010-09-21 14:45:00' AS SmallDateTime), CAST('2010-09-21 18:30:00' AS SmallDateTime), 'ESP4502   ', 90.8500)
,(33, 'APA', 'MAD', CAST('2010-05-21 07:05:00' AS SmallDateTime), CAST('2010-05-21 14:30:00' AS SmallDateTime), 'ESP8067   ', 450.9500)
,(34, 'AGP', 'SVQ', CAST('2010-02-21 14:05:00' AS SmallDateTime), CAST('2010-02-21 17:30:00' AS SmallDateTime), 'ESP8067   ', 50.5500)
,(35, 'SVQ', 'JFK', CAST('2010-03-21 14:05:00' AS SmallDateTime), CAST('2010-03-21 17:30:00' AS SmallDateTime), 'ESP5077   ', 450.7500)

INSERT AS_Vuelos (Codigo, Aeropuerto_Salida, Aeropuerto_Llegada, Salida, Llegada, Matricula_Avion, Precio_Pasaje) VALUES (36, 'PMI', 'NRT', CAST('2010-01-25 14:05:00' AS SmallDateTime), CAST('2010-01-25 17:30:00' AS SmallDateTime), 'ESP5077   ', 1255.9500)
,(37, 'APA', 'AGP', CAST('2010-11-21 14:05:00' AS SmallDateTime), CAST('2010-11-21 17:30:00' AS SmallDateTime), 'ESP4502   ', 850.9500)
,(38, 'MAD', 'SVQ', CAST('2010-01-23 14:45:00' AS SmallDateTime), CAST('2010-01-23 18:30:00' AS SmallDateTime), 'ESP4502   ', 90.8500)
,(39, 'APA', 'MAD', CAST('2010-05-17 07:05:00' AS SmallDateTime), CAST('2010-05-17 14:30:00' AS SmallDateTime), 'ESP8067   ', 450.9500)
,(40, 'AGP', 'FCO', CAST('2010-02-21 14:05:00' AS SmallDateTime), CAST('2010-02-21 17:30:00' AS SmallDateTime), 'ESP8067   ', 50.5500)
,(41, 'SVQ', 'JFK', CAST('2010-03-21 14:05:00' AS SmallDateTime), CAST('2010-03-21 17:30:00' AS SmallDateTime), 'ESP5077   ', 450.7500)
,(42, 'PMI', 'NRT', CAST('2010-01-25 14:05:00' AS SmallDateTime), CAST('2010-01-25 17:30:00' AS SmallDateTime), 'ESP5077   ', 1255.9500)

INSERT AS_Vuelos (Codigo, Aeropuerto_Salida, Aeropuerto_Llegada, Salida, Llegada, Matricula_Avion, Precio_Pasaje) VALUES (43, 'LGA', 'AGP', CAST('2010-11-23 14:05:00' AS SmallDateTime), CAST('2010-11-24 07:30:00' AS SmallDateTime), 'ESP4502   ', 650.9500)
,(44, 'MAD', 'LGA', CAST('2010-01-23 14:45:00' AS SmallDateTime), CAST('2010-01-23 22:30:00' AS SmallDateTime), 'FRA5021   ', 474.8500)
,(45, 'CAI', 'MAD', CAST('2010-05-24 07:05:00' AS SmallDateTime), CAST('2010-05-24 14:30:00' AS SmallDateTime), 'FRA4502   ', 550.9500)
,(46, 'PMI', 'SVQ', CAST('2010-02-21 14:05:00' AS SmallDateTime), CAST('2010-02-21 17:30:00' AS SmallDateTime), 'ESP8067   ', 80.5500)
,(47, 'SVQ', 'CAI', CAST('2010-06-21 14:05:00' AS SmallDateTime), CAST('2010-06-21 17:30:00' AS SmallDateTime), 'ESP5077   ', 450.7500)
,(48, 'LGA', 'NRT', CAST('2010-01-19 14:05:00' AS SmallDateTime), CAST('2010-01-19 17:30:00' AS SmallDateTime), 'FRA0955   ', 1255.9500)
,(49, 'AGP', 'SVQ', CAST('2010-04-14 14:05:00' AS SmallDateTime), CAST('2010-04-14 17:30:00' AS SmallDateTime), 'ESP8067   ', 50.5500)
 
INSERT AS_Vuelos (Codigo, Aeropuerto_Salida, Aeropuerto_Llegada, Salida, Llegada, Matricula_Avion, Precio_Pasaje) VALUES (50, 'AGP', 'FCO', CAST('2010-04-14 14:05:00' AS SmallDateTime), CAST('2010-04-14 17:30:00' AS SmallDateTime), 'ESP8067   ', 50.5500)
,(51, 'AGP', 'SVQ', CAST('2010-04-21 14:05:00' AS SmallDateTime), CAST('2010-04-21 17:30:00' AS SmallDateTime), 'ESP8067   ', 50.5500)
,(52, 'AGP', 'FCO', CAST('2010-04-21 14:05:00' AS SmallDateTime), CAST('2010-04-21 17:30:00' AS SmallDateTime), 'ESP8067   ', 50.5500)
,(53, 'APA', 'MAD', CAST('2010-07-17 07:05:00' AS SmallDateTime), CAST('2010-07-17 14:30:00' AS SmallDateTime), 'ESP8067   ', 450.9500)
,(54, 'APA', 'AGP', CAST('2011-01-21 14:05:00' AS SmallDateTime), CAST('2011-01-21 17:30:00' AS SmallDateTime), 'ESP4502   ', 850.9500)
,(55, 'APA', 'AGP', CAST('2011-01-14 14:05:00' AS SmallDateTime), CAST('2011-01-14 17:30:00' AS SmallDateTime), 'ESP4502   ', 850.9500)
,(56, 'APA', 'MAD', CAST('2010-07-14 07:05:00' AS SmallDateTime), CAST('2010-07-14 14:30:00' AS SmallDateTime), 'ESP8067   ', 450.9500)

INSERT AS_Vuelos (Codigo, Aeropuerto_Salida, Aeropuerto_Llegada, Salida, Llegada, Matricula_Avion, Precio_Pasaje) VALUES (57, 'APA', 'MIA', CAST('2014-12-14 07:05:00' AS SmallDateTime), CAST('2014-12-14 14:30:00' AS SmallDateTime), 'USA5077   ', 150.9500)
,(58, 'APA', 'JFK', CAST('2015-09-14 14:05:00' AS SmallDateTime), CAST('2015-09-14 17:30:00' AS SmallDateTime), 'USA5068   ', 150.9500)
,(59, 'APA', 'JFK', CAST('2015-10-14 14:05:00' AS SmallDateTime), CAST('2015-10-14 17:30:00' AS SmallDateTime), 'USA5068   ', 150.9500)
,(60, 'APA', 'JFK', CAST('2015-08-14 14:05:00' AS SmallDateTime), CAST('2015-08-14 17:30:00' AS SmallDateTime), 'USA5068   ', 150.9500)
,(61, 'APA', 'AGP', CAST('2016-01-14 14:05:00' AS SmallDateTime), CAST('2016-01-14 17:30:00' AS SmallDateTime), 'ESP4502   ', 850.9500)
,(62, 'APA', 'MAD', CAST('2010-07-10 07:05:00' AS SmallDateTime), CAST('2010-07-10 14:30:00' AS SmallDateTime), 'ESP8067   ', 450.9500)
,(63, 'APA', 'JFK', CAST('2014-08-21 14:05:00' AS SmallDateTime), CAST('2014-08-21 17:30:00' AS SmallDateTime), 'USA5068   ', 150.9500)

INSERT AS_Vuelos (Codigo, Aeropuerto_Salida, Aeropuerto_Llegada, Salida, Llegada, Matricula_Avion, Precio_Pasaje) VALUES (64, 'APA', 'MIA', CAST('2014-12-21 07:05:00' AS SmallDateTime), CAST('2014-12-21 14:30:00' AS SmallDateTime), 'USA5077   ', 150.9500)
,(65, 'APA', 'JFK', CAST('2014-09-21 14:05:00' AS SmallDateTime), CAST('2014-09-21 17:30:00' AS SmallDateTime), 'USA5068   ', 150.9500)
,(66, 'APA', 'JFK', CAST('2014-10-21 14:05:00' AS SmallDateTime), CAST('2014-10-21 17:30:00' AS SmallDateTime), 'USA5068   ', 150.9500)
,(67, 'APA', 'AGP', CAST('2011-01-21 14:05:00' AS SmallDateTime), CAST('2011-01-21 17:30:00' AS SmallDateTime), 'ESP4502   ', 850.9500)
,(68, 'APA', 'MAD', CAST('2010-07-21 07:05:00' AS SmallDateTime), CAST('2010-07-21 14:30:00' AS SmallDateTime), 'ESP8067   ', 450.9500)
,(69, 'CAI', 'MAD', CAST('2010-07-17 07:05:00' AS SmallDateTime), CAST('2010-07-17 14:30:00' AS SmallDateTime), 'FRA4502   ', 550.9500)
,(70, 'CAI', 'MAD', CAST('2010-07-24 07:05:00' AS SmallDateTime), CAST('2010-07-24 14:30:00' AS SmallDateTime), 'FRA4502   ', 550.9500)

INSERT AS_Vuelos (Codigo, Aeropuerto_Salida, Aeropuerto_Llegada, Salida, Llegada, Matricula_Avion, Precio_Pasaje) VALUES (71, 'CVG', 'JFK', CAST('2014-08-25 14:05:00' AS SmallDateTime), CAST('2014-08-25 17:30:00' AS SmallDateTime), 'USA5068   ', 155.9500)
,(72, 'CVG', 'JFK', CAST('2014-08-18 14:05:00' AS SmallDateTime), CAST('2014-08-18 17:30:00' AS SmallDateTime), 'USA5068   ', 155.9500)
,(73, 'LGA', 'NRT', CAST('2010-03-12 14:05:00' AS SmallDateTime), CAST('2010-03-12 17:30:00' AS SmallDateTime), 'FRA0955   ', 1255.9500)
,(74, 'LGA', 'AGP', CAST('2011-01-16 14:05:00' AS SmallDateTime), CAST('2011-01-17 07:30:00' AS SmallDateTime), 'ESP4502   ', 650.9500)
,(75, 'LGA', 'AGP', CAST('2011-01-23 14:05:00' AS SmallDateTime), CAST('2011-01-24 07:30:00' AS SmallDateTime), 'ESP4502   ', 650.9500)
,(76, 'LGA', 'NRT', CAST('2010-03-19 14:05:00' AS SmallDateTime), CAST('2010-03-19 17:30:00' AS SmallDateTime), 'FRA0955   ', 1255.9500)
,(77, 'MAD', 'LGA', CAST('2010-03-23 14:45:00' AS SmallDateTime), CAST('2010-03-23 22:30:00' AS SmallDateTime), 'FRA5021   ', 474.8500)

INSERT AS_Vuelos (Codigo, Aeropuerto_Salida, Aeropuerto_Llegada, Salida, Llegada, Matricula_Avion, Precio_Pasaje) VALUES (78, 'MAD', 'SVQ', CAST('2010-03-23 14:45:00' AS SmallDateTime), CAST('2010-03-23 18:30:00' AS SmallDateTime), 'ESP4502   ', 90.8500)
,(79, 'MAD', 'LGA', CAST('2010-03-16 14:45:00' AS SmallDateTime), CAST('2010-03-16 22:30:00' AS SmallDateTime), 'FRA5021   ', 474.8500)
,(80, 'MAD', 'SVQ', CAST('2010-11-21 14:45:00' AS SmallDateTime), CAST('2010-11-21 18:30:00' AS SmallDateTime), 'ESP4502   ', 90.8500)
,(81, 'MAD', 'SVQ', CAST('2010-11-14 14:45:00' AS SmallDateTime), CAST('2010-11-14 18:30:00' AS SmallDateTime), 'ESP4502   ', 90.8500)
,(82, 'MAD', 'SVQ', CAST('2010-03-16 14:45:00' AS SmallDateTime), CAST('2010-03-16 18:30:00' AS SmallDateTime), 'ESP4502   ', 90.8500)
,(83, 'MIA', 'JFK', CAST('2014-10-16 14:45:00' AS SmallDateTime), CAST('2014-10-16 18:30:00' AS SmallDateTime), 'USA5068   ', 290.9500)
,(84, 'MIA', 'JFK', CAST('2014-10-23 14:45:00' AS SmallDateTime), CAST('2014-10-23 18:30:00' AS SmallDateTime), 'USA5068   ', 290.9500)

INSERT AS_Vuelos (Codigo, Aeropuerto_Salida, Aeropuerto_Llegada, Salida, Llegada, Matricula_Avion, Precio_Pasaje) VALUES (85, 'PMI', 'SVQ', CAST('2010-04-14 14:05:00' AS SmallDateTime), CAST('2010-04-14 17:30:00' AS SmallDateTime), 'ESP8067   ', 80.5500)
,(86, 'PMI', 'NRT', CAST('2010-03-18 14:05:00' AS SmallDateTime), CAST('2010-03-18 17:30:00' AS SmallDateTime), 'ESP5077   ', 1255.9500)
,(87, 'PMI', 'NRT', CAST('2010-03-18 14:05:00' AS SmallDateTime), CAST('2010-03-18 17:30:00' AS SmallDateTime), 'ESP5077   ', 1255.9500)
,(88, 'PMI', 'NRT', CAST('2010-03-25 14:05:00' AS SmallDateTime), CAST('2010-03-25 17:30:00' AS SmallDateTime), 'ESP5077   ', 1255.9500)
,(89, 'PMI', 'SVQ', CAST('2010-04-21 14:05:00' AS SmallDateTime), CAST('2010-04-21 17:30:00' AS SmallDateTime), 'ESP8067   ', 80.5500)
,(90, 'PMI', 'NRT', CAST('2010-03-25 14:05:00' AS SmallDateTime), CAST('2010-03-25 17:30:00' AS SmallDateTime), 'ESP5077   ', 1255.9500)
,(91, 'SVQ', 'CAI', CAST('2010-08-21 14:05:00' AS SmallDateTime), CAST('2010-08-21 17:30:00' AS SmallDateTime), 'ESP5077   ', 450.7500)

INSERT AS_Vuelos (Codigo, Aeropuerto_Salida, Aeropuerto_Llegada, Salida, Llegada, Matricula_Avion, Precio_Pasaje) VALUES (92, 'SVQ', 'JFK', CAST('2010-05-21 14:05:00' AS SmallDateTime), CAST('2010-05-21 17:30:00' AS SmallDateTime), 'ESP5077   ', 450.7500)
,(93, 'SVQ', 'JFK', CAST('2016-05-21 14:05:00' AS SmallDateTime), CAST('2016-05-21 17:30:00' AS SmallDateTime), 'ESP5077   ', 450.7500)
,(94, 'SVQ', 'JFK', CAST('2016-05-14 14:05:00' AS SmallDateTime), CAST('2016-05-14 17:30:00' AS SmallDateTime), 'ESP5077   ', 450.7500)
,(95, 'SVQ', 'JFK', CAST('2016-05-14 14:05:00' AS SmallDateTime), CAST('2016-05-14 17:30:00' AS SmallDateTime), 'ESP5077   ', 450.7500)
,(96, 'SVQ', 'CAI', CAST('2016-08-14 14:05:00' AS SmallDateTime), CAST('2016-08-14 17:30:00' AS SmallDateTime), 'ESP5077   ', 450.7500)
,(97, 'AGP', 'SVQ', CAST('2016-02-14 14:05:00' AS SmallDateTime), CAST('2016-02-14 17:30:00' AS SmallDateTime), 'ESP8067   ', 50.5500)
,(98, 'AGP', 'FCO', CAST('2016-02-14 14:05:00' AS SmallDateTime), CAST('2016-02-14 17:30:00' AS SmallDateTime), 'ESP8067   ', 50.5500)
,(99, 'AGP', 'SVQ', CAST('2016-04-14 14:05:00' AS SmallDateTime), CAST('2016-04-14 17:30:00' AS SmallDateTime), 'ESP8067   ', 50.5500)
GO

INSERT AS_Vuelos (Codigo, Aeropuerto_Salida, Aeropuerto_Llegada, Salida, Llegada, Matricula_Avion, Precio_Pasaje) VALUES (100, 'AGP', 'FCO', CAST('2011-04-14 14:05:00' AS SmallDateTime), CAST('2011-04-14 17:30:00' AS SmallDateTime), 'ESP8067   ', 50.5500)
,(101, 'AGP', 'SVQ', CAST('2017-04-21 14:05:00' AS SmallDateTime), CAST('2017-04-21 17:30:00' AS SmallDateTime), 'ESP8067   ', 50.5500)
,(102, 'AGP', 'FCO', CAST('2017-04-21 14:05:00' AS SmallDateTime), CAST('2017-04-21 17:30:00' AS SmallDateTime), 'ESP8067   ', 50.5500)
,(103, 'AGP', 'SVQ', CAST('2017-02-21 14:05:00' AS SmallDateTime), CAST('2017-02-21 17:30:00' AS SmallDateTime), 'ESP8067   ', 50.5500)
,(104, 'AGP', 'FCO', CAST('2017-02-21 14:05:00' AS SmallDateTime), CAST('2017-02-21 17:30:00' AS SmallDateTime), 'ESP8067   ', 50.5500)
,(105, 'APA', 'MAD', CAST('2017-05-17 07:05:00' AS SmallDateTime), CAST('2017-05-17 14:30:00' AS SmallDateTime), 'ESP8067   ', 450.9500)
,(106, 'APA', 'AGP', CAST('2017-11-21 14:05:00' AS SmallDateTime), CAST('2017-11-21 17:30:00' AS SmallDateTime), 'ESP4502   ', 850.9500)

INSERT AS_Vuelos (Codigo, Aeropuerto_Salida, Aeropuerto_Llegada, Salida, Llegada, Matricula_Avion, Precio_Pasaje) VALUES (107, 'APA', 'MAD', CAST('2011-07-17 07:05:00' AS SmallDateTime), CAST('2011-07-17 14:30:00' AS SmallDateTime), 'ESP8067   ', 450.9500)
,(108, 'APA', 'AGP', CAST('2012-01-21 14:05:00' AS SmallDateTime), CAST('2012-01-21 17:30:00' AS SmallDateTime), 'ESP4502   ', 850.9500)
,(109, 'APA', 'AGP', CAST('2012-01-14 14:05:00' AS SmallDateTime), CAST('2012-01-14 17:30:00' AS SmallDateTime), 'ESP4502   ', 850.9500)
,(110, 'APA', 'MAD', CAST('2011-07-14 07:05:00' AS SmallDateTime), CAST('2011-07-14 14:30:00' AS SmallDateTime), 'ESP8067   ', 450.9500)
,(111, 'APA', 'MIA', CAST('2009-07-14 07:05:00' AS SmallDateTime), CAST('2009-07-14 14:30:00' AS SmallDateTime), 'USA5077   ', 150.9500)
,(112, 'APA', 'JFK', CAST('2009-04-14 14:05:00' AS SmallDateTime), CAST('2009-04-14 17:30:00' AS SmallDateTime), 'USA5068   ', 150.9500)
,(113, 'APA', 'JFK', CAST('2009-05-14 14:05:00' AS SmallDateTime), CAST('2009-05-14 17:30:00' AS SmallDateTime), 'USA5068   ', 150.9500)

INSERT AS_Vuelos (Codigo, Aeropuerto_Salida, Aeropuerto_Llegada, Salida, Llegada, Matricula_Avion, Precio_Pasaje) VALUES (114, 'APA', 'JFK', CAST('2009-03-14 14:05:00' AS SmallDateTime), CAST('2009-03-14 17:30:00' AS SmallDateTime), 'USA5068   ', 150.9500)
,(115, 'APA', 'AGP', CAST('2012-01-14 14:05:00' AS SmallDateTime), CAST('2012-01-14 17:30:00' AS SmallDateTime), 'ESP4502   ', 850.9500)
,(116, 'APA', 'MAD', CAST('2011-07-10 07:05:00' AS SmallDateTime), CAST('2011-07-10 14:30:00' AS SmallDateTime), 'ESP8067   ', 450.9500)
,(117, 'APA', 'JFK', CAST('2009-03-21 14:05:00' AS SmallDateTime), CAST('2009-03-21 17:30:00' AS SmallDateTime), 'USA5068   ', 150.9500)
,(118, 'APA', 'MIA', CAST('2009-07-21 07:05:00' AS SmallDateTime), CAST('2009-07-21 14:30:00' AS SmallDateTime), 'USA5077   ', 150.9500)
,(119, 'APA', 'JFK', CAST('2009-04-21 14:05:00' AS SmallDateTime), CAST('2009-04-21 17:30:00' AS SmallDateTime), 'USA5068   ', 150.9500)
,(120, 'APA', 'JFK', CAST('2009-05-21 14:05:00' AS SmallDateTime), CAST('2009-05-21 17:30:00' AS SmallDateTime), 'USA5068   ', 150.9500)

INSERT AS_Vuelos (Codigo, Aeropuerto_Salida, Aeropuerto_Llegada, Salida, Llegada, Matricula_Avion, Precio_Pasaje) VALUES (121, 'APA', 'AGP', CAST('2012-01-21 14:05:00' AS SmallDateTime), CAST('2012-01-21 17:30:00' AS SmallDateTime), 'ESP4502   ', 850.9500)
,(122, 'APA', 'MAD', CAST('2011-07-21 07:05:00' AS SmallDateTime), CAST('2011-07-21 14:30:00' AS SmallDateTime), 'ESP8067   ', 450.9500)
,(123, 'APA', 'AGP', CAST('2011-11-14 14:05:00' AS SmallDateTime), CAST('2011-11-14 17:30:00' AS SmallDateTime), 'ESP4502   ', 850.9500)
,(124, 'APA', 'MAD', CAST('2011-05-14 07:05:00' AS SmallDateTime), CAST('2011-05-14 14:30:00' AS SmallDateTime), 'ESP8067   ', 450.9500)
,(125, 'APA', 'MIA', CAST('2009-05-14 07:05:00' AS SmallDateTime), CAST('2009-05-14 14:30:00' AS SmallDateTime), 'USA5077   ', 150.9500)
,(126, 'APA', 'JFK', CAST('2009-02-14 14:05:00' AS SmallDateTime), CAST('2009-02-14 17:30:00' AS SmallDateTime), 'USA5068   ', 150.9500)
,(127, 'APA', 'JFK', CAST('2009-03-14 14:05:00' AS SmallDateTime), CAST('2009-03-14 17:30:00' AS SmallDateTime), 'USA5068   ', 150.9500)

INSERT AS_Vuelos (Codigo, Aeropuerto_Salida, Aeropuerto_Llegada, Salida, Llegada, Matricula_Avion, Precio_Pasaje) VALUES (128, 'APA', 'JFK', CAST('2009-01-14 14:05:00' AS SmallDateTime), CAST('2009-01-14 17:30:00' AS SmallDateTime), 'USA5068   ', 150.9500)
,(129, 'APA', 'AGP', CAST('2011-11-14 14:05:00' AS SmallDateTime), CAST('2011-11-14 17:30:00' AS SmallDateTime), 'ESP4502   ', 850.9500)
,(130, 'APA', 'MAD', CAST('2011-05-10 07:05:00' AS SmallDateTime), CAST('2011-05-10 14:30:00' AS SmallDateTime), 'ESP8067   ', 450.9500)
,(131, 'APA', 'JFK', CAST('2009-01-21 14:05:00' AS SmallDateTime), CAST('2009-01-21 17:30:00' AS SmallDateTime), 'USA5068   ', 150.9500)
,(132, 'APA', 'MIA', CAST('2009-05-21 07:05:00' AS SmallDateTime), CAST('2009-05-21 14:30:00' AS SmallDateTime), 'USA5077   ', 150.9500)
,(133, 'APA', 'JFK', CAST('2009-02-21 14:05:00' AS SmallDateTime), CAST('2009-02-21 17:30:00' AS SmallDateTime), 'USA5068   ', 150.9500)
,(134, 'APA', 'JFK', CAST('2009-03-21 14:05:00' AS SmallDateTime), CAST('2009-03-21 17:30:00' AS SmallDateTime), 'USA5068   ', 150.9500)

INSERT AS_Vuelos (Codigo, Aeropuerto_Salida, Aeropuerto_Llegada, Salida, Llegada, Matricula_Avion, Precio_Pasaje) VALUES (135, 'APA', 'AGP', CAST('2011-11-21 14:05:00' AS SmallDateTime), CAST('2011-11-21 17:30:00' AS SmallDateTime), 'ESP4502   ', 850.9500)
,(136, 'APA', 'MAD', CAST('2011-05-21 07:05:00' AS SmallDateTime), CAST('2011-05-21 14:30:00' AS SmallDateTime), 'ESP8067   ', 450.9500)
,(137, 'CAI', 'MAD', CAST('2011-05-17 07:05:00' AS SmallDateTime), CAST('2011-05-17 14:30:00' AS SmallDateTime), 'FRA4502   ', 550.9500)
,(138, 'CAI', 'MAD', CAST('2011-07-17 07:05:00' AS SmallDateTime), CAST('2011-07-17 14:30:00' AS SmallDateTime), 'FRA4502   ', 550.9500)
,(139, 'CAI', 'MAD', CAST('2011-07-24 07:05:00' AS SmallDateTime), CAST('2011-07-24 14:30:00' AS SmallDateTime), 'FRA4502   ', 550.9500)
,(140, 'CAI', 'MAD', CAST('2011-05-24 07:05:00' AS SmallDateTime), CAST('2011-05-24 14:30:00' AS SmallDateTime), 'FRA4502   ', 550.9500)
,(141, 'CVG', 'JFK', CAST('2009-03-25 14:05:00' AS SmallDateTime), CAST('2009-03-25 17:30:00' AS SmallDateTime), 'USA5068   ', 155.9500)

INSERT AS_Vuelos (Codigo, Aeropuerto_Salida, Aeropuerto_Llegada, Salida, Llegada, Matricula_Avion, Precio_Pasaje) VALUES (142, 'CVG', 'JFK', CAST('2009-03-18 14:05:00' AS SmallDateTime), CAST('2009-03-18 17:30:00' AS SmallDateTime), 'USA5068   ', 155.9500)
,(143, 'CVG', 'JFK', CAST('2009-01-25 14:05:00' AS SmallDateTime), CAST('2009-01-25 17:30:00' AS SmallDateTime), 'USA5068   ', 155.9500)
,(144, 'CVG', 'JFK', CAST('2009-01-18 14:05:00' AS SmallDateTime), CAST('2009-01-18 17:30:00' AS SmallDateTime), 'USA5068   ', 155.9500)
,(145, 'LGA', 'NRT', CAST('2011-01-12 14:05:00' AS SmallDateTime), CAST('2011-01-12 17:30:00' AS SmallDateTime), 'FRA0955   ', 1255.9500)
,(146, 'LGA', 'AGP', CAST('2011-11-16 14:05:00' AS SmallDateTime), CAST('2011-11-17 07:30:00' AS SmallDateTime), 'ESP4502   ', 650.9500)
,(147, 'LGA', 'NRT', CAST('2011-03-12 14:05:00' AS SmallDateTime), CAST('2011-03-12 17:30:00' AS SmallDateTime), 'FRA0955   ', 1255.9500)
,(148, 'LGA', 'AGP', CAST('2012-01-16 14:05:00' AS SmallDateTime), CAST('2012-01-17 07:30:00' AS SmallDateTime), 'ESP4502   ', 650.9500)

INSERT AS_Vuelos (Codigo, Aeropuerto_Salida, Aeropuerto_Llegada, Salida, Llegada, Matricula_Avion, Precio_Pasaje) VALUES (149, 'LGA', 'AGP', CAST('2012-01-23 14:05:00' AS SmallDateTime), CAST('2012-01-24 07:30:00' AS SmallDateTime), 'ESP4502   ', 650.9500)
,(150, 'LGA', 'NRT', CAST('2011-03-19 14:05:00' AS SmallDateTime), CAST('2011-03-19 17:30:00' AS SmallDateTime), 'FRA0955   ', 1255.9500)
,(151, 'LGA', 'AGP', CAST('2011-11-23 14:05:00' AS SmallDateTime), CAST('2011-11-24 07:30:00' AS SmallDateTime), 'ESP4502   ', 650.9500)
,(152, 'LGA', 'NRT', CAST('2011-01-19 14:05:00' AS SmallDateTime), CAST('2011-01-19 17:30:00' AS SmallDateTime), 'FRA0955   ', 1255.9500)
,(153, 'MAD', 'LGA', CAST('2011-01-23 14:45:00' AS SmallDateTime), CAST('2011-01-23 22:30:00' AS SmallDateTime), 'FRA5021   ', 474.8500)
,(154, 'MAD', 'SVQ', CAST('2011-01-23 14:45:00' AS SmallDateTime), CAST('2011-01-23 18:30:00' AS SmallDateTime), 'ESP4502   ', 90.8500)
,(155, 'MAD', 'LGA', CAST('2011-03-23 14:45:00' AS SmallDateTime), CAST('2011-03-23 22:30:00' AS SmallDateTime), 'FRA5021   ', 474.8500)

INSERT AS_Vuelos (Codigo, Aeropuerto_Salida, Aeropuerto_Llegada, Salida, Llegada, Matricula_Avion, Precio_Pasaje) VALUES (156, 'MAD', 'SVQ', CAST('2011-03-23 14:45:00' AS SmallDateTime), CAST('2011-03-23 18:30:00' AS SmallDateTime), 'ESP4502   ', 90.8500)
,(157, 'MAD', 'LGA', CAST('2011-03-16 14:45:00' AS SmallDateTime), CAST('2011-03-16 22:30:00' AS SmallDateTime), 'FRA5021   ', 474.8500)
,(158, 'MAD', 'SVQ', CAST('2011-11-21 14:45:00' AS SmallDateTime), CAST('2011-11-21 18:30:00' AS SmallDateTime), 'ESP4502   ', 90.8500)
,(159, 'MAD', 'SVQ', CAST('2011-11-14 14:45:00' AS SmallDateTime), CAST('2011-11-14 18:30:00' AS SmallDateTime), 'ESP4502   ', 90.8500)
,(160, 'MAD', 'SVQ', CAST('2011-03-16 14:45:00' AS SmallDateTime), CAST('2011-03-16 18:30:00' AS SmallDateTime), 'ESP4502   ', 90.8500)
,(161, 'MAD', 'LGA', CAST('2011-01-16 14:45:00' AS SmallDateTime), CAST('2011-01-16 22:30:00' AS SmallDateTime), 'FRA5021   ', 474.8500)
,(162, 'MAD', 'SVQ', CAST('2011-09-21 14:45:00' AS SmallDateTime), CAST('2011-09-21 18:30:00' AS SmallDateTime), 'ESP4502   ', 90.8500)

INSERT AS_Vuelos (Codigo, Aeropuerto_Salida, Aeropuerto_Llegada, Salida, Llegada, Matricula_Avion, Precio_Pasaje) VALUES (163, 'MAD', 'SVQ', CAST('2011-09-14 14:45:00' AS SmallDateTime), CAST('2011-09-14 18:30:00' AS SmallDateTime), 'ESP4502   ', 90.8500)
,(164, 'MAD', 'SVQ', CAST('2011-01-16 14:45:00' AS SmallDateTime), CAST('2011-01-16 18:30:00' AS SmallDateTime), 'ESP4502   ', 90.8500)
,(165, 'MIA', 'JFK', CAST('2009-03-16 14:45:00' AS SmallDateTime), CAST('2009-03-16 18:30:00' AS SmallDateTime), 'USA5068   ', 290.9500)
,(166, 'MIA', 'JFK', CAST('2009-03-23 14:45:00' AS SmallDateTime), CAST('2009-03-23 18:30:00' AS SmallDateTime), 'USA5068   ', 290.9500)
,(167, 'MIA', 'JFK', CAST('2009-05-16 14:45:00' AS SmallDateTime), CAST('2009-05-16 18:30:00' AS SmallDateTime), 'USA5068   ', 290.9500)
,(168, 'MIA', 'JFK', CAST('2009-05-23 14:45:00' AS SmallDateTime), CAST('2009-05-23 18:30:00' AS SmallDateTime), 'USA5068   ', 290.9500)
,(169, 'PMI', 'SVQ', CAST('2011-04-14 14:05:00' AS SmallDateTime), CAST('2011-04-14 17:30:00' AS SmallDateTime), 'ESP8067   ', 80.5500)

INSERT AS_Vuelos (Codigo, Aeropuerto_Salida, Aeropuerto_Llegada, Salida, Llegada, Matricula_Avion, Precio_Pasaje) VALUES (170, 'PMI', 'NRT', CAST('2011-03-18 14:05:00' AS SmallDateTime), CAST('2011-03-18 17:30:00' AS SmallDateTime), 'ESP5077   ', 1255.9500)
,(171, 'PMI', 'NRT', CAST('2017-03-18 14:05:00' AS SmallDateTime), CAST('2017-03-18 17:30:00' AS SmallDateTime), 'ESP5077   ', 1255.9500)
,(172, 'PMI', 'NRT', CAST('2017-03-25 14:05:00' AS SmallDateTime), CAST('2017-03-25 17:30:00' AS SmallDateTime), 'ESP5077   ', 1255.9500)
,(173, 'PMI', 'SVQ', CAST('2017-04-21 14:05:00' AS SmallDateTime), CAST('2017-04-21 17:30:00' AS SmallDateTime), 'ESP8067   ', 80.5500)
,(174, 'PMI', 'NRT', CAST('2018-01-25 14:05:00' AS SmallDateTime), CAST('2018-01-25 17:30:00' AS SmallDateTime), 'ESP5077   ', 1255.9500)
,(175, 'PMI', 'SVQ', CAST('2017-02-14 14:05:00' AS SmallDateTime), CAST('2017-02-14 17:30:00' AS SmallDateTime), 'ESP8067   ', 80.5500)
,(176, 'PMI', 'NRT', CAST('2017-01-18 14:05:00' AS SmallDateTime), CAST('2017-01-18 17:30:00' AS SmallDateTime), 'ESP5077   ', 1255.9500)

INSERT AS_Vuelos (Codigo, Aeropuerto_Salida, Aeropuerto_Llegada, Salida, Llegada, Matricula_Avion, Precio_Pasaje) VALUES (177, 'PMI', 'NRT', CAST('2011-01-18 14:05:00' AS SmallDateTime), CAST('2011-01-18 17:30:00' AS SmallDateTime), 'ESP5077   ', 1255.9500)
,(178, 'PMI', 'NRT', CAST('2016-01-25 14:05:00' AS SmallDateTime), CAST('2016-01-25 17:30:00' AS SmallDateTime), 'ESP5077   ', 1255.9500)
,(179, 'PMI', 'SVQ', CAST('2016-02-21 14:05:00' AS SmallDateTime), CAST('2016-02-21 17:30:00' AS SmallDateTime), 'ESP8067   ', 80.5500)
,(180, 'PMI', 'NRT', CAST('2016-01-25 14:05:00' AS SmallDateTime), CAST('2016-01-25 17:30:00' AS SmallDateTime), 'ESP5077   ', 1255.9500)
,(181, 'SVQ', 'CAI', CAST('2016-06-21 14:05:00' AS SmallDateTime), CAST('2016-06-21 17:30:00' AS SmallDateTime), 'ESP5077   ', 450.7500)
,(182, 'SVQ', 'JFK', CAST('2016-03-21 14:05:00' AS SmallDateTime), CAST('2016-03-21 17:30:00' AS SmallDateTime), 'ESP5077   ', 450.7500)
,(183, 'SVQ', 'JFK', CAST('2016-03-21 14:05:00' AS SmallDateTime), CAST('2016-03-21 17:30:00' AS SmallDateTime), 'ESP5077   ', 450.7500)

INSERT AS_Vuelos (Codigo, Aeropuerto_Salida, Aeropuerto_Llegada, Salida, Llegada, Matricula_Avion, Precio_Pasaje) VALUES (184, 'SVQ', 'JFK', CAST('2011-03-14 14:05:00' AS SmallDateTime), CAST('2011-03-14 17:30:00' AS SmallDateTime), 'ESP5077   ', 450.7500)
,(185, 'SVQ', 'JFK', CAST('2017-03-14 14:05:00' AS SmallDateTime), CAST('2017-03-14 17:30:00' AS SmallDateTime), 'ESP5077   ', 450.7500)
,(186, 'SVQ', 'CAI', CAST('2017-06-14 14:05:00' AS SmallDateTime), CAST('2017-06-14 17:30:00' AS SmallDateTime), 'ESP5077   ', 450.7500)
,(187, 'SVQ', 'CAI', CAST('2018-01-04 14:05:00' AS SmallDateTime), CAST('2018-01-04 17:30:00' AS SmallDateTime), 'ESP5077   ', 380.7500)
,(188, 'SVQ', 'JFK', CAST('2017-05-21 14:05:00' AS SmallDateTime), CAST('2017-05-21 17:30:00' AS SmallDateTime), 'ESP5077   ', 450.7500)
,(189, 'SVQ', 'JFK', CAST('2017-12-21 14:05:00' AS SmallDateTime), CAST('2017-12-21 21:14' AS SmallDateTime), 'ESP5077   ', 450.7500)
,(190, 'SVQ', 'JFK', CAST('2017-05-14 14:05:00' AS SmallDateTime), CAST('2017-05-14 17:30:00' AS SmallDateTime), 'ESP5077   ', 450.7500)
,(191, 'SVQ', 'JFK', CAST('2017-05-14 14:05:00' AS SmallDateTime), CAST('2017-05-14 17:30:00' AS SmallDateTime), 'ESP5077   ', 450.7500)
,(192, 'SVQ', 'CAI', CAST('2017-12-19 14:05:00' AS SmallDateTime), CAST('2017-12-19 17:53' AS SmallDateTime), 'ESP5077   ', 290.1000)
SET IDENTITY_INSERT AS_Vuelos OFF
--SET IDENTITY_INSERT AS_Incidencias ON
GO

INSERT INTO AS_Incidencias (Momento,Avion,Latitud,Longitud,Descripcion,Tipo) VALUES (CAST('2017-08-14 17:20:00' AS SmallDateTime),'ESP5077   ',30.751,-2.357,'Fallo menor en el GPS','A')
,(CAST('2016-06-14 11:20' AS SmallDateTime),'USA5068   ',28.751,-24.357,N'Pérdida de potencia en el motor de estribor','A')
,(CAST('2016-02-11 1:30' AS SmallDateTime),'USA5068   ',15.249,-12.778,N'La radio no funciona','A')
,(CAST('2016-11-06 08:08' AS SmallDateTime),'FRA5021   ',30.751,-2.522,N'Pérdida de potencia en el motor de estribor','A')
,(CAST('2017-01-22 05:48' AS SmallDateTime),'ESP4502   ',38.751,4.704,N'Aterrizaje forzoso en el aeropuerto de Málaga','P')
,(CAST('2015-04-24 11:20' AS SmallDateTime),'USA5068   ',44.023,124.099,N'Maniobra de evasión para evitar colisión','P')
,(CAST('2015-12-02 16:04' AS SmallDateTime),'USA5077   ',-21.1083,105.099,N'La tripulación fumó marihuana durante el vuelo','O')
GO

USE [AirSafety]
GO

INSERT INTO EX_Actualizaciones(MatriculaAvion,Latitud,Longitud,Descripcion,Tipo,AccidenteDefinitivo,NombreAvion,Fabricante,Modelo,Fecha_Fabricacion,Fecha_Entrada,Filas,Asientos_x_Fila,Autonomia)
     VALUES ('USA174139 ',-14.38,205.731,N'Aterrizaje de emergencia','P',0,Null,Null,Null,Null,Null,Null,Null,Null)
	 ,('ESP5077   ',-34.38,5.731,N'Incendio en el motor de cola','A',1,Null,Null,Null,Null,Null,Null,Null,Null)
	 ,('ESP8067   ',24.07,0.21,N'Puerta principal de cabina mal ajustada','A',0,Null,Null,Null,Null,Null,Null,Null,Null)
	 ,('GBR1137   ',23.36,1.82,N'Varios pasajeros intoxicados','O',0, 'Sussex', 'Boeing', 'B737', CAST('2017-02-20' AS Date), CAST('2017-05-03' AS Date), 24, 6, 3300)
	 ,('FRA5021   ',58.715,-24.307,N'Se acabó el hielo para los Gin¬Tonic del piloto','O',0,Null,Null,Null,Null,Null,Null,Null,Null)
GO



ALTER TABLE AS_Aviones  WITH CHECK ADD  CONSTRAINT FK_Aviones_Fabricantes FOREIGN KEY(ID_Fabricante)
REFERENCES AS_Fabricantes (ID_Fabricante)

GO
ALTER TABLE AS_Vuelos  WITH CHECK ADD  CONSTRAINT FK_Vuelos_AerLlegada FOREIGN KEY(Aeropuerto_Llegada)
REFERENCES AS_Aeropuertos (Codigo)

GO
ALTER TABLE AS_Vuelos  WITH CHECK ADD  CONSTRAINT FK_Vuelos_AerSalida FOREIGN KEY(Aeropuerto_Salida)
REFERENCES AS_Aeropuertos (Codigo)
ON UPDATE CASCADE

GO
ALTER TABLE AS_Vuelos  WITH CHECK ADD  CONSTRAINT FK_Vuelos_Aviones FOREIGN KEY(Matricula_Avion)
REFERENCES AS_Aviones (Matricula)

GO
ALTER TABLE AS_Aviones  WITH CHECK ADD  CONSTRAINT CK_Fechas CHECK  (Fecha_Fabricacion<=Fecha_Entrada)

GO
ALTER TABLE AS_Vuelos  WITH CHECK ADD  CONSTRAINT CK_AeropuertosDistIntos CHECK  ((Aeropuerto_Salida<>Aeropuerto_Llegada))

GO
ALTER TABLE AS_Vuelos  WITH CHECK ADD  CONSTRAINT CK_Einstein CHECK  ((Salida<Llegada))
