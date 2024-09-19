package entity;

public class Responsavel {

	private int id;
	private String matricula;
	private int ocorrenciaId;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAgenteId() {
		return matricula;
	}
	public void setAgenteId(String agenteId) {
		this.matricula = agenteId;
	}
	public int getOcorrenciaId() {
		return ocorrenciaId;
	}
	public void setOcorrenciaId(int ocorrenciaId) {
		this.ocorrenciaId = ocorrenciaId;
	}
	public Responsavel( String agenteId, int ocorrenciaId) {
		super();
		this.matricula = agenteId;
		this.ocorrenciaId = ocorrenciaId;
	}
	public Responsavel() {
		super();
	}
	
	
}
