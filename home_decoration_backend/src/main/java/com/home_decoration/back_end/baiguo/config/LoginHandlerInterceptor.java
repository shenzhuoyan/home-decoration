package com.home_decoration.back_end.baiguo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginHandlerInterceptor implements HandlerInterceptor {

    private final StringRedisTemplate stringRedisTemplate;

    public LoginHandlerInterceptor(StringRedisTemplate stringRedisTemplate) {
        this.stringRedisTemplate = stringRedisTemplate;
    }

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception{
        String redisToken = stringRedisTemplate.opsForValue().get("token");

        String token =request.getHeader("token");
        System.out.println("获取到token: "+token);
            if(redisToken!=null&&redisToken.equals(token)) {
                return true;
        }
        //指定相应的字符集：解决响应的乱码问题。这里的代码是直接作为网页显示到前端
        response.sendRedirect(request.getContextPath()+"/login");
        return false;
    }
}
