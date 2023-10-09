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
import java.util.ArrayList;
import javax.swing.JOptionPane;
import model.Product;

/**
 *
 * @author Levy
 */
public class ProductDAO {
    
    Connection con;
    
    public void create(Product p) {
        con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        String sql = "INSERT INTO produtos(nome, preco) VALUES(?,?)";
        
        try {
            stmt = con.prepareStatement(sql);
            stmt.setString(1, p.getName());
            stmt.setDouble(2, p.getPrice());
            
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Salvo com sucesso");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
    
    public ArrayList<Product> read() {
        con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs;
        ArrayList<Product> list = new ArrayList<>();
        
        String sql = "SELECT * FROM `produtos`";
        
        try {
            stmt = con.prepareStatement(sql);
            rs = stmt.executeQuery();
            
            while(rs.next()) {
                Product p = new Product();
                p.setId(rs.getInt("id"));
                p.setName(rs.getString("nome"));
                p.setPrice(rs.getDouble("preco"));
                
                list.add(p);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
        
        return list;
    }
    
    public Product readSingle(int id) {
        con = ConnectionFactory.getConnection();
        Product p = new Product();
        
        PreparedStatement stmt = null;
        
        String sql = "SELECT * FROM `produtos` WHERE id = ?";
        
        try {
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            
            if(rs.next()) {
                p.setId(rs.getInt("id"));
                p.setName(rs.getString("nome"));
                p.setPrice(rs.getDouble("preco"));
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
        
        return p;
    }
    
    public void update(Product p) {
        con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        String sql = "UPDATE produtos SET nome=?, preco=? WHERE id = ?";
        
        try {
            stmt = con.prepareStatement(sql);
            stmt.setString(1, p.getName());
            stmt.setDouble(2, p.getPrice());
            stmt.setInt(3, p.getId());
            
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Salvo com sucesso");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
    
    public void delete(int id) {
        con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        String sql = "DELETE FROM produtos WHERE id =?";
        
        try {
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, id);
            
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Produto deletado com sucesso");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
}
