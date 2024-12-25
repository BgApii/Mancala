package mancalatest;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Rules extends JFrame {
    public Rules() {
        // Mengatur properti JFrame
        setTitle("About Us");
        setSize(1280, 720);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null); // Menggunakan layout null

        // Label untuk background
        ImageIcon backgroundIcon = new ImageIcon("src/image/rules.png");
        JLabel backgroundLabel = new JLabel();
        backgroundLabel.setIcon(backgroundIcon);
        backgroundLabel.setBounds(0, 0, 1280, 720); // Atur posisi dan ukuran
        add(backgroundLabel);

        // JTextArea untuk menulis informasi Rules
        JTextArea rulesTextArea = new JTextArea();
        rulesTextArea.setText("""
                1. Mancala adalah permainan strategi untuk 2 pemain menggunakan papan dengan 12 lubang kecil (6 di tiap sisi)
                   dan 2 lubang besar (Mancala/penampung) di ujung papan.
                2. Setiap lubang kecil awalnya diisi dengan jumlah biji yang sama (biasanya 4 biji).
                3. Tujuan permainan adalah mengumpulkan biji terbanyak di Mancala milik Anda.
                4. Pemain bergiliran mengambil semua biji dari salah satu lubang kecil di sisi mereka.
                5. Biji didistribusikan satu per satu ke lubang-lubang berikutnya secara berurutan, searah jarum jam.
                6. Jika melewati Mancala milik sendiri, tambahkan biji; jika melewati Mancala lawan, lewati tanpa menambahkan
                   biji.
                7. Jika biji terakhir mendarat di Mancala milik Anda, Anda mendapat giliran tambahan.
                8. Jika biji terakhir mendarat di lubang kosong di sisi Anda, Anda mengambil biji itu serta semua biji di lubang
                   lawan yang sejajar, lalu memindahkannya ke Mancala Anda.
                9. Permainan berakhir jika semua lubang kecil di sisi salah satu pemain kosong.
                10.Biji yang tersisa di sisi lawan dipindahkan ke Mancala miliknya.
                11.Pemain menghitung biji di masing-masing Mancala.
                12.Pemain dengan jumlah biji terbanyak adalah pemenang.""");

        rulesTextArea.setFont(new Font("Berlin Sans FB", Font.PLAIN, 25));
        rulesTextArea.setBorder(null); // Menghilangkan border
        rulesTextArea.setOpaque(false); // Membuat background transparan
        rulesTextArea.setEditable(false); // Membuat teks tidak dapat diubah
        rulesTextArea.setForeground(Color.WHITE); // Warna teks
        rulesTextArea.setBounds(150, 150, 1010, 504); // Atur posisi dan ukuran
        add(rulesTextArea);

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
        setComponentZOrder(rulesTextArea, 0);
        setComponentZOrder(backLabel, 1);
        setComponentZOrder(backgroundLabel, 2);
    }
}
