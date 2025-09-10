/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CRUD;

import Object.NguoiMuon;
import java.util.ArrayList;

/**
 *
 * @author HoCanhQuy
 */
public interface INguoiMuonCRUD {
    ArrayList<NguoiMuon> getDsNguoiMuon();
    int themNguoiMuon(NguoiMuon nm);
    int suaNguoiMuon(NguoiMuon nm);
    int xoaNguoiMuon(String maNguoiMuon);
}
