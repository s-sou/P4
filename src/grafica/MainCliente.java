package grafica;

import grafica.ventana.VentanaInicial;

public class MainCliente {
		
	//Método main
	public static void main (String [] args) { 
			VentanaInicial ventana = new VentanaInicial();
			if (ventana.getOnline())	
				ventana.setVisible(true);
	}
	
}
