package com.voumel.up.web.mapper;

import com.volume.up.pojo.CheckItem;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author 小唐
 * @version 1.0
 * @project SpringBoot_Vue_voumel_parent
 * @description
 * @date 2023/7/26 19:11:49
 */
@Mapper
public interface CheckGroupAndCheckItemMapper {
    Integer deleteRelationshipOfCheckGroupAndCheckItem(@Param("checkGroupId") Integer checkGroupId);

    List<CheckItem> findCheckItemOfCheckGroupOfSetMeal(@Param("checkGroup_Id") Integer checkGroupId);
}
