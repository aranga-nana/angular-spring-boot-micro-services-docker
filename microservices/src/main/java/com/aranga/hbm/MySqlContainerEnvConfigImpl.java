package com.aranga.hbm;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Aranga on 9/02/2016.
 */
@Configuration
@ConditionalOnProperty(name = "mysql.env.mysql.version")
public class MySqlContainerEnvConfigImpl implements AppConfig
{
    public MySqlContainerEnvConfigImpl()
    {
        System.out.println("MySqlContainerEnvConfigImpl()");
    }

    @Value("${mysql.port.3306.tcp.addr}")
    private String host;
    @Override
    public int getPort()
    {
        return 3303;
    }

    @Override
    public String getDbname()
    {
        return "Test";
    }

    @Override
    public String getHost()
    {
        return host;
    }
    public void setHost(String host)
    {
        this.host = host;
    }
    @Override
    public String isShowSql()
    {
        return null;
    }

    @Override
    public String getUserName()
    {
        return null;
    }

    @Override
    public String getPassword()
    {
        return null;
    }
}