package mancala;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AboutUs extends JFrame {
    public AboutUs() {
        // Mengatur properti JFrame
        setTitle("About Us");
        setSize(1280, 720);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null); // Menggunakan layout null

        // Label untuk background
        ImageIcon backgroundIcon = new ImageIcon("src/image/aboutus.png");
        JLabel backgroundLabel = new JLabel();
        backgroundLabel.setIcon(backgroundIcon);
        backgroundLabel.setBounds(0, 0, 1280, 720); // Atur posisi dan ukuran
        add(backgroundLabel);

        // JTextArea untuk menulis informasi About Us
        JTextArea aboutTextArea = new JTextArea();
        aboutTextArea.setText("""
                Selamat Datang di Proyek Permainan Mancala!
                
                Kami adalah tim kecil yang penuh semangat, terdiri dari dua orang, yang dipersatukan oleh kecintaan kami pada permainan tradisional dan
                visi kami untuk membawanya ke dunia digital modern. Mancala, sebuah permainan strategi klasik yang telah dinikmati oleh banyak generasi,
                telah menginspirasi kami untuk menciptakan pengalaman bermain yang menarik dan inovatif.
                
                Kenali Tim Kami:
                [Apri Supandi Pasaribu] – Manajer Proyek dan Analis
                Bertanggung jawab untuk mengawasi jadwal proyek, memastikan kelancaran setiap fase, serta mengelola koordinasi tim agar komunikasi dan
                kolaborasi berjalan efektif. Selain itu, menganalisis data permainan dan masukan pengguna untuk memandu pengembangan, memastikan permainan
                sesuai dengan kebutuhan pengguna, serta menerapkan konsep yang telah dirancang ke dalam mekanika permainan.
                
                [M. Lukman Hakim] – Desainer
                Berfokus pada pembuatan desain yang menarik secara visual dan ramah pengguna untuk meningkatkan pengalaman bermain secara keseluruhan.
                Bertanggung jawab menciptakan antarmuka yang intuitif dan elemen estetis yang menyenangkan agar permainan dapat dinikmati oleh Pemain.
                
                Misi Kami:
                Untuk menciptakan versi digital dari Mancala yang menyenangkan, mudah dimainkan, dan dapat diakses oleh semua orang, sambil menjaga esensi
                dari warisan budaya ini tetap hidup.
                
                Terima kasih telah mendukung proyek kami. Kami berharap Anda menikmati permainan ini seperti halnya kami menikmati proses pembuatannya!""");

        aboutTextArea.setFont(new Font("Berlin Sans FB", Font.PLAIN, 20));
        aboutTextArea.setBorder(null); // Menghilangkan border
        aboutTextArea.setOpaque(false); // Membuat background transparan
        aboutTextArea.setEditable(false); // Membuat teks tidak dapat diubah
        aboutTextArea.setForeground(Color.WHITE); // Warna teks
        aboutTextArea.setBounds(120, 140, 1030, 504); // Atur posisi dan ukuran
        add(aboutTextArea);

        // Label untuk kembali ke Main Menu
        JLabel backLabel = new JLabel();
        backLabel.setIcon(new ImageIcon("src/image/b_back.png"));
        backLabel.setCursor(new Cursor(Cursor.HAND_CURSOR)); // Ubah kursor menjadi tangan
        backLabel.setBounds(50, 20, 100, 100); // Atur posisi dan ukuran
        add(backLabel);

        // Event untuk label kembali
        backLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                MainMenu mainMenu = new MainMenu();
                mainMenu.setVisible(true); // Tampilkan Main Menu
                dispose(); // Tutup frame About Us
            }
        });

        // Mengatur urutan komponen
        setComponentZOrder(aboutTextArea, 0);
        setComponentZOrder(backLabel, 1);
        setComponentZOrder(backgroundLabel, 2);
    }
}
