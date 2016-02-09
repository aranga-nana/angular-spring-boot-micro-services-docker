package com.aranga.config.hibernate.drivers;

import com.aranga.config.hibernate.AppConfig;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

/**
 * Created by Aranga on 8/02/2016.
 */
@Configuration
@ConditionalOnProperty(name = "db.type", havingValue = "mysql")
public class MySqlDataSourceDriverImpl implements DataSourceDriver
{
    private static final Logger LOG = Logger.getLogger(MySqlDataSourceDriverImpl.class);
    private final String driver = "com.mysql.jdbc.Driver";
    private final String dialect = "org.hibernate.dialect.MySQL5Dialect";
    private final String url = "jdbc:mysql://%s:%d/%s?autoReconnect=true&useSSL=false&verifyServerCertificate=false";


    @Autowired(required = false)
    private AppConfig dbconfig;

    @Override
    public DataSource create()
    {
        final String dbUrl = String.format(url,dbconfig.getHost(),dbconfig.getPort(),dbconfig.getDbname());
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(driver);
        dataSource.setUrl(dbUrl);
        dataSource.setUsername(dbconfig.getUserName());
        dataSource.setPassword(dbconfig.getPassword());

        LOG.info("Creating DataSource With URL:" + dbUrl);
        LOG.info("mysql DataSoruce created :" + driver);
        return dataSource;

    }
    public String getDialect()
    {
        return dialect;
    }
    public String getDriverClass()
    {
        return driver;
    }
}
