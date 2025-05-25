package com.todolist.service;

import com.todolist.Entity.Todolist;
import com.todolist.repository.TodolistRepository;

import java.sql.Date;
import java.util.List;

public class TodolistServiceImpl implements TodolistService{

    private final TodolistRepository todolistRepository;

    public TodolistServiceImpl(TodolistRepository todolistRepository) {
        this.todolistRepository = todolistRepository;
    }

    @Override
    public void showTodolist() {
        List<Todolist> todolist = todolistRepository.getAll();

        for (int i = 0; i < todolist.size(); i++) {
            Todolist todo = todolist.get(i);
            int number = i + 1;
            System.out.println(number + ". " + todo.getTodo() + " | " + todo.getDate());
        }
    }

    @Override
    public void addTodolist(String todo) {
        Todolist todolist = new Todolist(todo, new Date(System.currentTimeMillis()));
        todolistRepository.add(todolist);
    }

    @Override
    public void removeTodolist(int id) {

    }
}
