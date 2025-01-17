package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import entity.Objetos;
import entity.Proprietario;
import entity.Usuarios;

public class UsuariosModel {

    public static void create(Usuarios u, Connection con) throws SQLException {
        String sql = "INSERT INTO usuarios (cpf, nome, dtNasc, email, telefone, password) VALUES (?, ?, ?, ?, ?,?)";
        try (PreparedStatement st = con.prepareStatement(sql)) {
            st.setString(1, u.getCpf());
            st.setString(2, u.getNome());
            st.setString(3, u.getDtNasc());
            st.setString(4, u.getEmail());
            st.setString(5, u.getTelefone());
            st.setString(6, u.getPassword());
            st.execute();
        }
    }
    
    public static Usuarios findByCPF(String CPF, Connection con) throws SQLException {
        String sql = "SELECT * FROM usuarios WHERE CPF = ?";
        try (PreparedStatement st = con.prepareStatement(sql)) {
            st.setString(1, CPF);
            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) {
                    Usuarios u = new Usuarios();
                    u.setCpf(rs.getString("CPF"));
                    u.setNome(rs.getString("nome"));
                    u.setEmail(rs.getString("email"));
                    u.setDtNasc(rs.getString("dtnasc"));
                    u.setTelefone(rs.getString("telefone"));
                    u.setPassword(rs.getString("password"));
                    return u;
                } else {
                    return null; 
                }
            }
        }
    }
    
    public static Usuarios login(String CPF, String password, Connection con)throws SQLException {
        String sql = "SELECT * FROM usuarios WHERE CPF = ? and password = ?";
        try (PreparedStatement st = con.prepareStatement(sql)) {
            st.setString(1, CPF);
            st.setString(2, password);
            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) {
                    Usuarios u = new Usuarios();
                    u.setCpf(rs.getString("CPF"));
                    u.setNome(rs.getString("nome"));
                    u.setEmail(rs.getString("email"));
                    u.setDtNasc(rs.getString("dtnasc"));
                    u.setTelefone(rs.getString("telefone"));
                    u.setPassword(rs.getString("password"));
                    return u;
                } else {
                    return null; 
                }
            }
        }
    }
   
    public static List<Usuarios> listAll(Connection con) throws SQLException {
        String sql = "SELECT cpf, nome, dtNasc, email, telefone, password FROM usuarios";
        List<Usuarios> usuarios = new ArrayList<>();
        try (PreparedStatement st = con.prepareStatement(sql);
             ResultSet rs = st.executeQuery()) {
            while (rs.next()) {
                Usuarios u = new Usuarios();
                u.setCpf(rs.getString("cpf"));
                u.setNome(rs.getString("nome"));
                u.setDtNasc(rs.getString("dtNasc"));
                u.setEmail(rs.getString("email"));
                u.setTelefone(rs.getString("telefone"));
                u.setPassword(rs.getString("password"));

                usuarios.add(u);
            }
        }
        return usuarios;
    }

    public static void update(Usuarios u, Connection con) throws SQLException {
        String sql = "UPDATE usuarios SET nome = ?, dtNasc = ?, email = ?, telefone = ?, password = ? WHERE cpf = ?";
        try (PreparedStatement st = con.prepareStatement(sql)) {
            st.setString(1, u.getNome());
            st.setString(2, u.getDtNasc());
            st.setString(3, u.getEmail());
            st.setString(4, u.getTelefone());
            st.setString(5, u.getPassword());
            st.setString(6, u.getCpf());

            st.executeUpdate();
        }
    }

    public static void delete(String cpf, Connection con) throws SQLException {
        String sql = "DELETE FROM usuarios WHERE cpf = ?";
        try (PreparedStatement st = con.prepareStatement(sql)) {
            st.setString(1, cpf);
            st.execute();
        }
    }
}
