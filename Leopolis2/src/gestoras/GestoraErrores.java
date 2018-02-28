/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestoras;

import generated.Errores;
import generated.Incidencia;
import java.io.File;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

/**
 *
 * @author icastillo
 */
public class GestoraErrores {
    
    public void guardarListaErrores(File archivoXML, ArrayList<Incidencia> listaIncidencias){
        JAXBContext contexto;
        Errores errores=new Errores();
        errores.getIncidencia().addAll(listaIncidencias);
        try {
            contexto = JAXBContext.newInstance(Errores.class);
            Marshaller marshalero = contexto.createMarshaller();
            marshalero.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            StringWriter escribiente = new StringWriter();
            marshalero.marshal(errores, archivoXML);
            // ahora lo marshaleamos a un stream para visualizarlo
            marshalero.marshal(errores, escribiente);
            System.out.println("-----------------");
            System.out.println("Object2XML:");
            System.out.println(escribiente.toString());
            System.out.println("-----------------");
        } catch (JAXBException ex) {
            Logger.getLogger(GestoraErrores.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
