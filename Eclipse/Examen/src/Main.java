
/**
 *
 * @author icastillo
 *  
 */


public class Main {
    public static void main(String[] args){
    	
        GestoraConsultas gestoraConsultas=new GestoraConsultas();
        Integer[] tablaFilasAfectadas;
        GestoraMain gestoraMain=new GestoraMain();        
        
        try{
        	//Conectamos con la base de datos
            gestoraConsultas.getGestoraConexion().connect();//Opto por usar la propiedad GestoraConexion para no tener que abrir la conexion m�s de una vez  
            
            //Preparamos las sentencias a ejecutar
            gestoraConsultas.preparaSentencias();
            
            //Cargamos el ResultSet Actualizable necesario
            gestoraConsultas.cargaResultSetActualizable();
            
            //Actualizamos la base de datos
        	tablaFilasAfectadas=gestoraMain.actualizarDB(gestoraConsultas);
            
            //Cerramos la conexion con la base de datos
            gestoraConsultas.getGestoraConexion().close();
            
            //Mostrar filas afectadas
            System.out.println("Se ha insertado "+tablaFilasAfectadas[0]+" avion/es");
            System.out.println("Se ha insertado "+tablaFilasAfectadas[1]+" incidencia/s");
            System.out.println("Se di� de baja "+tablaFilasAfectadas[2]+" avion/es");
            
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
}
