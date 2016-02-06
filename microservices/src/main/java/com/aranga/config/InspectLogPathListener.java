package com.aranga.config;


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



        if (System.getenv("log_dir") == null &&
            System.getProperty("log_dir") == null)
        {
            System.setProperty("log_dir",System.getProperty("java.io.tmpdir"));
        }


    }
}
