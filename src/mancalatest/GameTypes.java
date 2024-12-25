package mancalatest;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GameTypes extends JFrame {
    public GameTypes() {
        setTitle("Game Types");
        setSize(1280, 720);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Mengatur layout ke null
        setLayout(null);

        // Label untuk backgroud
        ImageIcon GameTypeIcon = new ImageIcon("src/image/main menu.png");
        JLabel mainMenu = new JLabel();
        mainMenu.setIcon(GameTypeIcon);
        mainMenu.setBounds(0, 0, 1280, 720); // Atur posisi dan ukuran
        add(mainMenu);

        JLabel l_P1vsP2 = new JLabel(new ImageIcon("src/image/label.png"));
        l_P1vsP2.setBounds(950, 320, 320, 100);
        add(l_P1vsP2);

        Font font = new Font("Script MT Bold", Font.PLAIN, 48);

        JLabel P1vsP2 = new JLabel();
        P1vsP2.setText("P1 vs P2");
        P1vsP2.setFont(font);
        P1vsP2.setHorizontalAlignment(SwingConstants.CENTER);
        P1vsP2.setForeground(new Color(255, 255, 255));
        P1vsP2.setBounds(960, 330, 300, 80); // Atur posisi dan ukuran
        P1vsP2.setCursor(new Cursor(Cursor.HAND_CURSOR)); // Menambahkan ikon kursor tangan
        add(P1vsP2);

        JLabel l_P1vsCOM = new JLabel(new ImageIcon("src/image/label.png"));
        l_P1vsCOM.setBounds(890, 440, 320, 100);
        add(l_P1vsCOM);

        JLabel P1vsCOM = new JLabel();
        P1vsCOM.setText("P1 vs COM");
        P1vsCOM.setFont(font);
        P1vsCOM.setHorizontalAlignment(SwingConstants.CENTER);
        P1vsCOM.setForeground(new Color(255, 255, 255));
        P1vsCOM.setBounds(900, 450, 300, 80); // Atur posisi dan ukuran
        P1vsCOM.setCursor(new Cursor(Cursor.HAND_CURSOR)); // Menambahkan ikon kursor tangan
        add(P1vsCOM);

        JLabel backLabel = new JLabel();
        backLabel.setIcon(new ImageIcon("src/image/b_back.png"));
        backLabel.setCursor(new Cursor(Cursor.HAND_CURSOR)); // Ubah kursor menjadi tangan
        backLabel.setBounds(50, 20, 100, 100); // Atur posisi dan ukuran
        add(backLabel);

        setComponentZOrder(P1vsP2, 0);
        setComponentZOrder(P1vsCOM, 1);
        setComponentZOrder(l_P1vsP2, 2);
        setComponentZOrder(l_P1vsCOM, 3);
        setComponentZOrder(backLabel, 4);
        setComponentZOrder(mainMenu, 5);

        // Event ketika label ditekan
        P1vsP2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                //Pindah ke Frame 2 (vsPlayer)
                vsPlayer vsplayer = new vsPlayer() {
                    @Override
                    public void mousePressed(MouseEvent e) {}

                    @Override
                    public void mouseReleased(MouseEvent e) {}

                    @Override
                    public void mouseEntered(MouseEvent e) {}

                    @Override
                    public void mouseExited(MouseEvent e) {}
                };
                vsplayer.setVisible(true); // Tampilkan Frame 2
                dispose(); // Tutup frame Game Types
            }
        });


        P1vsCOM.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                vsCOM vscom = new vsCOM() {
                    @Override
                    public void mousePressed(MouseEvent e) {

                    }

                    @Override
                    public void mouseReleased(MouseEvent e) {

                    }

                    @Override
                    public void mouseEntered(MouseEvent e) {

                    }

                    @Override
                    public void mouseExited(MouseEvent e) {

                    }
                };
                vscom.setVisible(true);
                dispose();
            }
        });

        backLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                MainMenu mainMenu = new MainMenu();
                mainMenu.setVisible(true); // Tampilkan Main Menu
                dispose(); // Tutup frame Game Types
            }
        });
    }
}
