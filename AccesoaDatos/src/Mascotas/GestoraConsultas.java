/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Mascotas;

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
    
    
    
    
}
