package com.lauparr.exception;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by lauparr on 14/11/2016.
 */
@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = RuntimeException.class)
    protected ResponseEntity<Object> handleConflict(RuntimeException exception, WebRequest webRequest, HttpServletRequest httpServletRequest) {
        Map map = new HashMap();
        map.put("success", false);
        map.put("error", exception.getMessage());
        map.put("url", httpServletRequest.getRequestURL());
        map.put("stack", Arrays.toString(exception.getStackTrace()));
        return handleExceptionInternal(exception, map, new HttpHeaders(), HttpStatus.BAD_REQUEST, webRequest);
    }

}