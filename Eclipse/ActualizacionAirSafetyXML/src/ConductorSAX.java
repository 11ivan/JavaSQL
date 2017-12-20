



public class ConductorSAX {
public static void main (String[] args){
	String nombreArchivo = "Actualizaciones.xml";
	PruebaSAX1 probando = new PruebaSAX1 (nombreArchivo);
	try {
		probando.andale();
	}catch(Exception e) {
		throw e;
	}
    System.out.println("Perfecto");
}// Fin main

} // Fin conductorSAX
