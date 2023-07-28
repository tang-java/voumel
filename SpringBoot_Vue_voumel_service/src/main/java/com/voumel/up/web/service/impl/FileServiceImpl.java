package com.voumel.up.web.service.impl;

import com.voumel.up.constant.RedisConstant;
import com.voumel.up.util.QiniuUtils;
import com.voumel.up.web.service.FileService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author 小唐
 * @version 1.0
 * @project SpringBoot_Vue_voumel_parent
 * @description
 * @date 2023/7/26 21:47:08
 */
@Service
public class FileServiceImpl implements FileService {

    @Resource
    private QiniuUtils qiniuUtils;
    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Value("${qiniu.QiniuUrl}")
    private String imageUrl;

    @Override
    @Async("asyncExecutor")
    public void uploadFile(byte[] fileBytes, String fileName){
        try {
            //上传到七牛云存储
            int a=1;
            qiniuUtils.upload2Qiniu(fileBytes,fileName);
            // 带域名的地址，上传到redis数据库
            stringRedisTemplate.opsForSet().add(RedisConstant.URL_OF_ADD_SETMEAL_TO_REDIS,fileName);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
