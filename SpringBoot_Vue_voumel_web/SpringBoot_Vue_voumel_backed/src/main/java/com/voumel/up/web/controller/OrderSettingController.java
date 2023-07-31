package com.voumel.up.web.controller;

import com.voumel.up.entity.Result;
import com.voumel.up.web.service.OrderSettingService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @author 小唐
 * @version 1.0
 * @project SpringBoot_Vue_voumel_parent
 * @description
 * @date 2023/7/28 19:06:42
 */
@RestController
public class OrderSettingController {
    @Resource
    private OrderSettingService orderSettingService;
//    @GetMapping("/orderSetting")
//    public Result findOrderSetting(){
//        //TODO 查询预约
//    }

    /**
     * 修改预约人数
     * 通过日历----实现传入日期，修改orderSetting的可预约人数,若数据库中没有该日期，那么就往数据库插入一条数据
     * @param orderDate ---日期
     * @param number ---可预约人数
     * @return result
     */
    @GetMapping("/orderSetting/{date}/{number}")
    public Result updateOrderSettingByOrderDate(@PathVariable("date") String orderDate, @PathVariable String number){



        return null;
    }

    //TODO 根据日期范围查询预约相关信息 ----------看一看日期类
    // 开始条件当天传入日期，如果没有传入，那么就是从1号
    // 结束条件

    // 获取当前日期
    // 创建日历对象
    //

    @GetMapping("/orderSetting/{date}")
    public Result findOrderSettingBetweenStartDateAndEndDate(@PathVariable("date") String starDate){

        
        return null;
    }

}
