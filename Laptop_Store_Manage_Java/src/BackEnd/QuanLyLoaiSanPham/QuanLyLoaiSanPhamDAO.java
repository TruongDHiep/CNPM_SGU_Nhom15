package BackEnd.QuanLyLoaiSanPham;

import BackEnd.QuanLyKhuyenMai.KhuyenMai;
import DAO.ConnectDB.ConnectionDB;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import javax.swing.JOptionPane;

public class QuanLyLoaiSanPhamDAO {

    ConnectionDB qllspConnection;

    public QuanLyLoaiSanPhamDAO() {

    }

    public ArrayList<LoaiSanPham> readDB() {
        qllspConnection = new ConnectionDB();
        ArrayList<LoaiSanPham> dslsp = new ArrayList<>();
        try {
            String qry = "SELECT * FROM loaisanpham";
            ResultSet r = qllspConnection.sqlQuery(qry);
            if (r != null) {
                while (r.next()) {
                    String malsp = r.getString(1);
                    String tenlsp = r.getString(2);
                    String mota = r.getString(3);

                    dslsp.add(new LoaiSanPham(malsp, tenlsp, mota));
                }
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "-- ERROR! Lỗi đọc dữ liệu bảng loại sản phẩm");
        } finally {
            qllspConnection.closeConnect();
        }
        return listSort(dslsp);
    }
    
    
    
    public static ArrayList<LoaiSanPham> listSort(ArrayList<LoaiSanPham>list){
        
            Collections.sort(list, new Comparator<LoaiSanPham>() {
            @Override
            public int compare(LoaiSanPham ob1, LoaiSanPham ob2) {
                // Chuyển đổi mã nhân viên thành số và so sánh
                int ma1 = Integer.parseInt(ob1.getMaLSP().replaceAll("\\D", ""));
                int ma2 = Integer.parseInt(ob2.getMaLSP().replaceAll("\\D", ""));
                return Integer.compare(ma1, ma2);
            }
        });
            return list;
        }

    public ArrayList<LoaiSanPham> search(String columnName, String value) {
        qllspConnection = new ConnectionDB();
        ArrayList<LoaiSanPham> dslsp = new ArrayList<>();

        try {
            String qry = "SELECT * FROM loaisanpham WHERE " + columnName + " LIKE '%" + value + "%'";
            ResultSet r = qllspConnection.sqlQuery(qry);
            if (r != null) {
                while (r.next()) {
                    String malsp = r.getString(1);
                    String tenlsp = r.getString(2);
                    String mota = r.getString(3);

                    dslsp.add(new LoaiSanPham(malsp, tenlsp, mota));
                }
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "-- ERROR! Lỗi tìm dữ liệu " + columnName + " = " + value + " bảng loại sản phẩm");
        } finally {
            qllspConnection.closeConnect();
        }

        return dslsp;
    }

    public Boolean add(LoaiSanPham lsp) {
        qllspConnection = new ConnectionDB();
        Boolean ok = qllspConnection.sqlUpdate("INSERT INTO `loaisanpham` (`MaLSP`, `TenLSP`, `MoTa`) VALUES ('"
                + lsp.getMaLSP() + "', '" + lsp.getTenLSP() + "', '" + lsp.getMoTa()+ "');");
        qllspConnection.closeConnect();
        return ok;
    }

    public Boolean delete(String malsp) {
        qllspConnection = new ConnectionDB();
        Boolean ok = qllspConnection.sqlUpdate("DELETE FROM `loaisanpham` WHERE `loaisanpham`.`MaLSP` = '" + malsp + "'");
        qllspConnection.closeConnect();
        return ok;
    }

    public Boolean update(String MaLSP, String TenLSP, String Mota) {
        qllspConnection = new ConnectionDB();
        Boolean ok = qllspConnection.sqlUpdate("Update loaisanpham Set TenLSP='" + TenLSP + "', MoTa='" + Mota + "' where MaLSP='" + MaLSP + "'");
        qllspConnection.closeConnect();
        return ok;
    }

    public void close() {
        qllspConnection.closeConnect();
    }
}
