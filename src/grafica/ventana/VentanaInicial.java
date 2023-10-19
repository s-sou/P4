package grafica.ventana;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.rmi.RemoteException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.ude.grafica.controladores.ControladorCliente;

import grafica.controlador.ControladorVentanaInicial;

public class VentanaInicial extends JFrame {

	private boolean online;
	private ControladorVentanaInicial controller;
	
	private JFrame frame;
	
	public VentanaInicial() {
		controller = new ControladorVentanaInicial(this);
		setVisible(false);
		online=false;
		
		
		
		frame = new JFrame();
		frame.setBounds(100, 100, 590, 551);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 55, 554, 263);
		frame.getContentPane().add(scrollPane);
		
		frame.setVisible(true);
		System.out.println("finalizo el constructor de ventana");
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
