package GUI.FormQuanLy;

import BackEnd.QuanLyNhanVien.QuanLyNhanVienBUS;
import BackEnd.WorkWithExcel.DocExcel;
import BackEnd.WorkWithExcel.XuatExcel;
import GUI.FormHienThi.HienThiNhanVien;
import GUI.FormThemSua.ThemSuaNhanVienForm;
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

public class QuanLyNhanVienForm extends JPanel {

    HienThiNhanVien formHienThi = new HienThiNhanVien();

    ThemButton btnThem = new ThemButton();
    SuaButton btnSua = new SuaButton();
    XoaButton btnXoa = new XoaButton();
    
    ExportExcelButton btnXuatExcel = new ExportExcelButton();
    ImportExcelButton btnNhapExcel = new ImportExcelButton();

    public QuanLyNhanVienForm() {
        setLayout(new BorderLayout());

        // buttons
        if (!LoginForm.quyenLogin.getChiTietQuyen().contains("qlNhanVien")) {
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
        this.add(plBtn, BorderLayout.NORTH);
        this.add(formHienThi, BorderLayout.CENTER);

        // actionlistener
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
            new XuatExcel().xuatFileExcelNhanVien();
        });
        btnNhapExcel.addActionListener((ActionEvent ae) -> {
            new DocExcel().docFileExcelNhanVien();
        });
    }

    private void btnSuaMouseClicked() {
        String manv = formHienThi.getSelectedRow(1);
        if (manv != null) {
            ThemSuaNhanVienForm suanv = new ThemSuaNhanVienForm("Sửa", manv);

            // https://stackoverflow.com/questions/4154780/jframe-catch-dispose-event
            suanv.addWindowListener(new java.awt.event.WindowAdapter() {
                @Override
                public void windowClosed(java.awt.event.WindowEvent windowEvent) {
                    formHienThi.refresh();
                }
            });

        } else {
            JOptionPane.showMessageDialog(null, "Chưa chọn nhân viên nào để sửa");
        }
    }

    private void btnXoaMouseClicked() {
        String manv = formHienThi.getSelectedRow(1);
        if (manv != null) {
            QuanLyNhanVienBUS qlnvBUS = new QuanLyNhanVienBUS();
            if (qlnvBUS.getNhanVien(manv).getTrangThai() == 0) {
                if (JOptionPane.showConfirmDialog(null, "Bạn có chắc muốn xóa nhân viên " + manv + " ? "
                        + "Nhân viên sẽ được TẠM ẨN", "Chú ý", JOptionPane.YES_NO_OPTION) == JOptionPane.OK_OPTION) {
                    qlnvBUS.updateTrangThai(manv, 1);
                    formHienThi.refresh();
                }
            } else {
                if (JOptionPane.showConfirmDialog(null, "Bạn có chắc muốn XÓA HOÀN TOÀN nhân viên " + manv + " ?",
                        "Chú ý", JOptionPane.YES_NO_OPTION) == JOptionPane.OK_OPTION) {
                    qlnvBUS.delete(manv);
                    formHienThi.refresh();
                }
            }

        } else {
            JOptionPane.showMessageDialog(null, "Chưa chọn nhân viên nào để xóa");
        }
    }

    private void btnThemMouseClicked() {
        ThemSuaNhanVienForm themnv = new ThemSuaNhanVienForm("Thêm", "");
        themnv.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosed(java.awt.event.WindowEvent windowEvent) {
                formHienThi.refresh();
            }
        });
    }
}
