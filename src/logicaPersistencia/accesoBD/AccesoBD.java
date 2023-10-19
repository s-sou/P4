package logicaPersistencia.accesoBD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import logicaPersistencia.excepciones.PersistenciaException;
import logicaPersistencia.valueObjects.VOJuguete;
import logicaPersistencia.valueObjects.VONinio;

public class AccesoBD {
	
	public boolean existeNinio (Connection con, int ced){
		boolean existe = false;
		try{
			Consultas consulta = new Consultas();
			String query = consulta.existeNinio();
			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.setInt(1, ced);
			ResultSet rs = pstmt.executeQuery();
			existe = rs.next();
			rs.close();
			pstmt.close();
		}catch (SQLException e) {
			throw new PersistenciaException (e.getErrorCode(),"Error de datos");
		}
		return existe;
	}
	
	
	public boolean existeJuguete (Connection con, int ced, int num){
		boolean existe = false;
		try{
			Consultas consulta = new Consultas();
			String query = consulta.existeJug();
			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.setInt(1, ced);
			pstmt.setInt(2, num);
			ResultSet rs = pstmt.executeQuery();
			existe = rs.next();
			rs.close();
			pstmt.close();
		}catch (SQLException e) {
			throw new PersistenciaException (e.getErrorCode(),"Error de datos");
		}
		return existe;
	}
		
	
	public void nuevoNinio (Connection con, int ced, String nom, String ape) {
		try {
			Consultas consulta = new Consultas();
			String query = consulta.insertarNinio();
			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.setInt(1, ced);
			pstmt.setString(2, nom);
			pstmt.setString(3, ape);
			pstmt.executeUpdate();
			pstmt.close();
		}catch (SQLException e) {
			throw new PersistenciaException (e.getErrorCode(),"Error de datos");
		}
	}
	
	
	public void nuevoJuguete (Connection con, int num, String desc, int ced) {
		try {
			Consultas consulta = new Consultas();
			String query = consulta.insertarJuguete();
			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.setInt(1, num);
			pstmt.setString(2, desc);
			pstmt.setInt(3, ced);
			pstmt.executeUpdate();
			pstmt.close();
		}catch (SQLException e) {
			throw new PersistenciaException (e.getErrorCode(),"Error de datos");
		}
	}
	
	public int numeroNuevoJuguete (Connection con, int ced) {
		try {
			Consultas consulta = new Consultas();
			String query = consulta.cantidadJuguetes();
			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.setInt(1, ced);
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			int resu = rs.getInt("cantidad");
			rs.close();
			pstmt.close(); 
			return resu + 1;
		}catch (SQLException e) {
			throw new PersistenciaException (e.getErrorCode(),"Error de datos");
		}
	}
	
	public List<VONinio> listarNinios (Connection con){
		List<VONinio> lista = new ArrayList<VONinio>();
		int ced=0;
		String nom, ap;
		try{
			Consultas consulta = new Consultas();
			String query = consulta.listaNinios();
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				ced = rs.getInt("cedula");
				nom = rs.getString("nombre");
				ap = rs.getString("apellido");
				VONinio aux = new VONinio(ced, nom, ap);
				lista.add(aux);
			}
			rs.close();
			stmt.close();
		}catch (SQLException e) {
			throw new PersistenciaException (e.getErrorCode(),"Error de datos");
		}
		return lista;
	}
	

	public List<VOJuguete> listarJuguetes (Connection con){
		List<VOJuguete> lista = new ArrayList<VOJuguete>();
		int num=0;
		String desc;
		int cedN=0;
		try{
			Consultas consulta = new Consultas();
			String query = consulta.listaJuguetes();
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				num = rs.getInt("numero");
				desc = rs.getString("descripcion");
				cedN = rs.getInt("cedulaNinio");
				VOJuguete aux = new VOJuguete(num, desc, cedN);
				lista.add(aux);
			}
			rs.close();
			stmt.close();
		}catch (SQLException e) {
			throw new PersistenciaException (e.getErrorCode(),"Error de datos");
		}
		return lista;
	}
	
	
	public String darDescripcion (Connection con, int num, int ced) {
		String aux = null;
		try{
			Consultas consulta = new Consultas();
			String query = consulta.darDescripcion();
			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.setInt(1, num);
			pstmt.setInt(2, ced);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next())
				aux = rs.getString("descripcion");
			rs.close();
			pstmt.close();
		}catch (SQLException e) {
			throw new PersistenciaException (e.getErrorCode(),"Error de datos");
		}
		return aux;
	}
		
	
	public void borrarNinioJuguete(Connection con, int ced) {
	try {
		Consultas consulta = new Consultas();
		String query1 = consulta.borrarJuguetesNinio();
		PreparedStatement pstmt1 = con.prepareStatement(query1);
		pstmt1.setInt(1, ced);
		pstmt1.executeUpdate();
		pstmt1.close();
		String query2 = consulta.borrarNinio();
		PreparedStatement pstmt2 = con.prepareStatement(query2);
		pstmt2.setInt(1, ced);
		pstmt2.executeUpdate();
		pstmt2.close();
		
	}catch(SQLException e) {
		throw new PersistenciaException (e.getErrorCode(),"Error de datos");
	}
	}
	
	
	
}


