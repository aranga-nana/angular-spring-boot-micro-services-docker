package com.aranga.config;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationEnvironmentPreparedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.core.env.Environment;

/**
 * Created by Aranga on 7/02/2016.
 */

public class DockerContainerPortScanner implements ApplicationListener<ApplicationEnvironmentPreparedEvent>
{

    private static final Logger LOG = Logger.getLogger(DockerContainerPortScanner.class);



    public DockerContainerPortScanner()
    {

    }
    @Override
    public void onApplicationEvent(ApplicationEnvironmentPreparedEvent event)
    {
        LOG.info("DockerContainerPortScanner() loaded!!");
        LOG.info("HELLO!!");

        for(String s :event.getEnvironment().getSystemEnvironment().keySet())
        {
            LOG.info(s);
        }

    }

}
