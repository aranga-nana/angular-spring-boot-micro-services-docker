package com.aranga.domain;

import javax.persistence.*;

/**
 * Created by nanara0 on 10/12/2014.
 */
@Entity
@NamedQueries({
        @NamedQuery(
                name = "User.selectByLogin",
                query = "select u from  User u where u.login =:login"

        ),
        @NamedQuery(
                name = "User.selectAll",
                query = "select u from  User u"

        )
})
@Table(name="user")
public class User
{
    private long id;
    private String login;
    private String firstName;
    private String lastName;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",nullable = false,unique = true)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Column(name = "login",nullable = false,unique = true)
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }
    @Column(name = "first_name")
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    @Column(name = "last_name")
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

}
