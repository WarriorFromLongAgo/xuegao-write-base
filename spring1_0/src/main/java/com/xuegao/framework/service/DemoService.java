package com.xuegao.framework.service;

import com.xuegao.framework.annotation.GPService;

/**
 * @author xuegao
 * @version 1.0
 * @date 2022/1/5 23:32
 */
@GPService
public class DemoService implements IDemoService {
    @Override
    public String get(String name) {
        return "My name is " + name;
    }
}