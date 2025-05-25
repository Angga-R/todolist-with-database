package com.todolist.Entity;

import java.sql.Date;

public class Todolist {

    private int id;
    private String todo;
    private Date date;

    public Todolist(int id, String todo, Date date) {
        this.id = id;
        this.todo = todo;
        this.date = date;
    }

    public Todolist(String todo, Date date) {
        this.todo = todo;
        this.date = date;
    }

    public String getTodo() {
        return todo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTodo(String todo) {
        this.todo = todo;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
