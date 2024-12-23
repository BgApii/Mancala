package mancalatest;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseListener;

public abstract class GUI extends JFrame implements MouseListener {
    private final ImageIcon boardIcon = new ImageIcon("src/image/board1.png");
    static final ImageIcon[] stoneIcons = new ImageIcon[49];
    static final JLabel[] pits = new JLabel[14];
    static final JLabel[] pitLabels = new JLabel[14];

    public GUI() {
        initializeIcons();
        initComponents();
    }

    private void initializeIcons() {
        for (int i = 0; i <= 48; i++) {
            stoneIcons[i] = new ImageIcon("src/image/stone (" + i + ").png");
        }
    }

    private void initComponents() {
        JPanel jPanel1 = new JPanel();
        JLabel mainboard = new JLabel();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jPanel1.setLayout(null);

        for (int i = 0; i < 14; i++) {
            pits[i] = new JLabel();
            pitLabels[i] = new JLabel();
            pitLabels[i].setFont(new Font("Perpetua", Font.BOLD, 36));
            pitLabels[i].setForeground(Color.WHITE);
            pitLabels[i].setHorizontalAlignment(SwingConstants.CENTER);

            pits[i].addMouseListener(this);

            jPanel1.add(pits[i]);
            jPanel1.add(pitLabels[i]);
        }

        // Set bounds for pits and labels
        int[][] pitBounds = {
                {220, 410, 70, 80}, {370, 410, 70, 80}, {530, 410, 70, 80},
                {690, 410, 70, 80}, {840, 410, 70, 80}, {1000, 410, 70, 80},
                {1130, 300, 80, 150}, {1000, 240, 70, 80}, {840, 240, 70, 80},
                {690, 240, 70, 80}, {530, 240, 70, 80}, {380, 240, 70, 80},
                {220, 240, 70, 80}, {67, 296, 80, 150}
        };

        int[][] labelBounds = {
                {230, 530, 50, 30}, {390, 530, 50, 30}, {540, 530, 50, 30},
                {700, 530, 50, 30}, {850, 530, 50, 30}, {1010, 530, 50, 30},
                {1150, 480, 50, 30}, {1010, 170, 50, 30}, {850, 170, 50, 30},
                {700, 170, 50, 30}, {540, 170, 50, 30}, {380, 170, 50, 30},
                {230, 170, 50, 30}, {80, 230, 50, 30}
        };

        for (int i = 0; i < 14; i++) {
            pits[i].setBounds(pitBounds[i][0], pitBounds[i][1], pitBounds[i][2], pitBounds[i][3]);
            pitLabels[i].setBounds(labelBounds[i][0], labelBounds[i][1], labelBounds[i][2], labelBounds[i][3]);
        }

        mainboard.setIcon(boardIcon);
        mainboard.setBounds(0, 0, 1280, 720);
        jPanel1.add(mainboard);

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(jPanel1, BorderLayout.CENTER);

        setSize(1280, 720);
        setLocationRelativeTo(null);
        board();
    }

    private void board() {
        int[] initialStones = {4, 4, 4, 4, 4, 4, 0, 4, 4, 4, 4, 4, 4, 0};

        for (int i = 0; i < 14; i++) {
            pits[i].setIcon(stoneIcons[initialStones[i]]);
            pitLabels[i].setText(String.valueOf(initialStones[i]));
        }
    }
}
