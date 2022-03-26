
USE [CinepolisBD]
GO

SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO


CREATE PROCEDURE sp_ActualizarCarrito
	@inIdUsuario INT,
	@inProductos VARCHAR(MAX)
AS
BEGIN
	DECLARE @OutResultCode INT = 0;

	BEGIN TRY
		IF EXISTS (SELECT * FROM dbo.Carritos WHERE IdCliente = @inIdUsuario)
		BEGIN

			UPDATE dbo.Carritos
			SET
				Productos = @inProductos
			WHERE IdCliente = @inIdUsuario

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
