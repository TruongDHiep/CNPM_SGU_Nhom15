package GUI.FormQuanLy;

import BackEnd.QuanLyKhachHang.QuanLyKhachHangBUS;
import BackEnd.WorkWithExcel.DocExcel;
import BackEnd.WorkWithExcel.XuatExcel;
import GUI.FormHienThi.HienThiKhachHang;
import GUI.FormThemSua.ThemSuaKhachHangForm;
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

public class QuanLyKhachHangForm extends JPanel {

    HienThiKhachHang formHienThi = new HienThiKhachHang();

    ThemButton btnThem = new ThemButton();
    SuaButton btnSua = new SuaButton();
    XoaButton btnXoa = new XoaButton();
    
    ExportExcelButton btnXuatExcel = new ExportExcelButton();
    ImportExcelButton btnNhapExcel = new ImportExcelButton();

    public QuanLyKhachHangForm() {
        setLayout(new BorderLayout());

        // buttons
        if (!LoginForm.quyenLogin.getChiTietQuyen().contains("qlKhachHang")) {
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
        this.setBackground(Color.WHITE);

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
            new XuatExcel().xuatFileExcelKhachHang();
        });
        btnNhapExcel.addActionListener((ActionEvent ae) -> {
            new DocExcel().docFileExcelKhachhang();
        });
    }

    private void btnSuaMouseClicked() {
        String makh = formHienThi.getSelectedRow(1);
        if (makh != null) {
            ThemSuaKhachHangForm suakh = new ThemSuaKhachHangForm("Sửa", makh);

            // https://stackoverflow.com/questions/4154780/jframe-catch-dispose-event
            suakh.addWindowListener(new java.awt.event.WindowAdapter() {
                @Override
                public void windowClosed(java.awt.event.WindowEvent windowEvent) {
                    formHienThi.refresh();
                }
            });

        } else {
            JOptionPane.showMessageDialog(null, "Chưa chọn khách hàng nào để sửa");
        }
    }

    private void btnXoaMouseClicked() {
        String makh = formHienThi.getSelectedRow(1);
        if (makh != null) {
            QuanLyKhachHangBUS qlkhBUS = new QuanLyKhachHangBUS();
            if (qlkhBUS.getKhachHang(makh).getTrangThai() == 0) {
                if (JOptionPane.showConfirmDialog(null, "Bạn có chắc muốn xóa khách hàng " + makh + " ? "
                        + "Nhân viên sẽ được TẠM ẨN", "Chú ý", JOptionPane.YES_NO_OPTION) == JOptionPane.OK_OPTION) {
                    qlkhBUS.updateTrangThai(makh, 1);
                    formHienThi.refresh();
                }
            } else {
                if (JOptionPane.showConfirmDialog(null, "Bạn có chắc muốn XÓA HOÀN TOÀN khách hàng " + makh + " ?",
                        "Chú ý", JOptionPane.YES_NO_OPTION) == JOptionPane.OK_OPTION) {
                    qlkhBUS.delete(makh);
                    formHienThi.refresh();
                }
            }

        } else {
            JOptionPane.showMessageDialog(null, "Chưa chọn khách hàng nào để xóa");
        }
    }

    private void btnThemMouseClicked() {
        ThemSuaKhachHangForm themkh = new ThemSuaKhachHangForm("Thêm", "");
        themkh.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosed(java.awt.event.WindowEvent windowEvent) {
                formHienThi.refresh();
            }
        });
    }
}
