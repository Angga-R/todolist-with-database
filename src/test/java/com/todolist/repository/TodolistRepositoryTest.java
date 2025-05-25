package com.todolist.repository;

import com.todolist.util.DatabaseUtil;
import com.zaxxer.hikari.HikariDataSource;
import org.junit.jupiter.api.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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

        statement.executeUpdate("DELETE FROM todo");

        connection.close();
        statement.close();

    }

    @Nested
    class Add {


        @Test
        void testAdd() {
            todolistRepository.add("Test");
        }
    }

    @Nested
    class Remove {

        @BeforeEach
        void setUp() {
            todolistRepository.add("Test");
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

            connection.close();
            statement.close();

        }

        @Test
        void testRemoveFailed() {
            boolean isDeleted = todolistRepository.remove(23);
            Assertions.assertFalse(isDeleted);
        }
    }
}
