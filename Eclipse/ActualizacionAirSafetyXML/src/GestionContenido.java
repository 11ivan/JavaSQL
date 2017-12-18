import org.xml.sax.helpers.*;
import java.util.ArrayList;
import org.xml.sax.*;


public class GestionContenido extends DefaultHandler {

	
	private Actualizacion actualizacion;
	private String contenidoEtiqueta;
	
	
    public GestionContenido() {
        super();
        actualizacion=new Actualizacion();
        contenidoEtiqueta="";
    }

    /*public GestionContenido() {
		super();
		//this.listaLibros =listaLibros;
		//book=new Book();
		//etiquetaLeida="";
	}*/

	@Override
    public void startDocument(){
        System.out.println("Comienzo del documento XML");
    }
	
    @Override
    public void endDocument(){
        System.out.println("Fin del documento XML");
    }
    
    @Override
    public void startElement(String uri, String nombre, String nombreC, Attributes att){
		//Si el nombre de la etiqueta es Actualizacion 
    	if(nombre.equals("NombreAvion")) {
    		
    	}
    }
    
    @Override
    public void endElement(String uri, String nombre, String nombreC){
    	//Si la etiqueta de cierre es Actualizacion enviamos el objeto Actualizacion para su gestion
    	
    	switch (nombre) {
		case "MatriculaAvion":
			actualizacion.getIncidencia().setMatriculaAvion(contenidoEtiqueta);
			break;

		default:
			break;
		}
    }
    
    @Override
    public void characters (char[] ch, int inicio, int longitud)throws SAXException{
        String cad = new String(ch, inicio, longitud);
        cad = cad.replaceAll("[\t\n\r]",""); // Quitamos tabuladores y saltos de linea
        //
        contenidoEtiqueta = cad;
        
    }
    
    
    
    
}
// FIN GestionContenido
