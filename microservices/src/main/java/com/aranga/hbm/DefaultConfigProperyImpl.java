package com.aranga.hbm;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * Created by Aranga on 8/02/2016.
 */
@Configuration
@ComponentScan(basePackages = { "com.*" })
@PropertySource(value = {"classpath:/config.properties","file:${home_dir}/config.properties"},ignoreResourceNotFound = true)
@ConditionalOnMissingBean(MySqlContainerEnvConfigImpl.class)
public class DefaultConfigProperyImpl implements AppConfig
{

    @Value("${db.username:root}")
    private String userName;
    @Value("${db.password:password}")
    private String password;

    @Value("${db.port:3306}")
    private int port;

    @Value("${db.name:Test}")
    private String dbname;

    @Value("${db.host:localhost}")
    private String host;


    @Value("${db.showSql:false}")
    private String showSql;

    public DefaultConfigProperyImpl()
    {
        System.out.println("DefaultConfigProperyImpl()");
    }


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
        return dbname;
    }


    public void setDbname(String dbname)
    {
        this.dbname = dbname;
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
        return showSql;
    }


    public void setShowSql(String showSql)
    {
        this.showSql = showSql;
    }

    public String getUserName()
    {
        return userName;
    }

    public void setUserName(String userName)
    {
        this.userName = userName;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    @Override
    public String toString()
    {
        final StringBuilder sb = new StringBuilder("AppConfigDB{");
        sb.append("port=").append(port);
        sb.append(", dbname='").append(dbname).append('\'');
        sb.append(", host='").append(host).append('\'');
         sb.append(", username='").append(userName).append('\'');
        sb.append(", password='").append(userName!=null?"*******":"").append('\'');
        sb.append(", showSql='").append(showSql).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
