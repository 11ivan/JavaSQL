


/**
 *
 * @author Leo
 */
import org.xml.sax.helpers.*;

import java.util.ArrayList;

import org.xml.sax.*;
public class GestionContenido extends DefaultHandler {

	private ArrayList<Book> listaLibros;
	private Book book;
	private String etiquetaLeida;
	
    public GestionContenido() {
        super();
    }

    public GestionContenido(ArrayList<Book> listaLibros) {
		super();
		this.listaLibros =listaLibros;
		book=new Book();
		etiquetaLeida="";
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
    	if(nombre.equals("book")) {
    		System.out.println("\t< "+nombre +" category="+att.getValue(0)+">");
    	}else {
    		System.out.println("\t< "+nombre +">");
        }
        
    }
    @Override
    public void endElement(String uri, String nombre, String nombreC){
        System.out.println("\t</ "+nombre +">");
    }
    @Override
    public void characters (char[] ch, int inicio, int longitud)throws SAXException{
        String cad = new String(ch, inicio, longitud);
        cad = cad.replaceAll("[\t\n]",""); // Quitamos tabuladores y saltos de linea
        System.out.println("\t\t" + cad);
    }
}
// FIN GestionContenido
