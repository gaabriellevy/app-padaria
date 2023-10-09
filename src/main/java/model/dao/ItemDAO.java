/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.dao;
import connection.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import model.Item;

/**
 *
 * @author Levy
 */
public class ItemDAO {
    Connection con;
    
    public int create(Item i) {
        con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        String sql = "INSERT INTO itens(nome, preco, qnt) VALUES(?,?, ?)";
        
        try {
            stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, i.getName());
            stmt.setDouble(2, i.getPrice());
            stmt.setInt(3,i.getQnt());
            
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
               return rs.getInt(1);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
        
        return 0;
    }
}
