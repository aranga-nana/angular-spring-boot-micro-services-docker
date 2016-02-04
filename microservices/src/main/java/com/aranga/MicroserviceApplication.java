package com.aranga;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;

import java.io.File;

/**
 * Created by nanara0 on 3/02/2016.
 */
@SpringBootApplication
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class}) // DataSourceTransactionManagerAutoConfiguration.class
public class MicroserviceApplication implements ApplicationRunner
{
    @Override
    public void run(ApplicationArguments args) throws Exception
    {
        System.out.println(args);
    }

    public static void main(String[] args) throws Exception
    {


        if (System.getenv("LOG_DIR") !=null)
        {

            final String p =System.getProperty("java.io.tmpdir");
            final File f=new File(p);
            System.setProperty("log_dir",System.getenv("LOG_DIR"));
        }
        if (System.getProperty("log_dir") ==null)
        {

            final String p =System.getProperty("java.io.tmpdir");
            final File f=new File(p);
            System.setProperty("log_dir",f.getPath()+File.separator+"spring-boot"+File.separator+"logs");
        }
        SpringApplication.run(MicroserviceApplication.class, args);
    }
}
