package com.hexin.sample.annotation;


import java.lang.annotation.*;

/**
 * Title: SystemControllerLog
 * @date 2018年8月31日
 * @version V1.0
 * Description:  自定义注解，拦截service
 */
@Target({ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SystemServiceLog {
    String description() default "";
}