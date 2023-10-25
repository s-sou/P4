package logicaPersistencia;

import java.io.FileInputStream;
import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import logicaPersistencia.accesoBD.AccesoBD;
import logicaPersistencia.excepciones.*;
import logicaPersistencia.excepciones.JugueteException;
import logicaPersistencia.excepciones.NinioException;
import logicaPersistencia.excepciones.PersistenciaException;
import logicaPersistencia.excepciones.PropertiesException;
import logicaPersistencia.valueObjects.VOJuguete;
import logicaPersistencia.valueObjects.VONinio;

public class Fachada extends UnicastRemoteObject implements IFachada {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Connection con = null;
	private String error;
	private String url;
	private String user;
	private String password; 
	private static Fachada instancia;
	
	private Fachada(String url, String usr, String password) throws RemoteException {
			this.url = url;
			this.user = usr;
			this.password = password;		
	};
	
	public static Fachada getIstancia() throws RemoteException, PropertiesException {
	
		if (instancia == null)
			try {
				Properties p = new Properties();
				String nomArch = "config/datos.properties";
				p.load(new FileInputStream(nomArch));
				instancia = new Fachada(p.getProperty("url"), p.getProperty("user"), p.getProperty("password"));			
			}catch(IOException e) {
				throw new PropertiesException("Error de datos");
			}
		return instancia;
	};
	
	

	//Metodo 1
	public void nuevoNinio (VONinio vo) throws PersistenciaException, NinioException,ConnectionException {
		boolean existeCed = false;
		boolean errorPersistencia = false;
		boolean errorConnection = false;
		int errorCode = 0;
		try {
			con = DriverManager.getConnection(url,user,password);
			con.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
			con.setAutoCommit(false);
			
			int ci = vo.getCedula();
			String nombre = vo.getNombre();
			String apellido = vo.getApellido();
			
			
			AccesoBD abd = new AccesoBD();
			existeCed = abd.existeNinio(con, ci);
			if(!existeCed) abd.nuevoNinio(con, ci, nombre, apellido);
			else error = "Existe ninio";
			
			con.commit();
			con.close();
			
		} catch (SQLException e1){			
			try {
				if(con != null) {
					errorPersistencia = true;
					error = "Error de persistencia";
					errorCode = e1.getErrorCode();
					con.rollback();
					con.close();
				}
			} catch (SQLException e2) {
				errorConnection = true;
				error = "Error de connection";
				errorCode = e2.getErrorCode();
			}
			
		} finally {
			if(existeCed) throw new NinioException("1000", error);
			if(errorPersistencia) throw new PersistenciaException(errorCode, error);
			if(errorConnection) throw new ConnectionException(errorCode, error);
		}
	}
	
	
	//Metodo 2
	public void nuevoJuguete (String desc, int cedN) throws PersistenciaException, NinioException,ConnectionException {
		boolean existeCed = false;
		boolean errorPersistencia = false;
		boolean errorConnection = false;
		int errorCode = 0;
		try {
			con = DriverManager.getConnection(url,user,password);
			con.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
			con.setAutoCommit(false);
			
			AccesoBD abd = new AccesoBD();
			
			int numeroJug = abd.numeroNuevoJuguete(con, cedN);
			existeCed = abd.existeNinio(con, cedN);
			if(existeCed) abd.nuevoJuguete(con, numeroJug, desc, cedN);
			else error = "Nino no registrado";
			
			con.commit();
			con.close();
			
		} catch (SQLException e1){			
			try {
				if(con != null) {
					errorPersistencia = true;
					error = "Error de persistencia";
					errorCode = e1.getErrorCode();
					con.rollback();
					con.close();
				}
			} catch (SQLException e2) {
				errorConnection = true;
				error = "Error de connection";
				errorCode = e2.getErrorCode();
			}
			
		} finally {
			if(!existeCed) throw new NinioException("1000", error);
			if(errorPersistencia) throw new PersistenciaException(errorCode, error);
			if(errorConnection) throw new ConnectionException(errorCode, error);
		}
	}
	
	//Metodo 3
	public List<VONinio> listarNinios () throws PersistenciaException,ConnectionException {
		boolean errorPersistencia = false;
		boolean errorConnection = false;
		int errorCode = 0;		
		List<VONinio> lista = new ArrayList<VONinio>();
		try {
			con = DriverManager.getConnection(url,user,password);
			con.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
			con.setAutoCommit(false);
			
			AccesoBD abd = new AccesoBD();
			lista = abd.listarNinios(con);
			
			con.commit();
			con.close();
			
		} catch (SQLException e1){			
			try {
				if(con != null) {
					errorPersistencia = true;
					error = "Error de persistencia";
					errorCode = e1.getErrorCode();
					con.rollback();
					con.close();
				}
			} catch (SQLException e2) {
				errorConnection = true;
				error = "Error de connection";
				errorCode = e2.getErrorCode();
			}
			
		} finally {
			if(errorPersistencia) throw new PersistenciaException(errorCode, error);
			if(errorConnection) throw new ConnectionException(errorCode, error);
		}
		
		return lista;
	}
	
	//Metodo 4
	public List<VOJuguete> listarJuguetes (int cedN) throws PersistenciaException, NinioException,ConnectionException {
		boolean existeCed = false;
		boolean errorPersistencia = false;
		boolean errorConnection = false;
		int errorCode = 0;		
		List<VOJuguete> lista = new ArrayList<VOJuguete>();
		try {
			con = DriverManager.getConnection(url,user,password);
			con.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
			con.setAutoCommit(false);
			
			AccesoBD abd = new AccesoBD();			
			existeCed = abd.existeNinio(con, cedN);
			if(existeCed)
				lista = abd.listarJuguetes(con);
			else 
				error = "Nino no registrado";
			
			con.commit();
			con.close();
			
		} catch (SQLException e1){			
			try {
				if(con != null) {
					errorPersistencia = true;
					error = "Error de persistencia";
					errorCode = e1.getErrorCode();
					con.rollback();
					con.close();
				}
			} catch (SQLException e2) {
				errorConnection = true;
				error = "Error de connection";
				errorCode = e2.getErrorCode();
			}
			
		} finally {
			if(!existeCed) throw new NinioException("1000", error);
			if(errorPersistencia) throw new PersistenciaException(errorCode, error);
			if(errorConnection) throw new ConnectionException(errorCode, error);
		}
		
		return lista;
	}
	
	//Metodo 5
	public String darDescripcion (int num, int cedN) throws PersistenciaException, NinioException,JugueteException,ConnectionException {
		boolean existeCed = false;
		boolean existeJug = false;
		boolean errorPersistencia = false;
		boolean errorConnection = false;
		int errorCode = 0;
		String desc = "";
		
		try {
			con = DriverManager.getConnection(url,user,password);
			con.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
			con.setAutoCommit(false);
			
			AccesoBD abd = new AccesoBD();
			
			existeCed = abd.existeNinio(con, cedN);
			existeJug = abd.existeJuguete(con, cedN, num);
			if(existeCed)
				if(existeJug)
					desc = abd.darDescripcion(con, num, cedN);
				else
					error = "No existe Juguete";
			else 
				error = "Nino no registrado";
			
			con.commit();
			con.close();
			
		} catch (SQLException e1){			
			try {
				if(con != null) {
					errorPersistencia = true;
					error = "Error de persistencia";
					errorCode = e1.getErrorCode();
					con.rollback();
					con.close();
				}
			} catch (SQLException e2) {
				errorConnection = true;
				error = "Error de connection";
				errorCode = e2.getErrorCode();
			}
			
		} finally {
			if(!existeCed) throw new NinioException("1000", error);
			if(!existeJug) throw new JugueteException("1002", error);
			if(errorPersistencia) throw new PersistenciaException(errorCode, error);
			if(errorConnection) throw new ConnectionException(errorCode, error);
		}
		
		return desc;
	}
	
	
	
	//Metodo 6
	public void borrarNinioJuguetes (int cedN) throws PersistenciaException, NinioException,ConnectionException {
		boolean existeCed = false;
		boolean errorPersistencia = false;
		boolean errorConnection = false;
		int errorCode = 0;		
		try {
			con = DriverManager.getConnection(url,user,password);
			con.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
			con.setAutoCommit(false);
			
			AccesoBD abd = new AccesoBD();
			
			existeCed = abd.existeNinio(con, cedN);
			if(existeCed) {
				abd.borrarNinioJuguete(con, cedN);
			}
			else 
				error = "Nino no registrado";
			
			con.commit();
			con.close();
			
		} catch (SQLException e1){			
			try {
				if(con != null) {
					errorPersistencia = true;
					error = "Error de persistencia";
					errorCode = e1.getErrorCode();
					con.rollback();
					con.close();
				}
			} catch (SQLException e2) {
				errorConnection = true;
				error = "Error de connection";
				errorCode = e2.getErrorCode();
			}
			
		} finally {
			if(!existeCed) throw new NinioException("1000", error);
			if(errorPersistencia) throw new PersistenciaException(errorCode, error);
			if(errorConnection) throw new ConnectionException(errorCode, error);
		}
		
	}
	
}
