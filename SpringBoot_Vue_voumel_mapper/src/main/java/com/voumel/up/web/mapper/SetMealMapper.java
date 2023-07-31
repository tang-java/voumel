package com.voumel.up.web.mapper;

import com.volume.up.pojo.SetMeal;
import com.voumel.up.entity.QueryPageBean;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author 小唐
 * @version 1.0
 * @project SpringBoot_Vue_voumel_parent
 * @description
 * @date 2023/7/26 19:54:13
 */
@Mapper
public interface SetMealMapper {
    List<SetMeal> findSetMealByPageHelper(QueryPageBean queryPageBean);

    SetMeal findSetMealById(Integer setMealId);

    Integer addSetMeal(SetMeal setMeal);

    Integer addCheckGroupToSetMeal(@Param("setMealId") Integer setMealId,@Param("checkGroupId") Integer checkGroupId);

    Integer updateSetMeal(SetMeal setMeal);
}
