package logicaPersistencia.excepciones;

	public class NinioException extends Exception {
		private static final long serialVersionUID = 1L;
		private String codigo;
		
		public NinioException (String codigo, String mensaje)
		{
			super(mensaje);
			this.codigo = codigo;
		}
		
		public String getCodigo ()
		{
			return codigo;
		}
	}

