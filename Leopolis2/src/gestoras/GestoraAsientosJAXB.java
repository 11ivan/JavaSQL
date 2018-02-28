/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestoras;

import generated.Anotaciones;
import generated.Asiento;
import java.io.File;
import java.util.ArrayList;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

/**
 *
 * @author icastillo
 */
public class GestoraAsientosJAXB {
    
    private Anotaciones anotaciones;
    private ArrayList<Asiento> listaAsientos;
    
    public void abrirListaAsientosJAXB (File archivoXML){
        JAXBContext contexto;
        try {
            contexto = JAXBContext.newInstance(Anotaciones.class);
            Unmarshaller u = contexto.createUnmarshaller();
            anotaciones = (Anotaciones) u.unmarshal(archivoXML);
        }catch (JAXBException ex){
            //System.out.println(ex.getMessage());
            ex.printStackTrace();
        }
    }
    
    public void cargaListaAsientos(){
        listaAsientos = new ArrayList<Asiento>(anotaciones.getAsiento());
    }
    
    public ArrayList<Asiento> getListaAsientos(){
        return listaAsientos;
    }
    
}
