package com.voumel.up.web.service.impl;

import com.voumel.up.constant.RedisMessageConstant;
import com.voumel.up.util.TenXunSDK;
import com.voumel.up.web.service.SmsTemplateService;
import com.voumel.up.util.ValidateCodeUtils;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @author 小唐
 * @version 1.0
 * @project SpringBoot_Vue_voumel_parent
 * @description
 * @date 2023/7/31 21:55:34
 */
@Service
public class SmsTemplateServiceImpl implements SmsTemplateService {
    Logger log= LoggerFactory.getLogger(SmsTemplateServiceImpl.class);

    @Resource
    RedisTemplate redisTemplate;

    @Resource
    private TenXunSDK tenXunSDK;

    @Override
    public void sendSmsCode(String phoneNum) {
        Integer time=10;
        Integer smsCode = ValidateCodeUtils.generateValidateCode(6);
        redisTemplate.boundHashOps(phoneNum).put(RedisMessageConstant.SENDTYPE_ORDER,smsCode);
        redisTemplate.expire(phoneNum,time,TimeUnit.MINUTES);
        log.info("存入redis设置过期时间为{}分钟",time);
        tenXunSDK.sendMessage(phoneNum,String.valueOf(smsCode));
        log.info("调用第三方SDK发送短信验证码,验证码为：{}", smsCode);
    }
}
