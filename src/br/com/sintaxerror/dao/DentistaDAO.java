package br.com.sintaxerror.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

import br.com.sintaxerror.model.Dentista;
import br.com.sintaxerror.util.ConnectionFactory;

public class DentistaDAO {
	
	private Connection con;
	private PreparedStatement st;
	private ResultSet rs;
	
	public DentistaDAO() throws Exception{
		try {
			con = ConnectionFactory.getConnection();
		}
		catch(Exception e) {
			throw new Exception(e.getMessage());
		}
	}
	
	public boolean salvar(Dentista dentista) throws Exception {
		try {
			String sql = "INSERT INTO CAD_DENTISTA VALUES (?, ?, ?)";
			
			st = con.prepareStatement(sql);
			st.setInt(1, dentista.getCodDentista());
			st.setString(2, dentista.getNomeDentista());
			st.setString(3, dentista.getEspecialidade());
			
			st.executeUpdate();
			
			return true;
		}
		catch (SQLIntegrityConstraintViolationException e) {
			return false;
		}
		catch (Exception e1) {
			throw new Exception(e1.getMessage());
		}
	}
	
	public Dentista consultar (int codDentista) throws Exception {
		try{
			String sql = "SELECT * FROM CAD_DENTISTA WHERE COD_DENTISTA = ?";
			
			st = con.prepareStatement(sql);
			st.setInt(1, codDentista);
			
			rs = st.executeQuery();
			
			while(rs.next()) {
				int cod = rs.getInt("COD_DENTISTA");
				String nome = rs.getString("NOME_DENTISTA");
				String especialidade = rs.getString("ESPEC_DENTISTA");
				
				Dentista dentista = new Dentista(cod, nome, especialidade);
				
				return dentista;
			}
		}
		catch (Exception e) {
			throw new Exception(e.getMessage());
		}
		return null;
	}
	
	public void excluir (int codDentista) throws Exception{
		try {
			String sql = "DELETE FROM CAD_DENTISTA WHERE COD_DENTISTA = ?";
			
			st = con.prepareStatement(sql);
			st.setInt(1, codDentista);
			
			st.executeUpdate();
		}
		catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}
	
	public void alterar (Dentista dentista) throws Exception {
		try {
			String sql = "UPDATE CAD_DENTISTA SET NOME_DENTISTA = ?, ESPEC_DENTISTA = ? WHERE COD_DENTISTA = ?";
			
			st = con.prepareStatement(sql);
			st.setString(1, dentista.getNomeDentista());
			st.setString(2, dentista.getEspecialidade());
			st.setInt(3, dentista.getCodDentista());
			
			st.executeUpdate();
		}
		catch (Exception e) {
			throw new Exception (e.getMessage());
		}
	}
	
	public List<Dentista> listarTodos() throws Exception {
		List<Dentista> lista = new ArrayList<Dentista>();
		try {
			String sql = "SELECT * FROM CAD_DENTISTA";
			
			st = con.prepareStatement(sql);
			
			rs = st.executeQuery();
			
			while(rs.next()) {
				int cod = rs.getInt("COD_DENTISTA");
				String nome = rs.getString("NOME_DENTISTA");
				String especialidade = rs.getString("ESPEC_DENTISTA");
				
				lista.add(new Dentista(cod, nome, especialidade));
			}
			return lista;
		}
		catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}
}
