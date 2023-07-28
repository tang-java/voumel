package com.voumel.up.web.task;

import com.voumel.up.constant.RedisConstant;
import com.voumel.up.util.QiniuUtils;
import com.voumel.up.web.service.SetMealService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
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
    private StringRedisTemplate stringRedisTemplate;

    @Resource
    private CountDownLatch countDownLatch;

    @Value("${qiniu.QiniuUrl}")
    private String imageUrl;


    @Scheduled(cron = "0/30 * * * * ?")
    public void cleanQiniu() {
        Set<String> set = stringRedisTemplate.opsForSet().difference(RedisConstant.URL_OF_ADD_SETMEAL_TO_REDIS,RedisConstant.URL_OF_ADD_SETMEAL_TO_DB);
        Iterator<String> iterator = set.iterator();
        while (iterator.hasNext()){
            log.info(Thread.currentThread().getName() + ":======>开始清理云存储垃圾");
            try {
                countDownLatch.await(5000, TimeUnit.MILLISECONDS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            String next = iterator.next();
            String[] split = next.split(imageUrl);
            String s = split[1];
            qiniuUtils.deleteFileFromQiniu(s);
            stringRedisTemplate.opsForSet().remove(RedisConstant.URL_OF_ADD_SETMEAL_TO_REDIS,next);
        }
    }
}
