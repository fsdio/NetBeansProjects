/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import admin.Admin;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.parser.clipper.Paths;
import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import main.Login;
import module.EpochTime;
import pelanggan.Pelanggan;
import pelanggan.myProfile;

/**
 *
 * @author fsdio
 */
public class methodDB {
    // My Variable Global
    Connection con = null; PreparedStatement pst = null; ResultSet res = null;
    
    // Method Table
    public void DefaultTabel(JTable newTable, Integer size){
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        TableColumnModel columnModel = newTable.getColumnModel();
        for (int i = 0; i < size; i++) {
            newTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
            columnModel.getColumn(i).setPreferredWidth(200);
            columnModel.getColumn(i).setMinWidth(200);
        }
    }
    
    // LOGIN //
    public void Login(JTextField user, JPasswordField pass) throws ParseException{
            try {
                String SQLQuery = "SELECT COUNT(*), `type` FROM `user` WHERE `username`=? AND `password`=?;";
                con = (Connection) Connect.configDB();
                pst = con.prepareStatement(SQLQuery);
                pst.setString(1, user.getText().toLowerCase());
                pst.setString(2, String.valueOf(pass.getPassword()).toLowerCase());
                res = pst.executeQuery();
                
                while (res.next()) {                    
                    if(res.getInt(1)==0){
                        JOptionPane.showMessageDialog(null, "Data kosong.");
                        new Login().setVisible(true);
                    }else if(res.getString(2).equals("admin")){
                        new Admin().setVisible(true);
                    }else {
                        myProfile.setUsername(user.getText().toUpperCase());
                        new Pelanggan().setVisible(true);
                    }
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
    
    public void Daftar(JTextField user, JPasswordField pass, JTextField phone){
        if(user.getText().equals("")){
            user.requestFocus();
        }else if(String.valueOf(pass.getPassword()).equals("")){
            pass.requestFocus();
        }else if(phone.getText().equals("")){
            phone.requestFocus();
        }else {
            try {
                String SQLQuery = "INSERT INTO `user` (`username`, `password`, `phone`) VALUES (?,?,?);";
                con = (Connection) Connect.configDB();
                pst = con.prepareStatement(SQLQuery);
                pst.setString(1, user.getText());
                pst.setString(2, String.valueOf(pass.getPassword()));
                pst.setString(3, phone.getText());
                pst.execute();
                JOptionPane.showMessageDialog(null, "Data berhasil disimpan.");
            } catch (SQLIntegrityConstraintViolationException e) {
                JOptionPane.showMessageDialog(null, "Data sudah digunakan.");
            }catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e);
            } finally {
                // Close the result set
                if (res != null) {try {res.close();} catch (SQLException e) {}}
                // Close the prepared statement
                if (pst != null) {try {pst.close();} catch (SQLException e) {}}
                // Close the connection
                if (con != null) {try {con.close();} catch (SQLException e) {}}
            }
        }
    }
    
    // ADMIN //
    public void getDataHistoryDays(JTable nameTable, Long start, Long end) throws ParseException{
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("ID TOPUP");
        model.addColumn("USERNAME");
        model.addColumn("NOMINAL");
        model.addColumn("JENIS BAYAR");
        model.addColumn("STATUS");
        model.addColumn("CATATAN");
        
        try {
            String SQLQuery = "SELECT id, username, nominal, type_buy, status, catatan FROM topup WHERE date BETWEEN ? AND ?;";
            con = (Connection) Connect.configDB();
            pst = con.prepareStatement(SQLQuery);
            pst.setLong(1, start);
            pst.setLong(2, end);
            res = pst.executeQuery();
            
            while (res.next()) {                
                model.addRow(new Object[]{
                    res.getString(1).toUpperCase(),
                    res.getString(2).toUpperCase(),
                    res.getString(3).toUpperCase(),
                    res.getString(4).toUpperCase(),
                    res.getString(5).toUpperCase(),
                    res.getString(6).toUpperCase(),
                });
            }
            nameTable.setModel(model);
            DefaultTabel(nameTable, 6);
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
    }
    
    public void UpdateTopup(String status, JTextField id, JTextField username){
        try {
            String SQLQuery = "UPDATE `topup` SET `status` = ? WHERE `id` = ? AND `username` = ?;";
            con = (Connection) Connect.configDB();
            pst = con.prepareStatement(SQLQuery);
            pst.setString(1, status.toLowerCase());
            pst.setString(2, id.getText());
            pst.setString(3, username.getText());
            pst.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Data di"+status+".");
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
    }
    
    public void updateSaldoWithAdmin(String username, Integer saldo){
        try {
            int saldo_awal = 0;
            con = (Connection) Connect.configDB();
            String SQLQuery = "SELECT `saldo` FROM `user` WHERE `username` = ?;";
            pst = con.prepareStatement(SQLQuery);
            pst.setString(1, username);
            res = pst.executeQuery();
            while (res.next()) {                
                saldo_awal = res.getInt(1);
            }
            
            SQLQuery = "UPDATE `user` SET `saldo` = ? WHERE `username` = ?;";
            pst = con.prepareStatement(SQLQuery);
            pst.setInt(1, (saldo_awal+saldo));
            pst.setString(2, username);
            pst.executeUpdate();
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
    }
    
    public void getDataPelanggan(JTable nameTable){
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("USERNAME");
        model.addColumn("PASSWORD");
        model.addColumn("NO HANDPHONE");
        model.addColumn("SALDO");
        model.addColumn("TYPE   ");
        
        try {
            String SQLQuery = "SELECT * FROM `user`;";
            con = (Connection) Connect.configDB();
            pst = con.prepareStatement(SQLQuery);
            res = pst.executeQuery();
            
            while (res.next()) {                
                model.addRow(new Object[]{
                    res.getString(1).toUpperCase(),
                    res.getString(2).toUpperCase(),
                    res.getString(3).toUpperCase(),
                    res.getString(4).toUpperCase(),
                    res.getString(5).toUpperCase(),
                });
            }
            nameTable.setModel(model);
            DefaultTabel(nameTable, 5);
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
    }
    
    public void getDataPelangganId(JTable nameTable, String username){
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("USERNAME");
        model.addColumn("PASSWORD");
        model.addColumn("NO HANDPHONE");
        model.addColumn("SALDO");
        model.addColumn("TYPE   ");
        
        try {
            String SQLQuery = "SELECT * FROM `user` WHERE `username`=?;";
            con = (Connection) Connect.configDB();
            pst = con.prepareStatement(SQLQuery);
            pst.setString(1, username);
            res = pst.executeQuery();
            
            while (res.next()) {                
                model.addRow(new Object[]{
                    res.getString(1).toUpperCase(),
                    res.getString(2).toUpperCase(),
                    res.getString(3).toUpperCase(),
                    res.getString(4).toUpperCase(),
                    res.getString(5).toUpperCase(),
                });
            }
            nameTable.setModel(model);
            DefaultTabel(nameTable, 5);
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
    }
    
    public void setDataPelanggan(JTable nameTable, String ...value){
        
        try {
            String SQLQuery = "UPDATE `user` SET `password` = ?, `phone` = ?, `saldo` = ?, `type` = ? WHERE `username` = ?;";
            con = (Connection) Connect.configDB();
            pst = con.prepareStatement(SQLQuery);
            pst.setString(1, value[0]);
            pst.setString(2, value[1]);
            pst.setString(3, value[2]);
            pst.setString(4, value[3]);
            pst.setString(5, value[4]);
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
    }
    
    public void deleteDataPelanggan(String value){
        
        try {
            con = (Connection) Connect.configDB();
            
            // DELETE ORDER
            String SQLQuery = "DELETE FROM `order` WHERE `order`.`username` = ?;";
            pst = con.prepareStatement(SQLQuery);
            pst.setString(1, value);
            pst.executeUpdate();
            
            // DELETE ORDER
            SQLQuery = "DELETE FROM `topup` WHERE `topup`.`username` = ?;";
            pst = con.prepareStatement(SQLQuery);
            pst.setString(1, value);
            pst.executeUpdate();
            
            // DELETE USER
            SQLQuery = "DELETE FROM `user` WHERE `user`.`username` = ?;";
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
    }
    
    public void getDataPemesanan(JTable nameTable, Long start, Long end){
        
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("ID PESAN");
        model.addColumn("USERNAME");
        model.addColumn("TANGGAL");
        model.addColumn("ALAMAT");
        model.addColumn("JUMLAH");
        model.addColumn("HARGA");
        model.addColumn("TIPE PEMBAYARAN");
        model.addColumn("STATUS PEMBAYARAN");
        
        try {
            String SQLQuery = "SELECT id, username, date, alamat, quantity, harga, type_buy, status_buy FROM `order` WHERE `date` BETWEEN ? AND ?;";
            
            con = (Connection) Connect.configDB();
            pst = con.prepareStatement(SQLQuery);
            pst.setLong(1, start);
            pst.setLong(2, end);
            res = pst.executeQuery();
            
            while (res.next()) {                
                model.addRow(new Object[]{
                    res.getString(1).toUpperCase(),
                    res.getString(2).toUpperCase(),
                    res.getString(3).toUpperCase(),
                    res.getString(4).toUpperCase(),
                    res.getString(5).toUpperCase(),
                    res.getString(6).toUpperCase(),
                    res.getString(7).toUpperCase(),
                    res.getString(8).toUpperCase(),
                });
            }
            nameTable.setModel(model);
            DefaultTabel(nameTable, 8);
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
    }
    
    public void getDataPemesananId(JTable nameTable, String username, Long start, Long end){
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("ID PESAN");
        model.addColumn("USERNAME");
        model.addColumn("TANGGAL");
        model.addColumn("ALAMAT");
        model.addColumn("JUMLAH");
        model.addColumn("HARGA");
        model.addColumn("TIPE PEMBAYARAN");
        model.addColumn("STATUS PEMBAYARAN");
        
        try {
            String SQLQuery = "SELECT id, username, date, alamat, quantity, harga, type_buy, status_buy FROM `order` WHERE (`username`=?) AND (`date` BETWEEN ? AND ?);";
            con = (Connection) Connect.configDB();
            pst = con.prepareStatement(SQLQuery);
            pst.setString(1, username);
            pst.setLong(2, start);
            pst.setLong(3, end);
            res = pst.executeQuery();
            
            while (res.next()) {                
                model.addRow(new Object[]{
                    res.getString(1).toUpperCase(),
                    res.getString(2).toUpperCase(),
                    res.getString(3).toUpperCase(),
                    res.getString(4).toUpperCase(),
                    res.getString(5).toUpperCase(),
                    res.getString(6).toUpperCase(),
                    res.getString(7).toUpperCase(),
                    res.getString(8).toUpperCase(),
                });
            }
            nameTable.setModel(model);
            DefaultTabel(nameTable, 8);
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
    }
    
    public void createDataPemesanan(String ...value){
        try {
            String SQLQuery = "INSERT INTO `order` (`id`, `username`, `alamat`, `quantity`, `harga`, `type_buy`, `status_buy`, `date`) VALUES (NULL, ?, ?, ?, ?, ?, ?, ?);";
            con = (Connection) Connect.configDB();
            pst = con.prepareStatement(SQLQuery);
            pst.setString(1, value[0]);
            pst.setString(2, value[1]);
            pst.setString(3, value[2]);
            pst.setString(4, value[3]);
            pst.setString(5, value[4]);
            pst.setString(6, value[5]);
            pst.setString(7, value[6]);
            pst.execute();
            JOptionPane.showMessageDialog(null, "Data disimpan.");
        } catch (SQLIntegrityConstraintViolationException e) {
            JOptionPane.showMessageDialog(null, "Data username tidak tersedia.");
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
    }
    
    public void deleteDataPemesanan(JTable nameTable, Integer key, String username){
        try {
            String SQLQuery = "DELETE FROM `order` WHERE id=? AND username=?";
            con = (Connection) Connect.configDB();
            pst = con.prepareStatement(SQLQuery);
            pst.setInt(1, key);
            pst.setString(2, username);
            pst.execute();
            JOptionPane.showMessageDialog(null, "Data dihapus.");
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
    }
    
    
    // PELANGGAN //
    public void getDataUser(String username, JTextField user, JTextField pass, JTextField phone, JLabel saldo){
        try {
            String SQLQuery = "SELECT username, password, phone, saldo FROM `user` WHERE username=?;";
            con = (Connection) Connect.configDB();
            pst = con.prepareStatement(SQLQuery);
            pst.setString(1, username);
            res = pst.executeQuery();
            
            while (res.next()) { 
                user.setText(res.getString(1));
                pass.setText(res.getString(2));
                phone.setText(res.getString(3));
                saldo.setText("Rp. " + res.getString(4));
            }
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
    }
    
    public void getDataTopup(JTable nameTable, String user, Long start, Long end) throws ParseException{
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("ID TOPUP");
        model.addColumn("STATUS");
        model.addColumn("NOMINAL");
        model.addColumn("VIA PEMBAYARAN");
        model.addColumn("NO HP");
        model.addColumn("SALDO");
        model.addColumn("DATE");
        model.addColumn("PASSWORD");
        model.addColumn("CATATAN");
        
        try {
            String SQLQuery = "CALL getProfileTopup(?,?,?);";
            con = (Connection) Connect.configDB();
            pst = con.prepareStatement(SQLQuery);
            pst.setString(1, user);
            pst.setLong(2, start);
            pst.setLong(3, end);
            res = pst.executeQuery();
            
            while (res.next()) {                
                model.addRow(new Object[]{
                    res.getString(1).toUpperCase(),
                    res.getString(2).toUpperCase(),
                    res.getString(3).toUpperCase(),
                    res.getString(4).toUpperCase(),
                    res.getString(5).toUpperCase(),
                    res.getString(6).toUpperCase(),
                    res.getString(7).toUpperCase(),
                    res.getString(8).toUpperCase(),
                    res.getString(9).toUpperCase(),
                });
            }
            nameTable.setModel(model);
            DefaultTabel(nameTable, 9);
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
    }
    
    public void setDataUser(JTable nameTable, String ...value){    
        try {
            String SQLQuery = "UPDATE `user` SET `password` = ?, `phone` = ? WHERE `username` = ?;";
            con = (Connection) Connect.configDB();
            pst = con.prepareStatement(SQLQuery);
            pst.setString(1, value[0]);
            pst.setString(2, value[1]);
            pst.setString(3, value[2]);
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
    }
    
    public void getDataOrder(JTable nameTable, String user, Long start, Long end) throws ParseException{
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("ID PESAN");
        model.addColumn("TANGGAL");
        model.addColumn("ALAMAT");
        model.addColumn("JUMLAH");
        model.addColumn("HARGA");
        model.addColumn("TIPE PEMBAYARAN");
        model.addColumn("STATUS PEMBAYARAN");
        
        try {
            String SQLQuery = "CALL getOrder(?,?,?);";
            con = (Connection) Connect.configDB();
            pst = con.prepareStatement(SQLQuery);
            pst.setString(1, user);
            pst.setLong(2, start);
            pst.setLong(3, end);
            res = pst.executeQuery();
            
            while (res.next()) {                
                model.addRow(new Object[]{
                    res.getString(1).toUpperCase(),
                    res.getString(2).toUpperCase(),
                    res.getString(3).toUpperCase(),
                    res.getString(4).toUpperCase(),
                    res.getString(5).toUpperCase(),
                    res.getString(6).toUpperCase(),
                    res.getString(7).toUpperCase(),
                });
            }
            nameTable.setModel(model);
            DefaultTabel(nameTable, 7);
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
    }
    
    public void getSaldo(String username, JMenu saldo){
        try {
            String SQLQuery = "SELECT saldo FROM `user` WHERE username=?;";
            con = (Connection) Connect.configDB();
            pst = con.prepareStatement(SQLQuery);
            pst.setString(1, username);
            res = pst.executeQuery();
            
            while (res.next()) { 
                saldo.setText("RP. " + res.getString(1));
            }
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
    }
    
    public void updateSaldo(JMenu username, Integer saldo){
        try {
            String SQLQuery = "UPDATE `user` SET `saldo` = ? WHERE `username` = ?;";
            con = (Connection) Connect.configDB();
            pst = con.prepareStatement(SQLQuery);
            pst.setInt(1, saldo);
            pst.setString(2, username.getText().toLowerCase());
            pst.executeUpdate();
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
    }
    
    public void inputSaldo(String ...data){
        try {
            String SQLQuery = "INSERT INTO `topup` (`id`, `username`, `nominal`, `type_buy`, `status`, `catatan`, `date`) VALUES (NULL, ?, ?, ?, ?, ?, ?)";
            con = (Connection) Connect.configDB();
            pst = con.prepareStatement(SQLQuery);
            pst.setString(1, data[0]);
            pst.setInt(2, Integer.parseInt(data[1]));
            pst.setString(3, data[2]);
            pst.setString(4, "proses");
            pst.setString(5, data[3]);
            pst.setLong(6, EpochTime.currentTime());
            pst.execute();
            JOptionPane.showMessageDialog(null, "Anda sudah berhasil topup, namun tunggu persetujuan admin.");
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
    }
    
    // CETAK DATA PELANGGAN //
    public void generatePDF(JTable table, String laporan) throws IOException {
        
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showSaveDialog(null);
        
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            
            try {
                
                // OPEN FOLDER
                Document document = new Document(PageSize.A4.rotate());
                PdfWriter.getInstance(document, new FileOutputStream(selectedFile.getAbsolutePath() + ".pdf"));
                document.open();
                
                // MEMBUAT JUDUL PARAGRAF
                String tanggal = new SimpleDateFormat("EEEE, dd MMMM Y KK:mm:ss").format(new java.util.Date());
                String textJudul = "HASIL LAPORAN " + laporan.toUpperCase() + "\n" + tanggal + "\n\n";
                Font title = new Font(Font.FontFamily.HELVETICA, 18, Font.BOLD);
                Paragraph paragraph = new Paragraph(textJudul, title);
                paragraph.setAlignment(Element.ALIGN_CENTER);
                document.add(paragraph);

                // MEMBUAT TABEL
                PdfPTable pdfTable = new PdfPTable(table.getColumnCount());

                for (int i = 0; i < table.getColumnCount(); i++) {
                    PdfPCell cell = new PdfPCell(new Phrase(table.getColumnName(i)));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    pdfTable.addCell(cell);
                }

                for (int i = 0; i < table.getRowCount(); i++) {
                    for (int j = 0; j < table.getColumnCount(); j++) {
                        pdfTable.addCell(table.getValueAt(i, j).toString());
                    }
                }

                document.add(pdfTable);
                JOptionPane.showMessageDialog(null, "Create PDF done.");
                document.close();

            } catch (DocumentException | FileNotFoundException e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }
    }
}