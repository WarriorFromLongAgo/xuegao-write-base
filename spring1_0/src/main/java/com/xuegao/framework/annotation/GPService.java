package com.xuegao.framework.annotation;

/**
 * @author xuegao
 * @version 1.0
 * @date 2022/1/5 23:29
 */

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface GPService {
    String value() default "";
}

// 作者：Tom弹架构
// 链接：https://juejin.cn/post/7047372672539295757
// 来源：稀土掘金
// 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。