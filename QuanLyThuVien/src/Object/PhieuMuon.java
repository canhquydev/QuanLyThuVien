/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Object;
import java.util.Date;
/**
 *
 * @author HoCanhQuy
 */
public class PhieuMuon {
    private String maPhieuMuon;
    private String maSach;
    private String maNguoiMuon;
    private int soLuong;
    private String ngayMuon;
    private String ngayTra;
    private String tinhTrang;
    private int soLanGiaHan;
    
    public PhieuMuon(String maPhieuMuon, String maSach, String maNguoiMuon, int soLuong, String ngayMuon, String ngayTra, String tinhTrang, int soLanGiaHan) {
        this.maPhieuMuon = maPhieuMuon;
        this.maSach = maSach;
        this.maNguoiMuon = maNguoiMuon;
        this.soLuong = soLuong;
        this.ngayMuon = ngayMuon;
        this.ngayTra = ngayTra;
        this.tinhTrang = tinhTrang;
        this.soLanGiaHan = soLanGiaHan;
    }

    public String getMaPhieuMuon() {
        return maPhieuMuon;
    }

    public String getMaSach() {
        return maSach;
    }

    public String getMaNguoiMuon() {
        return maNguoiMuon;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public String getNgayMuon() {
        return ngayMuon;
    }

    public String getNgayTra() {
        return ngayTra;
    }

    public String getTinhTrang() {
        return tinhTrang;
    }
    
    public int getSoLanGiaHan(){
        return soLanGiaHan;
    }
    
    public void setMaPhieuMuon(String maPhieuMuon) {
        this.maPhieuMuon = maPhieuMuon;
    }

    public void setMaSach(String maSach) {
        this.maSach = maSach;
    }

    public void setMaNguoiMuon(String maNguoiMuon) {
        this.maNguoiMuon = maNguoiMuon;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public void setNgayMuon(String ngayMuon) {
        this.ngayMuon = ngayMuon;
    }

    public void setNgayTra(String ngayTra) {
        this.ngayTra = ngayTra;
    }

    public void setTinhTrang(String tinhTrang) {
        this.tinhTrang = tinhTrang;
    }

    public void setSoLanGiaHan(int soLanGiaHan) {
        this.soLanGiaHan = soLanGiaHan;
    }
    
   
    @Override
    public String toString() {
        return "PhieuMuon{" + "maPhieuMuon=" + maPhieuMuon + ", maSach=" + maSach + ", maNguoiMuon=" + maNguoiMuon + ", soLuong=" + soLuong + ", ngayMuon=" + ngayMuon + ", ngayTra=" + ngayTra + ", tinhTrang=" + tinhTrang + '}';
    }
    
}
