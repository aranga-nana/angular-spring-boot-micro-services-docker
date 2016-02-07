package com.aranga.config;

import java.io.File;

/**
 * Created by Aranga on 7/02/2016.
 */
public interface AppFileSystem
{


    AppFileSystem create(String path);
    boolean delete(String fileName);

}
