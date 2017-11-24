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
    Precondiciones: La tabla BI_Actualizaciones debe contener algún dato
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
        if(resultSet!=null){
            try {
                while(resultSet.next()){
                    
                }
            } catch (SQLException ex) {
                Logger.getLogger(GestoraConsultas.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    /*
    Proposito: Comprueba si es necesario realizar una actualizacion o una inserción 
    Precondiciones: La tabla BI_Actualizaciones debe contener algún dato
    Entradas: No hay
    Salidas: Un ResultSet con el resultado de la consulta a BI_Actualizaciones
    Postcondiciones: Se ha cargado en un ResultSet el contenido de la tabla BI_Actualizaciones
    */

    
    
}
