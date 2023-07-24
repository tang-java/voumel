package com.voumel.up.web.mapper;

import com.volume.up.pojo.CheckItem;
import com.voumel.up.entity.QueryPageBean;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author 小唐
 * @version 1.0
 * @project SpringBoot_Vue_voumel_parent
 * @description
 * @date 2023/7/24 15:27:29
 */
@Mapper
public interface CheckItemMapper {
    List<CheckItem> findItemByConditionAndPaging(QueryPageBean queryPageBean);

    Integer addCheckItem(CheckItem checkItem);
}
