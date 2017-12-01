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
    
    public GestoraConsultas(){
        conexion=new GestoraConexion();
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
        String consulta="SELECT * FROM EX_Actualizaciones";
        Statement sentencia;
        ResultSet resultSet=null;
        
        try {
            sentencia=conexion.getConnect().createStatement();
            resultSet=sentencia.executeQuery(consulta);                                          
        } catch (SQLException e) {
        	System.out.println(e.getMessage());        }      
        return resultSet;
    }
    
     /*
    Proposito: 
    Precondiciones: La tabla BI_Actualizaciones debe contener algún dato
    Entradas: No hay
    Salidas: no hay
    Postcondiciones: Se ha actualizado la base de datos
    */
    public void routerXD(ResultSet resultSet){
    	
    	String matriculaAvion;
    	java.math.BigDecimal latitud;
    	java.math.BigDecimal longitud;
    	String descripcion;
    	String tipo;
    	boolean accidenteDefinitivo;
    	String nombreAvion;
    	String fabricante;
    	String modelo;
        java.sql.Date fechaFabricacion;
        java.sql.Date fechaEntrada;
        short filas;
        short asientosXFila;
        int autonomia;
        
        if(resultSet!=null){
            try {            	           	
                while(resultSet.next()){
                    //1. Las modificaciones en la tabla AS_Aviones mediante un resultSet actualizable
                	//excepto las bajas que usaremos el procedimiento almacenado del ej 1
                    
                    //2. Para las bajas usaremos el procedimiento almacenado del ejercicio 1. con prepared o callable
                    
                    //3. Las inserciones en AS_Incidencias y el resto las puedes hacer como mejor te parezca.

                	matriculaAvion=resultSet.getString("MatriculaAvion");
                	latitud=resultSet.getBigDecimal("Latitud");
                	longitud=resultSet.getBigDecimal("Longitud");
                	descripcion=resultSet.getString("Descripcion");
                	tipo=resultSet.getString("Tipo");
                	accidenteDefinitivo=resultSet.getBoolean("AccidenteDefinitivo");
                	nombreAvion=resultSet.getString("NombreAvion");
                	fabricante=resultSet.getString("Fabricante");
                	modelo=resultSet.getString("Modelo");
                	fechaFabricacion=resultSet.getDate("Fecha_Fabricacion");
                	fechaEntrada=resultSet.getDate("Fecha_Entrada");
                	filas=resultSet.getShort("Filas");
                	asientosXFila=resultSet.getShort("Asientos_x_Fila");
                	autonomia=resultSet.getInt("Autonomia");
                	
                	
                    //Si los datos del avion estan a null excepto la matricula es que el avion ya esta registrado
                	if(matriculaAvion!=null && nombreAvion==null && fabricante==null) {
                		//Conseguir id del fabricante
                		short idFabricante=getIdFabricante(fabricante);
                		
                		//Hay que comprobar si hay accidente definitivo para efectuar la baja y saber si está activo
                		if(accidenteDefinitivo) {        		
                    		//Efectuaríamos la baja con el procedimiento del ej 1
                			executeBajaAvion(matriculaAvion);
                		}
                		//Insertamos la incidencia
                		insertInToIncidencias(matriculaAvion, latitud, longitud, descripcion, tipo);
                	}else {//Sino es que hay que registrar al avión en la base de datos
                		//Conseguir idFabricante
                		short idFabricante=getIdFabricante(fabricante);
                		
                		//Hay que comprobar si hay accidente definitivo para efectuar la baja y saber si está activo
                		if(accidenteDefinitivo) {
                			//Insertamos nuevo avion
                    		insertInToAviones(matriculaAvion, nombreAvion, idFabricante, modelo, fechaFabricacion, fechaEntrada, filas, asientosXFila, autonomia, true);
                    		//Y efectuaríamos la baja con el procedimiento del ej 1
                    		executeBajaAvion(matriculaAvion);
                		}else {
                    		insertInToAviones(matriculaAvion, nombreAvion, idFabricante, modelo, fechaFabricacion, fechaEntrada, filas, asientosXFila, autonomia, false);
                		}
                		//Insertamos la incidencia
                		insertInToIncidencias(matriculaAvion, latitud, longitud, descripcion, tipo);                		
                	}//fin sino                	                   
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
    Entradas: String matricula, String nombre, short idFabricante, String modelo
     		  java.sql.Date fechaFabricacion, java.sql.Date fechaEntrada, short filas
     		  short asientosXFila, int autonomia, boolean activo
    Salidas: Un entero con la cantidad de filas afectadas
    Postcondiciones: Se ha insertado en la Tabla AS_Aviones los nuevos datos
    */
    public int insertInToAviones(String matricula, String nombre, short idFabricante, String modelo,  java.sql.Date fechaFabricacion, java.sql.Date fechaEntrada, short filas, short asientosXFila, int autonomia, boolean activo ){
        int filasAfectadas=0;
        String cadenaSentencia="Select Matricula, Nombre, ID_Fabricante, Modelo, Fecha_Fabricacion, Fecha_Entrada, Filas, Asientos_x_Fila, Autonomia, Activo From AS_Aviones";     
        Statement sentencia;
        ResultSet resultSetAct;
        try{
        	sentencia=conexion.getConnect().createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE);
        	resultSetAct=sentencia.executeQuery(cadenaSentencia);
        	if(resultSetAct.next()){        		
        		//Posicionamos el cursor en la fila de inserción
        		resultSetAct.moveToInsertRow();
        		
        		//Cambiamos los datos para esa fila
        		resultSetAct.updateString("Matricula", matricula);
        		resultSetAct.updateString("Nombre", nombre);
        		resultSetAct.updateShort("ID_Fabricante", idFabricante);
        		resultSetAct.updateString("Modelo", modelo);
        		resultSetAct.updateDate("Fecha_Fabricacion", fechaFabricacion);        		
        		resultSetAct.updateDate("Fecha_Entrada", fechaEntrada);
        		resultSetAct.updateShort("Filas", filas);
        		resultSetAct.updateShort("Asientos_x_Fila", asientosXFila);
        		resultSetAct.updateInt("Autonomia", autonomia);
        		resultSetAct.updateBoolean("Activo", activo);

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
        String consulta="SELECT ID_Fabricante FROM AS_Fabricantes where Nombre=?";
        PreparedStatement sentenciaPreparada;
        ResultSet resultSet=null;      
        try {
        	sentenciaPreparada=conexion.getConnect().prepareStatement(consulta);
        	
        	sentenciaPreparada.setString(1, nombre);
            resultSet=sentenciaPreparada.executeQuery();    
            if(resultSet.next()) {
            	id=resultSet.getShort("ID_Fabricante");
            }
        } catch (SQLException e) {
        	System.out.println(e.getMessage());        }          	    	
    	return id;
    }   
       
    /*
    Proposito: Dada un matricula de avion marca a éste como no activo en la base de datos
    Precondiciones: No hay
    Entradas: String matricula del avion
    Salidas: no hay
    Postcondiciones: Se ha marcado el avion como no activo en la base de datos y se eliminan los futuros 
    			     vuelos asociados
    */
    public void executeBajaAvion(String matricula) {
        String cadenaSentencia="exec BajaAvion ?";
    	CallableStatement callableStatement;   	
    	try {
			callableStatement=conexion.getConnect().prepareCall(cadenaSentencia);
			callableStatement.setString(1, matricula);		
			callableStatement.executeUpdate();			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}    	
    }
        
    /*
    Proposito: Realiza una insercion en la table AS_Incidencias 
    Precondiciones: No hay
    Entradas: No hay
    Salidas: Un entero con la cantidad de filas afectadas
    Postcondiciones: Se ha insertado en la Tabla BI_Mascotas los nuevos datos
    */
    public int insertInToIncidencias(String matriculaAvion, java.math.BigDecimal latitud, java.math.BigDecimal longitud, String descripcion, String tipo){
        int filasAfectadas=0;
        String cadenaSentencia="insert INTO AS_Incidencias (Avion, Latitud, Longitud, Descripcion, Tipo) values( ?, ?, ?, ?, ?)";
        PreparedStatement sentenciaPreparada;
        try{
        	sentenciaPreparada=conexion.getConnect().prepareStatement(cadenaSentencia);
        	
            sentenciaPreparada.setString(1, matriculaAvion);
            sentenciaPreparada.setBigDecimal(2, latitud);
            sentenciaPreparada.setBigDecimal(3, longitud);
            sentenciaPreparada.setString(4, descripcion);
            sentenciaPreparada.setString(5, tipo);
            
            filasAfectadas=sentenciaPreparada.executeUpdate();

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
