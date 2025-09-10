/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CRUD;

import Object.Sach;
import java.util.ArrayList;

/**
 *
 * @author HoCanhQuy
 */
public interface ISachCRUD {
    ArrayList<Sach> getDsSach();
    int updateSoLuong(String maSach, int soLuong);
    int themSach(Sach s);
    int suaSach(Sach s);
    int xoaSach(String maSach);
}
