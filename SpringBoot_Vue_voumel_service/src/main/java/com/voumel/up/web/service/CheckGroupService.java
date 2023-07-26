package com.voumel.up.web.service;

import com.github.pagehelper.PageInfo;
import com.volume.up.pojo.CheckGroup;
import com.voumel.up.entity.PageResult;
import com.voumel.up.entity.QueryPageBean;

/**
 * @author 小唐
 * @version 1.0
 * @project SpringBoot_Vue_voumel_parent
 * @description
 * @date 2023/7/25 10:22:59
 */
public interface CheckGroupService {
    Integer addCheckGroup(CheckGroup checkGroup);

    Integer deleteCheckGroup(Integer id);

    CheckGroup findCheckGroupById(Integer id);

    Integer updateCheckGroup(CheckGroup checkGroup);

    Integer updateCheckGroup(CheckGroup checkGroup,Integer[] checkItemIds);

    PageResult findCheckGroup(QueryPageBean queryPageBean);

    Integer AddCheckItemsToTheCheckGroupByCheckGroupId(Integer[] checkItemIds, Integer checkGroupId);

    PageResult findAllCheckItemByCheckGroup(QueryPageBean queryPageBean);

    CheckGroup findAllCheckItemByCheckGroupId(Integer checkGroupId);

    Integer addCheckGroup(CheckGroup checkGroup, Integer[] checkItemIds);
}
