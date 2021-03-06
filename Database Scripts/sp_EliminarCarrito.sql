USE [CinepolisBD]
GO

SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO


CREATE PROCEDURE sp_EliminarCarrito
	@inIdCliente INT
AS
BEGIN
	DECLARE @OutResultCode INT = 0;

	BEGIN TRY
		IF EXISTS (SELECT * FROM dbo.Carritos WHERE IdCliente = @inIdCliente)
			BEGIN
				
				DELETE FROM dbo.Carritos
				WHERE IdCliente = @inIdCliente

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
