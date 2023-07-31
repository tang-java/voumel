package com.voumel.up.web.mapper;

import com.volume.up.pojo.CheckGroup;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author 小唐
 * @version 1.0
 * @project SpringBoot_Vue_voumel_parent
 * @description
 * @date 2023/7/31 11:41:56
 */
@Mapper
public interface SetMealAndCheckGroupMapper {

    void deleteCheckGroupOfSetMeal(Integer setMealId);

    Integer batchAddCheckGroupToSetMeal(Integer[] checkGroupIds, Integer setMealId);

    List<CheckGroup> findCheckGroupBySetMealId(Integer setMealId);
}
