package com.home_decoration.back_end.baiguo.controller;

import com.home_decoration.back_end.baiguo.domain.Notice;
import com.home_decoration.back_end.baiguo.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class NoticeController {

    @Autowired
    private NoticeService noticeService;

    @RequestMapping("/noticeList")
    public List<Notice> notice(int page){
        return noticeService.getAll(page);
    }

    @RequestMapping("/noticeList/page")
    public int noticePage(){
        return noticeService.pageAll();
    }

    @RequestMapping("/admin/noticeList/add")
    public boolean add(@RequestBody Notice notice){
        return noticeService.add(notice);
    }

    @RequestMapping("/admin/noticeList/rm")
    public boolean rm(int id){
        return noticeService.del(id);
    }

    @RequestMapping("/admin/noticeList/up")
    public boolean up(int id){
        return noticeService.setUp(id);
    }

    @RequestMapping("/admin/noticeList/top")
    public boolean top(int id){
        return noticeService.setTop(id);
    }

    @RequestMapping("/admin/noticeList/resume")
    public boolean resume(int id){
        return noticeService.resume(id);
    }

    @RequestMapping("/admin/noticeList/deletedPage")
    public int delPage(){
        return noticeService.pageDel();
    }

    @RequestMapping("/admin/noticeList/deleted")
    public List<Notice> deleted(int page){
        return noticeService.getDel(page);
    }

    @RequestMapping("/noticeList/get")
    public Notice get(int id){
        Notice notice = noticeService.get(id);
        return notice.getStatus()>0?notice:null;
    }
    @RequestMapping("/noticeList/important")
    public List<Notice> important(){
        return noticeService.important();
    }

    @RequestMapping("/noticeList/newest")
    public List<Notice> newest(){
        return noticeService.getNew();
    }
}
