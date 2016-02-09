package com.aranga.config.hibernate.drivers;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * Created by Aranga on 8/02/2016.
 */
@Configuration
@ConditionalOnProperty(name = "db.type",havingValue = "ms-sql")
public class MsSqlServerDataSourceDriverImpl implements DataSourceDriver
{
    @Override
    public DataSource create()
    {

        return null;
    }

    @Override
    public String getDialect()
    {
        return null;
    }

    @Override
    public String getDriverClass()
    {
        return null;
    }
}
