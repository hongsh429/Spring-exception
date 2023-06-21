package com.exception.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "error.bad") // if(그냥 값이 있니?) else (메세지 소스 있니? 없으면 디폴드)
public class BadRequestException extends RuntimeException {

}
