/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CRUD;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import Object.PhieuMuon;
import Object.Sach;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
/**
 *
 * @author HoCanhQuy
 */
public class SachCRUD implements ISachCRUD{
    ArrayList<Sach> dsSach = new ArrayList();
    
    @Override
    public ArrayList<Sach> getDsSach(){
        try(Connection conn = ConnectSQL.getConnection()){
            String sql = "SELECT * FROM SACH";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                dsSach.add(new Sach(rs.getString("maSach"), rs.getString("tenSach"), rs.getString("tacGia"), rs.getString("theLoai"), rs.getString("nhaXuatBan"), rs.getInt("namXuatBan"), rs.getInt("soLuong"), rs.getInt("tinhTrang")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(PhieuMuonCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return dsSach;
    }
    @Override
    public int updateSoLuong(String maSach, int soLuong){
        int rs = -1;
        String sqlupdate = "UPDATE SACH SET soLuong = ? where maSach = ?";
        try (Connection conn = ConnectSQL.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sqlupdate)) {
            stmt.setInt(1, soLuong);
            stmt.setString(2, maSach);
            rs = stmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(SachCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }
    
    
    @Override
    public int themSach(Sach s) {
        String sql = "INSERT INTO Sach (maSach, tenSach, tacGia, theLoai, nhaXuatBan, namXuatBan, soLuong, tinhTrang) "
                   + "VALUES (?,?,?,?,?,?,?,1)";
        
        try (Connection conn = ConnectSQL.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, s.getMaSach());
            ps.setString(2, s.getTenSach());
            ps.setString(3, s.getTacGia());
            ps.setString(4, s.getTheLoai());
            ps.setString(5, s.getNhaXuatBan());
            ps.setInt(6, s.getNamXuatBan());
            ps.setInt(7, s.getSoLuong());
            return ps.executeUpdate();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Lỗi khi thêm sách: " + e.getMessage());
            return 0;
        }
    }

    @Override
    public int suaSach(Sach s) {
        String sql = "UPDATE Sach SET tenSach=?, tacGia=?, theLoai=?, nhaXuatBan=?, namXuatBan=?, soLuong=? , tinhTrang = ?"
                   + "WHERE maSach=?";
        
        try (Connection conn = ConnectSQL.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)) {
            int tinhTrang = 0;
            ps.setString(1, s.getTenSach());
            ps.setString(2, s.getTacGia());
            ps.setString(3, s.getTheLoai());
            ps.setString(4, s.getNhaXuatBan());
            ps.setInt(5, s.getNamXuatBan());
            ps.setInt(6, s.getSoLuong());
            if(s.getTinhTrang() == 1){
                tinhTrang = 1;
            }
            ps.setInt(7, tinhTrang);
            ps.setString(8, s.getMaSach());
            return ps.executeUpdate();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Lỗi khi sửa sách: " + e.getMessage());
            return 0;
        }
    }

    @Override
    public int xoaSach(String maSach) {
        String sql = "UPDATE Sach SET tinhTrang = 0 WHERE maSach = ?";
        
        try (Connection conn = ConnectSQL.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, maSach);
            return ps.executeUpdate();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Lỗi khi ẩn sách: " + e.getMessage());
            return 0;
        }
    }
}
