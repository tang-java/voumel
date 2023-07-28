package com.voumel.up.web.config;

import com.voumel.up.util.QiniuUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author 小唐
 * @version 1.0
 * @project SpringBoot_Vue_voumel_parent
 * @description 配置类七牛云，云存储，
 * @date 2023/7/26 21:34:21
 */
@Configuration
public class QiniuConfig {
    @Value("${qiniu.AccessKey}")
    private String accessKey;
    @Value("${qiniu.SecretKey}")
    private String secretKey;
    @Value("${qiniu.Name}")
    private String name;


    @Bean
    public QiniuUtils createQiniuUtils() {
        QiniuUtils qiniuUtils = new QiniuUtils(accessKey,secretKey,name);
        return qiniuUtils;
    }
}
