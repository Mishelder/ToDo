package com.miaskor.database;

import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;

import javax.sql.DataSource;
import java.sql.Connection;

@UtilityClass
public class ConnectionManager {
    private static final DataSource dataSource = DataSourceFactory.getDataSource();

    @SneakyThrows
    public static Connection getConnection() {
        return dataSource.getConnection();
    }
}
