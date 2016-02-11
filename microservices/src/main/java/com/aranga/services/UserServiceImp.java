package com.aranga.services;

import com.aranga.domain.User;
import com.aranga.repository.UserRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by nanara0 on 5/01/2015.
 */
@Service("userService")
public class UserServiceImp implements UserService
{
    private static final Logger LOG = Logger.getLogger(UserServiceImp.class);
    @Autowired
    private UserRepository rep;
    @Override
    public User selectById(long id)
    {

        try
        {
            return rep.find(id);
        }catch (Throwable e)
        {
            LOG.error(e);
        }
        return null;
    }

    @Override
    public User getUser(String login)
    {
        try
        {
            return rep.selectByLogin(login);
        }catch (Throwable e)
        {
            LOG.error(e);
        }
        return null;

    }
    @Override
    public List<User> getAll()
    {
        final List<User> list = rep.selectAll();

        return list;

    }
}
