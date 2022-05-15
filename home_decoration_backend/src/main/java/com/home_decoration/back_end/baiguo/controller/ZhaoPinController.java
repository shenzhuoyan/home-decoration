package com.home_decoration.back_end.baiguo.controller;

import com.home_decoration.back_end.baiguo.domain.ZhaoPin;
import com.home_decoration.back_end.baiguo.service.ZhaoPinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ZhaoPinController {
    @Autowired
    private ZhaoPinService zhaoPinService;

    @RequestMapping("/zhaoPin")
    public List<ZhaoPin> get(int num){
        return zhaoPinService.getPage(num);
    }

    @RequestMapping("/admin/zhaoPin/add")
    public boolean add(@RequestBody ZhaoPin zhaoPin){
        return zhaoPinService.add(zhaoPin);
    }

    @RequestMapping("/admin/zhaoPin/rm")
    public boolean rm(int id){
        return zhaoPinService.rm(id);
    }

    @RequestMapping("/admin/zhaoPin/re")
    public boolean re(int id){
        return zhaoPinService.re(id);
    }

    @RequestMapping("/admin/zhaoPin/deleted")
    public List<ZhaoPin> all(int num){
        return zhaoPinService.getDelPage(num);
    }

    @RequestMapping("/admin/zhaoPin/deletedPage")
    public int deletedPage(){
        return zhaoPinService.pageNumber(0);
    }

    @RequestMapping("/zhaoPin/page")
    public int page(){
        return zhaoPinService.pageNumber(1);
    }
}
