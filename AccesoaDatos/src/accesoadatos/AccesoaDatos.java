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
        
        gestoraConsultas.getNumerosSorteo();
        
        gestoraConsultas.getGestoraConexion().close();
    }
    
}
