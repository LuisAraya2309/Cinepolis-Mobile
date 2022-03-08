USE [CinepolisBD]

SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

CREATE PROCEDURE sp_CrearCuenta
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
		BEGIN TRY
			SELECT
				@OutResultCode=0 ;

			DECLARE @edad INT = (Select floor(
									(cast(convert(varchar(8),getdate(),112) as int)-
									cast(convert(varchar(8),@inFechaNacimiento,112) as int) ) / 10000
								));

			IF NOT EXISTS (SELECT C.Identificacion FROM dbo.Clientes AS C WHERE C.Id = @inIdentificacion)
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
								SCOPE_IDENTITY(),
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

		END TRY
		BEGIN CATCH

				IF @@Trancount>0 
					ROLLBACK TRANSACTION TSaveMov;

				Set @OutResultCode=50005;
				
		END CATCH;

		SET NOCOUNT OFF;

END
GO
