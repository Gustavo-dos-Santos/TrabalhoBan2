package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import entity.Proprietario;

public class ProprietarioModel {

    public static void create(Proprietario p, Connection con) throws SQLException {
        String sql = "INSERT INTO proprietario (cpf, objeto_id) VALUES (?, ?)";
        try (PreparedStatement st = con.prepareStatement(sql)) {
            st.setString(1, p.getCpf());
            st.setInt(2, p.getObjetoId());
            st.execute();
        }
    }

    public static List<Proprietario> listAll(Connection con) throws SQLException {
        String sql = "SELECT id, cpf, objeto_id FROM proprietario";
        List<Proprietario> proprietarios = new ArrayList<>();
        try (PreparedStatement st = con.prepareStatement(sql);
             ResultSet rs = st.executeQuery()) {
            while (rs.next()) {
                Proprietario p = new Proprietario();
                p.setId(rs.getInt("id"));
                p.setCpf(rs.getString("usuario_id"));
                p.setObjetoId(rs.getInt("objeto_id"));
                proprietarios.add(p);
            }
        }
        return proprietarios;
    }

    public static void update(Proprietario p, Connection con) throws SQLException {
        String sql = "UPDATE proprietario SET cpf = ?, objeto_id = ? WHERE id = ?";
        try (PreparedStatement st = con.prepareStatement(sql)) {
            st.setString(1, p.getCpf());
            st.setInt(2, p.getObjetoId());
            st.setInt(3, p.getId());
            st.executeUpdate();
        }
    }

    public static void delete(int id, Connection con) throws SQLException {
        String sql = "DELETE FROM proprietario WHERE id = ?";
        try (PreparedStatement st = con.prepareStatement(sql)) {
            st.setInt(1, id);
            st.execute();
        }
    }

    public static List<Proprietario> listAllById(String cpf, Connection con) throws SQLException {
        List<Proprietario> proprietarios = new ArrayList<>();

        String sql = "SELECT id, cpf , objeto_id FROM proprietario WHERE id = ?";

        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, cpf);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Proprietario proprietario = new Proprietario();
                    proprietario.setId(rs.getInt("id"));
                    proprietario.setCpf(rs.getString("cpf"));
                    proprietario.setObjetoId(rs.getInt("objeto_id"));

                    proprietarios.add(proprietario);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e; 
        }
        return proprietarios;
    }
    
    public static List<Proprietario> listAllByCpf(String cpf, Connection con) throws SQLException {
        List<Proprietario> proprietarios = new ArrayList<>();

        String sql = "SELECT id, cpf , objeto_id FROM proprietario WHERE cpf = ?";

        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, cpf);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Proprietario proprietario = new Proprietario();
                    proprietario.setId(rs.getInt("id"));
                    proprietario.setCpf(rs.getString("cpf"));
                    proprietario.setObjetoId(rs.getInt("objeto_id"));

                    proprietarios.add(proprietario);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e; 
        }
        return proprietarios;
    }

    public static Proprietario findById(int proprietarioId, Connection con) throws SQLException {
        Proprietario proprietario = null;

        String sql = "SELECT id, cpf, objeto_id FROM proprietario WHERE id = ?";

        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, proprietarioId);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    proprietario = new Proprietario();
                    proprietario.setId(rs.getInt("id"));
                    proprietario.setCpf(rs.getString("cpf"));
                    proprietario.setObjetoId(rs.getInt("objeto_id"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e; 
        }

        return proprietario;
    }

    public static Proprietario findByObjetoId(int objetoId, Connection con) throws SQLException {
        Proprietario proprietario = null;

        String sql = "SELECT id, cpf, objeto_id FROM proprietario WHERE objeto_id = ?";

        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, objetoId);  

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    proprietario = new Proprietario();
                    proprietario.setId(rs.getInt("id"));
                    proprietario.setCpf(rs.getString("cpf"));
                    proprietario.setObjetoId(rs.getInt("objeto_id"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;  
        }

        return proprietario;
    }

    public static void deleteId(int proprietarioId, Connection con) throws SQLException {
        String sql = "DELETE FROM proprietario WHERE id = ?";

        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, proprietarioId); 
            
            int rowsAffected = stmt.executeUpdate();
            
            if (rowsAffected > 0) {
                System.out.println("Proprietário com ID " + proprietarioId + " deletado com sucesso.");
            } else {
                System.out.println("Nenhum proprietário encontrado com o ID " + proprietarioId);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao deletar proprietário: " + e.getMessage());
            throw e;
        }
    }



}
