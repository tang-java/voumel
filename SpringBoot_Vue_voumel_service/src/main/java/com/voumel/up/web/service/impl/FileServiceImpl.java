package com.voumel.up.web.service.impl;

import com.voumel.up.constant.RedisConstant;
import com.voumel.up.util.QiniuUtils;
import com.voumel.up.web.service.FileService;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.CountDownLatch;

/**
 * @author 小唐
 * @version 1.0
 * @project SpringBoot_Vue_voumel_parent
 * @description
 * @date 2023/7/26 21:47:08
 */
@Service
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class FileServiceImpl implements FileService, BeanFactoryPostProcessor {

    @Resource
    private QiniuUtils qiniuUtils;

    @Resource
    private StringRedisTemplate stringRedisTemplate;
    @Resource
    private RedisTemplate redisTemplate;

    @Override
    @Async("asyncExecutor")
    public void uploadFile(byte[] fileBytes, String fileName) {
        try {
            //上传到七牛云存储
            qiniuUtils.upload2Qiniu(fileBytes, fileName);
            // 带域名的地址，上传到redis数据库
            redisTemplate.opsForSet().add(RedisConstant.URL_OF_ADD_SETMEAL_TO_REDIS, fileName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {
        configurableListableBeanFactory.initializeBean(new CountDownLatch(1), "countDownLatch");
    }
}
