package com.voumel.up.controller;

import com.voumel.up.constant.MessageConstant;
import com.voumel.up.entity.Result;
import com.voumel.up.web.service.SmsTemplateService;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author 小唐
 * @version 1.0
 * @project SpringBoot_Vue_voumel_parent
 * @description 短信发送
 * @date 2023/7/31 21:42:08
 */
@RestController
public class SmsTemplateController {
    @Resource
    private SmsTemplateService smsTemplateService;

    @GetMapping("/setMealSmsCode/{phoneNum}")
    @Async("asyncExecutor")
    public Result setMealSmsCode(@PathVariable String phoneNum) {
        try {
            smsTemplateService.sendSmsCode(phoneNum);
            return new Result(true, MessageConstant.SEND_SMS_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,MessageConstant.SEND_SMS_FAIL);
        }
    }
}
