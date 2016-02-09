package com.aranga.config.hibernate.env;

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

    @Value("${mysql.env.mysql.root.password}")
    private String password;
    @Value("${mysql.port.3306.tcp.addr}")
    private String host;
    @Value("${mysql.port.3306.tcp.port:3306}")
    private int port;

    @Override
    public int getPort()
    {
        return port;
    }

    public void setPort(int port)
    {
        this.port = port;
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
        return "true";
    }

    @Override
    public String getUserName()
    {
        return "root";
    }

    @Override
    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }
}
