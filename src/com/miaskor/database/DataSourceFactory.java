package com.miaskor.database;

import com.miaskor.util.Constants;
import com.miaskor.util.PropertyUtil;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lombok.experimental.UtilityClass;

import static com.miaskor.util.Constants.DataSource.*;
@UtilityClass
public class DataSourceFactory {

    private static final HikariConfig HIKARI_CONFIG = new HikariConfig();

    static {
        init();
    }

    public static HikariDataSource getTestDataSource() {
        HIKARI_CONFIG.setJdbcUrl(PropertyUtil.getProperty(TEST_URL_KEY,Constants.PropertyName.DATABASE));
        return new HikariDataSource(HIKARI_CONFIG);
    }

    public static HikariDataSource getMainDataSource() {
        return new HikariDataSource(HIKARI_CONFIG);
    }

    private static void init() {
        HIKARI_CONFIG.setJdbcUrl(PropertyUtil
                .getProperty(MAIN_URL_KEY, Constants.PropertyName.DATABASE));
        HIKARI_CONFIG.setUsername(PropertyUtil
                .getProperty(NAME_KEY, Constants.PropertyName.DATABASE));
        HIKARI_CONFIG.setPassword(PropertyUtil
                .getProperty(PASSWORD_KEY, Constants.PropertyName.DATABASE));
        HIKARI_CONFIG.setPoolName(PropertyUtil
                .getProperty(POOL_NAME_KEY, Constants.PropertyName.DATABASE));
        HIKARI_CONFIG.setIdleTimeout(Long.parseLong(PropertyUtil
                .getProperty(IDLE_TIMEOUT_KEY, Constants.PropertyName.DATABASE)));
        HIKARI_CONFIG.setMaximumPoolSize(Integer.parseInt(PropertyUtil
                .getProperty(POOL_SIZE_KEY, Constants.PropertyName.DATABASE)));
        HIKARI_CONFIG.addDataSourceProperty(CACHE_PREP_STMTS, PropertyUtil
                .getProperty(CACHE_PREP_STMTS, Constants.PropertyName.DATABASE));
        HIKARI_CONFIG.addDataSourceProperty(PREP_STMT_CACHE_SIZE, PropertyUtil
                .getProperty(PREP_STMT_CACHE_SIZE, Constants.PropertyName.DATABASE));
        HIKARI_CONFIG.addDataSourceProperty(PREP_STMT_CACHE_SQL_LIMIT, PropertyUtil
                .getProperty(PREP_STMT_CACHE_SQL_LIMIT, Constants.PropertyName.DATABASE));
        HIKARI_CONFIG.setDriverClassName(PropertyUtil.getProperty(DRIVER_KEY, Constants.PropertyName.DATABASE));
    }



}
