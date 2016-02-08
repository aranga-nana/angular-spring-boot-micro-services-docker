package com.aranga.hbm;

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
