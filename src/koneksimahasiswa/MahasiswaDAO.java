package koneksimahasiswa;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class MahasiswaDAO {
    private Connection koneksi;
    
    public MahasiswaDAO() {
        koneksi = KoneksiDatabase.getKoneksi();
    }
    
    // Method untuk menambah data mahasiswa
    public void tambahMahasiswa(Mahasiswa mhs) {
        if (koneksi == null) {
            JOptionPane.showMessageDialog(null, "Tidak terhubung ke database!");
            return;
        }
        
        String sql = "INSERT INTO mahasiswa (nim, nama, jurusan, alamat) VALUES (?, ?, ?, ?)";
        try {
            PreparedStatement stmt = koneksi.prepareStatement(sql);
            stmt.setString(1, mhs.getNim());
            stmt.setString(2, mhs.getNama());
            stmt.setString(3, mhs.getJurusan());
            stmt.setString(4, mhs.getAlamat());
            stmt.executeUpdate();
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
    }
    
    // Method untuk update data mahasiswa
    public void updateMahasiswa(Mahasiswa mhs, String nimLama) {
        if (koneksi == null) {
            JOptionPane.showMessageDialog(null, "Tidak terhubung ke database!");
            return;
        }
        
        String sql = "UPDATE mahasiswa SET nim=?, nama=?, jurusan=?, alamat=? WHERE nim=?";
        try {
            PreparedStatement stmt = koneksi.prepareStatement(sql);
            stmt.setString(1, mhs.getNim());
            stmt.setString(2, mhs.getNama());
            stmt.setString(3, mhs.getJurusan());
            stmt.setString(4, mhs.getAlamat());
            stmt.setString(5, nimLama);
            stmt.executeUpdate();
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
    }
    
    // Method untuk mengambil semua data mahasiswa
    public List<Mahasiswa> getAllMahasiswa() {
        List<Mahasiswa> listMhs = new ArrayList<>();
        
        if (koneksi == null) {
            return listMhs; // return empty list
        }
        
        String sql = "SELECT * FROM mahasiswa";
        try {
            Statement stmt = koneksi.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Mahasiswa mhs = new Mahasiswa(
                    rs.getString("nim"),
                    rs.getString("nama"),
                    rs.getString("jurusan"),
                    rs.getString("alamat")
                );
                listMhs.add(mhs);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
        return listMhs;
    }
    
    // Method untuk menghapus data mahasiswa
    public void hapusMahasiswa(String nim) {
        if (koneksi == null) {
            JOptionPane.showMessageDialog(null, "Tidak terhubung ke database!");
            return;
        }
        
        String sql = "DELETE FROM mahasiswa WHERE nim = ?";
        try {
            PreparedStatement stmt = koneksi.prepareStatement(sql);
            stmt.setString(1, nim);
            stmt.executeUpdate();
            // ✅ HAPUS JOptionPane dari sini
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
    }
}