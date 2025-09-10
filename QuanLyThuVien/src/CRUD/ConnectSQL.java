/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CRUD;
import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.sql.Connection;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author HoCanhQuy
 */
public class ConnectSQL {
    public static Connection getConnection(){
        var server = "0D75D1721846358\\SQLEXPRESS";
        var user = "sa";
        var pass = "123456789";
        var db = "QLTV";
        var port = 1433;
        SQLServerDataSource ds = new SQLServerDataSource();
        ds.setServerName(server);
        ds.setUser(user);
        ds.setPassword(pass);
        ds.setDatabaseName(db);
        ds.setPortNumber(port);
        ds.setTrustServerCertificate(true); 
        ds.setEncrypt("true");
        try {
            Connection conn = ds.getConnection();
            return conn;
        } catch (SQLServerException ex) {
            Logger.getLogger(ConnectSQL.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
}
