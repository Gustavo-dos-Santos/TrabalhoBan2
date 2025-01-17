package controller;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import entity.Objetos;
import entity.Ocorrencias;
import entity.Proprietario;
import entity.Usuarios;
import model.ObjetosModel;
import model.UsuariosModel;

public class UsuariosController {
    private static Scanner input = new Scanner(System.in);  

    public static void Register(Connection con) throws SQLException {

    	 Usuarios us = new Usuarios();
    	    Scanner input = new Scanner(System.in);

    	    System.out.println("Nome: ");
    	    String nome = input.next();
    	    if (nome.length() < 3) {
    	        System.out.println("Erro: O nome deve ter pelo menos 3 caracteres.");
    	        return;
    	    }

    	    System.out.println("CPF: ");
    	    String cpf = input.next();
    	    if (!(cpf != null && cpf.matches("\\d{11}"))) {
    	        System.out.println("Erro: CPF inválido. Deve ter 11 dígitos.");
    	        return;
    	    }
    	    if (UsuariosModel.findByCPF(cpf, con) != null) {
    	        System.out.println("Erro: O CPF já está registrado.");
    	        return;
    	    }

    	    System.out.println("Senha : ");
    	    String pass = input.next();
    	    if (pass.length() < 6) {
    	        System.out.println("Erro: A senha deve ter pelo menos 6 caracteres.");
    	        return;
    	    }

    	    System.out.println("Data de Nascimento (dd/mm/aaaa): ");
    	    String dts = input.next();
    	    if (!(dts != null && dts.matches("^\\d{2}/\\d{2}/\\d{4}$"))) {
    	        System.out.println("Erro: Data de nascimento inválida.");
    	        return;
    	    }

    	    System.out.println("Telefone (somente números): ");
    	    String tel = input.next();
    	    if (!tel.matches("\\d{10,11}")) {
    	        System.out.println("Erro: O telefone deve ter 10 ou 11 dígitos.");
    	        return;
    	    }

    	    // Email
    	    System.out.println("Email: ");
    	    String email = input.next();
    	    if (!(email != null && email.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$"))) {
    	        System.out.println("Erro: E-mail inválido.");
    	        return;
    	    }

    	    us.setCpf(cpf);
    	    us.setDtNasc(dts);
    	    us.setEmail(email);
    	    us.setNome(nome);
    	    us.setPassword(pass);
    	    us.setTelefone(tel);

    	    try {
    	        UsuariosModel.create(us, con);
    	        System.out.println("Usuário registrado com sucesso.");
    	    } catch (SQLException e) {
    	        System.out.println("Erro ao registrar o usuário: " + e.getMessage());
    	    }
    	}

	public static void update(Usuarios usuario, Connection con) throws SQLException {
		UsuariosModel.update(usuario, con);
	}

	public static void delete(Usuarios usuario, Connection con) throws SQLException {
	    List<Objetos> objs = ObjetosController.listAllByCpf(usuario, con);
	    List<Ocorrencias> oc = OcorrenciasController.listAllByCpf(usuario, con);
	    List<Proprietario> p = ProprietarioController.listAllByCpf(usuario, con);

	    if (objs != null && !objs.isEmpty()) {
	        for (Objetos obj : objs) {
	            try {
	                ObjetosController.delete(obj.getId(), con);
	            } catch (SQLException e) {
	                e.printStackTrace();
	                throw e; 
	            }
	        }
	    }

	    if (oc != null && !oc.isEmpty()) {
	        for (Ocorrencias ocorrencia : oc) {
	            try {
	                OcorrenciasController.delete(ocorrencia.getId(), con);
	            } catch (SQLException e) {
	                e.printStackTrace();
	                throw e;  
	            }
	        }
	    }

	    if (p != null && !p.isEmpty()) {
	        for (Proprietario proprietario : p) {
	            try {
	                ProprietarioController.deleteId(proprietario.getId(), con);
	            } catch (SQLException e) {
	                e.printStackTrace();
	                throw e; 
	            }
	        }
	    }

	    try {
	        UsuariosModel.delete(usuario.getCpf(), con);
	        System.out.println("Usuário deletado com sucesso.");
	    } catch (SQLException e) {
	        e.printStackTrace();
	        throw e;  
	    }
	}

	public static List<Usuarios> listAll(Connection con) throws SQLException {
		return UsuariosModel.listAll(con);
	}

	public static Usuarios findByCpf(String cpf, Connection con) throws SQLException {
		return UsuariosModel.findByCPF(cpf, con);
	}

	
}
