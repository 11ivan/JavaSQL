/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package accesoadatos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.GregorianCalendar;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import sun.nio.cs.Surrogate;

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
    
    
    /*
    Propósito: Consulta que devuelve los números que han salido en el últomo sorteo de la primitiva
    Prototipo: void getNumerosSorteo()
    Precondiciones: Debe haber sorteos en la base de datos
    Entradas: No hay
    Salidas: No hay
    Postcondiciones: Se pinta en pantallas los números del último sorteo realizado
    */
    public void getNumerosSorteo(){
        String consulta = "Select TOP 1 num1, num2, num3, num4, num5, num6 from Sorteos order by [Fecha/Hora] desc";
        try {
            //Connection connexionBaseDatos = DriverManager.getConnection(camposConexion[0], camposConexion[1], camposConexion[2]);
            Statement sentencia = gestoraConexion.getConnect().createStatement();
            ResultSet resultados = sentencia.executeQuery(consulta);
            
            System.out.println("Numero 1 | "+"Numero 2 | "+"Numero 3 | "+"Numero 4 | "+"Numero 5 | "+"Numero 6 |");
            while(resultados.next()){
                    //id=resultados.getString("ID");
                    System.out.println("|   "+resultados.getString("num1")+"    |   "+
                    resultados.getString("num2")+"      |   "+
                    resultados.getString("num3")+"      |   "+
                    resultados.getString("num4")+"     |   "+
                    resultados.getString("num5")+"     |  "+
                    resultados.getString("num6")+"      |  ");
            }
            //connexionBaseDatos.close();
        } catch (SQLException e) {
                System.out.println(e);
        }			                
    }
    
    //-----------------------------------------------------------------------------
    /*
    Propósito: Graba una apuesta sencilla en la base de datos
    Prototipo: Boolean grabaSencilla(int idSorteo, int[] numerosApuesta)
    Precondiciones: El array de enteros tendrá 6 números entre 1 y 49 y el sorteo debe estar abierto
    Entradas: El ID del sorteo y un array de enteros con 6 números
    Salidas: Un booleano
    Postcondiciones: El booleano será verdadero si el boleto se ha introducido correctamnete en la base de datos y falso sino
    */
    public Boolean grabaSencilla(int idSorteo, int[] numerosApuesta){//ID, Fecha/Hora, ID_Sorteo, Importe, Reintegro--para tabla boletos
        Boolean introducido=true;                                    //ID_Boleto, Columna, Número, Tipo_Apuesta
        //String newid = System.Guid.NewGuid().ToString();
        Random aleatorio=new Random();
        Integer uniqueIdentifier= aleatorio.nextInt(9999)+1000;
        String newID= uniqueIdentifier.toString();
        String sentenciaPreparada="Insert into Boletos values("+newID+ new java.sql.Timestamp(new GregorianCalendar()) ")";
        //PreparedStatement preparedStatement=
        
        return introducido;
    }
    
    
}
