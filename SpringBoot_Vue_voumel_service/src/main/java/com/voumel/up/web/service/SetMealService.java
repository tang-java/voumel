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

    SetMeal findSetMealById(Integer id);

    void addSetMeal(SetMeal setMeal, Integer[] checkGroupIds);
}
