package com.xuegao.framework.controller;

import com.xuegao.framework.annotation.GPAutowired;
import com.xuegao.framework.annotation.GPController;
import com.xuegao.framework.annotation.GPRequestMapping;
import com.xuegao.framework.annotation.GPRequestParam;
import com.xuegao.framework.service.IDemoService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author xuegao
 * @version 1.0
 * @date 2022/1/5 23:33
 */
@GPController
@GPRequestMapping("/demo")
public class DemoController {

    @GPAutowired
    private IDemoService demoService;

    @GPRequestMapping("/query")
    public void query(HttpServletRequest req, HttpServletResponse resp,
                      @GPRequestParam("name") String name) {
        String result = demoService.get(name);
        try {
            resp.getWriter().write(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @GPRequestMapping("/add")
    public void add(HttpServletRequest req, HttpServletResponse resp,
                    @GPRequestParam("a") Integer a, @GPRequestParam("b") Integer b) {
        try {
            resp.getWriter().write(a + "+" + b + "=" + (a + b));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @GPRequestMapping("/remove")
    public void remove(HttpServletRequest req, HttpServletResponse resp,
                       @GPRequestParam("id") Integer id) {
    }

    // 作者：Tom弹架构
    // 链接：https://juejin.cn/post/7047372672539295757
    // 来源：稀土掘金
    // 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
}