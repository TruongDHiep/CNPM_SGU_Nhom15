/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BackEnd.QuanLyPhieuNhap;

import BackEnd.QuanLyNhanVien.NhanVien;
import DAO.ConnectDB.ConnectionDB;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import javax.swing.JOptionPane;

/**
 *
 * @author Admin
 */
public class QuanLyPhieuNhapDAO {

    ConnectionDB connection;

    public QuanLyPhieuNhapDAO() {
    }

    public ArrayList readDB() {
        connection = new ConnectionDB();
        ArrayList<PhieuNhap> dspn = new ArrayList<>();
        try {
            String qry = "SELECT * FROM phieunhap";
            ResultSet rs = connection.sqlQuery(qry);
            if (rs != null) {

                while (rs.next()) {
                    PhieuNhap pn = new PhieuNhap();

                    pn.setMaPN(rs.getString(1));
                    pn.setMaNCC(rs.getString(2));
                    pn.setMaNV(rs.getString(3));
                    pn.setNgayNhap(rs.getDate(4).toLocalDate());
                    pn.setGioNhap(rs.getTime(5).toLocalTime());
                    pn.setTongTien(rs.getFloat(6));
                    dspn.add(pn);
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Khong tim thay du lieu !!");
        } finally {
            connection.closeConnect();
        }
        return listSort(dspn);
    }

     public static ArrayList<PhieuNhap> listSort(ArrayList<PhieuNhap>list){
        
            Collections.sort(list, new Comparator<PhieuNhap>() {
            @Override
            public int compare(PhieuNhap ob1, PhieuNhap ob2) {
                // Chuyển đổi mã nhân viên thành số và so sánh
                int ma1 = Integer.parseInt(ob1.getMaPN().replaceAll("\\D", ""));
                int ma2 = Integer.parseInt(ob2.getMaPN().replaceAll("\\D", ""));
                return Integer.compare(ma1, ma2);
            }
        });
            return list;
        }

    
    
    public Boolean add(PhieuNhap pn) {
        connection = new ConnectionDB();
        Boolean ok = connection.sqlUpdate("INSERT INTO phieunhap(MaPN,MaNCC,MaNV,NgayNhap,GioNhap,TongTien) VALUES ('"
                + pn.getMaPN() + "','"
                + pn.getMaNCC() + "','"
                + pn.getMaNV() + "','"
                + pn.getNgayNhap() + "','"
                + pn.getGioNhap() + "','"
                + pn.getTongTien() + "');");
        connection.closeConnect();
        return ok;
    }

    public Boolean delete(String mapn) {
        connection = new ConnectionDB();
        if (!connection.sqlUpdate("DELETE FROM phieunhap WHERE MaPN='" + mapn + "';")) {
            JOptionPane.showMessageDialog(null, "Vui long xoa het chi tiet cua phiếu nhập truoc !!!");
            connection.closeConnect();
            return false;
        }
        connection.closeConnect();
        return false;
    }

    public Boolean update(PhieuNhap pn) {
        connection = new ConnectionDB();
        Boolean ok = connection.sqlUpdate("UPDATE phieunhap SET "
                + "MaNCC='" + pn.getMaNCC()
                + "', MaNV='" + pn.getMaNV()
                + "', NgayNhap='" + pn.getNgayNhap()
                + "', GioNhap='" + pn.getGioNhap()
                + "', TongTien='" + pn.getTongTien()
                + "' WHERE MaPN='" + pn.getMaPN() + "';");
        connection.closeConnect();
        return ok;
    }

    public Boolean updateTongTien(String _mapn, float _tongTien) {
        connection = new ConnectionDB();
        Boolean ok = connection.sqlUpdate("UPDATE phieunhap SET TongTien='" + _tongTien + "' WHERE MaPN='" + _mapn + "';");
        connection.closeConnect();
        return ok;
    }

    public Boolean update(String maPN, String maNCC, String maNV, LocalDate ngayNhap, LocalTime gioNhap, float tongTien) {
        PhieuNhap pn = new PhieuNhap();
        pn.setMaPN(maPN);
        pn.setMaNCC(maNCC);
        pn.setMaNV(maNV);
        pn.setNgayNhap(ngayNhap);
        pn.setGioNhap(gioNhap);
        pn.setTongTien(tongTien);
        return update(pn);
    }
}
