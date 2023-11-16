package generator.sql.testing.xgety.infrastructure.configuration;

import generator.sql.testing.xgety.infrastructure.util.Util;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import java.util.Objects;

@Setter
@Configuration
@PropertySource("classpath:application.properties")
public class ConstantConfig {
    private static ConstantConfig instance = null;

    @Autowired
    private Environment env;

    private String corePoolSize = null;
    private String maxPoolSize = null;
    private String queueCapacity = null;
    private String threadNamePrefix = null;
    private String queueCampaignProgramHost = null;
    private String queueCampaignProgramPort = null;
    private String queueCampaignProgramUsername = null;
    private String queueCampaignProgramPassword = null;
    private String queueCampaignProgramName = null;
    private String campaignProgramInterval = null;
    private String campaignProgramTransactionIdPrefix = null;
    private String campaignProgramExchangeName = null;
    private String campaignProgramMaxAck = null;
    private String mySqlUrl;
    private String mySqlUserName;
    private String mySqlPass;
    private String mySqlDriver;
    private String mySqlConnectionTimeout;
    private String mySqlMaxLifeTime;
    private String mySqlIdleTimeout;
    private String mySqlMaxPool;
    private String mySqlMinimumIdle;
    private String mySqlCachePrepStmt;
    private String mySqlPrepStmtCacheSize;
    private String mySqlPrepStmtCacheSqlLimit;
    private String cutOffMonth;
    private String queueCheck;
    private String idTableCP;
    private String trxKafkaTableCP;
    private String msisdnTableCP;
    private String mainTriggerTableCP;
    private String countInjectBlast;

    @Bean
    public static ConstantConfig getInstance() {
        if (Util.isEmptyOrNull(instance)) {
            instance = new ConstantConfig();
        }

        return instance;
    }

    public Integer getCorePoolSize() {
        return Integer.valueOf(Objects.requireNonNull(env.getProperty("spring.main.core_pool_size")));
    }

    public Integer getMaxPoolSize() {
        return Integer.valueOf(Objects.requireNonNull(env.getProperty("spring.main.max_pool_size")));
    }

    public Integer getQueueCapacity() {
        return Integer.valueOf(Objects.requireNonNull(env.getProperty("spring.main.queue_capacity")));
    }

    public String getThreadNamePrefix() {
        return env.getProperty("spring.main.thread_name_prefix");
    }

    public String getQueueCampaignProgramHost() {
        return env.getProperty("spring.rabbitmq.host");
    }

    public Integer getQueueCampaignProgramPort() {
        return Integer.valueOf(Objects.requireNonNull(env.getProperty("spring.rabbitmq.port")));
    }

    public String getQueueCampaignProgramUsername() {
        return env.getProperty("spring.rabbitmq.username");
    }

    public String getQueueCampaignProgramPassword() {
        return env.getProperty("spring.rabbitmq.password");
    }

    public String getQueueCampaignProgramName() {
        return env.getProperty("cp.rabbitmq.queue_name");
    }

    public Integer getCampaignProgramInterval() {
        return Integer.valueOf(Objects.requireNonNull(env.getProperty("cp.interval")));
    }

    public String getCampaignProgramTransactionIdPrefix() {
        return env.getProperty("cp.transaction_id.prefix");
    }

    public String getCampaignProgramExchangeName(){
         return env.getProperty("spring.rabbitmq.template.exchange");
    }

    public Integer getCampaignProgramMaxAck() {
        return Integer.valueOf(Objects.requireNonNull(env.getProperty("cp.max_ack")));
    }

    public String getMySqlUrl() {
        return env.getProperty("spring.datasource.url");
    }

    public String getMySqlUserName() {
        return env.getProperty("spring.datasource.username");
    }

    public String getMySqlPass() {
        return env.getProperty("spring.datasource.password");
    }

    public String getMySqlDriver() {
        return env.getProperty("spring.datasource.driver-class-name");
    }

    public Integer getMySqlConnectionTimeout() {
        return Integer.valueOf(Objects.requireNonNull(env.getProperty("spring.datasource.hikari.connectionTimeout")));
    }
    public Integer getMySqlIdleTimeout() {
        return Integer.valueOf(Objects.requireNonNull(env.getProperty("spring.datasource.hikari.idleTimeout")));
    }
    public Integer getMySqlMaxLifeTime() {
        return Integer.valueOf(Objects.requireNonNull(env.getProperty("spring.datasource.hikari.maxLifetime")));
    }

    public Integer getMySqlMaxPool() {
        return Integer.valueOf(Objects.requireNonNull(env.getProperty("spring.datasource.hikari.maximum-pool-size")));
    }

    public Integer getMySqlMinimumIdle() {
        return Integer.valueOf(Objects.requireNonNull(env.getProperty("spring.datasource.hikari.minimumIdle")));
    }

    public Integer getCountBlastInject() {
        return Integer.valueOf(Objects.requireNonNull(env.getProperty("blast.inject.counter")));
    }

    public String getMySqlCachePrepStmt() {
        return env.getProperty("spring.datasource.hikari.data-source-properties.cachePrepStmts");
    }

    public String getMySqlPrepStmtCacheSize() {
        return env.getProperty("spring.datasource.hikari.data-source-properties.prepStmtCacheSize");
    }

    public String getMySqlPrepStmtCacheSqlLimit() {
        return env.getProperty("spring.datasource.hikari.data-source-properties.prepStmtCacheSqlLimit");
    }

    public String getQueueCheck() {
        return env.getProperty("cp.queue.check");
    }

    public String getIdTableCP() {
        return env.getProperty("cp.table.id");
    }

    public String getTrxKafkaTableCP() {
        return env.getProperty("cp.table.trxKafkaId");
    }

    public String getMsisdnTableCP() {
        return env.getProperty("cp.table.msisdn");
    }

    public String getMainTriggerTableCP() {
        return env.getProperty("cp.table.mainTrigger");
    }

    public Integer getCutOffMonth() {
        return Integer.valueOf(Objects.requireNonNull(env.getProperty("cp.cut.off.month")));
    }
}
