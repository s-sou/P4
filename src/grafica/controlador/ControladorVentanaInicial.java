package grafica.controlador;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Properties;

import grafica.ventana.VentanaInicial;
import logicaPersistencia.IFachada;

public class ControladorVentanaInicial {
	private VentanaInicial ventana;
	private IFachada f;
	

	//Metodo constructor
    public ControladorVentanaInicial(VentanaInicial ventana) {
        this.ventana = ventana;
        try {
        	Properties p = new Properties();
			String nomProperties = "config/datos.properties";
			p.load (new FileInputStream (nomProperties));
			String puerto = p.getProperty("puerto");
			String dirIP = p.getProperty("ip");
        	f = (IFachada)Naming.lookup("//" + dirIP + ":" + puerto + "/Fachada");
        	ventana.setOnline(true);
        }
        catch (MalformedURLException e1) {
        	ventana.mostrarMensaje("ERROR URL MALFORMADA!!");
        }
        catch (RemoteException e1) {
        	ventana.mostrarMensaje("ERROR DE RED!!");
        }
        catch (NotBoundException e1) {
        	ventana.mostrarMensaje("ERROR CONEXION REMOTA!!");
        } 
        catch (IOException e) { 
        	e.printStackTrace();
        }
    }
}

