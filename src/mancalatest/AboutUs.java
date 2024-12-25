package mancalatest;

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
                Welcome to the Mancala Game Project!
                
                We are a small yet passionate team of two, united by our love for traditional games and our vision to bring them into
                themodern digital world. Mancala, a classic strategy game enjoyed by generations, has inspired us to create an engaging
                and innovative gaming experience.
                
                Meet Our Team:
                [Apri Supandi Pasaribu] – Project Manager and Analyst
                Responsible for overseeing the project timeline, ensuring smooth progress in every phase, and managing team coordination
                for effective communication and collaboration. Additionally, analyzes gameplay data and user feedback to guide
                development, ensures the game aligns with user needs, and implements the designed concepts into the game mechanics.
                
                [M. Lukman Hakim] – Designer
                Focuses on creating visually appealing and user-friendly designs that enhance the overall gaming experience. Responsible for
                crafting intuitive interfaces and aesthetically pleasing elements that make the game enjoyable and accessible to players.
                
                Our Mission:
                To create a digital version of Mancala that is fun, easy to play, and accessible to everyone, while keeping the essence of
                this cultural treasure alive.
                
                Thank you for supporting our project. We hope you enjoy the game as much as we enjoyed building it!""");

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
