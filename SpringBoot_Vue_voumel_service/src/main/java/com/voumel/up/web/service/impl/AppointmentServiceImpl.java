package com.voumel.up.web.service.impl;

import com.github.pagehelper.PageHelper;
import com.volume.up.pojo.Order;
import com.voumel.up.entity.QueryPageBean;
import com.voumel.up.web.mapper.OrderMapper;
import com.voumel.up.web.service.AppointmentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 小唐
 * @version 1.0
 * @project SpringBoot_Vue_voumel_parent
 * @description
 * @date 2023/7/29 16:51:39
 */
@Service
public class AppointmentServiceImpl implements AppointmentService {
    @Resource
    private OrderMapper orderMapper;

    @Override
    public List<Order> findOrderByStatusAndPaging(QueryPageBean queryPageBean) {
        Integer currentPage = queryPageBean.getCurrentPage();
        Integer pageSize = queryPageBean.getPageSize();
        String status = queryPageBean.getQueryString();
        if (currentPage == null || currentPage == 0) {
            currentPage = 1;
        }
        if (pageSize == null || pageSize == 0) {
            pageSize = 5;
        }
        PageHelper.startPage(currentPage,pageSize);
        List<Order> orderList = orderMapper.findAllOrderByOrderStatus(Integer.valueOf(status));
        return orderList;
    }
}
