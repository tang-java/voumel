package com.voumel.up.web.mapper;

import com.volume.up.pojo.OrderSetting;
import org.apache.ibatis.annotations.Mapper;

import java.util.Date;

/**
 * @author 小唐
 * @version 1.0
 * @project SpringBoot_Vue_voumel_parent
 * @description
 * @date 2023/8/1 19:38:35
 */
@Mapper
public interface OrderSettingMapper {
    OrderSetting findOrderSettingByOrderDate(Date orderDate);

    void updateOrderSettingIsreservationByOrderDate(OrderSetting orderSetting);

}
