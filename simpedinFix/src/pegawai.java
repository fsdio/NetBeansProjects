/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

/**
 *
 * @author Filan
 */
import java.io.File;
import java.sql.*;
//import com.toedter.calendar.JDateChooser;
//import simpedin.koneksi.koneksi;
import java.text.SimpleDateFormat;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Map;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

public class pegawai extends javax.swing.JFrame {

    // deklarasi
    Connection con;
    Statement stat;
    ResultSet rs;
    String sql;
    PreparedStatement prep;
    String tanggal1;
    String tanggal2;
    String sppdID;
    
    DefaultTableModel tabmode;
    
    public pegawai() {
        initComponents();
        //setLocationRelativeTo(null);
        datatable();
        tabletarif();
        tablesppd();
        comboKepada();
        tableuser();
    }
    
//    public tarif() {
//        initComponents();
//        //setLocationRelativeTo(null);
//        tabletarif();
//    }
    
    protected void aktif(){
        txtNip.setEnabled(true);
        txtNama.setEnabled(true);
        txtBagian.setEnabled(true);
        jJabatan.setEnabled(true);
        txtNip.requestFocus();
    }
    
    protected void kosong(){
        txtNip.setText("");
        txtNama.setText("");
        txtBagian.setText("");
        //jJabatan.setSelected(true);
    }
    
    protected void tarkosong(){
        txtTar.setText("");
    }
    
    protected void sppdkosong(){
        taPerihal.setText("");
        tfKota.setText("");
    }
    
    protected void datatable(){
        koneksi DB= new koneksi();
        DB.config();
        con = DB.con;
        stat = DB.stm;
        
        Object [] baris = {"NIP","NAMA","BAGIAN","JABATAN"};
        tabmode=new DefaultTableModel(null,baris);
        tablePegawai.setModel(tabmode);
        
        sql="SELECT * FROM pegawai";
        
        try{
            stat = con.createStatement();
            rs = stat.executeQuery(sql);
            while(rs.next()){
                String a =rs.getString("nip");
                String b =rs.getString("nama");
                String c =rs.getString("bagian");
                String d =rs.getString("jabatan");
                
                String [] data = {a,b,c,d};
                tabmode.addRow(data);
            }
        } catch(Exception e){
        
        }
        
    }
    
    protected void tabletarif(){
        koneksi DB= new koneksi();
        DB.config();
        con = DB.con;
        stat = DB.stm;
        
        Object [] baris = {"JABATAN","ZONASI","TARIF"};
        tabmode=new DefaultTableModel(null,baris);
        tableTarif.setModel(tabmode);
        
        sql="SELECT * FROM tarif";
        
        try{
            stat = con.createStatement();
            rs = stat.executeQuery(sql);
            while(rs.next()){
                String a =rs.getString("jabatan");
                String b =rs.getString("zonasi");
                String c =rs.getString("tarif");
                
                String [] data = {a,b,c};
                tabmode.addRow(data);
            }
        } catch(Exception e){
        
        }
        
    }
    
    protected void tableuser(){
        koneksi DB= new koneksi();
        DB.config();
        con = DB.con;
        stat = DB.stm;
        
        Object [] baris = {"Username","Nama Lengkap"};
        tabmode=new DefaultTableModel(null,baris);
        tableUser.setModel(tabmode);
        
        sql="SELECT * FROM user";
        
        try{
            stat = con.createStatement();
            rs = stat.executeQuery(sql);
            while(rs.next()){
                String a =rs.getString("username");
                String b =rs.getString("nama_lengkap");
                
                String [] data = {a,b};
                tabmode.addRow(data);
            }
        } catch(Exception e){
        
        }
        
    }

    protected void tablesppd(){
        koneksi DB = new koneksi();
        DB.config();
        con = DB.con;
        stat = DB.stm;
        
        Object [] baris = {"Nomor","Kepada","Perihal","Kota Tujuan"};
        tabmode = new DefaultTableModel(null, baris);
        tableSppd.setModel(tabmode);
        
        sql = "SELECT * FROM sppd";
        
        try{
            stat = con.createStatement();
            rs = stat.executeQuery(sql);
            while(rs.next()){
                String a = rs.getString("no_sppd");
                String b = rs.getString("kepada");
                String c = rs.getString("perihal");
                String d = rs.getString("kota_tujuan");
                
                String [] data = {a,b,c,d};
                tabmode.addRow(data);
            }
        } catch(Exception e){
            
        }
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
        navbar = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        sidebar = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        btnPegawai = new javax.swing.JButton();
        btnTarif = new javax.swing.JButton();
        btnSppd = new javax.swing.JButton();
        btnRekap = new javax.swing.JButton();
        btnLogout = new javax.swing.JButton();
        bntUser = new javax.swing.JButton();
        content = new javax.swing.JPanel();
        akun = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jScrollPane5 = new javax.swing.JScrollPane();
        tableUser = new javax.swing.JTable();
        jButton2 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jTextField3 = new javax.swing.JTextField();
        jButton9 = new javax.swing.JButton();
        jLabel29 = new javax.swing.JLabel();
        jPasswordField1 = new javax.swing.JPasswordField();
        jButton6 = new javax.swing.JButton();
        jLabel31 = new javax.swing.JLabel();
        tarif = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jJabat = new javax.swing.JComboBox<>();
        jLabel11 = new javax.swing.JLabel();
        txtTar = new javax.swing.JTextField();
        jButton3 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableTarif = new javax.swing.JTable();
        jLabel10 = new javax.swing.JLabel();
        jZon = new javax.swing.JComboBox<>();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jButton11 = new javax.swing.JButton();
        sppd = new javax.swing.JPanel();
        title1 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        btnSimpan1 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        tableSppd = new javax.swing.JTable();
        btnBersihkan = new javax.swing.JButton();
        jKepada = new javax.swing.JComboBox<>();
        jScrollPane4 = new javax.swing.JScrollPane();
        taPerihal = new javax.swing.JTextArea();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        tfKota = new javax.swing.JTextField();
        jLabel28 = new javax.swing.JLabel();
        cbZona = new javax.swing.JComboBox<>();
        jLabel30 = new javax.swing.JLabel();
        cbTrans = new javax.swing.JComboBox<>();
        jButton7 = new javax.swing.JButton();
        dcPergi = new com.toedter.calendar.JDateChooser();
        dcKembali = new com.toedter.calendar.JDateChooser();
        pegawai = new javax.swing.JPanel();
        title = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtNip = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtNama = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtBagian = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jJabatan = new javax.swing.JComboBox<>();
        btnSimpan = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        buttonUbah = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablePegawai = new javax.swing.JTable();
        btnReset = new javax.swing.JButton();
        txtCari = new javax.swing.JTextField();
        btnCari = new javax.swing.JButton();
        jLabel32 = new javax.swing.JLabel();
        jButton10 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("PEGAWAI");
        getContentPane().setLayout(new java.awt.CardLayout());

        jPanel1.setBackground(new java.awt.Color(204, 255, 204));

        navbar.setBackground(new java.awt.Color(255, 255, 204));

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel1.setText("SISTEM INFORMASI MANAJEMEN PERJALANAN DINAS");

        javax.swing.GroupLayout navbarLayout = new javax.swing.GroupLayout(navbar);
        navbar.setLayout(navbarLayout);
        navbarLayout.setHorizontalGroup(
            navbarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(navbarLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        navbarLayout.setVerticalGroup(
            navbarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, navbarLayout.createSequentialGroup()
                .addContainerGap(17, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addContainerGap())
        );

        sidebar.setBackground(new java.awt.Color(204, 255, 204));
        sidebar.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.white, java.awt.Color.white, java.awt.Color.white, java.awt.Color.white));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Report/logo.jpg"))); // NOI18N
        jLabel2.setText("jLabel2");

        btnPegawai.setBackground(new java.awt.Color(204, 255, 204));
        btnPegawai.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        btnPegawai.setText("PEGAWAI");
        btnPegawai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPegawaiActionPerformed(evt);
            }
        });

        btnTarif.setBackground(new java.awt.Color(204, 255, 204));
        btnTarif.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        btnTarif.setText("TARIF");
        btnTarif.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTarifActionPerformed(evt);
            }
        });

        btnSppd.setBackground(new java.awt.Color(204, 255, 204));
        btnSppd.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        btnSppd.setText("SPPD");
        btnSppd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSppdActionPerformed(evt);
            }
        });

        btnRekap.setBackground(new java.awt.Color(204, 255, 204));
        btnRekap.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        btnRekap.setText("REKAP");
        btnRekap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRekapActionPerformed(evt);
            }
        });

        btnLogout.setBackground(new java.awt.Color(255, 204, 204));
        btnLogout.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        btnLogout.setText("LOGOUT");
        btnLogout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLogoutActionPerformed(evt);
            }
        });

        bntUser.setBackground(new java.awt.Color(204, 255, 204));
        bntUser.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        bntUser.setText("USER");
        bntUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntUserActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout sidebarLayout = new javax.swing.GroupLayout(sidebar);
        sidebar.setLayout(sidebarLayout);
        sidebarLayout.setHorizontalGroup(
            sidebarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, sidebarLayout.createSequentialGroup()
                .addContainerGap(21, Short.MAX_VALUE)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18))
            .addGroup(sidebarLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(sidebarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnPegawai, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnTarif, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnSppd, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnRekap, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnLogout, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(bntUser, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        sidebarLayout.setVerticalGroup(
            sidebarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(sidebarLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addGap(57, 57, 57)
                .addComponent(bntUser)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnPegawai)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnTarif)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSppd)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnRekap)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnLogout)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        content.setBackground(new java.awt.Color(204, 255, 204));
        content.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.white, java.awt.Color.white, java.awt.Color.white, java.awt.Color.white));
        content.setLayout(new java.awt.CardLayout());

        akun.setBackground(new java.awt.Color(204, 255, 204));
        akun.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.white, java.awt.Color.white, java.awt.Color.white, java.awt.Color.white));

        jLabel15.setText("Username");

        jLabel16.setText("Nama Lengkap");

        tableUser.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tableUser.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableUserMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(tableUser);

        jButton2.setText("Hapus");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton8.setText("Simpan");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        jButton9.setText("Cari");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        jLabel29.setText("Password");

        jButton6.setText("PDF");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jLabel31.setText("Username");

        javax.swing.GroupLayout akunLayout = new javax.swing.GroupLayout(akun);
        akun.setLayout(akunLayout);
        akunLayout.setHorizontalGroup(
            akunLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(akunLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(akunLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(akunLayout.createSequentialGroup()
                        .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(269, 269, 269)
                        .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(akunLayout.createSequentialGroup()
                        .addGroup(akunLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(akunLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jPasswordField1, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, akunLayout.createSequentialGroup()
                                    .addGap(6, 6, 6)
                                    .addGroup(akunLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jTextField1, javax.swing.GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE)
                                        .addComponent(jTextField2))))
                            .addGroup(akunLayout.createSequentialGroup()
                                .addComponent(jButton2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton8))
                            .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(akunLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(akunLayout.createSequentialGroup()
                                .addGap(24, 24, 24)
                                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 567, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, akunLayout.createSequentialGroup()
                                .addGap(224, 224, 224)
                                .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton6)
                                .addGap(23, 23, 23))))))
        );
        akunLayout.setVerticalGroup(
            akunLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(akunLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(akunLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(jLabel31))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(akunLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton9)
                    .addComponent(jButton6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(akunLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(akunLayout.createSequentialGroup()
                        .addComponent(jLabel16)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel29)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPasswordField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(akunLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton2)
                            .addComponent(jButton8))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 431, Short.MAX_VALUE))
                .addContainerGap())
        );

        content.add(akun, "card5");

        tarif.setBackground(new java.awt.Color(204, 255, 204));
        tarif.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.white, java.awt.Color.white, java.awt.Color.white, java.awt.Color.white));

        jPanel3.setBackground(new java.awt.Color(153, 255, 255));

        jLabel8.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel8.setText("DATA TARIF");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel8)
                .addGap(252, 252, 252))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(14, Short.MAX_VALUE)
                .addComponent(jLabel8)
                .addContainerGap())
        );

        jLabel9.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel9.setText("Jabatan");

        jJabat.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Rektor / Wakil Rektor", "Kepala Biro", "Manager", "Deputi Manager", "Sekretaris Bagian", "Analis Bagian", "Staff", "Supir" }));
        jJabat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jJabatActionPerformed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel11.setText("Tarif (hanya angka tanpa titik)");

        jButton3.setBackground(new java.awt.Color(255, 255, 153));
        jButton3.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        jButton3.setText("Ubah");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        tableTarif.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Jabatan", "Tarif"
            }
        ));
        tableTarif.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableTarifMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tableTarif);

        jLabel10.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel10.setText("Zonasi");

        jZon.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Zona 1", "Zona 2", "Zona 3" }));

        jLabel17.setForeground(new java.awt.Color(255, 0, 0));
        jLabel17.setText("Keterangan :");

        jLabel18.setForeground(new java.awt.Color(255, 0, 0));
        jLabel18.setText("Zona 1 : Jawa, Sumatera,");

        jLabel19.setForeground(new java.awt.Color(255, 0, 0));
        jLabel19.setText("Sulawesi, Kalimantan");

        jLabel20.setForeground(new java.awt.Color(255, 0, 0));
        jLabel20.setText("Zona 2 : Papua, Nusa Tenggara,");

        jLabel21.setForeground(new java.awt.Color(255, 0, 0));
        jLabel21.setText("Ambon");

        jLabel22.setForeground(new java.awt.Color(255, 0, 0));
        jLabel22.setText("Zona 3 : Bandung, Karawang,");

        jLabel23.setForeground(new java.awt.Color(255, 0, 0));
        jLabel23.setText("Bekasi, Tangerang");

        jLabel24.setForeground(new java.awt.Color(255, 0, 0));
        jLabel24.setText("Serang, Bogor");

        jButton11.setText("Print");
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout tarifLayout = new javax.swing.GroupLayout(tarif);
        tarif.setLayout(tarifLayout);
        tarifLayout.setHorizontalGroup(
            tarifLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tarifLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(tarifLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(tarifLayout.createSequentialGroup()
                        .addGroup(tarifLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tarifLayout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jLabel19)
                                .addGap(27, 27, 27))
                            .addGroup(tarifLayout.createSequentialGroup()
                                .addGroup(tarifLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(tarifLayout.createSequentialGroup()
                                        .addGap(45, 45, 45)
                                        .addComponent(jLabel21))
                                    .addComponent(jLabel22)
                                    .addGroup(tarifLayout.createSequentialGroup()
                                        .addGap(46, 46, 46)
                                        .addGroup(tarifLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel23)
                                            .addComponent(jLabel24))))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tarifLayout.createSequentialGroup()
                                .addGroup(tarifLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(tarifLayout.createSequentialGroup()
                                        .addComponent(jButton3)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jButton11))
                                    .addComponent(txtTar, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, tarifLayout.createSequentialGroup()
                                        .addGroup(tarifLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jJabat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel9)
                                            .addComponent(jLabel11)
                                            .addComponent(jLabel10)
                                            .addComponent(jZon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel17)
                                            .addComponent(jLabel18)
                                            .addComponent(jLabel20))
                                        .addGap(0, 0, Short.MAX_VALUE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        tarifLayout.setVerticalGroup(
            tarifLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tarifLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(tarifLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(tarifLayout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jJabat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jZon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(tarifLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton3)
                            .addComponent(jButton11))
                        .addGap(52, 52, 52)
                        .addComponent(jLabel17)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel18)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel19)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel20)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel21)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel22)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel23)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel24)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tarifLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );

        content.add(tarif, "card3");

        sppd.setBackground(new java.awt.Color(204, 255, 204));

        title1.setBackground(new java.awt.Color(153, 255, 255));

        jLabel12.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel12.setText("DATA SPPD");

        javax.swing.GroupLayout title1Layout = new javax.swing.GroupLayout(title1);
        title1.setLayout(title1Layout);
        title1Layout.setHorizontalGroup(
            title1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(title1Layout.createSequentialGroup()
                .addGap(254, 254, 254)
                .addComponent(jLabel12)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        title1Layout.setVerticalGroup(
            title1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(title1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel12)
                .addContainerGap(10, Short.MAX_VALUE))
        );

        jLabel13.setText("Kepada");

        jLabel14.setText("Perihal");

        btnSimpan1.setBackground(new java.awt.Color(153, 255, 153));
        btnSimpan1.setText("Simpan");
        btnSimpan1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSimpan1ActionPerformed(evt);
            }
        });

        jButton4.setBackground(new java.awt.Color(255, 153, 153));
        jButton4.setText("Hapus");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setBackground(new java.awt.Color(255, 255, 153));
        jButton5.setText("Ubah");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        tableSppd.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "NIP", "Nama", "Bagian", "Jabatan"
            }
        ));
        tableSppd.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableSppdMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tableSppd);

        btnBersihkan.setBackground(new java.awt.Color(153, 255, 153));
        btnBersihkan.setText("Bersihkan");
        btnBersihkan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBersihkanActionPerformed(evt);
            }
        });

        jKepada.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jKepadaActionPerformed(evt);
            }
        });

        taPerihal.setColumns(20);
        taPerihal.setRows(5);
        jScrollPane4.setViewportView(taPerihal);

        jLabel25.setText("Tanggal Pergi");

        jLabel26.setText("Tanggal Kembali");

        jLabel27.setText("Kota Tujuan");

        jLabel28.setText("Zonasi");

        cbZona.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Zona 1", "Zona 2", "Zona 3" }));

        jLabel30.setText("Transportasi");

        cbTrans.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Darat", "Udara" }));

        jButton7.setText("PDF");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout sppdLayout = new javax.swing.GroupLayout(sppd);
        sppd.setLayout(sppdLayout);
        sppdLayout.setHorizontalGroup(
            sppdLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(sppdLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(sppdLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(title1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(sppdLayout.createSequentialGroup()
                        .addGroup(sppdLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel13)
                            .addComponent(jKepada, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(34, 34, 34)
                        .addGroup(sppdLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(sppdLayout.createSequentialGroup()
                                .addGroup(sppdLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel26)
                                    .addComponent(jLabel25)
                                    .addComponent(jLabel27)
                                    .addGroup(sppdLayout.createSequentialGroup()
                                        .addGap(6, 6, 6)
                                        .addGroup(sppdLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(tfKota, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(sppdLayout.createSequentialGroup()
                                                .addComponent(btnSimpan1)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(jButton5))
                                            .addComponent(dcPergi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(dcKembali, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGap(24, 24, 24)
                                .addGroup(sppdLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel28)
                                    .addComponent(jLabel30)
                                    .addGroup(sppdLayout.createSequentialGroup()
                                        .addGap(6, 6, 6)
                                        .addGroup(sppdLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(cbZona, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(cbTrans, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                            .addGroup(sppdLayout.createSequentialGroup()
                                .addGap(306, 306, 306)
                                .addComponent(btnBersihkan)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton4)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, sppdLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton7)))
                .addContainerGap())
        );
        sppdLayout.setVerticalGroup(
            sppdLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(sppdLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(title1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(sppdLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(jLabel25)
                    .addComponent(jLabel28))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(sppdLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(sppdLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jKepada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cbZona, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(dcPergi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(sppdLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(jLabel26)
                    .addComponent(jLabel30))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(sppdLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(sppdLayout.createSequentialGroup()
                        .addGroup(sppdLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(cbTrans, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(dcKembali, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel27)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tfKota, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(sppdLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnSimpan1)
                            .addComponent(jButton5)
                            .addComponent(btnBersihkan)
                            .addComponent(jButton4)))
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jButton7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(16, Short.MAX_VALUE))
        );

        content.add(sppd, "card2");

        pegawai.setBackground(new java.awt.Color(204, 255, 204));

        title.setBackground(new java.awt.Color(153, 255, 255));

        jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel3.setText("DATA PEGAWAI");

        javax.swing.GroupLayout titleLayout = new javax.swing.GroupLayout(title);
        title.setLayout(titleLayout);
        titleLayout.setHorizontalGroup(
            titleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(titleLayout.createSequentialGroup()
                .addGap(242, 242, 242)
                .addComponent(jLabel3)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        titleLayout.setVerticalGroup(
            titleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(titleLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel4.setText("NIP");

        jLabel5.setText("Nama");

        jLabel6.setText("Bagian");

        jLabel7.setText("Jabatan");

        jJabatan.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Rektor / Wakil Rektor", "Kepala Biro", "Manager", "Deputi Manager", "Sekretaris Bagian", "Analis Bagian", "Staff", "Supir" }));
        jJabatan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jJabatanActionPerformed(evt);
            }
        });

        btnSimpan.setBackground(new java.awt.Color(153, 255, 153));
        btnSimpan.setText("Simpan");
        btnSimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSimpanActionPerformed(evt);
            }
        });

        jButton1.setBackground(new java.awt.Color(255, 153, 153));
        jButton1.setText("Hapus");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        buttonUbah.setBackground(new java.awt.Color(255, 255, 153));
        buttonUbah.setText("Ubah");
        buttonUbah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonUbahActionPerformed(evt);
            }
        });

        tablePegawai.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "NIP", "Nama", "Bagian", "Jabatan"
            }
        ));
        tablePegawai.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablePegawaiMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tablePegawai);

        btnReset.setBackground(new java.awt.Color(153, 255, 153));
        btnReset.setText("Reset");
        btnReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetActionPerformed(evt);
            }
        });

        txtCari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCariActionPerformed(evt);
            }
        });

        btnCari.setBackground(new java.awt.Color(204, 255, 255));
        btnCari.setText("cari");
        btnCari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCariActionPerformed(evt);
            }
        });

        jLabel32.setText("Nama");

        jButton10.setText("PDF");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pegawaiLayout = new javax.swing.GroupLayout(pegawai);
        pegawai.setLayout(pegawaiLayout);
        pegawaiLayout.setHorizontalGroup(
            pegawaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pegawaiLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pegawaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pegawaiLayout.createSequentialGroup()
                        .addComponent(title, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(pegawaiLayout.createSequentialGroup()
                        .addGroup(pegawaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pegawaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtNip)
                                .addComponent(txtNama)
                                .addComponent(txtBagian)
                                .addComponent(jLabel7)
                                .addComponent(jJabatan, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(pegawaiLayout.createSequentialGroup()
                                    .addComponent(jButton1)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 24, Short.MAX_VALUE)
                                    .addComponent(buttonUbah))
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pegawaiLayout.createSequentialGroup()
                                .addGap(54, 54, 54)
                                .addGroup(pegawaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnSimpan)
                                    .addComponent(btnReset))))
                        .addGap(18, 18, 18)
                        .addGroup(pegawaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(pegawaiLayout.createSequentialGroup()
                                .addGap(100, 100, 100)
                                .addGroup(pegawaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(pegawaiLayout.createSequentialGroup()
                                        .addComponent(txtCari, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(btnCari, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jLabel32))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton10)))
                        .addGap(0, 107, Short.MAX_VALUE))))
        );
        pegawaiLayout.setVerticalGroup(
            pegawaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pegawaiLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(title, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(pegawaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pegawaiLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(pegawaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(jLabel32))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtNip, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtNama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtBagian, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jJabatan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(pegawaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton1)
                            .addComponent(buttonUbah))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSimpan)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnReset)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(pegawaiLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                        .addGroup(pegawaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnCari)
                            .addComponent(jButton10)
                            .addComponent(txtCari, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );

        content.add(pegawai, "card2");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(navbar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(sidebar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(content, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(navbar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(sidebar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(content, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        getContentPane().add(jPanel1, "card2");

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnPegawaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPegawaiActionPerformed
        //remove panel
        content.removeAll();
        content.repaint();
        content.revalidate();
        //add panel
        content.add(pegawai);
        content.repaint();
        content.revalidate();
    }//GEN-LAST:event_btnPegawaiActionPerformed

    private void btnTarifActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTarifActionPerformed
        //remove panel
        content.removeAll();
        content.repaint();
        content.revalidate();
        //add panel
        content.add(tarif);
        content.repaint();
        content.revalidate();
    }//GEN-LAST:event_btnTarifActionPerformed

    private void btnSppdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSppdActionPerformed
        //remove panel
        content.removeAll();
        content.repaint();
        content.revalidate();
        //add panel
        content.add(sppd);
        content.repaint();
        content.revalidate();
    }//GEN-LAST:event_btnSppdActionPerformed

    private void btnRekapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRekapActionPerformed
        new Rekap().setVisible(true);
        dispose();
    }//GEN-LAST:event_btnRekapActionPerformed

    private void btnLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLogoutActionPerformed
        // TODO add your handling code here:
        new login().setVisible(true);
        dispose();
    }//GEN-LAST:event_btnLogoutActionPerformed

    private void jJabatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jJabatActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jJabatActionPerformed

    private void btnResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetActionPerformed
        kosong();
        datatable();
    }//GEN-LAST:event_btnResetActionPerformed

    private void btnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSimpanActionPerformed
        sql="INSERT INTO pegawai values(?,?,?,?)";
        try{
            int pil=0;
            
            prep=con.prepareStatement(sql);
            prep.setString(1, txtNip.getText());
            prep.setString(2, txtNama.getText());
            prep.setString(3, txtBagian.getText());
            String jab="";
            pil = jJabatan.getSelectedIndex();
            if(pil==0) {jab="Rektor / Wakil Rektor";};
            if(pil==1) {jab="Kepala Biro";};
            if(pil==2) {jab="Manager";};
            if(pil==3) {jab="Deputi Manager";};
            if(pil==4) {jab="Sekretaris Bagian";};
            if(pil==5) {jab="Analis Bagian";};
            if(pil==6) {jab="Staff";};
            if(pil==7) {jab="Supir";};
            prep.setString(4, jab);
            
            prep.executeUpdate();
            JOptionPane.showMessageDialog(null, "Data Berhasil Disimpan");
            kosong();
            txtNip.requestFocus();
            datatable();
        } catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Data Gagal Disimpan" +e);
        }
        
    }//GEN-LAST:event_btnSimpanActionPerformed

    private void jJabatanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jJabatanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jJabatanActionPerformed

    private void buttonUbahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonUbahActionPerformed
        try{
            int pil = 0;
            sql="UPDATE pegawai SET nama=?, bagian=?, jabatan=? Where nip=?";
            prep=con.prepareStatement(sql);
            //prep.setString(1, txtNip.getText());
            prep.setString(1, txtNama.getText());
            prep.setString(2, txtBagian.getText());
            String jab="";
            pil = jJabatan.getSelectedIndex();
            if(pil==0) {jab="Rektor / Wakil Rektor";};
            if(pil==1) {jab="Kepala Biro";};
            if(pil==2) {jab="Manager";};
            if(pil==3) {jab="Deputi Manager";};
            if(pil==4) {jab="Sekretaris Bagian";};
            if(pil==5) {jab="Analis Bagian";};
            if(pil==6) {jab="Staff";};
            if(pil==7) {jab="Supir";};
            prep.setString(3, jab);
            prep.setString(4, txtNip.getText());
            
            prep.executeUpdate();
            JOptionPane.showMessageDialog(null, "Data Berhasil Diubah");
            kosong();
            txtNip.requestFocus();
            datatable();
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Data Gagal Diubah" +e);
        }
    }//GEN-LAST:event_buttonUbahActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        int hapus=JOptionPane.showConfirmDialog(null, "Hapus", "Konfirmasi Dialog", JOptionPane.YES_NO_CANCEL_OPTION);
        if(hapus==0){
            sql="DELETE FROM pegawai where nip='"+txtNip.getText()+"'";
            try{
                prep=con.prepareStatement(sql);
                prep.executeUpdate();
                JOptionPane.showMessageDialog(null,"Data Berhasil Dihapus");
                kosong();
                txtNip.requestFocus();
                datatable();
            }catch(SQLException e){
                JOptionPane.showMessageDialog(null, "Data Gagal Dihapus" +e);
            }
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btnCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCariActionPerformed
        Object [] baris ={"NIP", "NAMA", "BAGIAN", "JABATAN"};
        tabmode=new DefaultTableModel(null, baris);
        tablePegawai.setModel(tabmode);
        
        sql ="SELECT * FROM pegawai WHERE nama like '%"+txtCari.getText()+"%'";
        try{
            stat=con.createStatement();
            rs=stat.executeQuery(sql);
            while(rs.next()){
                String a =rs.getString("nip");
                String b =rs.getString("nama");
                String c =rs.getString("bagian");
                String d =rs.getString("jabatan");
                
                String [] data = {a,b,c,d};
                tabmode.addRow(data);
                kosong();
            }
        }catch(Exception e){
            
        }
    }//GEN-LAST:event_btnCariActionPerformed

    @SuppressWarnings("empty-statement")
    private void tablePegawaiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablePegawaiMouseClicked
        int bar = tablePegawai.getSelectedRow();
        String a = tablePegawai.getValueAt(bar, 0).toString();
        String b = tablePegawai.getValueAt(bar, 1).toString();
        String c = tablePegawai.getValueAt(bar, 2).toString();
        String d = tablePegawai.getValueAt(bar, 3).toString();
        
        txtNip.setText(a);
        txtNama.setText(b);
        txtBagian.setText(c);
        int jab=0;
        if(d.equals("Rektor / Wakil Rektor")) {jab=0;}
        if(d.equals("Kepala Biro")) {jab=1;};
        if(d.equals("Manager")) {jab=2;};
        if(d.equals("Deputi Manager")) {jab=3;};
        if(d.equals("Sekretaris Bagian")) {jab=4;};
        if(d.equals("Analis Bagian")) {jab=5;};
        if(d.equals("Staff")) {jab=6;};
        if(d.equals("Supir")) {jab=7;};
        //prep.setString(4, jab);
        jJabatan.setSelectedIndex(jab);
    }//GEN-LAST:event_tablePegawaiMouseClicked

    private void tableTarifMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableTarifMouseClicked
        int bar = tableTarif.getSelectedRow();
        String a = tableTarif.getValueAt(bar, 0).toString();
        String b = tableTarif.getValueAt(bar,1).toString();
        String c = tableTarif.getValueAt(bar,2).toString();
        
        int jab = 0;
        if(a.equals("Rektor / Wakil Rektor")) {jab=0;};
        if(a.equals("Kepala Biro")) {jab=1;};
        if(a.equals("Manager")) {jab=2;};
        if(a.equals("Deputi Manager")) {jab=3;};
        if(a.equals("Sekretaris Bagian")) {jab=4;};
        if(a.equals("Analis Bagian")) {jab=5;};
        if(a.equals("Staff")) {jab=6;};
        if(a.equals("Supir")) {jab=7;};
        jJabat.setSelectedIndex(jab);
        
        int zon = 0;
        if(b.equals("1")) {zon=0;};
        if(b.equals("2")) {zon=1;};
        if(b.equals("3")) {zon=2;};
        jZon.setSelectedIndex(zon);
        
        txtTar.setText(c);
        
    }//GEN-LAST:event_tableTarifMouseClicked

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        try{
            int jabat = 0;
            int zon = 0;
            sql="UPDATE tarif SET tarif=? Where jabatan=? AND zonasi=?";
            prep=con.prepareStatement(sql);
            //prep.setString(1, txtNip.getText());
            //prep.setString(1, txtNama.getText());
            //prep.setString(2, txtBagian.getText());
            String jab="";
            jabat = jJabat.getSelectedIndex();
            if(jabat==0) {jab="Rektor / Wakil Rektor";};
            if(jabat==1) {jab="Kepala Biro";};
            if(jabat==2) {jab="Manager";};
            if(jabat==3) {jab="Deputi Manager";};
            if(jabat==4) {jab="Sekretaris Bagian";};
            if(jabat==5) {jab="Analis Bagian";};
            if(jabat==6) {jab="Staff";};
            if(jabat==7) {jab="Supir";};
            prep.setString(2, jab);
            
            String zonasi="";
            zon = jZon.getSelectedIndex();
            if(zon==0) {zonasi="1";};
            if(zon==1) {zonasi="2";};
            if(zon==2) {zonasi="3";};
            prep.setString(3, zonasi);
            
            prep.setString(1, txtTar.getText());
            
            prep.executeUpdate();
            JOptionPane.showMessageDialog(null, "Data Berhasil Diubah");
            tarkosong();
            //jJabat.setEnabled(false);
            //jZon.setEnabled(false);
            jJabat.requestFocus();
            jZon.requestFocus();
            tabletarif();
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Data Gagal Diubah" +e);
        }
    }//GEN-LAST:event_jButton3ActionPerformed

//    public void kepada(){
//        try{
//            sql = "SELECT jabatan FROM pegawai where nama='"+jKepada.getSelectedItem()+"'";
//            stat=con.createStatement();
//            rs=stat.executeQuery(sql);
//            
//            while(rs.next()){
//                Object[] ob = new Object[3];
//                ob[0] = rs.getString(1);
//                //ob[1] = rs.getString(2);
//                
//                jTextField4.setText((String) ob[0]);
//            }
//            
//            rs.close(); stat.close();
//        } catch(Exception e) {
//            System.out.println(e.getMessage());
//        }
//    }
    
    private void jKepadaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jKepadaActionPerformed
        //kepada();
    }//GEN-LAST:event_jKepadaActionPerformed

    private void btnSimpan1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSimpan1ActionPerformed
//        sql = "INSERT INTO sppd values(?,?,?,?,?,?,?,?,?,?)";
////        String jabat = "SELECT jabatan FROM pegawai WHERE nama='"+jKepada.getSelectedItem()+"'";
////        String tarr = "SELECT tarif FROM tarif WHERE jabatan='"+jabatt+"' AND zonasi='"+jComboBox3.getSelectedIndex()+"'";
////        String traa = "SELECT transport FROM transport WHERE jabatan='"+jabatt+"' AND jenis='"+jComboBox2.getSelectedItem()+"'";
//        
//        try{
//            int zon = 0;
//            int trans = 0;
//            
//            LocalDate awal = LocalDate.parse(tanggal1);
//            Date berangkat = Date.valueOf(awal);
//            LocalDate akhir = LocalDate.parse(tanggal2);
//            Date pulang = Date.valueOf(akhir);
//            
//            Period bedahari = Period.between(awal, akhir);
//            int beda = bedahari.getYears()*365 + bedahari.getMonths()*30 + bedahari.getDays();
//            
//            String tr ="";
//            trans = jComboBox3.getSelectedIndex();
//            if(trans == 0) {tr = "Darat";};
//            if(trans == 1) {tr = "Udara";};
//            
//            prep = con.prepareStatement(sql);
//            
////            stat=con.createStatement();
////            rs=stat.executeQuery("SELECT * FROM pegawai WHERE nama='"+jKepada.getSelectedItem()+"'");
////            String jabat = rs.getString("jabatan");
////            rs=stat.executeQuery("SELECT tarif FROM tarif WHERE jabatan='"+jabat+"' AND zonasi='"+jComboBox3.getSelectedIndex()+"'");
////            String tarr = rs.getString("tarif");
////            rs=stat.executeQuery("SELECT transport FROM transport WHERE jabatan='"+jabat+"' AND jenis='"+jComboBox2.getSelectedItem()+"'");
////            String traa = rs.getString("transport");
////            int tar = Integer.parseInt(tarr);
////            int tra = Integer.parseInt(traa);
////            int totTar = tar*beda;
////            int totTra = tra*beda;
////            int tot = totTar+totTra;
//            
//            prep.setString(1,"");
//            prep.setString(2, "0001/SPPD/1/C0/05/2023");
//            prep.setString(3, (String) jKepada.getSelectedItem());
//            prep.setString(4, jTextArea1.getText());
//            prep.setDate(5, berangkat);
//            prep.setDate(6, pulang);
//            prep.setInt(7, beda);
//            prep.setString(8, jTextField3.getText());
//            prep.setString(9,tr);
//            prep.setString(10, (String) jComboBox2.getSelectedItem());
////            prep.setInt(10, tar);
////            prep.setInt(11, totTar);
////            prep.setInt(12, tra);
////            prep.setInt(13, totTar);
////            prep.setInt(14, tot);
////            prep.setString(15, "");
//            
//            prep.executeUpdate();
//            JOptionPane.showMessageDialog(null, "Data Berhasil Disimpan");
//            sppdkosong();
//            jKepada.requestFocus();
//            tablesppd();
//        } catch(SQLException e){
//            JOptionPane.showMessageDialog(null, "Data Gagal Disimpan" +e);
//        }
        int id = getNewSppdID();
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/yyyy");
        String formattedDate = currentDate.format(formatter);
        String sppdID = String.format("%04d/SPPD/1/C0/%s", id, formattedDate);
        java.sql.Date sqlPergi = new java.sql.Date(dcPergi.getDate().getTime());
        java.sql.Date sqlKembali = new java.sql.Date(dcKembali.getDate().getTime());
        long jumlahHari = ChronoUnit.DAYS.between(dcPergi.getDate().toInstant(), dcKembali.getDate().toInstant());

        try{
            PreparedStatement psPegawai = con.prepareStatement("SELECT * FROM pegawai WHERE nama = ?");
            psPegawai.setString(1, jKepada.getSelectedItem().toString());
            ResultSet rsPegawai = psPegawai.executeQuery();
            rsPegawai.next();
            
            PreparedStatement psTarif = con.prepareStatement("SELECT * FROM tarif WHERE jabatan = ? AND zonasi = ?");
            psTarif.setString(1, rsPegawai.getString("jabatan"));
            psTarif.setString(2, cbZona.getSelectedItem().toString().split(" ")[1]);
            ResultSet rsTarif = psTarif.executeQuery();
            rsTarif.next();
            
            PreparedStatement psTrans = con.prepareStatement("SELECT transport FROM transport WHERE jabatan = ? AND jenis = ?");
            psTrans.setString(1, rsPegawai.getString("jabatan"));
            psTrans.setString(2, cbTrans.getSelectedItem().toString());
            ResultSet rsTrans = psTrans.executeQuery();
            rsTrans.next();
            
            ResultSet rs = psPegawai.executeQuery();
            PreparedStatement ps = con.prepareStatement("INSERT INTO sppd VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? ,? ,?, ?)");
            ps.setInt(1, id);
            ps.setString(2 , sppdID);
            ps.setString(3, jKepada.getSelectedItem().toString());
            ps.setString(4, taPerihal.getText());
            ps.setDate(5, sqlPergi);
            ps.setDate(6, sqlKembali);
            ps.setLong(7, jumlahHari);
            ps.setString(8, tfKota.getText());
            ps.setString(9, cbTrans.getSelectedItem().toString());
            ps.setString(10, cbZona.getSelectedItem().toString().split(" ")[1]);
            ps.setInt(11, rsTarif.getInt("tarif"));
            ps.setInt(12, rsTarif.getInt("tarif") * (int)jumlahHari);
            ps.setInt(13, rsTrans.getInt("transport"));
            ps.setInt(14, rsTrans.getInt("transport") * 2);
            ps.setInt(15, (rsTarif.getInt("tarif") * (int)jumlahHari) + (rsTrans.getInt("transport") * 2));
            ps.setString(16, "");
            ps.execute();
            tablesppd();
        }catch(SQLException e) {
            e.printStackTrace();
        }
        clearSPPD();
    }//GEN-LAST:event_btnSimpan1ActionPerformed

    private void dcPergiPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_dcPergiPropertyChange
        if (dcPergi.getDate()!=null){
            SimpleDateFormat Format=new SimpleDateFormat("yyyy-MM-dd");
            tanggal1=Format.format(dcPergi.getDate());
        }
    }//GEN-LAST:event_dcPergiPropertyChange

    private void dcKembaliPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_dcKembaliPropertyChange
//       if (jDateChooser2.getDate()!=null){
//           SimpleDateFormat Format = new SimpleDateFormat("yyyy-MM-dd");
//           tanggal2=Format.format(jDateChooser2.getDate());
//       }
    }//GEN-LAST:event_dcKembaliPropertyChange

    private void tableSppdMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableSppdMouseClicked
        JTable source = (JTable)evt.getSource();
        int row = source.rowAtPoint( evt.getPoint() );
        sppdID = source.getModel().getValueAt(row, 0)+"";
        try{
            PreparedStatement ps = con.prepareStatement("SELECT * FROM sppd WHERE id = ?");
            ps.setString(1, sppdID);
            ResultSet rs = ps.executeQuery();
            rs.next();
            
            jKepada.setSelectedItem(source.getModel().getValueAt(row, 1)+"");
            taPerihal.setText(source.getModel().getValueAt(row, 2)+"");
            dcPergi.setDate(rs.getDate("tanggal_pergi"));
            dcKembali.setDate(rs.getDate("tanggal_kembali"));
            tfKota.setText(rs.getString("kota_tujuan"));
            cbZona.setSelectedItem("Zona " + rs.getString("zonasi"));
            cbTrans.setSelectedItem("transportasi");
        }catch(SQLException e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_tableSppdMouseClicked

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        System.out.println(sppdID);
        try{
            PreparedStatement ps = con.prepareStatement("DELETE FROM sppd WHERE no_sppd = ?");
            ps.setString(1, sppdID);
            ps.execute();
            tablesppd();
        }catch(SQLException e) {
            e.printStackTrace();
        }
        clearSPPD();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/yyyy");
        String formattedDate = currentDate.format(formatter);
        java.sql.Date sqlPergi = new java.sql.Date(dcPergi.getDate().getTime());
        java.sql.Date sqlKembali = new java.sql.Date(dcKembali.getDate().getTime());
        long jumlahHari = ChronoUnit.DAYS.between(dcPergi.getDate().toInstant(), dcKembali.getDate().toInstant());

        try{
            PreparedStatement psPegawai = con.prepareStatement("SELECT * FROM pegawai WHERE nama = ?");
            psPegawai.setString(1, jKepada.getSelectedItem().toString());
            ResultSet rsPegawai = psPegawai.executeQuery();
            rsPegawai.next();
            
            PreparedStatement psTarif = con.prepareStatement("SELECT * FROM tarif WHERE jabatan = ? AND zonasi = ?");
            psTarif.setString(1, rsPegawai.getString("jabatan"));
            psTarif.setString(2, cbZona.getSelectedItem().toString().split(" ")[1]);
            ResultSet rsTarif = psTarif.executeQuery();
            rsTarif.next();
            
            PreparedStatement psTrans = con.prepareStatement("SELECT transport FROM transport WHERE jabatan = ? AND jenis = ?");
            psTrans.setString(1, rsPegawai.getString("jabatan"));
            psTrans.setString(2, cbTrans.getSelectedItem().toString());
            ResultSet rsTrans = psTrans.executeQuery();
            rsTrans.next();
            
            ResultSet rs = psPegawai.executeQuery();
            PreparedStatement ps = con.prepareStatement("UPDATE sppd SET kepada = ?, perihal = ?, tanggal_pergi = ?, tanggal_kembali = ?, jumlah_hari = ?, kota_tujuan = ?, transportasi = ?, zonasi = ?, lumpsum = ?, total_lumpsum = ?, transport = ?, total_transport = ?, total = ? WHERE no_sppd = ?");
            ps.setString(1, jKepada.getSelectedItem().toString());
            ps.setString(2, taPerihal.getText());
            ps.setDate(3, sqlPergi);
            ps.setDate(4, sqlKembali);
            ps.setLong(5, jumlahHari);
            ps.setString(6, tfKota.getText());
            ps.setString(7, cbTrans.getSelectedItem().toString());
            ps.setString(8, cbZona.getSelectedItem().toString().split(" ")[1]);
            ps.setInt(9, rsTarif.getInt("tarif"));
            ps.setInt(10, rsTarif.getInt("tarif") * (int)jumlahHari);
            ps.setInt(11, rsTrans.getInt("transport"));
            ps.setInt(12, rsTrans.getInt("transport") * 2);
            ps.setInt(13, (rsTarif.getInt("tarif") * (int)jumlahHari) + (rsTrans.getInt("transport") * 2));
            ps.setString(14, sppdID);
            ps.execute();
            tablesppd();
        }catch(SQLException e) {
            e.printStackTrace();
        }
        clearSPPD();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        if(sppdID == null) {
            JOptionPane.showMessageDialog(null, "Pilih Data SPPD!");
            return;
        }
        
        try {
        // Membuat objek Map untuk menyimpan parameter
        Map<String, Object> params = new HashMap<>();

        // Mendapatkan nilai dari JDateChooser dan menyimpannya ke dalam parameter
        params.put("sppdID", sppdID);

        // Mengisi laporan dengan data dan parameter
        JasperPrint jp = JasperFillManager.fillReport(getClass().getResourceAsStream("Report/DetailSPPD.jasper"), params, con);

        // Menampilkan laporan di viewer
        JasperViewer.viewReport(jp, false);
    } catch(Exception e) {
        JOptionPane.showMessageDialog(rootPane, e);
        e.printStackTrace();
    }
    }//GEN-LAST:event_jButton7ActionPerformed

    private void bntUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntUserActionPerformed
        //remove panel
        content.removeAll();
        content.repaint();
        content.revalidate();
        //add panel
        content.add(akun);
        content.repaint();
        content.revalidate();
    }//GEN-LAST:event_bntUserActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        Object [] baris ={"Username", "Nama Lengkap"};
        tabmode=new DefaultTableModel(null, baris);
        tableUser.setModel(tabmode);
        
        sql ="SELECT * FROM user WHERE username like '%"+jTextField3.getText()+"%'";
        try{
            stat=con.createStatement();
            rs=stat.executeQuery(sql);
            while(rs.next()){
                String a =rs.getString("username");
                String b =rs.getString("nama_lengkap");
                //String c =rs.getString("bagian");
                //String d =rs.getString("jabatan");
                
                String [] data = {a,b};
                tabmode.addRow(data);
                //kosong();
            }
        }catch(SQLException e){
            
        }
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        sql="INSERT INTO user (username, password, role, nama_lengkap)values (?,?,?,?)";
        try{
            prep=con.prepareStatement(sql);
            
            prep.setString(1, jTextField1.getText());
            prep.setString(2, String.valueOf(jPasswordField1.getPassword()));
            prep.setInt(3,0);
            prep.setString(4, jTextField2.getText());
            
            prep.executeUpdate();
            JOptionPane.showMessageDialog(null, "Data Berhasil Disimpan");
            //kosong();
            jTextField1.requestFocus();
            tableuser();
        } catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Data Gagal Disimpan" +e);
        }
    }//GEN-LAST:event_jButton8ActionPerformed

    private void tableUserMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableUserMouseClicked
        int bar=tableUser.getSelectedRow();
        String a = tabmode.getValueAt(bar, 0).toString();
        String b = tabmode.getValueAt(bar,1).toString();
         
        jTextField1.setText(a);
        jTextField2.setText(b);
    }//GEN-LAST:event_tableUserMouseClicked

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        int hapus=JOptionPane.showConfirmDialog(null, "Hapus", "Konfirmasi Dialog", JOptionPane.YES_NO_CANCEL_OPTION);
        if(hapus==0){
            sql="DELETE FROM user where username='"+jTextField1.getText()+"'";
            try{
                prep=con.prepareStatement(sql);
                prep.executeUpdate();
                JOptionPane.showMessageDialog(null,"Data Berhasil Dihapus");
                kosong();
                jTextField1.requestFocus();
                tableuser();
            }catch(SQLException e){
                JOptionPane.showMessageDialog(null, "Data Gagal Dihapus" +e);
            }
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void btnBersihkanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBersihkanActionPerformed

            jKepada.getSelectedItem().toString();
            taPerihal.setText("");
            dcPergi.getDate();
            dcKembali.getDate();
            tfKota.setText("");
            cbZona.getSelectedItem();
            cbTrans.getSelectedItem();  
            tableuser();        
    }//GEN-LAST:event_btnBersihkanActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        try {
            File namafile = new File("src/Report/DetailUSER.jasper");
            JasperPrint jp = JasperFillManager.fillReport(namafile.getPath(), null, con);
            JasperViewer jv = new JasperViewer(jp);
            jv.setVisible(true);
            JOptionPane.showMessageDialog(rootPane, "Berhasil Dicetak");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, "Error: " + e.getMessage());
        }
    }//GEN-LAST:event_jButton6ActionPerformed

    private void txtCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCariActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCariActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        // TODO add your handling code here:
        try {
            // Membuat objek Map untuk menyimpan parameter
            Map<String, Object> params = new HashMap<>();

            // Mendapatkan nilai dari JDateChooser dan menyimpannya ke dalam parameter
            params.put("pegawai", pegawai);

            // Mengisi laporan dengan data dan parameter
            JasperPrint jp = JasperFillManager.fillReport(getClass().getResourceAsStream("Report/DetailPegawai.jasper"), params, con);

            // Menampilkan laporan di viewer
            JasperViewer.viewReport(jp, false);
        } catch(JRException e) {
            JOptionPane.showMessageDialog(rootPane, e);
        }
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        // TODO add your handling code here:
        try {
            // Membuat objek Map untuk menyimpan parameter
            Map<String, Object> params = new HashMap<>();

            // Mendapatkan nilai dari JDateChooser dan menyimpannya ke dalam parameter
            params.put("pegawai", pegawai);

            // Mengisi laporan dengan data dan parameter
            JasperPrint jp = JasperFillManager.fillReport(getClass().getResourceAsStream("Report/DetailTARIF.jasper"), params, con);

            // Menampilkan laporan di viewer
            JasperViewer.viewReport(jp, false);
        } catch(JRException e) {
            JOptionPane.showMessageDialog(rootPane, e);
        }
    }//GEN-LAST:event_jButton11ActionPerformed
    
    public void comboKepada(){
        try{
            sql = "SELECT nama FROM pegawai order by nama ASC";
            stat=con.createStatement();
            rs=stat.executeQuery(sql);
            
            while(rs.next()){
                Object[] ob = new Object[3];
                ob[0] = rs.getString(1);
                
                jKepada.addItem((String) ob[0]);
            }
            rs.close(); stat.close();
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    private int getNewSppdID() {
        try{
            PreparedStatement ps = con.prepareStatement("SELECT id FROM sppd ORDER BY id DESC LIMIT 1");
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                return rs.getInt("id") + 1;
            }
        }catch(SQLException e) {
            e.printStackTrace();
        }
        return 1;
    }
    
    private void clearSPPD() {
        jKepada.setSelectedIndex(0);
        taPerihal.setText("");
        dcPergi.setDate(null);
        dcKembali.setDate(null);
        tfKota.setText("");
        cbZona.setSelectedIndex(0);
        cbTrans.setSelectedIndex(0);
    }
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(pegawai.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(pegawai.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(pegawai.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(pegawai.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new pegawai().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel akun;
    private javax.swing.JButton bntUser;
    private javax.swing.JButton btnBersihkan;
    private javax.swing.JButton btnCari;
    private javax.swing.JButton btnLogout;
    private javax.swing.JButton btnPegawai;
    private javax.swing.JButton btnRekap;
    private javax.swing.JButton btnReset;
    private javax.swing.JButton btnSimpan;
    private javax.swing.JButton btnSimpan1;
    private javax.swing.JButton btnSppd;
    private javax.swing.JButton btnTarif;
    private javax.swing.JButton buttonUbah;
    private javax.swing.JComboBox<String> cbTrans;
    private javax.swing.JComboBox<String> cbZona;
    private javax.swing.JPanel content;
    private com.toedter.calendar.JDateChooser dcKembali;
    private com.toedter.calendar.JDateChooser dcPergi;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JComboBox<String> jJabat;
    private javax.swing.JComboBox<String> jJabatan;
    private javax.swing.JComboBox<String> jKepada;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPasswordField jPasswordField1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JComboBox<String> jZon;
    private javax.swing.JPanel navbar;
    private javax.swing.JPanel pegawai;
    private javax.swing.JPanel sidebar;
    private javax.swing.JPanel sppd;
    private javax.swing.JTextArea taPerihal;
    private javax.swing.JTable tablePegawai;
    private javax.swing.JTable tableSppd;
    private javax.swing.JTable tableTarif;
    private javax.swing.JTable tableUser;
    private javax.swing.JPanel tarif;
    private javax.swing.JTextField tfKota;
    private javax.swing.JPanel title;
    private javax.swing.JPanel title1;
    private javax.swing.JTextField txtBagian;
    private javax.swing.JTextField txtCari;
    private javax.swing.JTextField txtNama;
    private javax.swing.JTextField txtNip;
    private javax.swing.JTextField txtTar;
    // End of variables declaration//GEN-END:variables

    private Object daysBetween(Date awal, Date akhir) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
