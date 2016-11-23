package com.lauparr.core.exception;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by lauparr on 14/11/2016.
 */
@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = Throwable.class)
    protected ResponseEntity<Object> handleConflict(Throwable exception, WebRequest webRequest, HttpServletRequest httpServletRequest) {
        Map map = new HashMap();
        map.put("success", false);
        map.put("error", exception.getMessage());
        map.put("url", httpServletRequest.getRequestURL());
        map.put("stack", Arrays.stream(exception.getStackTrace()).map(StackTraceElement::toString).collect(Collectors.toList()));
        return new ResponseEntity<Object>(map, HttpStatus.INTERNAL_SERVER_ERROR);
    }


}