package leopolis2;

import generated.Asiento;
import gestoras.GestoraAsientosJAXB;
import gestoras.GestoraDeGestoras;
import java.io.File;
import java.util.ArrayList;

/**
 PSEUDOCODIGO
    INICIO
    
 
  
  
  
  
  
  
  
  
    FIN
 */

public class Leopolis2 {
    public static void main(String[] args) {
        
        GestoraDeGestoras gestoraDeGestoras=new GestoraDeGestoras();
        GestoraAsientosJAXB gestoraAsientosJAXB=new GestoraAsientosJAXB();
        File file1=new File("archivos\\Registro11.xml");
        //File file1=new File("archivos\\PruebaErrores.xml");
        ArrayList<Asiento> listaAsientos=new ArrayList<Asiento>();
        
        gestoraAsientosJAXB.abrirListaAsientosJAXB(file1);
        gestoraAsientosJAXB.cargaListaAsientos();
        listaAsientos=gestoraAsientosJAXB.getListaAsientos();
        
        gestoraDeGestoras.actualizaDB(listaAsientos);
        
        
        //TESTEOS
        /*
        Byte id=0;
        gestoraDeGestoras.getCiudadane(id);
        */
        
        
        
    }  
}
