package br.com.sintaxerror.model;

public class Paciente {
	private String cpf;
	private String nome;
	private String sexo;
	private String rua;
	private String numero;
	private String complemento;
	private String uf;
	private String bairro;
	private String celular;
	private String dataNasc;
	private String email;
	private String senha;
	private String cidade;
	public Paciente() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Paciente(String cpf, String nome, String sexo, String rua, String numero, String complemento, String uf,
			String bairro, String celular, String dataNasc, String email, String senha, String cidade) {
		super();
		this.cpf = cpf;
		this.nome = nome;
		this.sexo = sexo;
		this.rua = rua;
		this.numero = numero;
		this.complemento = complemento;
		this.uf = uf;
		this.bairro = bairro;
		this.celular = celular;
		this.dataNasc = dataNasc;
		this.email = email;
		this.senha = senha;
		this.cidade = cidade;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getSexo() {
		return sexo;
	}
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	public String getRua() {
		return rua;
	}
	public void setRua(String rua) {
		this.rua = rua;
	}
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public String getComplemento() {
		return complemento;
	}
	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}
	public String getUf() {
		return uf;
	}
	public void setUf(String uf) {
		this.uf = uf;
	}
	public String getBairro() {
		return bairro;
	}
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	public String getCelular() {
		return celular;
	}
	public void setCelular(String celular) {
		this.celular = celular;
	}
	public String getDataNasc() {
		return dataNasc;
	}
	public void setDataNasc(String dataNasc) {
		this.dataNasc = dataNasc;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public String getCidade() {
		return cidade;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	
	
	
}
