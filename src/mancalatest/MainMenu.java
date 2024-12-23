package mancalatest;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MainMenu extends JFrame {
    public MainMenu() {
        setTitle("Main Menu");
        setSize(1280, 720);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Mengatur layout ke null
        setLayout(null);

        // Label untuk judul
        JLabel titleLabel = new JLabel("Selamat Datang di Mancala Game", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 36));
        titleLabel.setBounds(390, 200, 500, 50); // Atur posisi dan ukuran
        add(titleLabel);

        // Label untuk berpindah ke Frame 2
        JLabel startLabel = new JLabel(new ImageIcon("src/image/PlayGame.png"));
        startLabel.setBounds(540, 350, 200, 50); // Atur posisi dan ukuran
        startLabel.setCursor(new Cursor(Cursor.HAND_CURSOR)); // Menambahkan ikon kursor tangan
        add(startLabel);

        // Event ketika label ditekan
        startLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Pindah ke Frame 2 (MancalaGame)
                MancalaGame game = new MancalaGame() {
                    @Override
                    public void mousePressed(MouseEvent e) {}

                    @Override
                    public void mouseReleased(MouseEvent e) {}

                    @Override
                    public void mouseEntered(MouseEvent e) {}

                    @Override
                    public void mouseExited(MouseEvent e) {}
                };
                game.setVisible(true); // Tampilkan Frame 2
                dispose(); // Tutup Frame 1
            }
        });
    }
}
