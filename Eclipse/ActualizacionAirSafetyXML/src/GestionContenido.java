import org.xml.sax.helpers.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import org.xml.sax.*;


public class GestionContenido extends DefaultHandler {

	
	private Actualizacion actualizacion;
	private String contenidoEtiqueta;
	private GestoraConsultas gestoraConsultas;
	private GestoraActualizacion gestoraActualizacion;
	
	
    public GestionContenido() {
        super();
        actualizacion=new Actualizacion();
        contenidoEtiqueta="";
        gestoraConsultas=new GestoraConsultas();
        gestoraActualizacion=new GestoraActualizacion();
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
        try{
        	//Conectamos con la base de datos
            gestoraConsultas.getGestoraConexion().connect();//Opto por usar la propiedad GestoraConexion para no tener que abrir la conexion más de una vez  
            
            //Preparamos las sentencias a ejecutar
            gestoraConsultas.preparaSentencias();
            
            //Cargamos el ResultSet Actualizable necesario
            gestoraConsultas.cargaResultSetActualizable();                                
            
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
	
    @Override
    public void endDocument(){
        System.out.println("Fin del documento XML");
        
        //Cerramos la conexion con la base de datos
        gestoraConsultas.getGestoraConexion().close();
    }
    
    @Override
    public void startElement(String uri, String nombre, String nombreC, Attributes att){
		//Si el nombre de la etiqueta es Actualizacion 
    	if(nombre.equals("Actualizacion")) {
    		actualizacion=new Actualizacion();
    	}
    }
    
    @Override
    public void endElement(String uri, String nombre, String nombreC){
    	//Si la etiqueta de cierre es Actualizacion enviamos el objeto Actualizacion para su gestion
    	SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
    	java.util.Date parsed=null;
    	
    	switch (nombre) {
		case "MatriculaAvion":
			actualizacion.getIncidencia().setMatriculaAvion(contenidoEtiqueta);
			actualizacion.getAvion().setMatricula(contenidoEtiqueta);
			break;
			
		case "Latitud":
			actualizacion.getIncidencia().setLatitud(new java.math.BigDecimal(contenidoEtiqueta));
			break;
			
		case "Longitud":
			actualizacion.getIncidencia().setLongitud(new java.math.BigDecimal(contenidoEtiqueta));
			break;
			
		case "Descripcion":
			actualizacion.getIncidencia().setDescripcion(contenidoEtiqueta);
			break;
			
		case "Tipo":
			actualizacion.getIncidencia().setTipo(contenidoEtiqueta);
			break;
			
		case "AccidenteDefinitivo":
			if(contenidoEtiqueta.equals("1")) {
				actualizacion.getAvion().setActivo("N");
			}else {
				actualizacion.getAvion().setActivo("S");
			}			
			break;
			
		case "NombreAvion":
			actualizacion.getAvion().setNombre(contenidoEtiqueta);
			break;
			
		case "Fabricante":
			actualizacion.getAvion().setNombreFabricante(contenidoEtiqueta);
			break;
			
		case "Modelo":
			actualizacion.getAvion().setModelo(contenidoEtiqueta);
			break;
			
		case "Fecha_Fabricacion":
			try {
				parsed=dateFormat.parse(contenidoEtiqueta);
				actualizacion.getAvion().setFechaFabricacion(new java.sql.Date(parsed.getTime()));
			} catch (ParseException e) {				
				e.printStackTrace();
			}
			break;
			
		case "Fecha_Entrada":
			try {
				parsed=dateFormat.parse(contenidoEtiqueta);
				actualizacion.getAvion().setFechaEntrada(new java.sql.Date(parsed.getTime()));
			} catch (ParseException e) {				
				e.printStackTrace();
			}
			break;
			
		case "Filas":
			actualizacion.getAvion().setFilas(Integer.parseInt(contenidoEtiqueta));
			break;	
			
		case "Asientos_x005F_x_Fila":
			actualizacion.getAvion().setAsientosXFila(Integer.parseInt(contenidoEtiqueta));
			break;
		case "Autonomia":
			actualizacion.getAvion().setAutonomia(Integer.parseInt(contenidoEtiqueta));
			break;
			
		case "Actualizacion":
				gestoraActualizacion.actualizarDB(gestoraConsultas, actualizacion);
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
