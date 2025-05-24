package com.todolist.repository;

import com.todolist.util.DatabaseUtil;
import com.zaxxer.hikari.HikariDataSource;
import org.junit.jupiter.api.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class TodolistRepositoryTest {

    private TodolistRepository todolistRepository;
    @BeforeEach
    void setUp() {
        todolistRepository = new TodolistRepositoryImpl(DatabaseUtil.getDataSource());
    }

    @Nested
    class Add {

        @AfterEach
        void tearDown() throws SQLException {
            Connection connection = DatabaseUtil.getDataSource().getConnection();
            Statement statement = connection.createStatement();

            statement.executeUpdate("DELETE FROM todo");

            connection.close();
            statement.close();

        }

        @Test
        void testAdd() {
            todolistRepository.add("Test");
        }
    }
}
