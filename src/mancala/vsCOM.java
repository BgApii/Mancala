package mancala;

import javax.swing.*;
import java.awt.event.MouseEvent;

public abstract class vsCOM extends Board {
    private final int[] stones = {4, 4, 4, 4, 4, 4, 0, 4, 4, 4, 4, 4, 4, 0};
    private boolean isPlayerOneTurn = true; // true: Player 1, false: COM

    @Override
    public void mouseClicked(MouseEvent e) {
        // Hanya jalankan jika giliran Player 1
        if (!isPlayerOneTurn) return;

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
        if (pitIndex == 6 || pitIndex == 13) {
            JOptionPane.showMessageDialog(this, "You can't play Mancala.");
            return;
        }

        if (isPlayerOneTurn && (pitIndex < 0 || pitIndex > 6)) {
            JOptionPane.showMessageDialog(this, "It's not your turn to play this pit.");
            return;
        }

        // Ambil jumlah batu dari lubang yang dipilih
        final int[] stonesInHand = {stones[pitIndex]};
        if (stonesInHand[0] == 0) {
            JOptionPane.showMessageDialog(this, "This pit is empty. Choose another pit.");
            return;
        }

        stones[pitIndex] = 0; // Kosongkan lubang yang dipilih
        updateBoard();

        new SwingWorker<Void, Integer>() {
            private int currentIndex = pitIndex;

            @Override
            protected Void doInBackground() throws Exception {
                while (stonesInHand[0] > 0) {
                    Thread.sleep(400); // Delay untuk animasi

                    currentIndex = (currentIndex + 1) % stones.length;

                    // Lewati Mancala lawan
                    if ((isPlayerOneTurn && currentIndex == 13) || (!isPlayerOneTurn && currentIndex == 6)) {
                        continue;
                    }

                    stones[currentIndex]++;
                    stonesInHand[0]--;
                    publish(currentIndex); // Kirim indeks untuk memperbarui tampilan
                }
                return null;
            }

            @Override
            protected void process(java.util.List<Integer> chunks) {
                for (int index : chunks) {
                    pits[index].setIcon(stoneIcons[stones[index]]);
                    pitLabels[index].setText(String.valueOf(stones[index]));
                }
            }

            @Override
            protected void done() {
                checkCaptureAndSwitchTurn(currentIndex);
                updateBoard();

                // Cek akhir permainan atau giliran COM
                if (!checkGameEnd() && !isPlayerOneTurn) {
                    handleComMove();
                }
            }
        }.execute();
    }

    private void handleComMove() {
        Timer comTimer = new Timer(1000, e -> {
            int bestPit = -1;
            int maxScore = Integer.MIN_VALUE;

            for (int i = 7; i < 13; i++) {
                if (stones[i] > 0) { // Hanya pertimbangkan lubang yang valid
                    int[] tempStones = stones.clone();
                    int score = minimax(tempStones, i, 7, false); // Mulai Minimax dengan depth 7 dan player lawan

                    // Prioritaskan gerakan ke Mancala
                    if (canReachMancala(i)) {
                        score += 100; // Skor tinggi untuk mencapai Mancala
                    }

                    if (score > maxScore) {
                        maxScore = score;
                        bestPit = i;
                    }
                }
            }

            if (bestPit != -1) {
                handlePitClick(bestPit);
            }
        });

        comTimer.setRepeats(false);
        comTimer.start();
    }

    private int minimax(int[] board, int pitIndex, int depth, boolean isMaximizing) {
        if (depth == 0 || isGameOver(board)) {
            return evaluateBoard(board);
        }

        if (isMaximizing) {
            int maxEval = Integer.MIN_VALUE;
            for (int i = 7; i < 13; i++) {
                if (board[i] > 0) {
                    int[] tempBoard = board.clone();
                    simulateMove(tempBoard, pitIndex, true);
                    int eval = minimax(tempBoard, i, depth - 1, false);
                    maxEval = Math.max(maxEval, eval);
                }
            }
            return maxEval;

        } else {
            int minEval = Integer.MAX_VALUE;
            for (int i = 0; i < 6; i++) {
                if (board[i] > 0) {
                    int[] tempBoard = board.clone();
                    simulateMove(tempBoard, pitIndex, false);
                    int eval = minimax(tempBoard, i, depth - 1, true);
                    minEval = Math.min(minEval, eval);
                }
            }
            return minEval;
        }
    }

    private void simulateMove(int[] board, int pitIndex, boolean isMaximizing) {
        int stonesInHand = board[pitIndex];
        board[pitIndex] = 0;
        int currentIndex = pitIndex;

        while (stonesInHand > 0) {
            currentIndex = (currentIndex + 1) % board.length;

            // Lewati Mancala lawan
            if ((isMaximizing && currentIndex == 13) || (!isMaximizing && currentIndex == 6)) {
                continue;
            }

            board[currentIndex]++;
            stonesInHand--;
        }

        // Capture Rule
        if (currentIndex >= 0 && currentIndex < 6 && isMaximizing || currentIndex >= 7 && currentIndex < 13 && !isMaximizing) {
            int oppositeIndex = 12 - currentIndex;
            if (board[currentIndex] == 1 && board[oppositeIndex] > 0) {
                board[isMaximizing ? 6 : 13] += board[currentIndex] + board[oppositeIndex];
                board[currentIndex] = 0;
                board[oppositeIndex] = 0;
            }
        }
    }

    private boolean isGameOver(int[] board) {
        int playerOneSide = 0, playerTwoSide = 0;

        for (int i = 0; i < 6; i++) {
            playerOneSide += board[i];
        }
        for (int i = 7; i < 13; i++) {
            playerTwoSide += board[i];
        }

        return playerOneSide == 0 || playerTwoSide == 0;
    }

    private int evaluateBoard(int[] board) {
        int score = board[13] - board[6]; // Evaluasi dasar

        // Prioritaskan gerakan yang mencapai Mancala atau menangkap batu lawan
        for (int i = 7; i < 13; i++) {
            if (board[i] > 0 && canReachMancala(i)) {
                score += 50; // Tambahkan skor untuk mencapai Mancala
            }
        }

        // Pertimbangkan blokir lawan
        for (int i = 0; i < 6; i++) {
            if (board[i] == 1) {
                score -= 30; // Kurangi skor jika lawan dapat memanfaatkan lubang ini
            }
        }

        return score;
    }

    private boolean canReachMancala(int pitIndex) {
        return (pitIndex + stones[pitIndex]) % 14 == (isPlayerOneTurn ? 6 : 13);
    }

    private void updateBoard() {
        for (int i = 0; i < pits.length; i++) {
            pits[i].setIcon(stoneIcons[stones[i]]);
            pitLabels[i].setText(String.valueOf(stones[i]));
        }
        if (isPlayerOneTurn){
            Board.turn.setText("Player 1");
        } else {
            Board.turn.setText("COM");
        }
    }

    private boolean checkGameEnd() {
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

            String winner = stones[6] > stones[13] ? "Player 1 wins!" : stones[13] > stones[6] ? "COM wins!" : "It's a draw!";
            JOptionPane.showMessageDialog(this, winner);
            return true; // Permainan berakhir
        }
        return false; // Permainan belum berakhir
    }

    private void checkCaptureAndSwitchTurn(int currentIndex) {
        if (currentIndex != 6 && currentIndex != 13 && stones[currentIndex] == 1 &&
                (isPlayerOneTurn && currentIndex < 6 || !isPlayerOneTurn && currentIndex > 6 && currentIndex < 13)) {
            int oppositeIndex = 12 - currentIndex;
            int capturedStones = stones[oppositeIndex];

            if (capturedStones > 0) {
                stones[oppositeIndex] = 0; // Kosongkan lubang lawan
                stones[currentIndex] = 0; // Kosongkan lubang pemain

                if (isPlayerOneTurn) {
                    stones[6] += capturedStones + 1;
                } else {
                    stones[13] += capturedStones + 1;
                }
            }
        }

        if (!(isPlayerOneTurn && currentIndex == 6 || !isPlayerOneTurn && currentIndex == 13)) {
            isPlayerOneTurn = !isPlayerOneTurn; // Ganti giliran
        }
    }
}
