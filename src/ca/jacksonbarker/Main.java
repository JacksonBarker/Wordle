package ca.jacksonbarker;

import java.util.Locale;
import java.util.Scanner;
import java.util.Random;

public class Main {

    public static void main(String[] args) {
        Scanner scnr = new Scanner(System.in);
        String[] guesses = new String[6];
        String word = WordList.wordList()[new Random().nextInt(WordList.wordList().length)];
        String input;

        while (guesses[5] == null) {
            input = "";

            while (!isValidWord(input)) {
                System.out.println("Please enter a 5 letter word:");
                input = scnr.next().toLowerCase();
            }

            for (int i = 0; i < 6; i++) {
                if (guesses[i] == null) {
                    guesses[i] = input;
                    input = null;
                }
            }

            printBoard(guesses);
            System.out.println("");

            for (int i = 0; i < 6; i++) {
                if (guesses[i].equals(word)) {
                    System.out.println("You guessed the word!");
                    System.exit(0);
                }
            }
        }

        System.out.println("The word was: " + word);

    }

    public static void printBoard(String[] guesses) {
        System.out.println("");
        for (int i = 0; i < 6; i++) {
            if (guesses[i] != null) {
                System.out.println(guesses[i]);
            } else {
                System.out.println("☐☐☐☐☐");
            }
        }
    }

    public static boolean isValidWord(String word) {
        if (word.length() != 5) {
            return false;
        }

        for (int i = 0; i < WordList.wordList().length; i++) {
            if (word.equals(WordList.wordList()[i])) {
                return true;
            }
        }

        return false;
    }
}
