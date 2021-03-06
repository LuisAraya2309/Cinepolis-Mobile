USE [CinepolisBD]
GO
/****** Object:  StoredProcedure [dbo].[sp_ModificarCliente]    Script Date: 17/03/2022 09:24:07 a. m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

ALTER PROCEDURE [dbo].[sp_ModificarCliente]
	@inIdentificacion INT,
	@inNombre VARCHAR(64),
	@inApellido1 VARCHAR(64),
	@inApellido2 VARCHAR(64),
	@inCorreo VARCHAR(64),
	@inContraseña VARCHAR(64),
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
