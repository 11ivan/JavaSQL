import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class GestoraSentencias {

	private PreparedStatement preparedStatementSelectIdFabricante;
	private CallableStatement callableStatementExecBajaAvion;
	private PreparedStatement preparedstatementInsertInToIncidencias;
	//String cadenaSentencia="Select Matricula, Nombre, ID_Fabricante, Modelo, Fecha_Fabricacion, Fecha_Entrada, Filas, Asientos_x_Fila, Autonomia, Activo From AS_Aviones";     
	private Statement sentenciaResultSetActualizable;
	private GestoraConexion conexion;
	
	public GestoraSentencias(){
		conexion=new GestoraConexion();		
		try {
			conexion.connect();
			preparedStatementSelectIdFabricante=conexion.getConnect().prepareStatement("SELECT ID_Fabricante FROM AS_Fabricantes where Nombre=?");
			callableStatementExecBajaAvion=conexion.getConnect().prepareCall("exec BajaAvion ?");
			preparedstatementInsertInToIncidencias=conexion.getConnect().prepareStatement("insert INTO AS_Incidencias (Avion, Latitud, Longitud, Descripcion, Tipo) values( ?, ?, ?, ?, ?)");
			sentenciaResultSetActualizable=conexion.getConnect().createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE);
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

	public Statement getSentenciaResultSetActualizable() {
		return sentenciaResultSetActualizable;
	}

	
	public ResultSet getResultSetActualizable() {
		ResultSet resultSetAct=null;
		try {
			resultSetAct=sentenciaResultSetActualizable.executeQuery("Select Matricula, Nombre, ID_Fabricante, Modelo, Fecha_Fabricacion, Fecha_Entrada, Filas, Asientos_x_Fila, Autonomia, Activo From AS_Aviones");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return resultSetAct;
	}
	
	
}
