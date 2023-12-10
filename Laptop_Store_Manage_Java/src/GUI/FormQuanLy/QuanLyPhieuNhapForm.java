/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.FormQuanLy;

import BackEnd.QuanLyChiTietPN.QuanLyChiTietPhieuNhapBUS;
import BackEnd.QuanLyPhieuNhap.QuanLyPhieuNhapBUS;
import BackEnd.WorkWithExcel.XuatExcel;
import BackEnd.WritePDF.WritePDF;
import GUI.FormHienThi.HienThiPhieuNhap;
import GUI.FormThemSua.ThemSuaPhieuNhapForm;
import GUI.GiaoDienChuan.LoginForm;
import GUI.GiaoDienChuan.MyTable;
import GUI.MyButton.ExportExcelButton;
import GUI.MyButton.ImportExcelButton;
import GUI.MyButton.SuaButton;
import GUI.MyButton.ThemButton;
import GUI.MyButton.XoaButton;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author Admin
 */
public class QuanLyPhieuNhapForm extends JPanel {

    HienThiPhieuNhap formHienThi = new HienThiPhieuNhap();

    ThemButton btnThem = new ThemButton();
    SuaButton btnSua = new SuaButton();
    XoaButton btnXoa = new XoaButton();

    ExportExcelButton btnXuatExcel = new ExportExcelButton();
    ImportExcelButton btnNhapExcel = new ImportExcelButton();
    JButton btnPrintPDF = new JButton("In PDF");

    public QuanLyPhieuNhapForm() {
        setLayout(new BorderLayout());

        // buttons
        if (!LoginForm.quyenLogin.getChiTietQuyen().contains("qlPhieuNhap")) {
            btnNhapExcel.setEnabled(false);
        }
        btnThem.setEnabled(false);
        btnSua.setEnabled(false);
        btnXoa.setEnabled(false);

        JPanel plBtn = new JPanel();
//        plBtn.add(btnThem);
//        plBtn.add(btnXoa);
//        plBtn.add(btnSua);
        plBtn.add(btnXuatExcel);
        plBtn.add(btnNhapExcel);
        plBtn.add(btnPrintPDF);
        plBtn.setBackground(Color.white);
        

        this.add(formHienThi, BorderLayout.CENTER);
        this.add(plBtn, BorderLayout.NORTH);
        

        // s
        btnThem.addActionListener((ActionEvent ae) -> {
            btnThemMouseClicked();
        });
        btnXoa.addActionListener((ActionEvent ae) -> {
            btnXoaMouseClicked();
        });
        btnSua.addActionListener((ActionEvent ae) -> {
            btnSuaMouseClicked();
        });
        btnXuatExcel.addActionListener((ActionEvent ae) -> {
            new XuatExcel().xuatFileExcelPhieuNhap();
        });
        btnNhapExcel.addActionListener((ActionEvent ae) -> {
            JOptionPane.showMessageDialog(this, "Chức năng chưa hoàn thành!");
        });
        btnPrintPDF.addActionListener((ae) -> {
            if (formHienThi.getSelectedRow(0) != null) {
                MyTable mtb = formHienThi.getTable();
                new WritePDF().writePhieuNhap(String.valueOf(mtb.getTable().getValueAt(mtb.getTable().getSelectedRow(), 1)));
            } else {
                JOptionPane.showMessageDialog(null, "Chưa chọn phiếu nhập nào để in");
            }
        });
    }

    private void btnSuaMouseClicked() {
        String mapn = formHienThi.getSelectedRow(1);
        if (mapn != null) {
            ThemSuaPhieuNhapForm tspn = new ThemSuaPhieuNhapForm("Sửa", mapn);
            tspn.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    formHienThi.refresh();
                }
            });
        } else {
            JOptionPane.showMessageDialog(null, "Chưa chọn phiếu nhập nào để sửa");
        }
    }

    private void btnXoaMouseClicked() {
        String mapn = formHienThi.getSelectedRow(1);
        if (mapn != null) {
            if (JOptionPane.showConfirmDialog(null, "Bạn có chắc muốn xóa phiếu nhập " + mapn
                    + " ? Mọi chi tiết trong phiếu nhập sẽ bị xóa theo",
                    "Chú ý", JOptionPane.YES_NO_OPTION) == JOptionPane.OK_OPTION) {

                new QuanLyChiTietPhieuNhapBUS().deleteAll(mapn);
                new QuanLyPhieuNhapBUS().delete(mapn);

                formHienThi.refresh();
            }

        } else {
            JOptionPane.showMessageDialog(null, "Chưa chọn phiếu nhập nào để xóa");
        }
    }

    private void btnThemMouseClicked() {
        ThemSuaPhieuNhapForm thempn = new ThemSuaPhieuNhapForm("Thêm", "");
        thempn.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                formHienThi.refresh();
            }
        });
    }
}
