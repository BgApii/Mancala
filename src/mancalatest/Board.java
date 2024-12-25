package mancalatest;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public abstract class Board extends JFrame implements MouseListener {
    private final ImageIcon boardIcon = new ImageIcon("src/image/board.png");
    static final ImageIcon[] stoneIcons = new ImageIcon[49];
    static final JLabel[] pits = new JLabel[14];
    static final JLabel[] pitLabels = new JLabel[14];
    static JLabel turn = new JLabel();

    public Board() {
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
            pits[i].setCursor(new Cursor(Cursor.HAND_CURSOR));
            pitLabels[i] = new JLabel();
            pitLabels[i].setFont(new Font("Perpetua", Font.BOLD, 36));
            pitLabels[i].setForeground(Color.WHITE);
            pitLabels[i].setHorizontalAlignment(SwingConstants.CENTER);

            pits[i].addMouseListener(this);

            jPanel1.add(pits[i]);
            jPanel1.add(pitLabels[i]);
        }

        JLabel l_turn = new JLabel(new ImageIcon("src/image/Turn.png"));
        l_turn.setBounds(50, 30, 200, 100);
        jPanel1.add(l_turn);

        turn.setText("Player 1");
        turn.setFont(new Font("Script MT Bold", Font.PLAIN, 30));
        turn.setForeground(Color.WHITE);
        turn.setHorizontalAlignment(SwingConstants.CENTER);
        turn.setBounds(80, 48, 130, 60);
        add(turn);

        JLabel b_gameType = new JLabel(new ImageIcon("src/image/b_gameType.png"));
        b_gameType.setBounds(1120, 40, 100, 100);
        b_gameType.setCursor(new Cursor(Cursor.HAND_CURSOR));
        jPanel1.add(b_gameType);

        b_gameType.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                GameTypes gameTypes = new GameTypes();
                gameTypes.setVisible(true);
                dispose();
            }
        });

        // Set bounds for pits and labels
        int[][] pitBounds = {
                {200, 390, 100, 120}, {360, 390, 100, 120}, {520, 390,100, 120},
                {670, 390, 100, 120}, {830, 390, 100, 120}, {980, 390, 100, 120},
                {1100, 290, 140, 150}, {980, 220, 120, 100}, {830, 220, 120, 100},
                {670, 220, 120, 100}, {520, 220, 120, 100}, {360, 220, 120, 100},
                {200, 220, 120, 100}, {37, 296, 140, 150}
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
