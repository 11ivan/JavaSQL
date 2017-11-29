/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package accesoadatos;

/**
 *
 * @author icastillo
 */
public class AccesoaDatos {

    /**
     * @param args the command line arguments
     */
    
    
    public static void main(String[] args) {
        
        GestoraConsultas gestoraConsultas=new GestoraConsultas();                     
        gestoraConsultas.getGestoraConexion().connect();
        
        //Consulta para conseguir los números del último sorteo
        //gestoraConsultas.getNumerosSorteo();
        
        //Grabar una apuesta sencilla
        int[] arrayNumeros={5, 10, 20, 35, 12, 40};
        int idSorteo=3;
        System.out.println(gestoraConsultas.grabaSencilla(idSorteo, arrayNumeros));
        
        
        
        gestoraConsultas.getGestoraConexion().close();
    }
    
}
