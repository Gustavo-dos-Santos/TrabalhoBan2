package controller;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;

import db.Conexao;
import entity.Agentes;
import entity.Objetos;
import entity.Ocorrencias;
import entity.Proprietario;
import entity.Relatorios;
import entity.Responsavel;
import entity.Usuarios;

public class Principal {
	public static void main(String[] args) throws SQLException {
	    Scanner input = new Scanner(System.in);  
		Conexao c = new Conexao();
		Connection con = c.getConnection();
		Agentes agente = new Agentes();
		Usuarios usuario = new Usuarios();

		int op = 0;

		do {
		    op = menu(); 
		    try {
		        switch (op) {
		            case 1: {
		                UsuariosController.Register(con);  
		                break;
		            }
		            case 2: {
		                usuario = LoginController.usuarioLogin(con);  
		                int opu = 0;
		                do {
			                opu = menuUsuario();
			                
			                switch(opu) {
			                	case 1:{
			                		Objetos obj = ObjetosController.cadastrarObjeto(con);
			                		if(obj != null){			                		
			                			ProprietarioController.create(usuario, obj, con);
			                			System.out.println("------------");
			                		}
			                		
			                		break;
			                	}
			                	case 2:{
			                		List<Proprietario> list = ProprietarioController.listAllByCpf(usuario, con);
			                		if(list.size() != 0) {
			                			System.out.println("\n" +  list.size());
				                		list.forEach((p) -> {
				                	    	Objetos obj;
				                			try {
				                				obj = ObjetosController.findById(p.getObjetoId(),con);
				                		        showObjeto(obj);
				                			} catch (SQLException e) {
				                				e.printStackTrace();
				                			}
				                	    });
				                		System.out.println("Informe o Id");
				                		int objetoId = input.nextInt();
				                		
				                		boolean[] isTrue = {false};
				                		list.forEach((p)->{
				                			if(p.getObjetoId() == objetoId ) {
				                				isTrue[0] = true;
				                			}
				                		});
				                		if(isTrue[0]) {				                			
					                		ProprietarioController.delete(objetoId,list, con);
				                		}else {
				                			System.out.println("Id incorreto");
				                		}
				                		
			                		}else {
			                			System.out.println("Nenhum objeto registrado");
			                		}
			                	    break;
			                	}
			                	case 3:{
			                		List<Proprietario> list = ProprietarioController.listAllByCpf(usuario, con);
			                		if(list.size() != 0) {
			                			System.out.println("\n" +  list.size());
				                		list.forEach((p) -> {
				                	    	Objetos obj;
				                			try {
				                				obj = ObjetosController.findById(p.getObjetoId(),con);
				                				showObjeto(obj);
				                			} catch (SQLException e) {
				                				e.printStackTrace();
				                			}
				                	    });
			                		}else {
			                			System.out.println("Nenhum objeto registrado");
			                		}
			                	    break;
			                	}
			                	case 4:{
			                		List<Proprietario> list = ProprietarioController.listAllByCpf(usuario, con);
			                		if(list.size() != 0) {
			                			System.out.println("\n" +  list.size());
				                		list.forEach((p) -> {
				                	    	Objetos obj;
				                			try {
				                				obj = ObjetosController.findById(p.getObjetoId(),con);
				                		        showObjeto(obj);
				                			} catch (SQLException e) {
				                				e.printStackTrace();
				                			}
				                	    });
				                		System.out.println("Informe o Id do objeto");
				                		int objetoId = input.nextInt();
				                		boolean[] isTrue = {false};
				                		list.forEach((p)->{
				                			if(p.getObjetoId() == objetoId ) {
				                				isTrue[0] = true;
				                			}
				                		});
				                		if(isTrue[0]) {				                			
				                			OcorrenciasController.create(objetoId, con);
				                		}else {
				                			System.out.println("Id incorreto");
				                		}
			                		}else {
			                			System.out.println("Nenhum objeto registrado, cadastre um objeto para registrar uma Ocorrencia");
			                		}
			                		break;
			                	}
			                	case 5:{
			                		List<Ocorrencias> list = OcorrenciasController.listAllByCpf(usuario, con);
			                		if(list.size() != 0) {
				                		list.forEach((o) ->{
				                			showOcorrencia(o);
				                		});
				                		System.out.println("Informe o Id da ocorrencia");
				                		int ocorrenciaId = input.nextInt();
				                		boolean[] isTrue = {false};
				                		list.forEach((o)->{
				                			if(o.getId() == ocorrenciaId ) {
				                				isTrue[0] = true;
				                			}
				                		});
				                		if(isTrue[0]) {				                			
				                			OcorrenciasController.delete(ocorrenciaId, con);
				                		}else {
				                			System.out.println("Id incorreto");
				                		}
				                		
			                		}else {
			                			System.out.println("Nenhuma Ocorrencia registrada");
			                		}
			                		break;
			                	}
			                	case 6:{
			                		List<Ocorrencias> list = OcorrenciasController.listAllByCpf(usuario, con);
			                		if(list.size() != 0) {
			                			list.forEach((o) ->{
			                				showOcorrencia(o);
				                		});
				                	}else {
			                			System.out.println("Nenhuma Ocorrencia registrada");
			                		}
			                		break;
			                	}
			                	case 7:{
			                		showUsuario(usuario);
			                		break;
			                	}
			                	case 8:{
			                		Usuarios update = new Usuarios();
			                		String email = "";
			                		String senha = "";
			                		String telefone = "";
			                		
			                		showUsuario(usuario);
			                		
			                		System.out.println("Email:");
			                		email = input.next();
			                		
			                		System.out.println("Telefone:");
			                		telefone = input.next();
			                		
			                		System.out.println("Senha:");
			                		senha = input.next();
			                		
			                		usuario.setEmail(email);
			                		usuario.setPassword(senha);
			                		usuario.setTelefone(telefone);
			                		
			                		UsuariosController.update(usuario,con);
			                		break;
			                	}
			                	case 9:{
			                		System.out.println("Tem certeza que quer remover sua conta ? \n 0-não \n 1- sim ");
			                		int remover = input.nextInt();
			                		if(remover == 1) {
			                			UsuariosController.delete(usuario, con);
			                			opu = -1;
			                		}
			                		break;
			                	}
			                }
                		}while(opu <10 && opu > 0); 
		                break;
		            }
		            case 3: {
		                AgenteController.agenteRegister(con);  
		                break;
		            }
		            case 4: {
		                agente = LoginController.agenteLogin(con); 
		                int opa = 0;
		                do {
		                	opa = menuAgente();
//		            	    System.out.println("1 - Lista de ocorrências");
		                	switch (opa) {
								case 1:{
									List<Ocorrencias> list = OcorrenciasController.listAll(con);
									list.forEach((o)->{
										showOcorrencia(o);
									});
									break;
								}
//			            	    System.out.println("2 - Atender ocorrência");

								case 2:{
									List<Ocorrencias> list = OcorrenciasController.listAll(con);
			                		if(list.size() != 0) {
			                			System.out.println("\n" +  list.size());
										list.forEach((o)->{
											showOcorrencia(o);
										});
				                		System.out.println("Informe o Id da ocorrencia");
				                		int ocorrenciaId = input.nextInt();
				                		boolean isMine = ResponsavelController.isMine(agente.getMatricula(), ocorrenciaId,con);
				                		boolean[] isTrue = {false};
				                						                		
				                		list.forEach((o)->{
				                			if(o.getId() == ocorrenciaId && !isMine  ) {
				        
				                				isTrue[0] = true;
				                			}
				                		});
				                		if(isTrue[0]) {				                			
				                			ResponsavelController.create(agente, ocorrenciaId, con);
				                		}else {
				                			System.out.println("Erro, verifique se voce já esta atendendo");
				                		}
			                		}else {
			                			System.out.println("Nenhuma ocorrencia registrada");
			                		}
									
									break;
								}
//			            	    System.out.println("3 - Minhas Ocorrencias");

								case 3:{
									List<Ocorrencias> list = OcorrenciasController.listOcorrenciasByMatricula(agente.getMatricula(),con);
									list.forEach((o)->{
										showOcorrencia(o);
									});
									break;
								}
//			            	    System.out.println("4 - Atualizar minhas ocorrência");

								case 4:{
									List<Ocorrencias> list = OcorrenciasController.listOcorrenciasByMatricula(agente.getMatricula(),con);
			                		if(list.size() != 0) {
										list.forEach((o)->{
											showOcorrencia(o);
										});
										System.out.println("Informe o Id da ocorrencia");
				                		int ocorrenciaId = input.nextInt();
				                		boolean[] isTrue = {false};
				                		list.forEach((o)->{
				                			if(o.getId() == ocorrenciaId ) {
				                				isTrue[0] = true;
				                			}
				                		});
				                		
				                		if(isTrue[0]) {				    
				                			System.out.println("Providencias:");
				                			String providencia = input.next();
				                			OcorrenciasController.update(ocorrenciaId, providencia, con);
				                		}else {
				                			System.out.println("Id incorreto");
				                		}	
			                		}else {
			                			System.out.println("Nenhuma ocorrencia registrada");
			                		}
										
									break;
								}
//			            	    System.out.println("5 - Informações da conta");

								case 5:{
									showAgente(agente);
									break;
								}
//			            	    System.out.println("6 - Editar conta");

								case 6:{
									AgenteController.update(agente,con);
									break;
								}
//			            	    System.out.println("7 - Remover conta");

								case 7:{
									System.out.println("Tem certeza que quer remover sua conta ? \n 0-não \n 1- sim ");
			                		int remover = input.nextInt();
			                		if(remover == 1) {
										AgenteController.delete(agente,con);
			                			opa = -1;
			                		}
									
									break;
								}
		                	}
		                }while(opa <8 && opa> 0); 
		                break;
		                
		            }
		            case 5: {
		                int opad = 0;
		                do {
		                	opad= menuAdmin();
		                	switch (opad) {
								case 1:{
									List<Usuarios> list = UsuariosController.listAll(con);
									list.forEach((us)->{										
										showUsuario(us);
									});
									System.out.println("Informe o CPF do usuario");
			                		String cpf = input.next();
			                		boolean[] isTrue = {false};
			                						                		
									list.forEach((us)->{			
			                			if(us.getCpf().equals(cpf)) {			
			                				isTrue[0] = true;
			                			}
									});
									
			                		if(isTrue[0]) {		
			                			Relatorios rel = AdminController.RelatorioUsuarioObjeto(cpf,con);
			                			showUsuario(rel.getUsuario()); 
			                			List<Objetos> relList = rel.getObjetos();
			                			relList.forEach((objeto)->{
			                				showObjeto(objeto);
			                			});
			                			
			                		}else {
			                			System.out.println("Erro, CPF não existe");
			                		}
									
									break;
								}
								case 2:{
									List<Agentes> list = AgenteController.listAll(con);
									list.forEach((age)->{
										showAgente(age);
									});
									System.out.println("Informe a Matricula do Agente");
			                		String matricula = input.next();
			                		boolean[] isTrue = {false};
			                						                		
			                		list.forEach((age)->{
			                			if(age.getMatricula().equals(matricula) ) {
			        
			                				isTrue[0] = true;
			                			}
			                		});
			                		if(isTrue[0]) {		
			                			Relatorios rel = AdminController.RelatorioAgenteOcorrencia(matricula,con);
			                			showAgente(rel.getAgentes()); 
			                			rel.getOcorrencias().forEach((ocorrencia)->{
			                				showOcorrencia(ocorrencia);
			                			});
			                			
			                		}else {
			                			System.out.println("Erro, Matricula não existe");
			                		}
									
									
									break;
								}
								case 3:{
									List<Usuarios> list = UsuariosController.listAll(con);
									list.forEach((us)->{										
										showUsuario(us);
									});
									System.out.println("Informe o CPF do usuario");
			                		String cpf = input.next();
			                		boolean[] isTrue = {false};
			                						                		
			                		
									list.forEach((us)->{			
			                			if(us.getCpf().equals(cpf)) {			
			                				isTrue[0] = true;
			                			}
									});
			                		
			                		
			                		if(isTrue[0]) {		
			                			Relatorios rel = AdminController.RelatorioUsuarioOcorrencia(cpf,con);
			                			showUsuario(rel.getUsuario()); 
			                			rel.getOcorrencias().forEach((ocorrencia)->{
			                				showOcorrenciaObjeto(ocorrencia);
			                			});
			                			
			                		}else {
			                			System.out.println("Erro, CPF não existe");
			                		}
									
									break;

								}

							}
		                }while(opad > 0 && opad <4);
		                break;
		            }
		        }
		    } catch (SQLException ex) {
		        System.out.println("Erro de SQL: " + ex.getMessage());  
		    } catch (Exception e) {
		        System.out.println("Erro inesperado: " + e.getMessage());  
		    }

		} while (op > 0 && op < 5);  

		con.close(); 

	}
	


	public static int menu () {
		System.out.println("");
        System.out.println("Informe o número da opção que desejas executar: ");
        System.out.println("1 - Registro de Usuario");
        System.out.println("2 - Login Usuario");
        System.out.println("3 - Registro de Agente");
        System.out.println("4 - Login Agente");
        System.out.println("5 - Admin");
        System.out.println("Digite qualquer outro valor para sair");
        System.out.print("Sua opção: ");
        Scanner input = new Scanner(System.in);
        int i = input.nextInt();
        System.out.println("-------------------------\n");
        return i;
    }
	
	
	public static int menuUsuario() {
	    System.out.println("");
	    System.out.println("Informe o número da opção que desejas executar: ");
	    System.out.println("1 - Cadastrar objeto");
	    System.out.println("2 - Remover objeto");
	    System.out.println("3 - Lista de objetos");
	    System.out.println("4 - Registrar ocorrência");
	    System.out.println("5 - Remover ocorrência");
	    System.out.println("6 - Lista de ocorrências");
	    System.out.println("7 - Informações da conta");
	    System.out.println("8 - Editar conta");
	    System.out.println("9 - Remover conta");

	    System.out.println("Digite qualquer outro valor para sair");
	    System.out.print("Sua opção: ");
	    Scanner input = new Scanner(System.in);
        int i = input.nextInt();
        System.out.println("-------------------------\n");
        return i;
	}

	
	public static int menuAgente() {
	    System.out.println("");
	    System.out.println("Informe o número da opção que desejas executar: ");
	    System.out.println("1 - Lista de ocorrências");
	    System.out.println("2 - Atender ocorrência");
	    System.out.println("3 - Minhas Ocorrencias");
	    System.out.println("4 - Atualizar minhas ocorrência");
	    System.out.println("5 - Informações da conta");
	    System.out.println("6 - Editar conta");
	    System.out.println("7 - Remover conta");

	    System.out.println("Digite qualquer outro valor para sair");
	    System.out.print("Sua opção: ");
	    Scanner input = new Scanner(System.in);
        int i = input.nextInt();
        System.out.println("-------------------------\n");
        return i;
	}

	
	public static int menuAdmin() {
	    System.out.println("");
	    System.out.println("Informe o número da opção que desejas executar: ");
	    System.out.println("1 - Relatorio de Usuários e seus Objetos");
	    System.out.println("2 - Relatorio de Agentes e suas Ocorrencias");
	    System.out.println("3 - Relatorio das ocorrencias de Usuario");
	    System.out.println("Digite qualquer outro valor para sair");
	    System.out.print("Sua opção: ");
	    Scanner input = new Scanner(System.in);
        int i = input.nextInt();
        System.out.println("-------------------------\n");
        return i;
	}
	
	public static void showObjeto(Objetos obj) {
		System.out.println("ID: " + obj.getId());
        System.out.println("Numero de Serie: " + obj.getNumserie());
        System.out.println("Marca: " + obj.getMarca());
        System.out.println("Categoria: " + obj.getCategoria());
        System.out.println("Cor: " + obj.getCor());
        System.out.println("Modelo: " + obj.getModelo());
        System.out.println("Observação: " + obj.getObservacao());
        System.out.println("----------------------------\n");
	}
	
	public static void showOcorrencia(Ocorrencias o) {
		System.out.println("ID: " + o.getId());
        System.out.println("Data: " + o.getData());
        System.out.println("Rua: " + o.getRua());
        System.out.println("Bairro: " + o.getBairro());
        System.out.println("Numero: " + o.getNumero());
        System.out.println("Descrição: " + o.getDescricao());
        System.out.println("Providencias: " + o.getProvidencias());
        System.out.println("----------------------------\n");
	}
	
	public static void showUsuario(Usuarios usuario) {
		System.out.println("CPF: " + usuario.getCpf());
		System.out.println("Data Nascimento: " + usuario.getDtNasc());
		System.out.println("Nome: " + usuario.getNome());
		System.out.println("Email: " + usuario.getEmail());
		System.out.println("Telefone: " + usuario.getTelefone());
		System.out.println("Senha: " + usuario.getPassword());
        System.out.println("----------------------------\n");
	}
	
	public static void showAgente(Agentes agentes) {
		System.out.println("Matricula: " + agentes.getMatricula());
		System.out.println("Nome: " + agentes.getNome());
		System.out.println("Senha: " + agentes.getPassword());
        System.out.println("----------------------------\n");
	}
	
	private static void showOcorrenciaObjeto(Ocorrencias o) {
		System.out.println("ID: " + o.getId());
        System.out.println("Data: " + o.getData());
        System.out.println("Rua: " + o.getRua());
        System.out.println("Bairro: " + o.getBairro());
        System.out.println("Numero: " + o.getNumero());
        System.out.println("Descrição: " + o.getDescricao());
        System.out.println("Providencias: " + o.getProvidencias());
        
        System.out.println("\nObjeto \n");
		System.out.println("ID: " + o.getObjeto().getId());
        System.out.println("Numero de Serie: " + o.getObjeto().getNumserie());
        System.out.println("Marca: " + o.getObjeto().getMarca());
        System.out.println("Categoria: " + o.getObjeto().getCategoria());
        System.out.println("Cor: " + o.getObjeto().getCor());
        System.out.println("Modelo: " + o.getObjeto().getModelo());
        System.out.println("Observação: " + o.getObjeto().getObservacao());
        System.out.println("----------------------------\n");
        
	}
	

	
}
