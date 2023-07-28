package com.voumel.up;


import com.voumel.up.web.service.FileService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.Map;
import java.util.concurrent.CountDownLatch;

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
        ApplicationContext run = SpringApplication.run(ApplicationBacked.class);
        Map<String,CountDownLatch> bean = run.getBeansOfType(CountDownLatch.class);
        FileService bean1 = run.getBean(FileService.class);
        System.out.println(bean);
    }
}
