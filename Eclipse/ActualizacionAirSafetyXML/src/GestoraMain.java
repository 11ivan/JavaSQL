import java.sql.ResultSet;
import java.sql.SQLException;

public class GestoraMain {

    /*
   Proposito: Recorre el ResultSet de EX_Actualizaciones y dependidendo del estado de las 
   		   columnas NombreAvion y Fabricante realizaremos inserciones en la tabla AS_Incidencias,
   		   AS_Aviones y/o daremos de baja a un avion/es y los futuros vuelos asociados si ha tenido
   		   un Accidente Definitivo
   Precondiciones: No hay
   Entradas: No hay
   Salidas: No hay
   Postcondiciones: Se ha actualizado la base de datos
   */
   public void actualizarDB(GestoraConsultas gestoraConsultas, Actualizacion actualizacion){
	   //ResultSet resultSet=gestoraConsultas.getResulSetActualizaciones();
   
         
       try {            	
    	   gestoraConsultas.getGestoraConexion().getConnect().setAutoCommit(false);
    	   
           	//Damos los datos al avion
           	/*avion.setNombre(resultSet.getString("NombreAvion"));
           	avion.setMatricula(resultSet.getString("MatriculaAvion"));                	
           	if(resultSet.getString("Fabricante")!=null){
           		avion.setIdFabricante(gestoraConsultas.getIdFabricante(resultSet.getString("Fabricante")));
           	}
           	avion.setModelo(resultSet.getString("Modelo"));
           	avion.setFechaFabricacion(resultSet.getDate("Fecha_Fabricacion"));
           	avion.setFechaEntrada(resultSet.getDate("Fecha_Entrada"));                	
           	avion.setFilas(resultSet.getInt("Filas"));               	
           	avion.setAsientosXFila(resultSet.getInt("Asientos_x_Fila"));
           	avion.setAutonomia(resultSet.getInt("Autonomia"));
           	if(resultSet.getBoolean("AccidenteDefinitivo")) {
           		avion.setActivo("N");
           	}else {
           		avion.setActivo("S");
           	}*/
           	
           	//Damos los datos a la incidencia
           	/*incidencia.setMatriculaAvion(avion.getMatricula());
           	incidencia.setLatitud(resultSet.getBigDecimal("Latitud"));
           	incidencia.setLongitud(resultSet.getBigDecimal("Longitud"));
           	incidencia.setDescripcion(resultSet.getString("Descripcion"));
           	incidencia.setTipo(resultSet.getString("Tipo"));  */          	
           	
            //Si los datos del avion estan a null excepto la matricula es que el avion ya esta registrado
           	if(actualizacion.getAvion().getNombre()!=null && actualizacion.getAvion().getIdFabricante()!=-1) {
       			//Insertamos nuevo avion
           		gestoraConsultas.insertInToAviones(actualizacion.getAvion());               	
           	}                	
           	//Insertamos la incidencia
           	gestoraConsultas.insertInToIncidencias(actualizacion.getIncidencia());      
       		if(actualizacion.getAvion().getActivo()=="N") {
           		//Y efectuaríamos la baja con el procedimiento del ej 1
       			gestoraConsultas.executeBajaAvion(actualizacion.getAvion().getMatricula());
       		}
           
          //Eliminamos los  datos de la tabla EX_Actualizaciones
           //gestoraConsultas.deleteActualizaciones();
          
       } catch (SQLException e) {        	
       	System.out.println(e.getMessage()+"ES AQUI actulizarDB");
       }	   
   }
 
	
	
}
