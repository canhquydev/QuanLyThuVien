/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CRUD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import Object.NguoiMuon;
import Object.Sach;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JOptionPane;

/**
 *
 * @author HoCanhQuy
 */
public class NguoiMuonCRUD implements INguoiMuonCRUD{
    ArrayList<NguoiMuon> dsNguoiMuon = new ArrayList();
    
    @Override
    public ArrayList<NguoiMuon> getDsNguoiMuon(){
        try(Connection conn = ConnectSQL.getConnection()){
            String sql = "SELECT * FROM NGUOIMUON";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                dsNguoiMuon.add(new NguoiMuon(rs.getString("maNguoiMuon"), 
                rs.getString("hoTen"), rs.getString("gioiTinh"), 
                rs.getString("diaChi"), rs.getString("soDienThoai"), rs.getString("email")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(PhieuMuonCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return dsNguoiMuon;
    }
    
    @Override
    public int themNguoiMuon(NguoiMuon nm){
        try(Connection conn = ConnectSQL.getConnection()){
            String sql = "INSERT INTO NGUOIMUON (MaNguoiMuon, HoTen, GioiTinh, DiaChi, SoDienThoai, Email) VALUES(?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, nm.getMaNguoiMuon());
            stmt.setString(2, nm.getHoTen());
            stmt.setString(3, nm.getGioiTinh());
            stmt.setString(4, nm.getDiaChi());
            stmt.setString(5, nm.getSoDienThoai());
            stmt.setString(6, nm.getEmail());
            return stmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(PhieuMuonCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
    
    @Override
    public int suaNguoiMuon(NguoiMuon nm){
        try(Connection conn = ConnectSQL.getConnection()){
            String sql = "UPDATE NGUOIMUON SET hoTen = ?, gioiTinh = ?, diaChi = ?, soDienThoai = ?, email = ? WHERE maNguoiMuon = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, nm.getHoTen());
            stmt.setString(2, nm.getGioiTinh());
            stmt.setString(3, nm.getDiaChi());
            stmt.setString(4, nm.getSoDienThoai());
            stmt.setString(5, nm.getEmail());
            stmt.setString(6, nm.getMaNguoiMuon());
            return stmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(PhieuMuonCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
    
    @Override
    public int xoaNguoiMuon(String maNguoiMuon){
        try(Connection conn = ConnectSQL.getConnection()){
            String sql = "DELETE FROM NGUOIMUON WHERE maNguoiMuon = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, maNguoiMuon);
            return stmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(PhieuMuonCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
}
