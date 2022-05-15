package com.home_decoration.back_end.baiguo.controller;

import com.home_decoration.back_end.baiguo.domain.YingPin;
import com.home_decoration.back_end.baiguo.service.YingPinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class YingPinController {

    @Autowired
    private YingPinService yingPinService;

    @RequestMapping("/admin/yingPin")
    public List<YingPin> get(int num){
        return yingPinService.getPage(num);
    }

    @RequestMapping("/yingPin/add")
    public boolean add(@RequestBody YingPin yingPin){
        return yingPinService.add(yingPin);
    }

    @RequestMapping("/admin/yingPin/page")
    public int page(){
        return yingPinService.pageNumber();
    }

    @RequestMapping("/admin/yingPin/get")
    public List<YingPin> add(int zhaoId){
        return yingPinService.getByZhaoId(zhaoId);
    }
}
