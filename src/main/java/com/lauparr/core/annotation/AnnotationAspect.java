package com.lauparr.core.annotation;

import com.google.common.collect.Maps;
import com.lauparr.core.dto.restful.CustomResponse;
import com.lauparr.core.exception.RestException;
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
    public Object restfulAspect(ProceedingJoinPoint joinPoint) throws Throwable {
        Map map = Maps.newHashMap();
        CustomResponse response = new CustomResponse();
        try {
            Restful restful = ((MethodSignature) joinPoint.getSignature()).getMethod().getAnnotation(Restful.class);
            Object result = joinPoint.proceed();
            response.ok(result);
        } catch (RestException e) {
            response.error(e);
        } catch (Throwable e) {
            throw e;
        }
        return response;
    }

}
