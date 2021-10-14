package com.epam.esm.zotov.module2.api.exception.advice;

import java.util.Locale;

import com.epam.esm.zotov.module2.api.exception.NoResourceFoundException;
import com.epam.esm.zotov.module2.api.exception.RestExceptionResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
@PropertySource("classpath:error.properties")
public class LocalizedSpecificExceptionAdvice {
    @Value("${error.msg.4040}")
    private String error4040;
    @Value("${error.code.4040}")
    private String error4040Code;

    @Autowired
    private MessageSource messageSource;

    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<RestExceptionResponse> noResourceFoundHandle(Locale locale) {
        RestExceptionResponse response = new RestExceptionResponse(messageSource.getMessage(error4040, null, locale),
                error4040Code);

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
}