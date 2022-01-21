package ca.jacksonbarker;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scnr = new Scanner(System.in);
        String[] guesses = new String[6];
        String input;

        while (guesses[5] == null) {
            input = "";

            while (!isValidWord(input)) {
                System.out.println("Please enter a 5 letter word:");
                input = scnr.next();
            }

            for (int i = 0; i < 6; i++) {
                if (guesses[i] == null) {
                    guesses[i] = input;
                    input = null;
                }
            }

            printBoard(guesses);
            System.out.println("");

        }

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
