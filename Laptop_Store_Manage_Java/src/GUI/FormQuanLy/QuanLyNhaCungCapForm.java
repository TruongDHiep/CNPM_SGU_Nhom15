package GUI.FormQuanLy;

import BackEnd.QuanLyNCC.QuanLyNhaCungCapBUS;
import BackEnd.WorkWithExcel.DocExcel;
import BackEnd.WorkWithExcel.XuatExcel;
import GUI.FormHienThi.HienThiNhaCungCap;
import GUI.FormThemSua.ThemSuaNhaCungCapForm;
import GUI.GiaoDienChuan.LoginForm;
import GUI.MyButton.ExportExcelButton;
import GUI.MyButton.ImportExcelButton;
import GUI.MyButton.SuaButton;
import GUI.MyButton.ThemButton;
import GUI.MyButton.XoaButton;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class QuanLyNhaCungCapForm extends JPanel {

    HienThiNhaCungCap formHienThi = new HienThiNhaCungCap();

    ThemButton btnThem = new ThemButton();
    SuaButton btnSua = new SuaButton();
    XoaButton btnXoa = new XoaButton();
    ExportExcelButton btnXuatExcel = new ExportExcelButton();
    ImportExcelButton btnNhapExcel = new ImportExcelButton();

    public QuanLyNhaCungCapForm() {
        setLayout(new BorderLayout());

        if (!LoginForm.quyenLogin.getChiTietQuyen().contains("qlNCC")) {
            btnThem.setEnabled(false);
            btnXoa.setEnabled(false);
            btnSua.setEnabled(false);
            btnNhapExcel.setEnabled(false);
        }

        JPanel plBtn = new JPanel();
        plBtn.add(btnThem);
        plBtn.add(btnXoa);
        plBtn.add(btnSua);
        plBtn.add(btnXuatExcel);
        plBtn.add(btnNhapExcel);
        plBtn.setBackground(Color.white);

        this.add(formHienThi, BorderLayout.CENTER);
        this.add(plBtn, BorderLayout.NORTH);

        btnThem.addActionListener((ActionEvent ae) -> {
            btnThemMouseClicked();
        });
        btnXoa.addActionListener((ActionEvent ae) -> {
            btnXoaMouseClicked();
        });
        btnSua.addActionListener((ActionEvent ae) -> {
            btnSuaMouseClicked();
            formHienThi.refresh();
        });
        btnNhapExcel.addActionListener((ActionEvent ae) -> {
            new DocExcel().docFileExcelNhaCungCap();
        });
        btnXuatExcel.addActionListener((ActionEvent ae) -> {
            new XuatExcel().xuatFileExcelNhaCungCap();
        });
    }

    private void btnSuaMouseClicked() {
        String mancc = formHienThi.getSelectedRow(1);
        if (mancc != null) {
            ThemSuaNhaCungCapForm suancc = new ThemSuaNhaCungCapForm("Sửa", mancc);

            suancc.addWindowListener(new java.awt.event.WindowAdapter() {
                @Override
                public void windowClosed(java.awt.event.WindowEvent windowEvent) {
                    formHienThi.refresh();
                }
            });

        } else {
            JOptionPane.showMessageDialog(null, "Chưa chọn nhà cung cấp nào để sửa");
        }
    }

    private void btnXoaMouseClicked() {
        String mancc = formHienThi.getSelectedRow(1);
        if (mancc != null) {
            if (JOptionPane.showConfirmDialog(null, "Bạn có chắc muốn xóa nhà cung cấp " + mancc + " ?", "Chú ý", JOptionPane.YES_NO_OPTION) == JOptionPane.OK_OPTION) {
                new QuanLyNhaCungCapBUS().delete(mancc);
                formHienThi.refresh();
            }

        } else {
            JOptionPane.showMessageDialog(null, "Chưa chọn nhà cung cấp nào để xóa");
        }
    }

    private void btnThemMouseClicked() {
        ThemSuaNhaCungCapForm themncc = new ThemSuaNhaCungCapForm("Thêm", "");
        themncc.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosed(java.awt.event.WindowEvent windowEvent) {
                formHienThi.refresh();
            }
        });
    }
}
