/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import View.SaleView;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.Product;
import model.Sale;
import model.Item;
import model.dao.ProductDAO;
import model.dao.SaleDAO;
import model.dao.ItemDAO;

/**
 *
 * @author Levy
 */
public class SaleController {
    private SaleView view;
    private SaleDAO saledao;
    private ItemDAO saleitemdao;
    private ProductDAO productdao;
    private ArrayList<Item> saleList;
    
    public SaleController() {
        productdao = new ProductDAO();
        
        view = new SaleView();
        view.setVisible(true);
        
        saleList = new ArrayList<>();
        
        ArrayList<Product> products = productdao.read();
        
        listValues(products);
        
        view.getBtnAdd().addActionListener((e) -> {
            addItem(products);
        });
        
        view.getBtnFinish().addActionListener((e) -> {
            finish();
        });
    }
    
    private void addItem(ArrayList<Product> products) {
        Item item = new Item();
        
        Product p = products.get(view.getComboBoxProduct().getSelectedIndex());
 
        
        item.setId(saleList.size()+1);
        item.setName(p.getName());
        item.setPrice(p.getPrice());
        item.setQnt(Integer.parseInt(view.getTxtQnt().getText()));
                
        saleList.add(item);
        view.getTxtQnt().setText("");
        
        DefaultTableModel model = (DefaultTableModel) view.getTbSale().getModel();
        
        model.addRow(new Object[]{
            item.getName(),
            item.getQnt()
         });
    }
    
    private void finish() {
        Sale sale = new Sale();
        saledao = new SaleDAO();
        Item item = new Item();
        saleitemdao = new ItemDAO();
        
        saleList.forEach((t) -> {
            sale.setValue(sale.getValue() + (t.getPrice() * t.getQnt()));
        });
                
        sale.setId(saledao.create(sale.getValue())); // cria a venda na tabela
        
        saleList.forEach((t) -> { // para cada item da venda...
            t.setId(saleitemdao.create(t)); // cria o item na tabela
            saledao.createItemRelaction(sale, t); // relaciona o item com a venda
        });
        
        // zera a view
        saleList = new ArrayList<>();
        
        DefaultTableModel model = (DefaultTableModel) view.getTbSale().getModel();
        model.setNumRows(0);
        
        JOptionPane.showMessageDialog(null, "Venda realizada com sucesso!");
    }
    
    private void listValues(ArrayList<Product> products) {
        try {           
            Sale s = new Sale();
            saledao = new SaleDAO();
            Product p = new Product();
            productdao = new ProductDAO();
        
            DefaultComboBoxModel model = (DefaultComboBoxModel) view.getComboBoxProduct().getModel();
                        
            for(int i = 0; i < products.size(); i++) {
                model.addElement(products.get(i).getName() + " - $" + products.get(i).getPrice());
            }
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao listar valores: " + e);
        }
    }
}
