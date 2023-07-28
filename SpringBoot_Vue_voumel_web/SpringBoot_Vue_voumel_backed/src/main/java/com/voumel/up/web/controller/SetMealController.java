package com.voumel.up.web.controller;

import com.volume.up.pojo.SetMeal;
import com.voumel.up.constant.MessageConstant;
import com.voumel.up.entity.PageResult;
import com.voumel.up.entity.QueryPageBean;
import com.voumel.up.entity.Result;
import com.voumel.up.web.service.SetMealService;
import org.springframework.web.bind.annotation.*;

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
     *
     * @param currentPage ---当前页码
     * @param pageSize    ---当前页面大小
     * @param queryString ---模糊查询的数据
     * @return
     */
    @GetMapping("/setMeal/{page}/{pageSize}")
    public Result findSetMeal(@PathVariable("page") Integer currentPage, @PathVariable Integer pageSize, String queryString) {
        QueryPageBean queryPageBean = new QueryPageBean(currentPage, pageSize, queryString);
        try {
            PageResult pageResult = setMealService.findSetMeal(queryPageBean);
            return new Result(true, MessageConstant.QUERY_SETMEALLIST_SUCCESS, pageResult);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.QUERY_SETMEALLIST_FAIL);
        }
    }

    /**
     * 新增套餐，并将检查组加入套餐中
     *
     * @param setMeal
     * @param checkGroupIds
     * @return
     */
    @PostMapping("/setMeal/{ids}")
    public Result addSetMeal(@RequestBody SetMeal setMeal, @PathVariable("ids") Integer[] checkGroupIds) {
        if (setMeal != null) {
            try {
                setMealService.addSetMeal(setMeal, checkGroupIds);
                return new Result(true, MessageConstant.ADD_SETMEAL_SUCCESS);
            } catch (Exception e) {
                e.printStackTrace();
                return new Result(false, MessageConstant.ADD_SETMEAL_FAIL);
            }
        }
        return new Result(false, MessageConstant.ADD_SETMEAL_FAIL + "可能的原因是参数没有封装");
    }
}
