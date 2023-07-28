package com.voumel.up.web.controller;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.read.builder.ExcelReaderBuilder;
import com.voumel.up.constant.MessageConstant;
import com.voumel.up.entity.ExcelOrderSetting;
import com.voumel.up.entity.Result;
import com.voumel.up.web.listener.ExcelOrderSettingReadListener;
import com.voumel.up.web.service.FileService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.InputStream;
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

    @Resource
    private ExcelOrderSettingReadListener excelOrderSettingReadListener;
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
        try {
            byte[] fileBytes = multipartFile.getBytes();
            String fileName = multipartFile.getOriginalFilename();
            String suffix = fileName.substring(fileName.length() - 4);
            String prefix = fileName.substring(0, fileName.length() - 4);
            fileName = prefix + UUID.randomUUID() + suffix;
            fileService.uploadFile(fileBytes, imageUrl + fileName);
            return new Result(true, MessageConstant.PIC_UPLOAD_SUCCESS, imageUrl + fileName);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.PIC_UPLOAD_FAIL);
        }
    }

    /**
     * 传入Excel表，进行解析，将数据存入数据库
     * 解析技术采用  EasyExcel
     * @param excelFile --传入的文件
     * @return result
     */
    @PostMapping("/file/excel")
    public Result uploadOrderSetting(@RequestBody MultipartFile excelFile) {
        //拿到Excel表之后，要解析，然后插入数据库
        try {
            InputStream excelFileInputStream = excelFile.getInputStream();
            EasyExcel.read(excelFileInputStream, ExcelOrderSetting.class,excelOrderSettingReadListener).sheet().doRead();
            return new Result(true,MessageConstant.IMPORT_ORDERSETTING_SUCCESS);
        } catch (IOException e) {
            e.printStackTrace();
            return new Result(false,MessageConstant.IMPORT_ORDERSETTING_FAIL);
        }
    }

//    @GetMapping("/file/excel")
//    public Result downloadOrderSettingExcel(){
//        //TODO 请求返回一个预约设置的Excel表
//    }


}
