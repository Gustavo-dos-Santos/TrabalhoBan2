package entity;

public class Agentes {
	private String nome;
	private String matricula;
	private String password;
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getMatricula() {
		return matricula;
	}
	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Agentes(String nome, String matricula, String password) {
		super();
		this.nome = nome;
		this.matricula = matricula;
		this.password = password;
	}
	public Agentes() {
		super();
	}
	
	
	
	
}
