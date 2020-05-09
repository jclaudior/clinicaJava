package br.com.sintaxerror.model;

public class Dentista {
	
	private int codDentista;
    private String nomeDentista;
    private String especialidade;
	
	public Dentista() {
		
	}

	public Dentista(int codDentista, String nomeDentista, String especialidade) {
		super();
		this.codDentista = codDentista;
		this.nomeDentista = nomeDentista;
		this.especialidade = especialidade;
	}

	public int getCodDentista() {
		return codDentista;
	}

	public void setCodDentista(int codDentista) {
		this.codDentista = codDentista;
	}

	public String getNomeDentista() {
		return nomeDentista;
	}

	public void setNomeDentista(String nomeDentista) {
		this.nomeDentista = nomeDentista;
	}

	public String getEspecialidade() {
		return especialidade;
	}

	public void setEspecialidade(String especialidade) {
		this.especialidade = especialidade;
	}
	
}
