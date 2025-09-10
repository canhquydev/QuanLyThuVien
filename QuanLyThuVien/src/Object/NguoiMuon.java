/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Object;

/**
 *
 * @author HoCanhQuy
 */
public class NguoiMuon {
    private String maNguoiMuon;
    private String hoTen;
    private String gioiTinh;
    private String diaChi;
    private String soDienThoai;
    private String Email;

    public NguoiMuon(String maNguoiMuon, String hoTen, String gioiTinh, String diaChi, String soDienThoai, String Email) {
        this.maNguoiMuon = maNguoiMuon;
        this.hoTen = hoTen;
        this.gioiTinh = gioiTinh;
        this.diaChi = diaChi;
        this.soDienThoai = soDienThoai;
        this.Email = Email;
    }

    public String getMaNguoiMuon() {
        return maNguoiMuon;
    }

    public String getHoTen() {
        return hoTen;
    }

    public String getGioiTinh() {
        return gioiTinh;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public String getSoDienThoai() {
        return soDienThoai;
    }

    public String getEmail() {
        return Email;
    }

    public void setMaNguoiMuon(String maNguoiMuon) {
        this.maNguoiMuon = maNguoiMuon;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public void setGioiTinh(String gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    @Override
    public String toString() {
        return "NguoiMuon{" + "maNguoiMuon=" + maNguoiMuon + ", hoTen=" + hoTen + ", gioiTinh=" + gioiTinh + ", diaChi=" + diaChi + ", soDienThoai=" + soDienThoai + ", Email=" + Email + '}';
    }
    
    
}
