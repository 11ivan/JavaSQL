package leopolis2;

import generated.Asiento;
import generated.Madre;
import gestoras.GestoraAsientosJAXB;
import gestoras.GestoraDeGestoras;
import gestoras.GestoraNacimientos;
import java.io.File;
import java.util.ArrayList;
import javax.xml.bind.JAXBElement;

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
        ArrayList<Asiento> listaAsientos=new ArrayList<Asiento>();
        
        gestoraAsientosJAXB.abrirListaAsientosJAXB(file1);
        gestoraAsientosJAXB.cargaListaAsientos();
        listaAsientos=gestoraAsientosJAXB.getListaAsientos();
        
        gestoraDeGestoras.actualizaDB(listaAsientos);
        
        
        //TESTEOS
        //Byte id=0;
        //gestoraDeGestoras.getCiudadane(id);
        
        //GestoraNacimientos gestoraNacimientos = new GestoraNacimientos();
        //System.out.println(gestoraNacimientos.compruebaPastaFavoritaExistente("Canelone"));
        /*Madre madre = (Madre) listaAsientos.get(1).getCiudadane().get(0).getContent().get(1);
        String mamiblue = madre.getID().toString();*/
    }  
}
