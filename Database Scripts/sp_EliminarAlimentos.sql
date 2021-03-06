USE [CinepolisBD]
GO
/****** Object:  StoredProcedure [dbo].[sp_EliminarAlimento]    Script Date: 16/03/2022 08:53:30 p. m. ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO


alter PROCEDURE [dbo].[sp_EliminarAlimento]
	@inAlimento VARCHAR(64)
AS
BEGIN
	DECLARE @OutResultCode INT = 0;

	BEGIN TRY
		IF EXISTS (SELECT * FROM dbo.Alimentos WHERE Nombre = @inAlimento)
			BEGIN
				
				DELETE FROM dbo.Alimentos
				WHERE Nombre = @inAlimento

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
