package com.voumel.up.web.service;

import com.volume.up.pojo.CheckItem;
import com.voumel.up.entity.PageResult;
import com.voumel.up.entity.QueryPageBean;

import java.util.List;

/**
 * @author 小唐
 * @version 1.0
 * @project SpringBoot_Vue_voumel_parent
 * @description
 * @date 2023/7/24 15:23:09
 */
public interface CheckItemService {
    PageResult findItemByConditionAndPaging(QueryPageBean queryPageBean);

    Integer addCheckItem(CheckItem checkItem);

    CheckItem findCheckItemById(Integer id);

    Integer updateCheckItemStatus(CheckItem checkItem);

    Integer updateCheckItem(CheckItem checkItem);

    List<CheckItem> findAllCheckItem();

}
