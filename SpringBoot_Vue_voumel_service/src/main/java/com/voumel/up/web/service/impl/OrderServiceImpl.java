package com.voumel.up.web.service.impl;

import com.volume.up.pojo.Member;
import com.volume.up.pojo.Order;
import com.volume.up.pojo.OrderSetting;
import com.voumel.up.constant.MessageConstant;
import com.voumel.up.entity.Result;
import com.voumel.up.web.mapper.MemberMapper;
import com.voumel.up.web.mapper.OrderMapper;
import com.voumel.up.web.mapper.OrderSettingMapper;
import com.voumel.up.web.service.OrderService;
import com.voumel.up.web.service.OrderSettingService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

/**
 * @author 小唐
 * @version 1.0
 * @project SpringBoot_Vue_voumel_parent
 * @description
 * @date 2023/8/1 19:06:14
 */
@Service
public class OrderServiceImpl implements OrderService {
    @Resource
    private OrderMapper orderMapper;
    @Resource
    private OrderSettingService orderSettingService;

    @Resource
    private OrderSettingMapper orderSettingMapper;
    @Resource
    private MemberMapper memberMapper;

    @Override
    public Result addOrder(Map<String, Object> orderMap) {
        //TODO 分布式锁
        String name = (String) orderMap.get("name");
        String sex = (String) orderMap.get("sex");
        String idCard = (String) orderMap.get("idCard");
        String phoneNum = (String) orderMap.get("phoneNum");
        String setMealId = (String) orderMap.get("setMealId");

        //1.要先检查当前日期是否可以预约
        String orderDate = (String) orderMap.get("orderDate");
//        OrderSettingService.findOrderSettingByOrderDate(orderDate);

        OrderSetting orderSetting = orderSettingService.findOrderSettingByOrderDate(orderDate);
        if (orderSetting == null) {
            return new Result(false, MessageConstant.SELECTED_DATE_CANNOT_ORDER);
        }
        //2.检查当前日期是否预约已满
        if (orderSetting.getReservations() >= orderSetting.getNumber()) {
            return new Result(false, MessageConstant.ORDER_FULL);
        }
        //3.是否是会员，如果不是，那么自动注册会员
        Member member = memberMapper.findMemberByPhoneNum(phoneNum);
        if (member == null) {
            //注册会员
            Date regTime = new Date();
            member = new Member();
            member.setPhoneNumber(phoneNum);
            member.setName(name);
            member.setRegTime(regTime);
            member.setSex(sex);
            member.setIdCard(idCard);
            memberMapper.addMember(member);//这里要主键回填
        }
        //3.如果是会员那么检查是否重复预约
        Order order=new Order();
        order.setSetMealId(Integer.valueOf(setMealId));
        try {
            Date date = new SimpleDateFormat("yyyy-MM-dd").parse(orderDate);
            order.setOrderDate(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        order.setMemberId(member.getId());
        Order orderDB=orderMapper.findConditionOrder(order);
        if (orderDB!=null){
            return new Result(false,MessageConstant.HAS_ORDERED);
        }
        //5.进行预约，更新已预约人数，插入到数据库，状态为未支付----
        try {
            order.setOrderType("微信");
            order.setOrderStatus(1);
            orderMapper.addOrder(order);
            //修改orderSetting表
            orderSetting.setReservations(orderSetting.getReservations()+1);
            orderSettingMapper.updateOrderSettingIsreservationByOrderDate(orderSetting);
            return new Result(true,MessageConstant.ORDER_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,MessageConstant.ORDER_FAIL);
        }
    }
}
