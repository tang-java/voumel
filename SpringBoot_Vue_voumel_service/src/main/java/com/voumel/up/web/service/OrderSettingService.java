package com.voumel.up.web.service;

import com.volume.up.pojo.OrderSetting;

import java.util.Date;

/**
 * @author 小唐
 * @version 1.0
 * @project SpringBoot_Vue_voumel_parent
 * @description
 * @date 2023/7/29 19:15:49
 */
public interface OrderSettingService {
//    OrderSetting findOrderSettingByOrderDate(Date orderDate);

    OrderSetting findOrderSettingByOrderDate(String orderDate);
}
