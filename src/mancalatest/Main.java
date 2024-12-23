package mancalatest;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainMenu mainMenu = new MainMenu();
            mainMenu.setVisible(true);
//            MancalaGame game = new MancalaGame() {
//                @Override
//                public void mousePressed(java.awt.event.MouseEvent e) {}
//
//                @Override
//                public void mouseReleased(java.awt.event.MouseEvent e) {}
//
//                @Override
//                public void mouseEntered(java.awt.event.MouseEvent e) {}
//
//                @Override
//                public void mouseExited(java.awt.event.MouseEvent e) {}
//            };
//            game.setVisible(true);
        });
    }
}
