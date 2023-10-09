/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import View.SeeSalesView;
import java.time.LocalDateTime;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.Sale;
import model.dao.SaleDAO;

/**
 *
 * @author Levy
 */
public class SeeSalesController {
    private SeeSalesView view;
    private SaleDAO saledao;
    
    public SeeSalesController() {
        view = new SeeSalesView();
        view.setVisible(true);
        listValues();
    }
    
    private void listValues() {
        try {
            Sale sale = new Sale();
            saledao = new SaleDAO();
            
            DefaultTableModel tb = (DefaultTableModel) view.getTbSales().getModel();
            tb.setNumRows(0);
            
            ArrayList<Sale> sales = saledao.read();
                        
            for(int i = 0; i < sales.size(); i++) {
                LocalDateTime dateObj = sales.get(i).getDate();
                String day = String.valueOf(dateObj.getDayOfMonth());
                String month = String.valueOf(dateObj.getMonthValue());
                String year = String.valueOf(dateObj.getYear());
                String hour = String.valueOf(dateObj.getHour());
                String minute = String.valueOf(dateObj.getMinute());
                
                String date = day + '/' + month + '/' + year + ' ' + hour + ':' + minute;
                
                tb.addRow(new Object[]{
                   sales.get(i).getValue(),
                   date
                });
            }
            
            
        }catch(Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao listar valores: " + e);
        }
    }
}
