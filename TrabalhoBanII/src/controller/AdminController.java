package controller;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import entity.Agentes;
import entity.Objetos;
import entity.Ocorrencias;
import entity.Relatorios;
import entity.Usuarios;
import model.AdminModel;

public class AdminController {

	public static Relatorios RelatorioUsuarioObjeto(String cpf, Connection con) throws SQLException {
		Usuarios us = UsuariosController.findByCpf(cpf, con);
		List<Objetos> objs = ObjetosController.listAllByCpf(us, con);
		
		
		
		Relatorios rel = new Relatorios(us,objs );
		
		return rel;
	}

	public static Relatorios RelatorioAgenteOcorrencia(String matricula, Connection con) throws SQLException {
		Agentes agente = AgenteController.findByMatricula(matricula,con);
		List<Ocorrencias> ocorrencias = OcorrenciasController.listOcorrenciasByMatricula(matricula, con);
		
		Relatorios rel = new Relatorios(agente,ocorrencias);
		return rel;
	}

	public static Relatorios RelatorioUsuarioOcorrencia(String cpf, Connection con) throws SQLException {
		Usuarios us = UsuariosController.findByCpf(cpf, con);
		List<Ocorrencias> ocorrencias = OcorrenciasController.listOcorrenciasByCpf(cpf, con);

		Relatorios rel = new Relatorios(ocorrencias,us);
		return rel;
	}

}
