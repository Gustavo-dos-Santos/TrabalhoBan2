package controller;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import entity.Agentes;
import entity.Responsavel;
import model.AgentesModel;

public class AgenteController {
    private static Scanner input = new Scanner(System.in);  

	public static void agenteRegister(Connection con) throws SQLException {
        Agentes agente = new Agentes();

    
        System.out.println("Matrícula (6 dígitos): ");
        String matricula = input.next();
        
        System.out.println("Nome (6 dígitos): ");
        String nome = input.next();
   
        System.out.println("Senha (mínimo 6 caracteres): ");
        String pass = input.next();
            
        agente.setMatricula(matricula);
        agente.setNome(nome);
        agente.setPassword(pass);
        AgentesModel.create(agente, con);
        System.out.println("Agente registrado com sucesso.");
    }

	public static void update(Agentes agente, Connection con) throws SQLException {

		System.out.println("Senha:");
		String pass = input.next();
		agente.setPassword(pass);
		
		AgentesModel.update(agente,con );
	}

	public static void delete(Agentes agente, Connection con) throws SQLException {
		ResponsavelController.deleteByMatricula(agente.getMatricula(),con);
		AgentesModel.delete(agente.getMatricula(), con);
		System.out.println("Deletado com sucesso");
	}

	public static List<Agentes> listAll(Connection con) throws SQLException {
		return  AgentesModel.listAll(con);
	}

	public static Agentes findByMatricula(String matricula, Connection con) throws SQLException {
		return AgentesModel.findByMatricula(matricula, con);
	}
}
