package com.aranga.welcome;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Aranga on 7/02/2016.
 */
@RestController
public class GreetingController
{
    @RequestMapping("/greeting")
    public String greeting()
    {
        return "Hello,I'm spring boot RestController !!";
    }
}
