package ca.jacksonbarker;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scnr = new Scanner(System.in);
        String[] guesses = new String[6];

        System.out.println("Please enter a 5 letter word:");
        String input = scnr.next();

        while (guesses[5] == null) {
            System.out.println("Please enter a 5 letter word:");
            input = scnr.next();

            if (input.length() != 5) {
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

        }

    }

    public static void printBoard(String[] guesses) {
        System.out.println();
        for (int i = 0; i < 6; i++) {
            if (guesses[i] != null) {
                System.out.println(guesses[i]);
            } else {
                System.out.println("☐☐☐☐☐");
            }
        }
    }
}
