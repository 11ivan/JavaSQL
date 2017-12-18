
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author icastillo
 * 
 * 
 */

public class GestoraConexion {
    
    private Connection conexionBaseDatos;
    
    /*
	 Proposito: Abre la conexion con la base de datos
	 Prototipo: void connect()
	 Precondiciones: no hay
	 Entradas: no hay
	 Salidas: no hay 
	 Postcondiciones: Se ha abierto la conexión con la base de datos
	 */	
	public void connect(){
            try {			
                   conexionBaseDatos = DriverManager.getConnection("jdbc:sqlserver://localhost", "manolito", "1234");
            } catch (SQLException e) {
                    e.printStackTrace();
            }			
	}
        
        
    public void close(){
        try {
            conexionBaseDatos.close();
        } catch (SQLException ex) {
            //Logger.getLogger(GestoraConexion.class.getName()).log(Level.SEVERE, null, ex);
            //ex.printStackTrace();
            System.out.println(ex.getMessage());
        }
    }
    
    public Connection getConnect(){
        return conexionBaseDatos;
    }
    
}
