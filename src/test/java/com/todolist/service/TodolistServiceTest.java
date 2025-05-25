package com.todolist.service;

import com.todolist.Entity.Todolist;
import com.todolist.repository.TodolistRepository;
import com.todolist.repository.TodolistRepositoryImpl;
import com.todolist.util.DatabaseUtil;
import com.zaxxer.hikari.HikariDataSource;
import org.junit.jupiter.api.*;

import java.sql.*;

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

    @AfterEach
    void tearDown() throws SQLException {
        Connection connection = dataSource.getConnection();
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
                todolistRepository.add(new Todolist("Test", new Date(System.currentTimeMillis())));
            }
        }

        @Test
        void testShowTodolist() {
            todolistService.showTodolist();
        }
    }

    @Nested
    class AddTodolist {

        @Test
        void testAdd() {
            todolistService.addTodolist("Test");
            todolistService.showTodolist();
        }
    }

    @Nested
    class RemoveTodolist {

        @BeforeEach
        void setUp() {
            todolistRepository.add(new Todolist("Test", new Date(System.currentTimeMillis())));
        }

        @Test
        void testRemoveSuccess() throws SQLException{
            Connection connection = DatabaseUtil.getDataSource().getConnection();
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery("SELECT * FROM todo WHERE todo='Test'");
            if(resultSet.next()) {
                todolistService.removeTodolist(resultSet.getInt("id"));
            }

            resultSet.close();
            connection.close();
            statement.close();
        }

        @Test
        void testRemoveFailed() {
            todolistService.removeTodolist(23245);
        }
    }
}
