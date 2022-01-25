package ca.jacksonbarker;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Arrays;
import java.util.Random;
import javax.swing.*;
import javax.swing.border.Border;

public class Main extends JFrame implements KeyListener {

    public Main() {addKeyListener(this);}
    public static final Main game = new Main();

    public static JLabel[][] cells = new JLabel[6][5];
    public static JLabel[] keys = new JLabel[28];
    public static String[] keyLabels = new String[]{""};

    public static int activeLine = 0;

    public static final int[] wordLetters = new int[26];
    public static int[] inputLetters = new int[26];

    //public static final String word = WordList.wordList(false, 'a')[new Random().nextInt(WordList.wordList(false, 'a').length)];
    public static String word = "hello";

    public static void main(String[] args) {
        JPanel grid = new JPanel();
        JPanel keyboard1 = new JPanel();
        JPanel keyboard2 = new JPanel();
        
        game.setSize(500, 600);
        game.setTitle("WORDLE");
        game.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        grid.setLayout(new GridLayout(0, 5));
        keyboard1.setLayout(new GridLayout(0, 10));
        keyboard2.setLayout(new GridLayout(0, 9));
        Border border = BorderFactory.createLineBorder(Color.gray, 2);
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 5; j++) {
                cells[i][j] = new JLabel("", SwingConstants.CENTER);
                cells[i][j].setFont(new Font("Arial Black", 0, 64));
                cells[i][j].setOpaque(true);
                cells[i][j].setBorder(border);
                grid.add(cells[i][j]);
            }
        }

        for (int i = 0; i < 28; i++) {
            if (i > 9) {
                keys[i] = new JLabel("", SwingConstants.CENTER);
                keys[i].setFont(new Font("Arial", 0, 32));
                keys[i].setOpaque(true);
                keys[i].setBorder(border);
                keyboard1.add(keys[i]);
            } else {
                keys[i] = new JLabel("", SwingConstants.CENTER);
                keys[i].setFont(new Font("Arial", 0, 32));
                keys[i].setOpaque(true);
                keys[i].setBorder(border);
                keyboard1.add(keys[i]);
            }
        }

        game.add(grid);
        game.setVisible(true);

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < wordLetters.length; j++) {
                if (word.charAt(i) == j + 97) {
                    wordLetters[j] += 1;
                }
            }
        }
    }

    public static boolean isValidWord(String word) {
        if (word.length() != 5) {
            return false;
        }

        for (int i = 0; i < word.length(); i++) {
            if (!java.lang.Character.isLetter(word.charAt(i))) {
                return false;
            }
        }

        for (int i = 0; i < WordList.wordList(true, word.charAt(0)).length; i++) {
            if (word.equals(WordList.wordList(true, word.charAt(0))[i])) {
                return true;
            }
        }

        return false;
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {
        switch (keyEvent.getKeyCode()) {
            case KeyEvent.VK_A:
                input('a');
                break;
            case KeyEvent.VK_B:
                input('b');
                break;
            case KeyEvent.VK_C:
                input('c');
                break;
            case KeyEvent.VK_D:
                input('d');
                break;
            case KeyEvent.VK_E:
                input('e');
                break;
            case KeyEvent.VK_F:
                input('f');
                break;
            case KeyEvent.VK_G:
                input('g');
                break;
            case KeyEvent.VK_H:
                input('h');
                break;
            case KeyEvent.VK_I:
                input('i');
                break;
            case KeyEvent.VK_J:
                input('j');
                break;
            case KeyEvent.VK_K:
                input('k');
                break;
            case KeyEvent.VK_L:
                input('l');
                break;
            case KeyEvent.VK_M:
                input('m');
                break;
            case KeyEvent.VK_N:
                input('n');
                break;
            case KeyEvent.VK_O:
                input('o');
                break;
            case KeyEvent.VK_P:
                input('p');
                break;
            case KeyEvent.VK_Q:
                input('q');
                break;
            case KeyEvent.VK_R:
                input('r');
                break;
            case KeyEvent.VK_S:
                input('s');
                break;
            case KeyEvent.VK_T:
                input('t');
                break;
            case KeyEvent.VK_U:
                input('u');
                break;
            case KeyEvent.VK_V:
                input('v');
                break;
            case KeyEvent.VK_W:
                input('w');
                break;
            case KeyEvent.VK_X:
                input('x');
                break;
            case KeyEvent.VK_Y:
                input('y');
                break;
            case KeyEvent.VK_Z:
                input('z');
                break;
            case KeyEvent.VK_BACK_SPACE:
                backspace();
                break;
            case KeyEvent.VK_ENTER:
                System.arraycopy(wordLetters, 0, inputLetters, 0, 26);
                if (isValidWord((cells[activeLine][0].getText() + cells[activeLine][1].getText() + cells[activeLine][2].getText() + cells[activeLine][3].getText() + cells[activeLine][4].getText()).toLowerCase())) {
                    for (int i = 0; i < cells[0].length; i++) {
                        cells[activeLine][i].setBackground(Color.lightGray);
                        if (Character.toLowerCase(cells[activeLine][i].getText().charAt(0)) == word.charAt(i)) {
                            cells[activeLine][i].setBackground(Color.green);
                            inputLetters[Character.toLowerCase(cells[activeLine][i].getText().charAt(0)) - 97] -= 1;
                        }
                    }
                    for (int i = 0; i < cells[0].length; i++) {
                        if (inputLetters[Character.toLowerCase(cells[activeLine][i].getText().charAt(0)) - 97] > 0 && cells[activeLine][i].getBackground() != Color.green) {
                            cells[activeLine][i].setBackground(Color.yellow);
                            inputLetters[Character.toLowerCase(cells[activeLine][i].getText().charAt(0)) - 97] -= 1;
                        }
                    }
                    if ((cells[activeLine][0].getText() + cells[activeLine][1].getText() + cells[activeLine][2].getText() + cells[activeLine][3].getText() + cells[activeLine][4].getText()).equals(word)) {
                        JOptionPane.showMessageDialog(game, "You guessed the Wordle!");
                        System.exit(0);
                    }
                    activeLine += 1;
                } else {
                    JOptionPane.showMessageDialog(game, "Not in word list.");
                }
                if (activeLine > 5) {
                    JOptionPane.showMessageDialog(game, "The Wordle was: " + word);
                    System.exit(0);
                }
                break;
        }
    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {}

    @Override
    public void keyTyped(KeyEvent keyEvent) {}

    public void input(char inputChar) {
        inputChar = Character.toUpperCase(inputChar);
        if (cells[activeLine][0].getText().equals("")) {
            cells[activeLine][0].setText(String.valueOf(inputChar));
        } else if (cells[activeLine][1].getText().equals("")) {
            cells[activeLine][1].setText(String.valueOf(inputChar));
        } else if (cells[activeLine][2].getText().equals("")) {
            cells[activeLine][2].setText(String.valueOf(inputChar));
        } else if (cells[activeLine][3].getText().equals("")) {
            cells[activeLine][3].setText(String.valueOf(inputChar));
        } else if (cells[activeLine][4].getText().equals("")) {
            cells[activeLine][4].setText(String.valueOf(inputChar));
        }
    }

    public void backspace() {
        if (!cells[activeLine][4].getText().equals("")) {
            cells[activeLine][4].setText("");
        } else if (!cells[activeLine][3].getText().equals("")) {
            cells[activeLine][3].setText("");
        } else if (!cells[activeLine][2].getText().equals("")) {
            cells[activeLine][2].setText("");
        } else if (!cells[activeLine][1].getText().equals("")) {
            cells[activeLine][1].setText("");
        } else if (!cells[activeLine][0].getText().equals("")) {
            cells[activeLine][0].setText("");
        }
    }
}
