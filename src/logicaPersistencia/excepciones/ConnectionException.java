package logicaPersistencia.excepciones;

public class ConnectionException extends Exception {
	private static final long serialVersionUID = 1L;
	private int codigo;
	
	public ConnectionException (int codigo, String mensaje)
	{
		super(mensaje);
		this.codigo = codigo;
	}
	
	public int getCodigo ()
	{
		return codigo;
	}
}
