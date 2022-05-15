package com.home_decoration.back_end.baiguo.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
public class PushPicture {
    @Value("${file.uploadFolder}")
    String uploadFolder;

    @PostMapping("/admin/picture/push")
    public String upLoadPicture(MultipartFile picture){
        //System.out.println("收到图片");
        //先保存封面图
        String fileName = picture.getOriginalFilename();
        String dateTime = new SimpleDateFormat("yyyy-MM-dd_hh-mm-ss-s").format(new Date());
        String pic = uploadFolder + dateTime + fileName;//实际存储的图片路径
        String aliaPic = "/picture/get/" + dateTime + fileName;//可以访问的虚拟文件路径
        File dest = new File(pic);
        try {
            picture.transferTo(dest);//存储图片
        } catch (IOException e) {
            System.out.println("文件保存失败"+pic);
        }
        return aliaPic;
    }
}
