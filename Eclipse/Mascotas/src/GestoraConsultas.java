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
    Proposito: Carga en un ResultSet en contenido de la tabla BI_Actualizaciones
    Precondiciones: No hay
    Entradas: No hay
    Salidas: Un ResultSet con el resultado de la consulta a BI_Actualizaciones
    Postcondiciones: Se ha cargado en un ResultSet el contenido de la tabla BI_Actualizaciones
    */
    public ResultSet cargaContenidoActualizaciones(){
        //conexion.connect();
        String consulta="SELECT * FROM BI_Actualizaciones";
        Statement sentencia;
        ResultSet resultSet=null;
        
        try {
            sentencia=conexion.getConnect().createStatement();
            resultSet=sentencia.executeQuery(consulta);                                          
        } catch (SQLException ex) {
            Logger.getLogger(GestoraConsultas.class.getName()).log(Level.SEVERE, null, ex);
        }      
        return resultSet;
    }
    
     /*
    Proposito: Según el estado de las columnas raza, especie y enfermedad, 
               decidirá si realiza un a actualización o una inserción en las distintas tablas 
    Precondiciones: La tabla BI_Actualizaciones debe contener algún dato
    Entradas: No hay
    Salidas: Un ResultSet con el resultado de la consulta a BI_Actualizaciones
    Postcondiciones: Se ha cargado en un ResultSet el contenido de la tabla BI_Actualizaciones
    */
    public void routerXD(ResultSet resultSet){
        //String fecha
        java.sql.Timestamp fechaVisita;
        int temperatura;
        int peso;
        String codigoMascota;
        String raza;
        String especie;
        java.sql.Date fechaNac=null;
        java.sql.Date fechaFall=null;
        String alias;
        int codigoPropietario;
        String enfermedad;
        String cadenaBegin="exec Begin Transaction";
        String cadenaRollback="exec rollback";
        Statement sentencia=null;
        if(resultSet!=null){
            try {
            	sentencia=conexion.getConnect().createStatement();
            	//sentencia.execute(cadenaBegin);
                while(resultSet.next()){
                    //1. insercion en la table BI_Mascotas mediante resulset actualizable
                    
                    //2. inserciones en BI_MascotasEnfermedades deben hacerse con una sentencia preparada (Prepared Statement o CallableStatement).
                    //Esta sentencia debe ejecutar el procedimiento almacenado del ejercicio 1.
                    
                    //3. Las inserciones en BI_Visitas las puedes hacer como mejor te parezca.

                    fechaVisita=resultSet.getTimestamp("Fecha");
                    temperatura=resultSet.getInt("Temperatura");
                    peso=resultSet.getInt("Peso");
                    codigoMascota=resultSet.getString("CodigoMascota");
                    raza=resultSet.getString("Raza");
                    especie=resultSet.getString("Especie");
                    if(resultSet.getDate("FechaNacimiento")!=null){
                        fechaNac=resultSet.getDate("FechaNacimiento");
                    }
                    if(resultSet.getDate("FechaFallecimiento")!=null){
                        fechaFall=resultSet.getDate("FechaFallecimiento");
                    }
                    alias=resultSet.getString("Alias");
                    codigoPropietario=resultSet.getInt("CodigoPropietario");
                    enfermedad=resultSet.getString("Enfermedad");
                    
                    
                    //Si la raza, la especie y enfermedad son null es que la mascota ya está registrada,
                    //será inserción sólo en BI_Visitas
                    if(raza==null && especie==null && enfermedad==null){
                        insertInTovisitas(fechaVisita, temperatura, peso, codigoMascota);
                    }
                    
                    //Si la raza, epecie son null pero la enfermedad no es que la mascota ya está registrada,
                    //se hará la inserción en BI_MascotasEnfermedades y BI_Visitas
                    if(raza==null && especie==null && enfermedad!=null){
                    	insertInTovisitas(fechaVisita, temperatura, peso, codigoMascota);
                    	insertInToMascotasEnfermedades(enfermedad, fechaVisita, codigoMascota);
                    }       
                    
                    //Si la raza y especie no son null pero la enfermedad sí, es que la mascota no está registrada, 
                    //se hará una inserción en la tabla BI_Mascotas, BI_Visitas y BI_Clientes
                    if(raza!=null && especie!=null && enfermedad==null){
                    	//insertar primero en clientes, despues en mascotas
                    	
                    	//Comprobar si el cliente ya existe para hacer la inserci�n
                    	if(!existeCliente(codigoPropietario)){
                    		insertInToClientes(codigoPropietario);
                    	}                   	                 
                    	//Comprobar si la mascota ya existe para hacer la inserci�n
                    	if(!existeMascota(codigoMascota)){
                    		insertInToMascotas(codigoMascota, raza, especie, fechaNac, fechaFall, alias, codigoPropietario);
                    	}
                    	insertInTovisitas(fechaVisita, temperatura, peso, codigoMascota);
                    }       

                    //Si la raza, especie y enfermedad no son null, es que la mascota no está registrada, 
                    //se hará una inserción en la tabla BI_Mascotas, BI_Visitas, BI_Clientes y BI_MascotasEnfermedades
                    if(raza!=null && especie!=null && enfermedad!=null){          	
                    	//insertar primero en clientes, despues en mascotas
                    	
                    	//Comprobar si el cliente ya existe para hacer la inserci�n
                    	if(!existeCliente(codigoPropietario)){
                    		insertInToClientes(codigoPropietario);
                    	}                   	
                    	//Comprobar si la mascota ya existe para hacer la inserci�n
                    	if(!existeMascota(codigoMascota)){
                    		insertInToMascotas(codigoMascota, raza, especie, fechaNac, fechaFall, alias, codigoPropietario);
                    	}                    	                 	
                    	insertInTovisitas(fechaVisita, temperatura, peso, codigoMascota);             	
                    	insertInToMascotasEnfermedades(enfermedad, fechaVisita, codigoMascota);
                    }                       
                    
                }//fin mientras
                //sentencia.execute(cadenaRollback);
            } catch (SQLException ex) {        	
                Logger.getLogger(GestoraConsultas.class.getName()).log(Level.SEVERE, null, ex);
            }
        }//fin si
    }
    
    /*
    Proposito: Realiza una insercion en la table BI_Mascotas mediante resulset actualizable
    Precondiciones: No hay
    Entradas: No hay
    Salidas: Un entero con la cantidad de filas afectadas
    Postcondiciones: Se ha insertado en la Tabla BI_Mascotas los nuevos datos
    */
    public int insertInToMascotas(String codigoMascota, String raza, String especie, java.sql.Date fechaNacimiento, java.sql.Date fechaFallecimiento, String alias, int codPropietario){
        int filasAfectadas=0;
        String cadenaSentencia="Select Codigo, Raza, Especie, FechaNacimiento, FechaFallecimiento, Alias, CodigoPropietario From BI_Mascotas";
        //Cursor cursor;
        Statement sentencia;
        ResultSet resultSetAct;
        try{
        	sentencia=conexion.getConnect().createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE);
        	resultSetAct=sentencia.executeQuery(cadenaSentencia);
        	if(resultSetAct.next()){        		
        		//Posicionamos el cursor en la fila de inserci�n
        		resultSetAct.moveToInsertRow();
        		
        		//Cambiamos los datos para esa fila
        		resultSetAct.updateString("Codigo", codigoMascota);
        		resultSetAct.updateString("Raza", raza);
        		resultSetAct.updateString("Especie", especie);
        		resultSetAct.updateDate("FechaNacimiento", fechaNacimiento);
        		if(fechaFallecimiento!=null){
        			resultSetAct.updateDate("FechaFallecimiento", fechaFallecimiento);
        		}
        		resultSetAct.updateString("Alias", alias);
        		resultSetAct.updateInt("CodigoPropietario", codPropietario);
        		//Y la insertamos
        		resultSetAct.insertRow();
        	}
        }catch(SQLException e){
        	//e.printStackTrace();
        	System.out.println(e.getMessage());
        }                    
        return filasAfectadas;
    }
    
    
    /*
    Proposito: Realiza una insercion en la table BI_Visitas
    Precondiciones: No hay
    Entradas: fecha de visita tipo java.sql.TimeStamp, temperatura tipo int, peso int, codMascota String
    Salidas: Un entero con la cantidad de filas afectadas
    Postcondiciones: Se ha insertado en la Tabla BI_Visitas los nuevos datos
    */
    public int insertInTovisitas(java.sql.Timestamp fechaVisita, int temperatura, int peso, String codMascota){
        int filasAfectadas=0;
        String cadenaSentencia="insert into BI_Visitas (Fecha, Temperatura, Peso, Mascota) values (?, ?, ?, ?)";
        PreparedStatement sentenciaPreparada;
        
        try {
            sentenciaPreparada=conexion.getConnect().prepareStatement(cadenaSentencia);
            
            sentenciaPreparada.setTimestamp(1, fechaVisita);
            sentenciaPreparada.setInt(2, temperatura);
            sentenciaPreparada.setInt(3, peso);
            sentenciaPreparada.setString(4, codMascota);
            
            filasAfectadas=sentenciaPreparada.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(GestoraConsultas.class.getName()).log(Level.SEVERE, null, ex);
        }   
        return filasAfectadas;
    }
    
    
    /*
    Proposito: Realiza una insercion en la tabla BI_Mascotas_Enfermedades usando un procedimiento almacenado de la base de datos
    Precondiciones: No hay
    Entradas: Cadena nombre de la enfermedad, java.sql.TimesTamp fecha de diagnostico, cadena codigo de mascota
    Salidas: Un entero con la cantidad de filas afectadas
    Postcondiciones: Se ha insertado en la Tabla BI_Mascotas_Enfermedades los nuevos datos
    */
    public int insertInToMascotasEnfermedades(String nombreEnfermedad, java.sql.Timestamp fechaDiagnostico, String codMascota){
    	int filasAfectadas=0;
    	String cadenaSentencia="exec InsertMascotasEnfermedades ?, ?, ?";
    	CallableStatement callableStatement;
    	
    	try {
			callableStatement=conexion.getConnect().prepareCall(cadenaSentencia);
			callableStatement.setString(1, nombreEnfermedad);
			callableStatement.setTimestamp(2, fechaDiagnostico);
			callableStatement.setString(3, codMascota);
			filasAfectadas=callableStatement.executeUpdate();			
		} catch (SQLException e) {
			//e.printStackTrace();
			System.out.println(e.getMessage());
		}
    	return filasAfectadas;
    }
    
    /*
    Proposito: Realiza una insercion en la tabla BI_Clientes 
    Precondiciones: No hay
    Entradas: Un entero que es el codigo
    Salidas: Un entero con la cantidad de filas afectadas
    Postcondiciones: Se ha insertado en la Tabla BI_Clientes los nuevos datos
    */
    public int insertInToClientes(int codigo){
        int filasAfectadas=0;
        String cadenaSentencia="insert into BI_Clientes (Codigo, Telefono, Direccion) values (?, ?, ?)";
        PreparedStatement sentenciaPreparada;
		try{
			sentenciaPreparada=conexion.getConnect().prepareStatement(cadenaSentencia);
			
			sentenciaPreparada.setInt(1, codigo);
			sentenciaPreparada.setString(2, "000000000");
			sentenciaPreparada.setString(3, "Desconocida");
			
			filasAfectadas=sentenciaPreparada.executeUpdate();
		}catch(SQLException e){
			e.printStackTrace();
		}           
        return filasAfectadas;
    }
    
    
    /*
    Proposito: Comprueba si un cliente ya existe en la base de datos
    Precondiciones: No hay
    Entradas: Un entero que es el codigo
    Salidas: Un booleano
    Postcondiciones: El booleano ser� verdadero si el cliente existe y false sino
    */
    public boolean existeCliente(int codigo){
    	boolean existe=false;
    	String cadenaConsulta="Select * from BI_Clientes where Codigo=?";
    	PreparedStatement preparedStatement;
    	ResultSet result;
    	try{
    		preparedStatement=conexion.getConnect().prepareStatement(cadenaConsulta);
    		preparedStatement.setInt(1, codigo);
    		result=preparedStatement.executeQuery();
    		if(result.next()){
    			existe=true;
    		}
    	}catch(SQLException e){
    		System.out.println(e.getMessage());
    	}	
    	return existe;
    }

    /*
    Proposito: Comprueba si una mascota ya existe en la base de datos
    Precondiciones: No hay
    Entradas: Un entero que es el codigo
    Salidas: Un booleano
    Postcondiciones: El booleano ser� verdadero si la mascota existe y false sino
    */
    public boolean existeMascota(String codigo){
    	boolean existe=false;
    	String cadenaConsulta="Select * from BI_Mascotas where Codigo=?";
    	PreparedStatement preparedStatement;
    	ResultSet result;
    	try{
    		preparedStatement=conexion.getConnect().prepareStatement(cadenaConsulta);
    		preparedStatement.setString(1, codigo);
    		result=preparedStatement.executeQuery();
    		if(result.next()){
    			existe=true;
    		}
    	}catch(SQLException e){
    		System.out.println(e.getMessage());
    	}	
    	return existe;
    }

    
    
    
    
}
