package com.todolist.util;

import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;

public class DatabaseUtilTest {

    @Test
    void testConnection() throws SQLException {
        Connection connection = DatabaseUtil.getDataSource().getConnection();

        connection.close();
    }
}
