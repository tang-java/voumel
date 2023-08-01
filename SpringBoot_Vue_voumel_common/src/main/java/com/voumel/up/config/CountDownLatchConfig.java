package com.voumel.up.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.CountDownLatch;

/**
 * @author 小唐
 * @version 1.0
 * @project SpringBoot_Vue_voumel_parent
 * @description
 * @date 2023/7/28 17:40:05
 */
@Configuration
public class CountDownLatchConfig {
    @Bean
    public CountDownLatch creatCountDownLatch() {
        CountDownLatch countDownLatch = new CountDownLatch(1);
        return countDownLatch;
    }
}
