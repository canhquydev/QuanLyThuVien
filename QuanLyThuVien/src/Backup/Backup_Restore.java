/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Backup;

import CRUD.ConnectSQL;
import GUI.tabNapDuLieu;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author HoCanhQuy
 */
public class Backup_Restore {
    public int taoFileBackup(String duongDan){
        String sql = "BACKUP DATABASE QLTV TO DISK = N'" + duongDan + "'";
        try(Connection conn = ConnectSQL.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)){
            return stmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(tabNapDuLieu.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
    
    public int khoiPhuc(String file){
        int rs = 0;
        String sql = "USE MASTER RESTORE DATABASE QLTV FROM DISK = N'" + file + "'"  + "WITH REPLACE";
        try(Connection conn = ConnectSQL.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)){
            rs = stmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(tabNapDuLieu.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }
}
