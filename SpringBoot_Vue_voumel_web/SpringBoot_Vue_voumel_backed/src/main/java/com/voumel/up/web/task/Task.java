package com.voumel.up.web.task;

import com.voumel.up.constant.RedisConstant;
import com.voumel.up.util.QiniuUtils;
import com.voumel.up.web.service.SetMealService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;


/**
 * @author 小唐
 * @version 1.0
 * @project SpringBoot_Vue_voumel_parent
 * @description 定时任务类
 * @date 2023/7/27 19:29:06
 */
@Component
public class Task {
    Logger log = LoggerFactory.getLogger(Task.class);
    @Resource
    private QiniuUtils qiniuUtils;

    @Resource
    private CountDownLatch countDownLatch;

    @Resource
    private RedisTemplate redisTemplate;
    @Value("${qiniu.QiniuUrl}")
    private String imageUrl;


    @Scheduled(cron = "0/30 * * * * ?")
    public void cleanQiniu() {
        Set<String> set = redisTemplate.opsForSet().difference(RedisConstant.URL_OF_ADD_SETMEAL_TO_REDIS,RedisConstant.URL_OF_ADD_SETMEAL_TO_DB);
        Iterator<String> iterator = set.iterator();
        try {
            countDownLatch.await(500000, TimeUnit.MILLISECONDS);
            log.info(":======>共有{}个垃圾，开始清理云存储垃圾",set.size());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        while (iterator.hasNext()){
            String next = iterator.next();
            String[] split = next.split(imageUrl);
            String s = split[1];
            qiniuUtils.deleteFileFromQiniu(s);
            redisTemplate.opsForSet().remove(RedisConstant.URL_OF_ADD_SETMEAL_TO_REDIS,next);
        }
        log.info("=======>清理云存储垃圾成功，共清理{}个垃圾",set.size());
    }
}
