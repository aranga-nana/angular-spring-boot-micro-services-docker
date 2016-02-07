package com.aranga.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import java.io.File;

/**
 * Created by Aranga on 7/02/2016.
 */
@Configuration
public class AppFileSystemImpl implements AppFileSystem
{
    @Autowired
    private Environment env;
    private String base;
    private File tmpDir;
    @PostConstruct
    public void postConstruct()
    {
        base = env.getProperty("java.io.tmpdir");
        if(!StringUtils.isEmpty(env.getProperty("home.dir")))
        {
            base=env.getProperty("home.dir");
            File f =new File(base);
            base = f.getAbsolutePath();
            System.setProperty("home.dir",base);
        }
        tmpDir = new File(env.getProperty("java.io.tmpdir"));
    }
    public  boolean isValid()
    {
        return !StringUtils.isEmpty(env.getProperty("home.dir"));
    }
    @Override
    public AppFileSystem create(String path)
    {
        return null;
    }

    @Override
    public boolean delete(String fileName)
    {
        return false;
    }
}
