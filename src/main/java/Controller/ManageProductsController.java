/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import View.ManageProductsView;
import java.awt.event.ActionEvent;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.Product;
import model.dao.ProductDAO;

/**
 *
 * @author Levy
 */
public class ManageProductsController {
    private ManageProductsView view;
    private ProductDAO productdao;
    
    public ManageProductsController() {
        view = new ManageProductsView();
        view.setVisible(true);
        listValues();
        
        view.getBtnInsert().addActionListener((ActionEvent e)->{
            insertProduct();
        });
        
        view.getTbProducts().addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                view.getBtnDelete().setEnabled(true);
                view.getBtnEdit().setEnabled(true);
            }

            @Override
            public void focusLost(FocusEvent e) {}
        });
        
        view.getBtnDelete().addActionListener((ActionEvent e) ->{
            delete();
        });
        
        view.getBtnEdit().addActionListener((ActionEvent e) -> {
            view.dispose();
            edit();
        });
    }
    
    private void insertProduct(){
        Product p = new Product();
        productdao = new ProductDAO();
                
        p.setName(view.getTxtName().getText());
        p.setPrice(Double.parseDouble(view.getTxtPrice().getText()));
        
        productdao.create(p);
        
        listValues();
    }
    
    private void listValues() {
        try {            
            Product p = new Product();
            productdao = new ProductDAO();
        
            DefaultTableModel model = (DefaultTableModel) view.getTbProducts().getModel();
            model.setNumRows(0);
            
            ArrayList<Product> list = productdao.read();
            
            for(int i = 0; i < list.size(); i++) {
                model.addRow(new Object[]{
                   list.get(i).getId(),
                   list.get(i).getName(),
                   list.get(i).getPrice(),
                });
            }
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao listar valores: " + e);
        }
    }
    
    private void delete(){
        int coluna = view.getTbProducts().getSelectedRow();
        
        if(coluna == -1) {
            JOptionPane.showMessageDialog(null, "Selecione uma coluna para editar");
        }else {
            productdao.delete(Integer.parseInt(view.getTbProducts().getValueAt(coluna,0).toString()));

            listValues();
        }
    }
    
    private void edit(){
        int coluna = view.getTbProducts().getSelectedRow();
        
        if(coluna == -1) {
            JOptionPane.showMessageDialog(null, "Selecione uma coluna para editar");
        }else {
            int id = Integer.valueOf(view.getTbProducts().getValueAt(coluna,0).toString());
            
            System.out.println(id);
            
            new EditProductController(id);
            listValues();
        }
    }
    
}
