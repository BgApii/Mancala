package mancalatest;

import javax.swing.*;
import java.awt.event.MouseEvent;

public abstract class MancalaGame extends GUI {
    private int[] stones = {4, 4, 4, 4, 4, 4, 0, 4, 4, 4, 4, 4, 4, 0};
    private boolean isPlayerOneTurn = true; // true: Player 1, false: Player 2

    @Override
    public void mouseClicked(MouseEvent e) {
        // Identify which pit is clicked
        for (int i = 0; i < pits.length; i++) {
            if (e.getSource() == pits[i]) {
                handlePitClick(i);
                break;
            }
        }
    }

    private void handlePitClick(int pitIndex) {
        // Validate player turn
        if (isPlayerOneTurn && (pitIndex < 0 || pitIndex > 5) ||
                !isPlayerOneTurn && (pitIndex < 7 || pitIndex > 12)) {
            JOptionPane.showMessageDialog(this, "It's not your turn to play this pit.");
            return;
        }

        // Mancala rules: Distribute stones from the clicked pit
        int stonesInHand = stones[pitIndex];
        if (stonesInHand == 0) {
            JOptionPane.showMessageDialog(this, "This pit is empty. Choose another pit.");
            return;
        }

        stones[pitIndex] = 0; // Clear the clicked pit
        int currentIndex = pitIndex;

        while (stonesInHand > 0) {
            currentIndex = (currentIndex + 1) % stones.length;
            if (currentIndex == 7 && !isPlayerOneTurn || currentIndex == 13 && isPlayerOneTurn) {
                continue; // Skip opponent's Mancala
            }

            stones[currentIndex]++;
            stonesInHand--;
        }

        // Check for capture rule
        if (currentIndex != 7 && currentIndex != 13 && stones[currentIndex] == 1 &&
                (isPlayerOneTurn && currentIndex < 6 || !isPlayerOneTurn && currentIndex > 6 && currentIndex < 13)) {
            int oppositeIndex = 12 - currentIndex;
            int capturedStones = stones[oppositeIndex];

            // Only capture if there are stones in the opposite pit
            if (capturedStones > 0) {
                stones[oppositeIndex] = 0; // Clear opposite pit
                stones[currentIndex] = 0; // Clear current pit

//            stones[oppositeIndex] = 0;
//            stones[currentIndex] = 0;

                if (isPlayerOneTurn) {
                    stones[6] += capturedStones + 1;
                } else {
                    stones[13] += capturedStones + 1;
                }
            } else {
            // If the opposite pit is empty, do nothing
            stones[currentIndex] = 1; // Reset the stone in currentIndex
            }
        }

        // Check if the last stone lands in the player's Mancala
        if (!(isPlayerOneTurn && currentIndex == 6 || !isPlayerOneTurn && currentIndex == 13)) {
            isPlayerOneTurn = !isPlayerOneTurn; // Switch turns
        }

        updateBoard();

        // Check for game end
        checkGameEnd();
    }

    private void updateBoard() {
        for (int i = 0; i < pits.length; i++) {
            pits[i].setIcon(stoneIcons[stones[i]]);
            pitLabels[i].setText(String.valueOf(stones[i]));


        }
    }

    private void checkGameEnd() {
        int playerOneSide = 0;
        int playerTwoSide = 0;

        for (int i = 0; i < 6; i++) {
            playerOneSide += stones[i];
        }

        for (int i = 7; i < 13; i++) {
            playerTwoSide += stones[i];
        }

        if (playerOneSide == 0 || playerTwoSide == 0) {
            stones[6] += playerOneSide;
            stones[13] += playerTwoSide;

            for (int i = 0; i < 6; i++) {
                stones[i] = 0;
            }

            for (int i = 7; i < 13; i++) {
                stones[i] = 0;
            }

            updateBoard();

            String winner = stones[6] > stones[13] ? "Player 1 wins!" : stones[13] > stones[6] ? "Player 2 wins!" : "It's a draw!";
            JOptionPane.showMessageDialog(this, winner);
        }
    }

//    public static void main(String[] args) {
//        SwingUtilities.invokeLater(() -> new MancalaGame() {
//            @Override
//            public void mousePressed(MouseEvent e) {
//
//            }
//
//            @Override
//            public void mouseReleased(MouseEvent e) {
//
//            }
//
//            @Override
//            public void mouseEntered(MouseEvent e) {
//
//            }
//
//            @Override
//            public void mouseExited(MouseEvent e) {
//
//            }
//        }.setVisible(true));
//    }
}
