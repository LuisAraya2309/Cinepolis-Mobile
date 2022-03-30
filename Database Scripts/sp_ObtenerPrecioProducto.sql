USE [CinepolisBD]
GO

SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO


CREATE PROCEDURE sp_ObtenerPrecioProducto
	@inNombre VARCHAR(64)
AS
BEGIN
	DECLARE @OutResultCode INT = 0;

	BEGIN TRY
			SELECT
				A.Precio 
			FROM dbo.Alimentos AS A
			WHERE A.Nombre = @inNombre

	END TRY
	BEGIN CATCH
		SET @OutResultCode = 505;
	END CATCH

	SELECT @OutResultCode AS Codigo;
END
