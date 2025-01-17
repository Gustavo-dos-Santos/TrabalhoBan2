package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import entity.Responsavel;

public class ResponsavelModel {

	public static void create(Responsavel r, Connection con) throws SQLException {
	    String sql = "INSERT INTO responsavel (matricula, ocorrencia_id) VALUES (?, ?)";

	    try (PreparedStatement st = con.prepareStatement(sql)) {
	        st.setString(1, r.getAgenteId());
	        st.setInt(2, r.getOcorrenciaId()); 

	        int rowsAffected = st.executeUpdate(); 

	        if (rowsAffected > 0) {
	            System.out.println("Responsável inserido com sucesso!");
	        } else {
	            System.out.println("Nenhuma linha foi inserida.");
	        }
	    } catch (SQLException e) {
	        e.printStackTrace(); 
	        throw e;  
	    }
	}


    public static List<Responsavel> listAll(Connection con) throws SQLException {
        String sql = "SELECT id, matricula, ocorrencia_id FROM responsavel";
        List<Responsavel> responsaveis = new ArrayList<>();
        try (PreparedStatement st = con.prepareStatement(sql);
             ResultSet rs = st.executeQuery()) {
            while (rs.next()) {
                Responsavel r = new Responsavel();
                r.setId(rs.getInt("id"));
                r.setAgenteId(rs.getString("matricula"));
                r.setOcorrenciaId(rs.getInt("ocorrencia_id"));
                responsaveis.add(r);
            }
        }
        return responsaveis;
    }
    
    public static List<Responsavel> listAllByMatricula(String matricula, Connection con) throws SQLException {
        String sql = "SELECT id, matricula, ocorrencia_id FROM responsavel WHERE matricula = ?";
        List<Responsavel> responsaveis = new ArrayList<>();
        
        try (PreparedStatement st = con.prepareStatement(sql)) {
            st.setString(1, matricula); 

            try (ResultSet rs = st.executeQuery()) { 
                while (rs.next()) {
                    Responsavel r = new Responsavel();
                    r.setId(rs.getInt("id"));
                    r.setAgenteId(rs.getString("matricula"));
                    r.setOcorrenciaId(rs.getInt("ocorrencia_id"));
                    responsaveis.add(r); 
                }
            }
        }
        return responsaveis;
    }


    public static void update(Responsavel r, Connection con) throws SQLException {
        String sql = "UPDATE responsavel SET matricula = ?, ocorrencia_id = ? WHERE id = ?";
        try (PreparedStatement st = con.prepareStatement(sql)) {
            st.setString(1, r.getAgenteId());
            st.setInt(2, r.getOcorrenciaId());
            st.setInt(3, r.getId());
            st.executeUpdate();
        }
    }

    public static void delete(String id, Connection con) throws SQLException {
        String sql = "DELETE FROM responsavel WHERE id = ?";
        try (PreparedStatement st = con.prepareStatement(sql)) {
            st.setString(1, id);
            st.execute();
        }
    }


    public static void deleteAllByMatricula(String matricula, Connection con) throws SQLException {
        String sql = "DELETE FROM responsavel WHERE matricula = ?";

        try (PreparedStatement st = con.prepareStatement(sql)) {
            st.setString(1, matricula);  

            int rowsAffected = st.executeUpdate();  

            if (rowsAffected > 0) {
                System.out.println(rowsAffected + " registro(s) deletado(s) com sucesso.");
            } else {
                System.out.println("Nenhum registro encontrado para a matrícula: " + matricula);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e; 
        }
    }

    public static boolean isMine(String matricula, int ocorrenciaId, Connection con) throws SQLException {
        String sql = "SELECT COUNT(*) FROM responsavel WHERE matricula = ? AND ocorrencia_id = ?";

        try (PreparedStatement st = con.prepareStatement(sql)) {
            st.setString(1, matricula); 
            st.setInt(2, ocorrenciaId); 

            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) {
                    int count = rs.getInt(1); 
                    return count > 0; 
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e; 
        }
        
        return false;
    }


}
