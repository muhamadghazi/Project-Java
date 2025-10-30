package koneksimahasiswa;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class KoneksiDatabase {
    private static Connection koneksi;
    
    public static Connection getKoneksi() {
        if (koneksi == null) {
            try {
                String url = "jdbc:mysql://localhost:3306/mahasiswa_db";
                String user = "root";
                String password = "";
               
                DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
                
                koneksi = DriverManager.getConnection(url, user, password);
                System.out.println("Koneksi berhasil!");
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, 
                    "Koneksi database gagal!\nError: " + e.getMessage() + 
                    "\n\nPastikan:\n1. MySQL server berjalan\n2. Database 'mahasiswa_db' sudah dibuat", 
                    "Database Error", 
                    JOptionPane.ERROR_MESSAGE);
                System.out.println("Koneksi gagal: " + e.getMessage());
            }
        }
        return koneksi;
    }
    
    public static void closeKoneksi() {
        if (koneksi != null) {
            try {
                koneksi.close();
                koneksi = null;
                System.out.println("Koneksi ditutup.");
            } catch (SQLException e) {
                System.out.println("Gagal menutup koneksi: " + e.getMessage());
            }
        }
    }
}