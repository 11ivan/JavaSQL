import java.util.Calendar;

/*
 * Propiedades Básicas:
 * 		Matricula: Cadena, Consultable, Modificable
 * 		Nombre: Cadena, Consultable, Modificable
 * 		idFabricante: short, Consultable, Modificable
 * 		Modelo: Cadena, Consultable, Modificable
 * 		Filas: short, Consultable, Modificable
 * 		Asientos por fila: short 
 * 		Autonomia: entero, Consultable, Modificable
 * 		Activo: Booleano, Consultable, Modificable
 * 
 * 
 * Propiedades Agregadas:
 * 		Fecha Fabricacion: java.sql.Date, Consultable, Modificable
 * 		Fecha Entrada: java.sql.Date, Consultable, Modificable
 * 
 *
*/

public class Avion {
	
	//Propiedades
	private String matricula;
	private String nombre;
	private short idFabricante;
	private String modelo; 
	private java.sql.Date fechaFabricacion;
	private java.sql.Date fechaEntrada;
	private Integer filas;
	private Integer asientosXFila;
	private Integer autonomia;
	private String activo;
	
	//Contructores
	public Avion() {
		matricula="";
		nombre="";
		idFabricante=-1;
		modelo="";
		fechaFabricacion=new java.sql.Date(Calendar.getInstance().getTimeInMillis());
		fechaEntrada=new java.sql.Date(Calendar.getInstance().getTimeInMillis());
		filas=0;
		asientosXFila=0;
		autonomia=0;
		activo="";		
	}
	
	public Avion(String matricula, String nombre, short idFabricante, String modelo, java.sql.Date fechaFabricacion,
			     java.sql.Date fechaEntrada, Integer filas, Integer asientosXFila, Integer autonomia, String activo)
	{
		this.matricula=matricula;
		this.nombre=nombre;
		this.idFabricante=idFabricante;
		this.modelo=modelo;
		this.fechaFabricacion=fechaFabricacion;
		this.fechaEntrada=fechaEntrada;
		this.filas=filas;
		this.asientosXFila=asientosXFila;
		this.autonomia=autonomia;
		this.activo=activo;
	}

	
	//Getters and Setters
	public String getMatricula() {
		return matricula;
	}


	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public short getIdFabricante() {
		return idFabricante;
	}


	public void setIdFabricante(short idFabricante) {
		this.idFabricante = idFabricante;
	}


	public String getModelo() {
		return modelo;
	}


	public void setModelo(String modelo) {
		this.modelo = modelo;
	}


	public java.sql.Date getFechaFabricacion() {
		return fechaFabricacion;
	}


	public void setFechaFabricacion(java.sql.Date fechaFabricacion) {
		this.fechaFabricacion = fechaFabricacion;
	}


	public java.sql.Date getFechaEntrada() {
		return fechaEntrada;
	}


	public void setFechaEntrada(java.sql.Date fechaEntrada) {
		this.fechaEntrada = fechaEntrada;
	}


	public Integer getFilas() {
		return filas;
	}


	public void setFilas(Integer filas) {
		this.filas = filas;
	}


	public Integer getAsientosXFila() {
		return asientosXFila;
	}


	public void setAsientosXFila(Integer asientosXFila) {
		this.asientosXFila = asientosXFila;
	}


	public Integer getAutonomia() {
		return autonomia;
	}


	public void setAutonomia(Integer autonomia) {
		this.autonomia = autonomia;
	}


	public String getActivo() {
		return activo;
	}


	public void setActivo(String activo) {
		this.activo = activo;
	}
	
	
	
}
