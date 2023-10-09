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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.Product;
import model.User;

/**
 *
 * @author Levy
 */
public class UserDAO {
    Connection con;
    
    public UserDAO() {
        con = ConnectionFactory.getConnection();
    }
    
    public void create(User u) {
        PreparedStatement stmt = null;
        
        String sql = "INSERT INTO usuarios(nome, senha, cargo) VALUES(?,?,?)";
        
        try {
            stmt = con.prepareStatement(sql);
            stmt.setString(1, u.getName());
            stmt.setString(2, u.getPassword());
            stmt.setInt(3, u.getRole());
            
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Salvo com sucesso");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
    
    public ArrayList<User> read() {
        PreparedStatement stmt = null;
        ResultSet rs;
        ArrayList<User> list = new ArrayList<>();
        
        String sql = "SELECT * FROM usuarios";
        
        try {
            stmt = con.prepareStatement(sql);
            rs = stmt.executeQuery();
            
            while(rs.next()) {
                User w = new User();
                w.setId(rs.getInt("id"));
                w.setName(rs.getString("nome"));
                w.setRole(rs.getInt("cargo"));
                
                list.add(w);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
        
        return list;
    }

    
    
    public User login(String name, String password) {
        User u = new User();
        
        PreparedStatement stmt = null;
        
        String sql = "SELECT * FROM usuarios WHERE nome=? AND senha=?";
        
        try {
            stmt = con.prepareStatement(sql);
            stmt.setString(1, name);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();
            
            if(rs.next()) {
                u.setId(rs.getInt("id"));
                u.setName(rs.getString("nome"));
                u.setPassword(rs.getString("senha"));
                u.setRole(rs.getInt("cargo"));
            }
        }catch(SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro: " + ex);
        }
        
        return u;
    }
}
