package br.kliemann.crudcores.repository;

import br.kliemann.crudcores.database.DatabaseConnection;
import br.kliemann.crudcores.model.Cor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class CorRepository {

    private String INSERT = "insert into cor(descricao) values(?);";
    private String UPDATE = "update cor set descricao = ? where id = ?";
    private String DELETE = "delete cor where id = ?";
    private String FIND_BY_ID = "select id, descricao from cor where id = ?";
    private String FIND_ALL = "select id, descricao from cor";

    public void insert(Cor cor) throws SQLException {

        Connection conn = new DatabaseConnection().getConnection();
        PreparedStatement ps = null;
        
        try {
            ps = conn.prepareStatement(INSERT);
            ps.setString(1, cor.getDesc());
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Cor cadastrada com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar cor!");
        } finally {
            DatabaseConnection.closeConnection(conn, ps);
        }

    }
    
    public ArrayList<Cor> findAll() throws SQLException {

        Connection conn = new DatabaseConnection().getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<Cor> cores = new ArrayList<>();
        
        try {
            ps = conn.prepareStatement(FIND_ALL);
            rs = ps.executeQuery();
            while(rs.next()) {
                Cor cor = new Cor();
                cor.setId(rs.getInt("id"));
                cor.setDesc(rs.getString("descricao"));
                cores.add(cor);
            }
        } catch(SQLException ex) { 
            JOptionPane.showMessageDialog(null, "Erro ao listar cores!");
        } finally {
            DatabaseConnection.closeConnection(conn, ps, rs);
        }

        return cores;
        
    }
    
    public void delete(Cor cor) throws SQLException {

        Connection conn = new DatabaseConnection().getConnection();;
        PreparedStatement ps = null;
        
        try {
            ps = conn.prepareStatement(DELETE);
            ps.setInt(1, cor.getId());
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Cor excluida com sucesso!");
        } catch(SQLException ex) { 
            JOptionPane.showMessageDialog(null, "Erro ao excluir cor!");
        } finally {
            DatabaseConnection.closeConnection(conn, ps);
        }

    }
    
    public void update(Cor cor) throws SQLException {

        Connection conn = null;
        PreparedStatement ps = null;
        
        try {
            conn = new DatabaseConnection().getConnection();
            ps = conn.prepareStatement(UPDATE);
            ps.setString(1, cor.getDesc());
            ps.setInt(2, cor.getId());
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Cor atualizada com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar cor!");
        } finally {
            DatabaseConnection.closeConnection(conn, ps);
        }

    }
    
    public Cor findById(int id) throws SQLException {   

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Cor resultado = new Cor();
                
        try {

            conn = new DatabaseConnection().getConnection();

            ps = conn.prepareStatement(FIND_BY_ID);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            
            while(rs.next()){
                
                resultado.setDesc(rs.getString("descricao"));
                resultado.setId(rs.getInt("id"));
                
            }

        } finally {
            if (rs != null)
                rs.close();
            if (ps != null)
                ps.close();
            if (conn != null)
                conn.close();
        }
        
        return resultado;

    }

}
