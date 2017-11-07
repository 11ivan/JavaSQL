/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package accesoadatos;

import java.sql.Connection;
import java.sql.DriverManager;
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
    
    GestoraConexion gestoraConexion;
    
    
    public GestoraConsultas(){
        gestoraConexion=new GestoraConexion();
    }
    
    public GestoraConexion getGestoraConexion(){
        return gestoraConexion;
    }
    
    public void getNumerosSorteo(){
        String consulta = "Select TOP 1 num1, num2, num3, num4, num5, num6 from Sorteos order by [Fecha/Hora] desc";
        try {
            //Connection connexionBaseDatos = DriverManager.getConnection(camposConexion[0], camposConexion[1], camposConexion[2]);
            Statement sentencia = gestoraConexion.getConnect().createStatement();
            ResultSet resultados = sentencia.executeQuery(consulta);

            while(resultados.next()){
                    //id=resultados.getString("ID");
                    System.out.println(resultados.getString("num1")+
                    resultados.getString("num2")+
                    resultados.getString("num3")+
                    resultados.getString("num4")+
                    resultados.getString("num5")+
                    resultados.getString("num6"));
            }
            //connexionBaseDatos.close();
        } catch (SQLException e) {
                System.out.println(e);
        }			                
    }
    
    
    
}
