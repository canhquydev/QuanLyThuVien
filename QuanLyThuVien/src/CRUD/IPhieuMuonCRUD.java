/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CRUD;

import Object.PhieuMuon;
import java.sql.Date;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import org.jfree.chart.JFreeChart;

/**
 *
 * @author HoCanhQuy
 */
public interface IPhieuMuonCRUD {
    ArrayList<PhieuMuon> getDanhSachPM();
    int autoUpdate(String maPhieuMuon, String tinhTrang);
    int themPhieuMuon(PhieuMuon pm);
    int traSach(PhieuMuon pm);
    int suaPhieu(PhieuMuon pm);
    int giaHanPhieu(PhieuMuon pm);
    DefaultTableModel thongKeMuonSachTable(String selected, Date startDate, 
    Date endDate, DefaultTableModel model);
    JFreeChart thongKeMuonSachChart(String selected, Date startDate, Date endDate);
}
