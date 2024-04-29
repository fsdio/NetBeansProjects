/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aplikasikaryawan;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author fsdio
 */
public class Connect {
    
    private static Connection mysqlconfig;
    public static Connection configDB() throws SQLException {
        try {
            String user="root"; //user database
            String pass=""; //password database
            String db="201943501285_absensi"; //name database
            String url="jdbc:mysql://localhost:3306/"+db; //url database
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
            mysqlconfig = DriverManager.getConnection(url, user, pass);   
            // JOptionPane.showMessageDialog(null, "Database Connect");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Database Error : " + e); //perintah menampilkan error pada koneksi
        }
        return mysqlconfig;
    }
}
