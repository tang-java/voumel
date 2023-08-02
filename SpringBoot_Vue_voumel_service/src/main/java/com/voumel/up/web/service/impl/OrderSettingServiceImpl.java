package com.voumel.up.web.service.impl;

import com.volume.up.pojo.OrderSetting;
import com.voumel.up.web.mapper.OrderSettingMapper;
import com.voumel.up.web.service.OrderSettingService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author 小唐
 * @version 1.0
 * @project SpringBoot_Vue_voumel_parent
 * @description
 * @date 2023/7/31 08:44:33
 */
@Service
public class OrderSettingServiceImpl implements OrderSettingService {
    @Resource
    private OrderSettingMapper orderSettingMapper;

//    @Override
//    public OrderSetting findOrderSettingByOrderDate(Date orderDate) {
//        OrderSetting orderSetting = orderSettingMapper.findOrderSettingByOrderDate(orderDate);
//        return orderSetting;
//    }

    @Override
    public OrderSetting findOrderSettingByOrderDate(String orderDate) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = dateFormat.parse(orderDate);
            OrderSetting orderSetting = orderSettingMapper.findOrderSettingByOrderDate(date);
            return orderSetting;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}
