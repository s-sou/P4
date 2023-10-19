package logicaPersistencia.excepciones;

	public class PersistenciaException extends RuntimeException {

		private static final long serialVersionUID = 1L;
		private int codigo;
		
		public PersistenciaException (int codigo, String mensaje)
		{
			super(mensaje);
			this.codigo = codigo;
		}
		
		public int getCodigo ()
		{
			return codigo;
		}
}
