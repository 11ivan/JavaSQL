select * from EX_Actualizaciones
select * from AS_Fabricantes
select * from AS_Incidencias
delete from AS_Incidencias where id=8
select * from AS_Aviones

--Procedimiento almacenado que da de baja a un avión que ha sufrido un accidente definitivo, 
--poniendo el valor N en la columna activo.
--Datos de entrada: CHAR(10) que es la matricula del avión
Create procedure BajaAvion @matriculaAvion CHAR(10) 
as BEGIN
	update AS_Aviones set Activo='N' where Matricula=@matriculaAvion
	Delete from AS_Vuelos where Matricula_Avion=@matriculaAvion and Salida>=GETDATE()
END