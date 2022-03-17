USE [CinepolisBD]
GO

SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

CREATE PROCEDURE sp_AgregarTarjeta
	@inTitular VARCHAR(64)
	, @inNumeroTarjeta VARCHAR(64)
	, @inCodigoSeguridad VARCHAR(64)
	, @inFechaVencimiento DATE
	, @inIdCliente INT
	, @OutResultCode INT OUTPUT

AS
BEGIN
	SET NOCOUNT ON;

		SELECT
			@OutResultCode=0 ;

		IF NOT EXISTS (SELECT * FROM dbo.Tarjetas AS T WHERE T.IdCliente = @inIdCliente AND T.NumeroTarjeta = @inNumeroTarjeta)
			BEGIN
					
				BEGIN TRANSACTION TSaveMov
					INSERT INTO dbo.Tarjetas
						(
							IdCliente,
							Titular,
							NumeroTarjeta,
							FechaVencimiento,
							NumeroSeguridad
				
						)
						VALUES
						(
							@inIdCliente,
							@inTitular,
							@inNumeroTarjeta,
							@inFechaVencimiento,
							@inCodigoSeguridad
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
