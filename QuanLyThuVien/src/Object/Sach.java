/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Object;

/**
 *
 * @author HoCanhQuy
 */
public class Sach {
    private String maSach;
    private String tenSach;
    private String tacGia;
    private String theLoai;
    private String nhaXuatBan;
    private int namXuatBan;
    private int soLuong;
    private int tinhTrang;

    public Sach(String maSach, String tenSach, String tacGia, String theLoai, String nhaXuatBan, int namXuatBan, int soLuong, int tinhTrang) {
        this.maSach = maSach;
        this.tenSach = tenSach;
        this.tacGia = tacGia;
        this.theLoai = theLoai;
        this.nhaXuatBan = nhaXuatBan;
        this.namXuatBan = namXuatBan;
        this.soLuong = soLuong;
        this.tinhTrang = tinhTrang;
    }
    
    public String getMaSach() {
        return maSach;
    }

    public String getTenSach() {
        return tenSach;
    }

    public String getTacGia() {
        return tacGia;
    }

    public String getTheLoai() {
        return theLoai;
    }

    public String getNhaXuatBan() {
        return nhaXuatBan;
    }

    public int getNamXuatBan() {
        return namXuatBan;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public int getTinhTrang() {
        return tinhTrang;
    }
    
    public void setTinhTrang(int tinhTrang) {
        this.tinhTrang = tinhTrang;
    }
    
    public void setMaSach(String maSach) {
        this.maSach = maSach;
    }

    public void setTenSach(String tenSach) {
        this.tenSach = tenSach;
    }

    public void setTacGia(String tacGia) {
        this.tacGia = tacGia;
    }

    public void setTheLoai(String theLoai) {
        this.theLoai = theLoai;
    }

    public void setNhaXuatBan(String nhaXuatBan) {
        this.nhaXuatBan = nhaXuatBan;
    }

    public void setNamXuatBan(int namXuatBan) {
        this.namXuatBan = namXuatBan;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }
    
    
    @Override
    public String toString() {
        return "Sach{" + "maSach=" + maSach + ", tenSach=" + tenSach + ", tacGia=" + tacGia + ", theLoai=" + theLoai + ", nhaXuatBan=" + nhaXuatBan + ", namXuatBan=" + namXuatBan + ", soLuong=" + soLuong + '}';
    }
    
}
