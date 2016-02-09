package com.aranga.config.hibernate.env;

/**
 * Created by Aranga on 8/02/2016.
 */
public interface AppConfig
{
    int getPort();


    String getDbname();


    String getHost();


    String isShowSql();

    String getUserName();

    String getPassword();


}
