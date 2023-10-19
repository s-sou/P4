package logicaPersistencia;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import logicaPersistencia.excepciones.*;
import logicaPersistencia.excepciones.JugueteException;
import logicaPersistencia.excepciones.NinioException;
import logicaPersistencia.excepciones.PersistenciaException;
import logicaPersistencia.valueObjects.VOJuguete;
import logicaPersistencia.valueObjects.VONinio;

public interface IFachada extends Remote{
	
	//Metodo 1
	public void nuevoNinio (VONinio vo) throws RemoteException, PersistenciaException, NinioException,ConnectionException;

	//Metodo 2
	public void nuevoJuguete (String desc, int cedN) throws RemoteException, PersistenciaException, NinioException,ConnectionException;

	//Metodo 3
	public List<VONinio> listarNinios () throws RemoteException, PersistenciaException,ConnectionException;

	//Metodo 4
	public List<VOJuguete> listarJuguetes (int cedN) throws RemoteException, PersistenciaException, NinioException,ConnectionException;

	//Metodo 5
	public String darDescripcion (int num, int cedN) throws RemoteException, PersistenciaException, NinioException,JugueteException,ConnectionException;
	
	//Metodo 6
	public void borrarNinioJuguetes (int cedN) throws RemoteException, PersistenciaException, NinioException,ConnectionException;
			
}
