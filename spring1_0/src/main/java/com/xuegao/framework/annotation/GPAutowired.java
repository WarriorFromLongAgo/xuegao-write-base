package com.xuegao.framework.annotation;


import java.lang.annotation.*;

/**
 * @author xuegao
 * @version 1.0
 * @date 2022/1/5 23:30
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface GPAutowired {
    String value() default "";
}
