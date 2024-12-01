import javax.swing.*;
import java.awt.event.ActionEvent;

public class TicTacToeGame {
    private final int MOVES_FOR_WIN = 5;
    private final int MOVES_FOR_TIE = 7;

    private TicTacToeBoard ticTacToeBoard;
    private TicTacToeFrame ticTacToeFrame;
    private String player = "X";
    private boolean playing = true;
    private int moveCnt;

    public TicTacToeGame() {
        ticTacToeBoard = new TicTacToeBoard();
        ticTacToeFrame = new TicTacToeFrame();
        initializeGame();
    }

    private void initializeGame() {
        ticTacToeBoard.clearBoard();
        for (int row = 0; row < ticTacToeBoard.getRow(); row++) {
            for (int col = 0; col < ticTacToeBoard.getCol(); col++) {
                ticTacToeFrame.getTiles()[row][col].addActionListener((e) -> tileClicked(e));
            }
        }
    }

    private void tileClicked (ActionEvent e) {
        TicTacToeTile clickedTile = (TicTacToeTile) e.getSource();
        int row = clickedTile.getRow();
        int col = clickedTile.getCol();

        clickedTile.setText(player);
        clickedTile.setEnabled(false);

        ticTacToeBoard.setBoard(row, col, player);

        moveCnt++;

        if(moveCnt >= MOVES_FOR_WIN) {
            if(ticTacToeBoard.isWin(player)) {
                JOptionPane.showMessageDialog(ticTacToeFrame,"Player " + player + " wins!");
                playing = false;
            }
        }

        if(playing && moveCnt >= MOVES_FOR_TIE) {
            if(ticTacToeBoard.isTie()) {
                JOptionPane.showMessageDialog(ticTacToeFrame,"It's a Tie!");
                playing = false;
            }
        }

        // Switch player
        if(player.equals("X")) {
            player = "O";
        }
        else {
            player = "X";
        }

        if (!playing) {
            int result = JOptionPane.showConfirmDialog(ticTacToeFrame, "Would you like to play again?",
                    "Play Again?", JOptionPane.YES_NO_OPTION);
            if (result == JOptionPane.YES_OPTION) {
                restartGame();  // Restart the game if player wants to play again
            } else {
                System.exit(0);  // Exit the application if player chooses not to play again
            }
        }
    }

    private void restartGame() {
        ticTacToeBoard.clearBoard();
        ticTacToeFrame.resetTiles();
        moveCnt = 0;
        player = "X";
        playing = true;
    }
}