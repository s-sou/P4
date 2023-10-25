package grafica;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.util.Properties;

import logicaPersistencia.Fachada;
import logicaPersistencia.excepciones.NetworkException;
import logicaPersistencia.excepciones.PropertiesException;

public class MainServidor {

	//Atributos
 	private Fachada f;
    private String dirIP;
	private String puerto;
	private String nomProperties;
	
	//Constructor que publica la clase Fachada
		public MainServidor() {
			try {
				f = new Fachada();
			} catch (RemoteException e) {
				System.out.println("1");
				throw new NetworkException("ERROR DE RED");
			}
			try { 
				Properties p = new Properties();
				nomProperties = "config/datos.properties";
				p.load (new FileInputStream (nomProperties));
				puerto = p.getProperty("puerto");
				dirIP = p.getProperty("dirIP");
			} catch (IOException e) { 
				throw new PropertiesException("Error de acceso a datos");
			}
			try { 
				LocateRegistry.createRegistry(Integer.valueOf(puerto));
				Naming.rebind("//" + dirIP + ":" + puerto + "/Fachada", f);
				System.out.println ("PUBLICADO OK");
			} catch (RemoteException e){
				e.printStackTrace();
			} catch (MalformedURLException e) {
				e.printStackTrace(); 
			}
		}
		
		//Metodo main
		public static void main (String [] args) { 
			new MainServidor();
		}
		

}
