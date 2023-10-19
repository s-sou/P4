package grafica.ventana;

import java.awt.EventQueue;

import javax.swing.JFrame;

public class VentanaInicial {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaInicial window = new VentanaInicial();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public VentanaInicial() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}



public void setOnline(boolean b) {
	// TODO Auto-generated method stub
	
}

public void mostrarMensaje(String string) {
	// TODO Auto-generated method stub
	
}

public boolean getOnline() {
	// TODO Auto-generated method stub
	return false;
}

public void setVisible(boolean b) {
	// TODO Auto-generated method stub
	
}

}
