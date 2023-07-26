package com.voumel.up.web.controller;

import com.voumel.up.constant.MessageConstant;
import com.voumel.up.entity.PageResult;
import com.voumel.up.entity.QueryPageBean;
import com.voumel.up.entity.Result;
import com.voumel.up.web.service.SetMealService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author 小唐
 * @version 1.0
 * @project SpringBoot_Vue_voumel_parent
 * @description 这是菜单的Controller
 * @date 2023/7/26 19:47:20
 */
@RestController
public class SetMealController {
    @Resource
    private SetMealService setMealService;
    /**
     * 根据条件查询所有的套餐
     * @param currentPage ---当前页码
     * @param pageSize ---当前页面大小
     * @param queryString ---模糊查询的数据
     * @return
     */
    @GetMapping("/setMeal/{page}/{pageSize}")
    public Result findSetMeal(@PathVariable("page") Integer currentPage, @PathVariable Integer pageSize,String queryString){
        QueryPageBean queryPageBean = new QueryPageBean(currentPage, pageSize, queryString);
        try {
            PageResult pageResult=setMealService.findSetMeal(queryPageBean);
            return new Result(true, MessageConstant.QUERY_SETMEALLIST_SUCCESS,pageResult);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,MessageConstant.QUERY_SETMEALLIST_FAIL);
        }
    }
}
