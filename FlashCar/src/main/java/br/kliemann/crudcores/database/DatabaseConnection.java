package br.kliemann.crudcores.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DatabaseConnection {
    
    public static Connection getConnection() throws SQLException {
        
        return DriverManager.getConnection(
                "jdbc:h2:C:\\Users\\Guilherme\\Desktop\\studing\\ADS UNIPAR\\programacao_orientada_objetos_ads\\Quarto Semestre\\H2Database\\flashcar", "", "");
        
    }
    
    public static void closeConnection(Connection con) {
        
        try {
            if(con != null) 
                con.close();
        } catch(SQLException ex) {
            Logger.getLogger(DatabaseConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
     public static void closeConnection(Connection con, PreparedStatement ps) {
        
         closeConnection(con);
         
        try {
            if(ps != null) 
                ps.close();
        } catch(SQLException ex) {
            Logger.getLogger(DatabaseConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
     
     public static void closeConnection(Connection con, PreparedStatement ps, ResultSet rs) {
        
         closeConnection(con, ps);
         
        try {
            if(rs != null) 
                rs.close();
        } catch(SQLException ex) {
            Logger.getLogger(DatabaseConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
