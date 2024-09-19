package entity;

public class Ocorrencias {
	private int id;
	private int proprietario_id;
	private String data;
	private String rua;
	private String bairro;
	private String descricao;
	private String providencias;
	private String numero;
	private Objetos objeto;
	public Objetos getObjeto() {
		return objeto;
	}
	public void setObjeto(Objetos objeto) {
		this.objeto = objeto;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getProprietario_id() {
		return proprietario_id;
	}
	public void setProprietario_id(int proprietario_id) {
		this.proprietario_id = proprietario_id;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public String getRua() {
		return rua;
	}
	public void setRua(String rua) {
		this.rua = rua;
	}
	public String getBairro() {
		return bairro;
	}
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public String getProvidencias() {
		return providencias;
	}
	public void setProvidencias(String providencias) {
		this.providencias = providencias;
	}
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public Ocorrencias(int proprietario_id, String data, String rua, String bairro, String descricao,
			String providencias, String numero) {
		super();
		this.proprietario_id = proprietario_id;
		this.data = data;
		this.rua = rua;
		this.bairro = bairro;
		this.descricao = descricao;
		this.providencias = providencias;
		this.numero = numero;
	}
	public Ocorrencias() {
		super();
	}
	
	
	
}
