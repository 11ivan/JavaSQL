


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
    		//System.out.println("\t< "+nombre +" category="+att.getValue(0)+">");
    		this.book=new Book();
    		//this.book.setTitle(nombre);
    		this.book.setCategory(att.getValue(0));
    	}else if(nombre.equals("title")){
    		//System.out.println("\t< "+nombre +">");
    		this.book.setTitle(nombre);
    		this.book.setLanguage(att.getValue(0));
        }       
    	this.etiquetaLeida=nombre;
    }
    
    @Override
    public void endElement(String uri, String nombre, String nombreC){
    	if(nombre.equals("book")) {
    		this.listaLibros.add(this.book);
    		System.out.println(book.toString());
    	}
        //System.out.println("\t</ "+nombre +">");
    }
    
    @Override
    public void characters (char[] ch, int inicio, int longitud)throws SAXException{
        String cad = new String(ch, inicio, longitud);
        cad = cad.replaceAll("[\t\n\r]",""); // Quitamos tabuladores y saltos de linea
        //System.out.println("\t\t" + cad);
        if(cad.replaceAll(" ", "").length()>0) {
	        switch(this.etiquetaLeida) {
	        	case "author":	        		
	        			this.book.getAuthors().add(cad);	        		
	        		break;
	        		
	        	case "year":       				        			
        				this.book.setYear(Integer.parseInt(cad));	        				        			
	        		break;
	        		
	        	case "price":	    
	        			this.book.setPrice(Double.parseDouble(cad));		        		
	        		break;
	        }
        }//fin si
    }
    
    
    
    
}
// FIN GestionContenido
