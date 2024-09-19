package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import entity.Objetos;
import entity.Proprietario;

public class ObjetosModel {

	public static Objetos create(Objetos o, Connection con) throws SQLException {
	    String sql = "INSERT INTO objetos (num_serie, categoria, marca, modelo, cor, observacoes) VALUES (?, ?, ?, ?, ?, ?)";

	    try (PreparedStatement st = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
	        st.setString(1, o.getNumserie());
	        st.setString(2, o.getCategoria());
	        st.setString(3, o.getMarca());
	        st.setString(4, o.getModelo());
	        st.setString(5, o.getCor());
	        st.setString(6, o.getObservacao());
	        
	        int rowsAffected = st.executeUpdate();
	        
	        if (rowsAffected > 0) {
	            try (ResultSet generatedKeys = st.getGeneratedKeys()) {
	                if (generatedKeys.next()) {
	                    int id = generatedKeys.getInt(1); 
	                    o.setId(id); 
	                }
	            }
	        }
	    } catch (SQLException e) {

	        System.err.println("Erro ao cadastrar o objeto: " + e.getMessage());
	        throw e; 
	    }
	    
	    return o;
	}


    public static List<Objetos> listAll(Connection con) throws SQLException {
        String sql = "SELECT numserie, categoria, marca, modelo, cor, observacao FROM objetos";
        List<Objetos> objetos = new ArrayList<>();
        try (PreparedStatement st = con.prepareStatement(sql);
             ResultSet rs = st.executeQuery()) {
            while (rs.next()) {
                Objetos o = new Objetos();
                o.setNumserie(rs.getString("numserie"));
                o.setCategoria(rs.getString("categoria"));
                o.setMarca(rs.getString("marca"));
                o.setModelo(rs.getString("modelo"));
                o.setCor(rs.getString("cor"));
                o.setObservacao(rs.getString("observacao"));
                objetos.add(o);
            }
        }
        return objetos;
    }

    public static void update(Objetos o, Connection con) throws SQLException {
        String sql = "UPDATE objetos SET categoria = ?, marca = ?, modelo = ?, cor = ?, observacao = ? WHERE numserie = ?";
        try (PreparedStatement st = con.prepareStatement(sql)) {
            st.setString(1, o.getCategoria());
            st.setString(2, o.getMarca());
            st.setString(3, o.getModelo());
            st.setString(4, o.getCor());
            st.setString(5, o.getObservacao());
            st.setString(6, o.getNumserie());
            st.executeUpdate();
        }
    }

    public static void delete(int objetoId, Connection con) throws SQLException {
        String sql = "DELETE FROM objetos WHERE id = ?";
        try (PreparedStatement st = con.prepareStatement(sql)) {
            st.setInt(1, objetoId);
            st.execute();
        }
    }


    public static Objetos findById(int id, Connection con) throws SQLException {
        Objetos ob = null;

        String sql = "SELECT id, num_serie, categoria, marca, modelo, cor, observacoes FROM objetos WHERE id = ?";

        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    ob = new Objetos();
                    ob.setId(rs.getInt("id"));
                    ob.setNumserie(rs.getString("num_serie"));
                    ob.setCategoria(rs.getString("categoria"));
                    ob.setMarca(rs.getString("marca"));
                    ob.setModelo(rs.getString("modelo"));
                    ob.setCor(rs.getString("cor"));
                    ob.setObservacao(rs.getString("observacoes"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e; 
        }
        return ob;
    }


    public static List<Objetos> listAllByCpf(String cpf, Connection con) throws SQLException {
        List<Objetos> objetos = new ArrayList<>();

        String sql = "SELECT obj.id, obj.num_serie, obj.categoria, obj.marca, obj.modelo, obj.cor, obj.observacoes " +
                     "FROM objetos obj " +
                     "JOIN proprietario p ON obj.id = p.objeto_id " +
                     "WHERE p.cpf = ?";

        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, cpf);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Objetos objeto = new Objetos();
                    objeto.setId(rs.getInt("id"));
                    objeto.setNumserie(rs.getString("num_serie"));
                    objeto.setCategoria(rs.getString("categoria"));
                    objeto.setMarca(rs.getString("marca"));
                    objeto.setModelo(rs.getString("modelo"));
                    objeto.setCor(rs.getString("cor"));
                    objeto.setObservacao(rs.getString("observacoes"));

                    objetos.add(objeto); 
                }
            }
        } catch (SQLException e) {
            System.err.println("Erro ao listar objetos por CPF: " + e.getMessage());
            throw e;  
        }

        return objetos;
    }



}
