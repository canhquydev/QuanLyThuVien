/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Logic;
import CRUD.IPhieuMuonCRUD;
import CRUD.ISachCRUD;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import CRUD.PhieuMuonCRUD;
import Object.Sach;
import CRUD.SachCRUD;
import Exception.InvalidDataException;
import Object.PhieuMuon;
import java.util.Date;

/**
 *
 * @author HoCanhQuy
 */
public class LogicPhieuMuon{
    ISachCRUD sach = new SachCRUD();
    IPhieuMuonCRUD phieuMuon = new PhieuMuonCRUD();
    public int kiemTraSoLuongPM(String maNguoiMuon){
        int cnt = 0;
        for(PhieuMuon pm : phieuMuon.getDanhSachPM()){
            if(pm.getMaNguoiMuon().trim().equals(maNguoiMuon.trim()) && pm.getTinhTrang().trim().equals("Đang mượn")){
                cnt++;
            }
        }
        if(cnt >= 3){
            return 0;
        }
        return 1;
    }
    
    public int kiemTraSoLuongSach(String maSach, int soLuongMuon){
        for(Sach s: sach.getDsSach()){
            if(maSach.trim().equals(s.getMaSach().trim()) && s.getSoLuong() < soLuongMuon){
                return 0; 
            }
        }
        return 1;
    }
    
    public int themPhieu(PhieuMuon phieu) throws InvalidDataException{
        checked(phieu);
        if(kiemTraSoLuongSach(phieu.getMaSach(), phieu.getSoLuong()) == 0){
            throw new InvalidDataException("Số lượng sách không đủ để cho mượn!");
        }
        else if(kiemTraSoLuongPM(phieu.getMaNguoiMuon()) == 0){
            throw new InvalidDataException("Người mượn không thể mượn quá 3 tài liệu");
        }
        
        int soLuongCon = 0;
        for(Sach s: sach.getDsSach()){
            if(s.getMaSach().trim().equals(phieu.getMaSach().trim())){
                soLuongCon = s.getSoLuong() - phieu.getSoLuong();
                s.setSoLuong(soLuongCon);
            }
        }
        
        int kq1 = phieuMuon.themPhieuMuon(phieu);
        int kq2 = sach.updateSoLuong(phieu.getMaSach(), soLuongCon);
        if(kq1 != -1 && kq2 != -1){
//            JOptionPane.showMessageDialog(null, "Thêm phiếu mượn thành công");
            return 1;
        }
        return 0;
    }
    
    public int suaPhieu(PhieuMuon phieu) throws InvalidDataException{
        checked(phieu);
        if(phieuMuon.suaPhieu(phieu) != -1){
            return 1;
        }
        return 0;
    }
    
    public void checked(PhieuMuon phieu) throws InvalidDataException{
        java.sql.Date ngayTra = java.sql.Date.valueOf(phieu.getNgayTra());
        java.util.Date ngayHienTai = new java.util.Date();
        if(ngayTra.compareTo(ngayHienTai) <= 0){
            throw new InvalidDataException("Ngày trả phải sau ngày hiện tại.");
        }
        if(phieu.getSoLuong() <= 0){
            throw new InvalidDataException("Vui lòng nhập số lượng hợp lệ");
        }
        if(phieu.getMaSach().equals("Chọn mã sách") ||
           phieu.getMaNguoiMuon().equals("Chọn mã người mượn")
           ){
            throw new InvalidDataException("Vui lòng nhập đầy đủ thông tin phiếu mượn.");
        }
    }
    
    public int traSach(PhieuMuon phieu) throws InvalidDataException{
        if(phieu.getTinhTrang().trim().equals("Đã trả")){
            throw new InvalidDataException("Phiếu mượn đã được trả trước đó");
        }
        int soLuongNew = phieu.getSoLuong();
        for(Sach s:sach.getDsSach()){
            if(s.getMaSach().trim().equals(phieu.getMaSach().trim())){
                soLuongNew += s.getSoLuong();
            }
        }
        System.out.println(soLuongNew);
        int rs = phieuMuon.traSach(phieu);
        int rs1 = sach.updateSoLuong(phieu.getMaSach(), soLuongNew);
        if(rs != -1 && rs1 != -1){
            return 1;
        }
        return 0;
    }
    
    public int giaHanPhieu(PhieuMuon phieu) throws InvalidDataException{
        if(phieu.getTinhTrang().trim().equals("Đã trả")){
            throw new InvalidDataException("Phiếu mượn đã được trả, không thể gia hạn");
        }
        if(phieu.getSoLanGiaHan() >= 4){
            throw new InvalidDataException("Phiếu mượn không thể gia hạn quá 3 lần");
        }
        if(phieuMuon.giaHanPhieu(phieu) == 1){
            return 1;
        }
        return 0;
    }
}
