/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Mascotas;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author icastillo
 */
public class Main {
    public static void main(String[] args){
        GestoraConsultas gestoraConsultas=new GestoraConsultas();
        
        try{
            gestoraConsultas.getGestoraConexion().connect();
            ResultSet resultSet=gestoraConsultas.cargaContenidoActualizaciones();
            gestoraConsultas.routerXD(resultSet);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
