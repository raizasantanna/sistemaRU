package br.ccomp.gateway;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.ccomp.modelo.Departamento;

public class DepartamentoGateway {
	
	Connection con = ConnectionFactory.getConnection();
	
	public void insert(String nome, String sigla){
		String sql = "INSERT INTO departamento(nome, sigla) " +
				"VALUES (?,?)";
		
		try {
			PreparedStatement prst = con.prepareStatement(sql);
			prst.setString(1, nome);
			prst.setString(2, sigla);
			
			prst.executeUpdate();
			
			prst.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} 		
			
	}
	
	public void delete(Integer idDepartamento) throws SQLException{
		String sql = "DELETE FROM departamento " +
				"WHERE id = ?";
		
		PreparedStatement prst = con.prepareStatement(sql);
		prst.setInt(1, idDepartamento);
		
		prst.executeQuery();
		
		prst.close();
		
	}
	
	public ArrayList<Departamento> findAll() throws SQLException{
		ArrayList<Departamento> departamentos = new ArrayList<Departamento>();
		Connection conn = null;
		
		String sql = "SELECT * FROM DEPARTAMENTO";
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = ConnectionFactory.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()){
				departamentos.add(new br.ccomp.modelo.Departamento(
						rs.getInt("DEPARTAMENTO.ID"),
						rs.getString("DEPARTAMENTO.NOME"),
						rs.getString("DEPARTAMENTO.SIGLA")));
			}
			
			conn.close();
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return departamentos;
	}

	public ResultSet find(Integer id) throws SQLException{
		String sql = "SELECT * FROM departamento " +
				"WHERE id = ?";
		
		PreparedStatement prst = con.prepareStatement(sql);
		prst.setInt(1, id);
		ResultSet rs = prst.executeQuery();
		
		prst.close();
		return rs;
	}
	
	public boolean find(String sigla) throws SQLException{
		String sql = "SELECT * FROM departamento " +
				"WHERE sigla = ?";
		
		PreparedStatement prst = con.prepareStatement(sql);
		prst.setString(1, sigla);
		
		ResultSet rs = prst.executeQuery();
		
		boolean achou = false;
		
		if(rs.next())
			achou = true;
		
		prst.close();
		//con.close();
		rs.close();
		
		return achou;
	}
}
