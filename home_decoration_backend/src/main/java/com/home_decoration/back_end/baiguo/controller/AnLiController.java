package com.home_decoration.back_end.baiguo.controller;

import com.home_decoration.back_end.baiguo.domain.AnLi;
import com.home_decoration.back_end.baiguo.service.AnLiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AnLiController {
    @Autowired
    AnLiService anLiService;


    @RequestMapping("/anLi/all")
    public List<AnLi> all(){
        return anLiService.getAll();
    }


    @RequestMapping("/anLi/get")
    public AnLi get(int id){
        return anLiService.get(id);
    }


    @RequestMapping("/admin/anLi/add")
    public boolean get(@RequestBody AnLi anLi){
        return anLiService.add(anLi);
    }


    @RequestMapping("/admin/anLi/del")
    public boolean del(int id){
        return anLiService.del(id);
    }


    @RequestMapping("/admin/anLi/all")
    public List<AnLi> adminAll(){
        return anLiService.adminAll();
    }

    @RequestMapping("/anLi/six")
    public List<AnLi> six(){
        return anLiService.get6();
    }
}
