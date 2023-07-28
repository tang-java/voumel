package com.voumel.up.web.mapper;

import com.volume.up.pojo.CheckGroup;
import com.voumel.up.entity.QueryPageBean;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author 小唐
 * @version 1.0
 * @project SpringBoot_Vue_voumel_parent
 * @description
 * @date 2023/7/25 10:38:46
 */
@Mapper
public interface CheckGroupMapper {

    Integer addCheckGroup(CheckGroup checkGroup);

    Integer deleteCheckGroup(Integer checkGroupId);

    Integer deleteTheRelationshipBetweenCheckGroupsAndCheckItems(Integer checkGroupId);

    CheckGroup findCheckGroupById(Integer id);

    Integer updateCheckGroup(CheckGroup checkGroup);

    List<CheckGroup> findCheckGroup(QueryPageBean queryPageBean);

    Integer addCheckItemToCheckGroup(@Param("checkItemId") Integer checkItemId,@Param("checkGroupId") Integer checkGroupId);

    List<CheckGroup> findCheckGroupAndCheckItem(QueryPageBean queryPageBean);

    Integer addCheckGroup(Integer checkGroupId, Integer checkItemId);

    List<CheckGroup> findALLCheckGroup();

}
