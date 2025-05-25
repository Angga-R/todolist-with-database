package com.todolist.util;

import java.util.Scanner;

public class InputUtil {

    private static final Scanner scanner = new Scanner(System.in);

    public static String input(String label){

        System.out.print(label + ": ");
        return scanner.nextLine();
    }
}
