package com.miaskor.database;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TestConnectionManager implements ConnectionManager {
    private static final TestConnectionManager INSTANCE = new TestConnectionManager();
    private final DataSource testDataSource = DataSourceFactory.getTestDataSource();

    public  Connection getConnection() {
        try {
            return testDataSource.getConnection();
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public static TestConnectionManager getInstance(){
        return INSTANCE;
    }
}
