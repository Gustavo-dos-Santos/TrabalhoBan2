package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;

import entity.Objetos;
import entity.Ocorrencias;
import entity.Proprietario;
import entity.Usuarios;
import model.OcorrenciasModel;

public class OcorrenciasController {
    private static Scanner input = new Scanner(System.in);  

	public static void create(int objetoId, Connection con) throws SQLException {
		Proprietario p = ProprietarioController.findByObjetoId(objetoId,con);
		
		if(p != null) {
		System.out.println("Cadastrar ocorrencia");
		System.out.println("Data:");
		String data = input.next();
		System.out.println("Rua: ");
		String rua = input.next();
		System.out.println("Bairro: ");
		String bairro = input.next();
		System.out.println("Numero: ");
		String numero = input.next();
		System.out.println("Descrição:");
		String descricao = input.next();
		
		Ocorrencias oc = new Ocorrencias(p.getId(),data,rua,bairro,descricao,"Aguardo",numero);
		OcorrenciasModel.create(oc, con);
		System.out.println("Cadastrado com sucesso");
		}else {
			System.out.println("Erro, tente novamente mais tarde");
		}
	}

	public static List<Ocorrencias> listAllByCpf(Usuarios usuario, Connection con) throws SQLException {
		List<Ocorrencias> listOc = OcorrenciasModel.findOcorrenciasByCpf(usuario.getCpf(), con);
		
		return listOc;
	}

	public static void delete(int ocorrenciaId, Connection con) throws SQLException {
		OcorrenciasModel.delete(ocorrenciaId, con);
		System.out.println("Deletado com sucesso");
	}

	public static List<Ocorrencias> deleteAllByCpf(Usuarios usuario, Connection con) throws SQLException {
		List<Ocorrencias> listOc = OcorrenciasModel.findOcorrenciasByCpf(usuario.getCpf(), con);
		listOc.forEach((ocorrencia)->{
			System.out.println(ocorrencia.getProprietario_id());
			System.out.println(ocorrencia.getId());

			try {
				OcorrenciasModel.delete(ocorrencia.getId(), con);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		});
		return null;
	}

	public static List<Ocorrencias> listAll(Connection con) throws SQLException {
		return OcorrenciasModel.listAll(con);
	}

	public static List<Ocorrencias> listOcorrenciasByMatricula(String matricula, Connection con) throws SQLException {
		return OcorrenciasModel.listByMatricula(matricula,con);
	}

	public static void update(int ocorrenciaId, String providencia, Connection con) throws SQLException {
		Ocorrencias oc = OcorrenciasModel.findById(ocorrenciaId, con);
		oc.setProvidencias(providencia);
		OcorrenciasModel.update(oc, con);
		
	}

	public static List<Ocorrencias> listOcorrenciasByCpf(String cpf, Connection con) throws SQLException {
	    List<Ocorrencias> ocorrenciasList = new ArrayList<>();
	    
	    String sql = "SELECT o.id AS ocorrencia_id, o.data, o.rua, o.bairro, o.descricao, o.providencias, o.numero, " +
	                 "obj.num_serie, obj.categoria, obj.marca, obj.modelo, obj.cor, obj.observacoes " +
	                 "FROM ocorrencias o " +
	                 "JOIN proprietario p ON o.proprietario_id = p.id " +
	                 "JOIN objetos obj ON p.objeto_id = obj.id " +
	                 "JOIN usuarios u ON p.cpf = u.cpf " +
	                 "WHERE u.cpf = ? " +
	                 "ORDER BY o.data DESC";

	    try (PreparedStatement stmt = con.prepareStatement(sql)) {
	        stmt.setString(1, cpf);
	        
	        try (ResultSet rs = stmt.executeQuery()) {
	            while (rs.next()) {
	                Ocorrencias ocorrencia = new Ocorrencias();
	                ocorrencia.setId(rs.getInt("ocorrencia_id"));
	                ocorrencia.setData(rs.getString("data"));
	                ocorrencia.setRua(rs.getString("rua"));
	                ocorrencia.setBairro(rs.getString("bairro"));
	                ocorrencia.setDescricao(rs.getString("descricao"));
	                ocorrencia.setProvidencias(rs.getString("providencias"));
	                ocorrencia.setNumero(rs.getString("numero"));
	                
	                Objetos objeto = new Objetos();
	                objeto.setNumserie(rs.getString("num_serie"));
	                objeto.setCategoria(rs.getString("categoria"));
	                objeto.setMarca(rs.getString("marca"));
	                objeto.setModelo(rs.getString("modelo"));
	                objeto.setCor(rs.getString("cor"));
	                objeto.setObservacao(rs.getString("observacoes"));

	                ocorrencia.setObjeto(objeto); 
	                
	                ocorrenciasList.add(ocorrencia); 
	            }
	        }
	    }

	    return ocorrenciasList; 
	}



}
