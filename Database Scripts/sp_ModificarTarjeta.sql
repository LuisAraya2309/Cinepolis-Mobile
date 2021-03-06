USE [CinepolisBD]
GO
/****** Object:  StoredProcedure [dbo].[sp_ModificarAlimento]    Script Date: 15/03/2022 03:08:20 p. m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO


ALTER PROCEDURE [dbo].[sp_ModificarAlimento]
	@inNombreViejo VARCHAR(64),
	@inNombre VARCHAR(64),
	@inCantidad INT,
	@inTipoAlimentos INT,
	@inPrecio MONEY,
	@inImagen VARBINARY(MAX)
AS
BEGIN
	DECLARE @OutResultCode INT = 0;
	DECLARE @idAlimento INT;

	BEGIN TRY
		IF EXISTS (SELECT * FROM dbo.Alimentos WHERE Nombre = @inNombreViejo)
		BEGIN
			SET @idAlimento = (SELECT Id FROM dbo.Alimentos WHERE Nombre = @inNombreViejo);

			UPDATE dbo.Alimentos
			SET
				Nombre = @inNombre,
				Cantidad = @inCantidad,
				IdTipoAlimentos = @inTipoAlimentos,
				Precio = @inPrecio,
				Imagen = @inImagen
			WHERE Id = @idAlimento

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
