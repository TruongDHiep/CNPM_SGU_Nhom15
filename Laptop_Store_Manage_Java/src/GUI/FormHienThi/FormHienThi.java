/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.FormHienThi;

import GUI.GiaoDienChuan.MyTable;
import java.awt.Color;
import javax.swing.JPanel;

/**
 *
 * @author DELL
 */
public class FormHienThi extends JPanel {
    
    MyTable mtb;
    
    public FormHienThi() {

        this.setBackground(Color.white);
        
    }
    
    public String getSelectedRow(int col) {
        int i = mtb.getTable().getSelectedRow();
        if (i >= 0) {
            int realI = mtb.getTable().convertRowIndexToModel(i);
            return mtb.getModel().getValueAt(realI, col).toString();
        }
        return null;
    }
    
    public MyTable getTable() {
        return this.mtb;
    }
}
