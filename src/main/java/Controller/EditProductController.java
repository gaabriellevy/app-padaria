/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import View.EditProductView;
import model.Product;
import model.dao.ProductDAO;

/**
 *
 * @author Levy
 */
public class EditProductController {
    private EditProductView view;
    private ProductDAO productdao;
    
    public EditProductController(int id) {
        view = new EditProductView();
        view.setVisible(true);
        
        updateFields(id);
        
        view.getBtnSend().addActionListener((e) -> {
            edit(id);
        });
    }
    
    private void updateFields(int id) {
        productdao = new ProductDAO();
        Product p = productdao.readSingle(id);
        
        view.getTxtName().setText(p.getName());
        view.getTxtPrice().setText(String.valueOf(p.getPrice()));
    }
    
    private void edit(int id) {
        productdao = new ProductDAO();
        Product p = productdao.readSingle(id);
        
        p.setName(view.getTxtName().getText());
        p.setPrice(Double.parseDouble(view.getTxtPrice().getText()));

        productdao.update(p);
        
        view.dispose();
        new ManageProductsController();
    }
    
}
