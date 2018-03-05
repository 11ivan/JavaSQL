package leopolis2;

import generated.Asiento;
import generated.Madre;
import gestoras.GestoraAsientosJAXB;
import gestoras.GestoraDeGestoras;
import gestoras.GestoraNacimientos;
import java.io.File;
import java.util.ArrayList;
import javax.xml.bind.JAXBElement;

public class Leopolis2 {
    public static void main(String[] args) {
        
        GestoraDeGestoras gestoraDeGestoras=new GestoraDeGestoras();
        GestoraAsientosJAXB gestoraAsientosJAXB=new GestoraAsientosJAXB();
        File file1=new File("archivos\\Registro11.xml");
        ArrayList<Asiento> listaAsientos=new ArrayList<Asiento>();
        
        //Leemos el fichero XML
        gestoraAsientosJAXB.abrirListaAsientosJAXB(file1);
        //Cargamos la lista de Asientos leida
        gestoraAsientosJAXB.cargaListaAsientos();
        //Asignamos a un ArrayList
        listaAsientos=gestoraAsientosJAXB.getListaAsientos();
        //Llamada al método actualizaDB de la gestora de gestoras
        gestoraDeGestoras.actualizaDB(listaAsientos);        
     
    }  
}
