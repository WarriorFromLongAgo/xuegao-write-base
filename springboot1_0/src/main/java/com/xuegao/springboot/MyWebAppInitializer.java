package com.xuegao.springboot;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class MyWebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    // 加载根配置信息 spring核心
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{com.xuegao.springboot.config.RootConfig.class};
    }

    // springmvc 加载 配置信息
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{com.xuegao.springboot.config.WebConfig.class};
    }

    // springmvc 拦截url映射 拦截所有请求
    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }
}

// 作者：llsydn
// 链接：https://juejin.cn/post/6995854333799579679
// 来源：掘金
// 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。