package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import entity.Ocorrencias;

public class OcorrenciasModel {

    public static void create(Ocorrencias ocorrencia, Connection con) throws SQLException {
        String sql = "INSERT INTO ocorrencias (proprietario_id, data, rua, bairro, descricao, providencias, numero) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, ocorrencia.getProprietario_id());
            stmt.setString(2, ocorrencia.getData());
            stmt.setString(3, ocorrencia.getRua());
            stmt.setString(4, ocorrencia.getBairro());
            stmt.setString(5, ocorrencia.getDescricao());
            stmt.setString(6, ocorrencia.getProvidencias());
            stmt.setString(7, ocorrencia.getNumero());
            stmt.executeUpdate();
        }
    }

    public static Ocorrencias findById(int id, Connection con) throws SQLException {
        Ocorrencias ocorrencia = null;
        String sql = "SELECT * FROM ocorrencias WHERE id = ?";

        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    ocorrencia = new Ocorrencias();
                    ocorrencia.setId(rs.getInt("id"));
                    ocorrencia.setProprietario_id(rs.getInt("proprietario_id"));
                    ocorrencia.setData(rs.getString("data"));
                    ocorrencia.setRua(rs.getString("rua"));
                    ocorrencia.setBairro(rs.getString("bairro"));
                    ocorrencia.setDescricao(rs.getString("descricao"));
                    ocorrencia.setProvidencias(rs.getString("providencias"));
                    ocorrencia.setNumero(rs.getString("numero"));
                }
            }
        }

        return ocorrencia;
    }
    
    public static Ocorrencias findByProprietarioId(int proprietarioId, Connection con) throws SQLException {
        Ocorrencias ocorrencia = null;
        String sql = "SELECT * FROM ocorrencias WHERE proprietario_id = ?";

        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, proprietarioId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    ocorrencia = new Ocorrencias();
                    ocorrencia.setId(rs.getInt("id"));
                    ocorrencia.setProprietario_id(rs.getInt("proprietario_id"));
                    ocorrencia.setData(rs.getString("data"));
                    ocorrencia.setRua(rs.getString("rua"));
                    ocorrencia.setBairro(rs.getString("bairro"));
                    ocorrencia.setDescricao(rs.getString("descricao"));
                    ocorrencia.setProvidencias(rs.getString("providencias"));
                    ocorrencia.setNumero(rs.getString("numero"));
                }
            }
        }

        return ocorrencia;
    }
    
    public static List<Ocorrencias> findOcorrenciasByCpf(String cpf, Connection con) throws SQLException {
        List<Ocorrencias> ocorrencias = new ArrayList<>();
        
        String sql = "SELECT o.id, o.proprietario_id, o.data, o.rua, o.bairro, o.descricao, o.providencias, o.numero " +
                     "FROM ocorrencias o " +
                     "JOIN proprietario p ON o.proprietario_id = p.id " +
                     "JOIN usuarios u ON p.cpf = u.cpf " +
                     "WHERE u.cpf = ?";

        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, cpf); 
            
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Ocorrencias ocorrencia = new Ocorrencias();
                    ocorrencia.setId(rs.getInt("id"));
                    ocorrencia.setProprietario_id(rs.getInt("proprietario_id"));
                    ocorrencia.setData(rs.getString("data"));
                    ocorrencia.setRua(rs.getString("rua"));
                    ocorrencia.setBairro(rs.getString("bairro"));
                    ocorrencia.setDescricao(rs.getString("descricao"));
                    ocorrencia.setProvidencias(rs.getString("providencias"));
                    ocorrencia.setNumero(rs.getString("numero"));
                    
                    ocorrencias.add(ocorrencia); 
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }

        return ocorrencias; 
    }



    public static List<Ocorrencias> listAll(Connection con) throws SQLException {
        List<Ocorrencias> listaOcorrencias = new ArrayList<>();
        String sql = "SELECT * FROM ocorrencias";

        try (Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Ocorrencias ocorrencia = new Ocorrencias();
                ocorrencia.setId(rs.getInt("id"));
                ocorrencia.setProprietario_id(rs.getInt("proprietario_id"));
                ocorrencia.setData(rs.getString("data"));
                ocorrencia.setRua(rs.getString("rua"));
                ocorrencia.setBairro(rs.getString("bairro"));
                ocorrencia.setDescricao(rs.getString("descricao"));
                ocorrencia.setProvidencias(rs.getString("providencias"));
                ocorrencia.setNumero(rs.getString("numero"));
                listaOcorrencias.add(ocorrencia);
            }
        }

        return listaOcorrencias;
    }

    public static void update(Ocorrencias ocorrencia, Connection con) throws SQLException {
        String sql = "UPDATE ocorrencias SET proprietario_id = ?, data = ?, rua = ?, bairro = ?, descricao = ?, providencias = ?, numero = ? WHERE id = ?";

        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, ocorrencia.getProprietario_id());
            stmt.setString(2, ocorrencia.getData());
            stmt.setString(3, ocorrencia.getRua());
            stmt.setString(4, ocorrencia.getBairro());
            stmt.setString(5, ocorrencia.getDescricao());
            stmt.setString(6, ocorrencia.getProvidencias());
            stmt.setString(7, ocorrencia.getNumero());
            stmt.setInt(8, ocorrencia.getId());
            stmt.executeUpdate();
        }
    }

    public static void delete(int id, Connection con) throws SQLException {
        String sql = "DELETE FROM ocorrencias WHERE id = ?";

        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, id);
            int rowsAffected = stmt.executeUpdate(); 

            if (rowsAffected > 0) {
                System.out.println("Ocorrência com ID " + id + " foi deletada com sucesso.");
            } else {
                System.out.println("Nenhuma ocorrência encontrada com o ID " + id);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao deletar a ocorrência: " + e.getMessage());
            throw e;  
        }
    }

    public static List<Ocorrencias> listByMatricula(String matricula, Connection con) throws SQLException {
        List<Ocorrencias> ocorrenciasList = new ArrayList<>();

        String sql = "SELECT o.id, o.data, o.rua, o.bairro, o.descricao, o.providencias, o.numero " +
                     "FROM ocorrencias o " +
                     "JOIN responsavel r ON o.id = r.ocorrencia_id " +
                     "WHERE r.matricula = ?";
        
        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, matricula);  
            
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Ocorrencias ocorrencia = new Ocorrencias();
                    ocorrencia.setId(rs.getInt("id"));
                    ocorrencia.setData(rs.getString("data"));
                    ocorrencia.setRua(rs.getString("rua"));
                    ocorrencia.setBairro(rs.getString("bairro"));
                    ocorrencia.setDescricao(rs.getString("descricao"));
                    ocorrencia.setProvidencias(rs.getString("providencias"));
                    ocorrencia.setNumero(rs.getString("numero"));

                    ocorrenciasList.add(ocorrencia);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;  
        }

        return ocorrenciasList;
    }



    
    
}
