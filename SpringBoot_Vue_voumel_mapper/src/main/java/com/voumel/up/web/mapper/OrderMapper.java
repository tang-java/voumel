package com.voumel.up.web.mapper;

import com.volume.up.pojo.Order;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author 小唐
 * @version 1.0
 * @project SpringBoot_Vue_voumel_parent
 * @description
 * @date 2023/7/29 16:53:58
 */
@Mapper
public interface OrderMapper {

    List<Order> findAllOrderByOrderStatus(@Param("orderStatus") Integer orderStatus);
}
