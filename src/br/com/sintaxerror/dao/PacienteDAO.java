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
			String sql = "insert into paciente value(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			
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
			String sql = "select * from paciente where cpf = ?";
			
			st = con.prepareStatement(sql);
			st.setString(1, cpf);
			
			rs = st.executeQuery();
			
			while (rs.next()) {
				String cpf2 = rs.getString("cpf");
				String nome = rs.getString("nome");
				String sexo = rs.getString("uf");
				String rua = rs.getString("rua");
				String numero = rs.getString("numero");
				String complemento = rs.getString("complemento");
				String uf = rs.getString("uf");
				String bairro = rs.getString("bairro");
				String celular = rs.getString("celular");
				String data = rs.getString("dataNasc");
				String email = rs.getString("email");
				String senha = rs.getString("senha");
				String cidade = rs.getNString("cidade");
				
				Paciente paciente = new Paciente(cpf2, nome, sexo, rua, numero, complemento, uf, bairro, celular, data, email, senha, cidade);
				
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
			String sql = "delete from paciente where cpf = ?";
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
			String sql = "update paciente set nome = ?, sexo = ?, rua = ?, numero = ?, complemento = ?, cidade = ?, uf = ?, bairro = ?, celular = ?, dataNasc = ?, email = ?, senha = ?"
					+ "where cpf = ?";
			
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
			String sql = "select * from paciente";
			
			st = con.prepareStatement(sql);
			
			rs = st.executeQuery();
			
			while (rs.next()) {
				String cpf2 = rs.getString("cpf");
				String nome = rs.getString("nome");
				String sexo = rs.getString("uf");
				String rua = rs.getString("rua");
				String numero = rs.getString("numero");
				String complemento = rs.getString("complemento");
				String uf = rs.getString("uf");
				String bairro = rs.getString("bairro");
				String celular = rs.getString("celular");
				String data = rs.getString("dataNasc");
				String email = rs.getString("email");
				String senha = rs.getString("senha");
				String cidade = rs.getString("cidade");
				
				listaPaciente.add(new Paciente(cpf2, nome, sexo, rua, numero, complemento, uf, bairro, celular, data, email, senha, cidade));
			}
			return listaPaciente;
		}
		catch (Exception e) {
			throw new Exception();
		}
	}
}
