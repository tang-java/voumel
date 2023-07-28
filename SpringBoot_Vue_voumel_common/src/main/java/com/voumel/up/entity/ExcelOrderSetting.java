package com.voumel.up.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ContentRowHeight;
import com.alibaba.excel.annotation.write.style.HeadRowHeight;

import java.util.Date;

/**
 * @author 小唐
 * @version 1.0
 * @project SpringBoot_Vue_voumel_parent
 * @description 预约设置excel表的实体类
 * @date 2023/7/28 20:03:36
 */
@HeadRowHeight(1)
@ContentRowHeight(1)
public class ExcelOrderSetting {
    @ExcelProperty(value = "预约日期",index = 0)
    private Date orderDate;
    @ExcelProperty(value = "可预约人数",index = 1)
    private Integer number;

    public ExcelOrderSetting() {
    }

    public ExcelOrderSetting(Date orderDate, Integer number) {
        this.orderDate = orderDate;
        this.number = number;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

}
