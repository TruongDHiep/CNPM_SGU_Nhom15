/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.FormQuanLy;

import BackEnd.QuanLyKhuyenMai.KhuyenMai;
import BackEnd.QuanLyKhuyenMai.QuanLyKhuyenMaiBUS;
import BackEnd.WorkWithExcel.DocExcel;
import BackEnd.WorkWithExcel.XuatExcel;
import GUI.FormHienThi.HienThiKhuyenMai;
import GUI.FormThemSua.ThemSuaKhuyenMaiForm;
import GUI.GiaoDienChuan.LoginForm;
import GUI.MyButton.ExportExcelButton;
import GUI.MyButton.ImportExcelButton;
import GUI.MyButton.SuaButton;
import GUI.MyButton.ThemButton;
import GUI.MyButton.XoaButton;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.time.LocalDate;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author DELL
 */
public class QuanLyKhuyenMaiForm extends JPanel {

    HienThiKhuyenMai formHienThi = new HienThiKhuyenMai();

    ThemButton btnThem = new ThemButton();
    SuaButton btnSua = new SuaButton();
    XoaButton btnXoa = new XoaButton();
    JButton btnKetThuc = new JButton("Kết thúc");
    
    ExportExcelButton btnXuatExcel = new ExportExcelButton();
    ImportExcelButton btnNhapExcel = new ImportExcelButton();

    public QuanLyKhuyenMaiForm() {
        setLayout(new BorderLayout());

        // buttons
        btnKetThuc.setIcon(new ImageIcon(this.getClass().getResource("/giaodienchuan/images/icons8_cancel_30px_1.png")));

        if (!LoginForm.quyenLogin.getChiTietQuyen().contains("qlKhuyenMai")) {
            btnThem.setEnabled(false);
            btnXoa.setEnabled(false);
            btnSua.setEnabled(false);
            btnKetThuc.setEnabled(false);
            btnNhapExcel.setEnabled(false);
        }

        JPanel plBtn = new JPanel();
        plBtn.add(btnThem);
        plBtn.add(btnXoa);
        plBtn.add(btnSua);
        plBtn.add(btnKetThuc);
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
        btnKetThuc.addActionListener((ActionEvent ae) -> {
            btnKetThucMouseClicked();
        });
        btnXuatExcel.addActionListener((ActionEvent ae) -> {
            new XuatExcel().xuatFileExcelKhuyenMai();
        });
        btnNhapExcel.addActionListener((ActionEvent ae) -> {
            new DocExcel().docFileExcelKhuyenMai();
        });
    }

    private void btnSuaMouseClicked() {
        String makm = formHienThi.getSelectedRow(1);
        if (makm != null) {
            ThemSuaKhuyenMaiForm suakm = new ThemSuaKhuyenMaiForm("Sửa", makm);

            // https://stackoverflow.com/questions/4154780/jframe-catch-dispose-event
            suakm.addWindowListener(new java.awt.event.WindowAdapter() {
                @Override
                public void windowClosed(java.awt.event.WindowEvent windowEvent) {
                    formHienThi.refresh();
                }
            });

        } else {
            JOptionPane.showMessageDialog(null, "Chưa chọn khuyến mãi nào để sửa");
        }
    }

    private void btnXoaMouseClicked() {
        String makm = formHienThi.getSelectedRow(1);
        if (makm != null) {
            if (JOptionPane.showConfirmDialog(null, "Bạn có chắc muốn xóa khuyến mãi " + makm,
                    "Chú ý", JOptionPane.YES_NO_OPTION) == JOptionPane.OK_OPTION) {

                new QuanLyKhuyenMaiBUS().delete(makm);

                formHienThi.refresh();
            }

        } else {
            JOptionPane.showMessageDialog(null, "Chưa chọn khuyến mãi nào để xóa");
        }
    }

    private void btnKetThucMouseClicked() {
        String makm = formHienThi.getSelectedRow(1);
        if (makm != null) {

            // check xem khuyến mãi có đang diễn ra ko
            String trangthai = new QuanLyKhuyenMaiBUS().getKhuyenMai(makm).getTrangThai();
            Boolean dangDienRa = trangthai.equals("Đang diễn ra");

            if (!dangDienRa) {
                JOptionPane.showMessageDialog(this, "Không thể dừng khuyến mãi " + trangthai);
                return;
            }

            // check đồng ý kết thúc
            if (JOptionPane.showConfirmDialog(null, "Bạn có chắc muốn dừng khuyến mãi " + makm
                    + " ? Ngày kết thúc Khuyến mãi sẽ được dời về hôm nay",
                    "Chú ý", JOptionPane.YES_NO_OPTION) == JOptionPane.OK_OPTION) {

                QuanLyKhuyenMaiBUS qlkmBUS = new QuanLyKhuyenMaiBUS();
                KhuyenMai km = qlkmBUS.getKhuyenMai(makm);
                qlkmBUS.update(km.getMaKM(), km.getTenKM(), km.getDieuKienKM(), km.getPhanTramKM(), km.getNgayBD(), LocalDate.now());

                formHienThi.refresh();
            }

        } else {
            JOptionPane.showMessageDialog(null, "Chưa chọn khuyến mãi nào để dừng");
        }
    }

    private void btnThemMouseClicked() {
        ThemSuaKhuyenMaiForm themkh = new ThemSuaKhuyenMaiForm("Thêm", "");
        themkh.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosed(java.awt.event.WindowEvent windowEvent) {
                formHienThi.refresh();
            }
        });
    }
}
