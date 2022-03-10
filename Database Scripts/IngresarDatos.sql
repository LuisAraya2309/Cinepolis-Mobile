-- Ingresar tipos de alimentos

Insert into dbo.TipoAlimentos
	VALUES
	(
		'Bebidas'
	)

Insert into dbo.TipoAlimentos
	VALUES
	(
		'Dulces'
	)

Insert into dbo.TipoAlimentos
	VALUES
	(
		'Aperitivos'
	)


-- -- -- -- Ingresar alimentos

--Ingresar Bebidas

DECLARE @imagenAlimento VARBINARY(MAX);

SET @imagenAlimento = (SELECT BulkColumn FROM OPENROWSET(BULK 'C:\Users\Sebastian\Desktop\TEC\VSemestre\Administracion\Proyecto\Cinepolis-Mobile\Images\coca-cola.jpg',SINGLE_BLOB) AS Imagen);
Insert into dbo.Alimentos
	VALUES
	(
		'Coca-cola',
		400,
		1,
		1200,
		@imagenAlimento

	)

SET @imagenAlimento = (SELECT BulkColumn FROM OPENROWSET(BULK 'C:\Users\Sebastian\Desktop\TEC\VSemestre\Administracion\Proyecto\Cinepolis-Mobile\Images\cafe.png',SINGLE_BLOB) AS Imagen);
Insert into dbo.Alimentos
	VALUES
	(
		'Refresco mediano',
		400,
		1,
		1300,
		@imagenAlimento

	)
SET @imagenAlimento = (SELECT BulkColumn FROM OPENROWSET(BULK 'C:\Users\Sebastian\Desktop\TEC\VSemestre\Administracion\Proyecto\Cinepolis-Mobile\Images\botellaAlpina.jpg',SINGLE_BLOB) AS Imagen);
Insert into dbo.Alimentos
	VALUES
	(
		'Botella con agua',
		400,
		1,
		900,
		@imagenAlimento
	)


--Ingresar dulces
SET @imagenAlimento = (SELECT BulkColumn FROM OPENROWSET(BULK 'C:\Users\Sebastian\Desktop\TEC\VSemestre\Administracion\Proyecto\Cinepolis-Mobile\Images\kitkat.jpg',SINGLE_BLOB) AS Imagen);
Insert into dbo.Alimentos
	VALUES
	(
		'Kit kat',
		15,
		2,
		600,
		@imagenAlimento

	)
SET @imagenAlimento = (SELECT BulkColumn FROM OPENROWSET(BULK 'C:\Users\Sebastian\Desktop\TEC\VSemestre\Administracion\Proyecto\Cinepolis-Mobile\Images\DulcesSkittles.jpg',SINGLE_BLOB) AS Imagen);
Insert into dbo.Alimentos
	VALUES
	(
		'Skittles',
		15,
		2,
		950

	)
SET @imagenAlimento = (SELECT BulkColumn FROM OPENROWSET(BULK 'C:\Users\Sebastian\Desktop\TEC\VSemestre\Administracion\Proyecto\Cinepolis-Mobile\Images\palomitasMantequilla.png',SINGLE_BLOB) AS Imagen);
Insert into dbo.Alimentos
	VALUES
	(
		'Palomitas clasicas',
		10,
		2,
		2100,
		@imagenAlimento

	)

SET @imagenAlimento = (SELECT BulkColumn FROM OPENROWSET(BULK 'C:\Users\Sebastian\Desktop\TEC\VSemestre\Administracion\Proyecto\Cinepolis-Mobile\Images\palomitasDulces.png',SINGLE_BLOB) AS Imagen);
Insert into dbo.Alimentos
	VALUES
	(
		'Palomitas dulces',
		10,
		2,
		2300,
		@imagenAlimento

	)

--Ingresar aperitivos 
SET @imagenAlimento = (SELECT BulkColumn FROM OPENROWSET(BULK 'C:\Users\Sebastian\Desktop\TEC\VSemestre\Administracion\Proyecto\Cinepolis-Mobile\Images\hotDog.png',SINGLE_BLOB) AS Imagen);
Insert into dbo.Alimentos
	VALUES
	(
		'Hot dog',
		40,
		3,
		1700,
		@imagenAlimento

	)
SET @imagenAlimento = (SELECT BulkColumn FROM OPENROWSET(BULK 'C:\Users\Sebastian\Desktop\TEC\VSemestre\Administracion\Proyecto\Cinepolis-Mobile\Images\nachosCinepolis.png',SINGLE_BLOB) AS Imagen);
Insert into dbo.Alimentos
	VALUES
	(
		'Nachos',
		50,
		3,
		1700,
		@imagenAlimento

	)

SET @imagenAlimento = (SELECT BulkColumn FROM OPENROWSET(BULK 'C:\Users\Sebastian\Desktop\TEC\VSemestre\Administracion\Proyecto\Cinepolis-Mobile\Images\sandwichCinepolis.jpg',SINGLE_BLOB) AS Imagen);
Insert into dbo.Alimentos
	VALUES
	(
		'Sandwich',
		50,
		3,
		1700,
		@imagenAlimento

	)


--Ingresar salas 

Insert into dbo.Salas
	VALUES
	(
		1,
		126
	)

Insert into dbo.Salas
	VALUES
	(
		2,
		126
	)

Insert into dbo.Salas
	VALUES
	(
		3,
		126
	)

Insert into dbo.Salas
	VALUES
	(
		4,
		88
	)

Insert into dbo.Salas
	VALUES
	(
		5,
		224
	)

Insert into dbo.Salas
	VALUES
	(
		6,
		88
	)

--Ingresar peliculas
DECLARE @imagenPelicula VARBINARY(MAX);

SET @imagenPelicula = (SELECT BulkColumn FROM OPENROWSET(BULK 'C:\Users\Sebastian\Desktop\TEC\VSemestre\Administracion\Proyecto\Cinepolis-Mobile\Images\theBatman.jpg',SINGLE_BLOB) AS Imagen);
Insert into dbo.Pelicula
	VALUES
	(
		'Batman',
		'Matt Reeves',
		'{1:Andy Serkis, 2:Robert Pattinson, 3:Zo� Kravitz}',
		'{1:Acci�n, 2:Aventura}',
		'{1:Espa�ol, 2:Ingles}',
		2022,
		176,
		12,
		@imagenPelicula
	)

SET @imagenPelicula = (SELECT BulkColumn FROM OPENROWSET(BULK 'C:\Users\Sebastian\Desktop\TEC\VSemestre\Administracion\Proyecto\Cinepolis-Mobile\Images\sing.jpg',SINGLE_BLOB) AS Imagen);
Insert into dbo.Pelicula
	VALUES
	(
		'Sing 2: �Ven y canta de nuevo!',
		'Garth Jennings',
		'{1:Letitia Wright, 2:Tori Kelly, 3:Bobby Cannavale}',
		'{1:Infantil, 2:Comedia}',
		'{1:Espa�ol, 2:Ingles}',
		2021,
		112,
		0,
		@imagenPelicula
	)

SET @imagenPelicula = (SELECT BulkColumn FROM OPENROWSET(BULK 'C:\Users\Sebastian\Desktop\TEC\VSemestre\Administracion\Proyecto\Cinepolis-Mobile\Images\CastilloMaldito.jpg',SINGLE_BLOB) AS Imagen);
Insert into dbo.Pelicula
	VALUES
	(
		'Castillo Maldito',
		'Fionn Watts',
		'{1:William Holstead, 2:Hellen Mackay, 3:Julie Higginson}',
		'{1:Terror, 2:Suspenso}',
		'{1:Espa�ol, 2:Ingles}',
		2020,
		87,
		15,
		@imagenPelicula
	)

SET @imagenPelicula = (SELECT BulkColumn FROM OPENROWSET(BULK 'C:\Users\Sebastian\Desktop\TEC\VSemestre\Administracion\Proyecto\Cinepolis-Mobile\Images\Belfast.jpg',SINGLE_BLOB) AS Imagen);
Insert into dbo.Pelicula
	VALUES
	(
		'Belfast',
		'Kenneth Branagh',
		'{1:Jamie Dornan, 2:Caitriona Balfe, 3:Judi Dench}',
		'{1:Drama, 2:Comedia dram�tica}',
		'{1:Ingles}',
		2022,
		98,
		0,
		@imagenPelicula
	)

--Ingresar tipos de usuarios

INSERT INTO dbo.TipoUsuarios 
	VALUES(
		'Administrador'
	)

INSERT INTO dbo.TipoUsuarios 
	VALUES(
		'Cliente'
	)


--Ingresar usuarios

INSERT INTO dbo.Usuarios 
	VALUES(
		'Cristian',
		'cristiangm2309@gmail.com',
		'contrase�a123',
		1,
		'G�mez',
		'Madrigal'

	)

INSERT INTO dbo.Usuarios 
	VALUES(
		'Tatiana',
		'tatiana292dm@gmail.com',
		'contrase�a123',
		1,
		'D�az',
		'Mora'
	)

INSERT INTO dbo.Usuarios 
	VALUES(
		'Juliana',
		'julianalc01@gmail.com',
		'contrase�a123',
		2,
		'Leiva',
		'Coto'
	)

INSERT INTO dbo.Clientes
	VALUES(
			SCOPE_IDENTITY(),
			305420217,
			22,
			'2000-02-17',
			'Primera dosis'
	)

INSERT INTO dbo.Tarjetas
	VALUES(
		SCOPE_IDENTITY(),
		'JULIANA LEIVA C',
		'1234765698786754',
		'2024-08-29',
		764
	)

INSERT INTO dbo.Usuarios 
	VALUES(
		'Ver�nica',
		'veroca06es@gmail.com',
		'contrase�a123',
		2,
		'Castro',
		'Espinosa'
	)

INSERT INTO dbo.Clientes
	VALUES(
			SCOPE_IDENTITY(),
			106470874,
			18,
			'2004-03-07',
			'Segunda dosis'
	)

INSERT INTO dbo.Tarjetas
	VALUES(
		SCOPE_IDENTITY(),
		'VERONICA CASTRO E',
		'9043872567439867',
		'2025-02-11',
		223
	)

INSERT INTO dbo.Usuarios 
	VALUES(
		'Ignacio',
		'nachoararo989@gmail.com',
		'contrase�a123',
		2,
		'Araya',
		'Robles'
	)

INSERT INTO dbo.Clientes
	VALUES(
			SCOPE_IDENTITY(),
			501820419,
			12,
			'2010-01-30',
			'Segunda dosis'
	)

INSERT INTO dbo.Tarjetas
	VALUES(
		SCOPE_IDENTITY(),
		'IGNACIO ARAYA R',
		'8834775465098926',
		'2022-11-01',
		572
	)

INSERT INTO dbo.Usuarios 
	VALUES(
		'Franco',
		'frantefe777@gmail.com',
		'contrase�a123',
		2,
		'Fernandez',
		'Tenorio'
	)

INSERT INTO dbo.Clientes
	VALUES(
			SCOPE_IDENTITY(),
			302230456,
			28,
			'1991-11-27',
			'Esquema completo'
	)

INSERT INTO dbo.Tarjetas
	VALUES(
		SCOPE_IDENTITY(),
		'FRANCO FERNANDEZ T',
		'7843561192534783',
		'2023-07-23',
		335
	)

--Primera pelicula
INSERT INTO dbo.Funciones
	VALUES(
		1,
		1,
		'2022-03-10',
		'12:00',
		'{1:i2, 2:i3, 3:d9, 4:d8, 5:d7}'
	)

INSERT INTO dbo.Funciones
	VALUES(
		2,
		1,
		'2022-03-10',
		'14:30',
		'{1:h5, 2:i3, 3:e11, 4:e10, 5:f4}'
	)

INSERT INTO dbo.Funciones
	VALUES(
		3,
		1,
		'2022-03-10',
		'16:45',
		'{1:c5, 2:g11, 3:g12, 4:g13, 5:g14}'
	)


INSERT INTO dbo.Funciones
	VALUES(
		1,
		1,
		'2022-03-10',
		'19:00',
		'{1:h3, 2:h6, 3:h7, 4:d8, 5:d7}'
	)

--Segunda pelicula
INSERT INTO dbo.Funciones
	VALUES(
		4,
		2,
		'2022-03-10',
		'12:45',
		'{1:c5, 2:g11, 3:g12, 4:g13, 5:g14}'
	)

INSERT INTO dbo.Funciones
	VALUES(
		5,
		2,
		'2022-03-10',
		'14:00',
		'{1:h3, 2:h6, 3:h7, 4:d8, 5:d7}'
	)

INSERT INTO dbo.Funciones
	VALUES(
		1,
		2,
		'2022-03-10',
		'15:00',
		'{1:i2, 2:i3, 3:d9, 4:d8, 5:d7}'
	)



INSERT INTO dbo.Funciones
	VALUES(
		2,
		2,
		'2022-03-10',
		'17:30',
		'{1:h5, 2:i3, 3:e11, 4:e10, 5:f4}'
	)

--Tercera pelicula

INSERT INTO dbo.Funciones
	VALUES(
		5,
		3,
		'2022-03-10',
		'12:00',
		'{1:h3, 2:h6, 3:h7, 4:d8, 5:d7}'
	)

INSERT INTO dbo.Funciones
	VALUES(
		3,
		3,
		'2022-03-10',
		'14:00',
		'{1:i2, 2:i3, 3:d9, 4:d8, 5:d7}'
	)

INSERT INTO dbo.Funciones
	VALUES(
		4,
		3,
		'2022-03-10',
		'15:00',
		'{1:c5, 2:g11, 3:g12, 4:g13, 5:g14}'
	)


INSERT INTO dbo.Funciones
	VALUES(
		2,
		3,
		'2022-03-10',
		'20:00',
		'{1:h5, 2:i3, 3:e11, 4:e10, 5:f4}'
	)

--Cuarta pelicula

INSERT INTO dbo.Funciones
	VALUES(
		6,
		4,
		'2022-03-10',
		'12:00',
		'{1:h3, 2:h6, 3:h7, 4:d8, 5:d7}'
	)

INSERT INTO dbo.Funciones
	VALUES(
		6,
		4,
		'2022-03-10',
		'14:00',
		'{1:i2, 2:i3, 3:d9, 4:d8, 5:d7}'
	)

INSERT INTO dbo.Funciones
	VALUES(
		6,
		4,
		'2022-03-10',
		'16:00',
		'{1:c5, 2:g11, 3:g12, 4:g13, 5:g14}'
	)


INSERT INTO dbo.Funciones
	VALUES(
		6,
		4,
		'2022-03-10',
		'18:00',
		'{1:h5, 2:i3, 3:e11, 4:e10, 5:f4}'
	)
