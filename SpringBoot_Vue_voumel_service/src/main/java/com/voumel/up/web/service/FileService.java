package com.voumel.up.web.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @author 小唐
 * @version 1.0
 * @project SpringBoot_Vue_voumel_parent
 * @description
 * @date 2023/7/26 21:31:32
 */
public interface FileService {
    /**
     * 将字节文件上传，并将文件的访问地址添加到redis中
     * @param fileBytes ---被转化为字节数组的文件
     * @param fileName ---需要保存的文件名
     * @return
     * @throws IOException
     */
    void uploadFile(byte[] fileBytes,String fileName) throws IOException;
}
