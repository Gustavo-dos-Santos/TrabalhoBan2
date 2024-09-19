package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Conexao {
    private Connection con;

    private String url = "jdbc:mysql://localhost:3306/ban2";
    private String user = "root";
    private String password = "admin";
    private String driver = "com.mysql.cj.jdbc.Driver";

    public Conexao() {
        try {
            Class.forName(this.driver);
            con = DriverManager.getConnection(url, user, password);
            
            if (con != null) {
                System.out.println("Conexão estabelecida com sucesso!");
            }
        } catch (ClassNotFoundException ex) {
            System.err.println("Erro ao carregar o driver JDBC: " + ex.getMessage());
            ex.printStackTrace();
        } catch (SQLException ex) {
            System.err.println("Erro ao conectar com o banco de dados: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    public Connection getConnection() {
        return this.con;
    }

    public void closeConnection() {
        if (this.con != null) {
            try {
                this.con.close();
                System.out.println("Conexão fechada com sucesso.");
            } catch (SQLException ex) {
                Logger.getLogger(Conexao.class.getName()).log(Level.SEVERE, "Erro ao fechar a conexão: " + ex.getMessage(), ex);
                ex.printStackTrace();
            }
        } else {
            System.out.println("A conexão já está fechada ou não foi estabelecida.");
        }
    }
}
