USE [CinepolisBD]
GO

SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO


CREATE PROCEDURE sp_MostrarClientes
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

	END TRY
	BEGIN CATCH
		SET @OutResultCode = 505;
	END CATCH

	SELECT @OutResultCode AS Codigo;
END
