package com.voumel.up.controller;

import com.voumel.up.constant.MessageConstant;
import com.voumel.up.constant.RedisMessageConstant;
import com.voumel.up.entity.Result;
import com.voumel.up.web.service.OrderService;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @author 小唐
 * @version 1.0
 * @project SpringBoot_Vue_voumel_parent
 * @description
 * @date 2023/8/1 19:04:06
 */
@RestController
public class OrderController {
    @Resource
    private OrderService orderService;
    @Resource
    private RedisTemplate redisTemplate;

    @PostMapping("/order")
    @Transactional(propagation = Propagation.REQUIRED,readOnly = false)
    public Result addOrder(@RequestBody Map orderMap) {
        //获取短信验证码、电话号码
        String code = (String) orderMap.get("validateCode");
        String phoneNum = (String) orderMap.get("phoneNum");
        //校验短信验证码
        Integer smsCode = (Integer) redisTemplate.boundHashOps(phoneNum).get(RedisMessageConstant.SENDTYPE_ORDER);
        if (smsCode==null){
            return new Result(false, MessageConstant.REDIS_SMS_EXPIRED);
        }else {
            if (smsCode.equals(Integer.valueOf(code))){
                return orderService.addOrder(orderMap);
            }else {
                return new Result(false,MessageConstant.REDIS_SMS_FAIL);
            }
        }
    }
}
