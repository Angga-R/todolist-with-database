package com.todolist;

import com.todolist.repository.TodolistRepository;
import com.todolist.repository.TodolistRepositoryImpl;
import com.todolist.service.TodolistService;
import com.todolist.service.TodolistServiceImpl;
import com.todolist.util.DatabaseUtil;
import com.todolist.util.InputUtil;
import com.todolist.view.TodolistView;

public class App {
    public static void main(String[] args) {

        TodolistRepository todolistRepository = new TodolistRepositoryImpl(DatabaseUtil.getDataSource());
        TodolistService todolistService = new TodolistServiceImpl(todolistRepository);
        TodolistView todolistView = new TodolistView(todolistService);
        boolean repeat = true;

        do {
            System.out.println("\n---------------------");
            todolistView.showTodolist();
            System.out.println("1. Add New Todo");
            System.out.println("2. Remove Todo");
            System.out.println("3. Exit");
            String choose = InputUtil.input("Choose number");
            switch (choose) {
                case "1" -> todolistView.addTodolist();
                case "2" -> todolistView.removeTodolist();
                case "3" -> {
                    System.out.println("Good Bye!");
                    repeat = false;
                }
                default -> System.err.println("Choose only 1 / 2 / 3!");
            }
        } while (repeat);

    }
}
