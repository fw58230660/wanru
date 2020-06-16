package com.wanru.advice;

import com.wanru.exception.CustomizeException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by FangWei on 2020-06-16.
 */
@ControllerAdvice
public class CustomizeExceptionHandler {
    @ExceptionHandler(Exception.class)
    ModelAndView handle(Throwable ex) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("error");
        if (ex instanceof CustomizeException) {
            modelAndView.addObject("message", ex.getMessage());
        } else {
            modelAndView.addObject("message", "程序员开小差了,请您稍后再试");
        }
        return modelAndView;
    }

}
