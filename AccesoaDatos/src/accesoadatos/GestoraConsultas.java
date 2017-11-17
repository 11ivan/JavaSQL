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
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.Random;
import java.util.UUID;
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
    Precondiciones: El array de enteros tendrá 6 números entre 1 y 49 no repetidos y el sorteo debe estar abierto
    Entradas: El ID del sorteo y un array de enteros con 6 números
    Salidas: Un booleano
    Postcondiciones: El booleano será verdadero si las combinacion se ha introducido correctamnete en la base de datos y falso sino
    */
    public Boolean grabaSencilla(int idSorteo, int[] numerosApuesta){//ID, Fecha/Hora, ID_Sorteo, Importe, Reintegro--para tabla boletos
        Boolean introducido=true;                                    //ID_Boleto, Columna, Número, Tipo_Apuesta--Para tabla combinaciones
        //String newid = System.Guid.NewGuid().ToString();
        Random aleatorio=new Random();
        UUID uniqueIdentifier;
        String newID="";
        String sentenciaPreparada="Insert into Combinaciones values("+ newID +","+ 1 +","+ "?" +","+ "Simple" +")";
        PreparedStatement preparedStatement;       
        
        do{
            uniqueIdentifier= UUID.randomUUID();
            newID= uniqueIdentifier.toString();
        }while(compruebaIdBoleto(newID));//Mientras exista el idBoleto generado
        
        if(grabaBoleto(idSorteo, newID)){//Si el boleto se ha insertado pasamos a grabar la apuesta
            try {
                preparedStatement=gestoraConexion.getConnect().prepareStatement(sentenciaPreparada);

                for(int i=0;i<numerosApuesta.length;i++){
                    preparedStatement.setInt(1, numerosApuesta[i]);
                    preparedStatement.executeUpdate();
                }
            } catch (SQLException ex) {
                Logger.getLogger(GestoraConsultas.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else{
            introducido=false;
        }    
        return introducido;
    }
    
//-----------------------------------------------------------------------------
    /*
    Propósito: Graba un boleto en la base de datos
    Prototipo: Boolean grabaBoleto(int idSorteo)
    Precondiciones: El sorteo debe estar abierto
    Entradas: El ID del sorteo
    Salidas: Un booleano
    Postcondiciones: El booleano será verdadero si el boleto se ha introducido correctamnete en la base de datos y falso sino
    */
    public Boolean grabaBoleto(int idSorteo, String idBoleto){//ID, Fecha/Hora, ID_Sorteo, Importe, Reintegro--para tabla boletos
        Boolean insertado=true;
        Random aleatorio=new Random();
        //GregorianCalendar calendar=new GregorianCalendar(Locale.FRANCE);
        Calendar calendar=GregorianCalendar.getInstance();
        GregorianCalendar gregorio=new GregorianCalendar(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        String fechaCadena=gregorio.toString();
        //fechaCadena=fechaCadena.substring(idSorteo, idSorteo)
        String insert="INSERT INTO Boletos (ID, [Fecha/Hora], ID_Sorteo, Importe, Reintegro) values ("+ idBoleto +","+ new java.sql.Timestamp(gregorio.getTimeInMillis())+ "," +idSorteo+ ","+ 1 +","+ (aleatorio.nextInt(9)+1)+ ")";
        Statement statement;
        int filasAfectadas=0;
        
        try {
            statement = gestoraConexion.getConnect().createStatement();
            insertado=statement.execute(insert);
            /*if(filasAfectadas==0){
                insertado=false;
            }*/
        } catch (SQLException ex) {
            //Logger.getLogger(GestoraConsultas.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex);
            insertado=false;
        }                 
        return insertado;
    }
    
    //-----------------------------------------------------------------------------
    /*
    Propósito: Comprueba si el ID de un boleto en la base de datos
    Prototipo: Boolean compruebaIdBoleto(String idBoleto)
    Precondiciones: El id no será nulo
    Entradas: Una cadena que es el id del boleto
    Salidas: Un booleano
    Postcondiciones: El booleano será verdadero si el id del boleto yqa existee en la base de datos y falso sino
    */
    public Boolean compruebaIdBoleto(String idBoleto){
        Boolean existe=false;
        String consulta="SELECT ID FROM Boletos WHERE ID="+idBoleto;
        Statement sentencia;
        ResultSet result;
        
        try{
            sentencia=gestoraConexion.getConnect().createStatement();
            result=sentencia.executeQuery(consulta);
            if(result!=null){
                existe=true;
            }
        }catch(SQLException e){
            System.out.println(e);
        }
        return existe;
    }
    
    
}
