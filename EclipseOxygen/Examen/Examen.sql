
--Procedimiento almacenado que da de baja a un avión que ha sufrido un accidente definitivo, 
--poniendo el valor N en la columna activo y eliminando los futuros vuelos asociados.
--Datos de entrada: CHAR(10) que es la matricula del avión
create procedure BajaAvion @matriculaAvion CHAR(10) 
as BEGIN
	BEGIN TRY
		BEGIN TRANSACTION
		update AS_Aviones set Activo='N' where Matricula=@matriculaAvion
		Delete from AS_Vuelos where Matricula_Avion=@matriculaAvion and Salida>=GETDATE()
	END TRY
	BEGIN CATCH
		RAISERROR ('Fallo en la ejecución del prodecimiento almacenado BajaAvion', 16, 217)
		ROLLBACK
	END CATCH
END