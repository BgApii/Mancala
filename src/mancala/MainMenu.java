package mancala;

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

        // Label untuk backgroud
        ImageIcon mainMenuIcon = new ImageIcon("src/image/main menu.png");
        JLabel mainMenu = new JLabel();
        mainMenu.setIcon(mainMenuIcon);
        mainMenu.setBounds(0, 0, 1280, 720); // Atur posisi dan ukuran
        add(mainMenu);

        JLabel l_playGame = new JLabel(new ImageIcon("src/image/label.png"));
        l_playGame.setBounds(950, 320, 320, 100);
        add(l_playGame);

        Font font = new Font("Script MT Bold", Font.PLAIN, 48);
        JLabel playGame = new JLabel();
        playGame.setText("Play Game");
        playGame.setFont(font);
        playGame.setHorizontalAlignment(SwingConstants.CENTER);
        playGame.setForeground(new Color(255, 255, 255));
        playGame.setBounds(960, 330, 300, 80); // Atur posisi dan ukuran
        playGame.setCursor(new Cursor(Cursor.HAND_CURSOR)); // Menambahkan ikon kursor tangan
        add(playGame);

        JLabel l_rules = new JLabel(new ImageIcon("src/image/label.png"));
        l_rules.setBounds(890, 440, 320, 100);
        add(l_rules);

        JLabel rules = new JLabel();
        rules.setText("Rules");
        rules.setFont(font);
        rules.setHorizontalAlignment(SwingConstants.CENTER);
        rules.setForeground(new Color(255, 255, 255));
        rules.setBounds(900, 450, 300, 80); // Atur posisi dan ukuran
        rules.setCursor(new Cursor(Cursor.HAND_CURSOR)); // Menambahkan ikon kursor tangan
        add(rules);

        JLabel l_aboutUs = new JLabel(new ImageIcon("src/image/label.png"));
        l_aboutUs.setBounds(830, 560, 320, 100);
        add(l_aboutUs);

        JLabel aboutUs = new JLabel();
        aboutUs.setText("About Us");
        aboutUs.setFont(font);
        aboutUs.setHorizontalAlignment(SwingConstants.CENTER);
        aboutUs.setForeground(new Color(255, 255, 255));
        aboutUs.setBounds(840, 570, 300, 80); // Atur posisi dan ukuran
        aboutUs.setCursor(new Cursor(Cursor.HAND_CURSOR)); // Menambahkan ikon kursor tangan
        add(aboutUs);

        setComponentZOrder(playGame, 0);
        setComponentZOrder(rules, 1);
        setComponentZOrder(aboutUs, 2);
        setComponentZOrder(l_playGame, 3);
        setComponentZOrder(l_rules, 4);
        setComponentZOrder(l_aboutUs, 5);
        setComponentZOrder(mainMenu, 6);

        // Event ketika label ditekan
        playGame.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                GameTypes gameTypes = new GameTypes();
                gameTypes.setVisible(true); // Tampilkan Game Types
                dispose(); // Tutup Main Menu
            }
        });


        rules.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Rules rules = new Rules();
                rules.setVisible(true);
                dispose();
            }
        });

        aboutUs.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                AboutUs aboutUs = new AboutUs();
                aboutUs.setVisible(true);
                dispose();
            }
        });
    }
}
