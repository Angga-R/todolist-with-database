package com.todolist.repository;

import com.todolist.Entity.Todolist;
import com.todolist.util.DatabaseUtil;
import com.zaxxer.hikari.HikariDataSource;
import org.junit.jupiter.api.*;

import java.sql.*;
import java.util.List;

public class TodolistRepositoryTest {

    private TodolistRepository todolistRepository;
    @BeforeEach
    void setUp() {
        todolistRepository = new TodolistRepositoryImpl(DatabaseUtil.getDataSource());
    }

    @AfterEach
    void tearDown() throws SQLException {
        Connection connection = DatabaseUtil.getDataSource().getConnection();
        Statement statement = connection.createStatement();

        statement.executeUpdate("DELETE FROM todo WHERE todo='test'");

        connection.close();
        statement.close();

    }

    @Nested
    class Add {


        @Test
        void testAdd() {
            todolistRepository.add(new Todolist("Test", new Date(System.currentTimeMillis())));
        }
    }

    @Nested
    class Remove {

        @BeforeEach
        void setUp() {
            todolistRepository.add(new Todolist("Test", new Date(System.currentTimeMillis())));
        }

        @Test
        void tesRemoveSuccess() throws SQLException{
            Connection connection = DatabaseUtil.getDataSource().getConnection();
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery("SELECT * FROM todo WHERE todo='test'");
            if(resultSet.next()) {
                boolean isDeleted = todolistRepository.remove(resultSet.getInt("id"));
                Assertions.assertTrue(isDeleted);
            }

            resultSet.close();
            connection.close();
            statement.close();

        }

        @Test
        void testRemoveFailed() {
            boolean isDeleted = todolistRepository.remove(23);
            Assertions.assertFalse(isDeleted);
        }
    }

    @Nested
    class GetAll {


        @BeforeEach
        void setUp() {
            for (int i = 0; i < 3; i++) {
                todolistRepository.add(new Todolist("Test", new Date(System.currentTimeMillis())));
            }
        }

        @Test
        void testGetAll() {
            List<Todolist> todolist = todolistRepository.getAll();

            Assertions.assertNotNull(todolist);
            todolist.forEach(todo -> {
                System.out.println(todo.getTodo() + " | " + todo.getDate());
            });
        }
    }
}
