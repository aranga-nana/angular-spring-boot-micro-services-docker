package com.aranga.web.welcome;


import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Aranga on 7/02/2016.
 */
@RestController
public class GreetingController
{
    private static final Logger LOG = Logger.getLogger(GreetingController.class);

    @Autowired
    private Environment env;
    @RequestMapping("/greeting")
    public Message greeting()
    {
        LOG.info("request completed!!");
        return new Message(env);
    }
}
