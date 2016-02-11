package com.aranga.web;

import org.hibernate.exception.JDBCConnectionException;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Aranga on 10/02/2016.
 */
@ControllerAdvice
public class GlobalExceptionHandler
{
    @ResponseBody
    @ResponseStatus(value = HttpStatus.SERVICE_UNAVAILABLE,reason = "critical error with database connection/structure")
    @ExceptionHandler({ SQLException.class, DataAccessException.class, DataAccessResourceFailureException.class, JDBCConnectionException.class})
    public Map<String,Object> dbError(Exception exception,HttpServletResponse response,HttpServletRequest request) throws IOException
    {
        Map<String,Object> m = new HashMap<>();
        m.put("id",Long.valueOf(12212));
        m.put("url", request.getRequestURL().toString());
        m.put("message", exception.getMessage());
        m.put("devMessage", "db server connection error");
        m.put("time", new Date().toString());
        return m;
    }
}
