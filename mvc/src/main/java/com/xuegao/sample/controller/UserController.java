package com.xuegao.sample.controller;

import com.xuegao.framework.annotation.MyAutowired;
import com.xuegao.framework.annotation.MyController;
import com.xuegao.framework.annotation.MyRequestMapping;
import com.xuegao.framework.servlet.ModelAndView;
import com.xuegao.sample.pojo.User;
import com.xuegao.sample.service.UserService;

import java.util.HashMap;
import java.util.Map;

@MyController
@MyRequestMapping("/web")
public class UserController {

    @MyAutowired
    private UserService userService;

    @MyRequestMapping("/hello.json")
    public ModelAndView hello(){
        User user = userService.getUser();
        ModelAndView mv = new ModelAndView();
        Map<String, Object> map = new HashMap<>();
        map.put("name", user.getName());
        map.put("addr", user.getAddr());
        mv.setView("template.fantj");
        mv.setModel(map);
        return mv;
    }
}
