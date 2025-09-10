/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Logic;

import Backup.Backup_Restore;
import Exception.InvalidDataException;

/**
 *
 * @author HoCanhQuy
 */
public class LogicBackupRestore {
    Backup_Restore bk = new Backup_Restore();
    public int backup(String duongDan) throws InvalidDataException{
        if(duongDan.equals("")){
            throw new InvalidDataException("Đường dẫn rỗng");
        }
        duongDan = duongDan + "\\backup.bak";
        if(bk.taoFileBackup(duongDan) != 0){
            return 1;
        }
        return 0;
    }
    public int restore(String file) throws InvalidDataException{
        if(file.equals("")){
            throw new InvalidDataException("Đường dẫn rỗng");
        }
        if(bk.khoiPhuc(file) != 0){
            return 1;
        }
        return 0;
    }
}
