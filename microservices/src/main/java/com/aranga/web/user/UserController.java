package com.aranga.web.user;

import com.aranga.domain.User;
import com.aranga.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by nanara0 on 3/02/2016.
 */
@RestController
@RequestMapping("/user")
public class UserController
{
    @Autowired
    private UserService service;
    @RequestMapping(value = "/",method = RequestMethod.GET,produces = "application/json")
    public List<User> get()
    {
            return service.getAll();



    }
    @RequestMapping("/{id}/")
    public User getById(@RequestParam("id") long id)
    {

        throw new IllegalStateException("item not found");
    }



}
