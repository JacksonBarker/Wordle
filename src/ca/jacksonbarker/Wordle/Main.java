package ca.jacksonbarker.Wordle;

import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import javax.swing.*;
import javax.swing.border.Border;

import static ca.jacksonbarker.Wordle.WordList.wordList;

public class Main extends JFrame implements KeyListener {

    public Main() {addKeyListener(this);}
    public static final Main game = new Main();

    public static JLabel[][] cells = new JLabel[6][5];
    public static JLabel[] keys = new JLabel[28];

    public static final String wordle = WordList.wordList(false, 'a')[new Random().nextInt(WordList.wordList(false, 'a').length)]; //Generates Random Wordle From Wordlist

    public static final int[] wordleLetters = new int[26];
    public static int[] inputLetters = new int[26];

    public static int activeLine = 0;
    public static int gameTime = 0; //initialises gameTime for Timer Thread
    //Creates Second Thread For Game Timer
    public static Thread asyncTimer = new Thread(){
        public void run(){
            while (true) {
                try {
                    Thread.sleep(1000); //Pause Thread For 1 second
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                gameTime += 1;
            }
        }
    };

    public static void main(String[] args) {
        asyncTimer.start();
        System.arraycopy(wordAsArray(wordle), 0, wordleLetters, 0, 26);
        initGame();
    }

    public static void initGame() {
        Border border = BorderFactory.createLineBorder(Color.gray, 2);
        String[] keyLabels = new String[]{"Q","W","E","R","T","Y","U","I","O","P","A","S","D","F","G","H","J","K","L","⏎","Z","X","C","V","B","N","M","⌫"};

        JPanel container = new JPanel();
        JPanel grid = new JPanel();
        JPanel keyboard1 = new JPanel();
        JPanel keyboard2 = new JPanel();
        JPanel keyboard3 = new JPanel();

        game.getContentPane().setLayout(new GridBagLayout());
        grid.setLayout(null);

        game.setSize(500, 700);
        game.setResizable(false);
        grid.setPreferredSize(new Dimension(400, 480));
        grid.setSize(new Dimension(400, 480));
        keyboard1.setPreferredSize(new Dimension(380, 50));
        keyboard2.setPreferredSize(new Dimension(342, 50));
        keyboard3.setPreferredSize(new Dimension(342, 50));

        game.setLayout(new GridLayout(0, 1));
        grid.setLayout(new GridLayout(0, 5));
        keyboard1.setLayout(new GridLayout(0, 10));
        keyboard2.setLayout(new GridLayout(0, 9));
        keyboard3.setLayout(new GridLayout(0, 9));

        container.setOpaque(true);
        container.setBackground(Color.white);

        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 5; j++) {
                cells[i][j] = new JLabel("", SwingConstants.CENTER);
                cells[i][j].setFont(new Font("Arial Black", 0, 48));
                cells[i][j].setOpaque(true);
                cells[i][j].setBackground(Color.white);
                cells[i][j].setBorder(border);
                grid.add(cells[i][j]);
            }
        }
        //Draws OnScreen Keyboard
        for (int i = 0; i < 28; i++) { //Draws 28 Boxes With Displayed Character For Keyboard
            keys[i] = new JLabel(keyLabels[i], SwingConstants.CENTER);
            keys[i].addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    onMouseClicked(e); //Adds MouseListener to Keyboxes so They May be Clicked for input
                }
            });

            keys[i].setOpaque(true);
            keys[i].setBorder(border);
            keys[i].setBackground(Color.white);
            if (i == 19 || i == 27) {
                keys[i].setFont(new Font("Segoe UI Emoji", 0, 24)); //Font for Backspace and Return Character
                keyboard3.add(keys[i]);
            } else if (i < 10) {
                keys[i].setFont(new Font("Arial Black", 0, 32));
                keyboard1.add(keys[i]);
            } else if (i < 19){
                keys[i].setFont(new Font("Arial Black", 0, 32));
                keyboard2.add(keys[i]);
            } else {
                keys[i].setFont(new Font("Arial Black", 0, 32));
                keyboard3.add(keys[i]);
            }
        }

        game.add(container);
        container.add(grid);
        container.add(keyboard1);
        container.add(keyboard2);
        container.add(keyboard3);

        game.setTitle("WORDLE"); //Sets Name of Game Window to Wordle
        game.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        game.setVisible(true);
    }
    //Counts Number Each Letter in The Wordle
    public static int[] wordAsArray(String word) {
        int[] wordLetters = new int[26];
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < wordLetters.length; j++) {
                if (word.charAt(i) == j + 65) {
                    wordLetters[j] += 1;
                }
            }
        }
        return wordLetters;
    }
    //Checks if The Word Input by Player is Valid
    public static boolean isValidWord(String word) {
        if (word.length() != 5) { //Checks if Input is 5 Letter Word
            return false;
        }

        for (int i = 0; i < word.length(); i++) {
            if (!java.lang.Character.isLetter(word.charAt(i))) { //Checks if Input has Numbers
                return false;
            }
        }

        for (int i = 0; i < wordList(true, word.charAt(0)).length; i++) {
            if (word.equals(WordList.wordList(true, word.charAt(0))[i])) { //Checks if Input is on Wordlist Based on First Letter of Word (word with first letter 'A' only checks list of 'A' words)
                return true;
            }
        }

        return false;
    }

    public static void enter() {
        System.arraycopy(wordleLetters, 0, inputLetters, 0, 26);
        if (isValidWord(cells[activeLine][0].getText() + cells[activeLine][1].getText() + cells[activeLine][2].getText() + cells[activeLine][3].getText() + cells[activeLine][4].getText())) {
            for (int i = 0; i < cells[0].length; i++) { //Change Box to Green if Inputted Character is In Correct Spot
                cells[activeLine][i].setBackground(Color.lightGray);
                if (cells[activeLine][i].getText().charAt(0) == wordle.charAt(i)) {
                    cells[activeLine][i].setBackground(Color.green);
                    inputLetters[cells[activeLine][i].getText().charAt(0) - 65] -= 1;
                }
            }
            for (int i = 0; i < cells[0].length; i++) { //Changes Box to Yellow if Wordle Contains Inputted Character But is in Wrong Spot
                if (inputLetters[cells[activeLine][i].getText().charAt(0) - 65] > 0 && cells[activeLine][i].getBackground() != Color.green) {
                    cells[activeLine][i].setBackground(Color.yellow);
                    inputLetters[cells[activeLine][i].getText().charAt(0) - 65] -= 1;
                }
            }
            if ((cells[activeLine][0].getText() + cells[activeLine][1].getText() + cells[activeLine][2].getText() + cells[activeLine][3].getText() + cells[activeLine][4].getText()).equals(wordle)) { //Win if Input is Equal to Wordle
                int minutes = gameTime / 60;
                int seconds = gameTime % 60;
                int guesses = activeLine + 1;
                if (guesses != 1) {
                    JOptionPane.showMessageDialog(game, String.format("You guessed the Wordle!\nYou took %d:%02d.\nYou made %d guesses.", minutes, seconds, guesses)); //Winbox for More Than 1 Guess
                } else {
                    JOptionPane.showMessageDialog(game, String.format("You guessed the Wordle!\nYou took %d:%02d.\nYou made %d guess.", minutes, seconds, guesses)); //Winbox For Only 1 Guess
                }
                System.exit(0);
            }
            activeLine += 1;
            updateKeyboard();
        } else {
            JOptionPane.showMessageDialog(game, "Not in word list."); //Tells Player That Input is Not in Wordlist
        }
        if (activeLine > 5) {  //Loss if #of Guesses is 6 or More
            int minutes = gameTime / 60;
            int seconds = gameTime % 60;
            JOptionPane.showMessageDialog(game, String.format("The Wordle was %s\nYou took %d:%02d.", wordle, minutes, seconds));
            System.exit(0);
        }
    }

    public static void input(char inputChar) {
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

    public static void backspace() {
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
    //Changes Color of OnScreen Keyboard Based on Characters in Input
    public static void updateKeyboard() {
        int prevLine = activeLine - 1;
        for (int i = 0; i < keys.length; i++) {
            for (int j = 0; j < cells[0].length; j++) {
                if (cells[prevLine][j].getBackground() == Color.lightGray && cells[prevLine][j].getText().equals(keys[i].getText()) && keys[i].getBackground() != Color.yellow && keys[i].getBackground() != Color.green) {
                    keys[i].setBackground(Color.lightGray);
                }
                if (cells[prevLine][j].getBackground() == Color.yellow && cells[prevLine][j].getText().equals(keys[i].getText()) && keys[i].getBackground() != Color.green) {
                    keys[i].setBackground(Color.yellow);
                }
                if (cells[prevLine][j].getBackground() == Color.green && cells[prevLine][j].getText().equals(keys[i].getText())) {
                    keys[i].setBackground(Color.green);
                }
            }
        }
    }
    //Allows OnScreen Keyboard to be Clicked and Send Input to Game
    public static void onMouseClicked(MouseEvent mouseEvent) {
        for (int i = 0; i < keys.length; i++) {
            if (mouseEvent.getSource() == keys[i]) {
                char keyClicked = keys[i].getText().charAt(0);
                switch (keyClicked) {
                    case ('A'):
                        input('A');
                        break;
                    case ('B'):
                        input('B');
                        break;
                    case ('C'):
                        input('C');
                        break;
                    case ('D'):
                        input('D');
                        break;
                    case ('E'):
                        input('E');
                        break;
                    case ('F'):
                        input('F');
                        break;
                    case ('G'):
                        input('G');
                        break;
                    case ('H'):
                        input('H');
                        break;
                    case ('I'):
                        input('I');
                        break;
                    case ('J'):
                        input('J');
                        break;
                    case ('K'):
                        input('K');
                        break;
                    case ('L'):
                        input('L');
                        break;
                    case ('M'):
                        input('M');
                        break;
                    case ('N'):
                        input('N');
                        break;
                    case ('O'):
                        input('O');
                        break;
                    case ('P'):
                        input('P');
                        break;
                    case ('Q'):
                        input('Q');
                        break;
                    case ('R'):
                        input('R');
                        break;
                    case ('S'):
                        input('S');
                        break;
                    case ('T'):
                        input('T');
                        break;
                    case ('U'):
                        input('U');
                        break;
                    case ('V'):
                        input('V');
                        break;
                    case ('W'):
                        input('W');
                        break;
                    case ('X'):
                        input('X');
                        break;
                    case ('Y'):
                        input('Y');
                        break;
                    case ('Z'):
                        input('Z');
                        break;
                    case ('⌫'):
                        backspace();
                        break;
                    case ('⏎'):
                        enter();
                        break;

                }
            }
        }
    }
    //Creates Keyboard Input
    @Override
    public void keyPressed(KeyEvent keyEvent) {
        switch (keyEvent.getKeyCode()) {
            case KeyEvent.VK_A:
                input('A');
                break;
            case KeyEvent.VK_B:
                input('B');
                break;
            case KeyEvent.VK_C:
                input('C');
                break;
            case KeyEvent.VK_D:
                input('D');
                break;
            case KeyEvent.VK_E:
                input('E');
                break;
            case KeyEvent.VK_F:
                input('F');
                break;
            case KeyEvent.VK_G:
                input('G');
                break;
            case KeyEvent.VK_H:
                input('H');
                break;
            case KeyEvent.VK_I:
                input('I');
                break;
            case KeyEvent.VK_J:
                input('J');
                break;
            case KeyEvent.VK_K:
                input('K');
                break;
            case KeyEvent.VK_L:
                input('L');
                break;
            case KeyEvent.VK_M:
                input('M');
                break;
            case KeyEvent.VK_N:
                input('N');
                break;
            case KeyEvent.VK_O:
                input('O');
                break;
            case KeyEvent.VK_P:
                input('P');
                break;
            case KeyEvent.VK_Q:
                input('Q');
                break;
            case KeyEvent.VK_R:
                input('R');
                break;
            case KeyEvent.VK_S:
                input('S');
                break;
            case KeyEvent.VK_T:
                input('T');
                break;
            case KeyEvent.VK_U:
                input('U');
                break;
            case KeyEvent.VK_V:
                input('V');
                break;
            case KeyEvent.VK_W:
                input('W');
                break;
            case KeyEvent.VK_X:
                input('X');
                break;
            case KeyEvent.VK_Y:
                input('Y');
                break;
            case KeyEvent.VK_Z:
                input('Z');
                break;
            case KeyEvent.VK_BACK_SPACE:
                backspace();
                break;
            case KeyEvent.VK_ENTER:
                enter();
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {}

    @Override
    public void keyTyped(KeyEvent keyEvent) {}

}
