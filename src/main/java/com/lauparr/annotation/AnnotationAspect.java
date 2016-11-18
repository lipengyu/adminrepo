package com.lauparr.annotation;

import com.google.common.collect.Maps;
import com.lauparr.dto.restful.CustomResponse;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Created by lauparr on 17/11/2016.
 */
@Aspect
@Component
public class AnnotationAspect {

    @Pointcut("execution(public * *.*(..))")
    public void publicMethod() {
    }

    @Pointcut("within(@org.springframework.web.bind.annotation.RestController *)")
    public void controller() {
    }

    @Around(value = "publicMethod() && @annotation(Restful)")
    public Object restfulAspect(ProceedingJoinPoint joinPoint) {
        Map map = Maps.newHashMap();
        CustomResponse response = new CustomResponse();
        try {
            Restful restful = ((MethodSignature) joinPoint.getSignature()).getMethod().getAnnotation(Restful.class);
            Object result = joinPoint.proceed();
            response.ok(result);
        } catch (Throwable e) {
            response.error(e);
        }
        return response;
    }

}
