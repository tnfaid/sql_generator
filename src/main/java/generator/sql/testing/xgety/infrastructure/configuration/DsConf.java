package generator.sql.testing.xgety.infrastructure.configuration;

import org.springframework.stereotype.Service;

import javax.sql.DataSource;

@Service
public class DsConf {
    public static DataSource dbConfPes() {
        return HikariCPConfig.getInstance().getDataSourceMySql();
    }
}
