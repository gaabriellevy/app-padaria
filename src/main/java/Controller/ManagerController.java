/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import View.ManagerView;

/**
 *
 * @author Levy
 */
public class ManagerController {
    private ManagerView view;
    
    public ManagerController() {
        view = new ManagerView();

        view.setVisible(true);
        
        view.getBtnInsertProduct().addActionListener((e) -> {
            new ManageProductsController();
        });
        
        view.getBtnCart().addActionListener((e) -> {
            new SaleController();
        });
        
        view.getBtnInsertUser().addActionListener((e) -> {
            new ManageUsersController();
        });
        
        view.getBtnSales().addActionListener((e) -> {
            new SeeSalesController();
        });
 
        listValues();
    }
    
    private void listValues() {
        
    }
}
