package com.home_decoration.back_end.baiguo.controller;

import com.home_decoration.back_end.baiguo.domain.Good;
import com.home_decoration.back_end.baiguo.service.GoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class GoodController{
    @Autowired
    private GoodService goodService;

    @RequestMapping("/good/all")
    public List<Good> all(){
        return goodService.getAll();
    }


    @RequestMapping("/good/get")
    public Good get(int id){
        return goodService.getById(id);
    }


    @RequestMapping("/admin/good/add")
    public boolean add(@RequestBody Good good){
        return goodService.add(good);
    }

    @RequestMapping("/admin/good/del")
    public boolean del(int id){
        return goodService.del(id);
    }

    @RequestMapping("/admin/good/update")
    public boolean modify(@RequestBody Good good){
        return goodService.modify(good);
    }

    @RequestMapping("/admin/good/all")
    public List<Good> adminAll(){
        return goodService.detailAll();
    }
}
