package logicaPersistencia.excepciones;

public class NetworkException extends Exception {
	private static final long serialVersionUID = 1L;
	
	public NetworkException (String mensaje)
	{
		super(mensaje);
	}
	
}
