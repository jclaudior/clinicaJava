package br.com.sintaxerror.model;

public class Consulta {

	private int codConsulta;
	private Paciente paciente;
	private Dentista dentista;
	private String dia;
	private String horario;
	private String obs;
	
	public Consulta() {
		
	}

	public Consulta(int codConsulta, Paciente paciente, Dentista dentista, String dia, String horario, String obs) {
		super();
		this.codConsulta = codConsulta;
		this.paciente = paciente;
		this.dentista = dentista;
		this.dia = dia;
		this.horario = horario;
		this.obs = obs;
	}

	public int getCodConsulta() {
		return codConsulta;
	}

	public void setCodConsulta(int codConsulta) {
		this.codConsulta = codConsulta;
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	public Dentista getDentista() {
		return dentista;
	}

	public void setDentista(Dentista dentista) {
		this.dentista = dentista;
	}

	public String getDia() {
		return dia;
	}

	public void setDia(String dia) {
		this.dia = dia;
	}

	public String getHorario() {
		return horario;
	}

	public void setHorario(String horario) {
		this.horario = horario;
	}

	public String getObs() {
		return obs;
	}

	public void setObs(String obs) {
		this.obs = obs;
	}
	
	
}
