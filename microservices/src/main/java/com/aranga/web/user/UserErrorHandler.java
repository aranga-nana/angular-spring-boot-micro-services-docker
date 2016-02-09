package com.aranga.web.user;

import com.aranga.common;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

/**
 * Created by Aranga on 10/02/2016.
 */
@ControllerAdvice(basePackageClasses = UserController.class)
public class UserErrorHandler extends ResponseEntityExceptionHandler
{
    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public common error()
    {
        common c = new common();
        c.setState(HttpStatus.NOT_FOUND.value());
        c.setMessage("Error");
        c.setTime(new Date().toString());
        return c;
    }

}
