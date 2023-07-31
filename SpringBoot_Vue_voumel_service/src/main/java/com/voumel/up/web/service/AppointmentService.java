package com.voumel.up.web.service;

import com.volume.up.pojo.Order;
import com.voumel.up.entity.QueryPageBean;

import java.util.List;

/**
 * @author 小唐
 * @version 1.0
 * @project SpringBoot_Vue_voumel_parent
 * @description 预约
 * @date 2023/7/29 16:51:12
 */
public interface AppointmentService {
    List<Order> findOrderByStatusAndPaging(QueryPageBean queryPageBean);
}
