USE [CinepolisBD]
GO
/****** Object:  StoredProcedure [dbo].[sp_CrearCuenta]    Script Date: 14/03/2022 11:07:31 a. m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

ALTER PROCEDURE [dbo].[sp_CrearCuenta]
	@inNombre VARCHAR(64)
	, @inApellido1 VARCHAR(64)
	, @inApellido2 VARCHAR(64)
	, @inCorreoElectronico VARCHAR(64)
	, @inIdentificacion INT
    , @inContraseña VARCHAR(64)
	, @inFechaNacimiento DATE
	, @inEsquemaVacunas VARCHAR(64)
	, @OutResultCode INT OUTPUT

AS
BEGIN
	SET NOCOUNT ON;

		SELECT
			@OutResultCode=0 ;

		DECLARE @edad INT = (Select floor(
								(cast(convert(varchar(8),getdate(),112) as int)-
								cast(convert(varchar(8),@inFechaNacimiento,112) as int) ) / 10000
							));

		IF NOT EXISTS (SELECT * FROM dbo.Clientes AS C WHERE C.Identificacion = @inIdentificacion)
			BEGIN
					
				BEGIN TRANSACTION TSaveMov
					INSERT INTO dbo.Usuarios
						(
							Nombre,
							CorreoElectronico,
							Contraseña,
							IdTipoUsuarios,
							Apellido1,
							Apellido2
				
						)
						VALUES
						(
							@inNombre,
							@inCorreoElectronico,
							@inContraseña,
							2,
							@inApellido1,
							@inApellido2
						)

						INSERT INTO dbo.Clientes
						(
							Id,
							Identificacion,
							Edad,
							FechaNacimiento,
							EsquemaVacunacion
				
						)
						VALUES
						(
							(SELECT MAX(id) FROM dbo.Usuarios),
							@inIdentificacion,
							@edad,
							@inFechaNacimiento,
							@inEsquemaVacunas
						)



				COMMIT TRANSACTION TSaveMov;


			END
		ELSE
			BEGIN
				SELECT
					@OutResultCode = 1 ;
			END

		SELECT @OutResultCode AS OutResultCode;

	SET NOCOUNT OFF;

END
