package com.xuegao.springboot.controller;

import com.xuegao.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {
   @Autowired
   private UserService userService;

   @RequestMapping(value = "/index", produces = "text/html;charset=UTF-8")
   public String index() {
      // return "纯手写SpringBoot ok啦！！！"
      return userService.index();
   }
}

// 作者：llsydn
// 链接：https://juejin.cn/post/6995854333799579679
// 来源：掘金
// 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。