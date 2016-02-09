package com.aranga.config.app;

/**
 * Access to app home file system.
 * can be use to store all the uploaded fiels
 * Created by Aranga on 7/02/2016.
 */
public interface AppFileSystem
{


    AppFileSystem create(String path);
    boolean delete(String fileName);

}
