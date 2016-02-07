package com.aranga.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.annotation.PostConstruct;

/**
 * Created by nanara0 on 3/02/2016.
 */
@Configuration
public class WebAppConfig extends WebMvcConfigurerAdapter
{
    @Autowired
    private Environment env;

    @PostConstruct
    public void postConstruct()
    {
        System.out.println("env"+env);
    }
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        super.addInterceptors(registry);
    }
}
