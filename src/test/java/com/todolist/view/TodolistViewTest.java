package com.todolist.view;

import com.todolist.repository.TodolistRepository;
import com.todolist.repository.TodolistRepositoryImpl;
import com.todolist.service.TodolistService;
import com.todolist.service.TodolistServiceImpl;
import com.todolist.util.DatabaseUtil;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class TodolistViewTest {

    private TodolistService todolistService;
    private TodolistView todolistView;

    @BeforeEach
    void setUp() {
        TodolistRepository todolistRepository = new TodolistRepositoryImpl(DatabaseUtil.getDataSource());
        todolistService = new TodolistServiceImpl(todolistRepository);
        todolistView = new TodolistView(todolistService);
    }

    @AfterEach
    void tearDown() throws SQLException {
        Connection connection = DatabaseUtil.getDataSource().getConnection();
        Statement statement = connection.createStatement();
        statement.executeUpdate("DELETE FROM todo WHERE todo='Test'");
        statement.close();
        connection.close();
    }

    @Nested
    class ShowTodolist {

        @BeforeEach
        void setUp() {
            for (int i = 0; i < 3; i++) {
                todolistService.addTodolist("Test");
            }
        }

        @Test
        void testShow() {
            todolistView.showTodolist();
        }
    }
}
