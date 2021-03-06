USE [master]
GO
/****** Object:  Database [CinepolisBD]    Script Date: 07/03/2022 8:09:34 ******/
CREATE DATABASE [CinepolisBD]
GO
ALTER DATABASE [CinepolisBD] SET COMPATIBILITY_LEVEL = 150
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [CinepolisBD].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [CinepolisBD] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [CinepolisBD] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [CinepolisBD] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [CinepolisBD] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [CinepolisBD] SET ARITHABORT OFF 
GO
ALTER DATABASE [CinepolisBD] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [CinepolisBD] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [CinepolisBD] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [CinepolisBD] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [CinepolisBD] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [CinepolisBD] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [CinepolisBD] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [CinepolisBD] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [CinepolisBD] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [CinepolisBD] SET  DISABLE_BROKER 
GO
ALTER DATABASE [CinepolisBD] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [CinepolisBD] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [CinepolisBD] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [CinepolisBD] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [CinepolisBD] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [CinepolisBD] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [CinepolisBD] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [CinepolisBD] SET RECOVERY SIMPLE 
GO
ALTER DATABASE [CinepolisBD] SET  MULTI_USER 
GO
ALTER DATABASE [CinepolisBD] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [CinepolisBD] SET DB_CHAINING OFF 
GO
ALTER DATABASE [CinepolisBD] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [CinepolisBD] SET TARGET_RECOVERY_TIME = 60 SECONDS 
GO
ALTER DATABASE [CinepolisBD] SET DELAYED_DURABILITY = DISABLED 
GO
ALTER DATABASE [CinepolisBD] SET ACCELERATED_DATABASE_RECOVERY = OFF  
GO
ALTER DATABASE [CinepolisBD] SET QUERY_STORE = OFF
GO
USE [CinepolisBD]
GO
/****** Object:  User [usersql]    Script Date: 07/03/2022 8:09:34 ******/
GO
/****** Object:  Table [dbo].[Alimentos]    Script Date: 07/03/2022 8:09:34 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Alimentos](
	[Id] [int] IDENTITY(1,1) NOT NULL,
	[Nombre] [varchar](64) NOT NULL,
	[Cantidad] [int] NOT NULL,
	[IdTipoAlimentos] [int] NOT NULL,
	[Precio] [money] NOT NULL,
	[Imagen] [varbinary](max) NOT NULL,
 CONSTRAINT [PK_Alimentos] PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Carritos]    Script Date: 07/03/2022 8:09:34 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Carritos](
	[Id] [int] IDENTITY(1,1) NOT NULL,
	[IdCliente] [int] NOT NULL,
	[Productos] [nvarchar](max) NOT NULL,
 CONSTRAINT [PK_Carritos] PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Clientes]    Script Date: 07/03/2022 8:09:34 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Clientes](
	[Id] [int] NOT NULL,
	[Identificacion] [int] NOT NULL,
	[Edad] [int] NOT NULL,
	[FechaNacimiento] [date] NOT NULL,
	[EsquemaVacunacion] [varchar](64) NOT NULL,
 CONSTRAINT [PK_Clientes] PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Facturas]    Script Date: 07/03/2022 8:09:34 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Facturas](
	[Id] [int] IDENTITY(1,1) NOT NULL,
	[Fecha] [date] NOT NULL,
	[IdCliente] [int] NOT NULL,
	[MontoTotal] [money] NOT NULL,
 CONSTRAINT [PK_Facturas] PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[FacturasLineas]    Script Date: 07/03/2022 8:09:34 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[FacturasLineas](
	[Id] [int] IDENTITY(1,1) NOT NULL,
	[IdFactura] [int] NOT NULL,
	[TipoArticulo] [varchar](64) NOT NULL,
	[IdArticulo] [int] NOT NULL,
	[Cantidad] [int] NOT NULL,
	[MontoUnitario] [money] NOT NULL,
	[MontoTotal] [money] NOT NULL,
 CONSTRAINT [PK_FacturasLineas] PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Funciones]    Script Date: 07/03/2022 8:09:34 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Funciones](
	[Id] [int] IDENTITY(1,1) NOT NULL,
	[IdSala] [int] NOT NULL,
	[IdPelicula] [int] NOT NULL,
	[Fecha] [date] NOT NULL,
	[Hora] [time](0) NOT NULL,
	[AsientosDisponibles] [nvarchar](max) NOT NULL,
 CONSTRAINT [PK_Funciones] PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Pelicula]    Script Date: 07/03/2022 8:09:34 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Pelicula](
	[Id] [int] IDENTITY(1,1) NOT NULL,
	[Titulo] [varchar](64) NOT NULL,
	[Director] [varchar](64) NOT NULL,
	[Actores] [nvarchar](max) NOT NULL,
	[Generos] [nvarchar](max) NOT NULL,
	[Idiomas] [nvarchar](max) NOT NULL,
	[AñoPublicacion] [int] NOT NULL,
	[Duracion] [int] NOT NULL,
	[EdadRequerida] [int] NOT NULL,
	[Imagen] [varbinary](max) NOT NULL,
 CONSTRAINT [PK_Pelicula] PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[PrecioEntrada]    Script Date: 07/03/2022 8:09:34 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[PrecioEntrada](
	[Id] [int] IDENTITY(1,1) NOT NULL,
	[Nombre] [varchar](64) NOT NULL,
	[Precio] [money] NOT NULL,
 CONSTRAINT [PK_PrecioEntrada] PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Salas]    Script Date: 07/03/2022 8:09:34 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Salas](
	[Id] [int] IDENTITY(1,1) NOT NULL,
	[NumeroSala] [int] NOT NULL,
	[CantidadDisponible] [int] NOT NULL,
 CONSTRAINT [PK_Salas] PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Tarjetas]    Script Date: 07/03/2022 8:09:34 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Tarjetas](
	[Id] [int] IDENTITY(1,1) NOT NULL,
	[IdCliente] [int] NOT NULL,
	[Titular] [varchar](64) NOT NULL,
	[NumeroTarjeta] [varchar](16) NOT NULL,
	[FechaVencimiento] [date] NOT NULL,
	[NumeroSeguridad] [int] NOT NULL,
 CONSTRAINT [PK_Tarjetas] PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[TipoAlimentos]    Script Date: 07/03/2022 8:09:34 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[TipoAlimentos](
	[Id] [int] IDENTITY(1,1) NOT NULL,
	[Nombre] [varchar](64) NOT NULL,
 CONSTRAINT [PK_TipoAlimentos] PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[TipoUsuarios]    Script Date: 07/03/2022 8:09:34 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[TipoUsuarios](
	[Id] [int] IDENTITY(1,1) NOT NULL,
	[Nombre] [varchar](64) NOT NULL,
 CONSTRAINT [PK_TipoUsuarios] PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Usuarios]    Script Date: 07/03/2022 8:09:34 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Usuarios](
	[Id] [int] IDENTITY(1,1) NOT NULL,
	[Nombre] [varchar](64) NOT NULL,
	[CorreoElectronico] [varchar](64) NOT NULL,
	[Contraseña] [varchar](64) NOT NULL,
	[IdTipoUsuarios] [int] NOT NULL,
	[Apellido1] [varchar](64) NOT NULL,
	[Apellido2] [varchar](64) NOT NULL,
 CONSTRAINT [PK_Usuarios] PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
ALTER TABLE [dbo].[Alimentos]  WITH CHECK ADD  CONSTRAINT [FK_Alimentos_TipoAlimentos] FOREIGN KEY([IdTipoAlimentos])
REFERENCES [dbo].[TipoAlimentos] ([Id])
GO
ALTER TABLE [dbo].[Alimentos] CHECK CONSTRAINT [FK_Alimentos_TipoAlimentos]
GO
ALTER TABLE [dbo].[Carritos]  WITH CHECK ADD  CONSTRAINT [FK_Carritos_Clientes] FOREIGN KEY([IdCliente])
REFERENCES [dbo].[Clientes] ([Id])
GO
ALTER TABLE [dbo].[Carritos] CHECK CONSTRAINT [FK_Carritos_Clientes]
GO
ALTER TABLE [dbo].[Clientes]  WITH CHECK ADD  CONSTRAINT [FK_Clientes_Usuarios] FOREIGN KEY([Id])
REFERENCES [dbo].[Usuarios] ([Id])
GO
ALTER TABLE [dbo].[Clientes] CHECK CONSTRAINT [FK_Clientes_Usuarios]
GO
ALTER TABLE [dbo].[Facturas]  WITH CHECK ADD  CONSTRAINT [FK_Facturas_Clientes] FOREIGN KEY([IdCliente])
REFERENCES [dbo].[Clientes] ([Id])
GO
ALTER TABLE [dbo].[Facturas] CHECK CONSTRAINT [FK_Facturas_Clientes]
GO
ALTER TABLE [dbo].[FacturasLineas]  WITH CHECK ADD  CONSTRAINT [FK_FacturasLineas_Facturas] FOREIGN KEY([IdFactura])
REFERENCES [dbo].[Facturas] ([Id])
GO
ALTER TABLE [dbo].[FacturasLineas] CHECK CONSTRAINT [FK_FacturasLineas_Facturas]
GO
ALTER TABLE [dbo].[Funciones]  WITH CHECK ADD  CONSTRAINT [FK_Funciones_Pelicula] FOREIGN KEY([IdPelicula])
REFERENCES [dbo].[Pelicula] ([Id])
GO
ALTER TABLE [dbo].[Funciones] CHECK CONSTRAINT [FK_Funciones_Pelicula]
GO
ALTER TABLE [dbo].[Funciones]  WITH CHECK ADD  CONSTRAINT [FK_Funciones_Salas] FOREIGN KEY([IdSala])
REFERENCES [dbo].[Salas] ([Id])
GO
ALTER TABLE [dbo].[Funciones] CHECK CONSTRAINT [FK_Funciones_Salas]
GO
ALTER TABLE [dbo].[Tarjetas]  WITH CHECK ADD  CONSTRAINT [FK_Tarjetas_Clientes] FOREIGN KEY([IdCliente])
REFERENCES [dbo].[Clientes] ([Id])
GO
ALTER TABLE [dbo].[Tarjetas] CHECK CONSTRAINT [FK_Tarjetas_Clientes]
GO
ALTER TABLE [dbo].[Usuarios]  WITH CHECK ADD  CONSTRAINT [FK_Usuarios_TipoUsuarios] FOREIGN KEY([IdTipoUsuarios])
REFERENCES [dbo].[TipoUsuarios] ([Id])
GO
ALTER TABLE [dbo].[Usuarios] CHECK CONSTRAINT [FK_Usuarios_TipoUsuarios]
GO
/****** Object:  StoredProcedure [dbo].[sp_ConsultarAlimento]    Script Date: 07/03/2022 8:09:34 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO




CREATE PROCEDURE [dbo].[sp_ConsultarAlimento]
	@inId INT
AS
BEGIN
	DECLARE @OutResultCode INT = 0;

	BEGIN TRY
		IF EXISTS (SELECT Id FROM dbo.Alimentos WHERE Id = @inId)
		BEGIN
			
			SELECT
				A.Id AS Id,
				A.Nombre AS Nombre,
				A.Cantidad AS Cantidad,
				TP.Nombre AS TipoAlimento,
				A.Precio AS Precio,
				A.Imagen AS Imagen
			FROM dbo.Alimentos A INNER JOIN dbo.TipoAlimentos TP ON TP.Id = A.IdTipoAlimentos
			WHERE A.Id = @inId

		END
		ELSE
		BEGIN
			SET @OutResultCode = 1
		END

	END TRY
	BEGIN CATCH
		SET @OutResultCode = 505;
	END CATCH

	SELECT @OutResultCode AS Codigo;
END
GO
/****** Object:  StoredProcedure [dbo].[sp_ConsultarAlimentos]    Script Date: 07/03/2022 8:09:34 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO




CREATE PROCEDURE [dbo].[sp_ConsultarAlimentos]
AS
BEGIN
	DECLARE @OutResultCode INT = 0;

	BEGIN TRY
			SELECT
				A.Id AS Id,
				A.Nombre AS Nombre,
				A.Cantidad AS Cantidad,
				TP.Nombre AS TipoAlimento,
				A.Precio AS Precio,
				A.Imagen AS Imagen
			FROM dbo.Alimentos A INNER JOIN dbo.TipoAlimentos TP ON TP.Id = A.IdTipoAlimentos

	END TRY
	BEGIN CATCH
		SET @OutResultCode = 505;
	END CATCH

	SELECT @OutResultCode AS Codigo;
END
GO
/****** Object:  StoredProcedure [dbo].[sp_ConsultarCliente]    Script Date: 07/03/2022 8:09:34 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO




CREATE PROCEDURE [dbo].[sp_ConsultarCliente]
	@inIdentificacion INT
AS
BEGIN
	DECLARE @OutResultCode INT = 0;

	BEGIN TRY
		IF EXISTS (SELECT Id FROM dbo.Clientes WHERE Identificacion = @inIdentificacion)
		BEGIN

			SELECT
				U.Id AS Id,
				C.Identificacion AS Identificacion,
				U.Nombre AS Nombre,
				U.Apellido1 AS Apellido1,
				U.Apellido2 AS Apellido2,
				C.FechaNacimiento AS FechaNacimiento,
				C.Edad AS Edad,
				C.EsquemaVacunacion AS EsquemaVacunacion,
				U.CorreoElectronico AS CorreoElectronico,
				U.Contraseña AS Contra
			FROM dbo.Clientes C INNER JOIN dbo.Usuarios U ON U.Id = C.Id
			WHERE C.Identificacion = @inIdentificacion

		END
		ELSE
		BEGIN
			SET @OutResultCode = 1
		END


	END TRY
	BEGIN CATCH
		SET @OutResultCode = 505;
	END CATCH

	SELECT @OutResultCode AS Codigo;
END
GO
/****** Object:  StoredProcedure [dbo].[sp_ConsultarClientes]    Script Date: 07/03/2022 8:09:34 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO




CREATE PROCEDURE [dbo].[sp_ConsultarClientes]
AS
BEGIN
	DECLARE @OutResultCode INT = 0;

	BEGIN TRY

			SELECT
				U.Id AS Id,
				C.Identificacion AS Identificacion,
				U.Nombre AS Nombre,
				U.Apellido1 AS Apellido1,
				U.Apellido2 AS Apellido2,
				C.FechaNacimiento AS FechaNacimiento,
				C.Edad AS Edad,
				C.EsquemaVacunacion AS EsquemaVacunacion,
				U.CorreoElectronico AS CorreoElectronico,
				U.Contraseña AS Contra
			FROM dbo.Clientes C INNER JOIN dbo.Usuarios U ON U.Id = C.Id

	END TRY
	BEGIN CATCH
		SET @OutResultCode = 505;
	END CATCH

	SELECT @OutResultCode AS Codigo;
END
GO
/****** Object:  StoredProcedure [dbo].[sp_ConsultarFuncion]    Script Date: 07/03/2022 8:09:34 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[sp_ConsultarFuncion]
	@inId INT,
	@inFecha DATE
AS
BEGIN
	DECLARE @OutResultCode INT = 0;

	BEGIN TRY
		IF EXISTS (SELECT Id FROM dbo.Funciones WHERE Id = @inId)
		BEGIN

			SELECT
				F.Id AS Id,
				S.NumeroSala AS NumeroSala,
				F.Hora AS Hora,
				F.AsientosDisponibles AS AsientosDisponibles,
				P.Titulo AS Titulo,
				P.Idiomas AS Idiomas,
				P.EdadRequerida AS EdadRequerida
			FROM dbo.Funciones F INNER JOIN dbo.Pelicula P ON P.Id = F.IdPelicula 
			INNER JOIN dbo.Salas S ON S.Id = F.IdSala
			WHERE F.Id = @inId AND F.Fecha = @inFecha


		END
		ELSE
		BEGIN
			SET @OutResultCode = 1
		END



	END TRY
	BEGIN CATCH
		SET @OutResultCode = 505;
	END CATCH

	SELECT @OutResultCode AS Codigo;
END
GO
/****** Object:  StoredProcedure [dbo].[sp_ConsultarFunciones]    Script Date: 07/03/2022 8:09:34 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[sp_ConsultarFunciones]
	@inFecha DATE
AS
BEGIN
	DECLARE @OutResultCode INT = 0;

	BEGIN TRY

		SELECT
			F.Id AS Id,
			S.NumeroSala AS NumeroSala,
			F.Hora AS Hora,
			F.AsientosDisponibles AS AsientosDisponibles,
			P.Titulo AS Titulo,
			P.Idiomas AS Idiomas,
			P.EdadRequerida AS EdadRequerida
		FROM dbo.Funciones F INNER JOIN dbo.Pelicula P ON P.Id = F.IdPelicula 
		INNER JOIN dbo.Salas S ON S.Id = F.IdSala
		WHERE F.Fecha = @inFecha

	END TRY
	BEGIN CATCH
		SET @OutResultCode = 505;
	END CATCH

	SELECT @OutResultCode AS Codigo;
END
GO
/****** Object:  StoredProcedure [dbo].[sp_ConsultarPelicula]    Script Date: 07/03/2022 8:09:34 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[sp_ConsultarPelicula]
	@inId INT
AS
BEGIN
	DECLARE @OutResultCode INT = 0;

	BEGIN TRY
		IF EXISTS (SELECT Id FROM dbo.Pelicula WHERE Id = @inId)
		BEGIN

			SELECT
				Titulo AS Titulo,
				Director AS Director,
				Actores AS Actores,
				Generos AS Generos,
				Idiomas AS Idiomas,
				AñoPublicacion AS Publicacion,
				Duracion AS Duracion,
				EdadRequerida AS EdadRequeridad,
				Imagen AS Imagen
			FROM dbo.Pelicula WHERE Id = @inId

		END
		ELSE
		BEGIN
			SET @OutResultCode = 1
		END


	END TRY
	BEGIN CATCH
		SET @OutResultCode = 505;
	END CATCH

	SELECT @OutResultCode AS Codigo;
END
GO
/****** Object:  StoredProcedure [dbo].[sp_ConsultarPeliculas]    Script Date: 07/03/2022 8:09:34 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[sp_ConsultarPeliculas]
AS
BEGIN
	DECLARE @OutResultCode INT = 0;

	BEGIN TRY

			SELECT
				Titulo AS Titulo,
				Director AS Director,
				Actores AS Actores,
				Generos AS Generos,
				Idiomas AS Idiomas,
				AñoPublicacion AS Publicacion,
				Duracion AS Duracion,
				EdadRequerida AS EdadRequeridad,
				Imagen AS Imagen
			FROM dbo.Pelicula 

	END TRY
	BEGIN CATCH
		SET @OutResultCode = 505;
	END CATCH

	SELECT @OutResultCode AS Codigo;
END
GO
/****** Object:  StoredProcedure [dbo].[sp_EliminarAlimento]    Script Date: 07/03/2022 8:09:34 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO




CREATE PROCEDURE [dbo].[sp_EliminarAlimento]
	@inId INT
AS
BEGIN
	DECLARE @OutResultCode INT = 0;

	BEGIN TRY
		IF EXISTS (SELECT Id FROM dbo.Alimentos WHERE Id = @inId)
		BEGIN
			
			DELETE FROM dbo.Alimentos
			WHERE Id = @inId

		END
		ELSE
		BEGIN
			SET @OutResultCode = 1
		END

	END TRY
	BEGIN CATCH
		SET @OutResultCode = 505;
	END CATCH

	SELECT @OutResultCode AS Codigo;
END
GO
/****** Object:  StoredProcedure [dbo].[sp_EliminarCliente]    Script Date: 07/03/2022 8:09:34 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

CREATE PROCEDURE [dbo].[sp_EliminarCliente]
	@inIdentificacion INT
AS
BEGIN
	DECLARE @OutResultCode INT = 0;
	DECLARE @idUsuario INT;

	BEGIN TRY
		IF NOT EXISTS (SELECT Id FROM dbo.Clientes WHERE Identificacion = @inIdentificacion)
		BEGIN
			SET @idUsuario = (SELECT Id FROM dbo.Clientes WHERE Identificacion = @inIdentificacion);

			-- Eliminacion de Usuario
			DELETE FROM dbo.Usuarios
			WHERE Id = @idUsuario

			-- Eliminacion del cliente
			DELETE FROM dbo.Clientes
			WHERE Id = @idUsuario

		END
		ELSE
		BEGIN
			SET @OutResultCode = 1
		END


	END TRY
	BEGIN CATCH
		SET @OutResultCode = 505;
	END CATCH

	SELECT @OutResultCode AS Codigo;
END
GO
/****** Object:  StoredProcedure [dbo].[sp_EliminarFuncion]    Script Date: 07/03/2022 8:09:34 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[sp_EliminarFuncion]
	@inId INT
AS
BEGIN
	DECLARE @OutResultCode INT = 0;

	BEGIN TRY
		IF EXISTS (SELECT Id FROM dbo.Funciones WHERE Id = @inId)
		BEGIN

			DELETE FROM dbo.Funciones
			WHERE Id = @inId

		END
		ELSE
		BEGIN
			SET @OutResultCode = 1
		END

	END TRY
	BEGIN CATCH
		SET @OutResultCode = 505;
	END CATCH

	SELECT @OutResultCode AS Codigo;
END
GO
/****** Object:  StoredProcedure [dbo].[sp_EliminarPelicula]    Script Date: 07/03/2022 8:09:34 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[sp_EliminarPelicula]
	@inId INT
AS
BEGIN
	DECLARE @OutResultCode INT = 0;

	BEGIN TRY
		IF EXISTS (SELECT Id FROM dbo.Pelicula WHERE Id = @inId)
		BEGIN

			DELETE FROM dbo.Pelicula
			WHERE Id = @inId

		END
		ELSE
		BEGIN
			SET @OutResultCode = 1
		END


	END TRY
	BEGIN CATCH
		SET @OutResultCode = 505;
	END CATCH

	SELECT @OutResultCode AS Codigo;
END
GO
/****** Object:  StoredProcedure [dbo].[sp_InsertarAdministrador]    Script Date: 07/03/2022 8:09:34 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO




CREATE PROCEDURE [dbo].[sp_InsertarAdministrador]
	@inNombre VARCHAR(64),
	@inApellido1 VARCHAR(64),
	@inApellido2 VARCHAR(64),
	@inCorreo VARCHAR(64),
	@inContraseña VARCHAR(64),
	@inTipoUsuarios INT
AS
BEGIN
	DECLARE @OutResultCode INT = 0;

	BEGIN TRY
		IF NOT EXISTS (SELECT Id FROM dbo.Usuarios WHERE CorreoElectronico = @inCorreo AND Contraseña = @inContraseña)
		BEGIN

			INSERT INTO dbo.Usuarios(
				Nombre,
				Apellido1,
				Apellido2,
				CorreoElectronico,
				Contraseña,
				IdTipoUsuarios
			)VALUES(
				@inNombre,
				@inApellido1,
				@inApellido2,
				@inCorreo,
				@inContraseña,
				@inTipoUsuarios
			)

		END
		ELSE
		BEGIN
			SET @OutResultCode = 1
		END


	END TRY
	BEGIN CATCH
		SET @OutResultCode = 505;
	END CATCH

	SELECT @OutResultCode AS Codigo;
END
GO
/****** Object:  StoredProcedure [dbo].[sp_InsertarAlimento]    Script Date: 07/03/2022 8:09:34 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO




CREATE PROCEDURE [dbo].[sp_InsertarAlimento]
	@inNombre VARCHAR(64),
	@inCantidad INT,
	@inTipoAlimentos INT,
	@inPrecio MONEY,
	@inImagen VARBINARY(MAX)
AS
BEGIN
	DECLARE @OutResultCode INT = 0;

	BEGIN TRY
		
		INSERT INTO dbo.Alimentos(
			Nombre,
			Cantidad,
			IdTipoAlimentos,
			Precio,
			Imagen
		)VALUES(
			@inNombre,
			@inCantidad,
			@inTipoAlimentos,
			@inPrecio,
			@inImagen
		)


	END TRY
	BEGIN CATCH
		SET @OutResultCode = 505;
	END CATCH

	SELECT @OutResultCode AS Codigo;
END
GO
/****** Object:  StoredProcedure [dbo].[sp_InsertarCliente]    Script Date: 07/03/2022 8:09:34 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

CREATE PROCEDURE [dbo].[sp_InsertarCliente]
	@inIdentificacion INT,
	@inNombre VARCHAR(64),
	@inApellido1 VARCHAR(64),
	@inApellido2 VARCHAR(64),
	@inCorreo VARCHAR(64),
	@inContraseña VARCHAR(64),
	@inFechaNacimiento DATE,
	@inEdad INT,
	@inTipoUsuarios INT,
	@inEsquemaVacunacion VARCHAR(64)
AS
BEGIN
	DECLARE @OutResultCode INT = 0;
	DECLARE @idUsuario INT;

	BEGIN TRY
		IF NOT EXISTS (SELECT Id FROM dbo.Clientes WHERE Identificacion = @inIdentificacion) AND
		NOT EXISTS (SELECT Id FROM dbo.Usuarios WHERE CorreoElectronico = @inCorreo AND Contraseña = @inContraseña)
		BEGIN
			INSERT INTO dbo.Usuarios(
				Nombre,
				Apellido1,
				Apellido2,
				CorreoElectronico,
				Contraseña,
				IdTipoUsuarios
			)VALUES(
				@inNombre,
				@inApellido1,
				@inApellido2,
				@inCorreo,
				@inContraseña,
				@inTipoUsuarios
			)

			SET @idUsuario =(SELECT MAX(Id) FROM dbo.Usuarios);

			INSERT INTO dbo.Clientes(
				Id,
				Identificacion,
				Edad,
				FechaNacimiento,
				EsquemaVacunacion
			)VALUES(
				@idUsuario,
				@inIdentificacion,
				@inEdad,
				@inFechaNacimiento,
				@inEsquemaVacunacion
			)
		END
		ELSE
		BEGIN
			SET @OutResultCode = 1
		END


	END TRY
	BEGIN CATCH
		SET @OutResultCode = 505;
	END CATCH

	SELECT @OutResultCode AS Codigo;
END
GO
/****** Object:  StoredProcedure [dbo].[sp_InsertarFuncion]    Script Date: 07/03/2022 8:09:34 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[sp_InsertarFuncion]
	@inIdSala INT,
	@inIdPelicula INT,
	@inFecha DATE,
	@inHora TIME(0),
	@AsientosDisponibles NVARCHAR(MAX)
AS
BEGIN
	DECLARE @OutResultCode INT = 0;

	BEGIN TRY
		IF EXISTS (SELECT Id FROM dbo.Salas WHERE Id = @inIdSala) AND
		EXISTS (SELECT Id FROM dbo.Pelicula WHERE Id = @inIdPelicula)
		BEGIN

			INSERT INTO dbo.Funciones(
				IdSala,
				IdPelicula,
				Fecha,
				Hora,
				AsientosDisponibles
			)VALUES(
				@inIdSala,
				@inIdPelicula,
				@inFecha,
				@inHora,
				@AsientosDisponibles
			)

		END
		ELSE
		BEGIN
			SET @OutResultCode = 1
		END

	END TRY
	BEGIN CATCH
		SET @OutResultCode = 505;
	END CATCH

	SELECT @OutResultCode AS Codigo;
END
GO
/****** Object:  StoredProcedure [dbo].[sp_InsertarPelicula]    Script Date: 07/03/2022 8:09:34 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[sp_InsertarPelicula]
	@inTitulo VARCHAR(64),
	@inDirector VARCHAR(64),
	@inActores NVARCHAR(MAX),
	@inGeneros NVARCHAR(MAX),
	@inIdiomas NVARCHAR(MAX),
	@inPublicacion INT,
	@inDuracion INT,
	@inEdadRequeridad INT,
	@inImagen VARBINARY(MAX)
AS
BEGIN
	DECLARE @OutResultCode INT = 0;

	BEGIN TRY
		
		INSERT INTO dbo.Pelicula(
			Titulo,
			Director,
			Actores,
			Generos,
			Idiomas,
			AñoPublicacion,
			Duracion,
			EdadRequerida,
			Imagen
		)VALUES(
			@inTitulo,
			@inDirector,
			@inActores,
			@inGeneros,
			@inIdiomas,
			@inPublicacion,
			@inDuracion,
			@inEdadRequeridad,
			@inImagen
		)


	END TRY
	BEGIN CATCH
		SET @OutResultCode = 505;
	END CATCH

	SELECT @OutResultCode AS Codigo;
END
GO
/****** Object:  StoredProcedure [dbo].[sp_ModificarAlimento]    Script Date: 07/03/2022 8:09:34 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO




CREATE PROCEDURE [dbo].[sp_ModificarAlimento]
	@inId INT,
	@inNombre VARCHAR(64),
	@inCantidad INT,
	@inTipoAlimentos INT,
	@inPrecio MONEY,
	@inImagen VARBINARY(MAX)
AS
BEGIN
	DECLARE @OutResultCode INT = 0;
	DECLARE @idAlimento INT;

	BEGIN TRY
		IF EXISTS (SELECT Id FROM dbo.Alimentos WHERE Id = @inId)
		BEGIN
			SET @idAlimento = (SELECT Id FROM dbo.Alimentos WHERE Id = @inId);

			UPDATE dbo.Alimentos
			SET
				Nombre = @inNombre,
				Cantidad = @inCantidad,
				IdTipoAlimentos = @inTipoAlimentos,
				Precio = @inPrecio,
				Imagen = @inImagen
			WHERE Id = @idAlimento

		END
		ELSE
		BEGIN
			SET @OutResultCode = 1
		END

	END TRY
	BEGIN CATCH
		SET @OutResultCode = 505;
	END CATCH

	SELECT @OutResultCode AS Codigo;
END
GO
/****** Object:  StoredProcedure [dbo].[sp_ModificarCliente]    Script Date: 07/03/2022 8:09:34 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

CREATE PROCEDURE [dbo].[sp_ModificarCliente]
	@inIdentificacion INT,
	@inNombre VARCHAR(64),
	@inApellido1 VARCHAR(64),
	@inApellido2 VARCHAR(64),
	@inCorreo VARCHAR(64),
	@inContraseña VARCHAR(64),
	@inFechaNacimiento DATE,
	@inEdad INT,
	@inEsquemaVacunacion VARCHAR(64)
AS
BEGIN
	DECLARE @OutResultCode INT = 0;
	DECLARE @idUsuario INT;

	BEGIN TRY
		IF EXISTS (SELECT Id FROM dbo.Clientes WHERE Identificacion = @inIdentificacion) AND
		NOT EXISTS (SELECT Id FROM dbo.Usuarios WHERE CorreoElectronico = @inCorreo AND Contraseña = @inContraseña)
		BEGIN
			SET @idUsuario = (SELECT Id FROM dbo.Clientes WHERE Identificacion = @inIdentificacion);

			-- Modificacion de Usuario
			UPDATE dbo.Usuarios
			SET
				Nombre = @inNombre,
				Apellido1 = @inApellido1,
				Apellido2 = @inApellido2,
				CorreoElectronico = @inCorreo,
				Contraseña = @inContraseña
			WHERE Id = @idUsuario

			-- Modificacion del cliente
			UPDATE dbo.Clientes
			SET
				Edad = @inEdad,
				FechaNacimiento = @inFechaNacimiento,
				EsquemaVacunacion = @inEsquemaVacunacion
			WHERE Id = @idUsuario

		END
		ELSE
		BEGIN
			SET @OutResultCode = 1
		END


	END TRY
	BEGIN CATCH
		SET @OutResultCode = 505;
	END CATCH

	SELECT @OutResultCode AS Codigo;
END
GO
/****** Object:  StoredProcedure [dbo].[sp_ModificarFuncion]    Script Date: 07/03/2022 8:09:34 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[sp_ModificarFuncion]
	@inId INT,
	@inIdSala INT,
	@inIdPelicula INT,
	@inFecha DATE,
	@inHora TIME(0),
	@AsientosDisponibles NVARCHAR(MAX)
AS
BEGIN
	DECLARE @OutResultCode INT = 0;

	BEGIN TRY
		IF EXISTS (SELECT Id FROM dbo.Funciones WHERE Id = @inId)
		BEGIN

			UPDATE dbo.Funciones
			SET
				IdSala = @inIdSala,
				IdPelicula = @inIdPelicula,
				Fecha = @inFecha,
				Hora = @inHora,
				AsientosDisponibles = @AsientosDisponibles
			WHERE Id = @inId

		END
		ELSE
		BEGIN
			SET @OutResultCode = 1
		END

	END TRY
	BEGIN CATCH
		SET @OutResultCode = 505;
	END CATCH

	SELECT @OutResultCode AS Codigo;
END
GO
/****** Object:  StoredProcedure [dbo].[sp_ModificarPelicula]    Script Date: 07/03/2022 8:09:34 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[sp_ModificarPelicula]
	@inId INT,
	@inTitulo VARCHAR(64),
	@inDirector VARCHAR(64),
	@inActores NVARCHAR(MAX),
	@inGeneros NVARCHAR(MAX),
	@inIdiomas NVARCHAR(MAX),
	@inPublicacion INT,
	@inDuracion INT,
	@inEdadRequeridad INT,
	@inImagen VARBINARY(MAX)
AS
BEGIN
	DECLARE @OutResultCode INT = 0;
	DECLARE @idPelicula INT;

	BEGIN TRY
		IF EXISTS (SELECT Id FROM dbo.Pelicula WHERE Id = @inId)
		BEGIN
			SET @idPelicula = (SELECT Id FROM dbo.Pelicula WHERE Id = @inId);


			UPDATE dbo.Pelicula
			SET
				Titulo = @inTitulo,
				Director = @inDirector,
				Actores = @inActores,
				Generos = @inGeneros,
				Idiomas = @inIdiomas,
				AñoPublicacion = @inPublicacion,
				Duracion = @inDuracion,
				EdadRequerida = @inEdadRequeridad,
				Imagen = @inImagen
			WHERE Id = @idPelicula

		END
		ELSE
		BEGIN
			SET @OutResultCode = 1
		END


	END TRY
	BEGIN CATCH
		SET @OutResultCode = 505;
	END CATCH

	SELECT @OutResultCode AS Codigo;
END
GO
USE [master]
GO
ALTER DATABASE [CinepolisBD] SET  READ_WRITE 
GO
