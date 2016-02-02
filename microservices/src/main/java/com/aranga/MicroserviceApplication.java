package com.aranga;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created by nanara0 on 3/02/2016.
 */
@SpringBootApplication

public class MicroserviceApplication implements ApplicationRunner
{
    @Override
    public void run(ApplicationArguments args) throws Exception
    {

    }

    public static void main(String[] args)
    {
        SpringApplication.run(MicroserviceApplication.class, args);
    }
}
