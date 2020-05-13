package br.com.sintaxerror.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

import br.com.sintaxerror.model.Paciente;
import br.com.sintaxerror.model.Pagamento;
import br.com.sintaxerror.util.ConnectionFactory;

public class PagamentoDAO {
	
	private Connection con;
	private PreparedStatement st;
	private ResultSet rs;
	
	public PagamentoDAO() throws Exception {
		try {
			con = ConnectionFactory.getConnection();
		}
		catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}
	
	public boolean salvar(Pagamento pagamento) throws Exception {
		try {
			String sql = "INSERT INTO CAD_PAG VALUES (?, ?, ?, ?, ?)";
			
			st = con.prepareStatement(sql);
			st.setInt(1, pagamento.getCodPagamento());
			st.setString(2, pagamento.getPaciente().getCpf());
			st.setDouble(3, pagamento.getValor());
			st.setString(4, pagamento.getDia());
			st.setString(5, pagamento.getFormaPagamento());
			
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
	
	public Pagamento consultar(String cpf) throws Exception{
		try {
			String sql = "SELECT * FROM CAD_PAG "
					+ "JOIN CAD_PACIENTE ON CAD_PAG.PACIENTE_PAG = CAD_PACIENTE.CPF_PACIENTE"
					+ "WHERE CAD_PACIENTE.CPF_PACIENTE = ?";
			
			st = con.prepareStatement(sql);
			st.setString(1, cpf);
			
			rs = st.executeQuery();
			
			while (rs.next()) {
				//dados paciente
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
				
				
				Paciente paciente = new Paciente(cpf2, nome, sexo, rua, numero, complemento, uf, bairro, celular, data, email, senha,cidade);
				// dados pagamento
				int cod = rs.getInt("COD_PAG");
				double valor = rs.getDouble("VL_PAG");
				String dia = rs.getString("DTA_PAG");
				String forma = rs.getString("FORMA_PAG");
				
				Pagamento pagamento = new Pagamento(cod, paciente, valor, dia, forma);
				
				return pagamento;
			}
		}
		catch (Exception e) {
			throw new Exception(e.getMessage());
		}
		return null;
	}
	
	public void excluir (int codPagamento) throws Exception {
		try {
			String sql = "DELETE FROM CAD_PAG WHERE COD_PAG= ?";
			
			st = con.prepareStatement(sql);
			st.setInt(1, codPagamento);
			
			st.executeUpdate();
		}
		catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}
	
	public void alterar(Pagamento pagamento) throws Exception {
		try {
			String sql = "UPDATE CAD_PAG SET PACIENTE_PAG = ?, VL_PAG = ?, DTA_PAG = ?, FORMA_PAG = ? WHERE COD_PAG = ?";
			
			st = con.prepareStatement(sql);
			st.setString(1, pagamento.getPaciente().getCpf());
			st.setDouble(2, pagamento.getValor());
			st.setString(3, pagamento.getDia());
			st.setString(4, pagamento.getFormaPagamento());
			st.setInt(5, pagamento.getCodPagamento());
			
			st.executeUpdate();
		}
		catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}
	
	public List<Pagamento> listarTodos() throws Exception {
		try {
			List<Pagamento> lista = new ArrayList<Pagamento>();
			String sql = "SELECT * FROM CAD_PAG "
					+ "JOIN CAD_PACIENTE ON CAD_PAG.PACIENTE_PAG = CAD_PACIENTE.CPF_PACIENTE";
			
			st = con.prepareStatement(sql);
			
			rs = st.executeQuery();
			
			while (rs.next()) {
				//dados paciente
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
				String senha = rs.getString("SENHA_PACIENTEa");
				
				
				Paciente paciente = new Paciente(cpf2, nome, sexo, rua, numero, complemento, uf, bairro, celular, data, email, senha, cidade);
				
				// dados pagamento
				int cod = rs.getInt("COD_PAG");
				double valor = rs.getDouble("VL_PAG");
				String dia = rs.getString("DTA_PAG");
				String forma = rs.getString("FORMA_PAG");
				
				lista.add(new Pagamento(cod, paciente, valor, dia, forma));
			}
			return lista;
		}
		catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}
}
