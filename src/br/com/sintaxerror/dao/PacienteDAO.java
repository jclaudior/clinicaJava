package br.com.sintaxerror.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

import br.com.sintaxerror.model.Paciente;
import br.com.sintaxerror.util.ConnectionFactory;

public class PacienteDAO {
	
	private Connection con;
	private PreparedStatement st;
	private ResultSet rs;
	
	public PacienteDAO() throws Exception {
		
		try {
			con = ConnectionFactory.getConnection();
		}
		catch (Exception e) {
			throw new Exception(e.getMessage());
		}
		
	}
	
	public boolean salvar(Paciente paciente) throws Exception {
		boolean state = false;
		
		try {
			String sql = "INSERT INTO CAD_PACIENTE VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			
			st = con.prepareStatement(sql);
			
			st.setString(1, paciente.getCpf());
			st.setString(2, paciente.getNome());
			st.setString(3, paciente.getSexo());
			st.setString(4, paciente.getRua());
			st.setString(5, paciente.getNumero());
			st.setString(6, paciente.getComplemento());
			st.setString(7,  paciente.getCidade());
			st.setString(8, paciente.getUf());
			st.setString(9, paciente.getBairro());
			st.setString(10, paciente.getCelular());
			st.setString(11, paciente.getDataNasc());
			st.setString(12, paciente.getEmail());
			st.setString(13, paciente.getSenha());
			
			st.executeUpdate();
			
			state = true;
			return state;
		}
		catch (SQLIntegrityConstraintViolationException e) {
			return state;
		}
	}
	
	public Paciente consultar(String cpf) throws Exception{
		try {
			String sql = "SELECT * FROM CAD_PACIENTE WHERE CPF_PACIENTE = ?";
			
			st = con.prepareStatement(sql);
			st.setString(1, cpf);
			
			rs = st.executeQuery();
			
			while (rs.next()) {
				String cpf2 = rs.getString("CPF_PACIENTE");
				String nome = rs.getString("NOME_PACIENTE");
				String sexo = rs.getString("SEXO_PACIENTE");
				String rua = rs.getString("RUA_PACIENTE");
				String numero = rs.getString("NUMERO_PACIENTE");
				String complemento = rs.getString("COMPLE_PACIENTE");
				String cidade = rs.getString("CIDADE_PACIENTE");
				String uf = rs.getString("UF_PACIENTE");
				String bairro = rs.getString("BAIRRO_PACIENTE");
				String celular = rs.getString("CEL_PACIENTE");
				String data = rs.getString("DTANASC_PACIENTE");
				String email = rs.getString("EMAIL_PACIENTE");
				String senha = rs.getString("SENHA_PACIENTE");
				
				
				Paciente paciente = new Paciente(cpf2, nome, sexo, rua, numero, complemento, cidade, uf, bairro, celular, data, email, senha);
				
				return paciente;
			}
		}
		catch (Exception e) {
			throw new Exception();
		}
		return null;
	}
	
	public void excluir(String cpf) throws Exception {
		try {
			String sql = "DELETE FROM CAD_PACIENTE WHERE CPF_PACIENTE = ?";
			 st = con.prepareStatement(sql);
			 st.setString(1, cpf);
			 
			 st.executeUpdate();
		}
		catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}
	
	public void alterar(Paciente paciente) throws Exception {
		try {
			String sql = "UPDATE CAD_PACIENTE SET NOME_PACIENTE = ?, SEXO_PACIENTE = ?, RUA_PACIENTE = ?, NUMERO_PACIENTE = ?, COMPLE_PACIENTE = ?, CIDADE_PACIENTE = ?, UF_PACIENTE = ?, BAIRRO_PACIENTE = ?, CEL_PACIENTE = ?, DTANASC_PACIENTE = ?, EMAIL_PACIENTE = ?, SENHA_PACIENTE = ?"
					+ "WHERE CPF_PACIENTE = ?";
			
			st = con.prepareStatement(sql);
			
			st.setString(1, paciente.getNome());
			st.setString(2, paciente.getSexo());
			st.setString(3, paciente.getRua());
			st.setString(4, paciente.getNumero());
			st.setString(5, paciente.getComplemento());
			st.setString(6, paciente.getCidade());
			st.setString(7, paciente.getUf());
			st.setString(8, paciente.getBairro());
			st.setString(9, paciente.getCelular());
			st.setString(10, paciente.getDataNasc());
			st.setString(11, paciente.getEmail());
			st.setString(12, paciente.getSenha());
			st.setString(13, paciente.getCpf());
			
			
			st.executeUpdate();
		}
		catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}
	
	public List<Paciente> listarTodos() throws Exception{
		List<Paciente> listaPaciente = new ArrayList<Paciente>();
		
		try {
			String sql = "SELECT * FROM CAD_PACIENTE";
			
			st = con.prepareStatement(sql);
			
			rs = st.executeQuery();
			
			while (rs.next()) {
				String cpf2 = rs.getString("CPF_PACIENTE");
				String nome = rs.getString("NOME_PACIENTE");
				String sexo = rs.getString("SEXO_PACIENTE");
				String rua = rs.getString("RUA_PACIENTE");
				String numero = rs.getString("NUMERO_PACIENTE");
				String complemento = rs.getString("COMPLE_PACIENTE");
				String cidade = rs.getString("CIDADE_PACIENTE");
				String uf = rs.getString("UF_PACIENTE");
				String bairro = rs.getString("BAIRRO_PACIENTE");
				String celular = rs.getString("CEL_PACIENTE");
				String data = rs.getString("DTANASC_PACIENTE");
				String email = rs.getString("EMAIL_PACIENTE");
				String senha = rs.getString("SENHA_PACIENTE");
				
				
				listaPaciente.add(new Paciente(cpf2, nome, sexo, rua, numero, complemento, cidade, uf, bairro, celular, data, email, senha));
			}
			return listaPaciente;
		}
		catch (Exception e) {
			throw new Exception();
		}
	}
}
