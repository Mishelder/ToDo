package com.miaskor.database;

import com.miaskor.util.PropertyNames;
import com.miaskor.util.PropertyUtil;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lombok.experimental.UtilityClass;

import javax.sql.DataSource;

@UtilityClass
public class DataSourceFactory {

    private static final String URL_KEY = "db.url";
    private static final String NAME_KEY = "db.userName";
    private static final String PASSWORD_KEY = "db.password";
    private static final String CACHE_PREP_STMTS = "db.cachePrepStmts";
    private static final String PREP_STMT_CACHE_SIZE = "db.prepStmtCacheSize";
    private static final String PREP_STMT_CACHE_SQL_LIMIT = "db.prepStmtCacheSqlLimit";
    private static final String POOL_SIZE_KEY = "db.maxPoolSize";
    private static final String IDLE_TIMEOUT_KEY = "db.idleTimeout";
    private static final String POOL_NAME_KEY = "db.poolName";
    private static final String DRIVER_KEY = "db.driver";
    private static final HikariConfig HIKARI_CONFIG = new HikariConfig();

    static {
        init();
    }

    public static DataSource getDataSource() {
        return new HikariDataSource(HIKARI_CONFIG);
    }

    private static void init() {
        HIKARI_CONFIG.setJdbcUrl(PropertyUtil
                .getProperty(URL_KEY, PropertyNames.DATABASE));
        HIKARI_CONFIG.setUsername(PropertyUtil
                .getProperty(NAME_KEY, PropertyNames.DATABASE));
        HIKARI_CONFIG.setPassword(PropertyUtil
                .getProperty(PASSWORD_KEY, PropertyNames.DATABASE));
        HIKARI_CONFIG.setPoolName(PropertyUtil
                .getProperty(POOL_NAME_KEY, PropertyNames.DATABASE));
        HIKARI_CONFIG.setIdleTimeout(Long.parseLong(PropertyUtil
                .getProperty(IDLE_TIMEOUT_KEY, PropertyNames.DATABASE)));
        HIKARI_CONFIG.setMaximumPoolSize(Integer.parseInt(PropertyUtil
                .getProperty(POOL_SIZE_KEY, PropertyNames.DATABASE)));
        HIKARI_CONFIG.addDataSourceProperty(CACHE_PREP_STMTS, PropertyUtil
                .getProperty(CACHE_PREP_STMTS, PropertyNames.DATABASE));
        HIKARI_CONFIG.addDataSourceProperty(PREP_STMT_CACHE_SIZE, PropertyUtil
                .getProperty(PREP_STMT_CACHE_SIZE, PropertyNames.DATABASE));
        HIKARI_CONFIG.addDataSourceProperty(PREP_STMT_CACHE_SQL_LIMIT, PropertyUtil
                .getProperty(PREP_STMT_CACHE_SQL_LIMIT, PropertyNames.DATABASE));
        HIKARI_CONFIG.setDriverClassName(PropertyUtil.getProperty(DRIVER_KEY, PropertyNames.DATABASE));
    }


}
