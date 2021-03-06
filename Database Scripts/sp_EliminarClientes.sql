USE [CinepolisBD]
GO
/****** Object:  StoredProcedure [dbo].[sp_EliminarCliente]    Script Date: 25/03/2022 09:09:36 a. m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

ALTER PROCEDURE [dbo].[sp_EliminarCliente]
	@inIdentificacion INT
AS
BEGIN
	DECLARE @OutResultCode INT = 0;
	DECLARE @idUsuario INT;

	BEGIN TRY
		IF EXISTS (SELECT * FROM dbo.Clientes WHERE Identificacion = @inIdentificacion)
		BEGIN
			SET @idUsuario = (SELECT Id FROM dbo.Clientes WHERE Identificacion = @inIdentificacion);

			IF EXISTS (SELECT * FROM dbo.Carritos WHERE IdCliente = @idUsuario)
				BEGIN
					DELETE FROM dbo.Carritos
					WHERE Id = @idUsuario
				END
			
			-- Eliminacion del cliente
			DELETE FROM dbo.Clientes
			WHERE Id = @idUsuario
			
			-- Eliminacion de Usuario
			DELETE FROM dbo.Usuarios
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
