/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package aplikasikaryawan;

import static aplikasikaryawan.EpochTime.convertDateEpoch;
import static aplikasikaryawan.EpochTime.getDateEpoch;
import static aplikasikaryawan.EpochTime.getOfDay;
import static aplikasikaryawan.EpochTime.getWorkOfDay;
import com.formdev.flatlaf.FlatDarkLaf;
import com.toedter.calendar.JDateChooser;
import java.awt.Dimension;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author User
 */
public class Karyawan extends javax.swing.JFrame {
    
    // VARIABEL GLOBAL
    Connection con = null; PreparedStatement pst = null; ResultSet res = null;
    /**
     * Creates new form Admin
     */
    public Karyawan() {
        initComponents();
        profile_user.setText(myProfile.getUser());
        absen_date.setDate(new Date());
        profile_date.setDate(new Date());
        getProfile();
        time();
    }
    
    public void time(){
        new Thread(){
            @Override
            public void run() {
                while (true) {
                    String tanggal = new SimpleDateFormat("EEEE, dd MMMM Y").format(new java.util.Date (EpochTime.currentTime()));
                    String jam = new SimpleDateFormat("KK:mm:ss a").format(new java.util.Date (EpochTime.currentTime()));
                    text_tanggal.setText(String.valueOf(tanggal));
                    text_jam.setText(String.valueOf(jam));
                }
            }
            
        }.start();
    }

    public void getProfile(){
        try {
                String SQLQuery = "CALL GetProfile(?,?,?)";
                con = (Connection) Connect.configDB();
                pst = con.prepareStatement(SQLQuery);
                pst.setString(1, profile_user.getText());
                pst.setLong(2, EpochTime.getMonthEpoch(profile_date.getDate(), 0));
                pst.setLong(3, EpochTime.getMonthEpoch(profile_date.getDate(), 1));
                res = pst.executeQuery();
                
                while (res.next()) {                    
                    profile_user.setText(res.getString(1));
                    profile_pass.setText(res.getString(2));
                    profile_jk.setSelectedItem(res.getString(3).toUpperCase());
                    profile_ktp.setText(res.getString(4));
                    profile_divisi.setText(res.getString(5));
                    profile_jabatan.setText(res.getString(6));
                    profile_phone.setText(res.getString(7));
                    profile_pola.setText(res.getString(8));
                    profile_bonus.setText(res.getString(9));
                    profile_denda.setText(res.getString(10));
                    profile_gajipokok.setText(res.getString(11));
                    profile_gaji.setText(res.getString(12));
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e.getMessage());
                new Login().setVisible(true);
            } finally {
                // Close the result set
                if (res != null) {try {res.close();} catch (SQLException e) {}}
                // Close the prepared statement
                if (pst != null) {try {pst.close();} catch (SQLException e) {}}
                // Close the connection
                if (con != null) {try {con.close();} catch (SQLException e) {}}
            }
    }
    
    public void insertAbsen(String status){
        try {
            String SQLQuery = "INSERT INTO `tb_absen` (`karyawan`, `status`, `catatan`, `absen_in`, `absen_out`, `date`, `lembur`, `terlambat`, `confirm`, `denda`) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            con = (Connection) Connect.configDB();
            pst = con.prepareStatement(SQLQuery);
            pst.setString(1, profile_user.getText().toLowerCase());
            pst.setString(2, status);
            pst.setString(3, absen_note.getText().toLowerCase());
            pst.setLong(4, EpochTime.currentTime());
            pst.setLong(5, EpochTime.getWorkOfDay("END"));
            pst.setLong(6, EpochTime.getDateEpoch(EpochTime.convertDateEpoch(absen_date)));
            pst.setInt(7, absen_lembur.getSelectedIndex());
            pst.setInt(8, absen_late.getSelectedIndex());
            pst.setInt(9, absen_confirm.getSelectedIndex());
            pst.setInt(10, Integer.parseInt(absen_denda.getText()));
            pst.execute();
            JOptionPane.showMessageDialog(null, "Anda sudah berhasil absen, namun tunggu persetujuan admin.");
        } catch (SQLIntegrityConstraintViolationException e) {
            JOptionPane.showMessageDialog(null, "Data absen hanya bisa 1 data perhari");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        } catch (ParseException ex) {
            Logger.getLogger(Karyawan.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            // Close the result set
            if (res != null) {try {res.close();} catch (SQLException e) {}}
            // Close the prepared statement
            if (pst != null) {try {pst.close();} catch (SQLException e) {}}
            // Close the connection
            if (con != null) {try {con.close();} catch (SQLException e) {}}
        }
    }
    
    public void getAbsen(String user, JDateChooser Chooserdate) throws ParseException{
        String newDate = convertDateEpoch(Chooserdate);
        String status = "", denda = "", note = "";
        Integer confirm = 0, lembur = 0, late = 0;
        
        try {
            String SQLQuery = "CALL GetAbsenUser(?,?);";
            con = (Connection) Connect.configDB();
            pst = con.prepareStatement(SQLQuery);
            pst.setString(1, profile_user.getText().toLowerCase());
            pst.setLong(2, getDateEpoch(newDate));
            res = pst.executeQuery();
            
            while(res.next()) { 
                status = res.getString(1).toUpperCase();
                confirm = res.getInt(2);
                lembur = res.getInt(3);
                late = res.getInt(4);
                denda = res.getString(5);
                note = res.getString(6).toLowerCase();
            }
            
            absen_status.setSelectedItem(status);
            absen_confirm.setSelectedIndex(confirm);
            absen_lembur.setSelectedIndex(lembur);
            absen_late.setSelectedIndex(late);
            absen_denda.setText(denda);
            absen_note.setText(note);
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        } finally {
            // Close the result set
            if (res != null) {try {res.close();} catch (SQLException e) {}}
            // Close the prepared statement
            if (pst != null) {try {pst.close();} catch (SQLException e) {}}
            // Close the connection
            if (con != null) {try {con.close();} catch (SQLException e) {}}
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
        jPanel3 = new javax.swing.JPanel();
        absence = new javax.swing.JPanel();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        absensi = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        absen_status = new javax.swing.JComboBox<>();
        absen_cari = new javax.swing.JButton();
        absen_date = new com.toedter.calendar.JDateChooser();
        jLabel87 = new javax.swing.JLabel();
        absen_confirm = new javax.swing.JComboBox<>();
        jLabel38 = new javax.swing.JLabel();
        absen_lembur = new javax.swing.JComboBox<>();
        jLabel37 = new javax.swing.JLabel();
        absen_late = new javax.swing.JComboBox<>();
        jLabel95 = new javax.swing.JLabel();
        absen_denda = new javax.swing.JTextField();
        jLabel96 = new javax.swing.JLabel();
        jScrollPane13 = new javax.swing.JScrollPane();
        absen_note = new javax.swing.JTextArea();
        jPanel4 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        profile_user = new javax.swing.JTextField();
        profile_pass = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        profile_ktp = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        profile_divisi = new javax.swing.JTextField();
        profile_jabatan = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        profile_phone = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        profile_pola = new javax.swing.JTextField();
        profile_jk = new javax.swing.JComboBox<>();
        jLabel15 = new javax.swing.JLabel();
        profile_bonus = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        profile_denda = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        profile_gaji = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        profile_delete = new javax.swing.JButton();
        profile_update = new javax.swing.JButton();
        profile_date = new com.toedter.calendar.JDateChooser();
        profile_cek = new javax.swing.JButton();
        profile_gajipokok = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        admin_button = new javax.swing.JPanel();
        btn_hadir = new javax.swing.JButton();
        btn_keluar = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        text_tanggal = new javax.swing.JLabel();
        text_jam = new javax.swing.JLabel();
        btn_absen1 = new javax.swing.JButton();
        btn_absen2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(0, 102, 153));
        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jPanel3.setBackground(new java.awt.Color(204, 255, 204));
        jPanel3.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel3.setLayout(new java.awt.CardLayout());

        absence.setBackground(new java.awt.Color(204, 255, 204));
        absence.setMinimumSize(new java.awt.Dimension(700, 600));
        absence.setPreferredSize(new Dimension(700, 600));

        absen_status.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        absen_status.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "HADIR", "IZIN", "ALPA", "CUTI", "LIBUR" }));
        absen_status.setEnabled(false);
        absen_status.setOpaque(true);
        absen_status.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                absen_statusActionPerformed(evt);
            }
        });

        absen_cari.setText("CARI");
        absen_cari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                absen_cariActionPerformed(evt);
            }
        });

        absen_date.setDateFormatString("MM/dd/yyyy");

        jLabel87.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel87.setForeground(new java.awt.Color(255, 255, 255));
        jLabel87.setText("CONFIRM");

        absen_confirm.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        absen_confirm.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "NO", "YES" }));
        absen_confirm.setEnabled(false);

        jLabel38.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel38.setForeground(new java.awt.Color(255, 255, 255));
        jLabel38.setText("LEMBUR");

        absen_lembur.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        absen_lembur.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "NO", "YES" }));
        absen_lembur.setEnabled(false);

        jLabel37.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel37.setForeground(new java.awt.Color(255, 255, 255));
        jLabel37.setText("TERLAMBAT");

        absen_late.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        absen_late.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "NO", "YES" }));
        absen_late.setEnabled(false);

        jLabel95.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel95.setForeground(new java.awt.Color(255, 255, 255));
        jLabel95.setText("DENDA");

        absen_denda.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        absen_denda.setText("0");
        absen_denda.setEnabled(false);

        jLabel96.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel96.setForeground(new java.awt.Color(255, 255, 255));
        jLabel96.setText("CATATAN");

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addComponent(absen_date, javax.swing.GroupLayout.PREFERRED_SIZE, 304, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(absen_cari, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(absen_status, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel87, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(absen_confirm, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel38, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(absen_lembur, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel37, javax.swing.GroupLayout.DEFAULT_SIZE, 675, Short.MAX_VALUE)
                    .addComponent(absen_late, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel95, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(absen_denda)
                    .addComponent(jLabel96, javax.swing.GroupLayout.DEFAULT_SIZE, 675, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(absen_status, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(absen_date, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(absen_cari, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jLabel87)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(absen_confirm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel38)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(absen_lembur, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel37)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(absen_late, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel95)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(absen_denda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel96)
                .addContainerGap(7, Short.MAX_VALUE))
        );

        absen_note.setColumns(20);
        absen_note.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        absen_note.setRows(5);
        absen_note.setText("-");
        jScrollPane13.setViewportView(absen_note);

        javax.swing.GroupLayout absensiLayout = new javax.swing.GroupLayout(absensi);
        absensi.setLayout(absensiLayout);
        absensiLayout.setHorizontalGroup(
            absensiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(absensiLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, absensiLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane13, javax.swing.GroupLayout.PREFERRED_SIZE, 667, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
            .addGroup(absensiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(absensiLayout.createSequentialGroup()
                    .addContainerGap(18, Short.MAX_VALUE)
                    .addComponent(jLabel6)
                    .addGap(305, 305, 305)
                    .addComponent(jLabel7)
                    .addGap(143, 143, 143)
                    .addComponent(jLabel21)
                    .addGap(61, 61, 61)
                    .addComponent(jLabel31)
                    .addGap(172, 172, 172)))
        );
        absensiLayout.setVerticalGroup(
            absensiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(absensiLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane13, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34))
            .addGroup(absensiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(absensiLayout.createSequentialGroup()
                    .addContainerGap(551, Short.MAX_VALUE)
                    .addGroup(absensiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel7)
                        .addComponent(jLabel21)
                        .addComponent(jLabel6)
                        .addComponent(jLabel31))
                    .addContainerGap()))
        );

        jTabbedPane2.addTab("ABSEN", absensi);

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel3.setText("USERNAME");

        profile_user.setEditable(false);
        profile_user.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        profile_user.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                profile_userActionPerformed(evt);
            }
        });

        profile_pass.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel4.setText("PASSWORD");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel5.setText("JENIS KELAMIN");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel8.setText("NO KTP");

        profile_ktp.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel11.setText("DIVISI");

        profile_divisi.setEditable(false);
        profile_divisi.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N

        profile_jabatan.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel12.setText("JABATAN");

        profile_phone.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel13.setText("PHONE");

        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel14.setText("POLA KERJA");

        profile_pola.setEditable(false);
        profile_pola.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N

        profile_jk.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        profile_jk.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "PRIA", "WANITA" }));
        profile_jk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                profile_jkActionPerformed(evt);
            }
        });

        jLabel15.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel15.setText("BONUS/DENDA");

        profile_bonus.setEditable(false);
        profile_bonus.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N

        jLabel16.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel16.setText("GAJI POKOK");

        profile_denda.setEditable(false);
        profile_denda.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N

        jLabel17.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel17.setText("TOTAL GAJI");

        profile_gaji.setEditable(false);
        profile_gaji.setBackground(new java.awt.Color(51, 153, 0));
        profile_gaji.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        profile_gaji.setForeground(new java.awt.Color(0, 0, 0));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setText("Rp.");

        jLabel18.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel18.setText("Rp.");

        jLabel19.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel19.setText("Rp.");

        profile_delete.setText("DELETE");
        profile_delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                profile_deleteActionPerformed(evt);
            }
        });

        profile_update.setText("UPDATE");
        profile_update.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                profile_updateActionPerformed(evt);
            }
        });

        profile_date.setDateFormatString("MM/dd/yyyy");

        profile_cek.setText("CEK DATA");
        profile_cek.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                profile_cekActionPerformed(evt);
            }
        });

        profile_gajipokok.setEditable(false);
        profile_gajipokok.setBackground(new java.awt.Color(51, 153, 0));
        profile_gajipokok.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        profile_gajipokok.setForeground(new java.awt.Color(0, 0, 0));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setText("Rp.");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(profile_date, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(profile_cek)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(profile_update)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(profile_delete))
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(profile_pass, javax.swing.GroupLayout.PREFERRED_SIZE, 419, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(profile_user, javax.swing.GroupLayout.PREFERRED_SIZE, 419, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(profile_jk, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(profile_ktp, javax.swing.GroupLayout.PREFERRED_SIZE, 419, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(profile_divisi, javax.swing.GroupLayout.PREFERRED_SIZE, 419, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(profile_jabatan, javax.swing.GroupLayout.PREFERRED_SIZE, 419, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(profile_phone, javax.swing.GroupLayout.PREFERRED_SIZE, 419, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(profile_pola, javax.swing.GroupLayout.PREFERRED_SIZE, 419, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(jPanel4Layout.createSequentialGroup()
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(jPanel4Layout.createSequentialGroup()
                                    .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel4Layout.createSequentialGroup()
                                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(profile_gaji, javax.swing.GroupLayout.PREFERRED_SIZE, 386, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel4Layout.createSequentialGroup()
                                    .addComponent(profile_bonus)
                                    .addGap(43, 43, 43)
                                    .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(profile_denda, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGroup(jPanel4Layout.createSequentialGroup()
                            .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(profile_gajipokok, javax.swing.GroupLayout.PREFERRED_SIZE, 386, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(41, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(profile_delete)
                        .addComponent(profile_update)
                        .addComponent(profile_cek))
                    .addComponent(profile_date, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(profile_user, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(profile_pass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(profile_jk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(profile_ktp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(profile_divisi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(profile_jabatan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(profile_phone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(profile_pola, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(profile_bonus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(profile_denda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel16)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
                    .addComponent(profile_gajipokok, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel17)
                        .addComponent(profile_gaji, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(23, 23, 23))
        );

        jTabbedPane2.addTab("PROFILE", jPanel4);

        javax.swing.GroupLayout absenceLayout = new javax.swing.GroupLayout(absence);
        absence.setLayout(absenceLayout);
        absenceLayout.setHorizontalGroup(
            absenceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(absenceLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane2)
                .addContainerGap())
        );
        absenceLayout.setVerticalGroup(
            absenceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(absenceLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane2)
                .addContainerGap())
        );

        jPanel3.add(absence, "card2");

        admin_button.setBackground(new java.awt.Color(204, 255, 204));
        admin_button.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        btn_hadir.setBackground(new java.awt.Color(0, 102, 153));
        btn_hadir.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        btn_hadir.setForeground(new java.awt.Color(0, 0, 0));
        btn_hadir.setText("HADIR");
        btn_hadir.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btn_hadir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_hadirActionPerformed(evt);
            }
        });

        btn_keluar.setBackground(new java.awt.Color(153, 0, 0));
        btn_keluar.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        btn_keluar.setForeground(new java.awt.Color(255, 255, 255));
        btn_keluar.setText("KELUAR");
        btn_keluar.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btn_keluar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_keluarActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Montserrat ExtraBold", 1, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(0, 51, 153));
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel9.setText("TUNAS");

        jLabel10.setFont(new java.awt.Font("Montserrat ExtraBold", 1, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(204, 0, 0));
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel10.setText("TOYOTA");

        text_tanggal.setBackground(new java.awt.Color(0, 153, 102));
        text_tanggal.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        text_tanggal.setForeground(new java.awt.Color(0, 0, 0));
        text_tanggal.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        text_tanggal.setText("-");
        text_tanggal.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "TANGGAL", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14), new java.awt.Color(0, 0, 0))); // NOI18N
        text_tanggal.setOpaque(true);

        text_jam.setBackground(new java.awt.Color(0, 153, 102));
        text_jam.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        text_jam.setForeground(new java.awt.Color(0, 0, 0));
        text_jam.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        text_jam.setText("-");
        text_jam.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "JAM", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14), new java.awt.Color(0, 0, 0))); // NOI18N
        text_jam.setOpaque(true);

        btn_absen1.setBackground(new java.awt.Color(0, 102, 153));
        btn_absen1.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        btn_absen1.setForeground(new java.awt.Color(0, 0, 0));
        btn_absen1.setText("IZIN");
        btn_absen1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btn_absen1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_absen1ActionPerformed(evt);
            }
        });

        btn_absen2.setBackground(new java.awt.Color(0, 102, 153));
        btn_absen2.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        btn_absen2.setForeground(new java.awt.Color(0, 0, 0));
        btn_absen2.setText("CUTI");
        btn_absen2.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btn_absen2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_absen2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout admin_buttonLayout = new javax.swing.GroupLayout(admin_button);
        admin_button.setLayout(admin_buttonLayout);
        admin_buttonLayout.setHorizontalGroup(
            admin_buttonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(admin_buttonLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(admin_buttonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(admin_buttonLayout.createSequentialGroup()
                        .addGroup(admin_buttonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(text_jam, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(text_tanggal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btn_keluar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(6, 6, 6))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, admin_buttonLayout.createSequentialGroup()
                        .addGroup(admin_buttonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btn_hadir, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, admin_buttonLayout.createSequentialGroup()
                                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 10, Short.MAX_VALUE)))
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, admin_buttonLayout.createSequentialGroup()
                        .addGroup(admin_buttonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btn_absen2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btn_absen1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())))
        );
        admin_buttonLayout.setVerticalGroup(
            admin_buttonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(admin_buttonLayout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(admin_buttonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(jLabel10))
                .addGap(46, 46, 46)
                .addComponent(btn_hadir)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btn_absen1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btn_absen2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 223, Short.MAX_VALUE)
                .addComponent(text_tanggal)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(text_jam)
                .addGap(18, 18, 18)
                .addComponent(btn_keluar)
                .addGap(19, 19, 19))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addComponent(admin_button, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 715, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(admin_button, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btn_hadirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_hadirActionPerformed
        // TODO add your handling code here:
        insertAbsen("hadir");
    }//GEN-LAST:event_btn_hadirActionPerformed

    private void btn_keluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_keluarActionPerformed
        // TODO add your handling code here:
        int validation = JOptionPane.showConfirmDialog(null, "Anda yakin ingin keluar dari program?", "", JOptionPane.YES_NO_OPTION);
        switch (validation) {
            case JOptionPane.YES_OPTION :
            new Login().setVisible(true);
            dispose();
            default:
        }
    }//GEN-LAST:event_btn_keluarActionPerformed

    private void absen_statusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_absen_statusActionPerformed
        // TODO add your handling code here:
        SimpleDateFormat dcn = new SimpleDateFormat("MM/dd/yyyy");
        String date = dcn.format(absen_date.getDate());
        switch (absen_status.getSelectedIndex()) {
            case 0:
                //getTableAbsen("hadir",String.valueOf(date));
                absen_lembur.setEnabled(true);
                absen_late.setEnabled(true);
                absen_denda.setEnabled(false);
                absen_note.setEnabled(false);
                break;
            case 1:
                //getTableAbsen("izin", String.valueOf(date));
                absen_lembur.setEnabled(false);
                absen_late.setEnabled(false);
                absen_denda.setEnabled(true);
                absen_note.setEnabled(true);
                //absen_read.doClick();
                break;
            case 2:
                //getTableAbsen("alpa", String.valueOf(date));
                absen_lembur.setEnabled(false);
                absen_late.setEnabled(false);
                absen_denda.setEnabled(true);
                absen_note.setEnabled(true);
                //absen_read.doClick();
                break;
            case 3:
                //getTableAbsen("cuti", String.valueOf(date));
                absen_lembur.setEnabled(false);
                absen_late.setEnabled(false);
                absen_denda.setEnabled(true);
                absen_note.setEnabled(true);
                //absen_read.doClick();
                break;
            case 4:
                //getTableAbsen("libur", String.valueOf(date));
                absen_lembur.setEnabled(false);
                absen_late.setEnabled(false);
                absen_denda.setEnabled(true);
                absen_note.setEnabled(true);
                //absen_read.doClick();
                break;
            default:
                break;
        }
        // Close the result set
        if (res != null) {try {res.close();} catch (SQLException e) {}}
        // Close the prepared statement
        if (pst != null) {try {pst.close();} catch (SQLException e) {}}
        // Close the connection
        if (con != null) {try {con.close();} catch (SQLException e) {}}
    }//GEN-LAST:event_absen_statusActionPerformed

    private void absen_cariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_absen_cariActionPerformed
            // TODO add your handling code here:
            String user = profile_user.getText().toLowerCase();
        try {
            getAbsen(user, absen_date);
        } catch (ParseException ex) {
            Logger.getLogger(Karyawan.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }//GEN-LAST:event_absen_cariActionPerformed

    private void btn_absen1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_absen1ActionPerformed
        // TODO add your handling code here:
        insertAbsen("izin");
    }//GEN-LAST:event_btn_absen1ActionPerformed

    private void btn_absen2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_absen2ActionPerformed
        // TODO add your handling code here:
        insertAbsen("cuti");
    }//GEN-LAST:event_btn_absen2ActionPerformed

    private void profile_jkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_profile_jkActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_profile_jkActionPerformed

    private void profile_cekActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_profile_cekActionPerformed
        // TODO add your handling code here:
        getProfile();
    }//GEN-LAST:event_profile_cekActionPerformed

    private void profile_updateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_profile_updateActionPerformed
        // TODO add your handling code here:
        int validation = JOptionPane.showConfirmDialog(null, "Anda yakin ingin update data?", "", JOptionPane.YES_NO_OPTION);
        switch (validation) {
            case JOptionPane.YES_OPTION :
                try {
                    String SQLQuery = "UPDATE `tb_karyawan` SET `password` = ?, `gender` = ?, `ktp` = ?, `jabatan` = ?, `phone` = ? WHERE `nama` = ?;";
                    con = (Connection) Connect.configDB();
                    pst = con.prepareStatement(SQLQuery);
                    pst.setString(1, profile_pass.getText().toLowerCase());
                    pst.setString(2, String.valueOf(profile_jk.getSelectedItem()).toLowerCase());
                    pst.setString(3, profile_ktp.getText());
                    pst.setString(4, profile_jabatan.getText());
                    pst.setString(5, profile_phone.getText());
                    pst.setString(6, profile_user.getText());
                    pst.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Ubah data berhasil.");
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(null, e);
                } finally {
                    // Close the result set
                    if (res != null) {try {res.close();} catch (SQLException e) {}}
                    // Close the prepared statement
                    if (pst != null) {try {pst.close();} catch (SQLException e) {}}
                    // Close the connection
                    if (con != null) {try {con.close();} catch (SQLException e) {}}
                }
            default:
        }
    }//GEN-LAST:event_profile_updateActionPerformed

    private void profile_deleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_profile_deleteActionPerformed
        // TODO add your handling code here:
        int validation = JOptionPane.showConfirmDialog(null, "Anda yakin ingin menghapus data?", "", JOptionPane.YES_NO_OPTION);
        String value = profile_user.getText().toLowerCase();
        switch (validation) {
            case JOptionPane.YES_OPTION :
                try {
                    con = (Connection) Connect.configDB();

                    // DELETE KARYAWN
                    String SQLQuery = "DELETE FROM `tb_karyawan` WHERE `tb_karyawan`.`nama` = ?;";
                    pst = con.prepareStatement(SQLQuery);
                    pst.setString(1, value);
                    pst.executeUpdate();

                    // DELETE GAJI
                    SQLQuery = "DELETE FROM `tb_gaji` WHERE `tb_gaji`.`karyawan` = ?;";
                    pst = con.prepareStatement(SQLQuery);
                    pst.setString(1, value);
                    pst.executeUpdate();

                    // DELETE POLA KERJA
                    SQLQuery = "DELETE FROM `tb_pola_kerja` WHERE `tb_pola_kerja`.`nama` = ?;";
                    pst = con.prepareStatement(SQLQuery);
                    pst.setString(1, value);
                    pst.executeUpdate();
                    
                    // DELETE ABSEN
                    SQLQuery = "DELETE FROM `tb_absen` WHERE `tb_absen`.`karyawan` = ?;";
                    pst = con.prepareStatement(SQLQuery);
                    pst.setString(1, value);
                    pst.executeUpdate();

                    JOptionPane.showMessageDialog(null, "Hapus akun berhasil.");
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(null, e);
                } finally {
                    // Close the result set
                    if (res != null) {try {res.close();} catch (SQLException e) {}}
                    // Close the prepared statement
                    if (pst != null) {try {pst.close();} catch (SQLException e) {}}
                    // Close the connection
                    if (con != null) {try {con.close();} catch (SQLException e) {}}
                }
            default:
        }
    }//GEN-LAST:event_profile_deleteActionPerformed

    private void profile_userActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_profile_userActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_profile_userActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        try {
            UIManager.setLookAndFeel( new FlatDarkLaf() );
        } catch( UnsupportedLookAndFeelException ex ) {
            System.err.println( "Failed to initialize LaF" );
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Karyawan().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton absen_cari;
    private javax.swing.JComboBox<String> absen_confirm;
    private com.toedter.calendar.JDateChooser absen_date;
    private javax.swing.JTextField absen_denda;
    private javax.swing.JComboBox<String> absen_late;
    private javax.swing.JComboBox<String> absen_lembur;
    private javax.swing.JTextArea absen_note;
    private javax.swing.JComboBox<String> absen_status;
    private javax.swing.JPanel absence;
    private javax.swing.JPanel absensi;
    private javax.swing.JPanel admin_button;
    private javax.swing.JButton btn_absen1;
    private javax.swing.JButton btn_absen2;
    private javax.swing.JButton btn_hadir;
    private javax.swing.JButton btn_keluar;
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
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel87;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabel95;
    private javax.swing.JLabel jLabel96;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane13;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JTextField profile_bonus;
    private javax.swing.JButton profile_cek;
    private com.toedter.calendar.JDateChooser profile_date;
    private javax.swing.JButton profile_delete;
    private javax.swing.JTextField profile_denda;
    private javax.swing.JTextField profile_divisi;
    private javax.swing.JTextField profile_gaji;
    private javax.swing.JTextField profile_gajipokok;
    private javax.swing.JTextField profile_jabatan;
    private javax.swing.JComboBox<String> profile_jk;
    private javax.swing.JTextField profile_ktp;
    private javax.swing.JTextField profile_pass;
    private javax.swing.JTextField profile_phone;
    private javax.swing.JTextField profile_pola;
    private javax.swing.JButton profile_update;
    private javax.swing.JTextField profile_user;
    private javax.swing.JLabel text_jam;
    private javax.swing.JLabel text_tanggal;
    // End of variables declaration//GEN-END:variables
}
