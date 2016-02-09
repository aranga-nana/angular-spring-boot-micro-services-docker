package com.aranga.web.welcome;

import org.springframework.core.env.Environment;

import java.util.Date;

/**
 * Created by Aranga on 8/02/2016.
 */
public class Message
{
    private String containerId;
    private String time;
    public Message(Environment env)
    {
        this.containerId = env.getProperty("HOSTNAME");
        this.time = new Date().toString();
    }
    public String getContainerId()
    {
        return containerId;
    }

    public void setContainerId(String containerId)
    {
        this.containerId = containerId;
    }

    public String getTime()
    {
        return time;
    }

    public void setTime(String time)
    {
        this.time = time;
    }
}
