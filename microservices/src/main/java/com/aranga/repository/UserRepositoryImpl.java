package com.aranga.repository;


import com.aranga.domain.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by nanara0 on 5/01/2015.
 */
@Repository("userRepository")
public class UserRepositoryImpl implements UserRepository
{

    @PersistenceContext
    private EntityManager em;

    @Override
    public User find(long id)
    {
        final User u = em.find(User.class,id);
        return u;
    }

    public User selectByLogin(String login)
    {
        try
        {
            final List<User> list = em.createNamedQuery("User.selectByLogin", User.class).setParameter("login", login).getResultList();

            if (list.size() == 1) {
                return list.get(0);
            }
        }catch (Throwable e)
        {
            e.printStackTrace();;
        }

        return null;
    }

    @Override
    public List<User> selectAll()
    {

          final List<User> list = em.createNamedQuery("User.selectAll", User.class).getResultList();
          return list;



    }
}
