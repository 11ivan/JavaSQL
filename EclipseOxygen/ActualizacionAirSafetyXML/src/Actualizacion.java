
public class Actualizacion {

	private Incidencia incidencia;
	private Avion avion;
	
	
	//Constructores
	public Actualizacion() {
		incidencia=new Incidencia();
		avion=new Avion();
	}
	
	
	//Getters and Setters
	public Incidencia getIncidencia() {
		return incidencia;
	}

	public void setIncidencia(Incidencia incidencia) {
		this.incidencia = incidencia;
	}

	public Avion getAvion() {
		return avion;
	}

	public void setAvion(Avion avion) {
		this.avion = avion;
	}
	
	
	
	
}
