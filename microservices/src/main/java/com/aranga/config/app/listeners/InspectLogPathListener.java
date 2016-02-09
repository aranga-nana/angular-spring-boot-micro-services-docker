package com.aranga.config.app.listeners;


import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationListener;

/**
 * Created by Aranga on 6/02/2016.
 */
public class InspectLogPathListener implements ApplicationListener<ApplicationStartedEvent>
{

    @Override
    public void onApplicationEvent(ApplicationStartedEvent event)
    {


        System.setProperty("SQL_LOG_LEVEL","INFO");
        System.setProperty("SPRING_LOG_LEVEL","INFO");
        System.setProperty("ORM_LOG_LEVEL","INFO");
        if (System.getenv("log_dir") == null &&
            System.getProperty("log_dir") == null)
        {
            System.setProperty("log_dir",System.getProperty("java.io.tmpdir"));
        }

        if (System.getenv("SQL_LOG_LEVEL")!= null)
        {
            System.setProperty("SQL_LOG_LEVEL",System.getenv("SQL_LOG_LEVEL"));
        }
        if (System.getenv("SPRING_LOG_LEVEL")!= null)
        {
            System.setProperty("SPRING_LOG_LEVEL",System.getenv("SPRING_LOG_LEVEL"));
        }
        if (System.getenv("ORM_LOG_LEVEL")!= null)
        {
            System.setProperty("ORM_LOG_LEVEL",System.getenv("ORM_LOG_LEVEL"));
        }
    }
}
