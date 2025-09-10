/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Logic;

import CRUD.INguoiMuonCRUD;
import CRUD.IPhieuMuonCRUD;
import CRUD.NguoiMuonCRUD;
import CRUD.PhieuMuonCRUD;
import Exception.InvalidDataException;
import Object.NguoiMuon;
import Object.PhieuMuon;

/**
 *
 * @author HoCanhQuy
 */
public class LogicNguoiMuon {
    INguoiMuonCRUD nguoiMuon = new NguoiMuonCRUD();
    IPhieuMuonCRUD phieuMuon = new PhieuMuonCRUD();
    public void checked(NguoiMuon nm) throws InvalidDataException{
        if(nm.getSoDienThoai().length() > 10){
            throw new InvalidDataException("Số điện thoại sai định dạng (VD: 0123456789)");
        }
        if(nm.getHoTen().isEmpty() ||
           nm.getGioiTinh().equals("Chọn giới tính") ||
           nm.getDiaChi().isEmpty() ||
           nm.getSoDienThoai().isEmpty() ||
           nm.getEmail().isEmpty()){
            throw new InvalidDataException("Vui lòng nhập thông tin đầy đủ");
        }
    }
    public int themNguoiMuon(NguoiMuon nm) throws InvalidDataException{
        checked(nm);
        if(nguoiMuon.themNguoiMuon(nm) == 1){
            return 1;
        }
        return 0;
    }
    
    public int suaNguoiMuon(NguoiMuon nm) throws InvalidDataException{
        checked(nm);
        if(nguoiMuon.suaNguoiMuon(nm) == 1){
            return 1;
        }
        return 0;
    }
    
    public int xoaNguoiMuon(String maNguoiMuon) throws InvalidDataException{
        for(PhieuMuon pm: phieuMuon.getDanhSachPM()){
            if(!pm.getMaNguoiMuon().trim().equals(maNguoiMuon)) {
            } else {
                throw new InvalidDataException("Người mượn đang mượn sách, không thể xoá");
            }
        }
        if(nguoiMuon.xoaNguoiMuon(maNguoiMuon) == 1){
            return 1;
        }
        return 0;
    }
}
