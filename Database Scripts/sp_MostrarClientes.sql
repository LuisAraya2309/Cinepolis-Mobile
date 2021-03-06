USE [CinepolisBD]
GO
/****** Object:  StoredProcedure [dbo].[sp_MostrarClientes]    Script Date: 18/03/2022 05:18:20 p. m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO


CREATE PROCEDURE [dbo].[sp_MostrarClientes]
AS
BEGIN
	DECLARE @OutResultCode INT = 0;

	BEGIN TRY
			SELECT
				U.Nombre AS Nombre,
				U.Apellido1 AS Apellido1,
				U.Apellido2 AS Apellido2,
				(SELECT C.Identificacion FROM dbo.Clientes AS C WHERE C.Id = U.Id) AS Identificacion
			FROM dbo.Usuarios AS U
			WHERE IdTipoUsuarios = 2

	END TRY
	BEGIN CATCH
		SET @OutResultCode = 505;
	END CATCH

	SELECT @OutResultCode AS Codigo;
END
