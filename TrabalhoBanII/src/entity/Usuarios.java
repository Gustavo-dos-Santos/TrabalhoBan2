package entity;

public class Usuarios {
	private String nome;
	private String dtNasc;
	private String email;
	private String telefone;
	private String cpf;
	private String password;
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getDtNasc() {
		return dtNasc;
	}
	public void setDtNasc(String dtNasc) {
		this.dtNasc = dtNasc;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Usuarios(String nome, String dtNasc, String email, String telefone, String cpf, String password) {
		super();
		this.nome = nome;
		this.dtNasc = dtNasc;
		this.email = email;
		this.telefone = telefone;
		this.cpf = cpf;
		this.password = password;
	}
	public Usuarios() {
		super();
	}
	
	
	
	
	
}
