package com.todolist.repository;

import com.todolist.Entity.Todolist;

import java.util.List;

public interface TodolistRepository {

    void add(Todolist todolist);

    boolean remove(int id);

    List<Todolist> getAll();
}
