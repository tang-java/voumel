package com.voumel.up.web.service;

import com.voumel.up.entity.Result;

import java.util.Map;

/**
 * @author 小唐
 * @version 1.0
 * @project SpringBoot_Vue_voumel_parent
 * @description
 * @date 2023/8/1 19:06:02
 */
public interface OrderService {
    Result addOrder(Map<String, Object> orderMap);
}
