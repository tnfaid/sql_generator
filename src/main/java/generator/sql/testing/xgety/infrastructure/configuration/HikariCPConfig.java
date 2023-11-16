package generator.sql.testing.xgety.infrastructure.configuration;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import generator.sql.testing.xgety.infrastructure.util.Util;
import lombok.Setter;

import javax.sql.DataSource;

@Setter
public class HikariCPConfig {

    @Setter
    private static HikariCPConfig instance;
    private DataSource dataSourceMySql;

    private HikariCPConfig(){
        Util.debugLogger.info("Create Datasource");
        HikariConfig hikariConfigPes = new HikariConfig();
        hikariConfigPes.setJdbcUrl(ConstantConfig.getInstance().getMySqlUrl());
        hikariConfigPes.setUsername(ConstantConfig.getInstance().getMySqlUserName());
        hikariConfigPes.setPassword(ConstantConfig.getInstance().getMySqlPass());
        hikariConfigPes.setConnectionTestQuery("SELECT 1");
        hikariConfigPes.setMaximumPoolSize(ConstantConfig.getInstance().getMySqlMaxPool());
        hikariConfigPes.setMinimumIdle(ConstantConfig.getInstance().getMySqlMinimumIdle());
        hikariConfigPes.setMaxLifetime(ConstantConfig.getInstance().getMySqlMaxLifeTime());
        hikariConfigPes.setIdleTimeout(ConstantConfig.getInstance().getMySqlIdleTimeout());
        hikariConfigPes.setAllowPoolSuspension(true);
        hikariConfigPes.setDriverClassName(ConstantConfig.getInstance().getMySqlDriver());
        hikariConfigPes.addDataSourceProperty("autoReconnect", "true");
        hikariConfigPes.addDataSourceProperty("cachePrepStmts", ConstantConfig.getInstance().getMySqlCachePrepStmt());
        hikariConfigPes.addDataSourceProperty("prepStmtCacheSize", ConstantConfig.getInstance().getMySqlPrepStmtCacheSize());
        hikariConfigPes.addDataSourceProperty("prepStmtCacheSqlLimit", ConstantConfig.getInstance().getMySqlPrepStmtCacheSqlLimit());
        dataSourceMySql =new  HikariDataSource(hikariConfigPes);

    }

    public DataSource getDataSourceMySql() {
        return dataSourceMySql;
    }



    public static synchronized HikariCPConfig getInstance(){
        if (instance == null) {
            instance = new HikariCPConfig();
        }
        return instance;
    }

}
