package ca.jacksonbarker;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Arrays;
import java.util.Random;
import javax.swing.*;

public class Main extends JFrame implements KeyListener {

    public Main() {addKeyListener(this);}

    public static JLabel[][] grid = new JLabel[6][5];

    public static int activeLine = 0;

    public static final int[] wordLetters = new int[26];
    public static int[] inputLetters = new int[26];

    //public static final String word = WordList.wordList()[new Random().nextInt(WordList.wordList().length)];
    public static String word = "hello";

    public static void main(String[] args) {
        Main game = new Main();
        game.setSize(500, 600);
        game.setTitle("WORDLE");
        game.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        game.setLayout(new GridLayout(0, 5));
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 5; j++) {
                grid[i][j] = new JLabel("", SwingConstants.CENTER);
                grid[i][j].setFont(new Font("Comic Sans MS", Font.BOLD, 64));
                grid[i][j].setOpaque(true);
                game.add(grid[i][j]);
            }
        }
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

        for (int i = 0; i < WordList.wordList().length; i++) {
            if (word.equals(WordList.wordList()[i])) {
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
                if (isValidWord(grid[activeLine][0].getText() + grid[activeLine][1].getText() + grid[activeLine][2].getText() + grid[activeLine][3].getText() + grid[activeLine][4].getText())) {
                    for (int i = 0; i < grid[0].length; i++) {
                        if (grid[activeLine][i].getText().charAt(0) == word.charAt(i)) {
                            grid[activeLine][i].setBackground(Color.green);
                        } else if (inputLetters[grid[activeLine][i].getText().charAt(0) - 97] > 0) {
                            grid[activeLine][i].setBackground(Color.yellow);
                            inputLetters[grid[activeLine][i].getText().charAt(0) - 97] -= 1;
                        }
                        else {
                            grid[activeLine][i].setBackground(Color.lightGray);
                        }
                    }
                    activeLine += 1;
                }
                break;
        }
    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {}

    @Override
    public void keyTyped(KeyEvent keyEvent) {}

    public void input(char inputChar) {
        if (grid[activeLine][0].getText().equals("")) {
            grid[activeLine][0].setText(String.valueOf(inputChar));
        } else if (grid[activeLine][1].getText().equals("")) {
            grid[activeLine][1].setText(String.valueOf(inputChar));
        } else if (grid[activeLine][2].getText().equals("")) {
            grid[activeLine][2].setText(String.valueOf(inputChar));
        } else if (grid[activeLine][3].getText().equals("")) {
            grid[activeLine][3].setText(String.valueOf(inputChar));
        } else if (grid[activeLine][4].getText().equals("")) {
            grid[activeLine][4].setText(String.valueOf(inputChar));
        }
    }

    public void backspace() {
        if (!grid[activeLine][4].getText().equals("")) {
            grid[activeLine][4].setText("");
        } else if (!grid[activeLine][3].getText().equals("")) {
            grid[activeLine][3].setText("");
        } else if (!grid[activeLine][2].getText().equals("")) {
            grid[activeLine][2].setText("");
        } else if (!grid[activeLine][1].getText().equals("")) {
            grid[activeLine][1].setText("");
        } else if (!grid[activeLine][0].getText().equals("")) {
            grid[activeLine][0].setText("");
        }
    }
}
