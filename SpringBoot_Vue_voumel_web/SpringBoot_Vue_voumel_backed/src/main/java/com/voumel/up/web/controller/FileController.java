package com.voumel.up.web.controller;

import com.volume.up.pojo.SetMeal;
import com.voumel.up.constant.MessageConstant;
import com.voumel.up.entity.Result;
import com.voumel.up.web.service.FileService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.UUID;

/**
 * @author 小唐
 * @version 1.0
 * @project SpringBoot_Vue_voumel_parent
 * @description 文件上传，本类用于
 * @date 2023/7/26 21:28:24
 */
@RestController
public class FileController {
    @Resource
    private FileService fileService;

    @Value("${qiniu.QiniuUrl}")
    private String imageUrl;
    /**
     * 将文件上传到云存储
     * 将上传的图片的地址记录在redis中
     * 传入MultipartFile类型的对象
     *
     * @param multipartFile ---
     * @return
     */
    @PutMapping("/file/image")
    public Result uploadImage(@RequestBody MultipartFile multipartFile) {
        //TODO filename是返回的地址，要传到前端,并且要传到redis中存储，方便实现云存储定时垃圾清理
        try {
            byte[] fileBytes = multipartFile.getBytes();
            String fileName = multipartFile.getOriginalFilename();
            String suffix = fileName.substring(fileName.length() - 4);
            String prefix = fileName.substring(0, fileName.length() - 4);
            fileName = prefix + UUID.randomUUID() + suffix;
            fileService.uploadFile(fileBytes,imageUrl+fileName);
            return new Result(true, MessageConstant.PIC_UPLOAD_SUCCESS, imageUrl+fileName);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.PIC_UPLOAD_FAIL);
        }
    }

}
