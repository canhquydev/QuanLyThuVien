
package Logic;
import CRUD.ISachCRUD;
import CRUD.SachCRUD;
import Exception.InvalidDataException;
import java.time.LocalDate;
import javax.swing.*;
import Object.Sach;
import java.util.List;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author PC
 */
public class LogicSach {
    ISachCRUD sach = new SachCRUD();
    public void checked(Sach s) throws InvalidDataException{
        if(s.getSoLuong() <= 0){
            throw new InvalidDataException("Vui lòng nhập số lượng sách hợp lệ");
        }
        if( s.getTenSach().isEmpty()
            || s.getTacGia().isEmpty()
            || s.getTheLoai().isEmpty()
            || s.getNhaXuatBan().isEmpty()
            )
        {
            throw new InvalidDataException("Vui lòng nhập đầy đủ thông tin!");
        }
        
    }
    public void ktraTrungSach(Sach s) throws InvalidDataException{
        for(Sach S: sach.getDsSach()){
            if(s.getTacGia().trim().equals(S.getTacGia().trim()) && 
               s.getTenSach().trim().equals(S.getTenSach().trim()) &&
               s.getTheLoai().equals(S.getTheLoai()) &&
               s.getNhaXuatBan().trim().equals(S.getNhaXuatBan().trim()) &&
               s.getNamXuatBan() == S.getNamXuatBan()){
                throw new InvalidDataException("Sách đã tồn tại trong hệ thống");
            }
        }
    }
    public int themSach(Sach s) throws InvalidDataException{
        checked(s);
        ktraTrungSach(s);
        if(sach.themSach(s) == 1){
            return 1;
        }
        return 0;
    }
    public int suaSach(Sach s) throws InvalidDataException{
        checked(s);
        if(sach.suaSach(s) == 1){
            return 1;
        }
        return 0;
    }
    public int xoaSach(String maSach){
        if(sach.xoaSach(maSach) == 1){
            return 1;
        }
        return 0;
    }
}
