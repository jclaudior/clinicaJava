package br.com.sintaxerror.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

import br.com.clinica.model.Consulta;
import br.com.clinica.model.Dentista;
import br.com.clinica.model.Paciente;
import br.com.clinica.util.ConnectionFactory;

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
			String sql = "insert into consulta values (?, ?, ?, ?, ?, ?)";
			
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
			String sql = "update consulta set paciente_FK = ?, dentista_Fk = ?, dia = ?, horario = ?, obs = ?, where codConsulta = ?";
			
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
			String sql = "select * from paciente\r\n" + 
					"join consulta on paciente.cpf = consulta.paciente_fk\r\n" + 
					"join dentista on consulta.dentista_fk = dentista.codDentista\r\n" + 
					"where paciente.cpf = ?";
			
			st = con.prepareStatement(sql);
			st.setString(1, cpfPaciente);
			
			rs = st.executeQuery();
			
			while (rs.next()) {
				// dados consultas
				int cod = rs.getInt("codConsulta");
				String dia = rs.getString("dia");
				String horario = rs.getString("horario");
				String obs = rs.getString("obs");
				
				// dados paciente
				String cpf = rs.getString("cpf");
				String nomePaciente = rs.getString("nome");
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
				
				//dados dentista
				int codDentista = rs.getInt("codDentista");
				String nomeDentista = rs.getString("nomeDentista");
				String especialidade = rs.getString("especialidade");
				
				Paciente paciente = new Paciente(cpf, nomePaciente, sexo, rua, numero, complemento, uf, bairro, celular, data, email, senha);
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
			String sql = "select * from paciente\r\n" + 
					"join consulta on paciente.cpf = consulta.paciente_fk\r\n" + 
					"join dentista on consulta.dentista_fk = dentista.codDentista\r\n" + 
					"where consulta.codConsulta = ?";
			
			st = con.prepareStatement(sql);
			st.setInt(1, codConsulta);
			
			rs = st.executeQuery();
			
			while (rs.next()) {
				// dados consultas
				int cod = rs.getInt("codConsulta");
				String dia = rs.getString("dia");
				String horario = rs.getString("horario");
				String obs = rs.getString("obs");
				
				// dados paciente
				String cpf = rs.getString("cpf");
				String nomePaciente = rs.getString("nome");
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
				
				//dados dentista
				int codDentista = rs.getInt("codDentista");
				String nomeDentista = rs.getString("nomeDentista");
				String especialidade = rs.getString("especialidade");
				
				Paciente paciente = new Paciente(cpf, nomePaciente, sexo, rua, numero, complemento, uf, bairro, celular, data, email, senha);
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
			String sql = "delete from consulta where paciente_fk = ?";
			
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
			String sql = "delete from consulta where codConsulta = ?";
			
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
			String sql = "select * from paciente\r\n" + 
					"join consulta on paciente.cpf = consulta.paciente_fk\r\n" + 
					"join dentista on consulta.dentista_fk = dentista.codDentista\r\n";
			
			st = con.prepareStatement(sql);
			
			rs = st.executeQuery();
			
			while (rs.next()) {
				// dados consultas
				int cod = rs.getInt("codConsulta");
				String dia = rs.getString("dia");
				String horario = rs.getString("horario");
				String obs = rs.getString("obs");
				
				// dados paciente
				String cpf = rs.getString("cpf");
				String nomePaciente = rs.getString("nome");
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
				
				//dados dentista
				int codDentista = rs.getInt("codDentista");
				String nomeDentista = rs.getString("nomeDentista");
				String especialidade = rs.getString("especialidade");
				
				Paciente paciente = new Paciente(cpf, nomePaciente, sexo, rua, numero, complemento, uf, bairro, celular, data, email, senha);
				Dentista dentista = new Dentista(codDentista, nomeDentista, especialidade);
				
				listaConsulta.add(new Consulta(cod, paciente, dentista, dia, horario, obs));
			}
			return listaConsulta;
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new Exception();
		}
	}
}
