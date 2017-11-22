package com.bestv.wechat.liteapp.premierleague.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.sql.SQLException;


/**
 * 数据库源配置
 * @author GY
 *
 */
@Configuration
public class DataSourceConfig {

    private static Logger logger = LogManager.getLogger(DataSourceConfig.class);

    @Value("${spring.datasource.type}")
    private Class<? extends DataSource> dataSourceType;

    @Value("${spring.datasource.url}")
    private String dbUrl;

    @Value("${spring.datasource.type}")
    private String dbType;

    @Value("${spring.datasource.username}")
    private String username;

    @Value("${spring.datasource.password}")
    private String password;

    @Value("${spring.datasource.driverClassName}")
    private String driverClassName;

    @Value("${spring.datasource.initialSize}")
    private int initialSize;

    @Value("${spring.datasource.minIdle}")
    private int minIdle;

    @Value("${spring.datasource.maxActive}")
    private int maxActive;

    @Value("${spring.datasource.maxWait}")
    private int maxWait;

    @Value("${spring.datasource.timeBetweenEvictionRunsMillis}")
    private int timeBetweenEvictionRunsMillis;

    @Value("${spring.datasource.minEvictableIdleTimeMillis}")
    private int minEvictableIdleTimeMillis;

    @Value("${spring.datasource.validationQuery}")
    private String validationQuery;

    @Value("${spring.datasource.testWhileIdle}")
    private boolean testWhileIdle;

    @Value("${spring.datasource.testOnBorrow}")
    private boolean testOnBorrow;

    @Value("${spring.datasource.testOnReturn}")
    private boolean testOnReturn;

    @Value("${spring.datasource.poolPreparedStatements}")
    private boolean poolPreparedStatements;

    @Value("${spring.datasource.filters}")
    private String filters;

    @Value("${spring.datasource.connectionProperties}")
    private String connectionProperties;

    @Value("${spring.datasource.useGlobalDataSourceStat}")
    private boolean useGlobalDataSourceStat;

    @Value("${spring.datasource.druidLoginName}")
    private String druidLoginName;

    @Value("${spring.datasource.druidPassword}")
    private String druidPassword;

    @Value("${spring.datasource.removeAbandoned}")
    private boolean removeAbandoned;

    @Value("${spring.datasource.removeAbandonedTimeout}")
    private int removeAbandonedTimeout;

    @Value("${spring.datasource.logAbandoned}")
    private boolean logAbandoned;

    /**
     * 写库 数据源配置
     * @return
     */
    //@Bean(name = "writeDataSource")
    @Bean(name = "dataSource")
    @Primary
    public DataSource writeDataSource() {
        logger.info("-------------------- writeDataSource init ---------------------");
        return createDataSource();
    }

    private DataSource createDataSource() {
        DruidDataSource datasource = new DruidDataSource();
        try {
            datasource.setUrl(this.dbUrl);
            datasource.setUsername(username);
            datasource.setPassword(password);
            datasource.setDriverClassName(driverClassName);
            datasource.setInitialSize(initialSize);
            datasource.setMinIdle(minIdle);
            datasource.setMaxActive(maxActive);
            datasource.setMaxWait(maxWait);
            datasource.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);
            datasource.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
            datasource.setValidationQuery(validationQuery);
            datasource.setTestWhileIdle(testWhileIdle);
            datasource.setTestOnBorrow(testOnBorrow);
            datasource.setTestOnReturn(testOnReturn);
            datasource.setPoolPreparedStatements(poolPreparedStatements);
            datasource.setFilters(filters);
            datasource.setConnectionProperties(connectionProperties);
            datasource.setUseGlobalDataSourceStat(useGlobalDataSourceStat);
            datasource.setRemoveAbandoned(removeAbandoned);
            datasource.setRemoveAbandonedTimeout(removeAbandonedTimeout);
            datasource.setLogAbandoned(logAbandoned);
        } catch (SQLException e) {
            logger.error("druid configuration initialization filter", e);
        }
        return datasource;
    }
    /**
     * 有多少个从库就要配置多少个
     * @return
     */
    /*
    @Bean(name = "readDataSource01")
    public DataSource readDataSourceOne() {
        logger.info("-------------------- read01 DataSourceOne init ---------------------");
        DruidDataSource datasource = new DruidDataSource();
        try {
            datasource.setUrl(this.dbUrl);
            datasource.setUsername(username);
            datasource.setPassword(password);
            datasource.setDriverClassName(driverClassName);
            datasource.setInitialSize(initialSize);
            datasource.setMinIdle(minIdle);
            datasource.setMaxActive(maxActive);
            datasource.setMaxWait(maxWait);
            datasource.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);
            datasource.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
            datasource.setValidationQuery(validationQuery);
            datasource.setTestWhileIdle(testWhileIdle);
            datasource.setTestOnBorrow(testOnBorrow);
            datasource.setTestOnReturn(testOnReturn);
            datasource.setPoolPreparedStatements(poolPreparedStatements);
            datasource.setFilters(filters);
            datasource.setConnectionProperties(connectionProperties);
            datasource.setUseGlobalDataSourceStat(useGlobalDataSourceStat);
            datasource.setRemoveAbandoned(removeAbandoned);
            datasource.setRemoveAbandonedTimeout(removeAbandonedTimeout);
            datasource.setLogAbandoned(logAbandoned);
        } catch (SQLException e) {
            logger.error("druid configuration initialization filter", e);
        }
        return datasource;
    }
    @Bean(name = "readDataSource02")
    @ConfigurationProperties(prefix = "mysql.datasource.read02")
    public DataSource readDataSourceTwo() {
        log.info("-------------------- read02 DataSourceTwo init ---------------------");
        return DataSourceBuilder.create().type(dataSourceType).build();
    }*/
}
