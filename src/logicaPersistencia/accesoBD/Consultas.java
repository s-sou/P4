package logicaPersistencia.accesoBD;

public class Consultas {
	
	public String existeNinio() {
		return "SELECT * FROM ninios WHERE cedula = ?";
	}
	
	public String existeJug() {
		return "SELECT * FROM juguetes WHERE cedulaNinio = ? AND numero = ?";
	}

	public String insertarNinio() {
		return "INSERT INTO ninios (cedula, nombre, apellido) VALUES (?,?,?)";
	}
	
	public String insertarJuguete() {
		return "INSERT INTO juguetes (numero, descripcion, cedulaNinio) VALUES (?,?,?)";
	}
	
	public String cantidadJuguetes() {
		return "SELECT COUNT(*) as cantidad FROM juguetes WHERE cedula = ?";
	}
	
	public String listaNinios() {
		return "SELECT cedula, nombre, apellido FROM ninios ORDER BY cedula";
	}
	
	public String listaJuguetes() {
		return "SELECT numero, descripcion, cedulaNinio FROM juguetes WHERE cedulaNninio = ? ORDER BY numero"; 
	}
	
	public String darDescripcion() {
		return "SELECT descripcion FROM juguetes WHERE numero = ? AND cedulaNninio = ?"; 
	}
	
	public String borrarJuguetesNinio() {
		return "DELETE FROM juguetes WHERE cedulaNinio = ?";
	}
	
	public String borrarNinio() {
		return "DELETE FROM ninios WHERE cedula = ?";
	}
}
