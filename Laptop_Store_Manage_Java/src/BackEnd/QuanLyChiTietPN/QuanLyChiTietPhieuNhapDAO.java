/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BackEnd.QuanLyChiTietPN;

import BackEnd.QuanLyChiTietHoaDon.ChiTietHoaDon;
import DAO.ConnectDB.ConnectionDB;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import javax.swing.JOptionPane;

/**
 *
 * @author Admin
 */
public class QuanLyChiTietPhieuNhapDAO {

    ConnectionDB qlctpnConnection;

    public ArrayList<ChiTietPhieuNhap> readDB() {
        ArrayList<ChiTietPhieuNhap> dsctpn = new ArrayList<>();
        qlctpnConnection = new ConnectionDB();
        try {

            String query = "SELECT * FROM chitietphieunhap";
            ResultSet r = qlctpnConnection.sqlQuery(query);
            if (r != null) {
                while (r.next()) {
                    String ma = r.getString(1);
                    String maSP = r.getString(2);
                    Integer soLuong = r.getInt(3);
                    Float donGia = r.getFloat(4);

                    ChiTietPhieuNhap ctpn = new ChiTietPhieuNhap(ma, maSP, soLuong, donGia);
                    dsctpn.add(ctpn);
                }
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Không thấy data cần tìm trong ResultSet");

        } finally {
            qlctpnConnection.closeConnect();
        }
        return listSort(dsctpn);

    }
    
      public static ArrayList<ChiTietPhieuNhap> listSort(ArrayList<ChiTietPhieuNhap> list){
        
            Collections.sort(list, new Comparator<ChiTietPhieuNhap>() {
            @Override
            public int compare(ChiTietPhieuNhap ob1, ChiTietPhieuNhap ob2) {
                // Chuyển đổi mã nhân viên thành số và so sánh
                int ma1 = Integer.parseInt(ob1.getMaSP().replaceAll("\\D", ""));
                int ma2 = Integer.parseInt(ob2.getMaSP().replaceAll("\\D", ""));
                return Integer.compare(ma1, ma2);
            }
        });
            return list;
        }
    

    public ArrayList<ChiTietPhieuNhap> search(String columName, String value) {
        ArrayList<ChiTietPhieuNhap> dsctpn = new ArrayList<>();
        qlctpnConnection = new ConnectionDB();
        try {

            String query = "SELECT * FROM chitietphieunhap WHERE" + columName + "LIKE '%" + value + "%'";
            ResultSet r = qlctpnConnection.sqlQuery(query);
            if (r != null) {
                while (r.next()) {
                    String ma = r.getString(1);
                    String maSP = r.getString(2);
                    Integer soLuong = r.getInt(3);
                    Float donGia = r.getFloat(4);

                    ChiTietPhieuNhap ctpn = new ChiTietPhieuNhap(ma, maSP, soLuong, donGia);
                    dsctpn.add(ctpn);
                }
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Không thấy data cần tìm trong ResultSet");

        } finally {
            qlctpnConnection.closeConnect();
        }
        return dsctpn;

    }

    public boolean add(ChiTietPhieuNhap ctpn) {
        qlctpnConnection = new ConnectionDB();
        Boolean ok = qlctpnConnection.sqlUpdate("INSERT INTO `chitietphieunhap`(`MaPN`,`MaSP`,`SoLuong`,`DonGia`) VALUE('"
                + ctpn.getMa() + "', '" + ctpn.getMaSP() + "','" + ctpn.getSoLuong() + "','" + ctpn.getDonGia() + "')");
        qlctpnConnection.closeConnect();
        return ok;

    }

    public Boolean deleteAll(String _mapn) {
        qlctpnConnection = new ConnectionDB();
        Boolean success = qlctpnConnection.sqlUpdate("DELETE FROM chitietphieunhap WHERE MaPN='" + _mapn + "';");
        qlctpnConnection.closeConnect();
        return success;
    }

    public Boolean delete(String _mapn, String _masp) {
        qlctpnConnection = new ConnectionDB();
        Boolean success = qlctpnConnection.sqlUpdate("DELETE FROM chitietphieunhap WHERE MaPN='" + _mapn + "' AND MaSP='" + _masp + "';");
        qlctpnConnection.closeConnect();
        return success;
    }

    public boolean update(String mapn, String masp, int soluong, float dongia) {
        qlctpnConnection = new ConnectionDB();
        Boolean ok = qlctpnConnection.sqlUpdate("UPDATE `chitietphieunhap` SET "
                + "SoLuong='" + soluong
                + "',DonGia='" + dongia
                + "' WHERE MaPN='" + mapn + "' AND MaSP='" + masp + "';");
        qlctpnConnection.closeConnect();
        return ok;
    }
}
