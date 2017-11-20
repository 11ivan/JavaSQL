/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Mascotas;

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
public class GestoraConexion {
    
    private Connection conexionBaseDatos;
    
    /*
	 Proposito: Abre la conexion con la base de datos
	 Prototipo: void connect()
	 Precondiciones: no hay
	 Entradas: no hay
	 Salidas: no hay 
	 Postcondiciones: Se ha abierto la conexión con la base de datos
	 */	
	public void connect(){
            try {			
                   conexionBaseDatos = DriverManager.getConnection("jdbc:sqlserver://localhost", "manolito", "1234");
            } catch (SQLException e) {
                    e.printStackTrace();
            }			
	}
        
        
        public void close(){
            try {
                conexionBaseDatos.close();
            } catch (SQLException ex) {
                //Logger.getLogger(GestoraConexion.class.getName()).log(Level.SEVERE, null, ex);
                //ex.printStackTrace();
                System.out.println(ex.getMessage());
            }
        }
        
        public Connection getConnect(){
            return conexionBaseDatos;
        }
    
}
