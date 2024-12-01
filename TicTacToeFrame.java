import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class TicTacToeFrame extends JFrame {
    private final int ROW = 3;
    private final int COL = 3;

    private JPanel mainPnl;
    private JPanel boardPnl;
    private JPanel controlPnl;

    private TicTacToeTile[][] tiles;
    private JButton quitBtn;

    public TicTacToeFrame() {
        setTitle("Tic Tac Toe");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 800);

        // get screen dimensions
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int screenHeight = screenSize.height;
        int screenWidth = screenSize.width;
        // center frame in screen
        setLocation((screenWidth - getWidth()) / 2, (screenHeight - getHeight()) / 2);

        mainPnl = new JPanel();
        mainPnl.setLayout(new BorderLayout());

        createBoardPanel();
        mainPnl.add(boardPnl, BorderLayout.CENTER);

        createControlPanel();
        mainPnl.add(controlPnl, BorderLayout.SOUTH);

        add(mainPnl);

        setVisible(true);
    }

    public TicTacToeTile[][] getTiles() {
        return tiles;
    }

    public void resetTiles() {
        for(int row=0; row < ROW; row++) {
            for(int col=0; col < COL; col++) {
                tiles[row][col].setText(" ");
                tiles[row][col].setEnabled(true);
            }
        }
    }

    private void createBoardPanel() {
        boardPnl = new JPanel();
        boardPnl.setLayout(new GridLayout(3, 3));
        tiles = new TicTacToeTile[ROW][COL];
        for (int row = 0; row < ROW; row++) {
            for (int col = 0; col < COL; col++) {
                tiles[row][col] = new TicTacToeTile(row, col);
                tiles[row][col].setFont(new Font("Arial", Font.BOLD, 50));
                boardPnl.add(tiles[row][col]);
            }
        }
    }

    private void createControlPanel() {
        controlPnl = new JPanel();

        quitBtn = new JButton("Quit!");
        quitBtn.addActionListener((ActionEvent ae) -> System.exit(0));

        controlPnl.add(quitBtn);
    }
}