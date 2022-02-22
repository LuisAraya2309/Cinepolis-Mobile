USE [master]
GO
/****** Object:  Database [CinepolisBD]    Script Date: 21/02/2022 8:50:07 ******/
CREATE DATABASE [CinepolisBD]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'CinepolisBD', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL15.SQLEXPRESS\MSSQL\DATA\CinepolisBD.mdf' , SIZE = 8192KB , MAXSIZE = UNLIMITED, FILEGROWTH = 65536KB )
 LOG ON 
( NAME = N'CinepolisBD_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL15.SQLEXPRESS\MSSQL\DATA\CinepolisBD_log.ldf' , SIZE = 8192KB , MAXSIZE = 2048GB , FILEGROWTH = 65536KB )
 WITH CATALOG_COLLATION = DATABASE_DEFAULT
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
/****** Object:  Table [dbo].[Actores]    Script Date: 21/02/2022 8:50:07 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Actores](
	[Id] [int] IDENTITY(1,1) NOT NULL,
	[Nombre] [varchar](64) NOT NULL,
	[Apellido1] [varchar](64) NOT NULL,
	[Apellido2] [varchar](64) NOT NULL,
 CONSTRAINT [PK_Actores] PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Alimentos]    Script Date: 21/02/2022 8:50:07 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Alimentos](
	[Id] [int] IDENTITY(1,1) NOT NULL,
	[Nombre] [varchar](64) NOT NULL,
	[Cantidad] [varchar](64) NOT NULL,
	[IdTipoAlimentos] [int] NOT NULL,
	[Precio] [money] NOT NULL,
 CONSTRAINT [PK_Alimentos] PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Carritos]    Script Date: 21/02/2022 8:50:07 ******/
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
/****** Object:  Table [dbo].[Clientes]    Script Date: 21/02/2022 8:50:07 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Clientes](
	[Id] [int] IDENTITY(1,1) NOT NULL,
	[Identificacion] [int] NOT NULL,
	[Edad] [int] NOT NULL,
	[FechaNacimiento] [date] NOT NULL,
	[EsquemaVacunacion] [int] NOT NULL,
 CONSTRAINT [PK_Clientes] PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Facturas]    Script Date: 21/02/2022 8:50:07 ******/
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
/****** Object:  Table [dbo].[FacturasLineas]    Script Date: 21/02/2022 8:50:07 ******/
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
/****** Object:  Table [dbo].[Funciones]    Script Date: 21/02/2022 8:50:07 ******/
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
/****** Object:  Table [dbo].[Generos]    Script Date: 21/02/2022 8:50:07 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Generos](
	[Id] [int] IDENTITY(1,1) NOT NULL,
	[Nombre] [varchar](64) NOT NULL,
 CONSTRAINT [PK_Generos] PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Idiomas]    Script Date: 21/02/2022 8:50:07 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Idiomas](
	[Id] [int] IDENTITY(1,1) NOT NULL,
	[Nombre] [varchar](64) NOT NULL,
 CONSTRAINT [PK_Idiomas] PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Pelicula]    Script Date: 21/02/2022 8:50:07 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Pelicula](
	[Id] [int] IDENTITY(1,1) NOT NULL,
	[Titulo] [varchar](64) NOT NULL,
	[Director] [varchar](64) NOT NULL,
	[IdActores] [int] NOT NULL,
	[IdGeneros] [int] NOT NULL,
	[IdIdiomas] [int] NOT NULL,
	[AñoPublicacion] [int] NOT NULL,
	[Duracion] [int] NOT NULL,
	[EdadRequerida] [int] NOT NULL,
 CONSTRAINT [PK_Pelicula] PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[PeliculasXActores]    Script Date: 21/02/2022 8:50:07 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[PeliculasXActores](
	[Id] [int] IDENTITY(1,1) NOT NULL,
	[IdPelicula] [int] NOT NULL,
	[IdActor] [int] NOT NULL,
 CONSTRAINT [PK_PeliculasXActores] PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[PeliculasXGeneros]    Script Date: 21/02/2022 8:50:07 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[PeliculasXGeneros](
	[Id] [int] IDENTITY(1,1) NOT NULL,
	[IdPelicula] [int] NOT NULL,
	[IdGenero] [int] NOT NULL,
 CONSTRAINT [PK_PeliculasXGeneros] PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[PeliculasXIdiomas]    Script Date: 21/02/2022 8:50:07 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[PeliculasXIdiomas](
	[Id] [int] IDENTITY(1,1) NOT NULL,
	[IdPelicula] [int] NOT NULL,
	[IdIdioma] [int] NOT NULL,
 CONSTRAINT [PK_PeliculasXIdiomas] PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[PrecioEntrada]    Script Date: 21/02/2022 8:50:07 ******/
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
/****** Object:  Table [dbo].[Salas]    Script Date: 21/02/2022 8:50:07 ******/
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
/****** Object:  Table [dbo].[TipoAlimentos]    Script Date: 21/02/2022 8:50:07 ******/
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
/****** Object:  Table [dbo].[TipoUsuarios]    Script Date: 21/02/2022 8:50:07 ******/
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
/****** Object:  Table [dbo].[Usuarios]    Script Date: 21/02/2022 8:50:07 ******/
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
ALTER TABLE [dbo].[PeliculasXActores]  WITH CHECK ADD  CONSTRAINT [FK_PeliculasXActores_Actores] FOREIGN KEY([IdActor])
REFERENCES [dbo].[Actores] ([Id])
GO
ALTER TABLE [dbo].[PeliculasXActores] CHECK CONSTRAINT [FK_PeliculasXActores_Actores]
GO
ALTER TABLE [dbo].[PeliculasXActores]  WITH CHECK ADD  CONSTRAINT [FK_PeliculasXActores_Pelicula] FOREIGN KEY([IdPelicula])
REFERENCES [dbo].[Pelicula] ([Id])
GO
ALTER TABLE [dbo].[PeliculasXActores] CHECK CONSTRAINT [FK_PeliculasXActores_Pelicula]
GO
ALTER TABLE [dbo].[PeliculasXGeneros]  WITH CHECK ADD  CONSTRAINT [FK_PeliculasXGeneros_Generos] FOREIGN KEY([IdGenero])
REFERENCES [dbo].[Generos] ([Id])
GO
ALTER TABLE [dbo].[PeliculasXGeneros] CHECK CONSTRAINT [FK_PeliculasXGeneros_Generos]
GO
ALTER TABLE [dbo].[PeliculasXGeneros]  WITH CHECK ADD  CONSTRAINT [FK_PeliculasXGeneros_Pelicula] FOREIGN KEY([IdPelicula])
REFERENCES [dbo].[Pelicula] ([Id])
GO
ALTER TABLE [dbo].[PeliculasXGeneros] CHECK CONSTRAINT [FK_PeliculasXGeneros_Pelicula]
GO
ALTER TABLE [dbo].[PeliculasXIdiomas]  WITH CHECK ADD  CONSTRAINT [FK_PeliculasXIdiomas_Idiomas] FOREIGN KEY([IdIdioma])
REFERENCES [dbo].[Idiomas] ([Id])
GO
ALTER TABLE [dbo].[PeliculasXIdiomas] CHECK CONSTRAINT [FK_PeliculasXIdiomas_Idiomas]
GO
ALTER TABLE [dbo].[PeliculasXIdiomas]  WITH CHECK ADD  CONSTRAINT [FK_PeliculasXIdiomas_Pelicula] FOREIGN KEY([IdPelicula])
REFERENCES [dbo].[Pelicula] ([Id])
GO
ALTER TABLE [dbo].[PeliculasXIdiomas] CHECK CONSTRAINT [FK_PeliculasXIdiomas_Pelicula]
GO
ALTER TABLE [dbo].[Usuarios]  WITH CHECK ADD  CONSTRAINT [FK_Usuarios_TipoUsuarios] FOREIGN KEY([IdTipoUsuarios])
REFERENCES [dbo].[TipoUsuarios] ([Id])
GO
ALTER TABLE [dbo].[Usuarios] CHECK CONSTRAINT [FK_Usuarios_TipoUsuarios]
GO
USE [master]
GO
ALTER DATABASE [CinepolisBD] SET  READ_WRITE 
GO
