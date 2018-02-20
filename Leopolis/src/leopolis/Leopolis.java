/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package leopolis;

import generated.Asiento;
import gestoras.GestoraAsientosJAXB;
import java.io.File;
import java.util.ArrayList;

/**
 PSEUDOCODIGO
    INICIO
    
 
  
  
  
  
  
  
  
  
    FIN
 */


/**
 *
 * @author icastillo
 */
public class Leopolis {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        GestoraAsientosJAXB gestoraAsientosJAXB=new GestoraAsientosJAXB();
        File file1=new File("src\\leopolis\\archivos\\Registro11.xml");
        ArrayList<Asiento> listaAsientos=new ArrayList<Asiento>();
        
        gestoraAsientosJAXB.abrirListaAsientosJAXB(file1);
        gestoraAsientosJAXB.cargaListaAsientos();
        listaAsientos=gestoraAsientosJAXB.getListaAsientos();
        

        
    }
    
}
