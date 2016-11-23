package com.lauparr.core.annotation;

import java.lang.annotation.*;

/**
 * Created by lauparr on 17/11/2016.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
public @interface Restful {

    boolean secured() default false;

    String[] roles() default {};

}
