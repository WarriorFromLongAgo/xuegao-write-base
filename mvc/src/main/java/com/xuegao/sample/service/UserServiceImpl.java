package com.xuegao.sample.service;


import com.xuegao.framework.annotation.MyService;
import com.xuegao.sample.pojo.User;

@MyService
public class UserServiceImpl implements UserService {

    public User getUser(){
        return new User("fantj","yyy");
    }
}
