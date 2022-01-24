package ca.jacksonbarker;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Scanner;
import java.util.Random;
import javax.swing.*;

public class Main extends JFrame implements KeyListener {

    public static void main(String[] args) {
        int gameWidth = 5;
        int gameHeight = 6;

        JLabel[][] grid = new JLabel[gameHeight][gameWidth];

        JFrame game = new JFrame();
        game.setSize(gameWidth * 100, gameHeight * 100);
        game.setTitle("WORDLE");
        game.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        game.setLayout(new GridLayout(0, gameWidth));
        for (int i = 0; i < gameHeight; i++) {
            for (int j = 0; j < gameWidth; j++) {
                grid[i][j] = new JLabel("", SwingConstants.CENTER);
                grid[i][j].setFont(new Font("Comic Sans MS", Font.BOLD, 64));
                grid[i][j].setOpaque(true);
                game.add(grid[i][j]);
            }
        }
        game.setVisible(true);

        Scanner scnr = new Scanner(System.in);
        String[] guesses = new String[gameHeight];
        //String word = WordList.wordList()[new Random().nextInt(WordList.wordList().length)];
        String word = "hello";
        String input;
        int[] wordLetters = new int[26];
        int[] inputLetters = new int[26];

        for (int i = 0; i < gameWidth; i++) {
            for (int j = 0; j < wordLetters.length; j++) {
                if (word.charAt(i) == j + 97) {
                    wordLetters[j] += 1;
                }
            }
        }

        while (guesses[gameHeight - 1] == null) {
            input = "";

            while (!isValidWord(input, gameWidth)) {
                System.out.println("Enter a 5 letter word:");
                input = scnr.next().toLowerCase();
            }

            for (int i = 0; i < gameHeight; i++) {
                if (guesses[i] == null) {
                    guesses[i] = input;
                    input = null;
                }
            }

            for (int i = 0; i < gameHeight; i++) {
                if (guesses[i] != null) {
                    for (int j = 0; j < gameWidth; j++) {
                        grid[i][j].setText(guesses[i].charAt(j) + "");
                        if (word.charAt(j) == guesses[i].charAt(j)) {
                            grid[i][j].setBackground(Color.green);
                        } else {
                            grid[i][j].setBackground(Color.gray);
                        }
                    }
                }
            }

            for (int i = 0; i < gameHeight; i++) {
                if (guesses[i] != null && guesses[i].equals(word)) {
                    JOptionPane.showMessageDialog(game, "You guessed the Wordle!");
                    System.exit(0);
                }
            }
        }

        JOptionPane.showMessageDialog(game, "The Wordle was: " + word);
        System.exit(0);
    }

    public static boolean isValidWord(String word, int gameWidth) {
        if (word.length() != gameWidth) {
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
                break;
            case KeyEvent.VK_B:
                break;
            case KeyEvent.VK_C:
                break;
            case KeyEvent.VK_D:
                break;
            case KeyEvent.VK_E:
                break;
            case KeyEvent.VK_F:
                break;
            case KeyEvent.VK_G:
                break;
            case KeyEvent.VK_H:
                break;
            case KeyEvent.VK_I:
                break;
            case KeyEvent.VK_J:
                break;
            case KeyEvent.VK_K:
                break;
            case KeyEvent.VK_L:
                break;
            case KeyEvent.VK_M:
                break;
            case KeyEvent.VK_N:
                break;
            case KeyEvent.VK_O:
                break;
            case KeyEvent.VK_P:
                break;
            case KeyEvent.VK_Q:
                break;
            case KeyEvent.VK_R:
                break;
            case KeyEvent.VK_S:
                break;
            case KeyEvent.VK_T:
                break;
            case KeyEvent.VK_U:
                break;
            case KeyEvent.VK_V:
                break;
            case KeyEvent.VK_W:
                break;
            case KeyEvent.VK_X:
                break;
            case KeyEvent.VK_Y:
                break;
            case KeyEvent.VK_Z:
                break;
            case KeyEvent.VK_BACK_SPACE:
                break;
            case KeyEvent.VK_ENTER:
                break;
        }
    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {}

    @Override
    public void keyTyped(KeyEvent keyEvent) {}
}
