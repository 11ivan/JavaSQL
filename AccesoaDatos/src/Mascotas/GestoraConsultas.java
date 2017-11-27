/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Mascotas;

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
       // conexion.connect();
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
        String temperatura;
        String peso;
        String codigoMascota;
        String raza;
        String especie;
        String fechaNac;
        String fechaFall;
        String alias;
        String codigoPropietario;
        String enfermedad;
        if(resultSet!=null){
            try {
                while(resultSet.next()){
                    //insercion en la table BI_Mascotas mediante resulset actualizable
                    
                    //inserciones en BI_MascotasEnfermedades deben hacerse con una sentencia preparada (Prepared Statement o CallableStatement).
                    //Esta sentencia debe ejecutar el procedimiento almacenado del ejercicio 1.
                    
                    //Las inserciones en BI_Visitas las puedes hacer como mejor te parezca.

                    //Si la raza, la especie y enfermedad son null a enfermedad es que la mascota ya está registrada,
                    //será inserción sólo en BI_Visitas
                    
                    //Si la raza, epecie son null pero la enfermedad no es que la mascota ya está registrada,
                    //se hará la inserción en BI_MascotasEnfermedades y BI_Visitas
                    
                    //Si la raza y especie no son null pero la enfermedad sí, es que la mascota no está registrada, 
                    //se hará una inserción en la tabla BI_Mascotas, BI_Visitas y BI_Clientes
                    
                    //Si la raza, especie y enfermedad no son null, es que la mascota no está registrada, 
                    //se hará una inserción en la tabla BI_Mascotas, BI_Visitas, BI_Clientes y BI_MascotasEnfermedades
                    
                    
                }
            } catch (SQLException ex) {
                Logger.getLogger(GestoraConsultas.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    /*
    Proposito: Realiza una insercion en la table BI_Mascotas mediante resulset actualizable
    Precondiciones: No hay
    Entradas: No hay
    Salidas: Un entero con la cantidad de filas afectadas
    Postcondiciones: Se ha insertado en la Tabla BI_Mascotas los nuevos datos
    */
    public int insertInTOMascotas(){
        int filasAfectadas=0;
    
        
        
        
        return filasAfectadas;
    }
    
    
}
