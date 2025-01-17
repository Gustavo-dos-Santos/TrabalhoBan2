package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import entity.Objetos;
import entity.Proprietario;
import entity.Usuarios;
import model.ObjetosModel;

public class ObjetosController {
	public static Objetos cadastrarObjeto(Connection con) throws SQLException {
	    Scanner input = new Scanner(System.in);
	    
	    System.out.println("Informe o número de série:");
	    String numeroDeSerie = input.nextLine();
	    if (numeroDeSerie.contains(" ")) {
	        System.out.println("Erro: O número de série não pode conter espaços.");
	        return null;
	    }

	    System.out.println("Informe a marca:");
	    String marca = input.nextLine();
	    if (marca.contains(" ")) {
	        System.out.println("Erro: A marca não pode conter espaços.");
	        return null;
	    }

	    System.out.println("Informe o modelo:");
	    String modelo = input.nextLine();
	    if (modelo.contains(" ")) {
	        System.out.println("Erro: O modelo não pode conter espaços.");
	        return null;
	    }

	    System.out.println("Informe a cor:");
	    String cor = input.nextLine();
	    if (cor.contains(" ")) {
	        System.out.println("Erro: A cor não pode conter espaços.");
	        return null;
	    }

	    System.out.println("Informe a observação:");
	    String observacao = input.nextLine().trim(); // Remove espaços no início e fim

	    System.out.println("Informe a categoria:");
	    String categoria = input.nextLine();
	    if (categoria.contains(" ")) {
	        System.out.println("Erro: A categoria não pode conter espaços.");
	        return null;
	    }

	    input.close();

	    Objetos obj = new Objetos(numeroDeSerie, categoria, marca, modelo, cor, observacao);
	    
	    return ObjetosModel.create(obj, con);
	}



	public static Objetos findById(int id, Connection con) throws SQLException {
		Objetos ob = ObjetosModel.findById(id,con);
		return ob;
	}

	public static List<Objetos> listAllByCpf(Usuarios usuario, Connection con) throws SQLException {
		List<Objetos> listO = ObjetosModel.listAllByCpf(usuario.getCpf(), con);
		
		return listO;
	}

	

	public static void delete(int objetoId, Connection con) throws SQLException {
		ObjetosModel.delete(objetoId, con);
	}

	public static List<Objetos> deleteAllByCpf(Usuarios usuario, Connection con) throws SQLException {
		List<Objetos> list = ObjetosModel.listAllByCpf(usuario.getCpf(), con);
		list.forEach((objetos)->{
			try {
				ObjetosModel.delete(objetos.getId(), con);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		});
		return null;
	}
}
