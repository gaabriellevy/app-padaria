/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import View.LoginView;
import javax.swing.JOptionPane;
import model.User;
import model.dao.UserDAO;

/**
 *
 * @author aluno
 */
public class LoginController {
    private LoginView view;
    private User user;
    private UserDAO userdao;
    
    public LoginController(LoginView view) {
        this.view = view;
        userdao = new UserDAO();
        
        login();
    }
    
    private void login() {        
        String name = view.getTxtUserName().getText();
        String password = view.getTxtPassword().getText();
        
        userdao = new UserDAO();
        
        User user = userdao.login(name, password);
        
        if(user.getId() != 0) {
            if(user.getRole() == 1) {
                System.out.println("Managager");
                new ManagerController();
            }else if(user.getRole() == 2) {
                new SaleController();
            }
            
            view.dispose();
        }else {
            JOptionPane.showMessageDialog(null, "Erro no login. Tente novamente!");
        }
        
        
    }
}
