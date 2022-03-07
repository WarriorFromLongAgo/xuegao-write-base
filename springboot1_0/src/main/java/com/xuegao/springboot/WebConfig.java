package com.xuegao.springboot;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

/**
 * springmvc 配置信息
 * 
 * @EnableWebMvc 开启springmvc 功能<br>
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = { "com.xuegao.springboot" })
public class WebConfig extends WebMvcConfigurerAdapter {

   // springboot 整合jsp 最好是war
   // 需要配置视图转换器
   // 创建SpringMVC视图解析器
   @Bean
   public ViewResolver viewResolver() {
      InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
      viewResolver.setPrefix("/WEB-INF/views/");
      viewResolver.setSuffix(".jsp");
      // 可以在JSP页面中通过${}访问beans
      viewResolver.setExposeContextBeansAsAttributes(true);
      return viewResolver;
   }

}

// 作者：llsydn
// 链接：https://juejin.cn/post/6995854333799579679
// 来源：掘金
// 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。