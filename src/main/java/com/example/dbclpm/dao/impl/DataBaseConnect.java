package com.example.dbclpm.dao.impl;

import java.sql.Connection;
import java.sql.SQLException;

import com.zaxxer.hikari.HikariDataSource;

public class DataBaseConnect {
	public static Connection getConnection() throws SQLException {
        HikariDataSource dataSource = PoolConnection.getPoolConnection();
        return dataSource.getConnection();
    }
}
