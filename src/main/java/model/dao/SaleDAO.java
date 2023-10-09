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
import model.Sale;

/**
 *
 * @author Levy
 */
public class SaleDAO {
    Connection con;
    
    public ArrayList<Sale> read() {
        con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs;
        ArrayList<Sale> list = new ArrayList<>();
        
        String sql = "SELECT * FROM `vendas`";
        
        try {
            stmt = con.prepareStatement(sql);
            rs = stmt.executeQuery();
            
            while(rs.next()) {
                Sale sale = new Sale();
                sale.setId(rs.getInt("id"));
                sale.setValue(rs.getDouble("valor"));
                sale.setDate(rs.getTimestamp("data").toLocalDateTime());
                
                list.add(sale);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
        
        return list;
    }
    
    public int create(double value) {
        con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        String sql = "INSERT INTO vendas(valor, data) VALUES(?, NOW())";
        
        try {
            stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setDouble(1, value);
            
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
    
    public void createItemRelaction(Sale s, Item i) {
        con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        String sql = "INSERT INTO vendas_rel_itens(id_venda, id_item) VALUES(?,?)";
        
        try {
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, s.getId());
            stmt.setInt(2, i.getId());
            
            stmt.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
}
