import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Sudoku {
    class Tile extends JButton {
        int r;
        int c;
        Tile(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    int boardWidth = 600;
    int boardHeight = 650;

    String[] puzzle = {
            "--74916-5",
            "2---6-3-9",
            "-----7-1-",
            "-586----4",
            "--3----9-",
            "--62--187",
            "9-4-7---2",
            "67-83----",
            "81--45---"
    };

    String[] solution = {
            "387491625",
            "241568379",
            "569327418",
            "758619234",
            "123784596",
            "496253187",
            "934176852",
            "675832941",
            "812945763"
    };

    JFrame frame = new JFrame("🌸 Sudoku Pink 🌸");
    JLabel textLabel = new JLabel();
    JPanel textPanel = new JPanel();
    JPanel boardPanel = new JPanel();
    JPanel buttonsPanel = new JPanel();

    JButton numSelected = null;
    int errors = 0;

    // 🌸 tons de rosa suaves
    Color pinkBackground = new Color(255, 240, 245);
    Color softPink = new Color(255, 182, 193);
    Color lightPink = new Color(255, 192, 203);
    Color highlightPink = new Color(255, 105, 180);

    Sudoku() {
        frame.setSize(boardWidth, boardHeight);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout());
        frame.getContentPane().setBackground(pinkBackground);

        textLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 28));
        textLabel.setHorizontalAlignment(JLabel.CENTER);
        textLabel.setText(" Sudoku — Erros: 0 ");
        textLabel.setForeground(highlightPink);
        textPanel.setBackground(pinkBackground);
        textPanel.add(textLabel);
        frame.add(textPanel, BorderLayout.NORTH);

        boardPanel.setLayout(new GridLayout(9, 9));
        boardPanel.setBackground(pinkBackground);
        setupTiles();
        frame.add(boardPanel, BorderLayout.CENTER);

        buttonsPanel.setLayout(new GridLayout(1, 9));
        buttonsPanel.setBackground(pinkBackground);
        setupButtons();
        frame.add(buttonsPanel, BorderLayout.SOUTH);

        frame.setVisible(true);
    }

    void setupTiles() {
        for (int r = 0; r < 9; r++) {
            for (int c = 0; c < 9; c++) {
                Tile tile = new Tile(r, c);
                char tileChar = puzzle[r].charAt(c);
                tile.setFont(new Font("Comic Sans MS", Font.BOLD, 20));

                if (tileChar != '-') {
                    tile.setText(String.valueOf(tileChar));
                    tile.setBackground(softPink);
                    tile.setForeground(Color.DARK_GRAY);
                } else {
                    tile.setText("");
                    tile.setBackground(Color.white);
                }

                // bordas destacadas
                if ((r == 2 && c == 2) || (r == 2 && c == 5) || (r == 5 && c == 2) || (r == 5 && c == 5)) {
                    tile.setBorder(BorderFactory.createMatteBorder(1, 1, 4, 4, highlightPink));
                } else if (r == 2 || r == 5) {
                    tile.setBorder(BorderFactory.createMatteBorder(1, 1, 4, 1, highlightPink));
                } else if (c == 2 || c == 5) {
                    tile.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 4, highlightPink));
                } else {
                    tile.setBorder(BorderFactory.createLineBorder(highlightPink));
                }

                tile.setFocusable(false);
                boardPanel.add(tile);

                tile.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        Tile tile = (Tile) e.getSource();
                        int r = tile.r;
                        int c = tile.c;
                        if (numSelected != null) {
                            if (!tile.getText().equals("")) return;

                            String numSelectedText = numSelected.getText();
                            String tileSolution = String.valueOf(solution[r].charAt(c));
                            if (tileSolution.equals(numSelectedText)) {
                                tile.setText(numSelectedText);
                                tile.setForeground(highlightPink);
                            } else {
                                errors++;
                                textLabel.setText(" Sudoku — Erros: " + errors + " ");
                                tile.setBackground(lightPink);
                            }
                        }
                    }
                });
            }
        }
    }

    void setupButtons() {
        for (int i = 1; i < 10; i++) {
            JButton button = new JButton(String.valueOf(i));
            button.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
            button.setFocusable(false);
            button.setBackground(Color.white);
            button.setForeground(highlightPink);
            button.setBorder(BorderFactory.createLineBorder(highlightPink, 2));
            buttonsPanel.add(button);

            button.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    JButton btn = (JButton) e.getSource();
                    if (numSelected != null) {
                        numSelected.setBackground(Color.white);
                    }
                    numSelected = btn;
                    numSelected.setBackground(softPink);
                }
            });
        }
    }

    public static void main(String[] args) {
        new Sudoku();
    }
}
