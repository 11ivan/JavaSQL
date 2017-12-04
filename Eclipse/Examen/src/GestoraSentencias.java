import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class GestoraSentencias {

	private PreparedStatement preparedStatementSelectIdFabricante;
	private CallableStatement callableStatementExecBajaAvion;
	private PreparedStatement preparedstatementInsertInToIncidencias;
	private GestoraConexion conexion;
	
	public GestoraSentencias(){
		conexion=new GestoraConexion();		
		try {
			conexion.connect();
			preparedStatementSelectIdFabricante=conexion.getConnect().prepareStatement("SELECT ID_Fabricante FROM AS_Fabricantes where Nombre=?");
			callableStatementExecBajaAvion=conexion.getConnect().prepareCall("exec BajaAvion ?");
			preparedstatementInsertInToIncidencias=conexion.getConnect().prepareStatement("insert INTO AS_Incidencias (Avion, Latitud, Longitud, Descripcion, Tipo) values( ?, ?, ?, ?, ?)");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
	}

	public PreparedStatement getPreparedStatementSelectIdFabricante() {
		return preparedStatementSelectIdFabricante;
	}


	public CallableStatement getCallableStatementExecBajaAvion() {
		return callableStatementExecBajaAvion;
	}


	public PreparedStatement getPreparedstatementInsertInToIncidencias() {
		return preparedstatementInsertInToIncidencias;
	}

	
	
	
	
}
