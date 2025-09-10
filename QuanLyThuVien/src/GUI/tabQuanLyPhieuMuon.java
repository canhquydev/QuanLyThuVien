package GUI;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */

import CRUD.ConnectSQL;
import CRUD.INguoiMuonCRUD;
import CRUD.IPhieuMuonCRUD;
import CRUD.ISachCRUD;
import Object.PhieuMuon;
import Object.NguoiMuon;
import Object.Sach;
import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.awt.Color;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.util.*; 
import CRUD.NguoiMuonCRUD;
import CRUD.PhieuMuonCRUD;
import CRUD.SachCRUD;
import Exception.InvalidDataException;
import Logic.LogicPhieuMuon;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import java.util.regex.Pattern;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
public class tabQuanLyPhieuMuon extends javax.swing.JPanel {

    private DefaultTableModel tableModel;
    private IPhieuMuonCRUD phieuMuon;
    private ArrayList<PhieuMuon> dsPhieuMuon;
    private ISachCRUD sach;
    private ArrayList<Sach> dsSach;
    private INguoiMuonCRUD nguoiMuon;
    private ArrayList<NguoiMuon> dsNguoiMuon;
    private int selectedRow = -1;
    private int selected = -1;
    public tabQuanLyPhieuMuon() {
        initComponents();
        layDuLieu();
        tbHienThi.setShowGrid(true);
        tbHienThi.setGridColor(new Color(224, 224, 224));
        FlatSVGIcon iconAdd = new FlatSVGIcon("Images/Add.svg", 25, 25);
        FlatSVGIcon iconDelete = new FlatSVGIcon("Images/delete.svg", 25, 25);
        FlatSVGIcon iconEdit = new FlatSVGIcon("Images/edit.svg", 25, 25);
        FlatSVGIcon iconRefresh = new FlatSVGIcon("Images/refresh.svg", 22, 22);
        FlatSVGIcon iconExit = new FlatSVGIcon("Images/exit.svg", 25, 25);
        FlatSVGIcon iconSearch = new FlatSVGIcon("Images/search.svg", 18, 18);
        FlatSVGIcon iconRenew = new FlatSVGIcon("Images/renew.svg", 20, 20);
        FlatSVGIcon iconCheck = new FlatSVGIcon("Images/check.svg", 18, 18);
        btnMuonSach.setIcon(iconAdd);
        btnTraSach.setIcon(iconDelete);
        btnSua.setIcon(iconEdit);
        btnLamMoi.setIcon(iconRefresh);
        btnThoat.setIcon(iconExit);
        btnTimKiem.setIcon(iconSearch);
        btnGiaHan.setIcon(iconRenew);
        btnKiemTra.setIcon(iconCheck);
        
    }
    
    public void layDuLieu(){
        tableModel = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; 
            }
        };
        tableModel.addColumn("Mã phiếu mượn");
        tableModel.addColumn("Mã sách");
        tableModel.addColumn("Mã người mượn");
        tableModel.addColumn("Số lượng");
        tableModel.addColumn("Ngày mượn");
        tableModel.addColumn("Ngày trả");
        tableModel.addColumn("Tình trạng");
        tableModel.addColumn("Số lần gia hạn");
        
        phieuMuon = new PhieuMuonCRUD();
        dsPhieuMuon = phieuMuon.getDanhSachPM();
        int cnt = 0;
        for(PhieuMuon pm: dsPhieuMuon){
            Date ngayTra = Date.valueOf(pm.getNgayTra());
            LocalDate localDate = LocalDate.now();
            Date ngayHienTai = Date.valueOf(localDate);
            if(ngayTra.before(ngayHienTai) && !pm.getTinhTrang().equals("Đã hết hạn")){
                if(!pm.getTinhTrang().equals("Đã trả")){
                    phieuMuon.autoUpdate(pm.getMaPhieuMuon(), "Đã hết hạn");
                    cnt++;
                }
            }
            else if(ngayTra.after(ngayHienTai)&& pm.getTinhTrang().equals("Đã hết hạn")){
                if(!pm.getTinhTrang().equals("Đã trả")){
                        phieuMuon.autoUpdate(pm.getMaPhieuMuon(), "Đang mượn");
                        cnt++;
                    }
            }
        }
        System.out.println("Đã update " + cnt + " hàng");
        //lấy lại ds phiếu
        dsPhieuMuon.clear();
        dsPhieuMuon = phieuMuon.getDanhSachPM();
        
        cbMaPhieuMuon.removeAllItems();
        cbMaPhieuMuon.addItem("Chọn mã phiếu mượn");
        for(PhieuMuon pm: dsPhieuMuon){
            cbMaPhieuMuon.addItem(pm.getMaPhieuMuon().trim());
            tableModel.addRow(new Object[]{
                pm.getMaPhieuMuon().trim(),
                pm.getMaSach().trim(),
                pm.getMaNguoiMuon().trim(),
                pm.getSoLuong(),
                pm.getNgayMuon(),
                pm.getNgayTra(),
                pm.getTinhTrang(),
                pm.getSoLanGiaHan()
            });
        }
        tbHienThi.setModel(tableModel);
        
        
        sach = new SachCRUD();
        dsSach = sach.getDsSach();
        cbMaSach.removeAllItems();
        cbMaSach.addItem("Chọn mã sách");
        for(Sach s:dsSach){
            if(s.getTinhTrang() == 1){
                cbMaSach.addItem(s.getMaSach().trim());
            }
        }
        
        nguoiMuon = new NguoiMuonCRUD();
        dsNguoiMuon = nguoiMuon.getDsNguoiMuon();
        cbMaNguoiMuon.removeAllItems();
        cbMaNguoiMuon.addItem("Chọn mã người mượn");
        for(NguoiMuon s:dsNguoiMuon){
            cbMaNguoiMuon.addItem(s.getMaNguoiMuon().trim());
        }
        
        cbTimKiem.removeAllItems();
        cbTimKiem.addItem("Tìm kiếm theo mã phiếu mượn");
        cbTimKiem.addItem("Tìm kiếm theo mã sách");
        cbTimKiem.addItem("Tìm kiếm theo mã người mượn");
        cbTimKiem.addItem("Những sách đang mượn");
        cbTimKiem.addItem("Những phiếu đã hết hạn");
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        cbMaPhieuMuon = new javax.swing.JComboBox<>();
        cbMaSach = new javax.swing.JComboBox<>();
        cbMaNguoiMuon = new javax.swing.JComboBox<>();
        txtSoLuong = new javax.swing.JSpinner();
        txtNgayTra = new com.toedter.calendar.JDateChooser();
        jLabel7 = new javax.swing.JLabel();
        txtSoLanGiaHan = new javax.swing.JSpinner();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        btnTimKiem = new javax.swing.JButton();
        cbTimKiem = new javax.swing.JComboBox<>();
        pnChucNangKhac = new javax.swing.JPanel();
        btnTraSach = new javax.swing.JButton();
        btnMuonSach = new javax.swing.JButton();
        btnLamMoi = new javax.swing.JButton();
        btnGiaHan = new javax.swing.JButton();
        btnKiemTra = new javax.swing.JButton();
        lbSoLuong = new javax.swing.JLabel();
        btnThoat = new javax.swing.JButton();
        btnSua = new javax.swing.JButton();
        pnDanhSach = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbHienThi = new javax.swing.JTable();

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thông tin chi tiết phiếu mượn", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 3, 14))); // NOI18N

        jLabel6.setText("Mã phiếu mượn:");

        jLabel3.setText("Mã sách:");

        jLabel2.setText("Mã người mượn: ");

        jLabel4.setText("Số lượng: ");

        jLabel5.setText("Ngày trả: ");

        cbMaPhieuMuon.setEditable(true);

        cbMaSach.setEditable(true);

        cbMaNguoiMuon.setEditable(true);

        txtSoLuong.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtSoLuongFocusGained(evt);
            }
        });

        txtNgayTra.setDateFormatString("y-M-d");

        jLabel7.setText("Số lần gia hạn: ");

        txtSoLanGiaHan.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtSoLanGiaHanFocusGained(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel3)
                        .addComponent(jLabel2)
                        .addComponent(jLabel4)
                        .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel6))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addGap(30, 30, 30)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtSoLuong, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtNgayTra, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 188, Short.MAX_VALUE)
                    .addComponent(cbMaSach, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cbMaNguoiMuon, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cbMaPhieuMuon, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtSoLanGiaHan))
                .addGap(30, 30, 30))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(cbMaPhieuMuon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addComponent(jLabel3))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(cbMaSach, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(8, 8, 8)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbMaNguoiMuon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(8, 8, 8)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtNgayTra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addGap(8, 8, 8)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtSoLanGiaHan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(11, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Nhập thông tin tìm kiếm", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 3, 14))); // NOI18N

        jLabel1.setText("Tìm kiếm theo:");

        btnTimKiem.setText("Tìm kiếm");
        btnTimKiem.setToolTipText("");
        btnTimKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimKiemActionPerformed(evt);
            }
        });

        cbTimKiem.setForeground(new java.awt.Color(153, 153, 153));
        cbTimKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbTimKiemActionPerformed(evt);
            }
        });

        pnChucNangKhac.setBackground(new java.awt.Color(255, 255, 255));
        pnChucNangKhac.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Chức năng khác", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 3, 12))); // NOI18N

        btnTraSach.setText("Trả sách");
        btnTraSach.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTraSachActionPerformed(evt);
            }
        });

        btnMuonSach.setText("Mượn sách");
        btnMuonSach.setToolTipText("");
        btnMuonSach.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMuonSachActionPerformed(evt);
            }
        });

        btnLamMoi.setText("Làm mới");
        btnLamMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLamMoiActionPerformed(evt);
            }
        });

        btnGiaHan.setText("Gia hạn");
        btnGiaHan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGiaHanActionPerformed(evt);
            }
        });

        btnKiemTra.setText("Kiểm tra số lượng ");
        btnKiemTra.setToolTipText("");
        btnKiemTra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKiemTraActionPerformed(evt);
            }
        });

        btnThoat.setText("Thoát");
        btnThoat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThoatActionPerformed(evt);
            }
        });

        btnSua.setText("Sửa phiếu");
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnChucNangKhacLayout = new javax.swing.GroupLayout(pnChucNangKhac);
        pnChucNangKhac.setLayout(pnChucNangKhacLayout);
        pnChucNangKhacLayout.setHorizontalGroup(
            pnChucNangKhacLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnChucNangKhacLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnKiemTra, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(pnChucNangKhacLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(pnChucNangKhacLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(btnThoat, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnMuonSach, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                    .addComponent(btnGiaHan, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pnChucNangKhacLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnTraSach, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnLamMoi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnSua, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE))
                .addGap(73, 73, 73))
        );
        pnChucNangKhacLayout.setVerticalGroup(
            pnChucNangKhacLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnChucNangKhacLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnChucNangKhacLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnKiemTra)
                    .addComponent(lbSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnChucNangKhacLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnMuonSach)
                    .addComponent(btnTraSach))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnChucNangKhacLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnGiaHan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnSua, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnChucNangKhacLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnLamMoi)
                    .addComponent(btnThoat))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(cbTimKiem, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnTimKiem))
                    .addComponent(pnChucNangKhac, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(12, 12, 12))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTimKiem))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnChucNangKhac, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pnDanhSach.setBackground(new java.awt.Color(255, 255, 255));
        pnDanhSach.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Danh Sách", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 3, 14))); // NOI18N

        tbHienThi.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã phiếu mượn", "Mã sách", "Mã người mượn", "Số lượng", "Ngày mượn", "Ngày trả", "Tình trạng", "Số lần gia hạn"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbHienThi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbHienThiMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbHienThi);

        javax.swing.GroupLayout pnDanhSachLayout = new javax.swing.GroupLayout(pnDanhSach);
        pnDanhSach.setLayout(pnDanhSachLayout);
        pnDanhSachLayout.setHorizontalGroup(
            pnDanhSachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
            .addGroup(pnDanhSachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 837, Short.MAX_VALUE))
        );
        pnDanhSachLayout.setVerticalGroup(
            pnDanhSachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 237, Short.MAX_VALUE)
            .addGroup(pnDanhSachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 237, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 43, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(pnDanhSach, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(5, 5, 5)
                .addComponent(pnDanhSach, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(40, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txtSoLuongFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtSoLuongFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSoLuongFocusGained

    private void btnTimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimKiemActionPerformed
        // TODO add your handling code here:
        String txtTimKiem = cbTimKiem.getSelectedItem().toString();
        String noiDungTK = "";
        TableRowSorter sorter = new TableRowSorter(tableModel);
        tbHienThi.setRowSorter(sorter);
        if(txtTimKiem.equals("Tìm kiếm theo mã phiếu mượn")){
            if(cbMaPhieuMuon.getSelectedItem().toString().trim().equals("Chọn mã phiếu mượn")){
                JOptionPane.showMessageDialog(this, "Vui lòng chọn mã phiếu mượn để tìm kiếm");
                return;
            }
            noiDungTK = cbMaPhieuMuon.getSelectedItem().toString();
            sorter.setRowFilter(RowFilter.regexFilter("^" + Pattern.quote(noiDungTK)+ "$", 0));
        }

        else if(txtTimKiem.equals("Tìm kiếm theo mã sách")){
            if(cbMaSach.getSelectedItem().toString().equals("Chọn mã sách")){
                JOptionPane.showMessageDialog(this, "Vui lòng chọn mã sách để tìm kiếm");
                return;
            }
            noiDungTK = cbMaSach.getSelectedItem().toString();
            sorter.setRowFilter(RowFilter.regexFilter("^" + Pattern.quote(noiDungTK)+ "$", 1));
        }
        else if(txtTimKiem.equals("Tìm kiếm theo mã người mượn")){
            if(cbMaNguoiMuon.getSelectedItem().toString().equals("Chọn mã người mượn")){
                JOptionPane.showMessageDialog(this, "Vui lòng chọn mã người mượn để tìm kiếm");
                return;
            }
            noiDungTK = cbMaNguoiMuon.getSelectedItem().toString();
            sorter.setRowFilter(RowFilter.regexFilter("^" + Pattern.quote(noiDungTK)+ "$", 2));
        }
        else if(txtTimKiem.equals("Những sách đang mượn")){
            noiDungTK = "Đang mượn";
            sorter.setRowFilter(RowFilter.regexFilter("^" + Pattern.quote(noiDungTK)+ "$", 6));
        }
        else if(txtTimKiem.equals("Những phiếu đã hết hạn")){
            noiDungTK = "Đã hết hạn";
            sorter.setRowFilter(RowFilter.regexFilter("^" + Pattern.quote(noiDungTK)+ "$", 6));
        }
    }//GEN-LAST:event_btnTimKiemActionPerformed

    private void cbTimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbTimKiemActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbTimKiemActionPerformed

    private void btnTraSachActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTraSachActionPerformed
        // TODO add your handling code here:
        if(selectedRow < 0){
            JOptionPane.showMessageDialog(this, "Chọn dòng để trả sách");
            return;
        }
        try{
            String maPhieuMuon = tableModel.getValueAt(selectedRow, 0).toString().trim();
            String maSach = tableModel.getValueAt(selectedRow, 1).toString().trim();
            String maNguoiMuon = tableModel.getValueAt(selectedRow, 2).toString().trim();
            int soLuong = Integer.parseInt(tableModel.getValueAt(selectedRow, 3).toString());
            String ngayMuon =  tableModel.getValueAt(selectedRow, 4).toString().trim();
            LocalDate date = LocalDate.now();
            String ngayTra = Date.valueOf(date).toString();
            int soLanGiaHan = Integer.parseInt(tableModel.getValueAt(selectedRow, 7).toString());
            String tinhTrang = tableModel.getValueAt(selectedRow,6).toString();
            PhieuMuon pm = new PhieuMuon(maPhieuMuon, maSach, maNguoiMuon, soLuong, ngayMuon, ngayTra, tinhTrang, soLanGiaHan);
            LogicPhieuMuon kt = new LogicPhieuMuon();
            int rs = kt.traSach(pm);
            if(rs == -1){
                JOptionPane.showMessageDialog(this, "Chọn phiếu đang mượn sách");
                return;
            }
            if(rs == 1){
                JOptionPane.showMessageDialog(this, "Trả sách thành công");
                tableModel.setValueAt(date, selectedRow, 5);
                tableModel.setValueAt("Đã trả", selectedRow, 6);
            }
            else{
                JOptionPane.showMessageDialog(this, "Trả sách không thành công, lỗi không xác định");
            }
        }catch(InvalidDataException iDE){
            JOptionPane.showMessageDialog(this, iDE.getMessage());
        }catch(Exception e){
            JOptionPane.showMessageDialog(this, "Lỗi không xác định: " + e.getMessage());
        }
        
    }//GEN-LAST:event_btnTraSachActionPerformed

    private void btnMuonSachActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMuonSachActionPerformed
        // TODO add your handling code here:
        try{
            String maPhieuMuon;
            int id = 1;
            while(true){
                int chk = 0;
                maPhieuMuon = "PM" + Integer.toString(id);
                for(PhieuMuon s : phieuMuon.getDanhSachPM()){
                    if(s.getMaPhieuMuon().trim().equals(maPhieuMuon))
                    chk++;
                }
                if(chk == 0){
                    break;
                }
                id += 1;
            }
            String maNguoiMuon = cbMaNguoiMuon.getSelectedItem().toString();
            String maSach = cbMaSach.getSelectedItem().toString();
            int soLuong = Integer.parseInt(txtSoLuong.getValue().toString());
            LocalDate localDateMuon = LocalDate.now();
            String ngayMuon = localDateMuon.toString();
            java.util.Date ngayTra = null;
            ngayTra = txtNgayTra.getDate();
            if(ngayTra == null){
                JOptionPane.showMessageDialog(this, "Vui lòng nhập ngày trả");
                return;
            }
            String strNgayTra = new Date(ngayTra.getTime()).toString();
            String tinhTrang = "Đang mượn";
            int soLanGiaHan = 0;
            PhieuMuon pm = new PhieuMuon(maPhieuMuon, maSach, maNguoiMuon, soLuong, ngayMuon, strNgayTra, tinhTrang, soLanGiaHan);
            LogicPhieuMuon kt = new LogicPhieuMuon();

            int rs = kt.themPhieu(pm);
            if(rs == 1){
                JOptionPane.showMessageDialog(null, "Thêm phiếu mượn thành công");
                tableModel.addRow(new Object[]{maPhieuMuon, maSach, maNguoiMuon, soLuong, ngayMuon, strNgayTra, tinhTrang, soLanGiaHan});
            }
            else{
                JOptionPane.showMessageDialog(this, "Thêm phiếu mượn không thành công, lỗi không xác định");
            }
        }catch(InvalidDataException iDE){
            JOptionPane.showMessageDialog(this, iDE.getMessage());
        }catch(Exception e){
            JOptionPane.showMessageDialog(this, "Lỗi không xác định: " + e.getMessage());
        }
    }//GEN-LAST:event_btnMuonSachActionPerformed

    private void btnLamMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLamMoiActionPerformed
        // TODO add your handling code here:
        layDuLieu();
        TableRowSorter sorter = new TableRowSorter(tableModel);
        tbHienThi.setRowSorter(sorter);
        sorter.setRowFilter(RowFilter.regexFilter(""));
        cbTimKiem.setSelectedIndex(0);
        cbTimKiem.setForeground(new Color(153,153,153));
        cbMaNguoiMuon.setSelectedIndex(0);
        cbMaSach.setSelectedIndex(0);
        txtSoLuong.setValue(0);
        lbSoLuong.setText("");
        txtNgayTra.setDate(null);
    }//GEN-LAST:event_btnLamMoiActionPerformed

    private void btnGiaHanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGiaHanActionPerformed
        // TODO add your handling code here:
        if(selectedRow < 0){
            JOptionPane.showMessageDialog(this, "Chọn dòng để gia hạn sách!");
            return;
        }
        try{
            String maPhieuNhap = tableModel.getValueAt(selectedRow, 0).toString();
            String maSach = tableModel.getValueAt(selectedRow, 1).toString();
            String maNguoiMuon = tableModel.getValueAt(selectedRow, 2).toString();
            int soLuong = Integer.parseInt(tableModel.getValueAt(selectedRow, 3).toString());
            String ngayMuon = tableModel.getValueAt(selectedRow, 4).toString();
            String strNgayTra = tableModel.getValueAt(selectedRow, 5).toString();
            String tinhTrang = tableModel.getValueAt(selectedRow, 6).toString();
            int soLanGiaHan = Integer.parseInt(tableModel.getValueAt(selectedRow, 7).toString());

            soLanGiaHan += 1;
            Date ngayTra = Date.valueOf(strNgayTra);
            Calendar c = Calendar.getInstance();
            c.setTime(ngayTra);
            c.add(Calendar.DATE, 3);
            java.util.Date date = c.getTime();
            String strDate = new Date(date.getTime()).toString();

            Date ngayTraSS = Date.valueOf(strDate);
            LocalDate localDate = LocalDate.now();
            Date ngayHomNay = Date.valueOf(localDate);
            if(ngayTraSS.after(ngayHomNay) || ngayTraSS.compareTo(ngayHomNay) == 0){
                tinhTrang = "Đang mượn";
            }

            PhieuMuon pm = new PhieuMuon(maPhieuNhap, maSach, maNguoiMuon, soLuong, ngayMuon, strDate, tinhTrang, soLanGiaHan);

            LogicPhieuMuon kt = new LogicPhieuMuon();
            int rs = kt.giaHanPhieu(pm);
            if(rs == 1){
                JOptionPane.showMessageDialog(this, "Gia hạn sách thành công!");
                tableModel.setValueAt(strDate, selectedRow, 5);
                tableModel.setValueAt(soLanGiaHan, selectedRow, 7);
                tableModel.setValueAt(tinhTrang, selectedRow, 6);
            }
            else{
                JOptionPane.showMessageDialog(this, "Gia hạn không thành công, lỗi không xác định");
            }
        }catch(InvalidDataException iDE){
            JOptionPane.showMessageDialog(this, iDE.getMessage());
        }catch(Exception e){
            JOptionPane.showMessageDialog(this, "Lỗi không xác định: " + e.getMessage());
        }
    }//GEN-LAST:event_btnGiaHanActionPerformed

    private void btnKiemTraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKiemTraActionPerformed
        // TODO add your handling code here:
        String maSach = cbMaSach.getSelectedItem().toString();
        if(maSach.equals("Chọn mã sách")){
            JOptionPane.showMessageDialog(this, "Vui lòng chọn mã sách");
            return;
        }
        int soLuong = Integer.parseInt(txtSoLuong.getValue().toString());
        lbSoLuong.setText("");
        if(soLuong < 0){
            JOptionPane.showMessageDialog(this, "Nhập số lượng phù hợp!");
            return;
        }
        sach = new SachCRUD();
        for(Sach s: sach.getDsSach()){
            if(maSach.trim().equals(s.getMaSach().trim()) && s.getSoLuong() >= soLuong){
                lbSoLuong.setText("Số lượng còn lại là: " + s.getSoLuong() + " (Sẵn sàng cho mượn)");
                lbSoLuong.setForeground(Color.BLUE);
                return;
            }
            else if(maSach.trim().equals(s.getMaSach().trim()) && s.getSoLuong() < soLuong){
                lbSoLuong.setText("Số lượng còn lại là: " + s.getSoLuong() + " (Không đủ số lượng)");
                lbSoLuong.setForeground(Color.RED);
                return;
            }
        }
    }//GEN-LAST:event_btnKiemTraActionPerformed

    private void tbHienThiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbHienThiMouseClicked
        // TODO add your handling code here:
        if(evt.getClickCount() == 1){
            selected = tbHienThi.getSelectedRow();
            selectedRow = tbHienThi.convertRowIndexToModel(selected);
            cbMaPhieuMuon.setSelectedItem(tableModel.getValueAt(selectedRow, 0));
            cbMaSach.setSelectedItem(tableModel.getValueAt(selectedRow, 1));
            cbMaNguoiMuon.setSelectedItem(tableModel.getValueAt(selectedRow, 2));
            txtSoLuong.setValue(tableModel.getValueAt(selectedRow, 3));
            Date ngayTra = Date.valueOf(tableModel.getValueAt(selectedRow, 5).toString());
            txtNgayTra.setDate(ngayTra);
            txtSoLanGiaHan.setValue(tableModel.getValueAt(selectedRow, 7));
        }
        else{
            String maPhieuMuon = tbHienThi.getValueAt(selected, 0).toString();
            String maSach = tbHienThi.getValueAt(selected, 1).toString();
            String maNguoiMuon = tbHienThi.getValueAt(selected, 2).toString();
            chiTietPM thongTinForm = new chiTietPM(maPhieuMuon, maSach, maNguoiMuon);
            thongTinForm.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
            thongTinForm.setVisible(true);
        }
    }//GEN-LAST:event_tbHienThiMouseClicked

    private void btnThoatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThoatActionPerformed
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_btnThoatActionPerformed

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        // TODO add your handling code here:
        if(selectedRow < 0){
            JOptionPane.showMessageDialog(this, "Vui lòng chọn phiếu để sửa");
            return;
        }
        try{
            String maPhieuMuon = tableModel.getValueAt(selectedRow, 0).toString();
            String maSach = cbMaSach.getSelectedItem().toString();
            String maNguoiMuon = cbMaNguoiMuon.getSelectedItem().toString();
            int soLuong = Integer.parseInt(txtSoLuong.getValue().toString());
            String ngayMuon = tableModel.getValueAt(selectedRow, 4).toString();
            java.util.Date ngayTra = null;
            ngayTra = txtNgayTra.getDate();
            if(ngayTra == null){
                JOptionPane.showMessageDialog(this, "Vui lòng nhập ngày trả");
                return;
            }
            String strNgayTra = new Date(ngayTra.getTime()).toString();
            String tinhTrang = tableModel.getValueAt(selectedRow, 6).toString();
            int soLanGiaHan = Integer.parseInt(txtSoLanGiaHan.getValue().toString());
            PhieuMuon pm = new PhieuMuon(maPhieuMuon, maSach, maNguoiMuon, soLuong, ngayMuon, strNgayTra, tinhTrang, soLanGiaHan);
            LogicPhieuMuon kt = new LogicPhieuMuon();

            int rs = kt.suaPhieu(pm);
            if(rs == 1){
                JOptionPane.showMessageDialog(null, "Sửa phiếu mượn thành công");
                tableModel.setValueAt(maSach, selectedRow, 1);
                tableModel.setValueAt(maNguoiMuon, selectedRow, 2);
                tableModel.setValueAt(soLuong, selectedRow, 3);
                tableModel.setValueAt(ngayMuon, selectedRow, 4);
                tableModel.setValueAt(strNgayTra, selectedRow, 5);
                tableModel.setValueAt(tinhTrang, selectedRow, 6);
                tableModel.setValueAt(soLanGiaHan, selectedRow, 7);
            }
            else{
                JOptionPane.showMessageDialog(this, "Gia hạn không thành công, lỗi không xác định");
            }
        }catch(InvalidDataException iDE){
            JOptionPane.showMessageDialog(this, iDE.getMessage());
        }catch(Exception e){
            JOptionPane.showMessageDialog(this, "Lỗi không xác định: " + e.getMessage());
        }
    }//GEN-LAST:event_btnSuaActionPerformed

    private void txtSoLanGiaHanFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtSoLanGiaHanFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSoLanGiaHanFocusGained


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnGiaHan;
    private javax.swing.JButton btnKiemTra;
    private javax.swing.JButton btnLamMoi;
    private javax.swing.JButton btnMuonSach;
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnThoat;
    private javax.swing.JButton btnTimKiem;
    private javax.swing.JButton btnTraSach;
    private javax.swing.JComboBox<String> cbMaNguoiMuon;
    private javax.swing.JComboBox<String> cbMaPhieuMuon;
    private javax.swing.JComboBox<String> cbMaSach;
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
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbSoLuong;
    private javax.swing.JPanel pnChucNangKhac;
    private javax.swing.JPanel pnDanhSach;
    private javax.swing.JTable tbHienThi;
    private com.toedter.calendar.JDateChooser txtNgayTra;
    private javax.swing.JSpinner txtSoLanGiaHan;
    private javax.swing.JSpinner txtSoLuong;
    // End of variables declaration//GEN-END:variables
}
