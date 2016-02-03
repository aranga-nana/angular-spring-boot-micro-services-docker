package com.aranga.services;


import com.aranga.domain.User;

/**
 * Created by nanara0 on 5/01/2015.
 */
public interface UserService
{
    User selectById(long id);
    User getUser(String login);
}
