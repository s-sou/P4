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
	private JTextField textField;
	private JTextField txtIdNio;
	
	public VentanaInicial() {
		controller = new ControladorVentanaInicial(this);
		setVisible(false);
		online=false;
		
		
		
		frame = new JFrame();
		frame.setBounds(100, 100, 590, 551);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(60, 83, 150, 39);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		txtIdNio = new JTextField();
		txtIdNio.setHorizontalAlignment(SwingConstants.CENTER);
		txtIdNio.setText("ID NIÑO");
		txtIdNio.setBounds(60, 34, 150, 39);
		frame.getContentPane().add(txtIdNio);
		txtIdNio.setColumns(10);
		
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
