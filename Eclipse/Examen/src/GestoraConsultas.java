/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//package Mascotas;

import java.awt.Cursor;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author icastillo
 */
public class GestoraConsultas {
	
    private GestoraConexion conexion;
    private GestoraSentencias gestoraSentencias;
    
    public GestoraConsultas(){
        conexion=new GestoraConexion();
        gestoraSentencias=new GestoraSentencias();
    }
    
    public GestoraConexion getGestoraConexion(){
        return conexion;
    }
    
    /*
    Proposito: Carga en un ResultSet en contenido de la tabla EX_Actualizaciones
    Precondiciones: No hay
    Entradas: No hay
    Salidas: Un ResultSet con el resultado de la consulta a EX_Actualizaciones
    Postcondiciones: Se ha cargado en un ResultSet el contenido de la tabla EX_Actualizaciones
    */
    public ResultSet cargaContenidoActualizaciones(){     
        String consulta="SELECT MatriculaAvion, Latitud, Longitud, Descripcion, Tipo, AccidenteDefinitivo,"
        				+ "NombreAvion, Fabricante, Modelo, Fecha_Fabricacion, Fecha_Entrada, Filas, "
        				+ "Asientos_x_Fila, Autonomia FROM EX_Actualizaciones";	
        Statement sentencia;
        ResultSet resultSet=null;
        
        try {
            sentencia=conexion.getConnect().createStatement();
            resultSet=sentencia.executeQuery(consulta);                                          
        } catch (SQLException e) {
        	System.out.println(e.getMessage());        
        }      
        return resultSet;
    }
    
     /*
    Proposito: Dependidendo del estado de las columnas NombreAvion y Fabricante realizaremos 
    		   inserciones en la tabla AS_Incidencias, AS_Aviones y/o daremos de baja a un 
    		   avion/es y los futuros vuelos asociados si ha tenido un Accidente Definitivo
    Precondiciones: No hay
    Entradas: Un ResultSet con el contenido de la tabla EX_Actualizaciones
    Salidas: no hay
    Postcondiciones: Se ha actualizado la base de datos
    */
    public void routerXD(){
    	ResultSet resultSet=cargaContenidoActualizaciones();
        Incidencia incidencia=new Incidencia();
        Avion avion=new Avion();
        
        if(resultSet!=null){
            try {            	           	
                while(resultSet.next()){
            	
                	//Damos los datos al avion
                	avion.setNombre(resultSet.getString("NombreAvion"));
                	avion.setMatricula(resultSet.getString("MatriculaAvion"));                	
                	if(resultSet.getString("Fabricante")!=null){
                		avion.setIdFabricante(getIdFabricante(resultSet.getString("Fabricante")));
                	}
                	avion.setModelo(resultSet.getString("Modelo"));
                	avion.setFechaFabricacion(resultSet.getDate("Fecha_Fabricacion"));
                	avion.setFechaEntrada(resultSet.getDate("Fecha_Entrada"));                	
                	avion.setFilas(resultSet.getInt("Filas"));               	
                	avion.setAsientosXFila(resultSet.getInt("Asientos_x_Fila"));
                	avion.setAutonomia(resultSet.getInt("Autonomia"));
                	avion.setActivo(resultSet.getBoolean("AccidenteDefinitivo"));
                	
                	//Damos los datos a la incidencia
                	incidencia.setMatriculaAvion(avion.getMatricula());
                	incidencia.setLatitud(resultSet.getBigDecimal("Latitud"));
                	incidencia.setLongitud(resultSet.getBigDecimal("Longitud"));
                	incidencia.setDescripcion(resultSet.getString("Descripcion"));
                	incidencia.setTipo(resultSet.getString("Tipo"));             	
                	
                    //Si los datos del avion estan a null excepto la matricula es que el avion ya esta registrado
                	if(avion.getNombre()!=null && avion.getIdFabricante()!=-1) {
            			//Insertamos nuevo avion
                		insertInToAviones(avion);               	
                	}                	
                	//Insertamos la incidencia
            		insertInToIncidencias(incidencia);      
            		if(!avion.isActivo()) {
                		//Y efectuar�amos la baja con el procedimiento del ej 1
                		executeBajaAvion(avion.getMatricula());
            		}
                }//fin mientras
                
               deleteActualizaciones();
               
            } catch (SQLException e) {        	
            	System.out.println(e.getMessage());
            }
        }//fin si
    }
    
    
    /*
    Proposito: Realiza una insercion en la tabla AS_Aviones mediante un resultSet actualizable
    Precondiciones: No hay
    Entradas: Un objeto Avion
    Salidas: Un entero con la cantidad de filas afectadas
    Postcondiciones: Se ha insertado en la Tabla AS_Aviones los nuevos datos
    */
    public int insertInToAviones(Avion avion){
        int filasAfectadas=0;
        String cadenaSentencia="Select Matricula, Nombre, ID_Fabricante, Modelo, Fecha_Fabricacion, Fecha_Entrada, Filas, Asientos_x_Fila, Autonomia, Activo From AS_Aviones";     
        Statement sentencia;
        ResultSet resultSetAct;
        try{
        	sentencia=conexion.getConnect().createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE);
        	resultSetAct=sentencia.executeQuery(cadenaSentencia);
        	if(resultSetAct.next()){        		
        		//Posicionamos el cursor en la fila de inserci�n
        		resultSetAct.moveToInsertRow();
        		
        		//Cambiamos los datos para esa fila
        		resultSetAct.updateString("Matricula", avion.getMatricula());
        		resultSetAct.updateString("Nombre", avion.getNombre());
        		resultSetAct.updateShort("ID_Fabricante", avion.getIdFabricante());
        		resultSetAct.updateString("Modelo", avion.getModelo());
        		resultSetAct.updateDate("Fecha_Fabricacion", avion.getFechaFabricacion());        		
        		resultSetAct.updateDate("Fecha_Entrada", avion.getFechaEntrada());
        		resultSetAct.updateInt("Filas", avion.getFilas());
        		resultSetAct.updateInt("Asientos_x_Fila", avion.getAsientosXFila());
        		resultSetAct.updateInt("Autonomia", avion.getAutonomia());
        		resultSetAct.updateBoolean("Activo", avion.isActivo());

        		//Y la insertamos
        		resultSetAct.insertRow();
        	}
        }catch(SQLException e){
        	System.out.println(e.getMessage());
        }                    
        return filasAfectadas;
    }

    
    /*
    Proposito: Dado un nombre de fabricante busca el id del mismo en la base de datos y lo devuelve
    Precondiciones: No hay
    Entradas: String nombre del fabricante
    Salidas: Un short que es el id del fabricante
    Postcondiciones: Se ha insertado en la Tabla AS_Aviones los nuevos datos
    */
    public short getIdFabricante(String nombre) {
    	short id=0;
        ResultSet resultSet=null;      
        try {
        	gestoraSentencias.getPreparedStatementSelectIdFabricante().setString(1, nombre);
            resultSet=gestoraSentencias.getPreparedStatementSelectIdFabricante().executeQuery();    
            if(resultSet.next()) {
            	id=resultSet.getShort("ID_Fabricante");
            }
        } catch (SQLException e) {
        	System.out.println(e.getMessage());     
        }          	    	
    	return id;
    }   
       
    /*
    Proposito: Dada un matricula de avion marca a �ste como no activo en la base de datos
    Precondiciones: No hay
    Entradas: String matricula del avion
    Salidas: no hay
    Postcondiciones: Se ha marcado el avion como no activo en la base de datos y se eliminan los futuros 
    			     vuelos asociados
    */
    public void executeBajaAvion(String matricula) {
    	try {			
			gestoraSentencias.getCallableStatementExecBajaAvion().setString(1, matricula);		
			gestoraSentencias.getCallableStatementExecBajaAvion().executeUpdate();			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}    	
    }
        
    /*
    Proposito: Realiza una insercion en la table AS_Incidencias 
    Precondiciones: No hay
    Entradas: Un objeto Incidencia
    Salidas: Un entero con la cantidad de filas afectadas
    Postcondiciones: Se ha insertado en la Tabla AS_Incidencias los nuevos datos
    */
    public int insertInToIncidencias(Incidencia incidencia){
        int filasAfectadas=0;
        try{              	
            gestoraSentencias.getPreparedstatementInsertInToIncidencias().setString(1, incidencia.getMatriculaAvion());
            gestoraSentencias.getPreparedstatementInsertInToIncidencias().setBigDecimal(2, incidencia.getLatitud());
            gestoraSentencias.getPreparedstatementInsertInToIncidencias().setBigDecimal(3, incidencia.getLongitud());
            gestoraSentencias.getPreparedstatementInsertInToIncidencias().setString(4, incidencia.getDescripcion());
            gestoraSentencias.getPreparedstatementInsertInToIncidencias().setString(5, incidencia.getTipo());
            
            filasAfectadas=gestoraSentencias.getPreparedstatementInsertInToIncidencias().executeUpdate();

        }catch(SQLException e){
        	//e.printStackTrace();
        	System.out.println(e.getMessage());
        }                    
        return filasAfectadas;
    }
  
    /*
    Proposito: Elimina el contenido de la table EX_Actualizaciones
    Precondiciones: No hay
    Entradas: no hay
    Salidas: no hay
    Postcondiciones: Se ha eliminado el contenido de EX_Actualizaciones
    */
    public void deleteActualizaciones(){     
        String delete="DELETE FROM EX_Actualizaciones";
        Statement sentencia;
        
        try {
            sentencia=conexion.getConnect().createStatement();
            sentencia.executeUpdate(delete);                                        
        } catch (SQLException e) {
        	System.out.println(e.getMessage());        
        }             
    }


    
    
    
    
}
