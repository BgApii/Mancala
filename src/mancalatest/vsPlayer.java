package mancalatest;

import javax.swing.*;
import java.awt.event.MouseEvent;

public abstract class vsPlayer extends Board {
    private final int[] stones = {4, 4, 4, 4, 4, 4, 0, 4, 4, 4, 4, 4, 4, 0};
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
        // Validasi giliran pemain
        if (isPlayerOneTurn && (pitIndex < 0 || pitIndex > 5) ||
                !isPlayerOneTurn && (pitIndex < 7 || pitIndex > 12)) {
            JOptionPane.showMessageDialog(this, "It's not your turn to play this pit.");
            return;
        }

        final int[] stonesInHand = {stones[pitIndex]};
        if (stonesInHand[0] == 0) {
            JOptionPane.showMessageDialog(this, "This pit is empty. Choose another pit.");
            return;
        }

        stones[pitIndex] = 0; // Kosongkan lubang yang diklik
        updateBoard();

        new SwingWorker<Void, Integer>() {
            private int currentIndex = pitIndex;

            @Override
            protected Void doInBackground() throws Exception {
                while (stonesInHand[0] > 0) {
                    Thread.sleep(500); // Delay 500ms
                    currentIndex = (currentIndex + 1) % stones.length;

                    // Lewati Mancala lawan
                    if ((isPlayerOneTurn && currentIndex == 13) || (!isPlayerOneTurn && currentIndex == 6)) {
                        continue;
                    }

                    stones[currentIndex]++;
                    stonesInHand[0]--;
                    publish(currentIndex); // Perbarui tampilan
                }
                return null;
            }

            @Override
            protected void process(java.util.List<Integer> chunks) {
                // Perbarui tampilan setelah setiap distribusi batu
                for (int index : chunks) {
                    pits[index].setIcon(stoneIcons[stones[index]]);
                    pitLabels[index].setText(String.valueOf(stones[index]));
                }
            }

            @Override
            protected void done() {
                // Periksa aturan tangkapan dan giliran pemain
                checkCaptureAndSwitchTurn(currentIndex);
                updateBoard();
                checkGameEnd();
            }
        }.execute();
    }

    private void checkCaptureAndSwitchTurn(int currentIndex) {
        // Periksa aturan tangkapan
        if (currentIndex != 6 && currentIndex != 13 && stones[currentIndex] == 1 &&
                (isPlayerOneTurn && currentIndex < 6 || !isPlayerOneTurn && currentIndex > 6 && currentIndex < 13)) {
            int oppositeIndex = 12 - currentIndex;
            int capturedStones = stones[oppositeIndex];

            if (capturedStones > 0) {
                stones[oppositeIndex] = 0; // Kosongkan lubang seberang
                stones[currentIndex] = 0; // Kosongkan lubang saat ini

                if (isPlayerOneTurn) {
                    stones[6] += capturedStones + 1;
                } else {
                    stones[13] += capturedStones + 1;
                }
            }
        }

        // Periksa apakah giliran tetap
        if (!(isPlayerOneTurn && currentIndex == 6 || !isPlayerOneTurn && currentIndex == 13)) {
            isPlayerOneTurn = !isPlayerOneTurn; // Tukar giliran
        }
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
}
