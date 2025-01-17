package controller;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import entity.Agentes;
import entity.Responsavel;
import model.ResponsavelModel;

public class ResponsavelController {

	public static void create(Agentes agente, int ocorrenciaId, Connection con) throws SQLException {
		Responsavel res = new Responsavel(agente.getMatricula(), ocorrenciaId);
		ResponsavelModel.create(res, con);
	}

	public static List<Responsavel> findAllByMatricula(String matricula, Connection con) throws SQLException {
		List<Responsavel> list = ResponsavelModel.listAllByMatricula(matricula, con);
		
		return null;
		
	}

	public static void deleteByMatricula(String matricula, Connection con) throws SQLException {
		ResponsavelModel.deleteAllByMatricula(matricula,con);
	}

	public static boolean isMine(String matricula, int ocorrenciaId, Connection con) throws SQLException {

		return ResponsavelModel.isMine(matricula, ocorrenciaId, con);
	}

}
