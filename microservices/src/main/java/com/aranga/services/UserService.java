package com.aranga.services;


import com.aranga.domain.User;

import java.util.List;

/**
 * Created by nanara0 on 5/01/2015.
 */
public interface UserService
{
    User selectById(long id);
    User getUser(String login);
    List<User> getAll();
}
