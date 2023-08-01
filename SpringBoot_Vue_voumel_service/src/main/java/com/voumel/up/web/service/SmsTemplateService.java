package com.voumel.up.web.service;

/**
 * @author 小唐
 * @version 1.0
 * @project SpringBoot_Vue_voumel_parent
 * @description
 * @date 2023/7/31 21:54:59
 */
public interface SmsTemplateService {
    void sendSmsCode(String phoneNum);
}
