package controller;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

import entity.Agentes;
import entity.Usuarios;
import model.AgentesModel;
import model.UsuariosModel;

public class LoginController {
    private static Scanner input = new Scanner(System.in);  
    
    public static Usuarios usuarioLogin(Connection con) throws SQLException {
        Usuarios us = null;
        while (us == null) {            
            System.out.println("Login Usuario");
            System.out.print("CPF: ");
            String cpf = input.next();

            System.out.print("Senha: ");
            String pass = input.next();

            us = UsuariosModel.login(cpf, pass, con);
            if (us == null) {
                System.out.println("CPF ou senha incorretos. Tente novamente.");
            }
        }
        return us;
    }

    public static Agentes agenteLogin(Connection con) throws SQLException {
        Agentes ag = null;    
        System.out.println("Login Agentes");
        System.out.print("Matrícula: ");
        String matricula = input.next();

        System.out.print("Senha: ");
        String pass = input.next();

        ag = AgentesModel.login(matricula, pass, con);
        if (ag == null) {
            System.out.println("Matrícula ou senha incorretos. Tente novamente.");
        }
        
        return ag;
    }


	public static void admin(Connection con) {
		// TODO Auto-generated method stub
		
	}
}
