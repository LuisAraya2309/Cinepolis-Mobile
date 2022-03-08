

USE [CinepolisBD]
GO

SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

ALTER PROCEDURE [dbo].[sp_InicioSesion]
	@inCorreo VARCHAR(64),
	@inPassword VARCHAR(64)
	
AS
BEGIN
	
	SET NOCOUNT ON;
	
		SELECT 
			U.Id,
			U.IdTipoUsuarios
		FROM dbo.Usuarios AS U
		WHERE @inCorreo = U.CorreoElectronico AND @inPassword = U.Contraseña; 


	

	

	SET NOCOUNT OFF;
END
