/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import View.ManageUsersView;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.User;
import model.dao.UserDAO;

/**
 *
 * @author aluno
 */
public class ManageUsersController {
    private ManageUsersView view;
    private UserDAO userdao;
    
    public ManageUsersController() {
        view = new ManageUsersView();
        view.setVisible(true);
        listValues();
        
        view.getBtnInsert().addActionListener((e) -> {
            User u = new User();
            userdao = new UserDAO();
            u.setName(view.getTxtName().getText());
            u.setPassword(view.getTxtPassword().getText());
            u.setRole(view.getComboboxRole().getSelectedIndex()+1);

            userdao.create(u);
            listValues();
        });
    }
    
    private void listValues() {
        try {
            User u = new User();
            userdao = new UserDAO();
            
            DefaultTableModel tb = (DefaultTableModel) view.getTbUsers().getModel();
            tb.setNumRows(0);
            
            ArrayList<User> list = userdao.read();
            
            for(int i = 0; i < list.size(); i++) {
                tb.addRow(new Object[]{
                   list.get(i).getName(),
                   list.get(i).getRoleTxt()
                });
            }
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao listar valores: " + e);
        }
    }
}
