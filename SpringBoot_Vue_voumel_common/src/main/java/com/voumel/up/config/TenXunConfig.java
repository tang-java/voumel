package com.voumel.up.config;

import com.voumel.up.util.TenXunSDK;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author 小唐
 * @version 1.0
 * @project SpringBoot_Vue_voumel_parent
 * @description
 * @date 2023/8/1 12:39:17
 */
@Configuration
public class TenXunConfig {
    @Value("${tenxun.secretId}")
    private String secretId;
    @Value("${tenxun.secretKey}")
    private String secretKey;
    @Value("${tenxun.sign}")
    private String sign;
    @Value("${tenxun.appid}")
    private String appid;

    @Bean
    public TenXunSDK creatTengXunSDK(){
        TenXunSDK tengXunSDK = new TenXunSDK(secretId,secretKey,sign,appid);
        return tengXunSDK;
    }
}
