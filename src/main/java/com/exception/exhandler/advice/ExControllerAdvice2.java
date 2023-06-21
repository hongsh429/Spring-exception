package com.exception.exhandler.advice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice("com.exception.servlet")
public class ExControllerAdvice2 {

    @ExceptionHandler
    public String errorRuntimeException(RuntimeException e) {
        log.error("error ex", e);
        return "error/500";
    }
}
