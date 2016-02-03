package com.aranga.repository;


import com.aranga.domain.User;

/**
 * Created by nanara0 on 5/01/2015.
 */
public interface UserRepository
{
    User find(long id);
    User selectByLogin(String login);
}
