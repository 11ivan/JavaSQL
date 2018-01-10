import org.xml.sax.helpers.*;
import java.util.ArrayList;
import org.xml.sax.*;


public class GestionContenido extends DefaultHandler {

	//private ArrayList<Book> listaLibros;
	//private Book book;
	//private String etiquetaLeida;
	
    public GestionContenido() {
        super();
    }

    public GestionContenido(ArrayList<> listaLibros) {
		super();
		//this.listaLibros =listaLibros;
		//book=new Book();
		//etiquetaLeida="";
	}

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

    	
    }
    
    @Override
    public void endElement(String uri, String nombre, String nombreC){
    	
    }
    
    @Override
    public void characters (char[] ch, int inicio, int longitud)throws SAXException{
        String cad = new String(ch, inicio, longitud);
        cad = cad.replaceAll("[\t\n\r]",""); // Quitamos tabuladores y saltos de linea
        
        
    }
    
    
    
    
}
// FIN GestionContenido
