package pl.bastus.bookapp;

import java.util.Scanner;

interface Input {
    Scanner sc = new Scanner(System.in);
    default String getUserInput() {
        return sc.nextLine().trim();
    }
}