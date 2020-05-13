package br.com.sintaxerror.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import br.com.sintaxerror.model.Consulta;
import br.com.sintaxerror.model.Dentista;
import br.com.sintaxerror.model.Paciente;
import br.com.sintaxerror.util.ConnectionFactory;

public class ConsultaDAO {
	
	private Connection con;
	private PreparedStatement st;
	private ResultSet rs;
	
	public ConsultaDAO() throws Exception {
		try {
			con = ConnectionFactory.getConnection();
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}
	}
	
	public boolean salvar(Consulta consulta) throws Exception {
		boolean state = false;
		try {
			String sql = "INSERT INTO CAD_CONSULTA VALUES (?, ?, ?, ?, ?, ?)";
			
			st = con.prepareStatement(sql);
			
			st.setInt(1, consulta.getCodConsulta());
			st.setString(2, consulta.getPaciente().getCpf());
			st.setInt(3, consulta.getDentista().getCodDentista());
			st.setString(4, consulta.getDia());
			st.setString(5, consulta.getHorario());
			st.setString(6, consulta.getObs());
			
			st.executeUpdate();
			
			state = true;
			return state;
		}
		catch (SQLIntegrityConstraintViolationException e) {
			return false;
		}
	}
	
	public void alterar(Consulta consulta) throws Exception {
		try {
			String sql = "UPDATE CAD_CONSULTA SET PACIENTE_CONSULTA = ?, DENTISTA_CONSULTA = ?, DTA_CONSULTA = ?, HORARIO_CONSULTA = ?, OBS_CONSULTA = ?, WHERE COD_CONSULTA = ?";
			
			st = con.prepareStatement(sql);
			
			st.setString(1, consulta.getPaciente().getCpf());
			st.setInt(2, consulta.getDentista().getCodDentista());
			st.setString(3, consulta.getDia());
			st.setString(4, consulta.getHorario());
			st.setString(5, consulta.getObs());
			st.setInt(6, consulta.getCodConsulta());
			
			st.executeUpdate();
		}
		catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}
	
	public Consulta consultar(String cpfPaciente) throws Exception {
		try {
			String sql = "SELECT * FROM CAD_PACIENTE " + 
					"JOIN CAD_CONSULTA ON CAD_PACIENTE.CPF_PACIENTE = CAD_CONSULTA.PACIENTE_CONSULTA " + 
					"JOIN CAD_DENTISTA ON CAD_CONSULTA.DENTISTA_CONSULTA = CAD_DENTISTA.COD_DENTISTA " + 
					"WHERE CAD_PACIENTE.CPF_PACIENTE = ?";
			
			st = con.prepareStatement(sql);
			st.setString(1, cpfPaciente);
			
			rs = st.executeQuery();
			
			while (rs.next()) {
				// dados consultas
				int cod = rs.getInt("COD_CONSULTA");
				String dia = rs.getString("DTA_CONSULTA");
				String horario = rs.getString("HORARIO_CONSULTA");
				String obs = rs.getString("OBS_CONSULTA");
				
				// dados paciente
				String cpf = rs.getString("CPF_PACIENTE");
				String nomePaciente = rs.getString("NOME_PACIENTE");
				String sexo = rs.getString("SEXO_PACIENTE");
				String rua = rs.getString("RUA_PACIENTE");
				String numero = rs.getString("NUMERO_PACIENTE");
				String complemento = rs.getString("COMPLE_PACIENTE");
				String uf = rs.getString("UF_PACIENTE");
				String bairro = rs.getString("BAIRRO_PACIENTE");
				String celular = rs.getString("CEL_PACIENTE");
				String data = rs.getString("DTANASC_PACIENTE");
				String email = rs.getString("EMAIL_PACIENTE");
				String senha = rs.getString("SENHA_PACIENTE");
				String cidade = rs.getString("CIDADE_PACIENTE");
				//dados dentista
				int codDentista = rs.getInt("COD_DENTISTA");
				String nomeDentista = rs.getString("NOME_DENTISTA");
				String especialidade = rs.getString("ESPEC_DENTISTA");
				
				Paciente paciente = new Paciente(cpf, nomePaciente, sexo, rua, numero, complemento, uf, bairro, celular, data, email, senha, cidade);
				Dentista dentista = new Dentista(codDentista, nomeDentista, especialidade);
				
				Consulta consulta = new Consulta(cod, paciente, dentista, dia, horario, obs);
				
				return consulta;
			}
		}
		catch (Exception e) {
			throw new Exception(e.getMessage());
		}
		return null;
	}
	
	public Consulta consultar(int codConsulta) throws Exception {
		try {
			String sql = "SELECT * FROM COD_PACIENTE " + 
					"JOIN CAD_CONSULTA ON CAD_PACIENTE.CPF_PACIENTE = CAD_CONSULTA.PACIENTE_CONSULTA " + 
					"JOIN CAD_DENTISTA ON CAD_CONSULTA.DENTISTA_CONSULTA = CAD_DENTISTA.COD_DENTISTA " + 
					"WHERE CAD_CONSULTA.COD_CONSULTA = ?";
			
			st = con.prepareStatement(sql);
			st.setInt(1, codConsulta);
			
			rs = st.executeQuery();
			
			while (rs.next()) {
				// dados consultas
				int cod = rs.getInt("COD_CONSULTA");
				String dia = rs.getString("DTA_CONSULTA");
				String horario = rs.getString("HORARIO_CONSULTA");
				String obs = rs.getString("OBS_CONSULTA");
				
				// dados paciente
				String cpf = rs.getString("CPF_PACIENTE");
				String nomePaciente = rs.getString("NOME_PACIENTE");
				String sexo = rs.getString("SEXO_PACIENTE");
				String rua = rs.getString("RUA_PACIENTE");
				String numero = rs.getString("NUMERO_PACIENTE");
				String complemento = rs.getString("COMPLE_PACIENTE");
				String uf = rs.getString("UF_PACIENTE");
				String bairro = rs.getString("BAIRRO_PACIENTE");
				String celular = rs.getString("CEL_PACIENTE");
				String data = rs.getString("DTA_PACIENTE");
				String email = rs.getString("EMAIL_PACIENTE");
				String senha = rs.getString("SENHA_PACIENTE");
				String cidade = rs.getString("CIDADE_PACIENTE");
				
				//dados dentista
				int codDentista = rs.getInt("COD_DENTISTA");
				String nomeDentista = rs.getString("NOME_DENTISTA");
				String especialidade = rs.getString("ESPEC_DENTISTA");
				
				Paciente paciente = new Paciente(cpf, nomePaciente, sexo, rua, numero, complemento, uf, bairro, celular, data, email, senha, cidade);
				Dentista dentista = new Dentista(codDentista, nomeDentista, especialidade);
				
				Consulta consulta = new Consulta(cod, paciente, dentista, dia, horario, obs);
				
				return consulta;
			}
		}
		catch (Exception e) {
			throw new Exception(e.getMessage());
		}
		return null;
	}
	
	public void excluir(String cpf) throws Exception {
		try {
			String sql = "DELETE FROM CAD_CONSULTA WHERE PACIENTE_CONSULTA = ?";
			
			st = con.prepareStatement(sql);
			st.setString(1, cpf);
			
			st.executeUpdate();
		}
		catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}
	
	public void excluir(int codConsulta) throws Exception {
		try {
			String sql = "DELETE FROM CAD_CONSULTA WHERE COD_CONSULTA = ?";
			
			st = con.prepareStatement(sql);
			st.setInt(1, codConsulta);
			
			st.executeUpdate();
		}
		catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}
	
	public List<Consulta> listarTodos() throws Exception{
		List<Consulta> listaConsulta = new ArrayList<Consulta>();
		
		try {
			String sql = "SELECT * FROM CAD_PACIENTE " + 
					"JOIN CAD_CONSULTA ON CAD_PACIENTE.CPF_PACIENTE = CAD_CONSULTA.PACIENTE_CONSULTA " + 
					"JOIN CAD_DENTISTA ON CAD_CONSULTA.DENTISTA_CONSULTA = CAD_DENTISTA.COD_DENTISTA";
			
			st = con.prepareStatement(sql);
			
			rs = st.executeQuery();
			
			while (rs.next()) {
				// dados consultas
				int cod = rs.getInt("COD_CONSULTA");
				String dia = rs.getString("DTA_CONSULTA");
				String horario = rs.getString("HORARIO_CONSULTA");
				String obs = rs.getString("OBS_CONSULTA");
				
				// dados paciente
				String cpf = rs.getString("CPF_PACIENTE");
				String nomePaciente = rs.getString("NOME_PACIENTE");
				String sexo = rs.getString("SEXO_PACIENTE");
				String rua = rs.getString("RUA_PACIENTE");
				String numero = rs.getString("NUMERO_PACIENTE");
				String complemento = rs.getString("COMPLE_PACIENTE");
				String uf = rs.getString("UF_PACIENTE");
				String bairro = rs.getString("BAIRRO_PACIENTE");
				String celular = rs.getString("CEL_PACIENTE");
				String data = rs.getString("DTANASC_PACIENTE");
				String email = rs.getString("EMAIL_PACIENTE");
				String senha = rs.getString("SENHA_PACIENTE");
				String cidade = rs.getString("CIDADE_PACIENTE");
				
				//dados dentista
				int codDentista = rs.getInt("COD_DENTISTA");
				String nomeDentista = rs.getString("NOME_DENTISTA");
				String especialidade = rs.getString("ESPEC_DENTISTA");
				
			
			
				Paciente paciente = new Paciente(cpf, nomePaciente, sexo, rua, numero, complemento, uf, bairro, celular, data, email, senha,cidade);
				
				Dentista dentista = new Dentista(codDentista, nomeDentista, especialidade);
				
				listaConsulta.add(new Consulta(cod, paciente, dentista, dia, horario, obs));
			}
			return listaConsulta;
		}
		catch (Exception e) {
			throw new Exception();
		}
	}
	
	public int lastId() throws Exception{
		try {
			String sql = "SELECT * FROM CAD_CONSULTA ORDER BY COD_CONSULTA DESC LIMIT 1";
			
			st = con.prepareStatement(sql);
			
			rs = st.executeQuery();
			int id = 0;
			while (rs.next()) {
				id = rs.getInt("COD_CONSULTA");
				
			}
			return id;
		}
		catch(Exception e) {
			throw new Exception();
		}
	}
}
