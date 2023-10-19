package logicaPersistencia.excepciones;

	public class JugueteException extends RuntimeException {
		private static final long serialVersionUID = 1L;
		private String codigo;
		
		public JugueteException (String codigo, String mensaje)
		{
			super(mensaje);
			this.codigo = codigo;
		}
		
		public String getCodigo ()
		{
			return codigo;
		}
	}
