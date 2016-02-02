package com.aranga.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by nanara0 on 3/02/2016.
 */
@RestController
@RequestMapping("/")
public class UserController
{
    @RequestMapping("user/")
    public String get()
    {
        return "a";
    }
}
