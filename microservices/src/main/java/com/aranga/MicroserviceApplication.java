package com.aranga;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
import java.util.Properties;

/**
 * Created by nanara0 on 3/02/2016.
 */
@SpringBootApplication

public class MicroserviceApplication implements ApplicationRunner
{
    @Override
    public void run(ApplicationArguments args) throws Exception
    {
        System.out.println(args);
    }

    public static void main(String[] args) throws Exception
    {
        if (System.getProperty("log_dir") ==null)
        {

            final String p =System.getProperty("java.io.tmpdir");
            final File f=new File(p);
            System.setProperty("log_dir",f.getPath()+"/spring-boot/logs");
        }

        SpringApplication.run(MicroserviceApplication.class, args);
    }
}
