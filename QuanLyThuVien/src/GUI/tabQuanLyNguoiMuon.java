/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package GUI;

import CRUD.INguoiMuonCRUD;
import CRUD.NguoiMuonCRUD;
import Exception.InvalidDataException;
import Logic.LogicNguoiMuon;
import Object.NguoiMuon;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import java.awt.Color;
import java.util.ArrayList;
import java.util.regex.Pattern;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author HoCanhQuy
 */
public class tabQuanLyNguoiMuon extends javax.swing.JPanel {

    private DefaultTableModel tableModel;
    private INguoiMuonCRUD nguoiMuon;
    private ArrayList<NguoiMuon> dsNguoiMuon;
    private int selectedRow = -1;
    private int selected = -1;
    public tabQuanLyNguoiMuon() {
        initComponents();
        tbHienThi.setShowGrid(true);
        tbHienThi.setGridColor(new Color(224, 224, 224));
        FlatSVGIcon iconAdd = new FlatSVGIcon("Images/Add.svg", 32, 32);
        FlatSVGIcon iconDelete = new FlatSVGIcon("Images/delete.svg", 32, 32);
        FlatSVGIcon iconEdit = new FlatSVGIcon("Images/edit.svg", 32, 32);
        FlatSVGIcon iconRefresh = new FlatSVGIcon("Images/refresh.svg", 25, 25);
        FlatSVGIcon iconExit = new FlatSVGIcon("Images/exit.svg", 28, 28);
        FlatSVGIcon iconSearch = new FlatSVGIcon("Images/search.svg", 18, 18);
        btnThem.setIcon(iconAdd);
        btnXoa.setIcon(iconDelete);
        btnSua.setIcon(iconEdit);
        btnLamMoi.setIcon(iconRefresh);
        btnThoat.setIcon(iconExit);
        btnTimKiem.setIcon(iconSearch);
        layDuLieu();
    }
    
    public void layDuLieu(){
        tableModel = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; 
            }
        };
        tableModel.addColumn("Mã người mượn");
        tableModel.addColumn("Họ tên");
        tableModel.addColumn("Giới tính");
        tableModel.addColumn("Địa chỉ");
        tableModel.addColumn("Số điện thoại");
        tableModel.addColumn("Email");
        
        cbMaNguoiMuon.removeAllItems();
        nguoiMuon = new NguoiMuonCRUD();
        dsNguoiMuon = nguoiMuon.getDsNguoiMuon();
        for(NguoiMuon nm: dsNguoiMuon){
            cbMaNguoiMuon.addItem(nm.getMaNguoiMuon().trim());
            tableModel.addRow(new Object[]{
                nm.getMaNguoiMuon().trim(),
                nm.getHoTen().trim(),
                nm.getGioiTinh().trim(),
                nm.getDiaChi(),
                nm.getSoDienThoai(),
                nm.getEmail(),
            });
        }
        tbHienThi.setModel(tableModel);
        
        
        cbTimKiem.removeAllItems();
        cbTimKiem.addItem("Tìm kiếm theo mã người mượn");
        cbTimKiem.addItem("Tìm kiếm theo tên");
        
        cbGioiTinh.removeAllItems();
        cbGioiTinh.addItem("Chọn giới tính");
        cbGioiTinh.addItem("Nam");
        cbGioiTinh.addItem("Nữ");
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        txtSoDienThoai = new javax.swing.JTextField();
        txtHoTen = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtDiaChi = new javax.swing.JTextField();
        cbMaNguoiMuon = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        cbGioiTinh = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        cbTimKiem = new javax.swing.JComboBox<>();
        btnTimKiem = new javax.swing.JButton();
        btnThem = new javax.swing.JButton();
        btnSua = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        btnLamMoi = new javax.swing.JButton();
        btnThoat = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbHienThi = new javax.swing.JTable();

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thông tin người mượn sách:", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 3, 14))); // NOI18N
        jPanel2.setForeground(new java.awt.Color(255, 255, 255));

        jLabel2.setText("Họ tên:");

        jLabel3.setText("Giới tính:");

        jLabel4.setText("Địa chỉ:");

        jLabel5.setText("Số điện thoại:");

        jLabel6.setText("Email:");

        jLabel1.setText("Mã người mượn:");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtDiaChi)
                    .addComponent(txtHoTen)
                    .addComponent(txtEmail)
                    .addComponent(txtSoDienThoai)
                    .addComponent(cbGioiTinh, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cbMaNguoiMuon, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(cbMaNguoiMuon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(7, 7, 7)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtHoTen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(7, 7, 7)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(cbGioiTinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(7, 7, 7)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(7, 7, 7)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtSoDienThoai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(7, 7, 7)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Chức năng", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 3, 14))); // NOI18N

        jLabel7.setText("Tìm kiếm:");

        cbTimKiem.setForeground(new java.awt.Color(153, 153, 153));
        cbTimKiem.setPreferredSize(new java.awt.Dimension(72, 25));

        btnTimKiem.setText("Tìm kiếm");
        btnTimKiem.setToolTipText("");
        btnTimKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimKiemActionPerformed(evt);
            }
        });

        btnThem.setText("Thêm người mượn");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        btnSua.setText("Sửa người mượn");
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });

        btnXoa.setText("Xoá người mượn");
        btnXoa.setPreferredSize(new java.awt.Dimension(119, 39));
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });

        btnLamMoi.setText("Làm mới");
        btnLamMoi.setPreferredSize(new java.awt.Dimension(77, 39));
        btnLamMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLamMoiActionPerformed(evt);
            }
        });

        btnThoat.setText("Thoát");
        btnThoat.setPreferredSize(new java.awt.Dimension(72, 39));
        btnThoat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThoatActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnTimKiem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnXoa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnThem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnLamMoi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnSua, javax.swing.GroupLayout.DEFAULT_SIZE, 128, Short.MAX_VALUE))
                .addGap(31, 31, 31))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(123, 123, 123)
                .addComponent(btnThoat, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(136, 136, 136))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel7)
                        .addComponent(btnTimKiem))
                    .addComponent(cbTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(13, 13, 13)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnSua, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnThem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(10, 10, 10)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLamMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addComponent(btnThoat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10))
        );

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Danh sách", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 3, 14))); // NOI18N

        tbHienThi.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4", "Title 5", "Title 6"
            }
        ));
        tbHienThi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbHienThiMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbHienThi);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 257, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(66, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnTimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimKiemActionPerformed
        // TODO add your handling code here:
        String txtTimKiem = cbTimKiem.getSelectedItem().toString();
        String noiDungTK = "";
        TableRowSorter sorter = new TableRowSorter(tableModel);
        tbHienThi.setRowSorter(sorter);
        if(txtTimKiem.equals("Tìm kiếm theo mã người mượn")){
            noiDungTK = cbMaNguoiMuon.getSelectedItem().toString();
            sorter.setRowFilter(RowFilter.regexFilter("^" + Pattern.quote(noiDungTK)+ "$", 0));
        }

        else if(txtTimKiem.equals("Tìm kiếm theo tên")){
            if(txtHoTen.getText().equals("")){
                JOptionPane.showMessageDialog(this, "Vui lòng nhập tên để tìm kiếm");
                return;
            }
            noiDungTK = txtHoTen.getText();
            sorter.setRowFilter(RowFilter.regexFilter("^" + Pattern.quote(noiDungTK)+ "$", 1));
        }
    }//GEN-LAST:event_btnTimKiemActionPerformed

    private void btnLamMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLamMoiActionPerformed
        // TODO add your handling code here:
        cbMaNguoiMuon.setSelectedIndex(0);
        txtHoTen.setText("");
        cbGioiTinh.setSelectedIndex(0);
        txtDiaChi.setText("");
        txtSoDienThoai.setText("");
        txtEmail.setText("");
        layDuLieu();
        TableRowSorter sorter = new TableRowSorter(tableModel);
        tbHienThi.setRowSorter(sorter);
        sorter.setRowFilter(RowFilter.regexFilter(""));
    }//GEN-LAST:event_btnLamMoiActionPerformed

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        // TODO add your handling code here:
        try{
            String maNguoiMuon;
            int id = 1;
            while (true) {
                maNguoiMuon = "NM" + id;                 
                int dem = 0;
                for (NguoiMuon nm : dsNguoiMuon) {             
                    if (nm.getMaNguoiMuon().trim().equals(maNguoiMuon)) {
                        dem++;                      
                        break;
                    }
                }
                if (dem == 0) break;                
                id++;
            }   
            String hoTen = txtHoTen.getText();
            String gioiTinh = cbGioiTinh.getSelectedItem().toString();
            String diaChi = txtDiaChi.getText();
            String soDienThoai = txtSoDienThoai.getText();
            String email = txtEmail.getText();
            NguoiMuon nm = new NguoiMuon(maNguoiMuon, hoTen, gioiTinh, diaChi, soDienThoai, email);
            LogicNguoiMuon kt = new LogicNguoiMuon();
            int rs = kt.themNguoiMuon(nm);
            if(rs == 1){
                JOptionPane.showMessageDialog(this, "Thêm người mượn thành công");
                tableModel.addRow(new Object[] {
                    maNguoiMuon, hoTen, gioiTinh, diaChi, soDienThoai, email
                });
            }
            else{
                JOptionPane.showMessageDialog(this, "Thêm người mượn không thành công, lỗi không xác định");
            }
        }catch(InvalidDataException iDE){
            JOptionPane.showMessageDialog(this, iDE.getMessage());
        }catch(Exception e){
            JOptionPane.showMessageDialog(this, "Lỗi không xác định: " + e.getMessage());
        }
    }//GEN-LAST:event_btnThemActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        // TODO add your handling code here:
        if(selectedRow < 0){
            JOptionPane.showMessageDialog(this, "Vui lòng chọn dòng để sửa");
            return;
        }
        try{
            String maNguoiMuon = cbMaNguoiMuon.getSelectedItem().toString();
            LogicNguoiMuon kt = new LogicNguoiMuon();
            int rs = kt.xoaNguoiMuon(maNguoiMuon);
            if(rs == 1){
                JOptionPane.showMessageDialog(this, "XOÁ người mượn thành công");
                tableModel.removeRow(selectedRow);
            }
            else{
                JOptionPane.showMessageDialog(this, "Xoá người mượn không thành công, lỗi không xác định");
            }
        }catch(InvalidDataException iDE){
            JOptionPane.showMessageDialog(this, iDE.getMessage());
        }catch(Exception e){
            JOptionPane.showMessageDialog(this, "Lỗi không xác định: " + e.getMessage());
        }
    }//GEN-LAST:event_btnXoaActionPerformed

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        // TODO add your handling code here:
        if(selectedRow < 0){
            JOptionPane.showMessageDialog(this, "Vui lòng chọn dòng để sửa");
            return;
        }
        try{
            String maNguoiMuon = cbMaNguoiMuon.getSelectedItem().toString();
            String hoTen = txtHoTen.getText();
            String gioiTinh = cbGioiTinh.getSelectedItem().toString();
            String diaChi = txtDiaChi.getText();
            String soDienThoai = txtSoDienThoai.getText();
            String email = txtEmail.getText();
            NguoiMuon nm = new NguoiMuon(maNguoiMuon, hoTen, gioiTinh, diaChi, soDienThoai, email);
            LogicNguoiMuon kt = new LogicNguoiMuon();
            int rs = kt.suaNguoiMuon(nm);

            if(rs == 1){
                JOptionPane.showMessageDialog(this, "Sửa người mượn thành công");
                tableModel.setValueAt(hoTen, selectedRow, 1);
                tableModel.setValueAt(gioiTinh, selectedRow, 2);
                tableModel.setValueAt(diaChi, selectedRow, 3);
                tableModel.setValueAt(soDienThoai, selectedRow, 4);
                tableModel.setValueAt(email, selectedRow, 5);
            }else{
                JOptionPane.showMessageDialog(this, "Sửa người mượn không thành công, lỗi không xác định");
            }
        }catch(InvalidDataException iDE){
            JOptionPane.showMessageDialog(this, iDE.getMessage());
        }catch(Exception e){
            JOptionPane.showMessageDialog(this, "Lỗi không xác định: " + e.getMessage());
        }
        
    }//GEN-LAST:event_btnSuaActionPerformed

    private void btnThoatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThoatActionPerformed
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_btnThoatActionPerformed

    private void tbHienThiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbHienThiMouseClicked
        // TODO add your handling code here:
        selected = tbHienThi.getSelectedRow();
        selectedRow = tbHienThi.convertRowIndexToModel(selected);
        if(selectedRow >= 0){
            cbMaNguoiMuon.setSelectedItem(tableModel.getValueAt(selectedRow, 0));
            txtHoTen.setText(tableModel.getValueAt(selectedRow, 1).toString());
            cbGioiTinh.setSelectedItem(tableModel.getValueAt(selectedRow, 2).toString());
            txtDiaChi.setText(tableModel.getValueAt(selectedRow, 3).toString());
            txtSoDienThoai.setText(tableModel.getValueAt(selectedRow, 4).toString());
            txtEmail.setText(tableModel.getValueAt(selectedRow, 5).toString());
        }
    }//GEN-LAST:event_tbHienThiMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLamMoi;
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnThoat;
    private javax.swing.JButton btnTimKiem;
    private javax.swing.JButton btnXoa;
    private javax.swing.JComboBox<String> cbGioiTinh;
    private javax.swing.JComboBox<String> cbMaNguoiMuon;
    private javax.swing.JComboBox<String> cbTimKiem;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tbHienThi;
    private javax.swing.JTextField txtDiaChi;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtHoTen;
    private javax.swing.JTextField txtSoDienThoai;
    // End of variables declaration//GEN-END:variables
}
