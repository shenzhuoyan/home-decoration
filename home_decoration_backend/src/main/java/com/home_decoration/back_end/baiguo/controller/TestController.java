package com.home_decoration.back_end.baiguo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
public class TestController {
    @RequestMapping("/test")
    public boolean test(HttpServletRequest request, HttpServletResponse response) throws IOException {

        response.setContentType("text/html;charset=UTF-8");
        response.getWriter().print("权限不足");
        return false;
    }

    @RequestMapping("/tt")
    public boolean tt(){
        System.out.println("收到tt");
        return true;
    }
}
