USE [CinepolisBD]
GO

SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO


CREATE PROCEDURE sp_MostrarAlimentos
AS
BEGIN
	DECLARE @OutResultCode INT = 0;

	BEGIN TRY
			SELECT
				A.Nombre AS Nombre
			FROM dbo.Alimentos AS A

	END TRY
	BEGIN CATCH
		SET @OutResultCode = 505;
	END CATCH

	SELECT @OutResultCode AS Codigo;
END
