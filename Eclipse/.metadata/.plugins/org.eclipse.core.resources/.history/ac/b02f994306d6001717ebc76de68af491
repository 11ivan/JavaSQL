/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//package Mascotas;

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
        java.sql.Date fechaNac;
        java.sql.Date fechaFall;
        String alias;
        int codigoPropietario;
        String enfermedad;
        if(resultSet!=null){
            try {
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
                    	
                    	insertInTovisitas(fechaVisita, temperatura, peso, codigoMascota);
                    }       

                    //Si la raza, especie y enfermedad no son null, es que la mascota no está registrada, 
                    //se hará una inserción en la tabla BI_Mascotas, BI_Visitas, BI_Clientes y BI_MascotasEnfermedades
                    if(raza!=null && especie!=null && enfermedad!=null){
                    	//insertar primero en clientes, despues en mascotas
                    	insertInTovisitas(fechaVisita, temperatura, peso, codigoMascota);             	
                    	insertInToMascotasEnfermedades(enfermedad, fechaVisita, codigoMascota);
                    }                         
                }//fin mientras
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
    	String cadenaSentencia="exec dbo.InsertMascotasEnfermedades ?, ?, ?";
    	CallableStatement callableStatement;
    	
    	try {
			callableStatement=conexion.getConnect().prepareCall(cadenaSentencia);
			callableStatement.setString(1, nombreEnfermedad);
			callableStatement.setTimestamp(2, fechaDiagnostico);
			callableStatement.setString(3, codMascota);
			filasAfectadas=callableStatement.executeUpdate();			
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	return filasAfectadas;
    }
    
    
    
}
