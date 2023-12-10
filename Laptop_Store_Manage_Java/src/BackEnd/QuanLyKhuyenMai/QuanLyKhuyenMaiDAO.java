/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BackEnd.QuanLyKhuyenMai;

import BackEnd.QuanLyKhachHang.KhachHang;
import DAO.ConnectDB.ConnectionDB;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import javax.swing.JOptionPane;

/**
 *
 * @author DELL
 */
public class QuanLyKhuyenMaiDAO {
    ConnectionDB qlkmConnection;

    public QuanLyKhuyenMaiDAO() {

    }

    public ArrayList<KhuyenMai> readDB() {
        qlkmConnection = new ConnectionDB();
        ArrayList<KhuyenMai> dssp = new ArrayList<>();
        try {
            String qry = "SELECT * FROM khuyenmai";
            ResultSet r = qlkmConnection.sqlQuery(qry);
            if (r != null) {
                while (r.next()) {
                    String makm = r.getString("MaKM");
                    String tenkm = r.getString("TenKM");
                    float dkkm = r.getFloat("DieuKienKM");
                    float phantramkm = r.getFloat("PhanTramKM");
                    LocalDate ngaybd = r.getDate("NgayBD").toLocalDate();
                    LocalDate ngaykt = r.getDate("NgayKT").toLocalDate();
                    dssp.add(new KhuyenMai(makm, tenkm, dkkm, phantramkm, ngaybd, ngaykt));
                }
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "-- ERROR! Lỗi đọc dữ liệu bảng khuyến mãi");
        } finally {
            qlkmConnection.closeConnect();
        }
        return listSort(dssp);
    }
    
    
    
     public static ArrayList<KhuyenMai> listSort(ArrayList<KhuyenMai>list){
        
            Collections.sort(list, new Comparator<KhuyenMai>() {
            @Override
            public int compare(KhuyenMai ob1, KhuyenMai ob2) {
                // Chuyển đổi mã nhân viên thành số và so sánh
                int ma1 = Integer.parseInt(ob1.getMaKM().replaceAll("\\D", ""));
                int ma2 = Integer.parseInt(ob2.getMaKM().replaceAll("\\D", ""));
                return Integer.compare(ma1, ma2);
            }
        });
            return list;
        }
    

    public ArrayList<KhuyenMai> search(String columnName, String value) {
        qlkmConnection = new ConnectionDB();
        ArrayList<KhuyenMai> dssp = new ArrayList<>();

        try {
            String qry = "SELECT * FROM khuyenmai WHERE " + columnName + " LIKE '%" + value + "%'";
            ResultSet r = qlkmConnection.sqlQuery(qry);
            if (r != null) {
                while (r.next()) {
                    String makm = r.getString("MaKM");
                    String tenkm = r.getString("TenKM");
                    float dkkm = r.getFloat("DieuKienKM");
                    float phantramkm = r.getFloat("PhanTramKM");
                    LocalDate ngaybd = r.getDate("NgayBD").toLocalDate();
                    LocalDate ngaykt = r.getDate("NgayKT").toLocalDate();
                    dssp.add(new KhuyenMai(makm, tenkm, dkkm, phantramkm, ngaybd, ngaykt));
                }
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "-- ERROR! Lỗi tìm dữ liệu " + columnName + " = " + value + " bảng khuyến mãi");
        } finally {
            qlkmConnection.closeConnect();
        }

        return dssp;
    }

    public Boolean add(KhuyenMai km) {
        qlkmConnection = new ConnectionDB();
        Boolean ok = qlkmConnection.sqlUpdate("INSERT INTO `khuyenmai` (`MaKM`, `TenKM`, `DieuKienKM`, `PhanTramKM`, `NgayBD`, `NgayKT`) VALUES ('"
                + km.getMaKM()+ "', '"
                + km.getTenKM() + "', '"
                + km.getDieuKienKM() + "', '"
                + km.getPhanTramKM() + "', '"
                + km.getNgayBD() + "', '"
                + km.getNgayKT() + "');");
        qlkmConnection.closeConnect();
        return ok;
    }

    public Boolean delete(String makm) {
        qlkmConnection = new ConnectionDB();
        Boolean ok = qlkmConnection.sqlUpdate("DELETE FROM `khuyenmai` WHERE `khuyenmai`.`MaKM` = '" + makm + "'");
        qlkmConnection.closeConnect();
        return ok;
    }

    public Boolean update(String makm, String tenkm, float dkkm, float phantramkm, LocalDate ngaybd, LocalDate ngaykt) {
        qlkmConnection = new ConnectionDB();
        Boolean ok = qlkmConnection.sqlUpdate("Update KhuyenMai Set "
                + "TenKM='" + tenkm
                + "', DieuKienKM='" + dkkm
                + "', PhanTramKM='" + phantramkm
                + "', NgayBD='" + ngaybd
                + "', NgayKT='" + ngaykt
                + "' where MaKM='" + makm + "'");
        qlkmConnection.closeConnect();
        return ok;
    }

    public void close() {
        qlkmConnection.closeConnect();
    }
}
