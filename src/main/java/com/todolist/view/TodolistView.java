package com.todolist.view;

import com.todolist.service.TodolistService;
import com.todolist.util.InputUtil;

import java.util.InputMismatchException;

public class TodolistView {

    private final TodolistService todolistService;

    public TodolistView(TodolistService todolistService) {
        this.todolistService = todolistService;
    }

    public void showTodolist() {
        System.out.println("|--- My Todolist ---|");
        System.out.println("---------------------");
        System.out.println("| No | Todo  | Date |");
        System.out.println("---------------------");
        todolistService.showTodolist();
        System.out.println("---------------------");
    }

    public void addTodolist() {
        String todo = InputUtil.input("Input here (0 for back)");
        if(!todo.isBlank() && !todo.equals("0")) {
            todolistService.addTodolist(todo);
        }
    }

    public void removeTodolist() {
        System.out.println("Choose number want to remove (0 for back)");
        while (true) {
            try {
                int id = Integer.parseInt(InputUtil.input("Number"));
                if (id != 0) {
                    todolistService.removeTodolist(id);
                    break;
                }
                break;
            } catch (InputMismatchException exception) {
                System.err.println("Number Only!");
            }
        }
    }
}
