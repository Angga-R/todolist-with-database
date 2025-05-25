package com.todolist.service;

import com.todolist.repository.TodolistRepository;
import com.todolist.repository.TodolistRepositoryImpl;
import com.todolist.util.DatabaseUtil;
import com.zaxxer.hikari.HikariDataSource;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class TodolistServiceTest {

    private HikariDataSource dataSource;
    private TodolistRepository todolistRepository;
    private TodolistService todolistService;

    @BeforeEach
    void setUp() {

        dataSource = DatabaseUtil.getDataSource();
        todolistRepository = new TodolistRepositoryImpl(dataSource);
        todolistService = new TodolistServiceImpl(todolistRepository);
    }

    @Nested
    class ShowTodolist {

        @BeforeEach
        void setUp() {
            for (int i = 0; i < 3; i++) {
                todolistRepository.add("Test");
            }
        }

        @AfterEach
        void tearDown() throws SQLException {
            Connection connection = dataSource.getConnection();
            Statement statement = connection.createStatement();
            statement.executeUpdate("DELETE FROM todo WHERE todo='test'");

            statement.close();
            connection.close();
        }

        @Test
        void testShowTodolist() {
            todolistService.showTodolist();
        }
    }
}
