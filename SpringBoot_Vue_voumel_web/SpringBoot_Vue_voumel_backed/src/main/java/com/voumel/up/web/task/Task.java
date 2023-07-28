package com.voumel.up.web.task;

import com.voumel.up.util.QiniuUtils;
import com.voumel.up.web.service.SetMealService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;


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
    private SetMealService setMealService;

    @Scheduled(cron = "0/8 * * * * ?")
    public void cleanQiniu() {
        log.info(Thread.currentThread().getName() + ":======>开始清理云存储垃圾");
        // 查询所有套餐的img,并存入redis

//        stringRedisTemplate.opsForValue().set("lisi","zhangsan");
    }
}
