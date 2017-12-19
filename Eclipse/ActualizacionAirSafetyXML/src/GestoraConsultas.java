
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author icastillo
 * 
 * 
 */
public class GestoraConsultas {
	
    private GestoraConexion conexion;
    private GestoraSentencias gestoraSentencias;
    private ResultSet resultSetAct;
    
    public GestoraConsultas(){
        conexion=new GestoraConexion();
        gestoraSentencias=new GestoraSentencias();
        //gestoraSentencias.preparaSentencias();//si estos dos metodos los modulo quizás pueda abrir la conexión una única vez sin necesidad de hacerlo en GestoraSentencias
        //resultSetAct=gestoraSentencias.getResultSetActualizable();
    }
    
    public GestoraConexion getGestoraConexion(){
        return conexion;
    }
    
    public GestoraSentencias getGestoraSentencias(){
    	return gestoraSentencias;
    }
    
    
    /*
    Proposito: Prepara las sentencias de la GestoraSentencias
    Precondiciones: La conexion debe estar abierta
    Entradas: No hay
    Salidas: No hay
    Postcondiciones: Se han preparado las sentencias de la GestoraSentencias
    */
    public void preparaSentencias(){
    	gestoraSentencias.preparaSentencias(conexion);
    }
    
    /*
    Proposito: Carga la propiedad resultSetAct con un ResultSet Actualizable
    Precondiciones: No hay
    Entradas: No hay
    Salidas: No hay
    Postcondiciones: Se ha cargado en la propiedad resultSetAct el contenido de la tabla AS_Aviones
    */
    public void cargaResultSetActualizable(){
    	resultSetAct=gestoraSentencias.getResultSetActualizable();
    }
    
    /*
    Proposito: Carga en un ResultSet en contenido de la tabla EX_Actualizaciones
    Precondiciones: No hay
    Entradas: No hay
    Salidas: Un ResultSet con el resultado de la consulta a EX_Actualizaciones
    Postcondiciones: Se ha cargado en un ResultSet el contenido de la tabla EX_Actualizaciones
    */
    public ResultSet getResulSetActualizaciones(){     
        String consulta="SELECT MatriculaAvion, Latitud, Longitud, Descripcion, Tipo, AccidenteDefinitivo,"
        				+ "NombreAvion, Fabricante, Modelo, Fecha_Fabricacion, Fecha_Entrada, Filas, "
        				+ "Asientos_x_Fila, Autonomia FROM EX_Actualizaciones";	
        Statement sentencia;
        ResultSet resultSet=null;
        
        try {
            sentencia=conexion.getConnect().createStatement();
            resultSet=sentencia.executeQuery(consulta);                                          
        } catch (SQLException e) {
        	System.out.println(e.getMessage());        
        }      
        return resultSet;
    }
        
    
    /*
    Proposito: Realiza una insercion en la tabla AS_Aviones mediante un resultSet actualizable
    Precondiciones: No hay
    Entradas: Un objeto Avion
    Salidas: Un entero con la cantidad de filas afectadas
    Postcondiciones: Se ha insertado en la Tabla AS_Aviones el nuevo Avion
    */
    public int insertInToAviones(Avion avion){
        int filasAfectadas=0;
        try{
        	//conexion.getConnect().setAutoCommit(false);
        	if(this.resultSetAct.next()){        				
        		
        		//Posicionamos el cursor en la fila de inserción
        		this.resultSetAct.moveToInsertRow();
        		
        		//Cambiamos los datos para esa fila
        		this.resultSetAct.updateString("Matricula", avion.getMatricula());
        		this.resultSetAct.updateString("Nombre", avion.getNombre());
        		this.resultSetAct.updateShort("ID_Fabricante", getIdFabricante(avion.getNombreFabricante()));
        		this.resultSetAct.updateString("Modelo", avion.getModelo());
        		this.resultSetAct.updateDate("Fecha_Fabricacion", avion.getFechaFabricacion());        		
        		this.resultSetAct.updateDate("Fecha_Entrada", avion.getFechaEntrada());
        		this.resultSetAct.updateInt("Filas", avion.getFilas());
        		this.resultSetAct.updateInt("Asientos_x_Fila", avion.getAsientosXFila());
        		this.resultSetAct.updateInt("Autonomia", avion.getAutonomia());
        		this.resultSetAct.updateString("Activo", avion.getActivo());

        		//Y la insertamos
        		this.resultSetAct.insertRow();
        		filasAfectadas=1;
        		conexion.getConnect().commit();
        	}
        }catch(SQLException e){
        	System.out.println(e.getMessage());
        	//Si algo falla hacemos rollback
        	try {
				conexion.getConnect().rollback();
			} catch (SQLException e1) {
				System.out.println(e1.getMessage());
			}
        }
        return filasAfectadas;
    }

    
    /*
    Proposito: Dado un nombre de fabricante busca el id del mismo en la base de datos y lo devuelve
    Precondiciones: No hay
    Entradas: String nombre del fabricante
    Salidas: Un short que es el id del fabricante
    Postcondiciones: El entero es el id del fabricante
    */
    public short getIdFabricante(String nombre) {
    	short id=0;
        ResultSet resultSet=null;      
        try {
        	gestoraSentencias.getPreparedStatementSelectIdFabricante().setString(1, nombre);
            resultSet=gestoraSentencias.getPreparedStatementSelectIdFabricante().executeQuery();    
            if(resultSet.next()) {
            	id=resultSet.getShort("ID_Fabricante");
            }
        } catch (SQLException e) {
        	System.out.println(e.getMessage());     
        }          	    	
    	return id;
    }   
       
    /*
    Proposito: Dada un matricula de avion marca a éste como no activo en la base de datos
    Precondiciones: No hay
    Entradas: String matricula del avion
    Salidas: Un entero
    Postcondiciones: Se ha marcado el avion como no activo en la base de datos y se eliminan los futuros 
    			     vuelos asociados. 
    			     El entero que será 1 si la baja se realizó correctamente y 0 sino
    */
    public int executeBajaAvion(String matricula) {
    	int bajaCorrecta=0;
    	try {			
			gestoraSentencias.getCallableStatementExecBajaAvion().setString(1, matricula);		
			gestoraSentencias.getCallableStatementExecBajaAvion().executeUpdate();	
			bajaCorrecta=1;
			conexion.getConnect().commit();//No estoy seguro de si al ejecutar el procedimiento almacenado debería hacer commit aquí
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			//Si algo falla hacemos rollback
        	try {
				conexion.getConnect().rollback();
			} catch (SQLException e1) {
				System.out.println(e1.getMessage());
			}
        }
    	return bajaCorrecta;
    }
        
    /*
    Proposito: Realiza una insercion en la table AS_Incidencias 
    Precondiciones: No hay
    Entradas: Un objeto Incidencia
    Salidas: Un entero con la cantidad de filas afectadas
    Postcondiciones: Se ha insertado en la Tabla AS_Incidencias la nueva Incidencia
    */
    public int insertInToIncidencias(Incidencia incidencia){
        int filasAfectadas=0;
        try{              	
            gestoraSentencias.getPreparedstatementInsertInToIncidencias().setString(1, incidencia.getMatriculaAvion());
            gestoraSentencias.getPreparedstatementInsertInToIncidencias().setBigDecimal(2, incidencia.getLatitud());
            gestoraSentencias.getPreparedstatementInsertInToIncidencias().setBigDecimal(3, incidencia.getLongitud());
            gestoraSentencias.getPreparedstatementInsertInToIncidencias().setString(4, incidencia.getDescripcion());
            gestoraSentencias.getPreparedstatementInsertInToIncidencias().setString(5, incidencia.getTipo());
            
            filasAfectadas=gestoraSentencias.getPreparedstatementInsertInToIncidencias().executeUpdate();
            conexion.getConnect().commit();
        }catch(SQLException e){      	
        	System.out.println(e.getMessage());
        	//Si algo falla hacemos rollback
        	try {
				conexion.getConnect().rollback();
			} catch (SQLException e1) {
				System.out.println(e1.getMessage());
			}
        }
        return filasAfectadas;
    }
  
    /*
    Proposito: Elimina el contenido de la table EX_Actualizaciones
    Precondiciones: No hay
    Entradas: no hay
    Salidas: no hay
    Postcondiciones: Se ha eliminado el contenido de EX_Actualizaciones
    */
    public void deleteActualizaciones(){     
        String delete="DELETE FROM EX_Actualizaciones";
        Statement sentencia;
        
        try {
            sentencia=conexion.getConnect().createStatement();
            sentencia.executeUpdate(delete);   
            conexion.getConnect().commit();
        } catch (SQLException e) {
        	System.out.println(e.getMessage());   
        	//Si algo falla hacemos rollback
        	try {
				conexion.getConnect().rollback();
			} catch (SQLException e1) {
				System.out.println(e1.getMessage());
			}
        }
    }


    
    
    
    
}
