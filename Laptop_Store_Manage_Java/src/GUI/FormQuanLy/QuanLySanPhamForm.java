package GUI.FormQuanLy;

import BackEnd.QuanLySanPham.QuanLySanPhamBUS;
import BackEnd.WorkWithExcel.DocExcel;
import BackEnd.WorkWithExcel.XuatExcel;
import GUI.FormHienThi.HienThiSanPham;
import GUI.FormThemSua.ThemSuaSanPhamForm;
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

public class QuanLySanPhamForm extends JPanel {

    HienThiSanPham formHienThi = new HienThiSanPham();

    ThemButton btnThem = new ThemButton();
    SuaButton btnSua = new SuaButton();
    XoaButton btnXoa = new XoaButton();
    
    ExportExcelButton btnXuatExcel = new ExportExcelButton();
    ImportExcelButton btnNhapExcel = new ImportExcelButton();

    public QuanLySanPhamForm() {
        setLayout(new BorderLayout());

        // buttons
        if (!LoginForm.quyenLogin.getChiTietQuyen().contains("qlSanPham")) {
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

        this.add(formHienThi, BorderLayout.CENTER);
        this.add(plBtn, BorderLayout.NORTH);
        plBtn.setBackground(Color.white);


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
            new XuatExcel().xuatFileExcelSanPham();
        });
        btnNhapExcel.addActionListener((ActionEvent ae) -> {
            new DocExcel().docFileExcelSanPham();
        });
    }

    private void btnSuaMouseClicked() {
        String masp = formHienThi.getSelectedRow(1);
        if (masp != null) {
            ThemSuaSanPhamForm suasp = new ThemSuaSanPhamForm("Sửa", masp);

            // https://stackoverflow.com/questions/4154780/jframe-catch-dispose-event
            suasp.addWindowListener(new java.awt.event.WindowAdapter() {
                @Override
                public void windowClosed(java.awt.event.WindowEvent windowEvent) {
                    formHienThi.refresh();
                }
            });

        } else {
            JOptionPane.showMessageDialog(null, "Chưa chọn sản phẩm nào để sửa");
        }
    }

    private void btnXoaMouseClicked() {
        String masp = formHienThi.getSelectedRow(1);
        if (masp != null) {
            QuanLySanPhamBUS qlspBUS = new QuanLySanPhamBUS();
            if (qlspBUS.getSanPham(masp).getTrangThai() == 0) {
                if (JOptionPane.showConfirmDialog(null, "Bạn có chắc muốn xóa sản phẩm " + masp + " ? "
                        + "Sản phẩm sẽ được TẠM ẨN", "Chú ý", JOptionPane.YES_NO_OPTION) == JOptionPane.OK_OPTION) {
                    qlspBUS.updateTrangThai(masp, 1);
                    formHienThi.refresh();
                }
            } else {
                if (JOptionPane.showConfirmDialog(null, "Bạn có chắc muốn XÓA HOÀN TOÀN sản phẩm " + masp + " ?", 
                        "Chú ý", JOptionPane.YES_NO_OPTION) == JOptionPane.OK_OPTION) {
                    qlspBUS.delete(masp);
                    formHienThi.refresh();
                }
            }

        } else {
            JOptionPane.showMessageDialog(null, "Chưa chọn sản phẩm nào để xóa");
        }
    }

    private void btnThemMouseClicked() {
        ThemSuaSanPhamForm themsp = new ThemSuaSanPhamForm("Thêm", "");
        themsp.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosed(java.awt.event.WindowEvent windowEvent) {
                formHienThi.refresh();
            }
        });
    }
}
