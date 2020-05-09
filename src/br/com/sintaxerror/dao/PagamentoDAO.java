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
			String sql = "insert into pagamento values (?, ?, ?, ?, ?)";
			
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
			String sql = "select * from pagamento"
					+ "join paciente on pagamento.paciente_FK = paciente.cpf"
					+ "where paciente.cpf = ?";
			
			st = con.prepareStatement(sql);
			st.setString(1, cpf);
			
			rs = st.executeQuery();
			
			while (rs.next()) {
				//dados paciente
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
				
				Paciente paciente = new Paciente(cpf2, nome, sexo, rua, numero, complemento, uf, bairro, celular, data, email, senha);
				// dados pagamento
				int cod = rs.getInt("codPagamento");
				double valor = rs.getDouble("valor");
				String dia = rs.getString("dia");
				String forma = rs.getString("formaPagamento");
				
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
			String sql = "delete from pagamento where codPagamento = ?";
			
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
			String sql = "update pagamento set paciente_FK = ?, valor = ?, dia = ?, formaPagamento = ? where codPagamento = ?";
			
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
			String sql = "select * from pagamento";
			
			st = con.prepareStatement(sql);
			
			rs = st.executeQuery();
			
			while (rs.next()) {
				//dados paciente
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
				
				Paciente paciente = new Paciente(cpf2, nome, sexo, rua, numero, complemento, uf, bairro, celular, data, email, senha);
				
				// dados pagamento
				int cod = rs.getInt("codPagamento");
				double valor = rs.getDouble("valor");
				String dia = rs.getString("dia");
				String forma = rs.getString("formaPagamento");
				
				lista.add(new Pagamento(cod, paciente, valor, dia, forma));
			}
			return lista;
		}
		catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}
}
