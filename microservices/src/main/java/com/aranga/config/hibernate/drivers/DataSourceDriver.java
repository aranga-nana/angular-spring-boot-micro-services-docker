package com.aranga.config.hibernate.drivers;

import javax.sql.DataSource;

/**
 * Created by Aranga on 8/02/2016.
 */
public interface DataSourceDriver
{
    DataSource create();
    String getDialect();
    String getDriverClass();
}
