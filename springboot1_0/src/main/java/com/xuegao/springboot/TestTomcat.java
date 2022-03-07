package com.xuegao.springboot;

import org.apache.catalina.LifecycleException;
import org.apache.catalina.WebResourceRoot;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.startup.Tomcat;
import org.apache.catalina.webresources.DirResourceSet;
import org.apache.catalina.webresources.StandardRoot;

import javax.servlet.ServletException;
import java.io.File;

/**
 * 启动程序的main类。
 * <br/> @ClassName：Test
 * <br/> @Description：
 * <br/> @date：2021/8/16 20:49
 */
public class TestTomcat {
    public static void main(String[] args) throws ServletException, LifecycleException {
        // 使用Java内置Tomcat运行SpringMVC框架 原理：tomcat加载到
        // springmvc注解启动方式，就会创建springmvc容器
        start();
    }

    public static void start() throws ServletException, LifecycleException {
        // 创建Tomcat容器
        Tomcat tomcatServer = new Tomcat();
        // 端口号设置
        tomcatServer.setPort(9090);
        // 读取项目路径 加载静态资源
        StandardContext ctx = (StandardContext) tomcatServer.addWebapp("/", new File("src/main").getAbsolutePath());
        // 禁止重新载入
        ctx.setReloadable(false);
        // class文件读取地址
        File additionWebInfClasses = new File("target/classes");
        // 创建WebRoot
        WebResourceRoot resources = new StandardRoot(ctx);
        // tomcat内部读取Class执行
        resources.addPreResources(
                new DirResourceSet(resources, "/WEB-INF/classes", additionWebInfClasses.getAbsolutePath(), "/"));
        tomcatServer.start();
        // 异步等待请求执行
        tomcatServer.getServer().await();
    }

    // 作者：llsydn
    // 链接：https://juejin.cn/post/6995854333799579679
    // 来源：掘金
    // 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
}