package com.voumel.up;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author 小唐
 * @version 1.0
 * @project SpringBoot_Vue_voumel_parent
 * @description PC后台的启动类
 * @date 2023/7/22 17:18:04
 */
@SpringBootApplication
@EnableTransactionManagement
@EnableAsync
@EnableScheduling
public class ApplicationBacked {
    public static void main(String[] args) {
        SpringApplication.run(ApplicationBacked.class);
    }
}
