package logicaPersistencia.valueObjects;

public class VOJuguete {
	private int numero;
	private String descripcion;
	private int cedulaNinio;
	
	public VOJuguete (int numero, String descripcion, int cedulaNinio ) {		
		this.numero = numero;
		this.descripcion = descripcion;
		this.cedulaNinio = cedulaNinio;
	}

	public int getNumero() {
		return numero;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public int getCedulaNinio() {
		return cedulaNinio;
	}

	

}