package com.miaskor.database;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MainConnectionManager implements ConnectionManager {
    private static final MainConnectionManager INSTANCE = new MainConnectionManager();
    private final DataSource mainDataSource = DataSourceFactory.getMainDataSource();

    public Connection getConnection() {
        try {
            return mainDataSource.getConnection();
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public static MainConnectionManager getInstance(){
        return INSTANCE;
    }
}
