package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import entity.Agentes;

public class AgentesModel {

    public static void create(Agentes a, Connection con) throws SQLException {
        String sql = "INSERT INTO agentes (matricula, nome, password) VALUES (?, ?, ?)";
        try (PreparedStatement st = con.prepareStatement(sql)) {
            st.setString(1, a.getMatricula());
            st.setString(2, a.getNome());
            st.setString(3, a.getPassword());
            st.execute();
        }
    }

    public static List<Agentes> listAll(Connection con) throws SQLException {
        String sql = "SELECT matricula, nome, password FROM agentes";
        List<Agentes> agentes = new ArrayList<>();
        try (PreparedStatement st = con.prepareStatement(sql);
             ResultSet rs = st.executeQuery()) {
            while (rs.next()) {
                Agentes a = new Agentes();
                a.setMatricula(rs.getString("matricula"));
                a.setNome(rs.getString("nome"));
                a.setPassword(rs.getString("password"));
                agentes.add(a);
            }
        }
        return agentes;
    }
    
    public static Agentes findByMatricula(String matricula, Connection con) throws SQLException {
        String sql = "SELECT * FROM agentes WHERE matricula = ?";
        try (PreparedStatement st = con.prepareStatement(sql)) {
            st.setString(1, matricula); 
            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) {
                    Agentes agente = new Agentes();
                    agente.setNome(rs.getString("nome"));  
                    agente.setMatricula(rs.getString("matricula"));  
                    agente.setPassword(rs.getString("password"));  
                    return agente;  
                } else {
                    return null;  
                }
            }
        }
    }


    public static void update(Agentes a, Connection con) throws SQLException {
        String sql = "UPDATE agentes SET password = ? WHERE matricula = ?";
        try (PreparedStatement st = con.prepareStatement(sql)) {
            st.setString(1, a.getPassword());
            st.setString(2, a.getMatricula());
            st.executeUpdate();
        }
    }

    public static void delete(String matricula, Connection con) throws SQLException {
        String sql = "DELETE FROM agentes WHERE matricula = ?";
        try (PreparedStatement st = con.prepareStatement(sql)) {
            st.setString(1, matricula);
            st.execute();
        }
    }

    public static Agentes login(String matricula, String pass, Connection con) throws SQLException {
        String sql = "SELECT * FROM agentes WHERE matricula = ? AND password = ?";
        try (PreparedStatement st = con.prepareStatement(sql)) {
            st.setString(1, matricula); 
            st.setString(2, pass);   

            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) {
                    Agentes agente = new Agentes();
                    agente.setNome(rs.getString("nome"));
                    agente.setMatricula(rs.getString("matricula"));
                    agente.setPassword(rs.getString("password"));
                    return agente;  
                } else {
                    return null;
                }
            }
        }
    }

}
