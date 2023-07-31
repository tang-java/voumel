package com.voumel.up.web.controller;

import com.volume.up.pojo.Order;
import com.voumel.up.constant.MessageConstant;
import com.voumel.up.entity.QueryPageBean;
import com.voumel.up.entity.Result;
import com.voumel.up.web.service.AppointmentService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 小唐
 * @version 1.0
 * @project SpringBoot_Vue_voumel_parent
 * @description
 * @date 2023/7/29 16:35:49
 */
@RestController
public class AppointmentController {
    @Resource
    private AppointmentService appointmentService;

    /**
     * 根据状态分页查询订单信息
     *
     * @param currentPage --当前页码
     * @param pageSize    --当前页面大小
     * @param status      --订单状态
     * @return result
     */
    @GetMapping("/appointment/{page:\\d+}/{pageSize:\\d+}")
    public Result findOrderByStatusAndPaging(@PathVariable("page") Integer currentPage, @PathVariable Integer pageSize, @RequestParam(defaultValue = "1") Integer status) {
        QueryPageBean queryPageBean = new QueryPageBean(currentPage, pageSize, String.valueOf(status));
        try {
            List<Order> orderList=appointmentService.findOrderByStatusAndPaging(queryPageBean);
            return new Result(true, MessageConstant.QUERY_ORDER_SUCCESS,orderList);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,MessageConstant.QUERY_ORDER_FAIL);
        }
    }



}
