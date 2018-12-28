/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Shubham Srivastava
 */
public class MySQLConnection {
    Connection conn;
    Statement stmt;
    
    public MySQLConnection(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost/messenger", "shubham", "Shubh");
            stmt = conn.createStatement();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MySQLConnection.class.getName()).log(Level.SEVERE, null, ex);
        } catch( SQLException ex ){
            ex.printStackTrace();
        }
        
    }
    
    public ResultSet get(String sql){
        ResultSet rs = null;
        try{
            rs = stmt.executeQuery(sql);
        } catch( Exception ex ){
            ex.printStackTrace();
        }
        return rs;
    }
    
    public int put(String sql){
        int count = 0;
        try{
            count = stmt.executeUpdate(sql);
        } catch( Exception ex ){
            ex.printStackTrace();
        }
        return count;
    }
}
