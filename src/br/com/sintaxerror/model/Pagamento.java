package br.com.sintaxerror.model;

public class Pagamento {
	
	private int codPagamento;
	private Paciente paciente;
	private double valor;
	private String dia;
	private String formaPagamento;
	
	public Pagamento() {
		
	}

	public Pagamento(int codPagamento, Paciente paciente, double valor, String dia, String formaPagamento) {
		super();
		this.codPagamento = codPagamento;
		this.paciente = paciente;
		this.valor = valor;
		this.dia = dia;
		this.formaPagamento = formaPagamento;
	}

	public int getCodPagamento() {
		return codPagamento;
	}

	public void setCodPagamento(int codPagamento) {
		this.codPagamento = codPagamento;
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public String getDia() {
		return dia;
	}

	public void setDia(String dia) {
		this.dia = dia;
	}

	public String getFormaPagamento() {
		return formaPagamento;
	}

	public void setFormaPagamento(String formaPagamento) {
		this.formaPagamento = formaPagamento;
	}
	
}
