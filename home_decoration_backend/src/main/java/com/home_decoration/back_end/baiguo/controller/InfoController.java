package com.home_decoration.back_end.baiguo.controller;

import com.home_decoration.back_end.baiguo.domain.Info;
import com.home_decoration.back_end.baiguo.service.InfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class InfoController {
    @Autowired
    private InfoService infoService;

    //公司介绍
    @RequestMapping("/describe")
    public String describe(){
        return infoService.getDescribe();
    }

    @RequestMapping("/admin/describe/add")
    public boolean newDescribe(@RequestBody Map<String, String> map){
        if(map.get("describe")!=null)
            return infoService.addDescribe(map.get("describe"));
        return false;
    }

    @RequestMapping("/admin/describe/all")
    public List<Info> allDescribe(){
        return infoService.getAllDescribe();
    }


    //热线电话
    @RequestMapping("/hotLine")
    public String hotLine(){
        return infoService.getHotLine();
    }

    @RequestMapping("/admin/hotLine/add")
    public boolean newHotLine(@RequestBody Map<String, String> map){
        if(map.get("hotLine")!=null)
            return infoService.addHotLine(map.get("hotLine"));
        return false;
    }

    @RequestMapping("/admin/hotLine/all")
    public List<Info> allHotLine(){
        return infoService.getAllHotLine();
    }

    //备案号
    @RequestMapping("/beiAnNumber")
    public String beiAnNumber(){
        return infoService.getBeiAnNumber();
    }
    @RequestMapping("/admin/beiAnNumber/add")
    public boolean newBeiAnNumber(@RequestBody Map<String, String> map){
        if(map.get("beiAnNumber")!=null)
            return infoService.addBeiAnNumber(map.get("beiAnNumber"));
        return false;
    }

    @RequestMapping("/admin/beiAnNumber/all")
    public List<Info> allBeiAnNumber(){
        return infoService.getAllBeiAnNumber();
    }

    //地址
    @RequestMapping("/address")
    public String address(){
        return infoService.getAddress();
    }

    @RequestMapping("/admin/address/add")
    public boolean newAddress(@RequestBody Map<String, String> map){
        if(map.get("address")!=null)
            return infoService.addAddress(map.get("address"));
        return false;
    }

    @RequestMapping("/admin/address/all")
    public List<Info> allAddress(){
        return infoService.getAllAddress();
    }


    @RequestMapping("/banner")
    public List<Info> banner(){
        return infoService.getBanner();
    }

    @RequestMapping("/admin/banner/add")
    public boolean addBanner(String picture){
        return infoService.addBanner(picture);
    }

    @RequestMapping("/admin/banner/del")
    public boolean delBanner(int id){
        return infoService.delBanner(id);
    }
}
