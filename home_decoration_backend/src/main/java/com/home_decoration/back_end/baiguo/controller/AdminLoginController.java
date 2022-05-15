package com.home_decoration.back_end.baiguo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class AdminLoginController {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @RequestMapping("/adminLogin")
    public String adminLogin(String name,String password){
        if("admin".equals(name)&&"123456".equals(password)){
            String millis = String.valueOf(System.currentTimeMillis());
            stringRedisTemplate.opsForValue().set("token",millis);
            return millis;
        }
        return null;
    }
}
