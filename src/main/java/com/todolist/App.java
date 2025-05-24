package com.todolist;

import com.todolist.util.InputUtil;
import io.github.cdimascio.dotenv.Dotenv;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {

        String input = InputUtil.input("Name:");

        System.out.println(input);
    }
}
