/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CRUD;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import Object.PhieuMuon;
import Object.Sach;
import GUI.MainForm;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
/**
 *
 * @author HoCanhQuy
 */
public class PhieuMuonCRUD implements IPhieuMuonCRUD{
    ArrayList<PhieuMuon> dsPhieuMuon = new ArrayList();
    
    @Override
    public ArrayList<PhieuMuon> getDanhSachPM(){
        try(Connection conn = ConnectSQL.getConnection()){
            String sql = "SELECT * FROM PHIEUMUON";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                dsPhieuMuon.add(new PhieuMuon(rs.getString("maPhieuMuon"), rs.getString("maSach")
                , rs.getString("maNguoiMuon"), rs.getInt("soLuong"), rs.getString("ngayMuon"), 
                rs.getString("ngayTra"), rs.getString("tinhTrang"), rs.getInt("soLanGiaHan")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(PhieuMuonCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return dsPhieuMuon;
    }
    
    @Override
    public int autoUpdate(String maPhieuMuon, String tinhTrang){
        int rs = -1;
        String sql = "UPDATE PHIEUMUON SET TinhTrang = ? where maPhieuMuon = ?";
        try(Connection conn = ConnectSQL.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setString(1, tinhTrang);
            stmt.setString(2, maPhieuMuon);
            rs = stmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(MainForm.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }
    
    @Override
    public int themPhieuMuon(PhieuMuon pm){
        int rs = -1;
        String sql = "INSERT INTO PHIEUMUON (maPhieuMuon, maSach, maNguoiMuon, soLuong, ngayMuon, ngayTra, tinhTrang, soLanGiaHan) VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = ConnectSQL.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);) {
            stmt.setString(1, pm.getMaPhieuMuon());
            stmt.setString(2, pm.getMaSach());
            stmt.setString(3, pm.getMaNguoiMuon());
            stmt.setInt(4, pm.getSoLuong());
            stmt.setString(5, pm.getNgayMuon());
            stmt.setString(6, pm.getNgayTra());
            stmt.setString(7, pm.getTinhTrang());
            stmt.setInt(8, pm.getSoLanGiaHan());
            rs = stmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(PhieuMuonCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }
    
    @Override
    public int traSach(PhieuMuon pm){
        String sql = "UPDATE PHIEUMUON SET tinhTrang = ?, ngayTra = ? where maPhieuMuon = ?";
        LocalDate date = LocalDate.now();
        String sqldate = Date.valueOf(date).toString();
        int rs = -1;
//        String sqlupdate = "UPDATE SACH SET soLuong = ? where maSach = ?";
        try(Connection conn = ConnectSQL.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);){
            stmt.setString(1, "Đã trả");
            stmt.setString(2, sqldate);
            stmt.setString(3, pm.getMaPhieuMuon());
            rs = stmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(MainForm.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }
    
    @Override
    public int suaPhieu(PhieuMuon pm){
        int rs = -1;
        String sql = "UPDATE PHIEUMUON SET maSach = ?, maNguoiMuon = ?, soLuong = ?, ngayMuon = ?, ngayTra = ?, soLanGiaHan = ?, TinhTrang = ? where maPhieuMuon = ?";
        System.out.println(pm.getTinhTrang());
        try(Connection conn = ConnectSQL.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setString(1, pm.getMaSach());
            stmt.setString(2, pm.getMaNguoiMuon());
            stmt.setInt(3, pm.getSoLuong());
            stmt.setString(4, pm.getNgayMuon());
            stmt.setString(5, pm.getNgayTra());
            stmt.setInt(6, pm.getSoLanGiaHan());
            stmt.setString(7, pm.getTinhTrang());
            stmt.setString(8, pm.getMaPhieuMuon());
            rs = stmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(MainForm.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }
    @Override
    public int giaHanPhieu(PhieuMuon pm){
        int rs = -1;
        String sql = "UPDATE PHIEUMUON SET ngayTra = ?, soLanGiaHan = ?, TinhTrang = ? where maPhieuMuon = ?";
        System.out.println(pm.getTinhTrang());
        try(Connection conn = ConnectSQL.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setString(1, pm.getNgayTra());
            stmt.setInt(2, pm.getSoLanGiaHan());
            stmt.setString(3, pm.getTinhTrang());
            stmt.setString(4, pm.getMaPhieuMuon());
            rs = stmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(MainForm.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }
    
    @Override
    public DefaultTableModel thongKeMuonSachTable(String selected, Date startDate, Date endDate, DefaultTableModel model){
        String sql = "";
        switch (selected) {
            case "Ngày" -> sql = "select convert(date, NgayMuon) as ThoiGian, sum(SoLuong) AS SoLuong " +
            "from PhieuMuon where NgayMuon between ? and ? " +
            "group by convert(date, NgayMuon) order by ThoiGian";
            case "Tháng" -> sql = "select format(NgayMuon, 'MM/yyyy') as ThoiGian, sum(SoLuong) as SoLuong " +
            "from PhieuMuon where NgayMuon between ? and ? " +
            "group by format(NgayMuon, 'MM/yyyy') order by min(NgayMuon)";
            case "Năm" -> sql = "select year(NgayMuon) as ThoiGian, sum(SoLuong) as SoLuong " +
            "from PhieuMuon where NgayMuon between ? and ? " +
            "group by year(NgayMuon) order by ThoiGian";
        }
        try (Connection conn = ConnectSQL.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setDate(1, new java.sql.Date(startDate.getTime()));
            stmt.setDate(2, new java.sql.Date(endDate.getTime()));

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                model.addRow(new Object[]{
                    rs.getString("ThoiGian"),
                    rs.getInt("SoLuong")
                });
            }
            return model;
        } catch (SQLException e) {
            return null;
        }
    }
    
    @Override
    public JFreeChart thongKeMuonSachChart(String selected, Date startDate, Date endDate){
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        String sql = "";
        switch (selected) {
            case "Ngày" -> sql = "select convert(date, NgayMuon) as ThoiGian, sum(SoLuong) as SoLuong " +
            "from PhieuMuon where NgayMuon between ? and ? " +
            "group by convert(date, NgayMuon) order by ThoiGian";
            case "Tháng" -> sql = "select format(NgayMuon, 'MM/yyyy') as ThoiGian, sum(SoLuong) as SoLuong " +
            "from PhieuMuon where NgayMuon between ? and ? " +
            "group by format(NgayMuon, 'MM/yyyy') order by min(NgayMuon)";
            case "Năm" -> sql = "select year(NgayMuon) as ThoiGian, sum(SoLuong) as SoLuong " +
            "from PhieuMuon where NgayMuon between ? and ? " +
            "group by year(NgayMuon) order by ThoiGian";
        }
        try (Connection conn = ConnectSQL.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setDate(1, new java.sql.Date(startDate.getTime()));
            stmt.setDate(2, new java.sql.Date(endDate.getTime()));

            ResultSet rs = stmt.executeQuery();
            boolean hasData = false;
            while (rs.next()) {
                hasData = true;
                String time = rs.getString("ThoiGian");
                int count = rs.getInt("SoLuong");
                dataset.addValue(count, "Số lượng phiếu mượn", time);
            }

            if (!hasData) {
                return null;
            }

            JFreeChart barChart = ChartFactory.createBarChart(
                "Thống kê số lượng phiếu mượn theo " + selected,
                selected,
                "Số lượng phiếu",
                dataset,
                PlotOrientation.VERTICAL,
                true, true, false);
            return barChart;
        } catch (SQLException e) {
            return null;
        }
    }
}
