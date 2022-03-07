package com.xuegao.framework.annotation;

import java.lang.annotation.*;

/**
 * @author xuegao
 * @version 1.0
 * @date 2022/1/5 23:31
 */
@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface GPRequestParam {
    String value() default "";
}
