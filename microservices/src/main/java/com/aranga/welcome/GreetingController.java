package com.aranga.welcome;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Aranga on 7/02/2016.
 */
@RestController
public class GreetingController
{
    private static final Logger LOG = Logger.getLogger(GreetingController.class);
    @RequestMapping("/greeting")
    public String greeting()
    {
        LOG.info("request completed!!");
        return "Hello,I'm spring boot RestController !!";
    }
}
