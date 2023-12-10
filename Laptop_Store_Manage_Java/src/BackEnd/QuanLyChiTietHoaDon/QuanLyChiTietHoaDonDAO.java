package BackEnd.QuanLyChiTietHoaDon;

import BackEnd.QuanLySanPham.SanPham;
import DAO.ConnectDB.ConnectionDB;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import javax.swing.JOptionPane;

public class QuanLyChiTietHoaDonDAO {

    ConnectionDB connection;

    public QuanLyChiTietHoaDonDAO() {
    }

    public ArrayList readDB() {
        connection = new ConnectionDB();
        ArrayList<ChiTietHoaDon> dshd = new ArrayList<>();
        try {
            String qry = "SELECT * FROM chitiethoadon";
            ResultSet rs = connection.sqlQuery(qry);
            if (rs != null) {
                while (rs.next()) {
                    ChiTietHoaDon hd = new ChiTietHoaDon(rs.getString("MaHD"), rs.getString("MaSP"), rs.getInt("SoLuong"), rs.getFloat("DonGia"));
                    dshd.add(hd);
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Khong tim thay du lieu !!");
        } finally {
            connection.closeConnect();
        }
        return listSort(dshd);
    }

    
    public static ArrayList<ChiTietHoaDon> listSort(ArrayList<ChiTietHoaDon> list){
        
            Collections.sort(list, new Comparator<ChiTietHoaDon>() {
            @Override
            public int compare(ChiTietHoaDon ob1, ChiTietHoaDon ob2) {
                // Chuyển đổi mã nhân viên thành số và so sánh
                int ma1 = Integer.parseInt(ob1.getMaSanPham().replaceAll("\\D", ""));
                int ma2 = Integer.parseInt(ob2.getMaSanPham().replaceAll("\\D", ""));
                return Integer.compare(ma1, ma2);
            }
        });
            return list;
        }
    
    
    
    
    public Boolean add(ChiTietHoaDon hd) {
        connection = new ConnectionDB();
        Boolean success = connection.sqlUpdate("INSERT INTO chitiethoadon(MaHD,MaSP,SoLuong,DonGia) VALUES ('" 
                + hd.getMaHoaDon() + "','" 
                + hd.getMaSanPham() + "','" 
                + hd.getSoLuong() + "','" 
                + hd.getDonGia() + "');");
        connection.closeConnect();
        return success;
    }

    public Boolean delete(String _mahd, String _masp) {
        connection = new ConnectionDB();
        Boolean success = connection.sqlUpdate("DELETE FROM chitiethoadon WHERE "
                + "MaHD='" + _mahd
                + "' AND MaSP='" + _masp + "';");
        connection.closeConnect();
        return success;
    }

    public Boolean deleteAll(String _mahd) {
        connection = new ConnectionDB();
        Boolean success = connection.sqlUpdate("DELETE FROM chitiethoadon WHERE MaHD='" + _mahd + "';");
        connection.closeConnect();
        return success;
    }

    public Boolean update(ChiTietHoaDon ct) {
        connection = new ConnectionDB();
        Boolean success = connection.sqlUpdate("UPDATE chitiethoadon set "
                + "SoLuong='" + ct.getSoLuong()
                + "', DonGia='" + ct.getDonGia()
                + "' WHERE MaHD='" + ct.getMaHoaDon() + "' AND MaSP='" + ct.getMaSanPham() + "';");
        connection.closeConnect();
        return success;
    }

    public Boolean update(String maHoaDon, String maSanPham, int soLuong, float donGia) {
        ChiTietHoaDon hd = new ChiTietHoaDon(maHoaDon, maSanPham, soLuong, donGia);
        return update(hd);
    }

    public void closeConnection() {
        connection.closeConnect();
    }
}
