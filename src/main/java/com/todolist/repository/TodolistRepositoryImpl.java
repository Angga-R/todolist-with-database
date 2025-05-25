package com.todolist.repository;

import com.todolist.Entity.Todolist;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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
        String sql = "DELETE FROM todo WHERE id=?";
        try(Connection connection = dataSource.getConnection(); PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            int isDeleted = statement.executeUpdate();
            return isDeleted == 1;
        } catch (SQLException exception) {
            throw new RuntimeException(exception);
        }
    }

    @Override
    public List<Todolist> getAll() {
        try(Connection connection = dataSource.getConnection(); Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery("SELECT * FROM todo")) {
            List<Todolist> todolists = new ArrayList<>();
            while (resultSet.next()) {
                todolists.add(new Todolist(resultSet.getString("todo"), resultSet.getDate("date")));
            }
            return todolists;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
