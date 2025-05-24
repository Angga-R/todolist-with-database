package com.todolist.Entity;

import java.sql.Date;

public class Todolist {

    private String todo;
    private Date date;

    public Todolist(String todo, Date date) {
        this.todo = todo;
        this.date = date;
    }

    public String getTodo() {
        return todo;
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
