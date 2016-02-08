package com.aranga;


import com.aranga.config.InspectLogPathListener;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;

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
        System.out.println(args.getSourceArgs());
    }

    public static void main(String[] args) throws Exception
    {


        final SpringApplication app = new SpringApplication(MicroserviceApplication.class);
        app.addListeners(new InspectLogPathListener());

        app.run(args);

    }
}
