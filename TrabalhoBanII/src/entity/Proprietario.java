package entity;

public class Proprietario {

	private int id;
	private String cpf;
	private int objetoId;
	public Proprietario( String cpf, int objetoId) {
		super();
		this.cpf = cpf;
		this.objetoId = objetoId;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String usuarioId) {
		this.cpf = usuarioId;
	}
	public int getObjetoId() {
		return objetoId;
	}
	public void setObjetoId(int objetoId) {
		this.objetoId = objetoId;
	}

	public Proprietario( ) {

	}
	
	
}
