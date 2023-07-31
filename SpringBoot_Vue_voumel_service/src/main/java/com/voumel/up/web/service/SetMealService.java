package com.voumel.up.web.service;

import com.volume.up.pojo.SetMeal;
import com.voumel.up.entity.PageResult;
import com.voumel.up.entity.QueryPageBean;

/**
 * @author 小唐
 * @version 1.0
 * @project SpringBoot_Vue_voumel_parent
 * @description
 * @date 2023/7/26 19:53:03
 */
public interface SetMealService {
    PageResult findSetMeal(QueryPageBean queryPageBean);

    /**
     * 这里有两个选择，
     * 一：在服务层进行分别查询，然后封装
     * 二：在持久层进行联表查询
     * @param setMealId
     * @return
     */
    SetMeal findSetMealById(Integer setMealId);

    void addSetMeal(SetMeal setMeal, Integer[] checkGroupIds);

    Integer updateSetMeal(SetMeal setMeal, Integer[] checkGroupIds);

    Integer updateSetMeal(SetMeal setMeal);
}
