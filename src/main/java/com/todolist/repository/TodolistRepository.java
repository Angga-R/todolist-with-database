package com.todolist.repository;

import com.todolist.Entity.Todolist;

public interface TodolistRepository {

    void add(String todo);

    boolean remove(int id);

    Todolist[] getAll();
}
