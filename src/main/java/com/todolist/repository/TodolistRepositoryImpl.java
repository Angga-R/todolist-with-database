package com.todolist.repository;

import com.todolist.Entity.Todolist;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TodolistRepositoryImpl implements TodolistRepository{

    private final HikariDataSource dataSource;

    public TodolistRepositoryImpl(HikariDataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void add(String todo) {
        String sql = "INSERT INTO todo(todo, date) VALUES (?, ?)";
        try(Connection connection = dataSource.getConnection(); PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, todo);
            statement.setDate(2, new Date(System.currentTimeMillis()));
            statement.executeUpdate();
        } catch (SQLException exception) {
            throw new RuntimeException(exception);
        }
    }

    @Override
    public boolean remove(int id) {
        return false;
    }

    @Override
    public Todolist[] getAll() {
        return new Todolist[0];
    }
}
