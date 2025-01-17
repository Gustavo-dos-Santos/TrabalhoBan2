package controller;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;

import entity.Objetos;
import entity.Proprietario;
import entity.Usuarios;
import model.ProprietarioModel;

public class ProprietarioController {
	public static void create(Usuarios us, Objetos obj,Connection con ) throws SQLException {
		Proprietario proprietario = new Proprietario(us.getCpf(),obj.getId());
		ProprietarioModel.create(proprietario, con);
		System.out.println("Cadastrado com sucesso");
	}

	public static void delete(int objetoId, List<Proprietario> list, Connection con) throws SQLException {
	    int proprietarioId = -1;

	    for (Proprietario p : list) {
	        if (p.getObjetoId() == objetoId) {
	            proprietarioId = p.getId();
	            break;
	        }
	    }

	    if (proprietarioId != -1) {
	    	ObjetosController.delete(objetoId,con);
	        ProprietarioModel.delete(proprietarioId, con);
	        System.out.println("Deletado com sucesso");
	    } else {
	        System.out.println("Objeto com o id " + objetoId + " fornecido não encontrado.");
	    }
	}

	public static void deleteProprietario( Proprietario proprietario, Connection con) throws SQLException {
		ProprietarioModel.delete(proprietario.getId(), con);
	}

	
	public static List<Proprietario> listAll(Usuarios usuario, Connection con) throws SQLException {
	    List<Proprietario> list = ProprietarioModel.listAllById(usuario.getCpf(), con);
	    return list;
	}

	public static List<Proprietario> listAllByCpf(Usuarios usuario, Connection con) throws SQLException {
		List<Proprietario> list = ProprietarioModel.listAllByCpf(usuario.getCpf(), con);
		return list;
	}

	public static Proprietario findById(int proprietarioId, Connection con) throws SQLException {
		return ProprietarioModel.findById(proprietarioId,con);		
	}
	
	public static Proprietario findByObjetoId(int objetoId, Connection con) throws SQLException{
		return ProprietarioModel.findByObjetoId(objetoId,con);
	}

	public static void deleteId(int id, Connection con) throws SQLException {
		ProprietarioModel.deleteId(id, con);
	}

	public static List<Proprietario> deleteAllByCpf(Usuarios usuario, Connection con) throws SQLException {
		List<Proprietario> list = ProprietarioModel.listAllByCpf(usuario.getCpf(), con);
		list.forEach((proprietario)->{
			try {
				ProprietarioModel.deleteId(proprietario.getId(), con);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		});
		return null;
	}
	
}
