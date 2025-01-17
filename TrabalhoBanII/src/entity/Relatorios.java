package entity;

import java.util.List;

public class Relatorios {
	private Usuarios usuario;
	private List<Objetos> objetos;
	private Agentes agentes;
	private List<Ocorrencias> ocorrencias;
	
	public Usuarios getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuarios usuario) {
		this.usuario = usuario;
	}
	public List<Objetos> getObjetos() {
		return objetos;
	}
	public void setObjetos(List<Objetos> objetos) {
		this.objetos = objetos;
	}
	public Agentes getAgentes() {
		return agentes;
	}
	public void setAgentes(Agentes agentes) {
		this.agentes = agentes;
	}
	public List<Ocorrencias> getOcorrencias() {
		return ocorrencias;
	}
	public void setOcorrencias(List<Ocorrencias> ocorrencias) {
		this.ocorrencias = ocorrencias;
	}
	public Relatorios(Usuarios usuario, List<Objetos> objetos) {
		this.usuario = usuario;
		this.objetos = objetos;
	}
	public Relatorios(Agentes agentes, List<Ocorrencias> ocorrencias) {
		this.agentes = agentes;
		this.ocorrencias = ocorrencias;
	}
	
	public Relatorios( List<Ocorrencias> ocorrencias, Usuarios usuario) {
		this.usuario = usuario;
		this.ocorrencias = ocorrencias;
	}
	
	
	
	
}
